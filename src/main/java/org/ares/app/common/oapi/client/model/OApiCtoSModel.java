package org.ares.app.common.oapi.client.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OApiCtoSModel implements Serializable {
	
	public String getInnerid() {
		return innerid;
	}
	public void setInnerid(String innerid) {
		this.innerid = innerid;
	}
	public String getOuterid() {
		return outerid;
	}
	public void setOuterid(String outerid) {
		this.outerid = outerid;
	}
	
	private String innerid;
	private String outerid;
	
}
