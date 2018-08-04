package org.ares.app.common.advice.datastore;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public class WebDataStore extends DataStore {

	public HttpServletRequest getCurrentRequest(){
		return (HttpServletRequest)get(REQUEST_DATA);
	}
	
	public void saveRequest(HttpServletRequest request){
		put(REQUEST_DATA, request);
	}
		
	@Override
	public ThreadLocal<HashMap<String, Object>> getDataStore() {
		return DATA_STORE;
	}

	static final ThreadLocal<HashMap<String,Object>> DATA_STORE=new ThreadLocal<HashMap<String,Object>>();
	public final static String REQUEST_DATA="current_request_data";
	
	
}
