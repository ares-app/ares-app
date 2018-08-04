package org.ares.app.sys.wx.component;

import static org.ares.app.sys.wx.conf.WxParams.WX_MSG_TEXT_TYPE;

import java.util.HashMap;
import java.util.Map;

import org.ares.app.sys.wx.join.handle.IWxMessageHandle;
import org.ares.app.sys.wx.model.WxMessage;
import org.ares.app.sys.wx.model.WxModel;

/**
 * Controller Factory
 * @author ly
 *
 */
public class WxComponent {

	public WxMessage getWxMessage(WxModel model){
		WxMessage r=null;
		IWxMessageHandle handle=messageHandles.get(msgType);
		r=handle.processMessage(model);
		return r;
	}
	
	public void setMessageHandles(Map<String, IWxMessageHandle> messageHandles) {
		this.messageHandles = messageHandles;
	}

	private Map<String,IWxMessageHandle> messageHandles=new HashMap<>();
	private String msgType=WX_MSG_TEXT_TYPE;
}
