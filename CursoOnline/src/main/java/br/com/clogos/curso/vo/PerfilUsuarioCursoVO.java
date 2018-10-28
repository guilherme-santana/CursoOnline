package br.com.clogos.curso.vo;

public class PerfilUsuarioCursoVO {

	private String nomeCurso;
	private Integer cargaHoraria;
	private String cursoConcluido;
	private Long idCurso;
	
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public Integer getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public String getCursoConcluido() {
		return cursoConcluido;
	}
	public void setCursoConcluido(String cursoConcluido) {
		this.cursoConcluido = cursoConcluido;
	}
	public Long getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}
}
