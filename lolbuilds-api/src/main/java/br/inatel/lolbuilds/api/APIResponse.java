package br.inatel.lolbuilds.api;

import java.io.Serializable;

public class APIResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1487710676445258875L;

	private String status;
	private String message;
	
	public APIResponse(String status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
