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
		PreparedStatement stmt = null;
		ResultSet rs = null;
		for (int i = 0; i < t.size(); i++) {
			stmt = connection.prepareStatement("select * from tipospaciente where iri_tipo_paciente = ?");
			stmt.setString(1, t.get(i).getIri());
			rs = stmt.executeQuery();
			while (rs.next()) {
				Transtorno transtorno = new Transtorno();
				transtorno.setLabel(rs.getString("label_tipo_paciente"));
				transtornos.add(transtorno);
			}
		}
		rs.close();
		stmt.close();
		connection.close();
		Collections.sort(transtornos);
		return transtornos;
	}
}
