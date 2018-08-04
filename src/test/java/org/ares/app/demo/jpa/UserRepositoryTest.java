package org.ares.app.demo.jpa;

import javax.inject.Inject;

import org.ares.app.demo.jpa.service.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath:s-jpa.xml"})
public class UserRepositoryTest {

	@Test
	public void query(){
		ur.findAll().stream().forEach(System.out::println);
	}
	
	
	@Inject UserRepository ur;
}
