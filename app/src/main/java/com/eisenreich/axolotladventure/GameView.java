package com.eisenreich.axolotladventure;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {
    private Thread gameThread;
    private boolean isPlaying;
    private Player player;
    private Level level;
    private Paint paint;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private long startTime;
    private int score = 0;

    public GameView(Context context) {
        super(context);
        init(context);
    }

    public GameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context) {
        player = new Player(100, 100, 50, 50);
        level = new Level(100, 700); // Assume Level class initializes platforms, collectables, and goal
        paint = new Paint();
        startTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            control();
        }
    }

    private void update() {
        if (movingLeft) {
            player.moveLeft();
        } else if (movingRight) {
            player.moveRight();
        } else {
            player.stop();
        }

        player.update();

        // Check collisions with platforms
        for (Platform platform : level.getPlatforms()) {
            if (platform == null) continue;
            if (player.collidesWith(platform)) {
                player.handleCollision(platform);
            }
        }

        // Check collisions with collectables
        for (Collectable collectable : level.getCollectables()) {
            if (collectable == null) continue;
            if (!collectable.isCollected() && player.collidesWith(collectable)) {
                collectable.collect();
                score += collectable.getValue();
            }
        }

        // Check collision with goal
        if (player.collidesWith(level.getGoal())) {
            endGame(true);
        }

        // Check if player fell off the screen
        if (player.getY() > getHeight()) {
            player.loseLife();
            if (player.isDead()) {
                endGame(false);
            } else {
                player.respawn();
            }
        }
    }

    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawColor(Color.CYAN);

            level.draw(canvas, paint);
            player.draw(canvas);

            // Draw score and lives
            paint.setColor(Color.BLACK);
            paint.setTextSize(40);
            canvas.drawText("Score: " + score, 50, 50, paint);
            canvas.drawText("Lives: " + player.getLives(), 50, 100, paint);

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            Thread.sleep(17); // Approximately 60fps
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        isPlaying = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        isPlaying = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public void jump() {
        player.jump();
    }

    private void endGame(boolean levelCompleted) {
        isPlaying = false;
        long timeTaken = System.currentTimeMillis() - startTime;
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("time", timeTaken);
        intent.putExtra("levelCompleted", levelCompleted);
        //getContext().startActivity(intent);
    }
}
