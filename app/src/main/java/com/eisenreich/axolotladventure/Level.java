package com.eisenreich.axolotladventure;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

public class Level {
    public float startX, startY;

    // Collections for different objects in the level.
    private ArrayList<Platform> platforms;
    private ArrayList<Collectable> collectables;
    private Goal goal;

    public Level(float startX, float startY) {
        this.startX = startX;
        this.startY = startY;
        platforms = new ArrayList<>();
        collectables = new ArrayList<>();
    }

    // Add a platform to the level.
    public void addPlatform(Platform platform) {
        platforms.add(platform);
    }

    // Add a collectable item to the level.
    public void addCollectable(Collectable collectable) {
        collectables.add(collectable);
    }

    // Set the goal object.
    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    // Getters for our object lists.
    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public ArrayList<Collectable> getCollectables() {
        return collectables;
    }

    public Goal getGoal() {
        return goal;
    }

    // Draws all objects of the level.
    public void draw(Canvas canvas, Paint paint) {
        for (Platform platform : platforms) {
            platform.draw(canvas, paint);
        }
        for (Collectable collectable : collectables) {
            collectable.draw(canvas, paint);
        }
        if (goal != null) {
            goal.draw(canvas, paint);
        }
    }
}
