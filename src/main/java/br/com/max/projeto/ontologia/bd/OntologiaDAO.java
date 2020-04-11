package br.com.max.projeto.ontologia.bd;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OntologiaDAO {
	private Connection connection;

	public OntologiaDAO() {
		connection = new ConnectionFactory().getConnection();
	}

	public InputStream getOntologia() {
		InputStream in = null;
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from versoesontologia");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				//Blob blob = rs.getBlob(1);
				//in = blob.getBinaryStream();
				in = rs.getBinaryStream(1);
			}
			stmt.close();
			rs.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return in;
	}
}
