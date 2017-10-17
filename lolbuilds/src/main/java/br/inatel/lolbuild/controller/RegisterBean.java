package br.inatel.lolbuild.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.inatel.lolbuilds.dao.UserDAO;
import br.inatel.lolbuilds.entity.User;

@ManagedBean
@ViewScoped
public class RegisterBean {
	private static final long serialVersionUID = 3973801993975443027L;

	private int id;
	private String username;
	private String password;
	private String repeatPassword;
	
	private boolean invalidRegister;
	private String errorMessage;
	
	public void requestRegister() {
		UserDAO dao = new UserDAO();

		if(!password.equals(repeatPassword)) {
			invalidRegister = true;
			errorMessage = "Senhas são diferentes!";
		}
		else if(dao.findUsername(this.username)) {
			invalidRegister = true;
			errorMessage = "Usuário já cadastrado!";
		} else {
			invalidRegister = false;
			User user = new User();
			user.setUsername(this.username);
			user.setPassword(this.password);			
			dao.register(user);
			FacesContext
			.getCurrentInstance()
			.getApplication()
			.getNavigationHandler()
			.handleNavigation(FacesContext.getCurrentInstance(), null, "login.xhtml");			
		}
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

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public boolean isInvalidRegister() {
		return invalidRegister;
	}

	public void setInvalidRegister(boolean invalidRegister) {
		this.invalidRegister = invalidRegister;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}	
	
}
