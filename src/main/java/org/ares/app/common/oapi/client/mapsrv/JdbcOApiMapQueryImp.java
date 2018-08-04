package org.ares.app.common.oapi.client.mapsrv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ares.app.common.oapi.client.model.OApiAddrMapModel;
import org.ares.app.common.oapi.client.model.OApiClientModel;
import org.ares.app.common.oapi.client.model.OApiCtoSModel;
import org.ares.app.common.oapi.client.model.OApiServerModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class JdbcOApiMapQueryImp implements OApiMapQuery {

	@Override
	public List<OApiServerModel> queryOApiServers() {
		return npjt.query(sql_server, new BeanPropertyRowMapper<OApiServerModel>(OApiServerModel.class));
	}

	@Override
	public List<OApiClientModel> queryOApiClients() {
		return npjt.query(sql_client, new BeanPropertyRowMapper<OApiClientModel>(OApiClientModel.class));
	}

	@Override
	public List<OApiCtoSModel> queryOApiCtoS() {
		return npjt.query(sql_cmaps, new BeanPropertyRowMapper<OApiCtoSModel>(OApiCtoSModel.class));
	}
	
	@Override
	public Map<String, List<OApiAddrMapModel>> queryOApiMapByClient(String oaddr) {
		Map<String,String> p=new HashMap<String,String>();
		p.put("oaddr", oaddr);
		Map<String,List<OApiAddrMapModel>> r=new HashMap<String,List<OApiAddrMapModel>>();
		List<OApiAddrMapModel> l=npjt.query(sql_mapbycid, p, new BeanPropertyRowMapper<OApiAddrMapModel>(OApiAddrMapModel.class));
		r.put(oaddr, l);
		return r;
	}

	@Override
	public Map<String,List<OApiAddrMapModel>> queryAllOApiMap() {
		Map<String,List<OApiAddrMapModel>> r=new HashMap<String,List<OApiAddrMapModel>>();
		List<OApiAddrMapModel> l=npjt.query(sql_cmapsinfo, new BeanPropertyRowMapper<OApiAddrMapModel>(OApiAddrMapModel.class));
		String mk="ly";
		List<OApiAddrMapModel> lo=null;
		for(OApiAddrMapModel m:l){
			if(!mk.equals(m.getOaddr())){
				lo =new ArrayList<OApiAddrMapModel>();
				r.put(m.getOaddr(), lo);
			}
			lo.add(m);
			mk=m.getOaddr();
		}
		return r;
	}
	
	public void setNpjt(NamedParameterJdbcTemplate npjt) {
		this.npjt = npjt;
	}

	public void setSql_cmapsinfo(String sql_cmapsinfo) {
		this.sql_cmapsinfo = sql_cmapsinfo;
	}

	public void setSql_mapbycid(String sql_mapbycid) {
		this.sql_mapbycid = sql_mapbycid;
	}

	public void setSql_cmaps(String sql_cmaps) {
		this.sql_cmaps = sql_cmaps;
	}

	public void setSql_client(String sql_client) {
		this.sql_client = sql_client;
	}

	public void setSql_server(String sql_server) {
		this.sql_server = sql_server;
	}

	private NamedParameterJdbcTemplate npjt;
	private String sql_cmapsinfo;//query all map client to server
	private String sql_mapbycid;//query map by client id
	private String sql_cmaps;// client id => server id
	private String sql_client;//client addrs
	private String sql_server;//server addrs
	
}
