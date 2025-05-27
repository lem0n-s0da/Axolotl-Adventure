package com.eisenreich.axolotladventure;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {
    private int level, lives = 3, collectibles = 0;
    private float playerX = 100, playerY = 500, velocityY = 0;
    private Paint paint = new Paint();
    private Handler handler = new Handler();
    private long startTime;

    private final Runnable GAME_LOOP = new Runnable() {
        @Override
        public void run() {
            update();
            invalidate();
            handler.postDelayed(this, 16);
        }
    };
    public GameView(Context context, int level) {
        super(context);
        this.level = level;
        setBackgroundColor(Color.rgb(240, 248, 255));
        startTime = System.currentTimeMillis();
        handler.post(GAME_LOOP);
    }
    private void update() {
        velocityY += 2;
        playerY += velocityY;

        // ground collision
        if (playerY >= 800) {
            playerY = 800;
            velocityY = 0;
        }
        // fake finish line
        if (playerX > 1800 && playerY >= 750) {
            long time = System.currentTimeMillis() - startTime;
            saveProgress(time, collectibles);
            getContext().startActivity(new Intent(getContext(), MainActivity.class));
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // ground
        paint.setColor(Color.GREEN);
        canvas.drawRect(0, 850, 2000, 1000, paint);
        // player
        paint.setColor(Color.MAGENTA);
        canvas.drawCircle(playerX, playerY, 50, paint);
        // finish
        paint.setColor(Color.YELLOW);
        canvas.drawRect(1850, 750, 1900, 850, paint);
        // UI
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);
        canvas.drawText("Lives: " + lives, 1600, 100, paint);
        canvas.drawText("Level " + level, 1600, 150, paint);
        canvas.drawText("Collectibles " + collectibles, 1600, 200, paint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && playerY >= 800) {
            velocityY = -40; // jump
        }
        playerX += 40;
        return true;
    }
    private void saveProgress(long time, int collected) {
        SharedPreferences prefs = getContext().getSharedPreferences("GameData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("level" + level + "_completed", true);
        editor.putInt("current_level", Math.min(level + 1, 3));
        long bestTime = prefs.getLong("level" + level + "_time", Long.MAX_VALUE);
        if (time < bestTime) {
            editor.putLong("level" + level + "_time", time);
        }
        int bestCollect = prefs.getInt("level" + level + "_collectibles", 0);
        if (collected > bestCollect) {
            editor.putInt("level" + level + "_collectibles", collected);
        }
        editor.apply();
    }
}
