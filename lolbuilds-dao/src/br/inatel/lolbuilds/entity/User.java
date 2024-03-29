package br.inatel.lolbuilds.entity;

import java.io.Serializable;


public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String password;
	
	private String username;

	public User() {
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}