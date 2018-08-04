package org.ares.app.sys.coapi.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ares.app.common.oapi.client.mapsrv.OApiMapService;
import org.ares.app.sys.coapi.service.OApiDataAccess;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/coapi")
public class OApiClientAction {
	
	@RequestMapping("{path}")
	public @ResponseBody Map<String, Object> oapi(@PathVariable String path,HttpServletRequest req){
		Map<String,String[]> reqParam= req.getParameterMap();
		String out_path=OAPI_PREFIX+path;
		Map<String,Object> r=oada.getAPIDataByPath(out_path, reqParam);
		return r;
	}
	
	@RequestMapping("/refresh_cache")
	public @ResponseBody Map<String, Object> refreshOapiCache(){
		Map<String,Object> r=new HashMap<String,Object>();
		service.refreshAddrMapInCache();
		return r;
	}
	
	@RequestMapping("/use_cache")
	public @ResponseBody Map<String, Object> useCache(){
		Map<String,Object> r=new HashMap<String,Object>();
		service.setUseCache(true);
		return r;
	}
	
	@RequestMapping("/ip_addr")
	public @ResponseBody Map<String, Object> ipaddr(HttpServletRequest req){
		Map<String,Object> r=new HashMap<String,Object>();
		r.put("rip", req.getRemoteAddr());
		return r;
	}
	
	@RequestMapping("/use_db")
	public @ResponseBody Map<String, Object> useDB(){
		Map<String,Object> r=new HashMap<String,Object>();
		service.setUseCache(false);
		return r;
	}
	
	
	public void setService(OApiMapService service) {
		this.service = service;
	}

	public void setOada(OApiDataAccess oada) {
		this.oada = oada;
	}

	static final String OAPI_PREFIX="/oapi/";
	//private static Logger log = Logger.getLogger(OApiSrvAction.class);
	private OApiMapService service;
	private OApiDataAccess oada;
	
}
