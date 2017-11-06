package br.inatel.lolbuild.controller.restapi;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.inatel.lolbuild.util.SessionContext;
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
import br.inatel.lolbuilds.entity.BuildNode;

@Path("/build")
public class BuildAPI {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") Integer id) {
		try {
			int userId = 0;
			ArrayList<BuildNode> buildsNode = new ArrayList<BuildNode>();
			
			BuildDAO buildDao = new BuildDAO();			
			BuildItemDAO buildItemDao = new BuildItemDAO();
			BuildSpellDAO buildSpellDao = new BuildSpellDAO();
			ChampionDAO championDao = new ChampionDAO();
			
			ArrayList<Build> builds = buildDao.list(userId);
			for(Build build : builds) {
				BuildNode buildNode = new BuildNode();
				buildNode.setChampion(championDao.list(build.getId()));
				buildNode.setItems(buildItemDao.list(build.getId()));
				buildNode.setSpells(buildSpellDao.list(build.getId()));
				buildNode.setName(build.getName());
				buildsNode.add(buildNode);
			}
			String arg = "Deu ruim mas deu baum";
			return Response.status(200).entity(arg).build();
			//return Response.status(200).entity(buildsNode).build();
		} catch (Exception e) {
			e.printStackTrace();
			String arg = "Deu ruim mas deu baum";
			return Response.status(200).entity(arg).build();
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
			build.setType(newBuild.getType());
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
