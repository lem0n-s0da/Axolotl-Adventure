package com.eisenreich.axolotladventure;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

abstract class GameObject {
    protected float x, y, width, height;

    // Draw this object on the provided canvas using the given Paint.
    public abstract void draw(Canvas canvas, Paint paint);

    // Returns the bounding rectangle for collision detection.
    public RectF getBounds() {
        return new RectF(x, y, x + width, y + height);
    }
}

class Platform extends GameObject {
    public Platform(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.DKGRAY);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }
}

class Obstacle extends GameObject {
    public Obstacle(float x, float y, float w, float h) {
        this.x = x; this.y = y; this.width = w; this.height = h;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.RED);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }
}

class Collectable extends GameObject {
    private boolean collected = false;
    private int value = 10; // Value of the collectable

    public Collectable(float x, float y, float diameter) {
        this.x = x;
        this.y = y;
        this.width = diameter;
        this.height = diameter;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        if (!collected) {
            paint.setColor(Color.YELLOW);
            // Draw an oval as the collectable object.
            RectF bounds = getBounds();
            canvas.drawOval(bounds, paint);
        }
    }

    public boolean isCollected() {
        return collected;
    }

    public void collect() {
        collected = true;
    }

    public int getValue() {
        return value;
    }
}

class Goal extends GameObject {

    public Goal(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.GREEN);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }
}
