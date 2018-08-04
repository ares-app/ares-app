package org.ares.app.demo.jpa;

import org.ares.app.demo.model.UserModel;
import org.ares.app.demo.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath:test/t-jpa.xml"})
public class JpaUserServiceImpTest {
	
	@Test
	public void testGetUsers(){
		jpaus.getUsers().stream().forEach(System.out::println);
	}

	@Test
	public void testAddUser() {
		UserModel u=new UserModel();
		System.out.println(id);
		u.setUserid(id);
		u.setEmail(id+"@ares.com");
		u.setPassword(id+"ax");
		u.setUsername("ares"+id);
		jpaus.addUser(u);
	}
	
	@Test
	public void testGetUser() {
		System.out.println(jpaus.getUser(id));
	}
	
	@Test
	public void testUpdateUser() {
		UserModel u=new UserModel();
		u.setUserid(id);
		u.setEmail(id+"@ly.com");
		u.setPassword(id+"bz");
		u.setUsername("ly"+id);
		jpaus.updateUser(u);
	}

	@Test
	public void testDeleteUser() {
		jpaus.deleteUser(id);
	}
	
	static String id=""+new java.util.Random().nextInt(1000);
	@Autowired @Qualifier("jpaus")  IUserService jpaus;
	
}
