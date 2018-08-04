package org.ares.app.common.page.dialect;

public class MySQLDialect extends Dialect {

	/**
	 * if(sql.indexOf("limit ?, ?")>-1) 
				sql=sql.replaceAll("?, ?", "limit "+offset+", "+limit);
		else if(sql.indexOf("limit ?")>-1)
			sql=sql.replaceAll("limit ?", "limit "+limit);
	 */
	public String getLimitString(String sql, int offset, int limit,boolean hasOffset) {
		String r = new StringBuffer(sql.length() + 20).append(sql)
				.append(hasOffset ? " limit ?, ?" : " limit ?").toString();
		if(r.indexOf("limit ?, ?")>-1) 
			r=r.replace("limit ?, ?", "limit "+offset+", "+(limit-offset));
		else if(r.indexOf("limit ?")>-1)
			r=r.replace("limit ?", "limit "+limit);
		
		return r;
	}

}
