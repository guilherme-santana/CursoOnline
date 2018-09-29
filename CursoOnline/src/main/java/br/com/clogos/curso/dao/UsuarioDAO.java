package br.com.clogos.curso.dao;

import javax.persistence.PersistenceException;

import br.com.clogos.curso.entidades.Usuario;

public interface UsuarioDAO {
	
	Usuario existeUsuarioCastrado(String cpf, String email) throws PersistenceException;
}
