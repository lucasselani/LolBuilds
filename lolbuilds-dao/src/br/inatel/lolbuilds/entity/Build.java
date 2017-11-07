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

	private String name;
	
	private String type; 

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