package csl.suelo.clases;

import com.badlogic.gdx.math.Vector2;

import csl.espacio.Colisionable;

public interface Ubicable extends Colisionable{
public Vector2 pos();
public void setPos(float x,float y);
public float moving_force_lr();
public float moving_force_up();
}
