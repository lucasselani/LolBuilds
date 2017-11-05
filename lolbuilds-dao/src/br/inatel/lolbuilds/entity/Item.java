package br.inatel.lolbuilds.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;

	private String image;

	private String name;
	
	@ManyToMany(mappedBy = "item")
	private Collection<Build> build;
	
	public Item() {
	}

	public Collection<Build> getBuilds() {
		return build;
	}

	public void setBuilds(Collection<Build> builds) {
		this.build = builds;
	}
	
	public void setBuild(Build build) {
		if(this.build == null) {
			this.build = new ArrayList<Build>();
		}
		this.build.add(build);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

		public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}