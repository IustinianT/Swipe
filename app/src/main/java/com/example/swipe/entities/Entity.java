package com.example.swipe.entities;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.example.swipe.MainActivity;

import java.util.Random;

public abstract class Entity {
    protected PointF position;
    protected PointF velocity;

    protected int radius;
    protected Paint paint;

    protected float impulseMultiplier;
    protected float friction;

    protected Random rand = new Random();

    public void update(double delta) {
        // "bug proof" bouncing
        if (position.x >= MainActivity.GAME_WIDTH) {
            push(new PointF(velocity.x * -1, velocity.y));
            if (velocity.x > 0) {
                velocity.x *= -1;
            }
        }
        else if (position.x <= 0) {
            push(new PointF(velocity.x * -1, velocity.y));
            if (velocity.x < 0) {
                velocity.x *= -1;
            }
        }
        if (position.y >= MainActivity.GAME_HEIGHT) {
            push(new PointF(velocity.x, velocity.y * -1));
            if (velocity.y > 0) {
                velocity.y *= -1;
            }
        }
        else if (position.y <= 0) {
            push(new PointF(velocity.x, velocity.y * -1));
            if (velocity.y < 0) {
                velocity.y *= -1;
            }
        }

        position.x += velocity.x * delta * impulseMultiplier;
        position.y += velocity.y * delta * impulseMultiplier;

        // high fps -> delta = low
        velocity.x *= (1 - friction * delta * 60);
        velocity.y *= (1 - friction * delta * 60);
    }

    public void push(PointF velocity) {
        this.velocity = velocity;
    }

    public void draw(Canvas c) {
        c.drawCircle(position.x, position.y, radius, paint);
    }

    public PointF getPos() {
        return position;
    }
}
