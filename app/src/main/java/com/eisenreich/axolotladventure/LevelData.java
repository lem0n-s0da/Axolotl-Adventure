package com.eisenreich.axolotladventure;

import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

public class LevelData {
    public static Level getLevel(int number) {
        Level level = new Level(100, 700); // Starting position

        switch (number) {
            case 1:
                level.addPlatform(new Platform(50, 700, 100, 100));
                level.addPlatform(new Platform(900, 700, 100, 100));
                level.addCollectable(new Collectable(300, 650, 40));
                level.addCollectable(new Collectable(800, 650, 40));
                level.setGoal(new Goal(1200, 700, 90, 90));
                break;

            case 2:
                level.addPlatform(new Platform(600, 650, 100, 150));
                level.addPlatform(new Platform(1000, 600, 100, 200));
                level.addCollectable(new Collectable(400, 620, 40));
                level.addCollectable(new Collectable(950, 580, 40));
                level.addCollectable(new Collectable(1300, 650, 40));
                level.setGoal(new Goal(1500, 700, 90, 90));
                break;

            case 3:
                level.addPlatform(new Platform(500, 680, 200, 120));
                level.addPlatform(new Platform(1100, 700, 100, 100));
                level.addPlatform(new Platform(1600, 660, 150, 140));
                level.addCollectable(new Collectable(400, 650, 40));
                level.addCollectable(new Collectable(1050, 660, 40));
                level.addCollectable(new Collectable(1400, 690, 40));
                level.addCollectable(new Collectable(1800, 640, 40));
                level.setGoal(new Goal(2000, 700, 90, 90));
                break;
        }

        return level;
    }
}
