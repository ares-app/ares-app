package org.ares.app.sys.wx.join.action;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.ares.app.common.util.Util;
import org.ares.app.sys.wx.component.WxComponent;
import org.ares.app.sys.wx.join.handle.IWxVerifyHandle;
import org.ares.app.sys.wx.model.WxMessage;
import org.ares.app.sys.wx.model.WxModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WechatAction {
	
	@RequestMapping(value={"/wechat"},method=GET)
	public @ResponseBody String joinVerify(WxModel model){
		String r="sign error";
		Map<String,Object> params=Util.convBeanToMap(model);
		if(verify.check(params))
			r=model.getEchostr();
		return r;
	}
	
	@RequestMapping(value="/wechat",method=POST,produces={"application/octet-stream","text/xml"})
	public @ResponseBody Object receive(HttpServletRequest request,@RequestBody WxModel model){
		WxMessage r=wxc.getWxMessage(model);
		log.debug(r);
		return r;
	}
	
	Logger log=Logger.getLogger(getClass());
	@Inject IWxVerifyHandle verify;
	@Inject WxComponent wxc;

}
