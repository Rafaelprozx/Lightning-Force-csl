package csl.espacio;

import com.badlogic.gdx.Game;

import csl.espacio.clases.test.test;
import csl.suelo.test2;

@SuppressWarnings("unused")
public class Juego extends Game {

	
	@Override
	public void create () {
	setScreen(new test2());
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
