package csl.suelo.clases;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import csl.espacio.clases.Colisionable;
import csl.espacio.clases.Rendereable;

public class Enemigo implements Ejecutable, Ubicable, Hittable {

	
	public int damage() {
		return 1;
	}
	
	@Override
	public Rectangle col() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2 pos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPos(float x, float y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Batch batch, float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vector2 ubic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean could_render() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ejecutar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean could_execute() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int health() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hit(Colisionable c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void kill() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public float centerX() {
		return pos().x+(col().height/2);
	}

	@Override
	public float centerY() {
		return pos().y+(col().width/2);
	}

	@Override
	public float moving_force_lr() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float moving_force_up() {
		// TODO Auto-generated method stub
		return 0;
	}

}
