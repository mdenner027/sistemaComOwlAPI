package br.com.max.projeto.ontologia.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.max.projeto.ontologia.modelo.Sintoma;

public class SintomaDAO {
	private Connection connection;

	public SintomaDAO() {
		connection = new ConnectionFactory().getConnection();
	}

	public List<Sintoma> getSintomas() {
		List<Sintoma> sintomas = new ArrayList<Sintoma>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("select * from sintomas order by label_sintoma");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Sintoma sintoma = new Sintoma();
				sintoma.setIri(rs.getString("iri_sintoma"));
				sintoma.setLabel(rs.getString("label_sintoma"));
				sintomas.add(sintoma);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sintomas;
	}
}
