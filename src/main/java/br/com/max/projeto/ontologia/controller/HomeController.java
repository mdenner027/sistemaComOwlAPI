package br.com.max.projeto.ontologia.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.max.projeto.ontologia.bd.ComorbidadeDAO;
import br.com.max.projeto.ontologia.bd.SintomaDAO;
import br.com.max.projeto.ontologia.bd.SituacaoDAO;
import br.com.max.projeto.ontologia.bd.TranstornoDAO;
import br.com.max.projeto.ontologia.modelo.Caso;
import br.com.max.projeto.ontologia.modelo.Comorbidade;
import br.com.max.projeto.ontologia.modelo.Transtorno;
import br.com.max.projeto.ontologia.owlapi.Ontologia;

@Controller
public class HomeController {
	@RequestMapping(value = "/")
	public String home(Model model) {
		model.addAttribute("sintomas", new SintomaDAO().getSintomas());
		model.addAttribute("situacoes", new SituacaoDAO().getSituacoes());
		return "dashboard";
	}

	@RequestMapping(value = "inicio")
	public String inicio() {
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "analisar", method = RequestMethod.POST)
	public String analisar(String[] sintomas, String[] situacoes, int tempo, Model model) {
		System.out.println(Arrays.toString(sintomas));
		System.out.println(Arrays.toString(situacoes));
		Caso caso = new Caso();
		List<Transtorno> transtornos = new ArrayList<Transtorno>();
		List<Comorbidade> comorbidadades = new ArrayList<Comorbidade>();
		try {
			caso = new Ontologia().inferirDados(sintomas, situacoes, tempo);
			transtornos = new TranstornoDAO().getLabelsTranstorno(caso.getTranstornos());
			comorbidadades = new ComorbidadeDAO().getLabelsComorbidade(caso.getComorbidades());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OWLOntologyCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(caso.getTranstornos().get(0).getLabel());
		model.addAttribute("transtornos", transtornos);
		model.addAttribute("comorbidades", comorbidadades);
		return "results";
	}
}
