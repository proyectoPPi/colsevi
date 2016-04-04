package com.colsevi.application;

public class ListaGenerica {
	private String id;
	private String nombre;
	private Boolean seleccionable;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Boolean getSeleccionable() {
		return seleccionable;
	}
	public void setSeleccionable(Boolean seleccionable) {
		this.seleccionable = seleccionable;
	}
}