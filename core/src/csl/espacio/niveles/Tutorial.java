package csl.espacio.niveles;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import csl.espacio.Statics;
import csl.espacio.clases.Mundo;
import csl.espacio.naves.Impact;

public class Tutorial extends Nivel {

	public static OrthographicCamera cam = new OrthographicCamera(Statics.vp_width,Statics.vp_height);
	private static Impact player = new Impact(cam,new Vector2(0,0));
	private Stage st;
	private SpriteBatch batch;
	private Mundo wor;
	private TextureRegion asteroide;
	
	public Tutorial(Game gm) {
		super(gm,player);
	}

	@Override
	public void show() {
	st = new Stage(Statics.pantalla());
	batch = (SpriteBatch)st.getBatch();
	asteroide = new TextureRegion(new Texture(Gdx.files.internal("gfx/otros/asteroids.png")));
	wor = new Mundo(player, cam);
	InputMultiplexer x = new InputMultiplexer();
	x.addProcessor(st);
	x.addProcessor(player);
	Gdx.input.setInputProcessor(x);
	}

	@Override
	public void render(float delta) {
	Statics.clean(0.04f, 0.005f, 0.005f);
	cam.update();
	st.act();
	wor.actuar();
	batch.setProjectionMatrix(cam.combined);
	batch.begin();
	batch.draw(asteroide, 5, 5);
	player.Actuar(batch, delta);
	batch.end();
	st.draw();
	cam.translate(2,0);
	player.movimiento_base(2, 0);
	player.prevent_translate_left();
	}

	@Override
	public void resize(int width, int height) {
		

	}

	@Override
	public void pause() {
		

	}

	@Override
	public void resume() {
		

	}

	@Override
	public void hide() {
		

	}

	@Override
	public void dispose() {
		

	}

}
