package org.ares.app.common.advice;

import static org.ares.app.common.conf.Params.JSON_DATAGRID_PAGE;
import static org.ares.app.common.conf.Params.JSP_ERROR_CAUSE;
import static org.ares.app.common.conf.Params.JSP_MESSAGE;
import static org.ares.app.common.conf.Params.JSP_OPER_SUCCESS;
import static org.ares.app.common.conf.Params.OAPI_SERVICE_MESSAGE;
import static org.ares.app.common.conf.Params.RETURN_SUCCESS;
import static org.ares.app.common.conf.Params.RET_CODE;
import static org.ares.app.common.conf.Params.RET_HTTP_PROCESS_ERROR;
import static org.ares.app.common.conf.Params.RET_MESSAGE;
import static org.ares.app.common.conf.Params.RET_PROCESS_RIGHT;
import static org.ares.app.common.util.Util.isEmpty;
import static org.ares.app.common.util.Util.isNotEmpty;
import static org.ares.app.common.util.BeanCopy.copyProperties;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.ares.app.common.advice.datastore.WebDataStore;
import org.ares.app.common.exception.AppSysException;
import org.ares.app.common.exception.BizLogicException;
import org.ares.app.common.page.PageModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.client.HttpClientErrorException;

public class WebCtrlMethodAdvice {

	public Object actionAroundHandle(ProceedingJoinPoint pjp){
		Object r=null;
		Map<String,Object> m=null;
		try{
			r=pjp.proceed();
			if(r instanceof Map){
				@SuppressWarnings("unchecked")
				Map<String,Object> t=(Map<String, Object>)r;
				t.put(RETURN_SUCCESS, true);
				if(isEmpty(t.get(RET_CODE)))
				t.put(RET_CODE, RET_PROCESS_RIGHT);
			}
		}catch(BizLogicException e){
			if(r==null || r instanceof Map){
				m=new HashMap<String,Object>();
				m.put(RET_CODE, e.getRetCode());
				m.put(RETURN_SUCCESS, false);
				m.put(RET_MESSAGE, dataStore.get(OAPI_SERVICE_MESSAGE));
				r=m;
			}
			log.info(e.getMessage());
		}catch(AppSysException e){
			if(r==null || r instanceof Map){
				m=new HashMap<String,Object>();
				m.put(RET_CODE, e.getRetCode());
				m.put(RET_MESSAGE, dataStore.get(OAPI_SERVICE_MESSAGE));
				m.put(RETURN_SUCCESS, false);
				r=m;
			}
			log.info(e.getMessage());
			e.printStackTrace();
		}catch(HttpClientErrorException e){
			if(r==null || r instanceof Map){
				m=new HashMap<String,Object>();
				m.put(RET_CODE, RET_HTTP_PROCESS_ERROR);
				m.put(RET_MESSAGE, e.getMessage());
				m.put(RETURN_SUCCESS, false);
				r=m;
			}
			log.info(e.getMessage());
		}
		catch(Throwable e){
			log.info(e.getMessage());
			e.printStackTrace();
		}finally{
			if(useJspMsg){
				dataStore.getCurrentRequest().getSession().setAttribute(JSP_MESSAGE, dataStore.get(JSP_MESSAGE));
				dataStore.getCurrentRequest().getSession().setAttribute(JSP_OPER_SUCCESS, dataStore.get(JSP_OPER_SUCCESS));
				dataStore.getCurrentRequest().getSession().setAttribute(JSP_ERROR_CAUSE, dataStore.get(JSP_ERROR_CAUSE));
			}
		}
		return r;
	}
	
	public void actionAfterHandle(Map<String, Object> r) {
		if (null == r) {
			log.info("action result is null!return new a HashMap!");
			r = new HashMap<String, Object>();
		}
		PageModel page = new PageModel();// clear old request info=>new
		PageModel xpage = dataStore.getCurrentPage();
		if(xpage!=null){
			copyProperties(xpage, page);
			r.put(JSON_DATAGRID_PAGE, page);
		}
		if (isEmpty(r.get(RET_MESSAGE))&&isNotEmpty(dataStore.get(OAPI_SERVICE_MESSAGE)))
			r.put(RET_MESSAGE, dataStore.get(OAPI_SERVICE_MESSAGE));
		if (isEmpty(r.get(RET_CODE)))
			r.put(RET_CODE, dataStore.get(RET_CODE));
	}
	
	public void setDataStore(WebDataStore dataStore) {
		this.dataStore = dataStore;
	}

	public void setUseJspMsg(boolean useJspMsg) {
		this.useJspMsg = useJspMsg;
	}

	private boolean useJspMsg=false;
	private WebDataStore dataStore;
	private static Logger log = Logger.getLogger(WebCtrlMethodAdvice.class);
}
