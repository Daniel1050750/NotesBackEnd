package com.javacodegeeks.enterprise.rest.jersey;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.javacodegeeks.enterprise.rest.models.Notes;
import com.javacodegeeks.enterprise.rest.notes.NoteDAO;
import com.javacodegeeks.enterprise.rest.notes.NotesDAOJDBC;

@Path("/notes")
public class MainNotes {

	private NoteDAO noteDAO;

	public MainNotes() {
		noteDAO = new NotesDAOJDBC();
	}

	/* Create Note */
	@POST
	@Path("/note")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addNote(Notes note) {
		try {
			return Response.status(Response.Status.CREATED).entity(this.noteDAO.createNote(note)).build();
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	/* Update Note */
	@PUT
	@Path("/note/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateNote(@PathParam("id") int id, Notes note) {
		try {
			return Response.status(Response.Status.OK).entity(this.noteDAO.updateNote(id, note)).build();
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	/* Delete Note */
	@DELETE
	@Path("/notes/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteNote(@PathParam("id") int id) {
		try {
			return Response.status(Response.Status.OK).entity(this.noteDAO.deleteNote(id)).build();
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@GET
	@Path("/notes/{email}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Notes> getAllNotes(@PathParam("email") String email, Notes note) {
		try {
			this.noteDAO.getNote(email);
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return (List<Notes>) Response.status(Response.Status.OK).entity(note).build();
	}
}