package org.ares.app.demo.action;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.ares.app.demo.model.WxModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/wx")
public class WxAction {

	@RequestMapping("/receive")
	public @ResponseBody String receive(HttpServletRequest request,@RequestBody WxModel model){
		System.out.println(request.getContentType());
		System.out.println(model.getContent());
		return "receive";
		
	}
	@RequestMapping("/r")
	public void r(HttpServletRequest request){
		try {
			InputStreamReader isr=new InputStreamReader(request.getInputStream(),"utf-8");
			int ch;
			while((ch=isr.read())!=-1)
				System.out.print((char)ch);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@RequestMapping("/rcv")
	public @ResponseBody String rcv(HttpServletRequest request,WxModel model){
		System.out.println(request.getCharacterEncoding());
		System.out.println(request.getContentType());
		System.out.println(model.getContent());
		return "rcv";
	}
	
	@RequestMapping("/xml")
	public @ResponseBody WxModel wm(WxModel model){
		System.out.println(model.getContent());
		WxModel m=new WxModel();
		m.setContent("content");
		m.setFromUserName("ly");
		m.setMsgType("1111111");
		return m;
	}
	
}
