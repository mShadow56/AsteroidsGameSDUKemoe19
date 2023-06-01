package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.services.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            LifePart lifePart= enemy.getPart(LifePart.class);

            int s = (int) Math.round(Math.random());
            int t = (int) Math.round(Math.random());
            boolean ds = false;
            boolean ts = false;
            ds = s != 0;
            ts = t != 0;
            float rand= (float)(Math.random());

            // turning
            if (ds) {
                positionPart.setPosition(positionPart.getX()+1.5f, positionPart.getY()+1f);
            }else{
                positionPart.setPosition(positionPart.getX()+1.5f, positionPart.getY()-1f);
            }

            if(rand<=0.02){
                for (BulletSPI bullet: getBulletSPIs())
                world.addEntity(bullet.newBullet(enemy,gameData,true));

            }

            lifePart.process(gameData,enemy);
            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);

            updateShape(enemy);
        }
    }





    private void updateShape(Entity entity) {

        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        float[] shapex = new float[12];
        float[] shapey = new float[12];

        shapex[0] = x + (float)(Math.cos(radians -0.982795f )) * 7.21f;
        shapey[0] = y + (float)Math.sin(radians -0.982795f) * 7.21f;

        shapex[1] = x + (float)Math.cos(radians -0.463734f ) * 8.94f;
        shapey[1] =  y + (float)Math.sin(radians -0.463734f) * 8.94f;


        shapex[2] =  x + (float)Math.cos(radians +0.463734f ) * 8.94f;
        shapey[2] = y + (float)Math.sin(radians +0.463734f) * 8.94f;

        shapex[3] =  x + (float)Math.cos(radians +0.982795f ) * 7.21f;
        shapey[3] = y + (float)Math.sin(radians +0.982795f) * 7.21f;

        shapex[4]=x + (float)Math.cos(radians -0.982795f ) * 7.21f;
        shapey[4]= y + (float)Math.sin(radians -0.982795f) * 7.21f;

        shapex[5]=x + (float)Math.cos(radians -1.5708f) * 12f;
        shapey[5]=y + (float)Math.sin(radians -1.5708f) * 12f;

        shapex[6]=x + (float)Math.cos(radians -2.0345f ) * 8.94f;
        shapey[6]=y + (float)Math.sin(radians -2.0345f) * 8.94f;

        shapex[7]=x + (float)Math.cos(radians +2.0345f ) * 8.94f;
        shapey[7]=y + (float)Math.sin(radians +2.0345f) * 8.94f;

        shapex[8]=x + (float)Math.cos(radians +1.5708f ) * 12;
        shapey[8]=y + (float)Math.sin(radians +1.5708f) * 12;

        shapex[9]=x + (float)Math.cos(radians -1.5708f ) * 12;
        shapey[9]=y + (float)Math.sin(radians -1.5708f) * 12;

        shapex[10]=x + (float)Math.cos(radians +1.5708f ) * 12;
        shapey[10]=y + (float)Math.sin(radians +1.5708f) * 12;

        shapex[11]=x + (float)Math.cos(radians +0.982795f ) * 7.21f;
        shapey[11]=y + (float)Math.sin(radians +0.982795f) * 7.21f;






        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }


}