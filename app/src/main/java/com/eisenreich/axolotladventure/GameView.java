package com.eisenreich.axolotladventure;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {
    private Thread thread;
    private boolean isPlaying;
    private SurfaceHolder holder;
    private Paint paint;
    private Player player;
    private int lives = 3;
    private long startTime;
    private Level level;
    private int levelNumber;
    private Context context;

    public GameView(Context context, int levelNumber) {
        super(context);
        this.context = context;
        this.levelNumber = levelNumber;

        holder = getHolder();
        paint = new Paint();

        level = LevelData.getLevel(levelNumber);
        player = new Player(level.startX, level.startY);
        startTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        while (isPlaying) {
            if (!holder.getSurface().isValid()) continue;
            update();
            draw();
        }
    }

    private void update() {
        player.update();

        // Collision with obstacles
        for (Obstacle obstacle : level.obstacles) {
            if (player.collidesWith(obstacle)) {
                lives--;
                if (lives <= 0) {
                    gameOver();
                } else {
                    player.respawn(level.startX, level.startY);
                }
                return;
            }
        }
        // Collect collectables
        for (Collectable c : level.collectables) {
            if (!c.collected && player.collidesWith(c)) {
                c.collected = true;
                player.collected++;
            }
        }
        // Reached goal
        if (player.collidesWith(level.goal)) {
            finishLevel();
        }
    }

    private void draw() {
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        level.draw(canvas, paint);
        player.draw(canvas, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);
        canvas.drawText("Lives: " + lives, 20, 50, paint);
        canvas.drawText("Collected: " + player.collected, 20, 100, paint);
        canvas.drawText("Time: " + (System.currentTimeMillis() - startTime) / 1000 + "s", 20, 150, paint);
        holder.unlockCanvasAndPost(canvas);
    }

    private void gameOver() {
        isPlaying = false;
        // Handle game over logic
        ((Activity) context).runOnUiThread(() -> ((Activity) context).finish());
    }

    private void finishLevel() {
        isPlaying = false;
        // Save progress logic here
        ((Activity) context).runOnUiThread(() -> ((Activity) context).finish());
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
