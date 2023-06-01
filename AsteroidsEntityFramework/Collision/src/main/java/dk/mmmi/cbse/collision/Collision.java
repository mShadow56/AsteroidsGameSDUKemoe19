package dk.mmmi.cbse.collision;


import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class Collision implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {

        for (Entity entity : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                if(isColliding(entity,entity2)) {
                    if (!(entity.getIsAsteroid() && entity2.getIsAsteroid())){
                        reduceLife(entity);
                        reduceLife(entity2);


                    }



                        }



                    }
                }








            }








    public boolean isColliding(Entity entity, Entity entity2){

        if (entity!=entity2) {

            PositionPart positionPart = entity.getPart(PositionPart.class);
            PositionPart positionPart2 = entity2.getPart(PositionPart.class);


            float distance = (float)(Math.sqrt(Math.pow((positionPart.getX() - positionPart2.getX()), 2) + Math.pow((positionPart.getY() - positionPart2.getY()), 2)));

               return distance < (entity.getRadius() + entity2.getRadius());

            }
        return false;
    }

    public void reduceLife(Entity entity){
        LifePart lifePart = entity.getPart(LifePart.class);
        lifePart.setLife(lifePart.getLife() - 1);

    }





}

