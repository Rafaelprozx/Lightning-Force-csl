package csl.suelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import csl.espacio.Statics;
import csl.suelo.clases.Mundo;
import csl.suelo.clases.Pj;

public class test2 implements Screen {

	private Mundo mundo;
	private Pj player;
	private OrthographicCamera cam;
	private ShapeRenderer sh;
	private SpriteBatch batch;
	private BitmapFont font;
	
	public test2(){
		
	}
	
	@Override
	public void show() {
	cam = new OrthographicCamera(640,400);
	cam.position.x = 320;
	cam.position.y = 200;
	mundo = new Mundo(cam,4);
	font = new BitmapFont();
	sh = new ShapeRenderer();
	batch = new SpriteBatch();
	player = mundo.add_player(10, 30, 10, 10, 4, 1);
	mundo.add_wall(0, 0, 50, 15);
	mundo.add_wall(50, 0, 50, 15);
	mundo.add_wall(100, 0, 50, 15);
	mundo.add_wall(150, 0, 50, 15);
	mundo.add_wall(75, 15, 15, 15);
	Gdx.input.setInputProcessor(player);
	}

	@Override
	public void render(float delta) {
	Statics.clean(0, 0, 1);
	cam.update();
	batch.setProjectionMatrix(cam.combined);
	batch.begin();
	mundo.Actuar(batch, delta);
	font.draw(batch, "x = "+player.pos().x+", y = "+player.pos().y, 320, 200);
	batch.end();
	sh.setProjectionMatrix(cam.combined);
	sh.begin(ShapeType.Line);
	mundo.render_hitbox_player(sh);
	mundo.render_hitbox_wall(sh);
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
