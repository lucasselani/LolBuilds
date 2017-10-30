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

@WebFilter(filterName = "LoginFilter", urlPatterns = { "*" })
public class LoginFilter implements Filter {
	private static final String LOGIN = "login";
	private static final String SIGNUP = "signup";
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		User user = null;
		HttpSession sess = ((HttpServletRequest) request).getSession(false);
		String url = ((HttpServletRequest) request).getRequestURL().toString();
		
		if (sess != null) {
			user = (User) sess.getAttribute("userLogged");
		}

		if (user == null) {
			if(url.contains(LOGIN) || url.contains(SIGNUP)) {
				chain.doFilter(request, response);
			} else {
				String contextPath = ((HttpServletRequest) request).getContextPath();
				((HttpServletResponse) response).sendRedirect(contextPath + "/login.xhtml");
			}
		} else {
			if(url.contains(LOGIN) || url.contains(SIGNUP)) {
				String contextPath = ((HttpServletRequest) request).getContextPath();
				((HttpServletResponse) response).sendRedirect(contextPath + "/home.xhtml");				
			} else {
				chain.doFilter(request, response);
			}
			
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
