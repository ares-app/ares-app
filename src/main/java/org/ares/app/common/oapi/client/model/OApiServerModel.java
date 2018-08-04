package org.ares.app.common.oapi.client.model;

import java.io.Serializable;
/**
 * Inner Service OpenAPI Mapping
 * @author ly
 *
 */
@SuppressWarnings("serial")
public class OApiServerModel implements Serializable {
		
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
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

	private String descr;//服务描述
	private String addr;//服务地址
	private String name;//服务名称
	private String id;//服务标识
	private String seckey;//服务密码
	private String consid;//服务提供者id
	private boolean enable;
}
