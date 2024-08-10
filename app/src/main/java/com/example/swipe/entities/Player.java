package com.example.swipe.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;

public class Player {

    private PointF position;
    private PointF velocity;
    private int radius;
    private Paint playerPaint;

    private float impulseMultiplier;
    private float friction;

    public Player() {
        position = new PointF(500, 500);
        velocity = new PointF(0, 0);
        radius = 40;
        playerPaint = new Paint();
        playerPaint.setColor(Color.YELLOW);

        impulseMultiplier = 3f;
        friction = 0.08f;
    }

    public void update(double delta) {
        position.x += velocity.x * delta * impulseMultiplier;
        position.y += velocity.y * delta * impulseMultiplier;

        velocity.x *= (1 - friction);
        velocity.y *= (1 - friction);
    }

    public void push(PointF velocity) {
        this.velocity = velocity;
    }

    public void draw(Canvas c) {
        c.drawCircle(position.x, position.y, radius, playerPaint);
    }

    public PointF getPos() {
        return position;
    }
}
