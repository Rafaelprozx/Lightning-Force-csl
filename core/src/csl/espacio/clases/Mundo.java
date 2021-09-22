package csl.espacio.clases;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.Array;

public class Mundo {

	private Array<Enemigo> en;
	private Array<Projectil> es;
	private Pj pj;
	private Camera cm;
	
	public Mundo(Pj pjj,Camera cm){
	pj = pjj;
	en = new Array<Enemigo>();
	es = new Array<Projectil>();
	}
	
	public void add_enemigo(Enemigo e) {
		en.add(e);
	}
	
	public void add_enemigo(Enemigo... enemigos) {
		for(Enemigo e:enemigos){
			en.add(e);
		}
	}

	public void add_projectil(Projectil e){
		if(es.size == 0){
			es.add(e);
		}else{
			boolean r = true;
			for(int x=0;x<es.size;x++){
				if(es.get(x).deleted()){
					es.set(x, e);
					r = false;
				}
			}
			if(r){
				es.add(e);
			}
		}
	}
	
	public void actuar(){
		for(Enemigo d: en){
			if(d.could_render() && !d.deleted()){
				for(Projectil c : pj.proyectiles()){
					if(!c.deleted() && d.col().contains(c.col())) {
						c.Delete();
						d.hit(c);
					}
				}
				// fin bucle 1
				if(d.col().contains(pj.col())){
					d.Delete();
					pj.hit(d);
				}
			}
		}
		for(Projectil c:es){
			if(!c.deleted() && c.could_render()){
				if(c.col().contains(pj.col())){
					c.Delete();
					pj.hit(c);
				}
			}
		}
	}
	
}
