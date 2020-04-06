package br.com.max.projeto.ontologia.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.max.projeto.ontologia.modelo.Transtorno;

public class TranstornoDAO {
	private Connection connection;

	public TranstornoDAO() {
		connection = new ConnectionFactory().getConnection();
	}

	public List<Transtorno> getLabelsTranstorno(List<Transtorno> t) throws SQLException {
		List<Transtorno> transtornos = new ArrayList<Transtorno>();

		for (int i = 0; i < t.size(); i++) {
			PreparedStatement stmt = connection
					.prepareStatement("select * from tipospaciente where iri_tipo_paciente = ?");
			stmt.setString(1, t.get(i).getIri());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Transtorno transtorno = new Transtorno();
				transtorno.setLabel(rs.getString("label_tipo_paciente"));
				transtornos.add(transtorno);
			}
		}
		Collections.sort(transtornos);
		return transtornos;
	}
}
