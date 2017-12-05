package com.javacodegeeks.enterprise.rest.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import com.javacodegeeks.enterprise.rest.database.DataBaseConnection;
import com.javacodegeeks.enterprise.rest.database.Database;
import com.javacodegeeks.enterprise.rest.models.Users;

public class UsersDAOJDBC implements UserDAO {

	private static final String QUERY_INSERT_USER = "INSERT INTO " + Database.USER.getTableName()
			+ " (fname, lname, username, password, email, img) VALUES (?,?,?,?,?,?);";

	public boolean createUser(Users user) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DataBaseConnection.getConnection();
			ps = conn.prepareStatement(QUERY_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getFname());
			ps.setString(2, user.getLname());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getImg());
			ps.execute();
			return true;
		} catch (SQLException eSQL) {
			eSQL.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.close(conn);
		}
		return false;
	}
}