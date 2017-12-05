package com.javacodegeeks.enterprise.rest.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataBaseConnection {

	public static final String CONTEXT_STRING = "java:/comp/env";
	public static final String DATASOURCE_STRING = "jdbc/sm_web_app";
	public static final String DATASOURCE_NAME = "java:comp/env/jdbc/sm_web_app";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/";
	public static final String DATABASE = "sm_web_app";
	public static final String USER = "root";
	public static final String PASS = "";
	public static final String SERVER = "localhost";
	private static DataBaseConnection instance = null;
	private static DataSource src;

	private DataBaseConnection() {
		try {
			Context ctx = new InitialContext();
			src = (DataSource) ctx.lookup(DATASOURCE_NAME);
		} catch (NamingException eN) {
			eN.printStackTrace();
		} catch (Exception eX) {
			eX.printStackTrace();
		}
	}

	public static DataBaseConnection getInstance() {
		if (DataBaseConnection.instance == null) {
			DataBaseConnection.instance = new DataBaseConnection();
		}
		return DataBaseConnection.instance;
	}

	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		if (instance == null) {
			instance = new DataBaseConnection();
		}
		try {
			if (src == null) {
				MysqlDataSource mysqlDataSource = new MysqlDataSource();
				mysqlDataSource.setUser(USER);
				mysqlDataSource.setPassword(PASS);
				mysqlDataSource.setServerName(SERVER);
				mysqlDataSource.setDatabaseName(DATABASE);
				src = mysqlDataSource;
				conn = DriverManager.getConnection(DB_URL + DATABASE, USER, PASS);
			} else {
				conn = src.getConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}

	public static DataSource getDataSource() {
		if (DataBaseConnection.instance == null) {
			DataBaseConnection.instance = new DataBaseConnection();
		}
		return src;
	}

	public static void close(ResultSet rs) {
		if (rs == null)
			return;
		try {
			rs.close();
		} catch (SQLException eSQL) {
		}
	}

	public static void close(Statement st) {
		if (st == null)
			return;
		try {
			st.close();
		} catch (SQLException eSQL) {
		}
	}

	public static void close(PreparedStatement ps) {
		if (ps == null)
			return;
		try {
			ps.close();
		} catch (SQLException eSQL) {
		}
	}

	public static void close(Connection conn) {
		if (conn == null)
			return;
		try {
			conn.close();
		} catch (SQLException eSQL) {
		}
	}

	public static void close(ResultSet rs, Statement st, Connection conn) {
		close(rs);
		close(st);
		close(conn);
	}

	public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		close(rs);
		close(ps);
		close(conn);
	}

	public static void close(ResultSet rs, Statement st, PreparedStatement ps, Connection conn) {
		close(rs);
		close(st);
		close(ps);
		close(conn);
	}
}