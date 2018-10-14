package br.edu.faculdadedelta.projetoestagio.domain.enums;

public enum StatusPresenca {

	PRESENTE(1, "Presente"), AUSENTE(2, "Ausente");

	private int cod;
	private String descricao;

	private StatusPresenca(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusPresenca toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (StatusPresenca x : StatusPresenca.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
