package com.good.plat.datacenter.login.user.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.login.user.entity.User;
import com.good.plat.datacenter.login.user.entity.UserResource;

public interface UserService {
	/**
	 * @Title: userList
	 * @Description: 获取用户列表
	 * @return
	 * @throws Exception
	 * List<User>
	 * @author dmw
	 * @date 2016年2月29日 上午10:23:26
	 */
	public List<User> userList(User user) throws Exception;
	
	/**
	 * @Title: findUser
	 * @Description: 查找用户
	 * @param user
	 * @return
	 * @throws Exception
	 * User
	 * @author dmw
	 * @date 2016年2月29日 上午10:23:47
	 */
	public User findUser(User user) throws Exception;
	
	/**
	 * @Title: addUser
	 * @Description: TODO
	 * @param user
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年2月29日 上午10:24:29
	 */
	public BaseMessage addUser(User user) throws Exception;
	
	/**
	 * @Title: deleteUser
	 * @Description: 删除用户
	 * @param user
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年2月29日 上午10:25:01
	 */
	public BaseMessage deleteUser(User user) throws Exception;
	
	/**
	 * @Title: editUserUI
	 * @Description: 更新用户时回显数据
	 * @param user
	 * @return
	 * @throws Exception
	 * User
	 * @author dmw
	 * @date 2016年2月29日 上午10:25:26
	 */
	public User editUserUI(User user) throws Exception;
	
	/**
	 * @Title: updataUser
	 * @Description: 更新用户，不更新用户角色
	 * @param user
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年3月1日 下午10:32:39
	 */
	public BaseMessage updataUser(User user) throws Exception;
	
	/**
	 * @Title: editUser
	 * @Description: 更新用户
	 * @param user
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年2月29日 上午10:25:58
	 */
	public BaseMessage editUser(User user) throws Exception;
	
	/**
	 * @Title: editUserResourceUI
	 * @Description: 获取用户资源
	 * @param user
	 * @return
	 * @throws Exception
	 * UserResource
	 * @author dmw
	 * @date 2016年2月29日 上午10:26:08
	 */
	public UserResource editUserResourceUI(User user, 
			HttpSession session,HttpServletResponse response) throws Exception;
	
	/**
	 * @Title: editUserResource
	 * @Description: 修改用户资源
	 * @param userResource
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年2月29日 上午10:26:27
	 */
	public BaseMessage editUserResource(UserResource userResource) throws Exception; 

	
	/**
	 * @Title: editPassword
	 * @Description: 修改密码
	 * @param user
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年2月29日 上午10:26:43
	 */
	public BaseMessage editPassword(User user) throws Exception;
}
