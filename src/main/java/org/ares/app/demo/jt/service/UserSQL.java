package org.ares.app.demo.jt.service;

import org.ares.app.common.sign.DataAuth;
import org.ares.app.common.sign.Page;
import org.springframework.stereotype.Component;

@Component
public class UserSQL {

	@Page(size=5) @DataAuth
	public String getUsers(){
		return "select * from d_user";
	}
	
}
