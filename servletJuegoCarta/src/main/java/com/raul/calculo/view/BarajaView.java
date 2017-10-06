package com.raul.calculo.view;

import java.util.ArrayList;
import com.raul.calculo.business.Carta;

public class BarajaView {
	
	ArrayList<ArrayList<Carta>> mazo = new ArrayList<ArrayList<Carta>>();
	public BarajaView(ArrayList<ArrayList<Carta>> mazo)
	{
		this.mazo = mazo;
	}
	
	public String renderFirstView()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<html><title>Mesa</title><body><table style='width:100%' class='table'><tbody><tr><th>Pila 1</th><th>Pila 2</th><th>Pila 3</th></tr>");
		int contador=0; int numeroTabla=0;
		while (contador < 7)
		{
			sb.append("<tr>");
			for (numeroTabla=0; numeroTabla<3; numeroTabla++)
			{
				sb.append("<td>"+mazo.get(numeroTabla).get(contador).getNombre()+
						" "+mazo.get(numeroTabla).get(contador).getValor()+"</td>");
			}
			sb.append("</tr>");
			contador++;
		}
		sb.append("<tr><td></td></tr></table><table><tr></td><form method='POST' action='/servletJuegoCarta/servlet/baraja'><label>Selecciona la columna donde está tu carta: </label><select name='enviovalor'>");
		for (int j=0; j<3; j++){
			sb.append("<option value="+(j+1)+">Columna "+(j+1)+"</option>");
		}
		sb.append("</select><input type='submit' value='Enviar'></form></td></tr></table></body></html>");
		return sb.toString();
	}
}
