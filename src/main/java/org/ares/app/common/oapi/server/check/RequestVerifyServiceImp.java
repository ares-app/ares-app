package org.ares.app.common.oapi.server.check;

import java.util.List;
import java.util.Map;

import org.ares.app.common.conf.Params;
import org.ares.app.common.oapi.server.verify.OApiSrvVerify;

public class RequestVerifyServiceImp implements RequestVerifyService {

	@Override
	public Map<String,Object> check(Map<String, Object> params) {
		Map<String,Object> r=null;
		for(OApiSrvVerify v:verifys){
			r=v.check(params);
			if((Integer)r.get(Params.RET_CODE)!=0)
				break;
		}
		return r;
	}
	
	public void setVerifys(List<OApiSrvVerify> verifys) {
		this.verifys = verifys;
	}

	private List<OApiSrvVerify> verifys;

}
