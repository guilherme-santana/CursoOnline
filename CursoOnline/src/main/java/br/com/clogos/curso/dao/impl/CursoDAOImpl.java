package br.com.clogos.curso.dao.impl;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.clogos.curso.dao.CursoDAO;
import br.com.clogos.curso.entidades.ConteudoCurso;
import br.com.clogos.curso.entidades.Curso;
import br.com.clogos.curso.entidades.Usuario;
import br.com.clogos.curso.entidades.UsuarioCurso;
import br.com.clogos.curso.jpa.JPAConect;

@Named
public class CursoDAOImpl implements CursoDAO {

	private EntityManager entityManager; 
	
	public CursoDAOImpl() {}
	
	@Override
	public ConteudoCurso obterConteudoCurso(Long idCurso) throws PersistenceException {
		entityManager = JPAConect.getEntityManager();
		StringBuilder sql = new StringBuilder();
		ConteudoCurso conteudoCurso = null;
		
		sql.append("SELECT cc FROM ConteudoCurso cc ");
		sql.append("JOIN FETCH cc.curso c ");
		sql.append("WHERE c.idCurso=:idCurso");
		
		try {
			conteudoCurso = (ConteudoCurso) entityManager.createQuery(sql.toString())
					.setParameter("idCurso", idCurso).getSingleResult();
			
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			throw new PersistenceException(e.getMessage());
		} finally {
			if(entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return conteudoCurso;
	}
	
	@Override
	public Curso obterCurso(Long idCurso) throws PersistenceException {
		entityManager = JPAConect.getEntityManager();
		StringBuilder sql = new StringBuilder();
		Curso curso = null;
		
		sql.append("SELECT c FROM Curso c ");
		sql.append("WHERE c.idCurso=:idCurso");
		
		try {
			curso = (Curso) entityManager.createQuery(sql.toString())
					.setParameter("idCurso", idCurso).getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			throw new PersistenceException(e.getMessage());
		} finally {
			if(entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return curso;
	}
	
	@Override
	public Boolean existeCursoAndamento(Usuario usuario) throws PersistenceException {
		entityManager = JPAConect.getEntityManager();
		StringBuilder sql = new StringBuilder();
		UsuarioCurso usuarioCurso = null;
		Boolean ret = Boolean.FALSE;
		
		sql.append("select uc from UsuarioCurso uc where uc.pk.idCurso=:idCurso and uc.pk.idUsuario=:idUsuario ");
		
		try {
			usuarioCurso = (UsuarioCurso) entityManager.createQuery(sql.toString())
					.setParameter("idCurso", usuario.getCursoAndamento())
					.setParameter("idUsuario", usuario.getIdUsuario()).getSingleResult();
			
			if (usuarioCurso != null) {
				ret = Boolean.TRUE;
			}
			
		} catch (NoResultException e) {
			ret = Boolean.FALSE;
		} catch (PersistenceException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			if(entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return ret;
	}
	
	@Override
	public Boolean existeCursoAndamentoNaoFinalizado(Usuario usuario) throws PersistenceException {
		entityManager = JPAConect.getEntityManager();
		StringBuilder sql = new StringBuilder();
		UsuarioCurso usuarioCurso = null;
		Boolean ret = Boolean.FALSE;
		
		sql.append("select uc from UsuarioCurso uc where uc.pk.idUsuario=:idUsuario and uc.cursoConcluido=0 and uc.pk.idCurso <> :idCurso ");
		
		try {
			usuarioCurso = (UsuarioCurso) entityManager.createQuery(sql.toString())
					.setParameter("idCurso", usuario.getCursoAndamento())
					.setParameter("idUsuario", usuario.getIdUsuario()).getSingleResult();
			
			if (usuarioCurso != null) {
				ret = Boolean.TRUE;
			}
			
		} catch (NoResultException e) {
			ret = Boolean.FALSE;
		} catch (PersistenceException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			if(entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return ret;
	}
	
	@Override
	public Boolean verificarCursoAndamentoEstaFinalizado(Usuario usuario) throws PersistenceException {
		entityManager = JPAConect.getEntityManager();
		StringBuilder sql = new StringBuilder();
		UsuarioCurso usuarioCurso = null;
		Boolean ret = Boolean.FALSE;
		
		sql.append("select uc from UsuarioCurso uc where uc.pk.idUsuario=:idUsuario and uc.cursoConcluido=1 and uc.pk.idCurso = :idCurso ");
		
		try {
			usuarioCurso = (UsuarioCurso) entityManager.createQuery(sql.toString())
					.setParameter("idCurso", usuario.getCursoAndamento())
					.setParameter("idUsuario", usuario.getIdUsuario()).getSingleResult();
			
			if (usuarioCurso != null) {
				ret = Boolean.TRUE;
			}
			
		} catch (NoResultException e) {
			ret = Boolean.FALSE;
		} catch (PersistenceException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			if(entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return ret;
	}

}
