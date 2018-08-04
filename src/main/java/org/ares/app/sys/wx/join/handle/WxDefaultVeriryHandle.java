package org.ares.app.sys.wx.join.handle;

import java.util.Map;

import org.apache.log4j.Logger;
import org.ares.app.common.encr.data.WxchatDictSign;
import org.ares.app.common.repository.jt.simple.SimpleQuery;
import org.ares.app.common.util.Util;

public class WxDefaultVeriryHandle implements IWxVerifyHandle {

	@Override
	public boolean check(Map<String, Object> params) {
		boolean r=false;
		String signature=params.get(WxchatDictSign.WX_SIGNATURE)+"";
		String token=consumer.getSingleValue(offaccid);
		params.put(WxchatDictSign.WX_TOKEN, token);
		if(Util.isEmpty(signature))
			return false;
		String calcSignature=sign.hashSign(params);
		log.debug("[token="+token+"],[wechat signature="+signature+"],[calc signature="+calcSignature+"]");
		if(signature.toLowerCase().equals(calcSignature))
			r=true;
		return r;
	}
	
	public void setOffaccid(String offaccid) {
		this.offaccid = offaccid;
	}
	public void setSign(WxchatDictSign sign) {
		this.sign = sign;
	}
	public void setConsumer(SimpleQuery consumer) {
		this.consumer = consumer;
	}

	static Logger log=Logger.getLogger(IWxVerifyHandle.class);
	private String offaccid;
	private WxchatDictSign sign;
	private SimpleQuery consumer;
	
	public static void main(String[] args){
		System.out.println();
	}


}
