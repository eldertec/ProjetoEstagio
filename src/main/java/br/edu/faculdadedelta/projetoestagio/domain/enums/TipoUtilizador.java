package br.edu.faculdadedelta.projetoestagio.domain.enums;

public enum TipoUtilizador {

	COORDENADOR(1, "Coordenador"), REPRESENTANTE(2, "Representante"), FUNCIONARIO(3, "Funcionário");

	private int cod;
	private String descricao;

	private TipoUtilizador(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoUtilizador toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (TipoUtilizador x : TipoUtilizador.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
