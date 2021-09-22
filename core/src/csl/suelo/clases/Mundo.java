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
	
	public Mundo(Camera cm){
	cam = cm;
	bg = new Array<Rendereable>();
	en = new Array<Enemigo>();
	ob = new Array<Objeto>();
	wall = new Array<Wall>();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Pj>T add_player(float x,float y,float w,float h){
		return (T)(new Pj(x,y,w,h));
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
		
	}
	
}
