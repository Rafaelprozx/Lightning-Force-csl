package csl.suelo.clases;

import csl.espacio.Colisionable;

public interface Hittable {
	public int health();
	public boolean hit(Colisionable c);
	public void kill();
}
