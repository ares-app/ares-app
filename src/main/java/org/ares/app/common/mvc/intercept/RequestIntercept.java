package org.ares.app.common.mvc.intercept;

import static org.ares.app.common.util.BeanCopy.copyProperties;
import static org.ares.app.common.util.BeanCopy.fillBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ares.app.common.advice.datastore.WebDataStore;
import org.ares.app.common.conf.Params;
import org.ares.app.common.page.PageModel;
import org.ares.app.common.security.servlet.user.UserLogin;
import org.ares.app.common.security.servlet.user.UserModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestIntercept extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		
		dataStore.clear();
		dataStore.saveRequest(request);
		
		PageModel page = new PageModel();
		fillBean(request, page);
		dataStore.savePage(page);
		
		if(request.getSession().getAttribute(Params.SESSION_LOGIN_USER)==null){
			UserModel user=null;
			//no use spring security
			if(SecurityContextHolder.getContext().getAuthentication()!=null){
				Object prc=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if(!(prc instanceof String)){
					UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					String userid=userDetails.getUsername();
					user=loginUserService.getUser(userid);
					copyProperties(user, loginUser);
				}
			}
			request.getSession().setAttribute(Params.SESSION_LOGIN_USER, user);
		}
		return super.preHandle(request, response, handler);
	}
	
	public void setLoginUserService(UserLogin loginUserService) {
		this.loginUserService = loginUserService;
	}

	public void setDataStore(WebDataStore dataStore) {
		this.dataStore = dataStore;
	}
	
	public void setLoginUser(UserModel loginUser) {
		this.loginUser = loginUser;
	}

	private WebDataStore dataStore;
	private UserLogin loginUserService;
	private UserModel loginUser;
	
}
