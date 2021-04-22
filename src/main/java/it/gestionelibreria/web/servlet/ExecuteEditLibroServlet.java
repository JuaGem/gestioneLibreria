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

@WebServlet("/ExecuteEditLibroServlet")
public class ExecuteEditLibroServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ExecuteEditLibroServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idInputParam = request.getParameter("idEditInput");
		String titoloInputParam = request.getParameter("titolo");
		String genereInputParam = request.getParameter("genere");
		String pagineInputParam = request.getParameter("pagine");
		String dataPubblicazioneInputParam = request.getParameter("dataPubblicazione");
		
		Date dataPubblicazioneParsed = UtilityLibroForm.parseDatePubblicazioneFromString(dataPubblicazioneInputParam);
		
		if (!UtilityLibroForm.validateInput(titoloInputParam, genereInputParam, pagineInputParam, 
				dataPubblicazioneInputParam) || dataPubblicazioneParsed == null) {
			
			Libro libroTemp = new Libro();
			libroTemp.setId(Long.parseLong(idInputParam));
			libroTemp.setTitolo(titoloInputParam);
			libroTemp.setGenere(genereInputParam);
			if (!pagineInputParam.isEmpty() && UtilityLibroForm.checkInteger(pagineInputParam)) 
				libroTemp.setPagine(Integer.parseInt(pagineInputParam));
			libroTemp.setDataPubblicazione(dataPubblicazioneParsed);
			
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.setAttribute("edit_attribute", libroTemp);
			request.getRequestDispatcher("/libro/edit.jsp").forward(request, response);
			return;
			
		}
		
		Libro libroInstance = new Libro(titoloInputParam, genereInputParam, Integer.parseInt(pagineInputParam), dataPubblicazioneParsed);
		libroInstance.setId(Long.parseLong(idInputParam));
		
		try {
			
			MyServiceFactory.getLibroServiceInstance().aggiorna(libroInstance);
			request.setAttribute("listaLibriAttribute", MyServiceFactory.getLibroServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/libro/index.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/libro/results.jsp").forward(request, response);
		
	}

}
