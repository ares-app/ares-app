package org.ares.app.common.encr;

import java.util.Map;

public interface SignGenerator {

	/**
	 * @return sign=ATP:accesskey:timestamp:HMAC_SHA1{secretkey}(accesskey+IP+timestamp)
	 */
	String serverSign(Map<String,Object> params); 
	
	/**
	 * @return HMAC_SHA1{secretkey}(accesskey+IP+timestamp)
	 */
	String hashSign(Map<String,Object> params);
	
}
