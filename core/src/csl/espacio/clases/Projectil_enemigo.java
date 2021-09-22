package csl.espacio.clases;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Projectil_enemigo extends Projectil {

	public Projectil_enemigo(Vector2 post, Vector2 direccion, Rectangle hitbox, Camera cm) {
		super(post, direccion, hitbox, cm);
	}

}
