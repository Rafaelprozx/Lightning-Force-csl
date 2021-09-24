package csl.suelo.clases;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import csl.espacio.clases.Colisionable;
import csl.espacio.clases.Rendereable;
import csl.suelo.clases.Projectil.origen;
import csl.suelo.projectiles.balas_9mm;

public class Pj implements Rendereable, Ubicable, Hittable, InputProcessor{

	private Rectangle hitbox;
	private Vector2 pos;
	private int health;
	private boolean[] ss;
	private boolean facing;
	private Camera cam;
	private float mflr=0,mfu=5;
	
	public Pj(float x,float y,float w,float h,int heal,float mov,Camera cm){
	health = heal;
	pos = new Vector2(x,y);
	hitbox = new Rectangle(x,y,w,h);
	cam = cm;
	ss = new boolean[6];
	for(int a=0;a<6;a++){
	ss[a] = false;
	}
	facing = true;
	mflr = mov;
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

	public void Actuar(Batch draw,float delta){
		col();
		if(ss[3]){
			pos.add(mflr, 0);
		}else if(ss[2]){
			pos.sub(mflr, 0);
		}
		if(ss[4]){
			pos.add(0,mfu);
		}
		render(draw,delta);
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
		return true;
	}

	@Override
	public int health() {
		return health;
	}
	
	public Projectil projectil(){
		return new balas_9mm(pos, facing, origen.jugador, cam);
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

	@Override
	public boolean keyDown(int keycode) {
		switch(keycode){
		case Keys.W:
			ss[0] = true;
			break;
		case Keys.S:
			ss[1] = true;
			break;
		case Keys.A:
			ss[2] = true;
			facing = false;
			break;
		case Keys.D:
			ss[3] = true;
			facing = true;
			break;
		case Keys.J:
			ss[4] = true;
			break;
		case Keys.K:
			ss[5] = true;
			break;}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode){
		case Keys.W:
			ss[0] = false;
			break;
		case Keys.S:
			ss[1] = false;
			break;
		case Keys.A:
			ss[2] = false;
			break;
		case Keys.D:
			ss[3] = false;
			break;
		case Keys.J:
			ss[4] = false;
			break;
		case Keys.K:
			ss[5] = false;
			break;}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float centerX() {
		return pos.x+(hitbox.width/2);
	}

	@Override
	public float centerY() {
		return pos.y+(hitbox.height/2);
	}

	@Override
	public float moving_force_lr() {
		return mflr;
	}

	@Override
	public float moving_force_up() {
		return mfu;
	}

}
