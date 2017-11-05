package br.inatel.lolbuilds.api.model;

import java.util.ArrayList;
import br.inatel.lolbuilds.entity.Champion;
import br.inatel.lolbuilds.entity.Item;
import br.inatel.lolbuilds.entity.Spell;

public class BuildNode {
	private ArrayList<Item> items;
	private ArrayList<Spell> spells;
	private Champion champion;
	private String name;
	private int userId;
	
	public BuildNode() {}	
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public ArrayList<Spell> getSpells() {
		return spells;
	}

	public void setSpells(ArrayList<Spell> spells) {
		this.spells = spells;
	}

	public Champion getChampion() {
		return champion;
	}

	public void setChampion(Champion champion) {
		this.champion = champion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
