package csl.suelo.clases;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import csl.espacio.clases.Rendereable;

public class Mundo{
	
	private Camera cam;
	private Array<Rendereable> bg;
	private Array<Enemigo> en;
	private Array<Objeto> ob;
	private Array<Wall> wall;
	private Pj player;
	private float gr;
	
	public Mundo(Camera cmr,float gravity){
	cam = cmr;
	gr = gravity;
	bg = new Array<Rendereable>();
	en = new Array<Enemigo>();
	ob = new Array<Objeto>();
	wall = new Array<Wall>();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Pj>T add_player(float x,float y,float w,float h){
		player = new Pj(x,y,w,h);
		return (T)player;
	}
	
	public void add_fondo(Rendereable r){
		bg.add(r);
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
	
	
	public void Actuar(Batch batch,float delta){
		calculo();
	}
	
	private void calculo(){
		player.pos().sub(0,gr);
		for(Enemigo e:en){
			if(e.could_execute()){
				if(e.col().contains(player.col())){
					player.hit(e);
				}
			
			}
		}
	}
	
}
