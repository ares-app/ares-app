package org.ares.app.common.security.spring.dynamic.fism;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import org.ares.app.common.security.rbac.model.SRole;

/**
 * use cache
 * if resource or role relation change,then must use immediate query db
 * @author ly
 *
 */
public class SqlInvocationSecurityMetadataSourceImp implements FilterInvocationSecurityMetadataSource {

	@PostConstruct
	public void init(){
		m_roles_sec=new HashMap<String, ConfigAttribute>();
		m_url_ca = new HashMap<String, Collection<ConfigAttribute>>();
		
		ConfigAttribute c=null;
		Collection<ConfigAttribute> cs =null;
		
		Map<String, List<String>> m_url_roles=findResources();
		Set<String> set=m_url_roles.keySet();
		for(String uk:set){
			List<String> roles=m_url_roles.get(uk);
			for(String role:roles){
				if(!m_roles_sec.containsKey(role)){
					c = new SecurityConfig(role);
					m_roles_sec.put(role, c);
				}else
					c=m_roles_sec.get(role);
			}
			
			if(!m_url_ca.containsKey(uk)){
				cs = new ArrayList<ConfigAttribute>();
				m_url_ca.put(uk, cs);
			}
			cs=m_url_ca.get(uk);
			cs.add(c);
		}
	}
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	/**
	 * obj=> url
	 * return url=>roles
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object obj) throws IllegalArgumentException {
		Collection<ConfigAttribute> r=null;
		String url = ((FilterInvocation)obj).getRequestUrl();
		if(!usecache)
			init();
		Iterator<String> ite = m_url_ca.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (urlMatcher.match(resURL,url)) {
				if(r==null)
					r=new ArrayList<ConfigAttribute>();
				r.addAll(m_url_ca.get(resURL));
			}
		}
		return r;
	}

	/*
	 * index.do,ROLE_ADMIN,ROLE_USER
	 */
	protected Map<String, List<String>> findResources() {
		List<SRole> list= executeQuery();
		List<String> lRoles=null;
		Map<String, List<String>> resMap = new LinkedHashMap<String, List<String>>();
		for (SRole rm : list) {
			String url = rm.getResource();
			String role =rm.getRolename();
			if (resMap.containsKey(url)) 
				lRoles = resMap.get(url);
			 else{
				lRoles=new ArrayList<String>(); 
				resMap.put(url, lRoles);
			}
			lRoles.add(role);
		}
		return resMap;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<SRole> executeQuery(){
		return jt.query(resourceQuery, new RowMapper(){
			public Object mapRow(ResultSet r, int i) throws SQLException {
				SRole rm=new SRole();
				rm.setRolename(r.getString(ROLE_NAME));
				rm.setResource(r.getString(RESOURCE));
				return rm;
			}
		});
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	public void setResourceQuery(String resourceQuery) {
		this.resourceQuery = resourceQuery;
	}

	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}
	
	public void setUsecache(boolean usecache) {
		this.usecache = usecache;
	}

	static final String ROLE_ID="roleid";
	static final String ROLE_NAME="rolename";
	static final String RESOURCE="restr";
	
	PathMatcher urlMatcher = new AntPathMatcher();
	static Map<String, Collection<ConfigAttribute>> m_url_ca = null;
	static Map<String,ConfigAttribute> m_roles_sec=null;
	
	private String resourceQuery;
	private JdbcTemplate jt;
	private boolean usecache=true;// if !usecache then everytime query db
}
