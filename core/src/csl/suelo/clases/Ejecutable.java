package csl.suelo.clases;

import csl.espacio.clases.Rendereable;

public interface Ejecutable extends Rendereable {
	public void ejecutar();
	public boolean could_execute();
}
