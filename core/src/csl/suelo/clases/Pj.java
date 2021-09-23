package csl.suelo.clases;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import csl.espacio.clases.Colisionable;
import csl.espacio.clases.Rendereable;

public class Pj implements Rendereable, Ubicable, Hittable{

	private Rectangle hitbox;
	private Vector2 pos;
	private int health;
	
	public Pj(float x,float y,float w,float h,int heal){
	health = heal;
	pos = new Vector2(x,y);
	hitbox = new Rectangle(x,y,w,h);
	}
	
	@Override
	public Rectangle col() {
		hitbox.setPosition(pos.x, pos.y);
		return hitbox;
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
	public void render(Batch batch, float delta) {

	}

	@Override
	public boolean could_render() {
		return false;
	}

	@Override
	public int health() {
		return health;
	}

	@Override
	public boolean hit(Colisionable c) {
		if(c instanceof Enemigo){
			int damage = ((Enemigo)c).damage();
			if(damage >= health){
				kill();
				return true;
			}else{
				health -= damage;
			}
		}
		if(c instanceof Projectil_enemigo){
			int damage = ((Projectil_enemigo)c).Damage();
			if(damage >= health){
				kill();
				return true;
			}else{
				health -= damage;
			}
		}
		return false;
	}

	@Override
	public void kill(){
		
	}

	@Override
	public Vector2 ubic() {
		return pos;
	}

}
