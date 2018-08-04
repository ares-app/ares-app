package org.ares.app.sys.wx.join.handle;

import static java.util.UUID.randomUUID;

import javax.inject.Inject;

import org.ares.app.sys.wx.conf.WxParams;
import org.ares.app.sys.wx.join.service.JoinService;
import org.ares.app.sys.wx.model.WxMessage;
import org.ares.app.sys.wx.model.WxModel;
import org.ares.app.sys.wx.model.WxTextMessage;

/**
 * WX Join Controller
 * @author ly
 */
public class WxTextMessageHandle implements IWxMessageHandle {

	@Override
	public WxMessage processMessage(WxModel m) {
		WxTextMessage msg=new WxTextMessage();
		long ctime=System.currentTimeMillis();
		long msgid=Long.MAX_VALUE+randomUUID().hashCode();
		String content=m.getContent();
		msg.setCreateTime(Long.valueOf(ctime));
		msg.setFromUserName(m.getToUserName());
		msg.setToUserName(m.getFromUserName());
		msg.setMsgType(WxParams.WX_MSG_TEXT_TYPE);
		msg.setMsgId(Long.valueOf(msgid));
		msg.setContent(js.getContent(content));
		return msg;
	}
	
	@Inject JoinService js;
}
