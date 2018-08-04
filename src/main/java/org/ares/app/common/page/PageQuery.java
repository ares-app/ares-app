package org.ares.app.common.page;

import java.util.Map;

public interface PageQuery{

	String getSQL(String sql,int start,int end);
	
	String getSQL(String sql);
	
	void setPage(PageModel page);
	
	int getCount(String sql);
	
	int getCount(String sql,Object[] params);
	
	int getCount(String sql,Map<String,Object> params);
	
}
