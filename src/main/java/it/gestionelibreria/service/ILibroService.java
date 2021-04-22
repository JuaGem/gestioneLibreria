package it.gestionelibreria.service;

import java.util.List;

import it.gestionelibreria.dao.ILibroDAO;
import it.gestionelibreria.model.Libro;

public interface ILibroService {

	public void setLibroDao(ILibroDAO libroDao);

	public List<Libro> listAll() throws Exception;

	public Libro caricaSingoloElemento(Long idInput) throws Exception;

	public void aggiorna(Libro input) throws Exception;

	public void inserisciNuovo(Libro input) throws Exception;

	public void rimuovi(Libro input) throws Exception;

	public List<Libro> findByExample(Libro input) throws Exception;
	
}
