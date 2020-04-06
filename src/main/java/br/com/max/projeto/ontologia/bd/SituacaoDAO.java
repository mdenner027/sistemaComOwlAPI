package br.com.max.projeto.ontologia.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.max.projeto.ontologia.modelo.Situacao;

public class SituacaoDAO {
	private Connection connection;

	public SituacaoDAO() {
		connection = new ConnectionFactory().getConnection();
	}

	public List<Situacao> getSituacoes() {
		List<Situacao> situacoes = new ArrayList<Situacao>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("select * from situacoes order by label_situacao");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Situacao situacao = new Situacao();
				situacao.setIri(rs.getString("iri_situacao"));
				situacao.setLabel(rs.getString("label_situacao"));
				situacoes.add(situacao);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return situacoes;
	}
}
