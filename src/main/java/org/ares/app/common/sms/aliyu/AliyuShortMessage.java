package org.ares.app.common.sms.aliyu;

import java.util.Map;

import org.apache.log4j.Logger;
import org.ares.app.common.exception.BizLogicException;
import org.ares.app.common.sms.ShortMessage;
import org.ares.app.common.util.Util;

import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class AliyuShortMessage implements ShortMessage {

	@Override
	public Object sendShortMsg(Map<String,String> param) {
		Object r=null;
		AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			String no=param.get(SHORT_MSG_ALIYU_REC_NUM);
			if(Util.isEmpty(no))
				throw new BizLogicException("mobile number is null!");
			snsr.setRecNum(no);
			snsr.setSmsParamString(param.get(SHORT_MSG_ALIYU_SMS_PARAM_STRING));//{vercode:'5921'}
			snsr.setExtend(param.get(SHORT_MSG_ALIYU_EXTEND));
			rsp = client.execute(snsr);
			log.info(r=rsp.getBody());
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public void setSnsr(AlibabaAliqinFcSmsNumSendRequest snsr) {
		this.snsr = snsr;
	}
	public void setClient(TaobaoClient client) {
		this.client = client;
	}

	public static final String SHORT_MSG_ALIYU_SMS_PARAM_STRING="aliyu_sms_param_string";
	public static final String SHORT_MSG_ALIYU_REC_NUM="aliyu_rec_num";
	public static final String SHORT_MSG_ALIYU_EXTEND="aliyu_extend";
	
	private AlibabaAliqinFcSmsNumSendRequest snsr;
	private TaobaoClient client;
	private static Logger log=Logger.getLogger(AliyuShortMessage.class);
	
}
