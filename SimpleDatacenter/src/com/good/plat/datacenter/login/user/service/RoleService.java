package com.good.plat.datacenter.login.user.service;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.login.user.entity.Role;

public interface RoleService {
	public List<Role> findRoleList() throws Exception;
	
	public Role findRole(Role role) throws Exception;
	
	public BaseMessage addRole(Role role) throws Exception;
	
	public BaseMessage deleteRole(Role role) throws Exception;
	
	public Role editRoleUI(Role role) throws Exception;
	
	public BaseMessage editRole(Role role) throws Exception;
	
}
