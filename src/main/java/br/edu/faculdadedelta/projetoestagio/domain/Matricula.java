package br.edu.faculdadedelta.projetoestagio.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import br.edu.faculdadedelta.projetoestagio.domain.enums.StatusPagamento;
import br.edu.faculdadedelta.projetoestagio.domain.enums.StatusPresenca;

@Entity
public class Matricula implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MatriculaPK id = new MatriculaPK();

	private Date dataMatricula;
	private Integer statusPagamento;
	private Integer statusPresenca;

	public Matricula() {
	}

	public Matricula(Aluno aluno, Curso curso, Date dataMatricula, StatusPagamento statusPagamento,
			StatusPresenca statusPresenca) {
		super();
		id.setAluno(aluno);
		id.setCurso(curso);
		this.dataMatricula = dataMatricula;
		this.statusPagamento = statusPagamento.getCod();
		this.statusPresenca = statusPresenca.getCod();
	}

	public Aluno getAluno() {
		return id.getAluno();
	}
	
	public void setAluno(Aluno aluno) {
		id.setAluno(aluno);
	}

	public Curso getCurso() {
		return id.getCurso();
	}
	
	public void setCurso(Curso curso) {
		id.setCurso(curso);
	}

	public MatriculaPK getId() {
		return id;
	}

	public void setId(MatriculaPK id) {
		this.id = id;
	}

	public Date getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public StatusPagamento getStatusPagamento() {
		return StatusPagamento.toEnum(statusPagamento);
	}

	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento.getCod();
	}

	public StatusPresenca getStatusPresenca() {
		return StatusPresenca.toEnum(statusPresenca);
	}

	public void setStatusPresenca(StatusPresenca statusPresenca) {
		this.statusPresenca = statusPresenca.getCod();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matricula other = (Matricula) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
