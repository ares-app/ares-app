package org.ares.app.common.oapi.server.verify;

import static org.ares.app.common.conf.Params.MSG_NOT_ACCESSKEY;
import static org.ares.app.common.conf.Params.MSG_SIGN_ERROR;
import static org.ares.app.common.conf.Params.OAPI_ACCESS_KEY;
import static org.ares.app.common.conf.Params.OAPI_SECRET_KEY;
import static org.ares.app.common.conf.Params.OAPI_SIGN;
import static org.ares.app.common.conf.Params.RET_CODE;
import static org.ares.app.common.conf.Params.RET_CONSUMER_SIGN_ERROR;
import static org.ares.app.common.conf.Params.RET_MESSAGE;
import static org.ares.app.common.conf.Params.RET_NOT_AUTH;
import static org.ares.app.common.util.Util.isEmpty;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.ares.app.common.encr.datakey.OApiSign;
import org.ares.app.common.repository.jt.simple.SimpleQuery;

public class OApiSignVerify implements OApiSrvVerify {

	@Override
	public Map<String, Object> check(Map<String, Object> params) {
		Map<String, Object> r=new HashMap<String,Object>();
		r.put(RET_CODE, 0);
		String sign=params.get(OAPI_SIGN)+"";
		String accesskey=params.get(OAPI_ACCESS_KEY)+"";
		
		String secretkey=consumer.getSingleValue(accesskey);
		if(isEmpty(secretkey)){
			r.put(RET_CODE, RET_NOT_AUTH);
			r.put(RET_MESSAGE, MSG_NOT_ACCESSKEY);
			return r;
		}
		
		params.put(OAPI_SECRET_KEY, secretkey);
		String right_sign=signComponent.hashSign(params);
		log.debug("request map is+"+params);
		if(!right_sign.equals(sign)){
			log.info("  right sign is:"+right_sign);
			log.info("request sign is:"+sign);
			r.put(RET_CODE, RET_CONSUMER_SIGN_ERROR);
			r.put(RET_MESSAGE, MSG_SIGN_ERROR);
		}
		return r;
	}
	
	public void setSignComponent(OApiSign signComponent) {
		this.signComponent = signComponent;
	}
	public void setConsumer(SimpleQuery consumer) {
		this.consumer = consumer;
	}

	private static Logger log=Logger.getLogger(OApiSignVerify.class);
	private OApiSign signComponent;
	private SimpleQuery consumer;
}
