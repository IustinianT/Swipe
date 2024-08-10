package com.example.swipe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.swipe.entities.Enemy;
import com.example.swipe.entities.Player;

import java.util.Random;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private GameLoop gameLoop;
    private Random rand = new Random();

    private Player player;
    private Operator operator;

    public GamePanel(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);

        gameLoop = new GameLoop(this);

        player = new Player();
        operator = new Operator();
    }

    public void render() {
        Canvas c = holder.lockCanvas();
        c.drawColor(Color.BLACK);

        operator.drawEnemies(c);
        player.draw(c);

        holder.unlockCanvasAndPost(c);
    }

    public void update(double delta) {
        operator.updateEnemies(delta);
        player.update(delta);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            PointF directionVel = new PointF(
                    event.getX() - player.getPos().x,
                    event.getY() - player.getPos().y);
            player.push(directionVel);

            if (operator.getEnemies().size() < Operator.MAX_ENEMIES) {
                operator.addEnemy(new Enemy());
            }
        }
        return true;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.startGameLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}
