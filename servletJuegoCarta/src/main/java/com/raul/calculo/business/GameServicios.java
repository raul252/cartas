package com.raul.calculo.business;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import com.raul.calculo.business.Carta;

@Stateful
@LocalBean
public class GameServicios {
	
	private Mesa m = new Mesa();
	
	public GameServicios(){
		m= new Mesa();
	}
	
	public int getContador()
	{
		return m.getCounter();
	}
	
	public ArrayList<ArrayList<Carta>> empiezaJuego()
	{
		return m.startGame();
	}
	
	public ArrayList<ArrayList<Carta>> nextTable(int seleccion)
	{
		return m.nextTable(seleccion);
	}
	
	public Carta getCartaElegida(int seleccion)
	{
		return m.getCartaElegida(seleccion);
	}
}