package org.ares.app.common.advice.datastore;

import java.util.HashMap;

/**
 * now no use
 * @author ly
 *
 */
public class BizDataStore extends DataStore {

	@Override
	public ThreadLocal<HashMap<String, Object>> getDataStore() {
		return DATA_STORE;
	}

	static final ThreadLocal<HashMap<String,Object>> DATA_STORE=new ThreadLocal<HashMap<String,Object>>();
	
}
