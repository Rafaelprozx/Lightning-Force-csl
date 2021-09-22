package csl.espacio.clases;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemigo implements Colisionable, Rendereable {

	private Rectangle data;
	private Vector2 pos;
	private Camera cam;
	private boolean isr,isd;
	private float health,damage;
	
	public Enemigo(Vector2 p, Rectangle r,float heal,float dmg,Camera cm) {
	data = r;
	pos = p;
	isr = false;
	isd = false;
	cam = cm;
	health = heal;
	damage = dmg;
	}
	
	public Enemigo(int x, int y, int w, int h,float heal,float dmg,Camera cm) {
	this(new Vector2(x,y),new Rectangle(x,y,w,h),heal,dmg,cm);
	}

	public void hit(Projectil p){
	health -= p.Damage();
	if(health < 1) {
	Delete();
	}
	}
	
	public float Damage() {
	return 1;
	}
	
	public float health() {
	return health;
	}
	
	public boolean deleted() {
	return isd;
	}
	
	public void Delete() {
	isd = true;
	}
	
	
	
	@Override
	public void render(Batch batch, float delta) {
		
	}

	@Override
	public Vector2 ubic() {
		return pos;
	}

	@Override
	public Rectangle col() {
		return data;
	}

	@Override
	public boolean could_render() {
		return !isd && statics.In_cam_range(this, cam);
	}
}
