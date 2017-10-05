package com.raul.calculo.business;


public class Carta {
	private String nombre;
	private String valor;
	
	
	public Carta(String nombre, String valor){
		this.nombre = nombre;
		this.valor = valor;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setNumero(String valor) {
		this.valor = valor;
	}

}
