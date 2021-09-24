package csl.suelo.clases;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import csl.espacio.clases.Rendereable;

public class Wall implements Rendereable, Ubicable {

	private Rectangle hitbox;
	private Vector2 pos;
	private Camera cam;
	private float cx,cy;
	
	public Wall(float x,float y,float w,float h,Camera cm) {
		this(new Rectangle(x,y,w,h),cm);
	}

	public Wall(Rectangle r,Camera cm) {
		hitbox = r;
		cam = cm;
		pos = new Vector2(r.x,r.y);
		cx = r.x+(r.width/2);
		cy = r.y+(r.height/2);
	}
	
	@Override
	public Rectangle col() {
		return hitbox;
	}

	@Override
	public Vector2 pos() {
		return pos;
	}

	@Override
	public void setPos(float x, float y) {
		pos.set(x,y);
	}

	@Override
	public void render(Batch batch, float delta) {

	}
	
	public float centerY() {
		return cy;
	}
	
	public float centerX() {
		return cx;
	}

	@Override
	public Vector2 ubic() {
		return pos;
	}

	@Override
	public boolean could_render() {
		return statics.In_cam_range(this,cam);
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
