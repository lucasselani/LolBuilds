package br.inatel.lolbuilds.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Spell.findAll", query="SELECT s FROM Spell s")
public class Spell implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;

	private String image;

	private String name;
	
	public Spell() {
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
