package org.ares.app.common.oapi.client.ip;

import static org.ares.app.common.util.Util.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalIPServiceImp implements IPService {

	@Override
	public String getLocalIP() {
		String r=localip;
		if(isEmpty(r)){
			try {
				InetAddress addr = InetAddress.getLocalHost();
				r= addr.getHostAddress().toString();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		return r;
	}

	public void setLocalip(String localip) {
		this.localip = localip;
	}

	private String localip;
	
}
