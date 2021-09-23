package csl.espacio.clases;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import csl.espacio.projectiles.misil_basico;

public class Pj implements Colisionable,Rendereable,InputProcessor{

	private Rectangle d;
	private Vector2 pos;
	private boolean[] ss;
	private Array<Projectil> l;
	private Camera cam;
	private float del,health;
	private boolean ds;
	
	public Pj(Camera camera,Vector2 post,Rectangle hitbox,float heal){
	d = hitbox;
	pos = post;
	cam = camera;
	ss = new boolean[6];
	for(int x = 0;x < 6;x++){
	ss[x] = false;
	}
	l = new Array<Projectil>();
	del = 0;
	health = heal;
	ds = false;
	}
	
	public void hit(Colisionable c){
		if(c instanceof Projectil_enemigo){
			health -= ((Projectil)c).Damage();
		}else if(c instanceof Enemigo){
			health -= ((Enemigo)c).Damage();
		}
		if(health < 1){
			Destroy();
		}
	}
	
	public void Destroy(){
	ds = true;
	}
	
	public boolean Destroyed() {
		return ds;
	}
	
	public void Actuar(Batch batch ,float delta){
	mover();
	move();
	atacar(delta);
	d.setPosition(pos);
	render(batch,delta);
	if(l.size >= 0){
	for(Projectil a : l){
		a.render(batch, delta);
	}}
	}
	
	public void prevent_translate_left() {
		if((pos.x) < (cam.position.x-(cam.viewportWidth/2))){
			pos.x = (cam.position.x-(cam.viewportWidth/2));
		}
	}
	
	public void movimiento_base(float x, float y) {
		pos.x += x;
		pos.y += y;
	}
	
	public void prevent_translate_right() {
		if((pos.x+d.width) > cam.position.x+(cam.viewportWidth/2)){
			pos.x = (cam.position.x+(cam.viewportWidth/2)-d.width);
		}
	}
	
	public Array<Projectil> proyectiles(){
	return l;
	}
	
	private void atacar(float delta){
		if(del < recharge()){
			del += delta;
		}else {
			if(ss[4]){
				if(l.size > 0){
					boolean r= true;
					for(int x=0;x<l.size;x++){
						if(l.get(x).deleted()){
							l.set(x,basico());
							del = 0;
							ss[4] = false;
							r = false;
							break;
						}
					}
					if(r) {
					if(l.size < limit_ammo()) {
					l.add(basico());
					del = 0;
					ss[4] = false;}}
				}else{
					l.add(basico());
					del = 0;
					ss[4] = false;
				}
				ss[4] = false;
			}
		}
	}
	
	public float recharge(){
		return 1;
	}
	
	public int limit_ammo(){
		return 5;
	}
	
	public boolean charged(){
		return false;
	}
	
	private Projectil basico(){
		return new misil_basico(new Vector2(pos.x+15,pos.y+5),true,cam);
	}
	
	private void mover() {
		if(ss[0] | ss[1]){
			if(ss[0] && (pos.y+d.height) < cam.position.y+(cam.viewportHeight/2)){
			pos.add(0, upForce());
			return;
			}
			if(ss[1] && (pos.y) > cam.position.y-(cam.viewportHeight/2)){
			pos.sub(0, downForce());
			return;
			}
		}
	}
	private void move(){
		if(ss[2] | ss[3]){
			if(ss[3] && (pos.x+d.width) < cam.position.x+(cam.viewportWidth/2)){
			pos.add(rightForce(), 0);
			return;
			}
			if(ss[2] && (pos.x) > (cam.position.x-(cam.viewportWidth/2))){
			pos.sub(leftForce(), 0);
			return;
			}
		}
		
	}

	@Override
	public void render(Batch batch ,float delta) {
	}

	public boolean Up(){
		return ss[0];
	}
	
	public boolean Down(){
		return ss[1];
	}
	
	public boolean Left(){
		return ss[2];
	}
	
	public boolean Right(){
		return ss[3];
	}
	
	public boolean Attack(){
		return ss[4];
	}
	
	public boolean Special() {
		return false;
	}
	
	public boolean Ultimate() {
		return false;
	}
	
	public float upForce() {
		return 5f;
	}
	
	public float downForce() {
		return 5f;
	}
	
	public float leftForce() {
		return 5f;
	}
	
	public float rightForce() {
		return 5f;
	}
	
	@Override
	public Vector2 ubic() {
		return pos;
	}

	@Override
	public Rectangle col() {
	
		return d;
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
				break;
		case Keys.D:
			ss[3] = true;
				break;
		case Keys.SPACE:
			ss[4] = true;
				break;
		case Keys.F1:
			ss[5] = true;
				break;
		}
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
		case Keys.F1:
			ss[5] = false;
			System.out.println("x = "+cam.position.x+",y = "+cam.position.y);
			System.out.println("nx = "+pos.x+",ny = "+pos.y);
			System.out.println("vpw = "+cam.viewportWidth+",vph = "+cam.viewportHeight);
				break;
		}
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
	public boolean could_render() {
		return true;
	}
	
}
