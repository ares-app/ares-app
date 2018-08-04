package org.ares.app.sys.coapi.service;

import java.util.Map;

public interface OApiDataAccess {

	public Map<String, Object> getAPIDataByCurPath();
	
	public Map<String, Object> getAPIDataByPath(String out_path,Map<String,String[]> reqParam);
	
	public Map<String, Object> getAPIDataByPath(String out_path);
	
	public Map<String, Object> getAPIDataByInId(String[] in_id,Map<String,String[]> reqParam);
	
}
