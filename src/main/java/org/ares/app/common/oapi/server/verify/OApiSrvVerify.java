package org.ares.app.common.oapi.server.verify;

import java.util.Map;

public interface OApiSrvVerify {

	/**
	 * @param params key set is (sign,callback...)
	 * @return key set is (code,message)
	 */
	Map<String,Object> check(Map<String,Object> params);
	
}
