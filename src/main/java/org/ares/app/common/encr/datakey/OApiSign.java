package org.ares.app.common.encr.datakey;

import static org.ares.app.common.conf.Params.OAPI_ACCESS_KEY;
import static org.ares.app.common.conf.Params.OAPI_REQUEST_IP;
import static org.ares.app.common.conf.Params.OAPI_SECRET_KEY;
import static org.ares.app.common.conf.Params.OAPI_SIGN;

import java.util.Map;

import org.apache.log4j.Logger;
import org.ares.app.common.conf.Params;
import org.ares.app.common.encr.SignGenerator;
import org.ares.app.common.oapi.time.TimeService;

/**
 * HMAC_SHA1
 * 新式OPENAPI校验
 * 算法：哈希算法 HMAC_SHA1,HMAC_SHA1(consid+IP+timestamp)用secretKey作为密钥，默认小写
 * sign=ATP:accesskey:timestamp:HMAC_SHA1{secretkey}(accesskey+IP+timestamp) 
 * @author ly
 */

public class OApiSign extends BaseDataKeySign implements SignGenerator{
	
	public String serverSign(Map<String, Object> params) {
		String accesskey=params.get(OAPI_ACCESS_KEY)+"";
		String ip=params.get(OAPI_REQUEST_IP)+"";
		String secretkey=params.get(OAPI_SECRET_KEY)+"";
		return serverSign(accesskey,ip,secretkey);
	}

	String serverSign(String accesskey,String ip,String secretkey) {
		return serverSign(accesskey,ip,secretkey,false);
	}
	
	String serverSign(String accesskey,String ip,String secretkey,boolean eq) {
		String r=null;
		long reqTime=timeService.getServerUnixTime();
		r=convHexHash((accesskey+reqTime+ip).getBytes(),secretkey);
		r=prefix+":"+accesskey+":"+reqTime+":"+r;
		if(eq)
			r=OAPI_SIGN+"="+r;
		return r;
	}
	
	@Override
	public String hashSign(Map<String, Object> params) {
		log.debug("#[hashSign param]#"+params);
		String accesskey=params.get(OAPI_ACCESS_KEY)+"";
		String ip=params.get(OAPI_REQUEST_IP)+"";
		String secretkey=params.get(OAPI_SECRET_KEY)+"";
		Long timestamp=(Long)params.get(Params.OAPI_REQUEST_TIMESTAMP);
		return hashSign(accesskey,ip,timestamp,secretkey);
	}
	
	String hashSign(String accesskey,String ip,Long reqTime,String secretkey) {
		return convHexHash((accesskey+reqTime+ip).getBytes(),secretkey);
	}
	
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public void setTimeService(TimeService timeService) {
		this.timeService = timeService;
	}
	
	private String prefix="ATI";
	private TimeService timeService;
	private static Logger log = Logger.getLogger(OApiSign.class);
}
