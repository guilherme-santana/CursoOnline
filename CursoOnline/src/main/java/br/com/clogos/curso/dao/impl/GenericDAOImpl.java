package br.com.clogos.curso.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.clogos.curso.dao.GenericDAO;
import br.com.clogos.curso.dao.ObjectModel;
import br.com.clogos.curso.jpa.JPAConect;

@Named
public class GenericDAOImpl<T extends ObjectModel> implements Serializable, GenericDAO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager entityManager;
	
	public GenericDAOImpl() {}
	
	public Boolean save(Object oT) {
		entityManager = JPAConect.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(oT);
			entityManager.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			if(entityManager.isOpen()) {
				entityManager.close();
			}
		}
		
	}

	public Boolean update(Object oT) {
		entityManager = JPAConect.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(oT);
			entityManager.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		} finally {
			if(entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}

	public Boolean delete(Object oT) {
		entityManager = JPAConect.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.merge(oT));
			entityManager.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			if(entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}
	
	public Boolean saveList(List<?> list) {
		entityManager = JPAConect.getEntityManager();
		try {
		    entityManager.getTransaction().begin();
		    for(Object oT : list) {
		    	entityManager.persist(oT);
		    }
		    entityManager.getTransaction().commit();
		    return Boolean.valueOf(true);
		} catch (PersistenceException e) {
		    entityManager.getTransaction().rollback();
		    e.printStackTrace();
		    return Boolean.valueOf(false);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<?> findAll(Class<?> clazz, String coluna, String order, String join) {
		entityManager = JPAConect.getEntityManager();
		List<Object> lista = new ArrayList<Object>();
		try {
			entityManager.getTransaction().begin();
			String nameClass = clazz.getSimpleName();
			lista = entityManager.createQuery("SELECT c FROM " + nameClass+ " c "+join+" ORDER BY c."+coluna+" "+order)
					.getResultList();
			entityManager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		} finally {
			if(entityManager.isOpen()) {
				entityManager.close();
			}
		}
        return lista;
    }
	
	public Object findID(Class<?> clazz, String coluna, Long id) {
		entityManager = JPAConect.getEntityManager();
		Object obj = null;
		try {
			entityManager.getTransaction().begin();
			String nameClass = clazz.getSimpleName();
			obj = entityManager.createQuery("SELECT c FROM " + nameClass+ " c WHERE "+coluna+" = "+id)
					.getSingleResult();
			entityManager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		} finally {
			if(entityManager.isOpen()) {
				entityManager.close();
			}
		}
        return obj;
    }
	
	@SuppressWarnings("unchecked")
	public  List<?> findIDList(Class<?> clazz, String coluna, Long id) {
		entityManager = JPAConect.getEntityManager();
		List<Object> lista = new ArrayList<Object>();
		try {
			entityManager.getTransaction().begin();
			String nameClass = clazz.getSimpleName();
			lista = entityManager.createQuery("SELECT c FROM " + nameClass+ " c WHERE "+coluna+" = "+id)
					.getResultList();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		} finally {
			if(entityManager.isOpen()) {
				entityManager.close();
			}
		}
        return lista;
    }
	
	public Object findString(Class<?> clazz, String coluna, String param) throws PersistenceException {
		entityManager = JPAConect.getEntityManager();
		Object obj = null;
		try {
			entityManager.getTransaction().begin();
			String nameClass = clazz.getSimpleName();
			obj = entityManager.createQuery("SELECT c FROM " + nameClass+ " c WHERE "+coluna+" = "+"'"+param+"'")
					.getSingleResult();
			entityManager.getTransaction().commit();
		} catch (NoResultException e) { 
			return null;
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			throw new PersistenceException(e);
		} finally {
			if(entityManager.isOpen()) {
				entityManager.close();
			}
		}
        return obj;
    }
}
