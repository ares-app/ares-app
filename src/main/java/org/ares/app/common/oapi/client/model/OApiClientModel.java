package org.ares.app.common.oapi.client.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OApiClientModel implements Serializable {

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
	
	private String descr;//服务描述
	private String addr;//服务地址
	private String name;//服务名称
	private String id;//服务标识
	private boolean enable;//有效标识
	
}
