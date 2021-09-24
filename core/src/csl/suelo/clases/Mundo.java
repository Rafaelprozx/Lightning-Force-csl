package csl.suelo.clases;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import csl.espacio.clases.Colisionable;
import csl.espacio.clases.Rendereable;

public class Mundo{
	
	private Camera cam;
	private Array<Rendereable> bg,fg;
	private Array<Enemigo> en;
	private Array<Objeto> ob;
	private Array<Wall> wall;
	private Pj player;
	private float gr;
	
	public Mundo(Camera cmr,float gravity){
	cam = cmr;
	gr = gravity;
	bg = new Array<Rendereable>();
	fg = new Array<Rendereable>();
	en = new Array<Enemigo>();
	ob = new Array<Objeto>();
	wall = new Array<Wall>();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Pj>T add_player(float x,float y,float w,float h,int heal){
		player = new Pj(x,y,w,h,heal,cam);
		return (T)player;
	}
	
	public void add_fondo(Rendereable r){
		bg.add(r);
	}
	
	public void add_frente(Rendereable r){
		fg.add(r);
	}
	
	public void add_enemigo(Enemigo e) {
		en.add(e);
	}
	
	public void add_Objeto(Objeto o){
		ob.add(o);
	}
	
	public void add(Wall w){
		wall.add(w);
	}
	
	public void add_wall(Rectangle w){
		wall.add(new Wall(w,cam));
	}
	
	public void add_wall(float x,float y,float w,float h){
		wall.add(new Wall(x,y,w,h,cam));
	}
	
	public void Actuar(Batch batch,float delta){
		calculo();
		for(Rendereable r:bg){
			if(r.could_render()) {
			r.render(batch, delta);
			}
		}
		if(player.could_render()){
		player.Actuar(batch, delta);
		}
		for(Rendereable r:ob){
			if(r.could_render()) {
			r.render(batch, delta);
			}
		}
		for(Rendereable r:en){
			if(r.could_render()) {
			r.render(batch, delta);
			}
		}
		for(Rendereable r:wall){
			if(r.could_render()) {
			r.render(batch, delta);
			}
		}
		for(Rendereable r:fg){
			if(r.could_render()) {
			r.render(batch, delta);
			}
		}
	}
	
	public void render_hitbox_player(ShapeRenderer r){
		r.setColor(0, 1, 1, 1);
		r.rect(player.pos().x, player.pos().y, player.col().width, player.col().width);
	}
	
	public void render_hitbox_wall(ShapeRenderer r){
		r.setColor(1, 0, 0, 1);
		for(Wall l:wall){
			r.rect(l.col().x, l.col().y, l.col().width, l.col().height);
		}
	}
	
	public void render_hitbox_objetos(ShapeRenderer r){
		r.setColor(0, 0, 1, 1);
		for(Colisionable l:ob){
			r.rect(l.col().x, l.col().y, l.col().width, l.col().height);
		}
	}
	
	public void render_hitbox_enemigos(ShapeRenderer r){
		r.setColor(1, 1, 0, 1);
		for(Colisionable l:en){
			r.rect(l.col().x, l.col().y, l.col().width, l.col().height);
		}
	}
	
	private void calculo(){
		player.pos().sub(0,gr);
		for(Enemigo e:en){
			if(e.could_execute()){
				if(e.col().contains(player.col())){
					player.hit(e);
				}
				if(e.could_render()){
					e.pos().sub(0,gr);
				}
			}
		}
		for(Wall e:wall){
			if(e.could_render()){
				if(e.col().overlaps(player.col())){
					restat(player,e);
					}
				for(Objeto o:ob){
					if(o.could_render() && o.col().contains(e.col())){
					restat(o,e);
					}
				}
			}
		}
		for(Objeto o:ob){
			if(o.could_render()){
				o.pos().sub(0, gr);
			}
		}
		if(player.pos().y < 0){
			player.pos().y = 20;
		}
	}
	
	private void restat(Ubicable c, Colisionable w) {
		if(c.pos().y < w.col().y+w.col().height && c.pos().y > (w.col().y+w.col().height)-c.moving_force_lr()){
			c.pos().y = w.col().y+w.col().height+.1f;
		}else if(c.pos().y+c.col().height > w.col().y && c.pos().y+c.col().height < w.col().y+c.moving_force_lr()){
			c.pos().y = w.col().y-(c.col().height+(c.moving_force_lr()/2));
		}
		if(c.pos().x < w.col().x+w.col().width && c.pos().x > (w.col().x+w.col().width)-(c.moving_force_lr()+1)){
			c.pos().x = w.col().x+w.col().width+(c.moving_force_lr()/2);
		}else if(c.pos().x+c.col().width > w.col().x && c.pos().x+c.col().width < w.col().x+(c.moving_force_lr()+1)){
			c.pos().x = w.col().x-(c.col().width+(c.moving_force_lr()/2));
		}
			
	}
	
}
