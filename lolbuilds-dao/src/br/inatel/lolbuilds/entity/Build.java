package br.inatel.lolbuilds.entity;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the build database table.
 * 
 */
public class Build implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String name;
	
	private String type; 

	private int userId;
	
	private int championId;
	
	private Timestamp datetime;
	
	public Build() {
	}	

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public int getChampionId() {
		return this.championId;
	}

	public void setChampionId(int championId) {
		this.championId = championId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getDatetime() {
		return datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}
		

}