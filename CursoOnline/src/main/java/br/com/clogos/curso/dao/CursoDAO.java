package br.com.clogos.curso.dao;

import javax.persistence.PersistenceException;

import br.com.clogos.curso.entidades.ConteudoCurso;
import br.com.clogos.curso.entidades.Usuario;

public interface CursoDAO {
	ConteudoCurso obterConteudoCurso(Long idCurso) throws PersistenceException;
	Boolean existeCursoAndamento(Usuario usuario) throws PersistenceException;
	Boolean existeCursoAndamentoNaoFinalizado(Usuario usuario) throws PersistenceException;
	Boolean verificarCursoAndamentoEstaFinalizado(Usuario usuario) throws PersistenceException;
}
