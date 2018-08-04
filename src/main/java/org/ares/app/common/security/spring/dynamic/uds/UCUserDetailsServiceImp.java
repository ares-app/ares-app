package org.ares.app.common.security.spring.dynamic.uds;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.ares.app.common.security.rbac.custom.LazyUserFuncFactory;
import org.ares.app.common.security.rbac.model.SRole;
import org.ares.app.common.security.rbac.model.SUser;

public class UCUserDetailsServiceImp implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		SUser user = uf.getUserInCache(name); // 15-11-26 update UserFuncFactory => LazyUserFuncFactory not test
		if (user.getUserid() == null)
			throw new UsernameNotFoundException("The user name " + name + " can not be found!");
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		for(SRole r:user.getRoles()){
			String auth=r.getRolename();
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(auth);
			auths.add(authority);
		}
		return new User(name, user.getPassword().toLowerCase(),user.isEnabled(), true, true, true, auths);
	}

	public void setUf(LazyUserFuncFactory uf) {
		this.uf = uf;
	}
	
	private LazyUserFuncFactory uf; /*15-11-26 update UserFuncFactory => LazyUserFuncFactory not test*/
	
}
