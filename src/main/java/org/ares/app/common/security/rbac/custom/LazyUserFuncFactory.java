package org.ares.app.common.security.rbac.custom;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ares.app.common.security.rbac.model.SRole;
import org.ares.app.common.security.rbac.model.SUser;

public interface LazyUserFuncFactory {
	
	void addUserToCacheFromDB(String userid);
	
	void removeUserInCache(String userid);
	
	SUser getUserInCache(String userid);

	SUser getUserInDB(String userid);
	
	SUser getUserInCacheOrDB(String userid);
	
	void refreshUserInCache(String userid);

	List<SRole> getRolesOfUserInCache(String userid);

	Map<String, Set<String>> getUrlAuthorities(String userid);
	
}
