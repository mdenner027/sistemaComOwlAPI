package br.com.max.projeto.ontologia.modelo;

public class Comorbidade implements Comparable<Comorbidade> {
	private String iri;
	private String label;

	@Override
	public int compareTo(Comorbidade o) {
		// TODO Auto-generated method stub
		return this.label.compareTo(o.label);
	}

	public String getIri() {
		return iri;
	}

	public void setIri(String iri) {
		this.iri = iri;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
