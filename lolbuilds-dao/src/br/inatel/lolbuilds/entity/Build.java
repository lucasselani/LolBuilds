package br.inatel.lolbuilds.entity;

import java.io.Serializable;


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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
		

}