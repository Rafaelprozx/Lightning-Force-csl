package csl.suelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import csl.espacio.Statics;
import csl.suelo.clases.Pj;
import csl.suelo.fisica.Mundo;

public class test2 implements Screen {

	private Mundo mundo;
	private Pj player;
	private OrthographicCamera cam;
	private ShapeRenderer sh;
	private SpriteBatch batch;
	
	public test2(){
		
	}
	
	@Override
	public void show() {
	cam = new OrthographicCamera(640,400);
	cam.position.x = 320;
	cam.position.y = 200;
	mundo = new Mundo(cam,1);
	sh = new ShapeRenderer();
	batch = new SpriteBatch();
	player = mundo.add_player(10, 30, 5, 5, 1);
	mundo.add_wall(0,0,50,10);
	Gdx.input.setInputProcessor(player);
	}

	@Override
	public void render(float delta) {
	Statics.clean(0, 0, 1);
	cam.update();
	batch.setProjectionMatrix(cam.combined);
	batch.begin();
	mundo.Actuar(batch, delta);
	batch.end();
	sh.setProjectionMatrix(cam.combined);
	sh.begin(ShapeType.Line);
	sh.setColor(1, 0, 0, 1);
	sh.rect(player.pos().x, player.pos().y,player.col().width,player.col().height);
	sh.rect(0,0,50,5);
	sh.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
