package org.ares.app.demo.mbts.mapper;

import java.util.List;

import org.ares.app.demo.model.UserModel;

public interface UserMapper {

	int deleteByPrimaryKey(String userid);

	int insert(UserModel record);

	int insertSelective(UserModel record);

	UserModel selectByPrimaryKey(String userid);

	int updateByPrimaryKeySelective(UserModel record);

	int updateByPrimaryKey(UserModel record);
	
	List<UserModel> queryAll();

}