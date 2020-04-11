package br.com.max.projeto.ontologia.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.max.projeto.ontologia.modelo.Comorbidade;

public class ComorbidadeDAO {
	private Connection connection;

	public ComorbidadeDAO() {
		connection = new ConnectionFactory().getConnection();
	}

	public List<Comorbidade> getLabelsComorbidade(List<Comorbidade> c) {
		List<Comorbidade> comorbidades = new ArrayList<Comorbidade>();
		try {
			PreparedStatement stmt = null;
			ResultSet rs = null;
			for (int i = 0; i < c.size(); i++) {
				stmt = connection.prepareStatement("select * from comorbidades where iri_comorbidade = ?");
				stmt.setString(1, c.get(i).getIri());
				rs = stmt.executeQuery();
				while (rs.next()) {
					Comorbidade comorbidade = new Comorbidade();
					comorbidade.setLabel(rs.getString("label_comorbidade"));
					comorbidades.add(comorbidade);
				}
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (comorbidades.size() > 1) {
			for (int i = 0; i < comorbidades.size(); i++) {
				if (comorbidades.get(i).getLabel().equalsIgnoreCase("Sem comorbidades associadas")) {
					comorbidades.remove(i);
				}
			}
		}
		Collections.sort(comorbidades);
		return comorbidades;
	}
}
