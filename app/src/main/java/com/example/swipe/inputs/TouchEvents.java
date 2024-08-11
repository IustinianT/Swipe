package com.example.swipe.inputs;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;

import com.example.swipe.entities.Player;

public class TouchEvents {

    private boolean touchDown;
    private Player player;
    private float xStart, yStart;
    private float xTouch, yTouch;
    private boolean moved;
    private float radius = 50;
    private Paint linePaint, circlePaint;

    public TouchEvents(Player player) {
        this.player = player;
        linePaint = new Paint();
        linePaint.setColor(Color.YELLOW);
        circlePaint = new Paint();
        circlePaint.setColor(Color.WHITE);
    }

    public void draw(Canvas c) {
        if (touchDown) {
            // draw circle from initial touch down
            c.drawCircle(
                xStart, yStart, radius, circlePaint
            );

            // draw line from player to direction of touch move
            if (moved) {
                c.drawLine(
                    player.getPos().x, player.getPos().y,
                    player.getPos().x + (xTouch - xStart), player.getPos().y + (yTouch - yStart),
                    linePaint);
            }
        }
    }

    public boolean touchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                touchDown = true;
                xStart = event.getX();
                yStart = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                if (touchDown) {
                    moved = true;
                    xTouch = event.getX();
                    yTouch = event.getY();
                }
                break;

            case MotionEvent.ACTION_UP:
                // remove unwanted movement if player taps the screen
                if (moved) {
                    PointF directionVel = new PointF(
                        xTouch - xStart,
                        yTouch - yStart);
                    player.push(directionVel);
                }
                touchDown = false;
                moved = false;
                break;
        }

        return true;
    }
}
