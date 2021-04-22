package it.gestionelibreria.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.gestionelibreria.dao.ILibroDAO;
import it.gestionelibreria.model.Libro;
import it.gestionelibreria.web.listener.LocalEntityManagerFactoryListener;

public class LibroServiceImpl implements ILibroService {

	private ILibroDAO libroDAO;
	
	@Override
	public void setLibroDao(ILibroDAO libroDao) {
		this.libroDAO = libroDao;
	}

	@Override
	public List<Libro> listAll() throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			libroDAO.setEntityManager(entityManager);

			return libroDAO.list();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public Libro caricaSingoloElemento(Long idInput) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			libroDAO.setEntityManager(entityManager);

			return libroDAO.findOne(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public void aggiorna(Libro input) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
		
		try {
			
			entityManager.getTransaction().begin();
			
			libroDAO.setEntityManager(entityManager);
			
			libroDAO.update(entityManager.merge(input));
			
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public void inserisciNuovo(Libro input) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			entityManager.getTransaction().begin();

			libroDAO.setEntityManager(entityManager);

			libroDAO.insert(input);

			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public void rimuovi(Libro input) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			entityManager.getTransaction().begin();

			libroDAO.setEntityManager(entityManager);
			
			libroDAO.delete(input);

			entityManager.getTransaction().commit();

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
		
	}

	@Override
	public List<Libro> findByExample(Libro input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
