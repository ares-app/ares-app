package org.ares.app.common.security.spring.loginjump;

import static org.ares.app.common.conf.Params.LOGIN_JUMP_PATH;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.RedirectUrlBuilder;

public class UserLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	public UserLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	protected String buildHttpReturnUrlForRequest(HttpServletRequest request)throws IOException, ServletException {
		RedirectUrlBuilder urlBuilder = new RedirectUrlBuilder();
		urlBuilder.setScheme("http");
		urlBuilder.setServerName(request.getServerName());
		urlBuilder.setPort(request.getServerPort());
		urlBuilder.setContextPath(request.getContextPath());
		urlBuilder.setServletPath(request.getServletPath());
		urlBuilder.setPathInfo(request.getPathInfo());
		urlBuilder.setQuery(request.getQueryString());
		return urlBuilder.getUrl();
	}
	
	@Override
	public void commence(HttpServletRequest  request, HttpServletResponse response,AuthenticationException auth) throws IOException, ServletException {
		String returnUrl = buildHttpReturnUrlForRequest(request);
	    request.getSession().setAttribute(LOGIN_JUMP_PATH, returnUrl);
		super.commence( request, response, auth);
	}
	
}
