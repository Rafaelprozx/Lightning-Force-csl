package csl.suelo.clases;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import csl.espacio.clases.Rendereable;

public class Objeto implements Ejecutable, Ubicable {

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
	public float moving_force_lr() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float moving_force_up() {
		// TODO Auto-generated method stub
		return 0;
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
	public float highest_point() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float lowest_point() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float lefest_point() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float righest_point() {
		// TODO Auto-generated method stub
		return 0;
	}

}
