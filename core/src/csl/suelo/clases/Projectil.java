package csl.suelo.clases;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import csl.espacio.clases.Rendereable;

public class Projectil implements Rendereable, Ubicable {

	private Vector2 pos;
	private Vector2 dir;
	private Camera cam;
	private Rectangle d;
	private boolean cr;
	
	public Projectil(Vector2 post,Vector2 direccion,Rectangle hitbox, Camera cm){
		pos = post;
		dir = direccion;
		cam = cm;
		d = hitbox;
		cr = true;
	}

	@Override
	public void render(Batch batch, float delta) {
		if(could_render()) {
		pos.add(dir);
		}
	}
	
	public int Damage() {
		return 1;
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

}
