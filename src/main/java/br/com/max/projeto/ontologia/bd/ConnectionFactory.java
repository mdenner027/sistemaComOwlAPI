package br.com.max.projeto.ontologia.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
		String url = "ec2-34-193-232-231.compute-1.amazonaws.com";
		String user = "iqotyfuolprwdb";
		String pass = "f17de2e6db5a5f7cde274ca9f56fbd4a26ad615afdb07605863c61d18e408848";
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(url,user, pass);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}
}
