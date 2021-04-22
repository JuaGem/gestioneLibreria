package it.gestionelibreria.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionelibreria.model.Libro;
import it.gestionelibreria.service.MyServiceFactory;

@WebServlet("/PrepareDeleteLibroServlet")
public class PrepareDeleteLibroServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public PrepareDeleteLibroServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idParameter = request.getParameter("idLibro");
		
		try {
			
			Libro libro = MyServiceFactory.getLibroServiceInstance().caricaSingoloElemento(Long.parseLong(idParameter));
			request.setAttribute("libro_delete", libro);
			request.getRequestDispatcher("/libro/delete.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/libro/delete.jsp").forward(request, response);
			return;
		}
		
	}

}
