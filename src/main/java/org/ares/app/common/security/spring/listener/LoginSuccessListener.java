package org.ares.app.common.security.spring.listener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import org.ares.app.common.security.rbac.custom.LazyUserFuncFactory;

public class LoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

	/**
	 * //UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 * refresh cache of users
	 */
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent ase) {
		WebAuthenticationDetails auth = (WebAuthenticationDetails)ase.getAuthentication().getDetails();
		UserDetails userDetails = (UserDetails) ase.getAuthentication().getPrincipal();
		String userid=userDetails.getUsername();
		uf.addUserToCacheFromDB(userid);
		log.debug(auth.getRemoteAddress());
		log.debug("[login user:]"+userid);
	}
	
	private static Logger log = Logger.getLogger(LoginSuccessListener.class);
	public void setUf(LazyUserFuncFactory uf) {
		this.uf = uf;
	}
	
	private LazyUserFuncFactory uf; 
	
}
