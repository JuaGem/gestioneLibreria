package it.gestionelibreria.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.gestionelibreria.service.MyServiceFactory;

@WebServlet("/ExecuteVisualizzaLibroServlet")
public class ExecuteVisualizzaLibroServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ExecuteVisualizzaLibroServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idLibroParam = request.getParameter("idLibro");
		
		if (!NumberUtils.isCreatable(idLibroParam)) {
			request.setAttribute("errorMessage", "Attenzione, si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		try {
			request.setAttribute("visualizza_libro_attribute", MyServiceFactory.getLibroServiceInstance().caricaSingoloElemento(Long.parseLong(idLibroParam)));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/libro/show.jsp").forward(request, response);
	}

}
