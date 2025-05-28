package com.eisenreich.axolotladventure;

import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

public class LevelData {
    public static Level getLevel(int number) {
        Level level = new Level(100, 700); // Starting position

        switch (number) {
            case 1:
                level.obstacles.add(new Obstacle(500, 700, 100, 100));
                level.obstacles.add(new Obstacle(900, 700, 100, 100));
                level.collectables.add(new Collectable(300, 650));
                level.collectables.add(new Collectable(800, 650));
                level.goal = new Goal(1200, 700);
                break;

            case 2:
                level.obstacles.add(new Obstacle(600, 650, 100, 150));
                level.obstacles.add(new Obstacle(1000, 600, 100, 200));
                level.collectables.add(new Collectable(400, 620));
                level.collectables.add(new Collectable(950, 580));
                level.collectables.add(new Collectable(1300, 650));
                level.goal = new Goal(1500, 700);
                break;

            case 3:
                level.obstacles.add(new Obstacle(500, 680, 200, 120));
                level.obstacles.add(new Obstacle(1100, 700, 100, 100));
                level.obstacles.add(new Obstacle(1600, 660, 150, 140));
                level.collectables.add(new Collectable(400, 650));
                level.collectables.add(new Collectable(1050, 660));
                level.collectables.add(new Collectable(1400, 690));
                level.collectables.add(new Collectable(1800, 640));
                level.goal = new Goal(2000, 700);
                break;
        }

        return level;
    }
}
