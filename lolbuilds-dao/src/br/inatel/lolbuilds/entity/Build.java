package br.inatel.lolbuilds.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the build database table.
 * 
 */
@Entity
@NamedQuery(name="Build.findAll", query="SELECT b FROM Build b")
public class Build implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="champion_id")
	private int championId;

	private String name;

	@Column(name="user_id")
	private int userId;

	public Build() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getChampionId() {
		return this.championId;
	}

	public void setChampionId(int championId) {
		this.championId = championId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}