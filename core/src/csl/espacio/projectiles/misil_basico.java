package csl.espacio.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import csl.espacio.clases.Projectil;

public class misil_basico extends Projectil{

	private Texture txt;
	private Animation<TextureRegion> anim;
	private float del;
	private static float force = 9.5f;
	
	public misil_basico(Vector2 post, Boolean direccion, Camera cam) {
		super(post,direccion?new Vector2(force,0):new Vector2(-force,0),new Rectangle(post.x,post.y,8,8), cam);
		txt = new Texture(Gdx.files.internal("gfx/ammo/bullet1.png"));
		TextureRegion[][] rg = TextureRegion.split(txt, 8, 8);
		TextureRegion[] r = rg[0];
		anim = new Animation<TextureRegion>(0.05f,r);
		del=0;
	}

		@Override
		public void render(Batch batch, float delta) {
			super.render(batch, delta);
			if(could_render()){
				batch.draw(anim.getKeyFrame(del), ubic().x, ubic().y, col().width, col().height);
				del += delta;
			}
		}
}
