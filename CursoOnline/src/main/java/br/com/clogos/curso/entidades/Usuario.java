package br.com.clogos.curso.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.clogos.curso.dao.ObjectModel;

@Entity
@Table(name="USUARIO")
public class Usuario implements ObjectModel {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idUsuario")
	private Long idUsuario;
	
	@Column(name="nomeUsuario", nullable=false)
	private String nomeUsuario;
	
	@Column(name="cpfUsuario", nullable=false)
	private String cpfUsuario;
	
	@Column(name="emailUsuario", nullable=false)
	private String emailUsuario;
	
	@Column(name="telefoneUsuario")
	private String telefoneUsuario;
	
	@Column(name="senhaUsuario", nullable=false)
	private String senhaUsuario;
	
	@Column(name="dataCadastroUsuario", nullable=true)
	private Date dataCadastroUsuario;
	
	@Transient
	private Long cursoAndamento;
	@Transient
	private Integer qdtCompartilhamento;
	@Transient
	private String dataCadastroFormatada;
	
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public String getCpfUsuario() {
		return cpfUsuario;
	}
	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}
	public String getEmailUsuario() {
		return emailUsuario;
	}
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	public String getSenhaUsuario() {
		return senhaUsuario;
	}
	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
	
	public String getTelefoneUsuario() {
		return telefoneUsuario;
	}
	public void setTelefoneUsuario(String telefoneUsuario) {
		this.telefoneUsuario = telefoneUsuario;
	}
	public Integer getQdtCompartilhamento() {
		return qdtCompartilhamento;
	}
	public void setQdtCompartilhamento(Integer qdtCompartilhamento) {
		this.qdtCompartilhamento = qdtCompartilhamento;
	}
	public Long getCursoAndamento() {
		return cursoAndamento;
	}
	public void setCursoAndamento(Long cursoAndamento) {
		this.cursoAndamento = cursoAndamento;
	}
	public Date getDataCadastroUsuario() {
		return dataCadastroUsuario == null ? new Date() : dataCadastroUsuario;
	}
	public void setDataCadastroUsuario(Date dataCadastroUsuario) {
		this.dataCadastroUsuario = dataCadastroUsuario;
	}
	public String getDataCadastroFormatada() {
		return dataCadastroFormatada;
	}
	public void setDataCadastroFormatada(String dataCadastroFormatada) {
		this.dataCadastroFormatada = dataCadastroFormatada;
	}
}
