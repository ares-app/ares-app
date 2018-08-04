package org.ares.app.common.security.spring.vote;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import org.ares.app.common.security.rbac.custom.LazyUserFuncFactory;
import org.ares.app.common.security.rbac.model.SResource;
import org.ares.app.common.security.rbac.model.SUser;

@SuppressWarnings({"rawtypes"})
public class AccessDecisionVoterImpl implements AccessDecisionVoter {

	@Override
	public boolean supports(ConfigAttribute atr) {
		return true;
	}

	@Override
	public boolean supports(Class clazz) {
		return true;
	}

	@Override
	public int vote(Authentication auth, Object obj, Collection col) {
		int result = ACCESS_ABSTAIN;
		if (!(obj instanceof FilterInvocation))
			return result;
		FilterInvocation invo = (FilterInvocation) obj;
		String url = invo.getRequestUrl();// 当前请求的URL
		String userid = auth.getName();
		SUser u=uf.getUserInCache(userid);
		if(u==null)
			return result;
		
		result=match(u,url)?ACCESS_GRANTED:ACCESS_ABSTAIN; //15-11-26 update
		if(result!=ACCESS_GRANTED)
			log.info("user '"+userid+"' access '"+url+"' is refused!");
		return result;
	}
	
	boolean match(SUser u,String url){
		boolean r=false;
		List<SResource> res=u.getRes();
		for(SResource m:res){
			String au=m.getResurl();
			r = pathMatcher.match(au, url);
			if(r){
				break;
			}
		}
		return r;
	}

	public void setUf(LazyUserFuncFactory uf) {
		this.uf = uf;
	}

	private static final PathMatcher pathMatcher = new AntPathMatcher();
	private LazyUserFuncFactory uf;//15-11-26 update UserFuncFactory => LazyUserFuncFactory
	private static Logger log = Logger.getLogger(AccessDecisionVoterImpl.class);
	
}
