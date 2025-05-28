package com.eisenreich.axolotladventure;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Player {
    public float x, y;
    private float width = 100, height = 100;
    private float speed = 10;
    public int collected = 0;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        // scrolling
        x += speed;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLUE);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    public boolean collidesWith(GameObject object) {
        RectF playerRect = new RectF(x, y, x + width, y + height);
        return playerRect.intersects(playerRect, object.getRect());
    }

    public void respawn(float startX, float startY) {
        this.x = startX;
        this.y = startY;
    }
}
