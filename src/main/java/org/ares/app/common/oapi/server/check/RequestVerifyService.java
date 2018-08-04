package org.ares.app.common.oapi.server.check;

import java.util.Map;

public interface RequestVerifyService {

	/**
	 * @param params key set is (sign,callback...)
	 * @return key set is (code,message)
	*/
	Map<String,Object> check(Map<String,Object> params);
	
}
