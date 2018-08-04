package org.ares.app.common.security.rbac.model;

import java.util.ArrayList;
import java.util.List;

import org.ares.app.common.dto.DTO;

@SuppressWarnings("serial")
public class SUser implements DTO {

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<SRole> getRoles() {
		return roles;
	}
	public void setRoles(List<SRole> roles) {
		this.roles = roles;
	}
	public List<SResource> getRes() {
		return res;
	}
	public void setRes(List<SResource> res) {
		this.res = res;
	}
	public String getGruopid() {
		return gruopid;
	}
	public void setGruopid(String gruopid) {
		this.gruopid = gruopid;
	}

	@Override
	public int hashCode() {
		return userid.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		boolean r=false;
		SUser o=(SUser)obj;
		if(userid.equals(o.userid))
			r=true;
		return r;
	}
	
	private String userid;
	private String username;
	private boolean enabled;
	private String email;
	private String password;
	private String gruopid;
	private List<SRole> roles=new ArrayList<SRole>();
	private List<SResource> res=new ArrayList<SResource>();
}
