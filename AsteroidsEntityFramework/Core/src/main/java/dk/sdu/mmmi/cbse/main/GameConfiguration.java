package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.beansServices.EntityProcessingService;
import dk.sdu.mmmi.cbse.beansServices.GamePluginService;
import dk.sdu.mmmi.cbse.beansServices.PostEntityProcessingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfiguration {

    @Bean
    public Game game() {
        return new Game();
    }

    @Bean
    public IEntityProcessingService entityProcessingService() {
        return new EntityProcessingService();
    }

    @Bean
    public IPostEntityProcessingService postEntityProcessingService() {
        return new PostEntityProcessingService();
    }

    @Bean
    public IGamePluginService gamePluginService() {
        return new GamePluginService();
    }
}