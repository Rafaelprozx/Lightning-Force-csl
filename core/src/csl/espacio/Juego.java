package csl.espacio;

import com.badlogic.gdx.Game;

import csl.espacio.clases.test.test;
import csl.espacio.niveles.Tutorial;

public class Juego extends Game {

	
	@Override
	public void create () {
	setScreen(new test(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}
}
