package it.gestionelibreria.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionelibreria.model.Libro;
import it.gestionelibreria.service.MyServiceFactory;
import it.gestionelibreria.utility.UtilityLibroForm;

@WebServlet("/ExecuteInsertLibroServlet")
public class ExecuteInsertLibroServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ExecuteInsertLibroServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String titoloInputParam = request.getParameter("titolo");
		String genereInputParam = request.getParameter("genere");
		String pagineInputParam = request.getParameter("pagine");
		String dataPubblicazioneInputParam = request.getParameter("dataPubblicazione");
		
		Date dataPubblicazioneParsed = UtilityLibroForm.parseDatePubblicazioneFromString(dataPubblicazioneInputParam);
		
		if (!UtilityLibroForm.validateInput(titoloInputParam, genereInputParam, pagineInputParam, dataPubblicazioneInputParam)
			|| dataPubblicazioneInputParam == null) {
			Libro libroInstance = new Libro();
			libroInstance.setTitolo(titoloInputParam);
			libroInstance.setGenere(genereInputParam);
			if(!pagineInputParam.isEmpty() && UtilityLibroForm.checkInteger(pagineInputParam))
				libroInstance.setPagine(Integer.parseInt(pagineInputParam));
			if(dataPubblicazioneParsed!=null)
				libroInstance.setDataPubblicazione(dataPubblicazioneParsed);
			
			request.setAttribute("libroDaInserire", libroInstance);
			
			request.setAttribute("errorMessage", "Attenzione, sono presenti errori di validazione");
			request.getRequestDispatcher("/libro/insert.jsp").forward(request, response);
			return;
		}
		
		Libro libroInstance = new Libro(titoloInputParam, genereInputParam, Integer.parseInt(pagineInputParam), dataPubblicazioneParsed);
		
		try {
			MyServiceFactory.getLibroServiceInstance().inserisciNuovo(libroInstance);
			request.setAttribute("listaLibriAttribute", MyServiceFactory.getLibroServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/libro/insert.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/libro/results.jsp").forward(request, response);
		
	}

}
