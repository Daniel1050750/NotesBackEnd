package com.javacodegeeks.enterprise.rest.notes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.javacodegeeks.enterprise.rest.database.DataBaseConnection;
import com.javacodegeeks.enterprise.rest.database.Database;
import com.javacodegeeks.enterprise.rest.models.Notes;

public class NotesDAOJDBC implements NoteDAO {
	
	Connection conn;
	PreparedStatement ps;
	private final String QUERY_INSERT_NOTE = "INSERT INTO " + Database.NOTE.getTableName() + " (title, content, level, share) VALUES (?,?,?,?);";
	private final String QUERY_UPDATE_NOTE = "UPDATE " + Database.NOTE.getTableName() + " SET title = ? ," + " content = ? ," + " level = ? ," + " share = ? " + " WHERE id = ? ";
	private final String QUERY_DELETE_NOTE = "DELETE FROM " + Database.NOTE.getTableName() + " WHERE id = ? ";
	private final String QUERY_GETALL_NOTE = "SELECT * FROM " + Database.NOTE.getTableName() + " WHERE share like ";
	
	public boolean createNote(Notes notes) {
		conn = null;
		ps = null;
		try {
			conn = DataBaseConnection.getConnection();
			ps = conn.prepareStatement(QUERY_INSERT_NOTE, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, notes.getTitle());
			ps.setString(2, notes.getContent());
			ps.setInt(3, notes.getLevel());
			ps.setString(4, notes.getShare());
			ps.execute();
		} catch (SQLException eSQL) {
			eSQL.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DataBaseConnection.close(conn);
		}
		return true;
	}
	
	public boolean updateNote(int id, Notes notes) {
		conn = null;
		ps = null;
		try {
			conn = DataBaseConnection.getConnection();
			ps = conn.prepareStatement(QUERY_UPDATE_NOTE, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, notes.getTitle());
			ps.setString(2, notes.getContent());
			ps.setInt(3, notes.getLevel());
			ps.setString(4, notes.getShare());
			ps.setInt(5, id);
			ps.executeUpdate();
		} catch (SQLException eSQL) {
			eSQL.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DataBaseConnection.close(conn);
		}
		return true;
	}
	
	public boolean deleteNote(int id) {
		conn = null;
		ps = null;
		try {
			conn = DataBaseConnection.getConnection();
			ps = conn.prepareStatement(QUERY_DELETE_NOTE, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException eSQL) {
			eSQL.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DataBaseConnection.close(conn);
		}
		return true;
	}

	public List<Notes> getNote(String id) {
		conn = null;
		ps = null;
		try {
			conn = DataBaseConnection.getConnection();
			ps = conn.prepareStatement(QUERY_GETALL_NOTE, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException eSQL) {
			eSQL.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DataBaseConnection.close(conn);
		}
		return true;
	}
}
