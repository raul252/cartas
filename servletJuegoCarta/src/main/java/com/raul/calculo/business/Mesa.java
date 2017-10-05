package com.raul.calculo.business;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

public class Mesa {


	private static final String MAZO_TYPE[] = { "corazones","trébol", "picas", "diamantes" };
	private static final String MAZO_VALUE[] = { "1","2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	private int counter;
	
	private ArrayList<ArrayList<Carta>> table;
    /**
     * Default constructor. 
     */
	
	public Mesa(){
		table = new ArrayList<>();
	}
	
	public int getCounter()
	{
		return counter;
	}
	
	public ArrayList<ArrayList<Carta>> startGame() {
		counter = 1;
		ArrayList<Carta> cartas21 = getDatosAleatorios();
		table = arrayToTable(cartas21);
		return table;
	}
	
	public ArrayList<ArrayList<Carta>> nextTable(int selection) {
		ArrayList<Carta> cartas21 = tableToArray(selection);
		counter++;
		table =arrayToTable(cartas21);
		return table; 
	}
	
	
	public Carta getCartaElegida(int selection){
		ArrayList<Carta> cartas21 = tableToArray(selection);
		return cartas21.get(10);
	}
	
	private ArrayList<Carta> tableToArray(int selection) {
		//Obtener un array21 según las tablas
		ArrayList<Carta> cartas21 = new ArrayList<>();
		int valorArray = selection-1;
		int j = 0;
		for (int i=0; i<21;i++) {
			if (selection==1){
				if (i==0) {valorArray=1; j=0;};
				if (i==7) {valorArray=0; j=0;};
				if (i==14) {valorArray=2; j=0;};
				cartas21.add(table.get(valorArray).get(j++));
				
			} else if (selection ==2) {
				if (i==0)  {valorArray=0;j=0;};
				if (i==7) {valorArray=1;j=0;};
				if (i==14) {valorArray=2;j=0;};
				cartas21.add(table.get(valorArray).get(j++));
				
			} else if (selection == 3) {
				if (i==0)  {valorArray=0;j=0;};
				if (i==7) {valorArray=2;j=0;};
				if (i==14) {valorArray=1;j=0;};
				cartas21.add(table.get(valorArray).get(j++));
			}
		}
		return cartas21;
	}

	private static ArrayList<ArrayList<Carta>> arrayToTable(ArrayList<Carta> cartas21) {
		//Obtener un array de 3 tablas
		ArrayList<ArrayList<Carta>> table = new ArrayList<ArrayList<Carta>>(); 
		table.add(new ArrayList<Carta>());
		table.add(new ArrayList<Carta>());
		table.add(new ArrayList<Carta>());
		int contador = 0;
		for (int i=0; i<21;i++) {
			if (contador < 3)
			{
				table.get(contador).add(cartas21.get(i));
				contador++;
			} else { 
				contador = 0; 
				table.get(contador).add(cartas21.get(i));contador++;}
		}
		return table;
	}
	

	private static ArrayList<Carta> getDatosAleatorios() {
		ArrayList<Carta> mibaraja = new ArrayList<Carta>();
		
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
