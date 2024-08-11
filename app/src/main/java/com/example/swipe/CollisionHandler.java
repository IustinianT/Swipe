package com.example.swipe;

import com.example.swipe.entities.Enemy;
import com.example.swipe.entities.Player;

public class CollisionHandler {

    private Operator operator;
    private Player player;

    public CollisionHandler(Operator operator, Player player) {
        this.operator = operator;
        this.player = player;
    }

    public void checkAndHandleCollisions() {
        for (Enemy enemy : operator.getEnemies()) {
            if (player.collidesWith(enemy)) {
                handleCollisionWithEnemy(enemy);
            }
        }
    }

    // TODO: handle 2D collision with appropriate forces
    public void handleCollisionWithEnemy(Enemy enemy) {
        // deal damage to collided object if this.velocity > that.velocity
        double playerMagnitude = Math.sqrt(
                Math.pow(player.getVelocity().x, 2) +
                Math.pow(player.getVelocity().y, 2));
        double entityMagnitude = Math.sqrt(
                Math.pow(enemy.getVelocity().x, 2) +
                Math.pow(enemy.getVelocity().y, 2));

        if (playerMagnitude >= entityMagnitude) {
            operator.removeEnemy(enemy);
        }
    }
}
