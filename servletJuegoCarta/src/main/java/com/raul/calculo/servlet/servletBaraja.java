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
		
		Baraja ba = getStatefulBaraja(request);
		gamecounter = ba.getContador();
		if (gamecounter == 0){
			ArrayList<Carta> mazo = ba.getDatosAleatorios();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			BarajaView b = new BarajaView(mazo);
			out.println(b.renderFirstView());
			out.close();
			ba.sumaContador();
		} else {
			switch (gamecounter) {
			case 1:
				//Procesar el primer envio
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				String valor = request.getParameter("enviovalor");
				out.println(request.getParameter("enviovalor"));
				break;
			case 2:
				
				break;
			case 3:
				
				break;

			default:
				break;
			}
		}
	}
	
	private Baraja getStatefulBaraja(HttpServletRequest request) throws ServletException {
		HttpSession httpSession = request.getSession(true);
		Baraja statefulTestBean = 
				(Baraja) httpSession.getAttribute(STATEFUL_JAVABEAN_KEY);


		if (statefulTestBean == null) {
			try {

				InitialContext ic = new InitialContext();
				statefulTestBean =   (Baraja) ic.lookup("java:module/Baraja");
				httpSession.setAttribute(STATEFUL_JAVABEAN_KEY, statefulTestBean);	          	       
			} catch (NamingException e) {
				throw new ServletException(e);
			}
		}
		return statefulTestBean;
	}

}
