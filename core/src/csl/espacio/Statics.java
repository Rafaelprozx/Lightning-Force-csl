package csl.espacio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Statics {
	public static int vp_width = 640;
	public static int vp_height = 400;
	
	public static Viewport pantalla(){
		return new StretchViewport(vp_width, vp_height);
	}
	
	public static void clean() {
		ScreenUtils.clear(0, 0, 0, 1);
	}
	public static void clean(float r, float g, float b) {
		ScreenUtils.clear(r, g, b, 1);
	}
	
	public static FileHandle gfx(String g){
		return Gdx.files.internal("gfx/"+g);
	}
	
}
