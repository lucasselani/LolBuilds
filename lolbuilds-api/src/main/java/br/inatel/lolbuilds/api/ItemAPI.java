package br.inatel.lolbuilds.api;

import java.util.ArrayList;
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
import br.inatel.lolbuilds.dao.ChampionDAO;
import br.inatel.lolbuilds.dao.ItemDAO;
import br.inatel.lolbuilds.entity.Build;
import br.inatel.lolbuilds.entity.Champion;
import br.inatel.lolbuilds.entity.Item;

@Path("/item")
public class ItemAPI {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Item> get(@QueryParam("id") Integer id, @QueryParam("name") String name) {
		try {
			ItemDAO dao = new ItemDAO();
			ArrayList<Item> items = new ArrayList<Item>();
			items.add(dao.findItemByName(name));
			return items;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public APIResponse post(Item newItem) throws Exception {
		try {
			ItemDAO dao = new ItemDAO();
			dao.add(newItem);
			return new APIResponse("OK","");
		} catch (Exception e) {
			e.printStackTrace();
			return new APIResponse("NOK",e.getMessage());
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public APIResponse delete(Item item) throws Exception {
		try {
			ItemDAO dao = new ItemDAO();
			dao.delete(item.getId());
			return new APIResponse("OK","");
		} catch (Exception e) {
			e.printStackTrace();
			return new APIResponse("NOK",e.getMessage());
		}
	}
}
