package org.ares.app.common.oapi.server.audit;

import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class JdbcRequestAuditImp implements RequestAudit{

	public String getRequestID(){
		return (System.currentTimeMillis()+"")/*.hashCode()+""*/;
	}
	
	public void saveRequestAudit(Map<String,Object> params){
		npjt.update(sql, params);
	}
	
	public void setSql(String sql) {
		this.sql = sql;
	}

	public void setNpjt(NamedParameterJdbcTemplate npjt) {
		this.npjt = npjt;
	}

	private NamedParameterJdbcTemplate npjt;
	private String sql;
}
