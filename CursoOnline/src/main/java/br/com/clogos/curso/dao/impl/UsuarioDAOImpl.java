package br.com.clogos.curso.dao.impl;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.clogos.curso.dao.UsuarioDAO;
import br.com.clogos.curso.entidades.Usuario;
import br.com.clogos.curso.jpa.JPAConect;

@Named
public class UsuarioDAOImpl implements UsuarioDAO {

	private EntityManager em;
	
	public UsuarioDAOImpl() {}
	
	@Override
	public Usuario existeUsuarioCastrado(String cpf, String email) throws PersistenceException {
		em = JPAConect.getEntityManager();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT u FROM Usuario u WHERE u.cpfUsuario=:cpf OR u.emailUsuario=:email");
		Usuario user = null;
		
		try {
			user = (Usuario) em.createQuery(sql.toString())
					.setParameter("cpf", cpf)
					.setParameter("email", email)
					.getSingleResult();
		} catch (NoResultException e) { 
			return null;
		} catch (PersistenceException e) {
			throw new PersistenceException(e);
		} finally {
			if(em.isOpen()) {
				em.close();
			}
		}
		
		return user;
	}
}
