package br.com.max.projeto.ontologia.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.max.projeto.ontologia.modelo.Caso;
import br.com.max.projeto.ontologia.modelo.Comorbidade;
import br.com.max.projeto.ontologia.modelo.Transtorno;

public class CasoDAO {
	private Connection connection;

	public CasoDAO() {
		connection = new ConnectionFactory().getConnection();
	}

	public Caso getLabels(List<Transtorno> transtornos, List<Comorbidade> comorbidades) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			for (int i = 0; i < transtornos.size(); i++) {
				 stmt = connection
						.prepareStatement("select * from transtornos where iri_transtorno = ?");
				stmt.setString(1, transtornos.get(i).getIri());
				rs = stmt.executeQuery();
				while (rs.next()) {
					transtornos.get(i).setLabel(rs.getString("label_transtorno"));
				}
			}

			for (int i = 0; i < comorbidades.size(); i++) {
				stmt = connection
						.prepareStatement("select * from comorbidades where iri_comorbidade = ?");
				stmt.setString(1, comorbidades.get(i).getIri());
				rs = stmt.executeQuery();
				while (rs.next()) {
					comorbidades.get(i).setLabel(rs.getString("label_comorbidades"));
				}
				
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Caso caso = new Caso();
		caso.setComorbidades(comorbidades);
		caso.setTranstornos(transtornos);
		
		return caso;
	}
}
