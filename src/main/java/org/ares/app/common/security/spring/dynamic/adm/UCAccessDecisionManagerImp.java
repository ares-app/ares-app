package org.ares.app.common.security.spring.dynamic.adm;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class UCAccessDecisionManagerImp implements AccessDecisionManager {

	@Override
	public void decide(Authentication auth, Object obj,Collection<ConfigAttribute> col) throws AccessDeniedException,InsufficientAuthenticationException {
		if( col == null ) 
			return ;
		/*FilterInvocation fi=(FilterInvocation)obj;System.out.println(fi.getRequestUrl());*/
		
		Iterator<ConfigAttribute> ite = col.iterator();
		while( ite.hasNext()){
			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig)ca).getAttribute();
			//ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
			for( GrantedAuthority ga: auth.getAuthorities())
				if(needRole.trim().equals(ga.getAuthority().trim()))
					return;
		}
		throw new AccessDeniedException("access deny!");
	}

	@Override
	public boolean supports(ConfigAttribute ca) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}