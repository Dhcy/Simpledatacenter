package com.good.plat.datacenter.login.user.mapper;

import java.util.List;

import com.good.plat.datacenter.login.user.entity.User;
import com.good.plat.datacenter.login.user.entity.UserResource;

public interface UserMapper {
	//添加用户
	int insert(User record);
	
	int insertSelective(User record);
	
	//用户-角色关联表添加数据
	int insertUserRole(User user);
	
	//删除用户
    int deleteByPrimaryKey(Integer id);
    
    //删除用户-角色关联表数据
    int deleteUserRole(User user);

    //根据用户ID查询用户
    User selectByPrimaryKey(Integer id);
    
    //根据用户名查询
    User selectUserByUser(User user);
    
    //根据查询条件查询
    List<User> selectUserListByUser(User user);
    
    //更新用户
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    //用于修改用户时回显数据
    User updateUserUI(User user);
    
    //删除用户-资源表当前用户数据
    int deleteUserResource(UserResource userResource);
    
    //用于修改用户资源时回显数据
    List<UserResource> updateUserResourceUI(User user);
    
    //更新用户资源
    int updateUserResource(User user);
    
    //修改密码
    //int updatePassword(User user);
    
}