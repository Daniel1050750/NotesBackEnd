package com.javacodegeeks.enterprise.rest.notes;

import java.util.List;

import com.javacodegeeks.enterprise.rest.models.Notes;

public interface NoteDAO {
    public boolean createNote(Notes note);
    public boolean updateNote(int id, Notes note);
    public boolean deleteNote(int id);
	public List<Notes> getNote(String email);
}
