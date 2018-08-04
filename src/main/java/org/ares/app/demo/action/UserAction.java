package org.ares.app.demo.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ares.app.demo.model.UserModel;
import org.ares.app.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserAction {

	@RequestMapping("/list")
	public Map<String,Object> getUsers(){
		Map<String,Object> r=new HashMap<>();
		r.put("data", jpaus.getUsers());
		return r;
	}
	
	@RequestMapping("/get")
	public Map<String,Object> getUser(UserModel m,HttpServletRequest request){
		Map<String,Object> r=new HashMap<>();
		System.out.println(request.getParameter("userid")+"****************");
		r.put("data", jpaus.getUser(m.getUserid()));
		return r;
	}
	
	@Autowired @Qualifier("jpaus")  IUserService jpaus;
}
