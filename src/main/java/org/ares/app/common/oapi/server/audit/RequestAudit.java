package org.ares.app.common.oapi.server.audit;

import java.util.Map;

public interface RequestAudit {

	public String getRequestID();
	
	public void saveRequestAudit(Map<String,Object> params);
}
