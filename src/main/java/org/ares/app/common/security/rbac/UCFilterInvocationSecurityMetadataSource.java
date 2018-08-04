package org.ares.app.common.security.rbac;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import org.ares.app.common.security.rbac.custom.UserFuncFactory;
import org.ares.app.common.security.rbac.model.SResource;
import org.ares.app.common.security.rbac.model.SRole;

public class UCFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	/*
	 *  Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		ConfigAttribute ca = new SecurityConfig("ROLE_ADMIN");
		atts.add(ca);
		resourceMap.put("/index.jsp", atts);
		resourceMap.put("/i.jap", atts);
	 */
	@PostConstruct
	public void init(){
		m_roles_ca = new HashMap<String, Collection<ConfigAttribute>>();
		m_roles_sec=new HashMap<String, ConfigAttribute>();
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		
		ConfigAttribute c=null;
		Collection<ConfigAttribute> cs =null;
		
		List<SRole> roles=uf.getAllRoles();
		for(SRole role:roles){
			List<SResource> res=role.getRes();
			String role_flag=role.getRolename();
			for(SResource r:res){
				if(!m_roles_sec.containsKey(role_flag)){
					c = new SecurityConfig(role_flag);
					m_roles_sec.put(role_flag, c);
				}else
					c=m_roles_sec.get(role_flag);
				String url_auth=r.getResurl();
				if(!resourceMap.containsKey(url_auth)){
					cs=new ArrayList<ConfigAttribute>();
					resourceMap.put(url_auth, cs);
				}else
					cs=resourceMap.get(url_auth);
				cs.add(c);
			}
		}
	}
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object obj)throws IllegalArgumentException {
		String url = ((FilterInvocation)obj).getRequestUrl();
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (urlMatcher.match(resURL,url)) {
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	public void setUf(UserFuncFactory uf) {
		this.uf = uf;
	}

	static PathMatcher urlMatcher = new AntPathMatcher();
	static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	static Map<String, Collection<ConfigAttribute>> m_roles_ca = null;
	static Map<String,ConfigAttribute> m_roles_sec=null;
	
	private UserFuncFactory uf;
	
}
