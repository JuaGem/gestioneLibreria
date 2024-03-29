package it.gestionelibreria.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionelibreria.service.MyServiceFactory;

@WebServlet("/ListLibriServlet")
public class ListLibriServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ListLibriServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setAttribute("listaLibriAttribute", MyServiceFactory.getLibroServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		request.getRequestDispatcher("/libro/results.jsp").forward(request, response);
		
	}

}
