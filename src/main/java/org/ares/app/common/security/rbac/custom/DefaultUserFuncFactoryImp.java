package org.ares.app.common.security.rbac.custom;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ares.app.common.security.rbac.model.SResource;
import org.ares.app.common.security.rbac.model.SRole;
import org.ares.app.common.security.rbac.model.SUser;

public class DefaultUserFuncFactoryImp implements UserFuncFactory {

	@Override
	public void refreshAuths() {
		

	}

	@Override
	public SUser getUser(String userid) {
		
		return null;
	}

	@Override
	public SUser getUser(String userid, String password) {
		
		return null;
	}

	@Override
	public List<SRole> getRoles(String userid) {
		
		return null;
	}

	@Override
	public List<SResource> getRes(String userid) {
		
		return null;
	}

	@Override
	public List<SUser> getAllUsers() {
		
		return null;
	}

	@Override
	public List<SRole> getAllRoles() {
		
		return null;
	}

	@Override
	public List<SResource> getAllRes() {
		
		return null;
	}

	@Override
	public Map<String, Set<String>> getUrlAuthorities(String userid) {
		
		return null;
	}

	@Override
	public void setImmediate(boolean imd) {
		
	}

	@Override
	public SUser refreshUser(String userid) {
		return null;
	}

}
