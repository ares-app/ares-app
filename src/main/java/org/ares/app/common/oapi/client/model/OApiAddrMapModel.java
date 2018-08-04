package org.ares.app.common.oapi.client.model;

import java.io.Serializable;

/**
 * openapi client addr mapping server addr
 * @author ly
 *
 */
@SuppressWarnings("serial")
public class OApiAddrMapModel implements Serializable {
	
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public String getOaddr() {
		return oaddr;
	}
	public void setOaddr(String oaddr) {
		this.oaddr = oaddr;
	}
	public String getIname() {
		return iname;
	}
	public void setIname(String iname) {
		this.iname = iname;
	}
	public String getIaddr() {
		return iaddr;
	}
	public void setIaddr(String iaddr) {
		this.iaddr = iaddr;
	}
	public String getSeckey() {
		return seckey;
	}
	public void setSeckey(String seckey) {
		this.seckey = seckey;
	}
	public String getConsid() {
		return consid;
	}
	public void setConsid(String consid) {
		this.consid = consid;
	}
	public String getOdescr() {
		return odescr;
	}
	public void setOdescr(String odescr) {
		this.odescr = odescr;
	}
	public String getOenable() {
		return oenable;
	}
	public void setOenable(String oenable) {
		this.oenable = oenable;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getIdescr() {
		return idescr;
	}
	public void setIdescr(String idescr) {
		this.idescr = idescr;
	}
	public String getIenable() {
		return ienable;
	}
	public void setIenable(String ienable) {
		this.ienable = ienable;
	}

	//openapi client 
	private String oname;//name
	private String oaddr;//outer addr url
	private String odescr;//descr
	private String oenable;// enable 1 or 0
	private String oid;//outer addr id
	
	//openapi server
	private String iid;//inner addr id
	private String iname;//name
	private String iaddr;//inner addr url
	private String seckey;//client secretkey
	private String consid;//client asskey
	private String idescr;//descr
	private String ienable;//enable 1 or 0
}
