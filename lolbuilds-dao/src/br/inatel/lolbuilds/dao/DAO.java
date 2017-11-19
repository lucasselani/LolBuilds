package br.inatel.lolbuilds.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	String driverClassName = "com.mysql.jdbc.Driver";
	String connectionUrl = "jdbc:mysql://db:3306/loldb?autoReconnect=true&useSSL=false";
	String dbUser = "admin";
	String dbPwd = "lolbuilds";

	private static DAO connectionFactory = null;

	private DAO() {
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
		return conn;
	}

	public static DAO getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new DAO();
		}
		return connectionFactory;
	}
}