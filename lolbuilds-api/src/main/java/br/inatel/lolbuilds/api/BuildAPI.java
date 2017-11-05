package br.inatel.lolbuilds.api;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.inatel.lolbuilds.dao.BuildDAO;
import br.inatel.lolbuilds.dao.ItemDAO;
import br.inatel.lolbuilds.entity.Build;
import br.inatel.lolbuilds.entity.Item;

@Path("/build")
public class BuildAPI {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Build> get(@QueryParam("id") Integer id, @QueryParam("name") String name) {
		try {
			BuildDAO dao = new BuildDAO();
			return dao.findBuildByName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public APIResponse post(Build newBuild) throws Exception {
		try {
			BuildDAO dao = new BuildDAO();
			dao.add(newBuild);
			return new APIResponse("OK","");
		} catch (Exception e) {
			e.printStackTrace();
			return new APIResponse("NOK",e.getMessage());
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public APIResponse delete(Build build) throws Exception {
		try {
			BuildDAO dao = new BuildDAO();
			dao.delete(build.getId());
			return new APIResponse("OK","");
		} catch (Exception e) {
			e.printStackTrace();
			return new APIResponse("NOK",e.getMessage());
		}
	}
}
