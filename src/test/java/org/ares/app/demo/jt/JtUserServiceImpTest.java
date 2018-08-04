package org.ares.app.demo.jt;

import org.ares.app.demo.model.UserModel;
import org.ares.app.demo.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath:test/t-jt.xml"})
public class JtUserServiceImpTest {
	
	@Test
	public void testGetUsers(){
		jtus.getUsers().stream().forEach(System.out::println);
	}
	
	public void testAddUser() {
		UserModel u=new UserModel();
		System.out.println(id);
		u.setUserid(id);
		u.setEmail(id+"@ares.com");
		u.setPassword(id+"ax");
		u.setUsername("ares"+id);
		jtus.addUser(u);
	}
	
	
	public void testGetUser() {
		System.out.println(jtus.getUser(id));
	}
	
	
	public void testUpdateUser() {
		UserModel u=new UserModel();
		u.setUserid(id);
		u.setEmail(id+"@ly.com");
		u.setPassword(id+"bz");
		u.setUsername("ly"+id);
		jtus.updateUser(u);
	}

	
	public void testDeleteUser() {
		jtus.deleteUser(id);
	}
	
	static String id=""+new java.util.Random().nextInt(1000);
	@Autowired @Qualifier("jtus")  IUserService jtus;

}
