package br.inatel.lolbuilds.entity;

import java.io.Serializable;


public class Champion implements Serializable {
	private static final long serialVersionUID = 1L;


	private int id;

	private String image;

	private String name;

	public Champion() {
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