package org.ares.app.common.security.data;

/**
 * 数据权限
 * @author ly
 */
public interface DataSecurity {

	default String getQL(String source){
		return source;
	}
	
}
