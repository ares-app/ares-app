package org.ares.app.common.security.rbac.model;

import java.util.ArrayList;
import java.util.List;

import org.ares.app.common.dto.DTO;

@SuppressWarnings("serial")
public class SRole implements DTO {
	
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getUrls() {
		return urls;
	}
	public void setUrls(String urls) {
		this.urls = urls;
	}
	public List<SResource> getRes() {
		return res;
	}
	public void setRes(List<SResource> res) {
		this.res = res;
	}

	
	@Override
	public int hashCode() {
		return roleid.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		boolean r=false;
		SRole o=(SRole)obj;
		if(roleid.equals(o.roleid))
			r=true;
		return r;
	}

	private String roleid;
	private String rolename;
	private String resource;
	private String urls;//字符串拼接,逗号分隔
	private List<SResource> res=new ArrayList<SResource>();
}
