package org.ares.app.demo.mbts.service;

import javax.annotation.Resource;

import org.ares.app.demo.model.UserModel;
import org.ares.app.demo.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath:test/t-mybatis.xml"})
public class MybatisPageTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddUser() {
		UserModel u=new UserModel();
		u.setUserid(id);
		u.setEmail(id+"@ares.com");
		u.setPassword(id+"ax");
		u.setUsername("ares"+id);
		mybatisus.addUser(u);
	}
	
	@Test
	public void testGetUser() {
		System.out.println(mybatisus.getUser(id));
	}
	
	@Test
	public void testUpdateUser() {
		UserModel u=new UserModel();
		u.setUserid(id);
		u.setEmail(id+"@ly.com");
		u.setPassword(id+"bz");
		u.setUsername("ly"+id);
		mybatisus.updateUser(u);
	}

	//@Test
	public void testDeleteUser() {
		mybatisus.deleteUser(id);
	}

	static String id=""+new java.util.Random().nextInt(1000);
	@Resource @Qualifier("mybatisus")  IUserService mybatisus;
	
}
