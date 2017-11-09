package br.inatel.lolbuild.controller.restapi;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import br.inatel.lolbuild.util.SessionContext;
import br.inatel.lolbuilds.dao.BuildDAO;
import br.inatel.lolbuilds.dao.BuildItemDAO;
import br.inatel.lolbuilds.dao.BuildSpellDAO;
import br.inatel.lolbuilds.dao.ChampionDAO;
import br.inatel.lolbuilds.dao.ItemDAO;
import br.inatel.lolbuilds.dao.SpellDAO;
import br.inatel.lolbuilds.entity.Build;
import br.inatel.lolbuilds.entity.BuildItem;
import br.inatel.lolbuilds.entity.BuildNode;
import br.inatel.lolbuilds.entity.BuildSpell;
import br.inatel.lolbuilds.entity.Champion;
import br.inatel.lolbuilds.entity.Item;
import br.inatel.lolbuilds.entity.Spell;
import br.inatel.lolbuilds.entity.User;

@Path("/build")
public class BuildAPI {
	private BuildDAO buildDao = new BuildDAO();			
	private BuildItemDAO buildItemDao = new BuildItemDAO();
	private BuildSpellDAO buildSpellDao = new BuildSpellDAO();
	private ChampionDAO championDao = new ChampionDAO();
	private ItemDAO itemDao = new ItemDAO();
	private SpellDAO spellDao = new SpellDAO();		
	
    @Context
    private HttpServletRequest request;
    
	@GET
	@Path("/ownbuild")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<BuildNode> getOwnBuilds() {
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("userLogged");
			int userId = user.getId();			
			ArrayList<Build> builds = buildDao.getBuildsByUserId(userId);
			return parseBuild(builds);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path("/champion")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<BuildNode> getBuildByChampion(@QueryParam("name") String name) {
		try {		
			ArrayList<Build> builds = buildDao.getBuildsByChampion(name);			
			return parseBuild(builds);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path("/type")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<BuildNode> getBuildByType(@QueryParam("name") String type) {
		try {		
			ArrayList<Build> builds = buildDao.getBuildsByType(type);			
			return parseBuild(builds);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@POST
	@Path("/newbuild")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public APIResponse post(BuildNode newBuild) throws Exception {
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("userLogged");
			int userId = user.getId();		
	
			Champion champion = newBuild.getChampion();
			championDao.add(champion);
			
			Build build = new Build();
			String buildName = buildDao.defineBuildName(newBuild.getName());
			build.setName(buildName);
			build.setUserId(userId);
			build.setType(newBuild.getType());
			build.setChampionId(championDao.findChampionIdByName(champion.getName()));
			int buildId = buildDao.add(build);
			
			for(Item item : newBuild.getItems()) {
				itemDao.add(item);				
				int itemId = itemDao.findItemIdByName(item.getName());
				BuildItem buildItem = new BuildItem();				
				buildItem.setBuildId(buildId);
				buildItem.setItemId(itemId);
				buildItemDao.add(buildItem);
			}
			
			for(Spell spell : newBuild.getSpells()) {
				spellDao.add(spell);	
				int spellId = spellDao.findSpellIdByName(spell.getName());
				BuildSpell buildSpell = new BuildSpell();				
				buildSpell.setBuildId(buildId);
				buildSpell.setSpellId(spellId);
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
	
	private ArrayList<BuildNode> parseBuild(ArrayList<Build> builds) {
		ArrayList<BuildNode> buildsNode = new ArrayList<BuildNode>();
		for(Build build : builds) {
			BuildNode buildNode = new BuildNode();
			buildNode.setChampion(championDao.list(build.getId()));			
			buildNode.setItems(buildItemDao.list(build.getId()));			
			buildNode.setSpells(buildSpellDao.list(build.getId()));
			buildNode.setName(build.getName());
			buildNode.setType(build.getType());
			buildsNode.add(buildNode);
		}
		return buildsNode;
	}
}
