package csl.espacio.clases.test;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class test_camera extends OrthographicCamera{

	public test_camera(int i, int j) {
		super(i,j);
	}

	@Override
	public void update() {
		super.update();
		if(Gdx.input.isKeyPressed(Keys.UP)){
		translate(0, 2);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
		translate(0, -2);
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
		translate(-2, 0);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
		translate(2, 0);
		}
	}

}
