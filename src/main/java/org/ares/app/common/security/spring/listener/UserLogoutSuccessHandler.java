package org.ares.app.common.security.spring.listener;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import org.ares.app.common.security.rbac.custom.LazyUserFuncFactory;

public class UserLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		if(authentication!=null){
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String userid=userDetails.getUsername();
			uf.removeUserInCache(userid);
		}
		String url=getDefaultTargetUrl();
		if(url==null)
			return;
		if(url.startsWith("/"))
			url=request.getContextPath()+url;
		response.sendRedirect(url);
	}

	public void setUf(LazyUserFuncFactory uf) {
		this.uf = uf;
	}

	private LazyUserFuncFactory uf;
}
