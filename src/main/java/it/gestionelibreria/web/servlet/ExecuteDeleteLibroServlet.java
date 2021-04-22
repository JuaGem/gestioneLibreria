package it.gestionelibreria.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.gestionelibreria.model.Libro;
import it.gestionelibreria.service.MyServiceFactory;

@WebServlet("/ExecuteDeleteLibroServlet")
public class ExecuteDeleteLibroServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ExecuteDeleteLibroServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idLibroParam = request.getParameter("idDeleteInput");
		
		if (!NumberUtils.isCreatable(idLibroParam)) {
			request.setAttribute("errorMessage", "Attenzione, si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		try {
			
			Libro libro = MyServiceFactory.getLibroServiceInstance().caricaSingoloElemento(Long.parseLong(idLibroParam));
			MyServiceFactory.getLibroServiceInstance().rimuovi(libro);
			request.setAttribute("listaLibriAttribute", MyServiceFactory.getLibroServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione eseguita con successo!");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/libro/results.jsp").forward(request, response);
		
	}

}
