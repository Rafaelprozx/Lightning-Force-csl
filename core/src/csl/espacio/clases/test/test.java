package csl.espacio.clases.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import csl.espacio.clases.Colisionable;
import csl.espacio.clases.Pj;
import csl.espacio.naves.Impact;

public class test implements Screen {
	@SuppressWarnings("unused")
	private Game gm;
	private test_camera cam;
	private Stage st;
	private ShapeRenderer ln;
	private SpriteBatch b;
	boolean f = true;
	private Pj player;
	private InputMultiplexer inp;

	public test(Game j) {
		gm = j;
	}

	@Override
	public void show() {
		st = new Stage(new StretchViewport(640, 400));
		ln = new ShapeRenderer();
		cam = new test_camera(640, 400);
		ln.setAutoShapeType(true);
		b = (SpriteBatch) st.getBatch();
		player = new Impact(cam,Vector2.Zero);
		inp = new InputMultiplexer();
		inp.addProcessor(st);
		inp.addProcessor(player);
		Gdx.input.setInputProcessor(inp);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0.07f, 0.07f, 0.07f, 1);
		st.act();
		cam.update();
		b.setProjectionMatrix(cam.combined);
		b.begin();
		player.Actuar(b, delta);
		b.end();
		ln.setProjectionMatrix(cam.combined);
		ln.begin();
		ln.set(ShapeType.Line);
		ln.setColor(1, 0, 1, 1);
		//ln.rect(player.ubic().x, player.ubic().y, player.col().width, player.col().height);
		ln.setColor(0, 0, 1, 1);
		ln.rect(-319,-199,638,398);
		ln.setColor(1, 0, 0, 1);
		for (Colisionable c : player.proyectiles()) {
			ln.rect(c.col().x, c.col().y, c.col().width, c.col().height);
		}
		ln.end();
		st.draw();
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
