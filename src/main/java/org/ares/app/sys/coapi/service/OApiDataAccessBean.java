package org.ares.app.sys.coapi.service;

import static org.ares.app.common.conf.Params.OAPI_MSG_NOT_OAPI_SERVICE;
import static org.ares.app.common.conf.Params.OAPI_MSG_NOT_SERVICE;
import static org.ares.app.common.conf.Params.OAPI_SERVICE_NAME;
import static org.ares.app.common.conf.Params.OAPI_SERVICE_URL;
import static org.ares.app.common.conf.Params.OAPI_SIGN;
import static org.ares.app.common.conf.Params.RETURN_SUCCESS;
import static org.ares.app.common.conf.Params.RET_CODE;
import static org.ares.app.common.conf.Params.RET_MESSAGE;
import static org.ares.app.common.conf.Params.RET_NOT_INNER_SERVICE;
import static org.ares.app.common.conf.Params.RET_NOT_SERVICE;
import static org.ares.app.common.util.Util.cloneMap;
import static org.ares.app.common.util.Util.getGETSTR;
import static org.ares.app.common.util.Util.getNaturalValueMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.ares.app.common.advice.datastore.WebDataStore;
import org.ares.app.common.oapi.client.mapsrv.OApiMapService;
import org.springframework.web.client.RestTemplate;


@SuppressWarnings("unchecked")
public class OApiDataAccessBean implements OApiDataAccess {
	
	@Override
	public Map<String, Object> getAPIDataByPath(String out_path,Map<String, String[]> reqParam) {
		Map<String,Object> r=new HashMap<String,Object>();
		List<Map<String,String>> lm=service.getInnerAddrByOuterAddr(out_path, reqParam);
		if(lm.size()==0){
			r.put(RET_CODE, RET_NOT_SERVICE);
			r.put(RET_MESSAGE, OAPI_MSG_NOT_SERVICE);
			return r;
		}
		for(Map<String,String> m:lm){
			Map<String,String> param=getNaturalValueMap(reqParam);
			String url=m.get(OAPI_SERVICE_URL);
			String sign=m.get(OAPI_SIGN);
			param.put(OAPI_SIGN, sign);

			Map<String,String[]> nParam=cloneMap(reqParam);
			nParam.put(OAPI_SIGN, new String[]{sign});
			url=url+getGETSTR(nParam);
			log.debug("url is:"+url);
			Map<String,Object> lr=rest.postForObject(url, null, Map.class);
			if(lr==null){
				lr=new HashMap<String,Object>();
				lr.put(RET_CODE, RET_NOT_INNER_SERVICE);
				lr.put(RET_MESSAGE, OAPI_MSG_NOT_OAPI_SERVICE);
				r.put(RETURN_SUCCESS, false);
			}
			r.put(m.get(OAPI_SERVICE_NAME), lr);
		}
		return r;
	}

	@Override
	public Map<String, Object> getAPIDataByCurPath() {
		String out_path=dataStore.getCurrentRequest().getRequestURI()
				.substring(dataStore.getCurrentRequest().getContextPath().length()).replaceAll("\\.\\w+$", "");
		return getAPIDataByPath(out_path);
	}
	
	@Override
	public Map<String, Object> getAPIDataByPath(String out_path) {
		return getAPIDataByPath(out_path,dataStore.getCurrentRequest().getParameterMap());
	}
	
	@Override
	public Map<String, Object> getAPIDataByInId(String[] in_id,Map<String, String[]> reqParam) {
		return null;
	}

	private static Logger log = Logger.getLogger(OApiDataAccessBean.class);
	@Inject private OApiMapService service;
	@Inject private RestTemplate rest;
	@Inject private WebDataStore dataStore;
	
}
