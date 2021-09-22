package csl.espacio.naves;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import csl.espacio.clases.Pj;

public class Impact extends Pj {

	private TextureRegion base;
	private Animation<TextureRegion>[] anims;
	private float del;
	private boolean ga;
	
	@SuppressWarnings("unchecked")
	public Impact(Camera camera, Vector2 posicion_inicial) {
		super(camera,posicion_inicial,new Rectangle(0,0,22,15),1);
		TextureRegion[][] r = TextureRegion.split(new Texture(Gdx.files.internal("gfx/ship/impact.png")), 32, 32);
		TextureRegion[] rg = r[0];
		Animation<TextureRegion> m = new Animation<TextureRegion>(0.04f,new TextureRegion[]{rg[0],rg[1],rg[2]});
		m.setPlayMode(PlayMode.NORMAL);
		Animation<TextureRegion> a = new Animation<TextureRegion>(0.04f,new TextureRegion[]{rg[3],rg[4],rg[5],rg[6],rg[7]});
		a.setPlayMode(PlayMode.NORMAL);
		Animation<TextureRegion> u = new Animation<TextureRegion>(0.05f,new TextureRegion[]{rg[8],rg[9],rg[10],rg[11],rg[12],rg[13],rg[14],rg[15],rg[16],rg[17]});
		u.setPlayMode(PlayMode.NORMAL);
		anims = new Animation[]{m,a,u};
		base = rg[0];
		del = 0;
	}

	@Override
	public void render(Batch batch, float delta) {
		super.render(batch, delta);
		if(Attack() | ga){
			batch.draw((TextureRegion)anims[1].getKeyFrame(del, false), ubic().x-5, ubic().y-9,32,32);
			if(anims[1].isAnimationFinished(del)){
				ga = false;
				del = 0;
			}
		}else if(Up() | Down()){
			batch.draw((TextureRegion)anims[0].getKeyFrame(del, false), ubic().x-5, ubic().y-9,32,32);
		}else {
			batch.draw(base, ubic().x-5, ubic().y-9,32,32);
		}
		del += delta;
	}
	
	@Override
	public int limit_ammo() {
		return 10;
	}
	
	@Override
	public float recharge() {
		return 0.01f;
	}
	
	@Override
	public float upForce() {
		return 4.5f;
	}
	
	@Override
	public float downForce() {
		return 4.5f;
	}
	
	@Override
	public float leftForce() {
		return 5.8f;
	}
	
	@Override
	public float rightForce() {
		return 3.2f;
	}
}
