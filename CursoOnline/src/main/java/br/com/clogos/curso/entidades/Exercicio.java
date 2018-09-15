package br.com.clogos.curso.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.clogos.curso.dao.ObjectModel;

@Entity
@Table(name="EXERCICIO")
public class Exercicio implements ObjectModel {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idExercicio")
	private Long idExercicio;
	
	@Column(name="questaoExercicio")
	private String questaoExercicio;
	
	public Long getIdExercicio() {
		return idExercicio;
	}
	public void setIdExercicio(Long idExercicio) {
		this.idExercicio = idExercicio;
	}
	public String getQuestaoExercicio() {
		return questaoExercicio;
	}
	public void setQuestaoExercicio(String questaoExercicio) {
		this.questaoExercicio = questaoExercicio;
	}
}
