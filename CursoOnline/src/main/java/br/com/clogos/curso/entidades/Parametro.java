package br.com.clogos.curso.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.clogos.curso.dao.ObjectModel;

@Entity
@Table(name="PARAMETRO")
public class Parametro implements ObjectModel {
	private static final long serialVersionUID = -7330245088711534290L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idParametro")
	private Long idParametro;
	
	@Column(name="nomeParametro")
	private String nomeParametro;
	
	@Column(name="valorParametro")
	private String valorParametro;
	
	public Long getIdParametro() {
		return idParametro;
	}
	public void setIdParametro(Long idParametro) {
		this.idParametro = idParametro;
	}
	public String getNomeParametro() {
		return nomeParametro;
	}
	public void setNomeParametro(String nomeParametro) {
		this.nomeParametro = nomeParametro;
	}
	public String getValorParametro() {
		return valorParametro;
	}
	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}
}
