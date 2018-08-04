package org.ares.app.common.oapi.client.mapsrv;

import java.util.List;
import java.util.Map;

public interface OApiMapService {

	/**
	 * 内部服务地址及校验码集合
	 * k1=>inner_addr
	 * k2=>sign
	 * @param outeraddr
	 * @return
	 */
	List<Map<String,String>> getInnerAddrByOuterAddr(String outeraddr,Map<String,String[]> param);
	
	Map<String, String> buildOApiServiceParam(Map<String, Object> param);
	
	void refreshAddrMapInCache();
	
	boolean isUseCache();
	
	void setUseCache(boolean useCache);
}
