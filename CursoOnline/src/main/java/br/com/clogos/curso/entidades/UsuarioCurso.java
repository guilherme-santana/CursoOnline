package br.com.clogos.curso.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.clogos.curso.dao.ObjectModel;

@Entity
@Table(name="USUARIOCURSO")
public class UsuarioCurso implements ObjectModel {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private UsuarioCursoPK pk;
	
	@Column(name="dataInicioCurso")
	private Date dataInicioCurso;
	
	@Column(name="cursoConcluido")
	private Boolean cursoConcluido;
	
	@Column(name="dataConclusaoCurso")
	private Date dataConclusaoCurso;
	
	@Column(name="codigoCertificado")
	private String codigoCertificado;
	
	public UsuarioCursoPK getPk() {
		return pk;
	}
	public void setPk(UsuarioCursoPK pk) {
		this.pk = pk;
	}
	public Date getDataInicioCurso() {
		return dataInicioCurso;
	}
	public void setDataInicioCurso(Date dataInicioCurso) {
		this.dataInicioCurso = dataInicioCurso;
	}
	public Boolean getCursoConcluido() {
		return cursoConcluido;
	}
	public void setCursoConcluido(Boolean cursoConcluido) {
		this.cursoConcluido = cursoConcluido;
	}
	public Date getDataConclusaoCurso() {
		return dataConclusaoCurso;
	}
	public void setDataConclusaoCurso(Date dataConclusaoCurso) {
		this.dataConclusaoCurso = dataConclusaoCurso;
	}
	public String getCodigoCertificado() {
		return codigoCertificado;
	}
	public void setCodigoCertificado(String codigoCertificado) {
		this.codigoCertificado = codigoCertificado;
	}
}
