package it.gestionelibreria.dao;

public class MyDaoFactory {

	private static ILibroDAO libroDaoInstance = null;

	public static ILibroDAO getArticoloDAOInstance() {
		if (libroDaoInstance == null)
			libroDaoInstance = new LibroDAOImpl();

		return libroDaoInstance;
	}

}
