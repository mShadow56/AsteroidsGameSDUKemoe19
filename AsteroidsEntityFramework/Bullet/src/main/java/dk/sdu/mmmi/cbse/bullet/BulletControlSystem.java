package dk.sdu.mmmi.cbse.bullet;

import java.lang.Math;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;

import dk.sdu.mmmi.cbse.common.services.BulletSPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {
    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(dk.sdu.mmmi.cbse.bullet.Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            LifePart lifePart = bullet.getPart(LifePart.class);

            lifePart.reduceExpiration(gameData.getDelta());
            movingPart.setUp(true);

            if (lifePart.getExpiration()<=0){
                world.removeEntity(bullet);
            }



            movingPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);
            lifePart.process(gameData, bullet);

            updateShape(bullet);
        }
    }
    private void updateShape(Entity entity) {

        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();

        float[] shapex = new float[2];
        float[] shapey = new float[2];

        float radians = positionPart.getRadians();
        shapex[0] = x + (float)Math.cos(radians)*2 ;
        shapey[0] = y + (float)Math.sin(radians)*2;

        shapex[1] = x + (float)Math.cos(radians ) * 1;
        shapey[1] = y + (float)Math.sin(radians ) * 1;






        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

    @Override
    public Entity newBullet(Entity entity, GameData gameData, boolean rand) {
        PositionPart positionPart= entity.getPart(PositionPart.class);

        float deacceleration = 10;
        float acceleration = 5000;
        float maxSpeed = 320;
        float rotationSpeed = 5;
        float xs = positionPart.getX();
        float ys = positionPart.getY();

        float radians = positionPart.getRadians();

        if (rand){
            float random= (float)(Math.PI*2*Math.random());
            radians+=random;
        }


        Entity bullet = new Bullet();
        bullet.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        bullet.add(new PositionPart((float) (xs + Math.cos(radians) * 20) , (float) (ys + Math.sin(radians) * 20), radians));
        bullet.add(new LifePart(1,0.5f));
        bullet.setRadius(0.1f);

        return bullet;
    }
}
