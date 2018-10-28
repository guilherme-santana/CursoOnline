package br.com.clogos.curso.servico;

import javax.persistence.PersistenceException;

import br.com.clogos.curso.dao.GenericDAO;
import br.com.clogos.curso.dao.impl.GenericDAOImpl;
import br.com.clogos.curso.entidades.Usuario;
import br.com.clogos.curso.seguranca.CriptografiaBase64;

public class ValidarUsuario {
	
	private static ValidarUsuario instance;
	private GenericDAO genericDAO;
	
	public static ValidarUsuario getInstance() {
		if( instance == null ) {
			instance = new ValidarUsuario();
		}
		return instance;
	}
	
	public Usuario validarCredenciais(Usuario usuario) throws PersistenceException {
		Usuario userValido = null;
		try {
			userValido = (Usuario) getGenericDAO().findString(Usuario.class, "emailUsuario", usuario.getEmailUsuario());	
			
			if(userValido != null) {
				if(!userValido.getSenhaUsuario().equals(CriptografiaBase64.encrypt(usuario.getSenhaUsuario()))) {
					userValido = null;
				}
			}
		} catch (PersistenceException e) {
			throw new PersistenceException(e);
		}
		return userValido;
	}
	
	@SuppressWarnings("rawtypes")
	public GenericDAO getGenericDAO() {
		return genericDAO == null ? genericDAO = new GenericDAOImpl() : genericDAO;
	}

}
