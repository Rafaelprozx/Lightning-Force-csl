package csl.suelo.clases;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import csl.espacio.clases.Rendereable;

public class Projectil implements Rendereable, Ubicable ,Damageable{

	private Vector2 pos;
	private Vector2 dir;
	private Camera cam;
	private Rectangle d;
	private boolean cr;
	private origen org;
	
	public Projectil(Vector2 post,Vector2 direccion,Rectangle hitbox,Camera cm,origen f){
		pos = post;
		dir = direccion;
		cam = cm;
		d = hitbox;
		cr = true;
		org = f;
	}

	@Override
	public void render(Batch batch, float delta) {
		if(could_render()) {
		pos.add(dir);
		}
	}
	
	public void Delete() {
		cr = false;
	}
	
	public boolean could_render() {
	if(cr){
		if(!statics.In_cam_range(this,cam)){
			cr = false;
		}
	}
	return cr;
	}
	
	public origen origen(){
		return org;
	}
	
	public boolean deleted() {
	return !cr;
	}

	@Override
	public Vector2 ubic() {
		return pos;
	}

	@Override
	public Rectangle col() {
		d.setPosition(pos);
		return d;
	}

	@Override
	public Vector2 pos() {
		return pos;
	}

	@Override
	public void setPos(float x, float y) {
		pos.set(x, y);
		
	}

	@Override
	public float moving_force_lr() {
		return dir.x;
	}

	@Override
	public float moving_force_up() {
		return dir.y;
	}
	
	public static enum origen{
		jugador,enemigo,mundo,ninguno
	}

	@Override
	public boolean onHit(Hittable c) {
		Delete();
		return true;
	}

	@Override
	public int Damage() {
		return 1;
	}

	@Override
	public float highest_point() {
		return pos.y+d.height;
	}

	@Override
	public float lowest_point() {
		return pos.y;
	}

	@Override
	public float lefest_point() {
		return pos.x;
	}

	@Override
	public float righest_point() {
		return pos.x+d.width;
	}

}
