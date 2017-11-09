package br.inatel.lolbuild.controller.restapi;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.inatel.lolbuilds.dao.UserDAO;
import br.inatel.lolbuilds.entity.User;

@Path("/user")
public class UserAPI {

	@GET
	@Path("/allusers")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getAllUsers() {
		UserDAO userDao = new UserDAO();
		return userDao.list();
	}
}
