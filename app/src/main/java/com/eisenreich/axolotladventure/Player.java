package com.eisenreich.axolotladventure;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Player {
    private float x, y;
    private float width, height;
    private float velocityX = 0;
    private float velocityY = 0;
    private float gravity = 1.0f;
    private float jumpStrength = -20f;
    private float moveSpeed = 10f;
    private boolean onGround = false;
    private int lives = 3;
    private float startX, startY;
    private Paint paint;

    public Player(float startX, float startY, float width, float height) {
        this.startX = startX;
        this.startY = startY;
        this.x = startX;
        this.y = startY;
        this.width = width;
        this.height = height;
        paint = new Paint();
        paint.setColor(Color.MAGENTA);
    }

    public void update() {
        velocityY += gravity;
        x += velocityX;
        y += velocityY;
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
        if (obj == null) return false;
        return RectF.intersects(getBounds(), obj.getBounds());
    }

    public void handleCollision(GameObject obj) {
        RectF playerRect = getBounds();
        RectF objRect = obj.getBounds();

        if (playerRect.bottom > objRect.top && velocityY > 0) {
            y = objRect.top - height;
            velocityY = 0;
            onGround = true;
        }
    }

    public void respawn() {
        x = startX;
        y = startY;
        velocityX = 0;
        velocityY = 0;
    }

    public void loseLife() {
        lives--;
    }

    public boolean isDead() {
        return lives <= 0;
    }

    public RectF getBounds() {
        return new RectF(x, y, x + width, y + height);
    }

    public float getY() {
        return y;
    }

    public int getLives() {
        return lives;
    }
}
