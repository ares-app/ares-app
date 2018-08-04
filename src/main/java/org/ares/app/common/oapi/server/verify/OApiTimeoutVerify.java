package org.ares.app.common.oapi.server.verify;

import static org.ares.app.common.conf.Params.MSG_TIMEOUT_ERROR;
import static org.ares.app.common.conf.Params.OAPI_REQUEST_TIMESTAMP;
import static org.ares.app.common.conf.Params.RET_CODE;
import static org.ares.app.common.conf.Params.RET_MESSAGE;
import static org.ares.app.common.conf.Params.RET_TIME_OUT;

import java.util.HashMap;
import java.util.Map;

import org.ares.app.common.oapi.time.TimeService;

public class OApiTimeoutVerify implements OApiSrvVerify {

	@Override
	public Map<String, Object> check(Map<String, Object> params) {
		Map<String, Object> r=new HashMap<String,Object>();
		r.put(RET_CODE, 0);
		long reqTime=((Long)params.get(OAPI_REQUEST_TIMESTAMP)).longValue();
		long serverTime=timeService.getServerUnixTime();
		long len=serverTime-reqTime;
		if(!(len>=0 && len<maxsecond)){
			r.put(RET_CODE, RET_TIME_OUT);
			r.put(RET_MESSAGE, MSG_TIMEOUT_ERROR);
		}
		return r;
	}

	public void setMaxsecond(int maxsecond) {
		this.maxsecond = maxsecond;
	}

	public void setTimeService(TimeService timeService) {
		this.timeService = timeService;
	}

	private TimeService timeService;
	private int maxsecond=600;
	
}
