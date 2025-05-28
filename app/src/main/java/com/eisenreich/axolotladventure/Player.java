package com.eisenreich.axolotladventure;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Player {
    private float x, y;
    private float width, height;
    private float velocityX, velocityY;
    private boolean onGround;
    private int lives;
    private final float gravity = 1.0f;
    private final float jumpStrength = -20f;
    private final float moveSpeed = 10f;
    private final float startX, startY;
    private Paint paint;

    public Player(float startX, float startY, float width, float height) {
        this.startX = startX;
        this.startY = startY;
        this.x = startX;
        this.y = startY;
        this.width = width;
        this.height = height;
        this.velocityX = 0;
        this.velocityY = 0;
        this.onGround = false;
        this.lives = 3;
        this.paint = new Paint();
        paint.setColor(Color.MAGENTA);
    }

    public void update() {
        velocityY += gravity;
        x += velocityX;
        y += velocityY;

        // Floor collision (basic ground clamp)
        if (y + height > GameView.SCREEN_HEIGHT) {
            y = GameView.SCREEN_HEIGHT - height;
            velocityY = 0;
            onGround = true;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    public void moveLeft() {
        velocityX = -moveSpeed;
    }

    public void moveRight() {
        velocityX = moveSpeed;
    }

    public void stop() {
        velocityX = 0;
    }

    public void jump() {
        if (onGround) {
            velocityY = jumpStrength;
            onGround = false;
        }
    }

    public boolean collidesWith(GameObject obj) {
        //return RectF.intersects(getBounds(), obj.getBounds());
        return false;
    }

    public void respawn() {
        x = startX;
        y = startY;
        velocityX = 0;
        velocityY = 0;
    }

    public void loseLife() {
        lives--;
        respawn();
    }

    public boolean isDead() {
        return lives <= 0;
    }

    public RectF getBounds() {
        return new RectF(x, y, x + width, y + height);
    }

    public int getLives() {
        return lives;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
