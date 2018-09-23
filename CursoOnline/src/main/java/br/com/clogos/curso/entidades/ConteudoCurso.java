package br.com.clogos.curso.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.clogos.curso.dao.ObjectModel;

@Entity
@Table(name="CONTEUDOCURSO")
public class ConteudoCurso implements ObjectModel {
	private static final long serialVersionUID = 799872093651686623L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idConteudo")
	private Long idConteudo;
	
	@Column(name="introducaoCurso", columnDefinition="text", nullable=false)
	private String introducaoCurso;
	
	@Column(name="linkVideo")
	private String linkVideo;
	
	@OneToOne(mappedBy="conteudoCurso")
	private Curso curso;

	public Long getIdConteudo() {
		return idConteudo;
	}

	public void setIdConteudo(Long idConteudo) {
		this.idConteudo = idConteudo;
	}

	public String getIntroducaoCurso() {
		return introducaoCurso;
	}

	public void setIntroducaoCurso(String introducaoCurso) {
		this.introducaoCurso = introducaoCurso;
	}

	public String getLinkVideo() {
		return linkVideo;
	}

	public void setLinkVideo(String linkVideo) {
		this.linkVideo = linkVideo;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}
