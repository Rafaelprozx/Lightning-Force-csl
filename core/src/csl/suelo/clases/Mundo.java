package csl.suelo.clases;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import csl.espacio.clases.Colisionable;
import csl.espacio.clases.Colisionable.statics;
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
				if(e.col().contains(player.col())){
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
	}
	
	private void restat(Ubicable c, Colisionable w) {
		if(in_y_range(c,w)){
			if(c.col().x < w.col().x+w.col().width){
				c.pos().x = w.col().x+w.col().width;
			}else if((c.col().x +c.col().width) > w.col().x){
				c.pos().x = w.col().x-0.1f;
			}
		}else if(in_x_range(c,w)){
			if(c.col().y < w.col().y+w.col().height){
				c.pos().y = w.col().y+w.col().height;
			}else if((c.col().y +c.col().height) > w.col().y){
				c.pos().y = w.col().y-0.1f;
			}
		}
			
	}
	
	private boolean in_y_range(Colisionable a,Colisionable b){
		return (a.col().y+a.col().height)-1 > b.col().y && a.col().y < (b.col().y + b.col().height-1);
	}
	private boolean in_x_range(Colisionable a,Colisionable b){
		return (a.col().x+a.col().width-1) > b.col().x && a.col().x < (b.col().x + b.col().width-1);
	}
}
