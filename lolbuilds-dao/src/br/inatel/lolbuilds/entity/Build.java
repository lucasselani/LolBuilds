package br.inatel.lolbuilds.entity;

import java.io.Serializable;
import java.util.Collection;

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

	@Column(name="user_id")
	private int userId;
	
	@JoinTable(name="build_has_item")
	@ManyToMany
	private Collection<Item> item;

	public Collection<Item> getItems() {
		return item;
	}

	public void setItems(Collection<Item> items) {
		this.item = items;
	}

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

}