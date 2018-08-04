package org.ares.app.common.security.rbac.custom;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ares.app.common.security.rbac.model.SResource;
import org.ares.app.common.security.rbac.model.SRole;
import org.ares.app.common.security.rbac.model.SUser;

public interface UserFuncFactory {
	
	void refreshAuths();

	SUser getUser(String userid);

	SUser getUser(String userid, String password);
	
	SUser refreshUser(String userid);

	List<SRole> getRoles(String userid);

	List<SResource> getRes(String userid);

	List<SUser> getAllUsers();

	List<SRole> getAllRoles();

	List<SResource> getAllRes();
	
	Map<String, Set<String>> getUrlAuthorities(String userid);
	
	void setImmediate(boolean imd);
}
