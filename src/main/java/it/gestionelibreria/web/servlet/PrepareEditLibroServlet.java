package it.gestionelibreria.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionelibreria.service.MyServiceFactory;

@WebServlet("/PrepareEditLibroServlet")
public class PrepareEditLibroServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public PrepareEditLibroServlet() {
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idParameter = request.getParameter("idLibro");
		
		try {
			
			request.setAttribute("edit_attribute", MyServiceFactory.getLibroServiceInstance().caricaSingoloElemento(Long.parseLong(idParameter)));
			RequestDispatcher rd = request.getRequestDispatcher("/libro/edit.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione, si è verificato un errore.");
			request.getRequestDispatcher("/libro/edit.jsp").forward(request, response);
			return;
		}

	}

}
