package dk.sdu.mmmi.cbse.main;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {


		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(GameConfiguration.class);
		context.refresh();

		Game game = context.getBean(Game.class);

		Lwjgl3ApplicationConfiguration cfg= new Lwjgl3ApplicationConfiguration();
		cfg.setTitle("Asteroids");
		cfg.setWindowSizeLimits(1000,600,1000,600);
		cfg.setWindowedMode(1000,600);
		cfg.setResizable(false);

		new Lwjgl3Application(game, cfg);

	}

}

