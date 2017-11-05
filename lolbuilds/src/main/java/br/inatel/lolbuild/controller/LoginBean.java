package br.inatel.lolbuild.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.inatel.lolbuild.util.SessionContext;
import br.inatel.lolbuild.util.Util;
import br.inatel.lolbuilds.dao.UserDAO;
import br.inatel.lolbuilds.entity.User;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 3973801993975443027L;

	private int id;
	private String username;
	private String password;
	
	public void requestLogin() {
		User user = new User();
		UserDAO dao = new UserDAO();
		
		String cryptoPassword = Util.convertStringToMd5(this.password);
		user.setUsername(this.username);
		user.setPassword(cryptoPassword);
		int userId = dao.login(user);
		
		if(userId == -1) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário e/ou senha incorreto(s).", null));
			this.username = "";
			this.password = "";
		} else {
			System.out.println("Login realizado com sucesso!");
			user.setId(userId);
			SessionContext.getInstance().setAttribute("userLogged", user);
			FacesContext
				.getCurrentInstance()
				.getApplication()
				.getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null, "/home.xhtml?faces-redirect=true");
		}
	}
    public User getUser() {
        return (User) SessionContext.getInstance().getUserLogged();
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
	
}