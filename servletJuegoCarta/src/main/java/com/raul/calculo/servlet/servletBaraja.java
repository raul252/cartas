package com.raul.calculo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.raul.calculo.business.*;
import com.raul.calculo.view.BarajaView;

/**
 * Servlet implementation class servletBaraja
 */
@WebServlet("/servletBaraja")
public class servletBaraja extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String STATEFUL_JAVABEAN_KEY = "baraja_bean"; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletBaraja() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gamecounter = 0;
		
		GameServicios ba = getStatefulBaraja(request);
		ArrayList<ArrayList<Carta>> mazo;
		gamecounter = ba.getContador();
		PrintWriter out = response.getWriter();
		Integer valor = 0;
		BarajaView b = null;
		if (gamecounter == 0){
			mazo = ba.empiezaJuego();
			response.setContentType("text/html");
			out = response.getWriter();
			b = new BarajaView(mazo);
			out.println(b.renderFirstView());
			out.close();
		} else {
			switch (gamecounter) {
			case 1:
				//Procesar el primer envio
				response.setContentType("text/html");
				 out = response.getWriter();
				valor = Integer.parseInt(request.getParameter("enviovalor"));
				//Obtener la mesa ordenada
				mazo = ba.nextTable(valor);
				b = new BarajaView(mazo);
				out.println(b.renderFirstView());
				break;
			case 2:
				//Procesar el segundo envio
				response.setContentType("text/html");
				out = response.getWriter();
				valor = Integer.parseInt(request.getParameter("enviovalor"));
				//Obtener la mesa ordenada
				mazo = ba.nextTable(valor);
				b = new BarajaView(mazo);
				out.println(b.renderFirstView());
				break;
			case 3:
				response.setContentType("text/html");
				out = response.getWriter();
				valor = Integer.parseInt(request.getParameter("enviovalor"));
				Carta cartaSeleccionada = ba.getCartaElegida(valor); 
				out.print("<html><body>");
				out.print("<h3>La carta selecionada es "+cartaSeleccionada.getNombre()+" " + cartaSeleccionada.getValor()+"</h3>");
				out.print("</body></html>");
				break;
			}
		}
	}
	
	private GameServicios getStatefulBaraja(HttpServletRequest request) throws ServletException {
		HttpSession httpSession = request.getSession(true);
		GameServicios statefulTestBean = 
				(GameServicios) httpSession.getAttribute(STATEFUL_JAVABEAN_KEY);


		if (statefulTestBean == null) {
			try {

				InitialContext ic = new InitialContext();
				statefulTestBean =   (GameServicios) ic.lookup("java:module/GameServicios");
				httpSession.setAttribute(STATEFUL_JAVABEAN_KEY, statefulTestBean);	          	       
			} catch (NamingException e) {
				throw new ServletException(e);
			}
		}
		return statefulTestBean;
	}

}
