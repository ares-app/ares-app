package org.ares.app.demo.jpa.service;

import static org.ares.app.common.util.BeanCopy.copyProperties;

import java.util.List;

import javax.inject.Inject;

import org.ares.app.demo.jpa.entity.DUser;
import org.ares.app.demo.model.UserModel;
import org.ares.app.demo.service.IUserService;
import org.springframework.stereotype.Service;

@Service("jpaus")
public class JpaUserServiceImp implements IUserService {

	@Override
	public void addUser(UserModel user) {
		DUser entity=new DUser();
		copyProperties(user,entity);
		ur.save(entity);
	}

	@Override
	public UserModel getUser(String userid) {
		DUser u= ur.findByUserid(userid);
		System.out.println(u);
		UserModel r=new UserModel();
		copyProperties(u,r);
		return r;
	}

	@Override
	public void updateUser(UserModel user) {
		DUser u= ur.findByUserid(user.getUserid());
		copyProperties(user, u);
		ur.save(u);
	}

	@Override
	public void deleteUser(String userId) {
		ur.delete(userId);
	}
	
	@Override
	public List<?> getUsers() {
		return ur.findAll();
	}
	
	@Inject UserRepository ur;

}
