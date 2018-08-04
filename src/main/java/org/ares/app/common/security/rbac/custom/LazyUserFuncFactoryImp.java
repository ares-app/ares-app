package org.ares.app.common.security.rbac.custom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import org.ares.app.common.security.rbac.model.SResource;
import org.ares.app.common.security.rbac.model.SRole;
import org.ares.app.common.security.rbac.model.SUser;

/**
 * singleton
 * @author ly
 *
 */
public class LazyUserFuncFactoryImp implements LazyUserFuncFactory {
	
	@Override
	public void addUserToCacheFromDB(String userid) {
		refreshUserInCache(userid);
	}
	
	SRole getRoleInCache(SRole r){
		int ind=-1;
		if((ind=all_roles.indexOf(r))<0)
			all_roles.add(r);
		else
			r=all_roles.get(ind);
		return r;
	}
	
	SResource getResourceInCache(SResource rs){
		SResource r=rs;
		int ind=-1;
		if((ind=all_resources.indexOf(r))<0)
			all_resources.add(r);
		else{ 
			r=all_resources.get(ind);
			String url=rs.getResurl();
			if(url!=null && !url.equals(r.getResurl()))
				r.setResurl(url);
		}
		return r;
	}

	@Override
	public void removeUserInCache(String userid) {
		if(userid==null)
			return;
		for(SUser u:all_users)
			if (userid.equals(u.getUserid())){
				all_users.remove(u);
				break;
			}
	}

	@Override
	public SUser getUserInCacheOrDB(String userid) {
		SUser r=getUserInCache(userid);
		if(r==null){
			addUserToCacheFromDB(userid);
		}
		r=getUserInCache(userid);
		return r;
	}
	
	@Override
	public SUser getUserInCache(String userid) {
		SUser r=null;
		if(userid==null)
			return r;
		for(SUser u:all_users)
			if (userid.equals(u.getUserid())){
				r=u;
				break;
			}
		return r;
	}
	
	String getUserSQL(){
		String s_user=toSQL(t_user);
		String s_role=toSQL(t_role);
		String s_res=toSQL(t_res);
		String s_r_ur=toSQL(r_user_role);
		String s_r_rr=toSQL(r_role_res);
		
		StringBuilder t_sql=new StringBuilder("select * from ");
		t_sql.append("(").append(s_user).append("),");
		t_sql.append("(").append(s_role).append("),");
		t_sql.append("(").append(s_res).append(")");
		t_sql.append(" where (userid,roleid) in ").append("(").append(s_r_ur).append(") ");
		t_sql.append("   and (roleid,resid)  in ").append("(").append(s_r_rr).append(") ");
		t_sql.append(" and userid=?"); 
		return t_sql.toString();
	}

	@Override
	public SUser getUserInDB(String userid) {
		String sql=getUserSQL();
		
		final SUser user=new SUser();
		final List<SRole> roles=new ArrayList<SRole>();
		final List<SResource> ress=new ArrayList<SResource>();
		user.setRoles(roles);
		user.setRes(ress);
		
		jt.query(sql, new Object[] { userid },new RowCallbackHandler() {
			public void processRow(ResultSet r) throws SQLException {
				if(user.getUserid()==null){
					user.setUserid(r.getString("userid"));
					user.setPassword(r.getString("userpwd"));
					user.setUsername(r.getString("username"));
					user.setEnabled("1".equals(r.getString("status")));
				}
				SRole role=new SRole();
				role.setRoleid(r.getString("roleid"));
				role.setRolename(r.getString("rolename"));
				role=getRoleInCache(role);
				if(roles.indexOf(role)<0)
					roles.add(role);
				SResource res=new SResource();
				res.setResid(r.getString("resid"));
				res.setResname(r.getString("resname"));
				res.setResurl(r.getString("res"));
				res=getResourceInCache(res);
				if(ress.indexOf(res)<0)
					ress.add(res);
			}
		});
		
		if(user.getUserid()==null)
			return null;
		return user;
	}
	
	@Override
	public void refreshUserInCache(String userid) {
		removeUserInCache(userid);
		all_users.add(getUserInDB(userid));
	}

	@Override
	public List<SRole> getRolesOfUserInCache(String userid) {
		SUser u=getUserInCache(userid);
		return u.getRoles();
	}
	
	@Override
	public Map<String, Set<String>> getUrlAuthorities(String userid) {
		Map<String,Set<String>> urls=new HashMap<String,Set<String>>();
		SUser user=this.getUserInCache(userid);
		List<SRole> roles=user.getRoles();
		for(SRole r:roles){
			Set<String> s=new HashSet<String>();
			List<SResource> res=r.getRes();
			for(SResource f:res)
				s.add(f.getResurl());
			urls.put(r.getRoleid(), s);
		}
		return urls;
	}
	
	boolean isTable(String entity){
		return entity.toLowerCase().indexOf(" from ")<0;
	}
	
	/**
	 * 1-select * from s_user 2-s_user 3-s_user,a,b,c
	 */
	String toSQL(String entity){
		String r=entity;
		if(isTable(entity))
			if(entity.indexOf(",")<=0)
				r="select * from "+entity;
			else{
				final String[] rr=entity.split(",");
				r="select ";
				for(int i=1;i<rr.length;i++)
					r=r+rr[i]+",";
				r=r.substring(0, r.length()-1);
				r=r+" from "+rr[0];
			}
		return r;
	}

	/*-----------------------------bean get set method----------------------------------*/
	
	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}

	public void setT_user(String t_user) {
		this.t_user = t_user;
	}

	public void setT_role(String t_role) {
		this.t_role = t_role;
	}

	public void setT_res(String t_res) {
		this.t_res = t_res;
	}

	public void setR_user_role(String r_user_role) {
		this.r_user_role = r_user_role;
	}

	public void setR_role_res(String r_role_res) {
		this.r_role_res = r_role_res;
	}
	
	private final List<SUser> all_users=new ArrayList<SUser>();
	private final List<SRole> all_roles=new ArrayList<SRole>();
	private final List<SResource> all_resources=new ArrayList<SResource>();
	private JdbcTemplate jt;
	private String t_user;
	private String t_role;
	private String t_res;
	private String r_user_role;
	private String r_role_res;
	
}