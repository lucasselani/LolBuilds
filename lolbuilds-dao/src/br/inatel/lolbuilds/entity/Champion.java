package br.inatel.lolbuilds.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the champion database table.
 * 
 */
@Entity
@NamedQuery(name="Champion.findAll", query="SELECT c FROM Champion c")
public class Champion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String image;

	private String name;

	@Column(name="build_id")
	private int buildId;


	public Champion() {
	}

	public int getBuildId() {
		return buildId;
	}

	public void setBuildId(int buildId) {
		this.buildId = buildId;
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