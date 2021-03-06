package maindalorian.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import maindalorian.game.Sandbox;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "UnderBrick";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new Sandbox(), config);
	}
}
