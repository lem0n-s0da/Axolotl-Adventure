package com.eisenreich.axolotladventure;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

abstract class GameObject {
    float x, y, width, height;

    public RectF getRect() {
        return new RectF(x, y, x + width, y + height);
    }

    abstract void draw(Canvas canvas, Paint paint);
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
    public boolean collected = false;

    public Collectable(float x, float y) {
        this.x = x; this.y = y;
        this.width = 40; this.height = 40;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        if (!collected) {
            paint.setColor(Color.MAGENTA);
            canvas.drawOval(x, y, x + width, y + height, paint);
        }
    }
}

class Goal extends GameObject {
    public Goal(float x, float y) {
        this.x = x; this.y = y;
        this.width = 80; this.height = 80;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.GREEN);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }
}
