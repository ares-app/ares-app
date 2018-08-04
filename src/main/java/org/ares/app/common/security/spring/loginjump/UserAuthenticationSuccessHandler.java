package org.ares.app.common.security.spring.loginjump;

import static org.ares.app.common.conf.Params.JWT_LOGIN;
import static org.ares.app.common.conf.Params.LOGIN_JUMP_PATH;
import static org.ares.app.common.util.Util.isEmpty;
import static org.ares.app.common.util.Util.isNotEmpty;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import org.ares.app.common.exception.AppSysException;
import org.ares.app.common.jwt.Claims;
import org.ares.app.common.jwt.JwtMessage;

public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication auth) throws IOException,ServletException {
		checkParameter();
		String mobile=request.getParameter(mobileflag);
		String login=request.getParameter("login");
		String msg=null;
		boolean isMobile=false;
		if(isNotEmpty(mobile))
			isMobile=true;
		if(isMobile){
			PrintWriter out=response.getWriter();
			UserDetails ud = (UserDetails)auth.getPrincipal();
			String userid=ud.getUsername();
			
			if(!JWT_LOGIN.equals(login)){
				List<JwtMessage> list_jwt=new ArrayList<JwtMessage>();
				JwtMessage jwtm=new JwtMessage(true);
				jwtm.setAudience(userid);
				jwtm.upExpire();
				jwtm.setValidstart(System.currentTimeMillis());
				list_jwt.add(jwtm);
				jwtm=new JwtMessage(false);
				jwtm.setValidstart(System.currentTimeMillis());
				jwtm.setAudience(userid);
				jwtm.upExpire();
				list_jwt.add(jwtm);
				msg=claims.resultJson(list_jwt);
				System.out.println(msg);
			}else{
				msg="{\"msg\":\"jwt_login\",\"success\":true}";
			}
			
			out.write(msg);//re write
			out.flush();
		}
		else{
			String url = (String)request.getSession().getAttribute(LOGIN_JUMP_PATH);
            request.getSession().removeAttribute(LOGIN_JUMP_PATH);
            if(isEmpty(url))
            	url=request.getContextPath()+loginsuccessjump;
            response.sendRedirect(url);
		}
	}
	
	void checkParameter(){
		if(isEmpty(loginsuccessjump))
			throw new AppSysException("loginurl is empty!");
	}

	
	public void setMobileflag(String mobileflag) {
		this.mobileflag = mobileflag;
	}

	public void setLoginsuccessjump(String loginsuccessjump) {
		this.loginsuccessjump = loginsuccessjump;
	}

	private String mobileflag="mobile";//设置移动端的登录参数标识
	private String loginsuccessjump;
	@Inject private Claims claims;
}
