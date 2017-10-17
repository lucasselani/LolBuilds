package br.inatel.lolbuild.controller;

import java.io.Serializable;

import br.inatel.lolbuild.util.AppUrlStore;
import br.inatel.lolbuilds.dao.UserDAO;
import br.inatel.lolbuilds.entity.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 3973801993975443027L;

	private int id;
	private String username;
	private String password;
	
	private boolean invalidLogin;
	private String errorMessage;
	
	public void requestLogin() {
		User user = new User();
		UserDAO dao = new UserDAO();
		user.setUsername(this.username);
		user.setPassword(this.password);
		boolean isValidUser = dao.login(user);
		
		if(!isValidUser) {
			this.invalidLogin = true;
			this.errorMessage = "Usuário ou senha inválidos!";
			this.username = "";
			this.password = "";
		} else {
			invalidLogin = false;
			System.out.println("Login realizado com sucesso!");
			FacesContext
				.getCurrentInstance()
				.getApplication()
				.getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), 
						null, "signup.xhtml");
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isInvalidLogin() {
		return invalidLogin;
	}

	public void setInvalidLogin(boolean invalidLogin) {
		this.invalidLogin = invalidLogin;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}