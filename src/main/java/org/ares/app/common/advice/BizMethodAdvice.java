package org.ares.app.common.advice;

import static org.ares.app.common.conf.Params.FAILURE;
import static org.ares.app.common.conf.Params.JSP_ERROR_CAUSE;
import static org.ares.app.common.conf.Params.JSP_MESSAGE;
import static org.ares.app.common.conf.Params.JSP_OPER_SUCCESS;
import static org.ares.app.common.conf.Params.OAPI_SERVICE_MESSAGE;
import static org.ares.app.common.conf.Params.OPER_FAILED;
import static org.ares.app.common.conf.Params.OPER_SUCCESS;
import static org.ares.app.common.conf.Params.RET_CODE;
import static org.ares.app.common.conf.Params.RET_PROCESS_RIGHT;
import static org.ares.app.common.conf.Params.RET_SYS_PROCESS_ERROR;
import static org.ares.app.common.conf.Params.SERVICE_FAIL_CAUSE;
import static org.ares.app.common.conf.Params.SERVICE_MESSAGE;
import static org.ares.app.common.conf.Params.SERVICE_NAME;
import static org.ares.app.common.conf.Params.SERVICE_SUCCESS;
import static org.ares.app.common.conf.Params.SUCCESS;
import static org.ares.app.common.util.Util.isNotEmpty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.ares.app.common.advice.datastore.WebDataStore;
import org.ares.app.common.exception.AppSysException;
import org.ares.app.common.exception.BizLogicException;
import org.ares.app.common.page.PageModel;
import org.ares.app.common.page.PageQuery;
import org.ares.app.common.security.data.DataSecurity;
import org.ares.app.common.sign.DataAuth;
import org.ares.app.common.sign.OperMessage;
import org.ares.app.common.sign.Page;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

@SuppressWarnings("unchecked")
public class BizMethodAdvice {

	public Object sqla2DataAuthHandle(ProceedingJoinPoint pjp,DataAuth sign){
		Object result=null;
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		if(sign==null||!sign.value())
			return result;
		
		result=dataSecurity.getQL(result+"");
		log.info("[data auth sql]:"+result);
		return result;
	}
	
	public Object sqla1PageHandle(ProceedingJoinPoint pjp,Page sign){
		Object result=null;
		String source_sql=null;
		int total=0;
		try {
			result = pjp.proceed();
			source_sql=(String)result;
			if(sign==null||!sign.value())
				return result;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		PageModel pm = dataStore.getCurrentPage();
		if(pm==null)
			return result;
		
		pageQuery.setPage(pm);
		if(pjp.getArgs().length>0){
			Object params=pjp.getArgs()[0];
			if(params instanceof Object[]&&source_sql.indexOf("?")>0)
				total=pageQuery.getCount(source_sql, (Object[])params);
			else if(params instanceof Map&&source_sql.indexOf(":")>0)
				total=pageQuery.getCount(source_sql, ((Map<String,Object>)params));
			else
				total =pageQuery.getCount(source_sql);
		}else
			total =pageQuery.getCount(source_sql);

		pm.setPageSize(sign.size());
		pm.refresh(total);
		dataStore.savePage(pm);
		result=pageQuery.getSQL(result+"");
		log.debug("[page sql]:"+result);
		return result;
	}
	
	public void serviceNameBHandle(JoinPoint pjp,Object obj,OperMessage sign) {
		if(sign!=null)
			dataStore.put(SERVICE_NAME, sign.value());
	}
	
	public Object serviceNameHandle(ProceedingJoinPoint pjp,OperMessage sign) {
		if(sign!=null)
			dataStore.put(SERVICE_NAME, sign.value());
		Object result=null;
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Object serviceExceptionHandle(ProceedingJoinPoint pjp) throws Throwable {
		String method=pjp.getSignature().getName();
		log.debug("service mehtod--["+method+"] start at "+sdf.format(new Date())+"...");
		Object tempServiceName=dataStore.get(SERVICE_NAME);
		String serviceName=method;
		if(tempServiceName!=null)
			serviceName=tempServiceName+"";
		String message = serviceName + OPER_FAILED;
		Object result = null;
		
		Boolean success = FAILURE;
		String errorCause = null;
		String oapi_service_message=null;
		
		try {
			result = pjp.proceed();
			message = serviceName==null?method:serviceName + OPER_SUCCESS;
			success = SUCCESS;
			dataStore.put(SERVICE_SUCCESS, success);
			dataStore.put(RET_CODE, RET_PROCESS_RIGHT);
		} catch (BizLogicException ce) {
			errorCause = ce.getMessage();
			message = message + ":" + errorCause;// errorCause
			oapi_service_message = errorCause;
			log.error(message);
			throw ce;
		}catch (Throwable e) {
			errorCause = e.getMessage();
			message = message + ":" + errorCause;
			oapi_service_message = errorCause;
			log.error(message);
			e.printStackTrace();
			throw new AppSysException(RET_SYS_PROCESS_ERROR,message);
		} finally {
			dataStore.put(SERVICE_SUCCESS, success);
			dataStore.put(SERVICE_MESSAGE, message);
			dataStore.put(SERVICE_FAIL_CAUSE, errorCause);
			if(isNotEmpty(oapi_service_message))
				dataStore.put(OAPI_SERVICE_MESSAGE, oapi_service_message);
			
			dataStore.put(JSP_MESSAGE, message);//jsp_message
			dataStore.put(JSP_OPER_SUCCESS, message);
			dataStore.put(JSP_ERROR_CAUSE, success);
			log.info(message);
			log.debug("service mehtod--["+method+" end at "+sdf.format(new Date())+"...");
		}
		return result;
	}

	public void setDataStore(WebDataStore dataStore) {
		this.dataStore = dataStore;
	}

	public void setDataSecurity(DataSecurity ds) {
		this.dataSecurity = ds;
	}

	public void setPageQuery(PageQuery pq) {
		this.pageQuery = pq;
	}	

	private static Logger log = Logger.getLogger(BizMethodAdvice.class);
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private WebDataStore dataStore;
	private DataSecurity dataSecurity;
	private PageQuery pageQuery;
}
