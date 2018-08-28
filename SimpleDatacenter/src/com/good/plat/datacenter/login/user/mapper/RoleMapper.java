package com.good.plat.datacenter.login.user.mapper;

import java.util.List;

import com.good.plat.datacenter.login.user.entity.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);
    
    int insertRoleMenu(Role role);

    Role selectByPrimaryKey(Integer id);
    
    Role selectByRole(Role role);
    
    Role updateRoleUI(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> selectRoleList();
    
    //删除tb_logplat_role_permission里关联的roleid数据
    int deleteRoleMenuByRole(Role role);
}