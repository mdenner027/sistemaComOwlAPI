package br.com.max.projeto.ontologia.modelo;


public class Transtorno implements Comparable<Transtorno>{
	private String iri;
	private String label;

	@Override
	public int compareTo(Transtorno o1) {
		// TODO Auto-generated method stub
		return this.label.compareTo(o1.label);
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
