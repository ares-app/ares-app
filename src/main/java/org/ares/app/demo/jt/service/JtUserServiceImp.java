package org.ares.app.demo.jt.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.ares.app.common.exception.BizLogicException;
import org.ares.app.common.sign.OperMessage;
import org.ares.app.demo.model.UserModel;
import org.ares.app.demo.service.IUserService;
import org.springframework.stereotype.Service;

@Service("jtus")
public class JtUserServiceImp implements IUserService {

	@Override
	public void addUser(UserModel user) {

	}

	@Override
	public UserModel getUser(String userId) {
		return null;
	}

	@Override
	public void updateUser(UserModel user) {

	}

	@Override
	public void deleteUser(String userId) {

	}

	@OperMessage("用户查询")
	@Override
	public List<?> getUsers() {
		System.out.println(sql.getUsers());
		int x=1;
		if(x-1==0)
			throw new /*RuntimeException*/BizLogicException("biz error!");
		return new ArrayList<UserModel>();
	}
	
	@Inject UserSQL sql;

}
