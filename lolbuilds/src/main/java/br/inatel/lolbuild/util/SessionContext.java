package br.inatel.lolbuild.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.inatel.lolbuilds.entity.User;

public class SessionContext {
	private static SessionContext instance;

	public static SessionContext getInstance() {
		if (instance == null) {
			instance = new SessionContext();
		}
		return instance;
	}

	private SessionContext() {}

	private ExternalContext currentExternalContext() {
		if (FacesContext.getCurrentInstance() == null) {
			throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
		} else {
			return FacesContext.getCurrentInstance().getExternalContext();
		}
	}

	public User getUserLogged() {
		return (User) getAttribute("userLogged");
	}

	public void setUserLogged(User user) {
		setAttribute("userLogged", user);
	}

	public void finishSession() {
		currentExternalContext().invalidateSession();
	}

	public Object getAttribute(String name) {
		return currentExternalContext().getSessionMap().get(name);
	}

	public void setAttribute(String name, Object value) {
		currentExternalContext().getSessionMap().put(name, value);
	}

}
