package br.com.max.projeto.ontologia.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
		String url="jdbc:postgresql://ec2-54-210-128-153.compute-1.amazonaws.com:5432/d94apk39ffi4bu";
		String user = "lyfzrnrvijunkj";
		String pass = "dea1f02d87aa2fcf119130ac3d8fc8831466ecfbcb78c8da9c56b211801f9357";
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
