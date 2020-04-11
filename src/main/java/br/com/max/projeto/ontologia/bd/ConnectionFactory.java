package br.com.max.projeto.ontologia.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/tcc", "postgres", "postgres");
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
