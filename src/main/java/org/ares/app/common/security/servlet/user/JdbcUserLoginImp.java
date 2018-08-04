package org.ares.app.common.security.servlet.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

public class JdbcUserLoginImp implements UserLogin {

	@Override
	public UserModel getUser(String userid) {
		return getUserInDB(userid);
	}
	
	UserModel getUserInDB(String userid) {
		String sql=t_user;
		final UserModel user=new UserModel();
		
		jt.query(sql, new Object[] { userid },new RowCallbackHandler() {
			public void processRow(ResultSet r) throws SQLException {
				if(user.getUserid()==null){
					user.setUserid(r.getString("userid"));
					user.setPassword(r.getString("userpwd"));
					user.setUsername(r.getString("username"));
					user.setStatus(r.getString("status"));
				}
			}
		});
		
		if(user.getUserid()==null)
			return null;
		return user;
	}
	
	public void setT_user(String t_user) {
		this.t_user = t_user;
	}
	
	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}

	private JdbcTemplate jt;
	private String t_user;//select userid,userpwd,username,status from user where userid=?
}
