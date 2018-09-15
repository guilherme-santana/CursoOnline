package br.com.clogos.curso.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.clogos.curso.dao.ObjectModel;

@Entity
@Table(name="RESPOSTAEXERCICIO")
public class RespostaExercicio implements ObjectModel {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idResposta")
	private Long idResposta;
	
	@Column(name="opcaoResposta")
	private String opcaoResposta;
	
	@Column(name="bolCorreta")
	private Boolean bolCorreta;
	
	@ManyToOne @JoinColumn(name="fkExercicio")
	private Exercicio exercicio;
	
	public Long getIdResposta() {
		return idResposta;
	}
	public void setIdResposta(Long idResposta) {
		this.idResposta = idResposta;
	}
	public String getOpcaoResposta() {
		return opcaoResposta;
	}
	public void setOpcaoResposta(String opcaoResposta) {
		this.opcaoResposta = opcaoResposta;
	}
	public Boolean getBolCorreta() {
		return bolCorreta;
	}
	public void setBolCorreta(Boolean bolCorreta) {
		this.bolCorreta = bolCorreta;
	}
	public Exercicio getExercicio() {
		return exercicio;
	}
	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}
}
