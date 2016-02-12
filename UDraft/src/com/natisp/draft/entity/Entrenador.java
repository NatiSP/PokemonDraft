package com.natisp.draft.entity;

import java.util.ArrayList;
import java.util.List;

public class Entrenador {

	String nombre;
	List<Pokemon> equipo;
	public Entrenador(String string) {
		this.nombre = string;
		equipo = new ArrayList<Pokemon>();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Pokemon> getEquipo() {
		return equipo;
	}
	public void setEquipo(List<Pokemon> equipo) {
		this.equipo = equipo;
	}
	
	
}
