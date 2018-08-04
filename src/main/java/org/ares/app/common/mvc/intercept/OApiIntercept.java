package org.ares.app.common.mvc.intercept;


import static org.ares.app.common.conf.Params.CHARSET_ENCODING;
import static org.ares.app.common.conf.Params.JSONP_USE;
import static org.ares.app.common.conf.Params.JSON_CALLBACK;
import static org.ares.app.common.conf.Params.MSG_PARAM_ERROR;
import static org.ares.app.common.conf.Params.OAPI_ACCESS_KEY;
import static org.ares.app.common.conf.Params.OAPI_REQUEST_ADDR;
import static org.ares.app.common.conf.Params.OAPI_REQUEST_IP;
import static org.ares.app.common.conf.Params.OAPI_REQUEST_TIMESTAMP;
import static org.ares.app.common.conf.Params.OAPI_SIGN;
import static org.ares.app.common.conf.Params.RET_CODE;
import static org.ares.app.common.conf.Params.RET_MESSAGE;
import static org.ares.app.common.conf.Params.RET_PARAM_INVALID;
import static org.ares.app.common.conf.Params.RET_REQUEST_ID;
import static org.ares.app.common.conf.Params.UTF8_JSON_DATA_TYPE;
import static org.ares.app.common.util.Util.isEmpty;
import static org.ares.app.common.util.Util.isNotEmpty;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.ares.app.common.advice.datastore.WebDataStore;
import org.ares.app.common.oapi.server.audit.RequestAudit;
import org.ares.app.common.oapi.server.check.RequestVerifyService;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * OpenAPI端专用【代理端不用】
 * @author Administrator
 *
 */
public class OApiIntercept extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		/*String url=request.getRequestURI();
		if(!url.matches(apiUrlReg))
			return true;*/
		
		Map<String,Object> params=buildParams(request.getParameterMap());
		String ip=request.getRemoteAddr();
		log.debug("[API服务端接受的代理端IP:]-"+ip);
		params.put(OAPI_REQUEST_IP, ip);
		params.put(OAPI_REQUEST_ADDR, request.getRequestURI());
		requstAudit(params);
		
		if(params.containsKey(RET_CODE))//input param error
			return error_process(params,response);
		Map<String,Object> vr=verify.check(params);
		if((Integer)vr.get(RET_CODE)!=0){
			return error_process(vr,response);
		}
		
		dataStore.clear();
		if(isNotEmpty(request.getParameter(JSON_CALLBACK))){
			dataStore.put(JSONP_USE, true);
			dataStore.put(JSON_CALLBACK, request.getParameter(JSON_CALLBACK));
		}
		return super.preHandle(request, response, handler);
	}
	
	/**
	 * 出现错误并返回
	 * @param m
	 * @param response
	 * @return
	 * @throws Exception
	 */
	boolean error_process(Map<String,Object> m,HttpServletResponse response) throws Exception{
		String json=objectMapper.writeValueAsString(m);
		response.setContentType(UTF8_JSON_DATA_TYPE);
		log.info(json);
		response.getOutputStream().write(json.getBytes(CHARSET_ENCODING));
		response.getOutputStream().flush();
		return false;
	}
	
	/**
	 * sign=ATI:stuportal_c7oWCjWElHQqPGm6VWguFMvQ:1628215132:caf6oWfafjWElHvePGm6VWguFMCae
	 * @param rm
	 * @return
	 */
	Map<String,Object> buildParams(Map<String,String[]> rm){
		Map<String,Object> r=new HashMap<String,Object>();
		String sign_src=null;
		String[] a_sign=rm.get(OAPI_SIGN);
		if(a_sign!=null)
			sign_src=a_sign[0]+"";
		String[] params=null;
		String accesskey=null;
		long reqTime=-1;
		String sign=null;
		
		boolean param_error=false;
		if(isEmpty(sign_src))
			param_error=true;
		else{
			params=sign_src.split(":");
			if(params.length<4||!params[2].matches("\\d+")||isEmpty(params[1])||isEmpty(params[3]))
				param_error=true;
		}
			
		if(param_error){
			r.put(RET_CODE, RET_PARAM_INVALID);
			r.put(RET_MESSAGE, MSG_PARAM_ERROR);
			r.put(RET_REQUEST_ID, raudit.getRequestID());
			return r;
		}

		accesskey=params[1];
		reqTime=Long.parseLong(params[2]);
		sign=params[3];
		r.put(OAPI_SIGN, sign);
		r.put(OAPI_ACCESS_KEY, accesskey);
		r.put(OAPI_REQUEST_TIMESTAMP, reqTime);
		log.debug("rquest params is:"+r);
		return r;
	}
	
	/**
	 * //"insert into b_requestlog(rid,raddr,rtime,rip,rclientid,descr) values(:rid,:raddr,:rtime,:rip,:rcid,:descr)";
	 * @param m
	 */
	void requstAudit(Map<String,Object> m){
		Map<String,Object> a_m=new HashMap<String,Object>();
		a_m.put("rid", isEmpty(m.get(RET_REQUEST_ID))?raudit.getRequestID():m.get(RET_REQUEST_ID));
		a_m.put("raddr", m.get(OAPI_REQUEST_ADDR));
		a_m.put("rtime", m.get(OAPI_REQUEST_TIMESTAMP));
		a_m.put("rip", m.get(OAPI_REQUEST_IP));
		a_m.put("rcid", m.get(OAPI_ACCESS_KEY));
		a_m.put("descr", m.get(RET_MESSAGE));
		raudit.saveRequestAudit(a_m);
	}

	public void setRaudit(RequestAudit raudit) {
		this.raudit = raudit;
	}

	public void setVerify(RequestVerifyService verify) {
		this.verify = verify;
	}

	public void setDataStore(WebDataStore dataStore) {
		this.dataStore = dataStore;
	}

	/*public void setApiUrlReg(String apiUrlReg) {
		this.apiUrlReg = apiUrlReg;
	}

	private String apiUrlReg="^/api/\\w+?/\\w+?/v\\d/[\\w.]*$";*/
	private RequestAudit raudit;
	private RequestVerifyService verify;
	private WebDataStore dataStore;
	static final ObjectMapper objectMapper = new ObjectMapper();
	private static Logger log = Logger.getLogger(OApiIntercept.class);
	
}
