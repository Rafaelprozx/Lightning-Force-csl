package csl.suelo.projectiles;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import csl.espacio.clases.Colisionable;
import csl.espacio.clases.Rendereable;
import csl.suelo.clases.Projectil;

public class balas_9mm extends Projectil{

	public balas_9mm(Vector2 pos, boolean dir, Camera cm) {
		super(pos, dir?new Vector2(9,0):new Vector2(-9,0), new Rectangle(pos.x,pos.y,4,2), cm);
	}
	
	@Override
	public int Damage() {
		return (int)1.5;
	}
}
