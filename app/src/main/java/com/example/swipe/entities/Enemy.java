package com.example.swipe.entities;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import com.example.swipe.GamePanel;
import com.example.swipe.MainActivity;
import com.example.swipe.Operator;

public class Enemy extends Entity{

    private long lastMoved;

    public static float MOVE_INTERVAL = 1.5f;

    public Enemy() {
        // TODO: move constant values to a different file/enum/class
        position = new PointF(
                rand.nextInt(MainActivity.GAME_WIDTH),
                rand.nextInt(MainActivity.GAME_HEIGHT));
        velocity = new PointF(0, 0);
        radius = 40;

        impulseMultiplier = 1.5f;
        friction = 0.04f;

        paint = new Paint();
        paint.setColor(Color.RED);

        lastMoved = System.currentTimeMillis();
    }

    @Override
    public void update(double delta) {
        super.update(delta);
        int horizontal;
        int vertical;
        if (rand.nextInt(2) == 1) horizontal = -1; else horizontal = 1;
        if (rand.nextInt(2) == 1) vertical = -1; else vertical = 1;

        // TODO: if NOT selectedDirection: call select direction method

        if ((System.currentTimeMillis() - lastMoved) / 1000 > MOVE_INTERVAL) {
            push(new PointF(
                    rand.nextInt(MainActivity.GAME_WIDTH)*horizontal,
                    rand.nextInt(MainActivity.GAME_HEIGHT)*vertical));
            lastMoved = System.currentTimeMillis();
            // TODO: selectedDirection = false;
        }
        // TODO: decide direction of enemy and draw line towards that direction
    }

    /* TODO: make function to randomly select direction and velocity and return a new PointF
        and set selectedDirection to true
    */
}
