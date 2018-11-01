package br.com.clogos.curso.entidades;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.clogos.curso.dao.ObjectModel;

@Entity
@Table(name="CURSO")
public class Curso implements ObjectModel {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idCurso")
	private Long idCurso;
	
	@Column(name="nomeCurso")
	private String nomeCurso;
	
	@Column(name="descricaoCurso")
	private String descricaoCurso;
	
	@Column(name="cargaHorariaCurso")
	private Integer cargaHorariaCurso;
	
	@Column(name="dataCriacaoCurso")
	private Date dataCriacaoCurso;
	
	@Column(name="nomeImagemCurso")
	private String nomeImagemCurso;
	
	@OneToOne(cascade=CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name="fkConteudo", referencedColumnName="idConteudo")
	private ConteudoCurso conteudoCurso;

	public Long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public String getDescricaoCurso() {
		return descricaoCurso;
	}

	public void setDescricaoCurso(String descricaoCurso) {
		this.descricaoCurso = descricaoCurso;
	}

	public Integer getCargaHorariaCurso() {
		return cargaHorariaCurso;
	}

	public void setCargaHorariaCurso(Integer cargaHorariaCurso) {
		this.cargaHorariaCurso = cargaHorariaCurso;
	}

	public Date getDataCriacaoCurso() {
		return dataCriacaoCurso;
	}

	public void setDataCriacaoCurso(Date dataCriacaoCurso) {
		this.dataCriacaoCurso = dataCriacaoCurso;
	}

	public String getNomeImagemCurso() {
		return nomeImagemCurso;
	}

	public void setNomeImagemCurso(String nomeImagemCurso) {
		this.nomeImagemCurso = nomeImagemCurso;
	}

	public ConteudoCurso getConteudoCurso() {
		return conteudoCurso;
	}

	public void setConteudoCurso(ConteudoCurso conteudoCurso) {
		this.conteudoCurso = conteudoCurso;
	}
}
