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
	private float hx,hy;
	
	public Wall(float x,float y,float w,float h,Camera cm) {
		this(new Rectangle(x,y,w,h),cm);
	}

	public Wall(Rectangle r,Camera cm) {
		hitbox = r;
		cam = cm;
		pos = new Vector2(r.x,r.y);
		hx = r.x+r.width;
		hy = r.y+r.height;
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
		return 0;
	}

	@Override
	public float moving_force_up() {
		return 0;
	}

	@Override
	public float highest_point() {
		return hy;
	}

	@Override
	public float lowest_point() {
		return hitbox.y;
	}

	@Override
	public float lefest_point() {
		return hitbox.x;
	}

	@Override
	public float righest_point() {
		return hx;
	}

}
