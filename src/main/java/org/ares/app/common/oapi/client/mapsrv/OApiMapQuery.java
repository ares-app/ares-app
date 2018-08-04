package org.ares.app.common.oapi.client.mapsrv;

import java.util.List;
import java.util.Map;

import org.ares.app.common.oapi.client.model.OApiAddrMapModel;
import org.ares.app.common.oapi.client.model.OApiClientModel;
import org.ares.app.common.oapi.client.model.OApiCtoSModel;
import org.ares.app.common.oapi.client.model.OApiServerModel;

public interface OApiMapQuery {

	List<OApiClientModel> queryOApiClients();
	List<OApiCtoSModel> queryOApiCtoS();
	List<OApiServerModel> queryOApiServers();
	
	Map<String,List<OApiAddrMapModel>> queryOApiMapByClient(String oaddr);
	Map<String,List<OApiAddrMapModel>> queryAllOApiMap();
	
}
