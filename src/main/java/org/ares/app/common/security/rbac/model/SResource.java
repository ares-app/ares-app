package org.ares.app.common.security.rbac.model;

import org.ares.app.common.dto.DTO;

@SuppressWarnings("serial")
public class SResource implements DTO {

	public String getResid() {
		return resid;
	}
	public void setResid(String resid) {
		this.resid = resid;
	}
	public String getResname() {
		return resname;
	}
	public void setResname(String resname) {
		this.resname = resname;
	}
	public String getResurl() {
		return resurl;
	}
	public void setResurl(String resurl) {
		this.resurl = resurl;
	}
	
	@Override
	public int hashCode() {
		return resid.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		boolean r=false;
		SResource o=(SResource)obj;
		if(resid.equals(o.resid))
			r=true;
		return r;
	}
	
	private String resid;
	private String resname;
	private String resurl;
}
