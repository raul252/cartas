package com.raul.calculo.view;

import java.util.ArrayList;
import com.raul.calculo.business.Carta;

public class BarajaView {
	
	ArrayList<Carta> mazo = new ArrayList<>();
	public BarajaView(ArrayList<Carta> mazo)
	{
		this.mazo = mazo;
	}
	
	public String renderFirstView()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<html><title>Mazo</title><body><table style='width:100%' class='table'><tbody><tr><th>Pila 1</th><th>Pila 2</th><th>Pila 3</th></tr>");
		int contador=0;int valor = 0;
		while (contador < 21)
		{
			sb.append("<tr>");
			for (int j=0; j<3;j++)
			{
				valor = (contador/3)+1;
				sb.append("<td>Carta: "+valor+" "+mazo.get(contador).getNombre()+
						" "+mazo.get(contador).getValor()+"</td>");
				contador++;
			}
			sb.append("</tr>");
		}
		sb.append("<tr><td><form method='POST' action='/servletJuegoCarta/servlet/baraja'><label>Selecciona la columna donde está tu carta: </label><select name='enviovalor'>");
		for (int j=0; j<3; j++){
			sb.append("<option value="+(j+1)+">fila "+(j+1)+"</option>");
		}
		sb.append("</select><input type='submit' value='Enviar'></form></td></tr></table></body></html>");
		return sb.toString();
	}
}
