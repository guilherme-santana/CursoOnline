package br.com.clogos.curso.dao.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.clogos.curso.dao.UsuarioDAO;
import br.com.clogos.curso.entidades.Usuario;
import br.com.clogos.curso.entidades.UsuarioCurso;
import br.com.clogos.curso.jpa.JPAConect;
import br.com.clogos.curso.util.Util;
import br.com.clogos.curso.vo.PerfilUsuarioCursoVO;
import br.com.clogos.curso.vo.UsuarioCertificadoVO;

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
	
	@SuppressWarnings("rawtypes")
	public UsuarioCertificadoVO obterDadosEmitirCertificado(Long idUsuario, Long idCurso) throws PersistenceException {
		em = JPAConect.getEntityManager();
		StringBuilder sql = new StringBuilder();
		UsuarioCertificadoVO vo = null;
		
		sql.append("SELECT u.cpfUsuario, u.nomeUsuario, c.nomeCurso, c.cargaHorariaCurso, uc.codigoCertificado, uc.dataConclusaoCurso ");
		sql.append("FROM USUARIO u INNER JOIN USUARIOCURSO uc ON u.idUsuario=uc.idUsuario ");
		sql.append("INNER JOIN CURSO c ON uc.idCurso=c.idCurso ");
		sql.append("WHERE u.idUsuario=? AND c.idCurso=?");
		
		try {
			Query query = em.createNativeQuery(sql.toString()).setParameter(1, idUsuario).setParameter(2, idCurso);
			Iterator i = query.getResultList().iterator();
			
			while(i.hasNext()) {
				Object[] obj = (Object[]) i.next(); 
				vo = new UsuarioCertificadoVO();
				vo.setCpfUsuario(obj[0].toString());
				vo.setNomeUsuario(obj[1].toString());
				vo.setNomecurso(obj[2].toString());
				vo.setCargaHoraria(Integer.valueOf(obj[3].toString()));
				vo.setCodigoCertificado(obj[4].toString());
				vo.setDataConclusao(Util.convertStringToDate(obj[5].toString()));
			}

		} catch (NoResultException e) { 
			throw new PersistenceException(e);
		} catch (PersistenceException e) {
			throw new PersistenceException(e);
		} finally {
			if(em.isOpen()) {
				em.close();
			}
		}
		
		return vo;
	}
	
	public void updateUsuarioCursoConclusao(UsuarioCurso obj) {
		em = JPAConect.getEntityManager();
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE USUARIOCURSO SET dataConclusaoCurso=:dataConclusao, cursoConcluido=1, codigoCertificado=:codigo ");
		sql.append("WHERE idUsuario=:idUsuario AND idCurso=:idCurso");
		
		try {
			em.getTransaction().begin();
			
			Query query = em.createNativeQuery(sql.toString())
					.setParameter("dataConclusao", obj.getDataConclusaoCurso())
					.setParameter("codigo", obj.getCodigoCertificado())
					.setParameter("idUsuario", obj.getPk().getIdUsuario())
					.setParameter("idCurso", obj.getPk().getIdCurso());
			query.executeUpdate();
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			throw new PersistenceException(e);
		} finally {
			if(em.isOpen()) {
				em.close();
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List<PerfilUsuarioCursoVO> obterListaCursosUsuario(Long idUsuario) throws PersistenceException {
		em = JPAConect.getEntityManager();
		StringBuilder sql = new StringBuilder();
		List<PerfilUsuarioCursoVO> lista = new LinkedList<PerfilUsuarioCursoVO>();
		PerfilUsuarioCursoVO vo = null;
		
		sql.append("SELECT C.nomeCurso, C.cargaHorariaCurso, UC.cursoConcluido, C.idCurso FROM USUARIOCURSO UC ");
		sql.append("INNER JOIN CURSO C ON UC.idCurso=C.idCurso ");
		sql.append("WHERE UC.idUsuario=?");
		
		try {
			Query query = em.createNativeQuery(sql.toString()).setParameter(1, idUsuario);
			Iterator i = query.getResultList().iterator();
			
			while(i.hasNext()) {
				Object[] obj = (Object[]) i.next(); 
				vo = new PerfilUsuarioCursoVO();
				vo.setNomeCurso(obj[0].toString());
				vo.setCargaHoraria(Integer.valueOf(obj[1].toString()));
				vo.setCursoConcluido(preencherConcluido(Boolean.valueOf(obj[2].toString())));
				vo.setIdCurso(Long.valueOf(obj[3].toString()));
				lista.add(vo);
			}

		} catch (NoResultException e) { 
			throw new PersistenceException(e);
		} catch (PersistenceException e) {
			throw new PersistenceException(e);
		} finally {
			if(em.isOpen()) {
				em.close();
			}
		}
		
		return lista;
	}
	
	private String preencherConcluido(Boolean b) {
		if(b) {
			return "Sim";
		} else {
			return "Não";
		}
	}
}
