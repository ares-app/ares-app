package org.ares.app.common.sms;

import java.util.Map;

public interface ShortMessage {

	/**
	 * @param param
	 * @return
	 */
	Object sendShortMsg(Map<String,String> param);
	
}
