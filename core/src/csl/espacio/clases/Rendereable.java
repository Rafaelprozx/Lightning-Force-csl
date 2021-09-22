package csl.espacio.clases;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public interface Rendereable {
 public void render(Batch batch,float delta);
 public Vector2 ubic();
 public boolean could_render();
}
