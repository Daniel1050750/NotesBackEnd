package com.javacodegeeks.enterprise.rest.notes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import com.javacodegeeks.enterprise.rest.database.DataBaseConnection;
import com.javacodegeeks.enterprise.rest.database.Database;
import com.javacodegeeks.enterprise.rest.models.Notes;

public class NotesDAOJDBC implements NoteDAO {

	private static final String QUERY_INSERT_NOTE = "INSERT INTO " + Database.NOTE.getTableName()
			+ " (title, content, level, date, share) VALUES (?,?,?,NOW(),?);";

	public boolean createNote(Notes notes) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DataBaseConnection.getConnection();
			ps = conn.prepareStatement(QUERY_INSERT_NOTE, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, notes.getTitle());
			ps.setString(2, notes.getContent());
			ps.setInt(3, notes.getLevel());
			ps.setString(5, notes.getShare());
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
