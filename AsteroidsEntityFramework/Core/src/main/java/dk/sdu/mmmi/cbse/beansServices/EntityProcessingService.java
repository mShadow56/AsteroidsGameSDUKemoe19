package dk.sdu.mmmi.cbse.beansServices;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import org.springframework.stereotype.Service;

import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

@Service
public class EntityProcessingService implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (IEntityProcessingService entityProcessingService : ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList())) {
            entityProcessingService.process(gameData, world);
        }
    }
}
