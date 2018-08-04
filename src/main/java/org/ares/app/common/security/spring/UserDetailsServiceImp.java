package org.ares.app.common.security.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.ares.app.common.security.rbac.custom.LazyUserFuncFactory;
import org.ares.app.common.security.rbac.model.SUser;

public class UserDetailsServiceImp implements UserDetailsService {
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		SUser user = uf.getUserInCacheOrDB(name);//first in cache search,and in db
		if (user==null || user.getUserid() == null)
			throw new UsernameNotFoundException("The username " + name + " can not be found!");

		List<GrantedAuthority> resultAuths = new ArrayList<GrantedAuthority>();
		resultAuths.add(new SimpleGrantedAuthority("HODLE"));// AccessDecisionManager来决定
		return new User(name, user.getPassword(),user.isEnabled(), true, true, true, resultAuths);
	}

	public void setUf(LazyUserFuncFactory uf) {
		this.uf = uf;
	}
	
	private LazyUserFuncFactory uf; /*15-11-26 update UserFuncFactory => LazyUserFuncFactory */
}
