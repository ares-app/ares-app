package org.ares.app.common.page.dialect;

public class OracleDialect extends Dialect {

	public String getLimitString(String sql, int offset, int limit,boolean hasOffset) {

		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		if (hasOffset)
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		else
			pagingSelect.append("select * from ( ");
		
		pagingSelect.append(sql);
		if (hasOffset) {
			pagingSelect.append(" ) row_ where rownum <= ").append(limit).append(") where rownum_ > ").append(offset);
		} else {
			pagingSelect.append(" ) where rownum <= ").append(limit);
		}
		/*if (hasOffset) {
			pagingSelect.append(" ) row_ where rownum <= ?) where rownum_ > ?");
		} else {
			pagingSelect.append(" ) where rownum <= ?");
		}*/

		if (isForUpdate) {
			pagingSelect.append(" for update");
		}

		return pagingSelect.toString();
	}

	public String getCountSql(String sql){
		String r=null;
		if(isSelectSql(sql))
			r="select count(0) from ("+sql+") t";
		return r;
	}
}
