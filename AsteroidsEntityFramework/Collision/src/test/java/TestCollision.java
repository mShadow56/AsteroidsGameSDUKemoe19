package dk.sdu.mmmi.cbse.collisionsystem;

import dk.mmmi.cbse.collision.Collision;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestCollision {

    private Entity player = new Entity();
    private Entity enemy = new Entity();
    private Entity player2 = new Entity();
    private Entity enemy2 = new Entity();
    private Collision collision = new Collision();


    @BeforeEach
    void setUp() {
        player.setRadius(5);
        enemy.setRadius(5);
        player2.setRadius(1);
        enemy2.setRadius(1);
    }

    @Test
    void testIsColliding() {
        player.add(new PositionPart(5, 5, 10));
        enemy.add(new PositionPart(5, 5, 10));

        assertTrue(collision.isColliding(player, enemy));
    }

    @Test
    void testIsColliding2() {
        player2.add(new PositionPart(1, 1, 10));
        enemy2.add(new PositionPart(30, 30, 10));

        assertFalse(collision.isColliding(player2, enemy2));
    }
}
