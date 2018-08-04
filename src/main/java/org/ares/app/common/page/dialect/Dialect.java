package org.ares.app.common.page.dialect;

public abstract class Dialect {

	/**
	 * " limit ?, ?" : " limit ?"
	 * @param query
	 * @param offset
	 * @param limit
	 * @return
	 */
	public String getLimitString(String query, int offset, int limit) {
		if(!isSelectSql(query))//insert update delete
			return query;
		String sql= getLimitString(query, offset,limit,(offset > 0 || forceLimitUsage()));
		return sql;
	}

	public boolean forceLimitUsage() {
		return false;
	}
	
	public String getCountSql(String sql){
		String r=null;
		if(isSelectSql(sql))
			r="select count(0) from ("+sql+") as t";
		return r;
	}
	
	public boolean isSelectSql(String sql){
		return sql.toLowerCase().trim().startsWith("select");
	}

	abstract public String getLimitString(String sql, int offset, int limit,boolean hasOffset);
}
