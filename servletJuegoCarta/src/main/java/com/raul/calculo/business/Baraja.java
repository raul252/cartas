package com.raul.calculo.business;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import com.raul.calculo.business.Carta;

@Stateful
@LocalBean
public class Baraja {
	

	static final String MAZO_TYPE[] = { "corazones","trébol", "picas", "diamantes" };
	static final String MAZO_VALUE[] = { "1","2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	int counter = 0;
	
	private ArrayList<Carta> mibaraja;
	
	public Baraja(){
		
	}
	
	public int sumaContador(){
		return counter++;
	}
	
	public int getContador()
	{
		return counter;
	}
	
	
	public ArrayList<Carta> getDatosAleatorios() {
		mibaraja = new ArrayList<Carta>();
		
		while(mibaraja.size()<21){
			int  rdmType  = (int) (Math.random()*MAZO_TYPE.length-1);
			int  rdmValue  = (int) (Math.random()*MAZO_VALUE.length-1 );
					
			if (!hasCarta(mibaraja,rdmType,rdmValue)){
				mibaraja.add(new Carta(MAZO_TYPE[rdmType], MAZO_VALUE[rdmValue]));
			}
		}
		return mibaraja;
	}
	
	private static boolean hasCarta(ArrayList<Carta> mibaraja, int indexType, int indexValue){ 
		boolean encontrado = false;
		for (int i=0;i<mibaraja.size(); i++) {
			if (mibaraja.get(i).getNombre().equals(MAZO_TYPE[indexType])&&
					mibaraja.get(i).getValor().equals(MAZO_VALUE[indexValue])){
				 encontrado=true; 
				 break; 
			}
		}
		return encontrado; 
	}
	
}
