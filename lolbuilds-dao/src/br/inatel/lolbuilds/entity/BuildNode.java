package br.inatel.lolbuilds.entity;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import br.inatel.lolbuilds.entity.Champion;
import br.inatel.lolbuilds.entity.Item;
import br.inatel.lolbuilds.entity.Spell;

@XmlRootElement
public class BuildNode {
	private ArrayList<Item> items;
	private ArrayList<Spell> spells;
	private Champion champion;
	private String name;
	private String type;
	
	public BuildNode() {}	
	

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


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
}
