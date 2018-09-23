package br.com.clogos.curso.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.clogos.curso.dao.ExercicioDAO;
import br.com.clogos.curso.jpa.JPAConect;

public class ExercicioDAOImpl implements ExercicioDAO {
	
	private EntityManager entityManager;

	@Override
	public Boolean respostaCorreta(Long idExercicio, Long idResposta) {
		entityManager = JPAConect.getEntityManager();
		Boolean ret = Boolean.FALSE;
		StringBuilder sql = new StringBuilder();
		
		sql.append("select RE.bolCorreta from EXERCICIO e");
		sql.append("INNER JOIN RESPOSTAEXERCICIO RE ON E.idExercicio=RE.fkExercicio");
		sql.append("WHERE E.idExercicio=:idExercicio AND RE.idResposta=:idResposta AND RE.bolCorreta=1");
		
		try {
			Query query = entityManager.createNamedQuery(sql.toString())
					.setParameter("idExercicio", idExercicio).setParameter("idResposta", idResposta);
			
			if(query.getResultList().size() > 0) {
				ret = Boolean.TRUE;
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		} finally {
			if(entityManager.isOpen()) {
				entityManager.close();
			}
		}
        return ret;
	}

}
