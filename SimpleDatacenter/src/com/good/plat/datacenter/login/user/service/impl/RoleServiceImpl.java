package com.good.plat.datacenter.login.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.login.user.entity.Role;
import com.good.plat.datacenter.login.user.mapper.RoleMapper;
import com.good.plat.datacenter.login.user.service.RoleService;

@Service(value="roleService")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	public RoleServiceImpl() {
	}

	@Override
	public List<Role> findRoleList() throws Exception {
		
		List<Role> roleList = roleMapper.selectRoleList();
		return roleList;
	}

	@Override
	public Role findRole(Role role) throws Exception {
		return roleMapper.selectByRole(role);
	}

	@Override
	public BaseMessage addRole(Role role) throws Exception {
		BaseMessage mess = new BaseMessage();
		Role role2 = roleMapper.selectByRole(role);
		if (role2 != null) {
			mess.setStatus(0);
			mess.setMessage("添加失败:已有相同名称的角色");
			return mess;
		}
		
		int status1 = roleMapper.insertSelective(role);
		role.setId(role.getId());
		
		int status2 = roleMapper.insertRoleMenu(role);
		
		//System.out.println(role.getId());
		if (status1 != 0 && status2 != 0) {
			mess.setStatus(1);
			mess.setMessage("添加成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("添加失败");
		}
		
		return mess;
	}

	@Override
	public BaseMessage deleteRole(Role role) throws Exception {
		BaseMessage mess = new BaseMessage();
		int status = roleMapper.deleteByPrimaryKey(role.getId());
		if (status == 1) {
			mess.setStatus(status);
			mess.setMessage("删除成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("删除失败");
		}
		return mess;
	}
	
	
	@Override
	public Role editRoleUI(Role role) throws Exception {
		
		return roleMapper.updateRoleUI(role.getId());
	}


	@Override
	public BaseMessage editRole(Role role) throws Exception {
		
		int status1 = roleMapper.updateByPrimaryKeySelective(role);
		roleMapper.deleteRoleMenuByRole(role);
		int status3 = roleMapper.insertRoleMenu(role);
		
		BaseMessage mess = new BaseMessage();
		if (status1 != 0 && status3 != 0) {
			mess.setStatus(1);
			mess.setMessage("更新成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("更新失败");
		}
		
		return mess;
	}

}
