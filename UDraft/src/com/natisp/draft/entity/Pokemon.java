package com.natisp.draft.entity;

public class Pokemon {

	String nombre;
	boolean mega;
	
	public Pokemon(int i) {
		// TODO Auto-generated constructor stub
		this.nombre = String.valueOf(i);
	}
	
	public Pokemon(String n) {
		// TODO Auto-generated constructor stub
		this.nombre = n;
	}
	
	public Pokemon(String n, boolean m) {
		// TODO Auto-generated constructor stub
		this.nombre = n;
		this.mega = m;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isMega() {
		return mega;
	}
	public void setMega(boolean mega) {
		this.mega = mega;
	}
	
}
