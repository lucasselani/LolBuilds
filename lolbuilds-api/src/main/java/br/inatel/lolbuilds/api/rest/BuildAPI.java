package br.inatel.lolbuilds.api.rest;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.inatel.lolbuilds.api.model.APIResponse;
import br.inatel.lolbuilds.api.model.BuildNode;
import br.inatel.lolbuilds.dao.BuildDAO;
import br.inatel.lolbuilds.dao.ChampionDAO;
import br.inatel.lolbuilds.dao.ItemDAO;
import br.inatel.lolbuilds.dao.SpellDAO;
import br.inatel.lolbuilds.entity.Build;
import br.inatel.lolbuilds.entity.Champion;
import br.inatel.lolbuilds.entity.Item;
import br.inatel.lolbuilds.entity.Spell;

@Path("/build")
public class BuildAPI {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Build> get(@QueryParam("id") Integer id, @QueryParam("name") String name) {
		try {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public APIResponse post(BuildNode newBuild) throws Exception {
		try {
			BuildDAO buildDao = new BuildDAO();
			ItemDAO itemDao = new ItemDAO();
			SpellDAO spellDao = new SpellDAO();
			ChampionDAO championDao = new ChampionDAO();			
			
			Build build = new Build();
			build.setName(newBuild.getName());
			build.setUserId(newBuild.getUserId());
			build.setItems(newBuild.getItems());
			build.setSpells(newBuild.getSpells());
			buildDao.add(build);
			
			int buildId = buildDao.findBuildIdByName(newBuild.getName());			
			Champion champion = newBuild.getChampion();
			champion.setBuildId(buildId);
			championDao.add(champion);
			
			for(Item item : newBuild.getItems()) {
				item.setBuild(build);
				itemDao.add(item);
			}
			
			for(Spell spell : newBuild.getSpells()) {
				spell.setBuild(build);
				spellDao.add(spell);
			}
			
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
