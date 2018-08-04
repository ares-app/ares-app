package org.ares.app.demo.action;

import java.util.HashMap;
import java.util.Map;

import org.ares.app.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/oapi")
public class OApiXAction {

	@ResponseBody
	@RequestMapping("/xuserlist")
	public Map<String,Object> getUsers(){
		Map<String,Object> r=new HashMap<>();
		r.put("data", jpaus.getUsers());
		return r;
	}
	
	@Autowired @Qualifier("jpaus")  IUserService jpaus;
}
