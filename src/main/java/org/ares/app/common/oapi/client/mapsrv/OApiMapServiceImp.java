package org.ares.app.common.oapi.client.mapsrv;

import static org.ares.app.common.conf.Params.OAPI_ACCESS_KEY;
import static org.ares.app.common.conf.Params.OAPI_SERVICE_URL;
import static org.ares.app.common.conf.Params.OAPI_SERVICE_NAME;
import static org.ares.app.common.conf.Params.OAPI_REQUEST_IP;
import static org.ares.app.common.conf.Params.OAPI_REQUEST_TIMESTAMP;
import static org.ares.app.common.conf.Params.OAPI_SECRET_KEY;
import static org.ares.app.common.conf.Params.OAPI_SIGN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.ares.app.common.encr.SignGenerator;
import org.ares.app.common.oapi.client.ip.IPService;
import org.ares.app.common.oapi.client.model.OApiAddrMapModel;
import org.ares.app.common.oapi.time.TimeService;

public class OApiMapServiceImp implements OApiMapService {

	@PostConstruct
	public void init(){
		refreshAddrMapInCache();
	}

	@Override
	public void refreshAddrMapInCache() {
		map_caches=omapQuery.queryAllOApiMap();
	}
	
	@Override
	public List<Map<String, String>> getInnerAddrByOuterAddr(String outeraddr, Map<String, String[]> param) {
		List<Map<String, String>> r=new ArrayList<Map<String, String>>();
		List<OApiAddrMapModel> inners=findCtoSMap(outeraddr);
		String ip_addr=ipService.getLocalIP();
		log.debug("[openapi client IP:]-"+ip_addr);
		for(OApiAddrMapModel inner :inners){
			Map<String, String> addrmap=new HashMap<String,String>();
			Map<String,Object> params=new HashMap<String,Object>();
			params.put(OAPI_ACCESS_KEY, inner.getConsid());
			params.put(OAPI_REQUEST_IP, ip_addr);
			params.put(OAPI_REQUEST_TIMESTAMP, timeService.getServerUnixTime());
			params.put(OAPI_SECRET_KEY, inner.getSeckey());
			String sign=signGenerator.serverSign(params);
			addrmap.put(OAPI_SIGN, sign);
			addrmap.put(OAPI_SERVICE_URL, inner.getIaddr());
			addrmap.put(OAPI_SERVICE_NAME, inner.getIname());
			r.add(addrmap);
		}
		return r;
	}
	
	/**
	 * the map must contail below key
	 * OAPI_ACCESS_KEY
	 * OAPI_REQUEST_IP
	 * OAPI_REQUEST_TIMESTAMP
	 * OAPI_SECRET_KEY
	 * OAPI_SERVICE_URL
	 * OAPI_SERVICE_NAME
	 * @param param
	 * @return
	 */
	public Map<String, String> buildOApiServiceParam(Map<String, Object> param) {
		Map<String, String> addrmap=new HashMap<String,String>();
		String ip_addr=ipService.getLocalIP();
		param.put(OAPI_REQUEST_IP, ip_addr);
		param.put(OAPI_REQUEST_TIMESTAMP, timeService.getServerUnixTime());
		log.debug("[openapi client param]--"+param);
		String sign=signGenerator.serverSign(param);
		addrmap.put(OAPI_SIGN, sign);
		addrmap.put(OAPI_SERVICE_URL, param.get(OAPI_SERVICE_URL)+"");
		addrmap.put(OAPI_SERVICE_NAME, param.get(OAPI_SERVICE_NAME)+"");
		return addrmap;
	}
	
	
	List<OApiAddrMapModel> findCtoSMap(String outeraddr){
		List<OApiAddrMapModel> r=null;
		if(useCache)
			r=map_caches.get(outeraddr);
		else
			r=omapQuery.queryOApiMapByClient(outeraddr).get(outeraddr);
		return r;
	}
	
	@Override
	public boolean isUseCache() {
		return useCache;
	}

	public void setUseCache(boolean useCache) {
		this.useCache = useCache;
	}

	public void setOmapQuery(OApiMapQuery omapQuery) {
		this.omapQuery = omapQuery;
	}

	public void setTimeService(TimeService timeService) {
		this.timeService = timeService;
	}

	public void setIpService(IPService ipService) {
		this.ipService = ipService;
	}

	public void setSignGenerator(SignGenerator signGenerator) {
		this.signGenerator = signGenerator;
	}

	private static Logger log = Logger.getLogger(OApiMapServiceImp.class);
	private Map<String,List<OApiAddrMapModel>> map_caches;
	private boolean useCache;
	private OApiMapQuery omapQuery;
	private TimeService timeService;
	private IPService ipService;
	private SignGenerator signGenerator;
	
}
