package br.inatel.lolbuild.controller.restapi;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.inatel.lolbuild.util.Util;
import br.inatel.lolbuilds.dao.UserDAO;

@Path("/users")
public class UserAPI {

	@GET
	@Path("/allusers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers(@HeaderParam("authorization") String authorization) {
		if(authorization == null || !authorization.equals(Util.AUTHORIZATION)) {
			return Response.status(401).build();
		} else {
			UserDAO userDao = new UserDAO();
			return Response.status(200)
					.entity(userDao.list())
					.build();
		} 
	}
	
	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@QueryParam("username") String username, 
			@HeaderParam("authorization") String authorization) {
		if(authorization == null || !authorization.equals(Util.AUTHORIZATION)) {
			return Response.status(401).build();
		} else {
			UserDAO userDao = new UserDAO();
			return Response.status(200)
					.entity(userDao.getUserByUsername(username))
					.build();
		} 
	}
}
