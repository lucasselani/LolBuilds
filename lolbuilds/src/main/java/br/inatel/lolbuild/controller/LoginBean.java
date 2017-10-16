package br.inatel.lolbuild.controller;

import java.io.Serializable;
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
	private String email;
	private String password;
	
	public void requestLogin() {
		if(!validateLogin()) return;

		User user = new User();
		UserDAO dao = new UserDAO();
		user.setEmail(this.email);
		user.setPassword(this.password);
		boolean isValidUser = dao.login(user);
		if(!isValidUser) {
			System.out.println("Usuário ou senha inválidos!");
		} else {
			// MANDAR PARA TELA PRINCIPAL
		}
	}
	
	private boolean validateLogin() {
		if(this.email == null || (this.email != null && this.email.isEmpty())) {
			System.out.println("Preencha o campo e-mail!");
			return false;
		}
		if(this.password == null || (this.password != null && this.password.isEmpty())) {
			System.out.println("Preencha o campo senha!");
			return false;
		}
		return true;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}