package org.ares.app.sys.wx.join.handle;

import org.ares.app.sys.wx.model.WxMessage;
import org.ares.app.sys.wx.model.WxModel;

/**
 * input xml => output xml
 * include message,enent
 * @author ly
 *
 */
public interface IWxMessageHandle {

	WxMessage processMessage(WxModel m);
	
}
