package org.ares.app.demo.mbts.service;

import java.util.List;

import javax.annotation.Resource;

import org.ares.app.common.advice.datastore.WebDataStore;
import org.ares.app.common.page.PageModel;
import org.ares.app.demo.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath:test/t-mybatis.xml"})
public class MybatisUserServiceImpTest {

	
	@Test
	public void testGetUser() {
		PageModel page=new PageModel();
		page.setPageSize(2);
		dataStore.savePage(page);
		List<?> list=mybatisus.getUsers();
		System.out.println(list.size());
	}
	
	@Resource WebDataStore dataStore;
	@Resource @Qualifier("mybatisus")  IUserService mybatisus;
	
}
