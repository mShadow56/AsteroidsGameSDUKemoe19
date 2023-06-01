package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {
    /**
     * Manages the plugin of the game or more or less handles the initialization of the plugins
     *
     */
    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
}


