package br.com.clogos.curso.servico;

import java.util.List;

import br.com.clogos.curso.dao.GenericDAO;
import br.com.clogos.curso.dao.impl.GenericDAOImpl;
import br.com.clogos.curso.entidades.Curso;

public class IndexServico {
	private GenericDAO genericDAO;
	
	@SuppressWarnings("rawtypes")
	public GenericDAO getGenericDAO() {
		return genericDAO == null ? genericDAO = new GenericDAOImpl() : genericDAO;
	}
	
	@SuppressWarnings("unchecked")
	public List<Curso> listarTodosCursos() {
		return (List<Curso>) getGenericDAO().findAll(Curso.class, "nomeCurso", "", "");
	}
}
