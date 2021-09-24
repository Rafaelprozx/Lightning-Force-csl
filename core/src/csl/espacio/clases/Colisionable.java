package csl.espacio.clases;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;

public interface Colisionable {
	public Rectangle col();
	public float centerX();
	public float centerY();
	 public static class statics{
		 public static boolean In_cam_range(Colisionable c,Camera cam){
			 return !(c.col().x < cam.position.x-(cam.viewportWidth/2) || c.col().y < cam.position.y-(cam.viewportHeight/2) || c.col().x > cam.position.x+(cam.viewportWidth/2) || c.col().y > cam.position.y+(cam.viewportHeight/2));
		 }
		 
		 public static Rectangle camera_rec(Camera c) {
				return new Rectangle(c.position.x-(c.viewportWidth/2),c.position.y-(c.viewportHeight/2),c.position.x+(c.viewportWidth/2),c.position.y+(c.viewportHeight/2));
		}
		 
		 public static void camera_update(Camera c,Rectangle s){
			 s.setPosition(c.position.x-(c.viewportWidth/2), c.position.y-(c.viewportHeight/2));
		 }
		 
		 public static void move_cam(Camera c,Colisionable a,float distance){
			 if(a.col().x+distance > c.position.x+(c.viewportWidth/2)){
				 c.position.x = (a.col().x+distance)+(c.viewportWidth/2);
			 }else if(a.col().x-distance < (c.position.x-(c.viewportWidth/2))){
				 c.position.x = (a.col().x-distance)+(c.viewportWidth/2);
			 }
		 }
	 }
}
