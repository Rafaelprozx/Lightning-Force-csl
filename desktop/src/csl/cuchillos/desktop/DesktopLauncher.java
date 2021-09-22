package csl.cuchillos.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import csl.espacio.Juego;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 640;
		config.height = 400;
		config.resizable = false;
		config.title = "CSL Lightning Force";
		config.addIcon("ship-ico.png", FileType.Internal);
		new LwjglApplication(new Juego(), config);
	}
}
