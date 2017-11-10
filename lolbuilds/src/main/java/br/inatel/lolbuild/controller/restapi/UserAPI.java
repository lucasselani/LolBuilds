package br.inatel.lolbuild.controller.restapi;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.inatel.lolbuild.util.Util;
import br.inatel.lolbuilds.dao.UserDAO;

@Path("/user")
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
}
