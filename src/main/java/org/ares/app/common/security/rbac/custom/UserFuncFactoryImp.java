package org.ares.app.common.security.rbac.custom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import org.ares.app.common.security.rbac.model.SResource;
import org.ares.app.common.security.rbac.model.SRole;
import org.ares.app.common.security.rbac.model.SUser;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserFuncFactoryImp implements UserFuncFactory {

	@PostConstruct
	void init(){
		ALL_USER.clear();
		ALL_ROLE.clear();
		ALL_RES.clear();
		
		loadUser();
		loadRole();
		loadRes();
		
		load_r_user_role();
		load_r_role_res();
		load_r_user_res();
	}
	
	void loadUser(){
		String sql=toSQL(t_user);
		List list=jt.query(sql, new RowMapper(){
			public Object mapRow(ResultSet r, int i) throws SQLException {
				SUser model=new SUser();
				model.setUserid(r.getString("userid"));
				model.setPassword(r.getString("userpwd"));
				model.setUsername(r.getString("username"));
				model.setEnabled("1".equals(r.getString("status")));
				return model;
			}
		});
		ALL_USER.addAll(list);
	}
	
	/**
	 * 强制刷新用户,在缺失权限情况下
	 * @param userid
	 * @param force
	 * @return
	 */
	SUser loadUser(String userid,boolean force){
		String sql=toSQL(t_user);
		sql=sql+" where userid=?";
		final SUser t_user=new SUser();
		SUser ret_user=t_user;
		jt.query(sql, new Object[] { userid },new RowCallbackHandler() {
			public void processRow(ResultSet r) throws SQLException {
				t_user.setUserid(r.getString("userid"));
				t_user.setPassword(r.getString("userpwd"));
				t_user.setUsername(r.getString("username"));
				t_user.setEnabled("1".equals(r.getString("status")));
			}
		});
		if(t_user.getUserid()==null)
			return null;
		if(force||!findUserInCache(t_user)){
			addOrUpdateUserCache(t_user);
			ret_user=getUserInCache(t_user);
			load_r_user_role(userid);
			for(SRole r:t_user.getRoles())
				load_r_role_res(r.getRoleid());
			load_r_user_res(userid);
		}
		return ret_user;
	}
	
	boolean addOrUpdateUserCache(SUser user){
		if(user==null||user.getUserid()==null)
			return false;
		boolean find=false;
		for(SUser u:ALL_USER){
			if(u.getUserid().equals(user.getUserid())){
				u.setPassword(user.getPassword());
				find=true;
				break;
			}
		}
		if(!find)
			ALL_USER.add(user);
		return !find;
	}
	
	SUser loadUser(String userid){
		return loadUser(userid,false);
	}
	
	boolean findUserInCache(SUser f){
		for(SUser u:ALL_USER)
			if(u.getUserid().equals(f.getUserid()))
				return true;
		return false;
	}
	
	SUser getUserInCache(SUser f){
		for(SUser u:ALL_USER)
			if(u.getUserid().equals(f.getUserid()))
				return u;
		return null;
	}
	
	boolean findRoleInCache(SRole f){
		for(SRole u:ALL_ROLE)
			if(u==f)
				return true;
		return false;
	}
	
	void loadRole(){
		String sql=toSQL(t_role);
		List list=jt.query(sql, new RowMapper(){
			public Object mapRow(ResultSet r, int i) throws SQLException {
				SRole model=new SRole();
				model.setRoleid(r.getString("roleid"));
				model.setRolename(r.getString("rolename"));
				return model;
			}
		});
		ALL_ROLE.addAll(list);
	}
	
	void loadRes(){
		String sql=toSQL(t_res);
		List list=jt.query(sql, new RowMapper(){
			public Object mapRow(ResultSet r, int i) throws SQLException {
				SResource model=new SResource();
				model.setResid(r.getString("resid"));
				model.setResname(r.getString("resname"));
				model.setResurl(r.getString("res"));
				return model;
			}
		});
		ALL_RES.addAll(list);
	}
	
	void load_r_user_role(){
		//0:r_user_role,1:userid,2:roleid
		/*final String[] r_ur=r_user_role.split(",");
				sql="select "+r_ur[1]+","+r_ur[2]+" from "+r_ur[0];*/
		String sql=toSQL(r_user_role);
		jt.query(sql, new RowMapper(){
			public Object mapRow(ResultSet r, int i) throws SQLException {
				String userid=r.getString("userid");//String userid=r.getString(r_ur[1]);
				String roleid=r.getString("roleid");//String roleid=r.getString(r_ur[2]);
				build_user_r_role(userid,roleid);
				return null;
			}
		});
	}
	
	void load_r_user_role(String userid){
		String sql=toSQL(r_user_role);
		sql=sql+" where userid=?";
		jt.query(sql,new Object[]{userid}, new RowMapper(){
			public Object mapRow(ResultSet r, int i) throws SQLException {
				String userid=r.getString("userid");//String userid=r.getString(r_ur[1]);
				String roleid=r.getString("roleid");//String roleid=r.getString(r_ur[2]);
				build_user_r_role(userid,roleid);
				return null;
			}
		});
	}
	
	void load_r_role_res(){
		//0:r_user_role,1:roleid,2:resid
		/*final String[] r_rr=r_role_res.split(",");
				sql="select "+r_rr[1]+","+r_rr[2]+" from "+r_rr[0];*/
		String sql=toSQL(r_role_res);
		jt.query(sql, new RowMapper(){
			public Object mapRow(ResultSet r, int i) throws SQLException {
				String resid=r.getString("resid");
				String roleid=r.getString("roleid");
				build_role_r_res(roleid,resid);
				return null;
			}
		});
	}
	
	void load_r_role_res(String roleid){
		String sql=toSQL(r_role_res);
		sql=sql+ " where roleid=?";
		jt.query(sql,new Object[]{roleid}, new RowMapper(){
			public Object mapRow(ResultSet r, int i) throws SQLException {
				String resid=r.getString("resid");
				String roleid=r.getString("roleid");
				build_role_r_res(roleid,resid);
				return null;
			}
		});
	}
	
	void load_r_user_res(){
		for(SUser u:ALL_USER){
			List<SRole> roles=u.getRoles();
			for(SRole r:roles)
				u.getRes().addAll(r.getRes());
		}
	}
	
	void load_r_user_res(String userid){
		for(SUser u:ALL_USER){
			if(userid.equals(u.getUserid())){
				List<SRole> roles=u.getRoles();
				for(SRole r:roles)
					u.getRes().addAll(r.getRes());
			}
		}
	}
	
	void build_user_r_role(String userid,String roleid){
		int i=0,x=0;
		int len=ALL_USER.size();
		for(;i<len;i++){
			if(ALL_USER.get(i).getUserid().equals(userid))
				break;
		}
		if(i>=len)
			throw new RuntimeException("not found userid:["+userid+"]");
		len=ALL_ROLE.size();
		for(;x<len;x++){
			if(ALL_ROLE.get(x).getRoleid().equals(roleid))
				break;
		}
		if(x>=len)
			throw new RuntimeException("not found roleid:["+roleid+"]");
		
		ALL_USER.get(i).getRoles().add(ALL_ROLE.get(x));
	}
	
	void build_role_r_res(String roleid,String resid){
		int i=0,x=0;
		int len=ALL_RES.size();
		for(;i<len;i++){
			if(ALL_RES.get(i).getResid().equals(resid))
				break;
		}
		if(i>=len)
			throw new RuntimeException("not found resid:["+resid+"]");
		len=ALL_ROLE.size();
		for(;x<len;x++){
			if(ALL_ROLE.get(x).getRoleid().equals(roleid))
				break;
		}
		if(x>=len)
			throw new RuntimeException("not found roleid:["+roleid+"]");
		
		if(!ALL_ROLE.get(x).getRes().contains(ALL_RES.get(i)))
			ALL_ROLE.get(x).getRes().add(ALL_RES.get(i));
	}
	
	@Override
	public Map<String, Set<String>> getUrlAuthorities(String userid) {
		Map<String,Set<String>> urls=new HashMap<String,Set<String>>();
		SUser user=getUser(userid);
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
	
	@Override
	public SUser getUser(String userid) {
		SUser r=null;
		int i=0;
		int len=ALL_USER.size();
		for(;i<len;i++){
			if(ALL_USER.get(i).getUserid().equals(userid)){
				r=ALL_USER.get(i);
				break;
			}
		}
		//cache not found,then query db
		if(r==null||immediate)
			r=loadUser(userid,immediate);
		return r;
	}

	@Override
	public SUser getUser(String userid, String password) {
		SUser r=getUser(userid);
		if(r!=null&&password.equals(r.getPassword()))
			return null;
		return r;
	}

	@Override
	public List<SRole> getRoles(String userid) {
		return getUser(userid).getRoles();
	}

	@Override
	public List<SResource> getRes(String userid) {
		return getUser(userid).getRes();
	}


	@Override
	public List<SUser> getAllUsers() {
		return ALL_USER;
	}

	@Override
	public List<SRole> getAllRoles() {
		return ALL_ROLE;
	}

	@Override
	public List<SResource> getAllRes() {
		return ALL_RES;
	}
	
	@Override
	public void refreshAuths() {
		init();
	}
	
	@Override
	public SUser refreshUser(String userid) {
		return loadUser(userid,true);
	}

	private boolean isTable(String entity){
		return entity.toLowerCase().indexOf(" from ")<0;
	}
	
	/**
	 * 1	select * from s_user
	 * 2	s_user
	 * 3	s_user,a,b,c
	 * @param entity
	 * @return
	 */
	private String toSQL(String entity){
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
	
	@Override
	public void setImmediate(boolean imd) {
		this.immediate=imd;
	}
	
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

	final static String ANONYMOUSE_USER="anonymousUser";
	private static final List<SUser> ALL_USER=new ArrayList<SUser>();
	private static final List<SRole> ALL_ROLE=new ArrayList<SRole>();;
	private static final List<SResource> ALL_RES=new ArrayList<SResource>();
	
	private JdbcTemplate jt;
	private String t_user;
	private String t_role;
	private String t_res;
	private String r_user_role;
	private String r_role_res;
	
	private boolean immediate=true;
	
}
