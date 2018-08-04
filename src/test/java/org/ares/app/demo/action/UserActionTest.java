package org.ares.app.demo.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = {"classpath:test/t-mvc.xml"})
public class UserActionTest {

	@Test
	public void testGetUsers() throws Exception {
		String json="";
		Object v=mm.perform(post("/user/list.do","json").characterEncoding("UTF-8").
				contentType(MediaType.APPLICATION_JSON).content(json.getBytes()))
				.andReturn().getResponse().getContentAsString();
		System.out.println("[data]--"+v);
	}
	
	@Test
	public void testGetUser() throws Exception {
		Map<String,Object> param=new HashMap<>();
		param.put("userid", "admin");
		String json=om.writeValueAsString(param);
		System.out.println("json is:"+json);
		Object v=mm.perform(post("/user/get.do").characterEncoding("UTF-8").
				contentType(MediaType.APPLICATION_JSON).content(json.getBytes()).accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse().getContentAsString();
		System.out.println("[data]--"+v);
	}
	
	@Before
	public void setup() throws Exception {
		this.mm = webAppContextSetup(this.wac).build();
	}
	
	private static final ObjectMapper om=new ObjectMapper();
	@Autowired  protected WebApplicationContext wac;
	private MockMvc mm;

	/**
	 *  UserAction action=new UserAction();
	 *	MockMvc mm=standaloneSetup(action).build();
	*/
	
}
