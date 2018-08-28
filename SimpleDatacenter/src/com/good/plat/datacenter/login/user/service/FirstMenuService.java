package com.good.plat.datacenter.login.user.service;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.login.user.entity.FirstMenu;

public interface FirstMenuService {
	
	/**
	 * 
	 * @Title: findFirstMenuList
	 * @Description: 获取FirstMenu列表
	 * @return
	 * @throws Exception
	 * List<FirstMenu>
	 * @author dmw
	 * @date 2016年3月1日 下午2:46:01
	 */
	public List<FirstMenu> findFirstMenuList() throws Exception;
	
	/**
	 * 
	 * @Title: findMenuListByUserId
	 * @Description: 根据用户ID查询
	 * @param userId
	 * @return
	 * @throws Exception
	 * List<FirstMenu>
	 * @author dmw
	 * @date 2016年3月1日 下午2:46:06
	 */
	public List<FirstMenu> findMenuListByUserId(Integer userId) throws Exception;
	
	/**
	 * 
	 * @Title: findMenuListByRoles
	 * @Description: 根据用户的角色列表查询权限
	 * @param roleIds
	 * @return
	 * @throws Exception
	 * List<FirstMenu>
	 * @author dmw
	 * @date 2016年3月1日 下午2:46:09
	 */
	public List<FirstMenu> findMenuListByRoles(List<Integer> roleIds) throws Exception;
	
	/**
	 * 
	 * @Title: findFirstMenuById
	 * @Description: 根据ID查询
	 * @param id
	 * @return
	 * @throws Exception
	 * FirstMenu
	 * @author dmw
	 * @date 2016年3月1日 下午2:46:13
	 */
	public FirstMenu findFirstMenuById(Integer id) throws Exception;
	
	/**
	 * 
	 * @Title: findFirstMenuByName
	 * @Description: 根据名称查询
	 * @param name
	 * @return
	 * @throws Exception
	 * FirstMenu
	 * @author dmw
	 * @date 2016年3月1日 下午2:46:17
	 */
	public FirstMenu findFirstMenuByName(String name) throws Exception;
	
	/**
	 * 
	 * @Title: addFirstMenu
	 * @Description: 添加
	 * @param menu
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年3月1日 下午2:46:20
	 */
	public BaseMessage addFirstMenu(FirstMenu menu) throws Exception;
	
	/**
	 * 
	 * @Title: deleteFirstMenu
	 * @Description:  删除
	 * @param id
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年3月1日 下午2:46:23
	 */
	public BaseMessage deleteFirstMenu(Integer id) throws Exception;
	
	/**
	 * @Title: editFirstMenu
	 * @Description: 修改
	 * @param menu
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年3月1日 下午2:46:27
	 */
	public BaseMessage editFirstMenu(FirstMenu menu) throws Exception;

	List<FirstMenu> firstUserMenuListByPlatId(BaseSearchData baseSearchData) throws Exception;
}
