package br.com.clogos.curso.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import br.com.clogos.curso.entidades.Usuario;
import br.com.clogos.curso.entidades.UsuarioCurso;
import br.com.clogos.curso.vo.PerfilUsuarioCursoVO;
import br.com.clogos.curso.vo.UsuarioCertificadoVO;

public interface UsuarioDAO {
	
	Usuario existeUsuarioCastrado(String cpf, String email) throws PersistenceException;
	UsuarioCertificadoVO obterDadosEmitirCertificado(Long idUsuario, Long idCurso) throws PersistenceException;
	void updateUsuarioCursoConclusao(UsuarioCurso obj);
	List<PerfilUsuarioCursoVO> obterListaCursosUsuario(Long idUsuario) throws PersistenceException;
}
