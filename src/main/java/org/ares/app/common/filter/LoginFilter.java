package org.ares.app.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ares.app.common.conf.Params;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse)res;
		String accessurl=request.getRequestURI();
		Object login=request.getSession().getAttribute(Params.SESSION_LOGIN_USER);
		if(login!=null||accessurl.endsWith(LOGIN_JSP)||accessurl.endsWith(LOGIN_ACTION))
			chain.doFilter(req, res);
		else
			response.sendRedirect(request.getContextPath()+LOGIN_JSP);
		
	/*	HttpServletRequest request = (HttpServletRequest) req;
		System.out.println("***********"+request.getRequestURI());
		chain.doFilter(req, res);*/
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}
	
	private static final String  LOGIN_JSP="/login.jsp";
	private static final String  LOGIN_ACTION="/login.do";

}
