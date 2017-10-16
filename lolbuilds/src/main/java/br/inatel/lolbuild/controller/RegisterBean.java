package br.inatel.lolbuild.controller;

import br.inatel.lolbuilds.dao.UserDAO;
import br.inatel.lolbuilds.entity.User;

public class RegisterBean {
	private static final long serialVersionUID = 3973801993975443027L;

	private int id;
	private String username;
	private String email;
	private String password;
	private String confirmPassword;
	
	public void requestRegister() {
		if(!validateRegister()) return;
		User user = new User();
		UserDAO dao = new UserDAO();
		user.setEmail(this.email);
		user.setPassword(this.password);
		if(!dao.findEmail(user.getEmail())) {
			dao.register(user);
		} else {
			System.out.println("E-mail já cadastrado!");
		}
	}
	
	private boolean validateRegister() {
		if(this.email == null || (this.email != null && this.email.isEmpty())) {
			System.out.println("Preencha o campo e-mail!");
			return false;
		}
		if(this.username == null || (this.username != null && this.username.isEmpty())) {
			System.out.println("Preencha o campo username!");
			return false;
		}
		if(this.password == null || (this.password != null && this.password.isEmpty())) {
			System.out.println("Preencha o campo senha!");
			return false;
		}
		if(!this.password.equals(confirmPassword)) {
			System.out.println("Senha e confirmação não são as mesmas!");
			return false;	
		}
		return true;
	}

}
