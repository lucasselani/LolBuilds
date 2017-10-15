package br.inatel.lolbuilds.model;

import java.io.Serializable;
import br.com.projetoac.dao.UserDAO;
import br.inatel.lolbuilds.entity.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 3973801993975443027L;

	private int id;
	private String email;
	private String password;
	
	public void insereBanco() {
		User user = new User();
		UserDAO dao = new UserDAO();
		user.setEmail(this.email);
		user.setPassword(this.password);
		dao.add(user);
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