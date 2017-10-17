package br.inatel.lolbuild.util;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@ApplicationScoped
public class AppUrlStore implements Serializable {
	private static final long serialVersionUID = 1L;

	private String baseUrl = null;
	private String loginUrl = null;
	private String signupUrl = null;

	public String getBaseUrl() {return baseUrl; }
	public String getLoginUrl() { return loginUrl; }
	public String getSignupUrl() {return signupUrl; }

	@PostConstruct
    public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String baseUrl = externalContext.getInitParameter("BaseUrl");

		this.baseUrl = baseUrl;
		this.loginUrl = baseUrl + "login.xhtml";
		this.signupUrl = baseUrl + "signup.xhtml";
    }
}
