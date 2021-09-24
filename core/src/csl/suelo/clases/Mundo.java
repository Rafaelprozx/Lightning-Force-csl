package csl.suelo.clases;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import csl.espacio.Colisionable;
import csl.espacio.clases.Rendereable;

public class Mundo{
	
	private Camera cam;
	private Array<Rendereable> bg,fg;
	private Array<Enemigo> en;
	private Array<Objeto> ob;
	private Array<Wall> wall;
	private Array<Colisionable> all;
	private Array<Projectil> prj;
	private Pj player;
	private float gr,crr,cr;
	
	public Mundo(Camera cmr,float gravity){
	cam = cmr;
	gr = gravity;
	crr = .1f;
	cr = crr*2;
	bg = new Array<Rendereable>();
	fg = new Array<Rendereable>();
	en = new Array<Enemigo>();
	ob = new Array<Objeto>();
	wall = new Array<Wall>();
	all = new Array<Colisionable>();
	prj = new Array<Projectil>();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Pj>T add_player(float x,float y,float w,float h,int heal,float mov){
		player = new Pj(x,y,w,h,heal,mov,cam);
		all.add(player);
		return (T)player;
	}
	
	public <T extends Pj> T add_player(T pl){
		player = pl;
		all.add(pl);
		return pl;
	}
	
	public void add_fondo(Rendereable r){
		bg.add(r);
	}
	
	public void add_projectil(Projectil c){
		prj.add(c);
	}
	
	public void add_frente(Rendereable r){
		fg.add(r);
	}
	
	public void add_enemigo(Enemigo e) {
		en.add(e);
		all.add(e);
	}
	
	public void add_Objeto(Objeto o){
		ob.add(o);
		all.add(o);
	}
	
	public void add_wall(Wall w){
		wall.add(w);
		all.add(w);
	}
	
	public void add_wall(Rectangle w){
		wall.add(new Wall(w,cam));
		all.add(wall.get(wall.size-1));
	}
	
	public void add_wall(float x,float y,float w,float h){
		wall.add(new Wall(x,y,w,h,cam));
		all.add(wall.get(wall.size-1));
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
		r.rect(player.pos().x, player.pos().y, player.col().width, player.col().height);
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
				e.ejecutar();
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
				for(Enemigo o:en){
					if(o.could_render() && o.col().contains(e.col())){
					restat(o,e);
					}
				}
			}
		}
		for(Objeto o:ob){
			if(o.could_execute()){
				if(o.could_render()){
					o.pos().sub(0, gr);
				}
				if(o.col().overlaps(player.col())){
					restat_lr(o,player);
				}
			o.ejecutar();
			}
		}
		for(Projectil o:prj){
			if(!o.deleted() && o.could_render()){
				for(Colisionable e:all){
					if(e.col().overlaps(o.col())){
						if(e instanceof Wall){
							o.Delete();
						}else if(e instanceof Hittable){
								((Hittable)e).hit(o);
								o.onHit((Hittable)e);
						}
					}
				}
			}
		}
		
		// test
		if(player.pos().y < 0){
			player.pos().y = 20;
		}
		
	}
	
	private void restat(Ubicable c, Colisionable w) {
	restat_lr(c,w);
	restat_ud(c,w);
	}
	
	public void restat_lr(Ubicable c,Colisionable w){
		if(c.col().width <= w.col().width){
			restat_ud_eqma(c,w);
			}else{
			restat_ud_mn(c,w);
			}
	}
	
	public void restat_ud(Ubicable c,Colisionable w) {
		if(c.col().height <= w.col().height) {
			restat_lr_eqma(c,w);
			}else{
			restat_lr_mn(c,w);
			}
	}
	
	private void restat_ud_eqma(Ubicable c,Colisionable w){
		if(x_range(c,w)) {
		if(c.lowest_point() < w.highest_point() && c.lowest_point() > w.highest_point()-(gr*2)){
			c.pos().y = w.col().y+w.col().height+crr;
		}else if(c.highest_point() > w.lowest_point() && c.highest_point() < w.lowest_point()+(c.moving_force_up()*2)){
			c.pos().y = w.col().y-(c.col().height+(c.moving_force_up()/1.5f));
		}}
		
	}
	
	private void restat_lr_eqma(Ubicable c,Colisionable w){
		if(y_range(c,w)){
		if(c.lefest_point() < w.righest_point() && c.lefest_point() > w.righest_point()-(c.moving_force_lr()*1.5f)){
			c.pos().x = w.col().x+w.col().width+(c.moving_force_lr()/2f);
		}else if(c.righest_point() > w.lefest_point() && c.righest_point() < w.lefest_point()+(c.moving_force_lr()*1.5f)){
			c.pos().x = w.col().x-(c.col().width+(c.moving_force_lr()/2f));
		}}
	}
	
	private void restat_ud_mn(Ubicable c,Colisionable w){
		if(x_range(w,c)) {
		if(c.lowest_point() < w.highest_point() && c.lowest_point() > w.highest_point()-(gr*2)){
			c.pos().y = w.col().y+w.col().height+crr;
		}else if(c.highest_point() > w.lowest_point() && c.highest_point() < w.lowest_point()+(c.moving_force_up()*2)){
			c.pos().y = w.col().y-(c.col().height+(c.moving_force_up()/1.5f));
		}}
		
	}
	
	private void restat_lr_mn(Ubicable c,Colisionable w){
		if(y_range(w,c)){
		if(c.lefest_point() < w.righest_point() && c.lefest_point() > w.righest_point()-(c.moving_force_lr()*1.5f)){
			c.pos().x = w.col().x+w.col().width+(c.moving_force_lr()/2f);
		}else if(c.righest_point() > w.lefest_point() && c.righest_point() < w.lefest_point()+(c.moving_force_lr()*1.5f)){
			c.pos().x = w.col().x-(c.col().width+(c.moving_force_lr()/2f));
		}}
	}
	
	private boolean y_range(Colisionable c,Colisionable w){
		return (c.lowest_point()+cr < w.highest_point() && c.lowest_point() > w.lowest_point()+cr) || (c.highest_point() > w.lowest_point()+cr && c.highest_point()+cr < w.highest_point());
	}
	
	
	private boolean x_range(Colisionable c,Colisionable w) {
		return (c.lefest_point()+cr < w.righest_point() && c.lefest_point() > w.lefest_point()+cr) || (c.righest_point() > w.lefest_point()+cr && c.righest_point()+cr < w.righest_point());
	}
	
	
}
