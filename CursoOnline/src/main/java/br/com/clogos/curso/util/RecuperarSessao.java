package br.com.clogos.curso.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import br.com.clogos.curso.entidades.Usuario;

public class RecuperarSessao {
	
	public static Long getIdCursoAndamento(HttpServletRequest request) throws ServletException {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuariologado");
		if(usuario != null) {
			return usuario.getCursoAndamento();
		} else {
			throw new ServletException("Erro ao recuperar Curso Andamento Session");
		}
	}
}
