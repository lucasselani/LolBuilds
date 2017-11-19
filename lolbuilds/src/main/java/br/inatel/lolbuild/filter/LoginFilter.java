package br.inatel.lolbuild.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.inatel.lolbuilds.entity.User;

@WebFilter(filterName = "LoginFilter", urlPatterns = { "*.xhtml" })
public class LoginFilter implements Filter {
	private static final String LOGIN = "login";
	private static final String SIGNUP = "signup";
	private static final String BASE_URL = "http://buildonline.me/";
	private static final String RESOURCES = "javax.faces.resource";

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
	    HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;
	    
	    String loginUrl = req.getContextPath() + "/login.xhtml";
	    String homeUrl = req.getContextPath() + "/home.xhtml";
	        		
		HttpSession sess = req.getSession(false);
		String currentUrl = req.getRequestURL().toString();		
		String uri = req.getRequestURI();
		
		User user = null;
		if (sess != null) {
			user = (User) sess.getAttribute("userLogged");
		}		
	    
	    if (uri.contains(RESOURCES)){
	        chain.doFilter(request, response);
	    } else {
	    	if(user == null) {
	    		if(!(currentUrl.contains(LOGIN) || currentUrl.contains(SIGNUP) || currentUrl.equals(BASE_URL))) {
	    			res.sendRedirect(loginUrl);
	    		} else {
	    			chain.doFilter(request, response);
	    		}
	    	} else {
	    		if(currentUrl.contains(LOGIN) || currentUrl.contains(SIGNUP) || currentUrl.equals(BASE_URL)) {
	    			res.sendRedirect(homeUrl);
	    		} else {
	    			chain.doFilter(request, response);
	    		}
	    	}	    	
	    }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
