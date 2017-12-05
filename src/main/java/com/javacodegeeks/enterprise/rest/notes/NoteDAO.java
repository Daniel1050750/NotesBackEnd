package com.javacodegeeks.enterprise.rest.notes;

import com.javacodegeeks.enterprise.rest.models.Notes;

public interface NoteDAO {
    public boolean createNote(Notes note);
}
