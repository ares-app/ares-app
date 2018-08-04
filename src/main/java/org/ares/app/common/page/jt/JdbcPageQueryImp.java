package org.ares.app.common.page.jt;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.ares.app.common.page.PageModel;
import org.ares.app.common.page.PageQuery;
import org.ares.app.common.page.dialect.Dialect;

public class JdbcPageQueryImp implements PageQuery{
	
	public String getSQL(String sql,int start,int end) {
		return dialect.getLimitString(sql, start, end);
	}
	
	public String getSQL(String sql) {
		return getSQL(sql,page.getStart(),page.getEnd());
	}

	public int getCount(String sql) {
		sql="select count(*) from ("+sql+") t";
		int count=jt.queryForObject(sql, Integer.class);
		return count;
	}
	
	public int getCount(String sql,Object[] params) {
		sql="select count(*) from ("+sql+") t";
		int count=jt.queryForObject(sql,params, Integer.class);
		return count;
	}
	
	public int getCount(String sql,Map<String,Object> params) {
		sql="select count(*) from ("+sql+") t";
		int count=npjt.queryForObject(sql, params, Integer.class);
		return count;
	}
	
	public void setDialect(Dialect dialect){
		this.dialect=dialect;
	}
	
	public void setPage(PageModel page) {
		this.page = page;
	}
	
	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}
	
	public void setNpjt(NamedParameterJdbcTemplate npjt) {
		this.npjt = npjt;
	}


	protected Dialect dialect;
	private PageModel page;
	private JdbcTemplate jt;
	private NamedParameterJdbcTemplate npjt;
	
}
