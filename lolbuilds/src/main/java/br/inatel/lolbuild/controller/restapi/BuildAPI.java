package br.inatel.lolbuild.controller.restapi;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.inatel.lolbuild.util.SessionContext;
import br.inatel.lolbuilds.api.model.APIResponse;
import br.inatel.lolbuilds.api.model.BuildNode;
import br.inatel.lolbuilds.dao.BuildDAO;
import br.inatel.lolbuilds.dao.BuildItemDAO;
import br.inatel.lolbuilds.dao.BuildSpellDAO;
import br.inatel.lolbuilds.dao.ChampionDAO;
import br.inatel.lolbuilds.dao.ItemDAO;
import br.inatel.lolbuilds.dao.SpellDAO;
import br.inatel.lolbuilds.entity.Build;
import br.inatel.lolbuilds.entity.BuildItem;
import br.inatel.lolbuilds.entity.BuildSpell;
import br.inatel.lolbuilds.entity.Champion;
import br.inatel.lolbuilds.entity.Item;
import br.inatel.lolbuilds.entity.Spell;

@Path("/build")
public class BuildAPI {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<BuildNode> get(@QueryParam("id") Integer id, @QueryParam("name") String name) {
		try {
			int userId = SessionContext.getInstance().getUserLogged().getId();

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
			int userId = SessionContext.getInstance().getUserLogged().getId();
			BuildDAO buildDao = new BuildDAO();
			BuildItemDAO buildItemDao = new BuildItemDAO();
			BuildSpellDAO buildSpellDao = new BuildSpellDAO();
			ChampionDAO championDao = new ChampionDAO();
			ItemDAO itemDao = new ItemDAO();
			SpellDAO spellDao = new SpellDAO();		
			
			Build build = new Build();
			build.setName(newBuild.getName());
			build.setUserId(userId);
			buildDao.add(build);
			
			int buildId = buildDao.findBuildIdByName(newBuild.getName());			
			Champion champion = newBuild.getChampion();
			champion.setBuildId(buildId);
			championDao.add(champion);
			
			for(Item item : newBuild.getItems()) {
				itemDao.add(item);
				
				BuildItem buildItem = new BuildItem();				
				buildItem.setBuildId(buildId);
				buildItem.setItemId(item.getId());
				buildItemDao.add(buildItem);
			}
			
			for(Spell spell : newBuild.getSpells()) {
				spellDao.add(spell);
				
				BuildSpell buildSpell = new BuildSpell();				
				buildSpell.setBuildId(buildId);
				buildSpell.setSpellId(spell.getId());
				buildSpellDao.add(buildSpell);
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
