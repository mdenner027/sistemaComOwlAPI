package br.com.max.projeto.ontologia.modelo;

import java.util.List;

public class Caso {
	private List<Transtorno> transtornos;
	private List<Comorbidade> comorbidades;

	public List<Transtorno> getTranstornos() {
		return transtornos;
	}

	public void setTranstornos(List<Transtorno> transtornos) {
		this.transtornos = transtornos;
	}

	public List<Comorbidade> getComorbidades() {
		return comorbidades;
	}

	public void setComorbidades(List<Comorbidade> comorbidades) {
		this.comorbidades = comorbidades;
	}

}
