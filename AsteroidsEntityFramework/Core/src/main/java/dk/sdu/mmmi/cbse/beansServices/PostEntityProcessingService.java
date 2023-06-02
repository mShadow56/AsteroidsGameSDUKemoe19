package dk.sdu.mmmi.cbse.beansServices;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import org.springframework.stereotype.Service;

import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

@Service
public class PostEntityProcessingService implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (IPostEntityProcessingService postEntityProcessingService : ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList())) {
            postEntityProcessingService.process(gameData, world);
        }
    }
}
