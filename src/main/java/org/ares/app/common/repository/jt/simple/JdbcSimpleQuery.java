package org.ares.app.common.repository.jt.simple;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

public class JdbcSimpleQuery implements SimpleQuery {

	@Override
	public String getSingleValue(String accessKey) {
		return geSecretkeyInDB(accessKey);
	}

	String geSecretkeyInDB(String accesskey) {
		final String[] k=new String[1];
		log.debug("[sql:"+accesskey+"]--"+sql);
		jt.query(sql, new Object[] { accesskey },new RowCallbackHandler() {
			public void processRow(ResultSet r) throws SQLException {
				k[0]=r.getString(1);
			}
		});
		
		return k[0];
	}
	
	public void setSql(String sql) {
		this.sql = sql;
	}

	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}

	private JdbcTemplate jt;
	private String sql;//select secretkey from  s_secretkey where accesskey=?
	Logger log=Logger.getLogger(JdbcSimpleQuery.class);
}
