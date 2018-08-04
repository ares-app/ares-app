package org.ares.app.sys.coapi.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InnerTestAction {

	@RequestMapping("/app1/oapi/innertest01")
	public @ResponseBody Map<String, Object> inner_test_01(){
		Map<String,Object> r=new HashMap<String,Object>();
		r.put("message", "test_001");
		return r;
	}
	
	@RequestMapping("/app2/oapi/innertest02")
	public @ResponseBody Map<String, Object> inner_test_02(){
		Map<String,Object> r=new HashMap<String,Object>();
		r.put("message", "test_002");
		return r;
	}
	
	@RequestMapping("/app3/oapi/innertest03")
	public @ResponseBody Map<String, Object> inner_test_03(){
		Map<String,Object> r=new HashMap<String,Object>();
		r.put("message", "test_00333");
		return r;
	}
	
	@RequestMapping("/app/getdata/test")
	public @ResponseBody Map<String, Object> inner_test_04(HttpServletRequest request){
		Map<String,Object> r=new HashMap<String,Object>();
		log.info("sign is:"+request.getParameter("sign"));
		log.info("discid is :"+request.getParameter("discid"));
		System.out.println("***inner_test_04***"+request.getParameter("discid"));
		r.put("message", "access /getdata/test");
		return r;
	}
	
	private static Logger log = Logger.getLogger(InnerTestAction.class);
	
}
