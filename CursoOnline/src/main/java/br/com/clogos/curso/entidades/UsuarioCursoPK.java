package br.com.clogos.curso.entidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.clogos.curso.dao.ObjectModel;

@Embeddable
public class UsuarioCursoPK implements ObjectModel {
	private static final long serialVersionUID = 1L;
	
	@Column(name="idUsuario")
	private Long idUsuario;
	@Column
	private Long idCurso;
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}
}
