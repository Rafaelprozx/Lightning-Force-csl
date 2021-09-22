package csl.espacio.clases;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;

public interface Colisionable {
	public Rectangle col();
	 public static class statics{
		 public static boolean In_cam_range(Colisionable c,Camera cam){
			 return !(c.col().x < cam.position.x-320 || c.col().y < cam.position.y-200 || c.col().x > (cam.position.x-320)+cam.viewportWidth || c.col().y > (cam.position.y-200)+cam.viewportHeight);
		 }
		 
		 public static Rectangle camera_rec(Camera c) {
				return new Rectangle(c.position.x-320,c.position.y-200,(c.position.x-320)+c.viewportWidth,(c.position.y-200)+c.viewportHeight);
		}
	 }
}
