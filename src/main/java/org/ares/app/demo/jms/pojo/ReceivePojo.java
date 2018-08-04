package org.ares.app.demo.jms.pojo;

import java.util.Map;

import org.apache.log4j.Logger;

public class ReceivePojo {

	public void receive(String msg){
		log.info("[pojo]--"+msg);
	}
	
	public void receive(Map<String,Object> msg){
		log.info("[pojo]--"+msg);
	}
	
	static final Logger log=Logger.getLogger(ReceivePojo.class);
}
