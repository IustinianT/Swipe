package com.example.swipe.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

public class Player extends Entity{

    public Player() {
        position = new PointF(500, 500);
        velocity = new PointF(0, 0);

        radius = 40;
        paint = new Paint();
        paint.setColor(Color.YELLOW);

        impulseMultiplier = 2.5f;
        friction = 0.03f;
    }
}
