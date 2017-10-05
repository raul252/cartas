package com.raul.calculo.test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.raul.calculo.business.Carta;
import com.raul.calculo.business.Mesa;


public class MyTest {
	@Test
	public void miTest()
	{
		Mesa m = new Mesa();
		
		ArrayList<ArrayList<Carta>> tabla = m.startGame();
		Carta micarta = tabla.get(0).get(1);
			
		int column = findColumnCard(tabla,micarta); 
		Assert.assertNotEquals(-1,column); 
		 
		ArrayList<ArrayList<Carta>> tabla1= m.nextTable(column);
		column = findColumnCard(tabla1,micarta); 
		Assert.assertNotEquals(-1,column); 	
			
		
		ArrayList<ArrayList<Carta>> tabla2= m.nextTable(column);
		column = findColumnCard(tabla2,micarta); 
		Assert.assertNotEquals(-1,column); 
		
		
		Carta result = m.getCartaElegida(column); 
		
		Assert.assertEquals(micarta.getNombre(),result.getNombre());
		Assert.assertEquals(micarta.getValor(),result.getValor());
		
	}

	private int findColumnCard(ArrayList<ArrayList<Carta>> tabla, Carta card) {
		int c=-1; 
		
		for(int column=0; column<3; column++){
			for(int i=0; i<7; i++){
				Carta item = tabla.get(column).get(i);  
				if(item.getValor().equals(card.getValor())
						&& item.getNombre().equals(card.getNombre())){
					c=column+1; break; 
				}
			}
		}
		return c;
	}
}