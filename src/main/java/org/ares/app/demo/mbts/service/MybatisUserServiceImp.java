package org.ares.app.demo.mbts.service;

import java.util.List;

import javax.annotation.Resource;

import org.ares.app.common.sign.OperMessage;
import org.ares.app.demo.mbts.mapper.UserMapper;
import org.ares.app.demo.model.UserModel;
import org.ares.app.demo.service.IUserService;

public class MybatisUserServiceImp implements IUserService {

	@OperMessage("用户添加")
	@Override
	public void addUser(UserModel user) {
		userMapper.insert(user);
	}

	@Override
	public UserModel getUser(String userid) {
		UserModel u=userMapper.selectByPrimaryKey(userid);
		return u;
	}

	@Override
	public void updateUser(UserModel user) {
		userMapper.updateByPrimaryKey(user);
	}

	@Override
	public void deleteUser(String userid) {
		userMapper.deleteByPrimaryKey(userid);
	}
	
	@Override
	public List<?> getUsers() {
		return userMapper.queryAll();
	}
	
	@Resource
	private UserMapper userMapper;

}
