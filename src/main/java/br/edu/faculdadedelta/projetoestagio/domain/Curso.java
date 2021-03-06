package br.edu.faculdadedelta.projetoestagio.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private String local;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;
	private String hora;
	private String duracao;
	private Double valor;
	private Integer qtdVaga;
	private Integer vagasDisponiveis;

	@ManyToOne
	@JoinColumn(name = "instrutor_id")
	private Instrutor instrutor;

	@OneToMany(mappedBy = "id.curso")
	private Set<Matricula> matriculas = new HashSet<>();

	public Curso() {
		super();
	}

	public Curso(Long id, String nome, String descricao, String local, Date data, String hora, String duracao,
			Double valor, Integer qtdVaga, Instrutor instrutor) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.local = local;
		this.data = data;
		this.hora = hora;
		this.duracao = duracao;
		this.valor = valor;
		this.qtdVaga = qtdVaga;
		this.instrutor = instrutor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}

	public Integer getQtdVaga() {
		return qtdVaga;
	}

	public void setQtdVaga(Integer qtdVaga) {
		this.qtdVaga = qtdVaga;
	}

	public Set<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(Set<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	
	public Integer getVagasDisponiveis() {
		if(matriculas.isEmpty()) {
			vagasDisponiveis = qtdVaga;
		}else {
			vagasDisponiveis = (qtdVaga - matriculas.size());
		}
		return vagasDisponiveis;
	}

	public void setVagasDisponiveis(Integer vagasDisponiveis) {
		this.vagasDisponiveis = vagasDisponiveis;
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
		Curso other = (Curso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
