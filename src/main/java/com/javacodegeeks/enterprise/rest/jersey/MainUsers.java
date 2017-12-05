package com.javacodegeeks.enterprise.rest.jersey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.javacodegeeks.enterprise.rest.database.DataBaseConnection;
import com.javacodegeeks.enterprise.rest.database.Database;
import com.javacodegeeks.enterprise.rest.models.Credentials;
import com.javacodegeeks.enterprise.rest.models.Notes;
import com.javacodegeeks.enterprise.rest.models.Users;

@Path("/users")
public class MainUsers {

	// private NoteDAO noteDAO;
	// private UserDAO userDAO;

	/* Create User */
	@POST
	@Path("/user")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addUser(Users user) throws JSONException {
		try {			
			// this.userDAO.createUser(user);
			final String QUERY_INSERT_USER = "INSERT INTO " + Database.USER.getTableName()
					+ " (fname, lname, username, password, email, img) VALUES (?,?,?,?,?,?);";
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
			} catch (SQLException eSQL) {
				eSQL.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DataBaseConnection.close(conn);
			}
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
		return Response.status(Response.Status.CREATED).entity(user).build();
	}


	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response authenticateUser(Credentials credentials) {

		// String username = credentials.getUsername();
		// String password = credentials.getPassword();

		return Response.status(Response.Status.CREATED).entity(credentials).build();
	}

	/*@GET
	@Path("/usernotes/{email}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Notes> getAllNotes(@PathParam("email") String email, Notes note) {
		try {
			// this.noteDAO.createNote(note);
			final String QUERY_GETALL_NOTE = "SELECT * FROM " + Database.NOTE.getTableName() + " WHERE share like " + email;
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = DataBaseConnection.getConnection();
				ps = conn.prepareStatement(QUERY_GETALL_NOTE, Statement.RETURN_GENERATED_KEYS);
				ps.execute();
			} catch (SQLException eSQL) {
				eSQL.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DataBaseConnection.close(conn);
			}
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return Response.status(Response.Status.OK).entity(note).build();
	}

	/*
	 * @GET
	 * 
	 * @Path("/{parameter}") public Response responseMsg(@PathParam("parameter")
	 * String parameter,
	 * 
	 * @DefaultValue("Nothing to say") @QueryParam("value") String value) {
	 * 
	 * String output = "Hello from: " + parameter + " : " + value;
	 * 
	 * return Response.status(200).entity("joaquim").build(); }
	 * 
	 * 
	 * /*
	 * 
	 * @POST
	 * 
	 * @Path("/users")
	 * 
	 * @Consumes("text/plain") public String postClichedMessage(String message) {
	 * return "Olá Daniel"; }
	 *
	 * 
	 * Delete Note
	 * 
	 * @DELETE
	 * 
	 * @Path("/user/{id}/notes")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response deleteNote() throws
	 * Exception { return null; }
	 * 
	 * /* Get All Notes
	 * 
	 * @GET
	 * 
	 * @Path("/notes/")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response
	 * findUserById(@PathParam("username") String username, @PathParam("password")
	 * String password) { return Response.status(200).entity("teste").build();
	 * //return null; }
	 * 
	 * @GET
	 * 
	 * @Path("/user/{username}/{password}")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response findUserById() { return
	 * Response.status(200).entity("teste").build(); //return null; }
	 * 
	 * @PUT
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response editNote() throws
	 * Exception { return null; }
	 */
}