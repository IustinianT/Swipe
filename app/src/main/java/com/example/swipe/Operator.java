package com.example.swipe;

import android.graphics.Canvas;

import com.example.swipe.entities.Enemy;
import com.example.swipe.entities.Entity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Operator {

    private ArrayList<Enemy> enemies;
    private long lastSpawn;

    public static int MAX_ENEMIES = 5;
    public static int ENEMY_SPAWN_INTERVAL = 5;

    public Operator() {
        enemies = new ArrayList<>();
        lastSpawn = System.currentTimeMillis();
    }

    public void handleSpawn() {
        if (enemies.size() < MAX_ENEMIES) {
            if ((System.currentTimeMillis() - lastSpawn) / 1000 > ENEMY_SPAWN_INTERVAL) {
                addEnemy(new Enemy());
                lastSpawn = System.currentTimeMillis();
            }
        }
    }

    public void updateEnemies(double delta) {
        for (Enemy enemy : enemies) {
            enemy.update(delta);
        }
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        ArrayList<Enemy> newEnemies = new ArrayList<>();
        for (Enemy existingEnemy : enemies) {
            if (existingEnemy != enemy) {
                newEnemies.add(existingEnemy);
            }
        }
        enemies = newEnemies;
    }

    public void drawEnemies(Canvas c) {
        for (Enemy enemy : enemies) {
            enemy.draw(c);
        }
    }
}
