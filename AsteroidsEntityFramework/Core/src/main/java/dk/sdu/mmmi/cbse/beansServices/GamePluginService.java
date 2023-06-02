package dk.sdu.mmmi.cbse.beansServices;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.springframework.stereotype.Service;

import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

@Service
public class GamePluginService implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        for (IGamePluginService gamePluginService : ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList())) {
            gamePluginService.start(gameData, world);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (IGamePluginService gamePluginService : ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList())) {
            gamePluginService.stop(gameData, world);
        }
    }
}

