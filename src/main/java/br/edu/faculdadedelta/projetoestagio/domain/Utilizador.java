package br.edu.faculdadedelta.projetoestagio.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.edu.faculdadedelta.projetoestagio.domain.enums.TipoUtilizador;

@Entity
public class Utilizador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Integer tipo;

	@OneToOne
	@JoinColumn(name = "fk_user")
	private Usuario usuario;

	public Utilizador() {
		super();
	}

	public Utilizador(Long id, String nome, TipoUtilizador tipo, Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo.getCod();
		this.usuario = usuario;
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

	public TipoUtilizador getTipo() {
		return TipoUtilizador.toEnum(tipo);
	}

	public void setTipo(TipoUtilizador tipo) {
		this.tipo = tipo.getCod();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Utilizador other = (Utilizador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
