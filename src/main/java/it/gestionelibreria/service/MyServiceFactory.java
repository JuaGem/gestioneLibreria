package it.gestionelibreria.service;

import it.gestionelibreria.dao.ILibroDAO;
import it.gestionelibreria.dao.LibroDAOImpl;

public class MyServiceFactory {

	private static ILibroService LIBRO_SERVICE_INSTANCE = null;
	private static ILibroDAO LIBRODAO_INSTANCE = null;

	public static ILibroService getLibroServiceInstance() {
		if (LIBRO_SERVICE_INSTANCE == null)
			LIBRO_SERVICE_INSTANCE = new LibroServiceImpl();

		if (LIBRODAO_INSTANCE == null)
			LIBRODAO_INSTANCE = new LibroDAOImpl();

		LIBRO_SERVICE_INSTANCE.setLibroDao(LIBRODAO_INSTANCE);

		return LIBRO_SERVICE_INSTANCE;
	}

}
