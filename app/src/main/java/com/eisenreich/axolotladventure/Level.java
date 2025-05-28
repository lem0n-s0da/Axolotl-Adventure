package com.eisenreich.axolotladventure;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

public class Level {
    public float startX, startY;
    public ArrayList<Obstacle> obstacles;
    public ArrayList<Collectable> collectables;
    public Goal goal;

    public Level(float startX, float startY) {
        this.startX = startX;
        this.startY = startY;
        obstacles = new ArrayList<>();
        collectables = new ArrayList<>();
    }

    public void draw(Canvas canvas, Paint paint) {
        for (Obstacle o : obstacles) o.draw(canvas, paint);
        for (Collectable c : collectables) c.draw(canvas, paint);
        goal.draw(canvas, paint);
    }
}
