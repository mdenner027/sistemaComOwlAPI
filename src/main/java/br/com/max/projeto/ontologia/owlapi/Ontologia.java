package br.com.max.projeto.ontologia.owlapi;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;

import br.com.max.projeto.ontologia.bd.OntologiaDAO;
import br.com.max.projeto.ontologia.modelo.Caso;
import br.com.max.projeto.ontologia.modelo.Comorbidade;
import br.com.max.projeto.ontologia.modelo.Transtorno;

public class Ontologia {
	private static final String prefix = "http://www.semanticweb.org/max/ontologies/2019/10/untitled-ontology-49#";

	public Caso inferirDados(String[] sintoma, String[] situacao, int tempo) throws OWLOntologyCreationException {
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		InputStream in = new OntologiaDAO().getOntologia();
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(in);
		OWLDataFactory df = ontology.getOWLOntologyManager().getOWLDataFactory();
		OWLObjectProperty hasSymptom = df.getOWLObjectProperty(prefix + "has_symptom");
		OWLObjectProperty hasSituation = df.getOWLObjectProperty(prefix + "has_situation");
		OWLNamedIndividual transtorno = df.getOWLNamedIndividual(prefix + "transtorno");
		for (int i = 0; i < sintoma.length; i++) {
			OWLNamedIndividual symptom = df.getOWLNamedIndividual(IRI.create(prefix + "sintoma" + i));
			OWLClass cls = df.getOWLClass(prefix + sintoma[i]);
			OWLClassAssertionAxiom ax = df.getOWLClassAssertionAxiom(cls, symptom);
			OWLObjectPropertyAssertionAxiom ax2 = df.getOWLObjectPropertyAssertionAxiom(hasSymptom, transtorno,
					symptom);
			manager.applyChanges(new AddAxiom(ontology, ax));
			manager.applyChanges(new AddAxiom(ontology, ax2));
		}

		for (int i = 0; i < situacao.length; i++) {
			OWLNamedIndividual situation = df.getOWLNamedIndividual(IRI.create(prefix + "situacao" + i));
			OWLClass cls = df.getOWLClass(prefix + situacao[i]);
			OWLClassAssertionAxiom ax = df.getOWLClassAssertionAxiom(cls, situation);
			OWLObjectPropertyAssertionAxiom dax = df.getOWLObjectPropertyAssertionAxiom(hasSituation, transtorno,
					situation);
			manager.applyChanges(new AddAxiom(ontology, ax));
			manager.applyChanges(new AddAxiom(ontology, dax));
		}

		OWLNamedIndividual paciente = df.getOWLNamedIndividual(prefix + "paciente");
		OWLDataProperty dp = df.getOWLDataProperty(prefix + "time_with_symptoms");
		OWLDataPropertyAssertionAxiom daz = df.getOWLDataPropertyAssertionAxiom(dp, paciente, tempo);
		manager.applyChanges(new AddAxiom(ontology, daz));

		OWLReasonerFactory rf = new ReasonerFactory();
		OWLReasoner r = rf.createReasoner(ontology);
		r.precomputeInferences(InferenceType.DATA_PROPERTY_ASSERTIONS, 
				InferenceType.OBJECT_PROPERTY_ASSERTIONS);
		// r.getTypes(df.getOWLNamedIndividual(prefix + "paciente"),
		// true).forEach(System.out::println);

		@SuppressWarnings("deprecation")
		Set<OWLClass> pacienteTranstornos = r.getTypes(df.getOWLNamedIndividual(prefix + "paciente"), true)
				.getFlattened();

		@SuppressWarnings("deprecation")
		Set<OWLClass> pacienteComorbidades = r.getTypes(df.getOWLNamedIndividual(prefix + "comorbidade"), false)
				.getFlattened();

		List<Transtorno> transtornos = new ArrayList<Transtorno>();
		List<Comorbidade> comorbidades = new ArrayList<Comorbidade>();

		for (OWLClass cls : pacienteTranstornos) {
			Transtorno t = new Transtorno();
			String aux = cls.getIRI().toString().replace(prefix, "");

			t.setIri(aux);

			transtornos.add(t);

		}
		for (OWLClass cls : pacienteComorbidades) {
			Comorbidade s = new Comorbidade();
			String aux = cls.getIRI().toString().replace(prefix, "");
			s.setIri(aux);
			comorbidades.add(s);
		}

		System.out.println(transtornos.get(0).getIri());
		System.out.println(comorbidades.get(0).getIri());

		Caso caso = new Caso();
		caso.setComorbidades(comorbidades);
		caso.setTranstornos(transtornos);
		return caso;

	}
}
