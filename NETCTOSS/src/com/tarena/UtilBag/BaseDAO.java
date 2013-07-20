package com.tarena.UtilBag;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BaseDAO {
	private static String user;
	private static String password;
	private static String url;
	private static String driver;
	private static ThreadLocal<Connection> th;

	static {
		Properties p = new Properties();
		th = new ThreadLocal<Connection>();
		try {
			p.load(BaseDAO.class.getClassLoader().getResourceAsStream(
					"com/tarena/UtilBag/cofg.properties"));
			user = p.getProperty("user");
			password = p.getProperty("pwd");
			url = p.getProperty("url");
			driver = p.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Connection openConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public static Connection getConnection() throws SQLException {
		Connection conn = th.get();
		if (conn == null) {
			conn = openConnection();
			conn.setAutoCommit(false);
			th.set(conn);
		}
		return conn;

	}
	
	public static void commit() throws SQLException {
		try {
			Connection conn = getConnection();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback() throws SQLException {
		try {
			Connection conn = getConnection();
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close() throws SQLException {
		Connection conn = getConnection();
		if (conn != null) {
			conn.close();
			th.set(null);
		}

	}
}
