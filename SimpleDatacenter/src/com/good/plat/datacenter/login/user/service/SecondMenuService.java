package com.good.plat.datacenter.login.user.service;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.login.user.entity.SecondMenu;

/**
 * @ClassName: SecondMenuService
 * @Description: 2级菜单
 * @author dmw
 * @date 2016年3月1日 下午2:37:46
 */
public interface SecondMenuService {
	
	//获取全部权限列表
	public List<SecondMenu> findPermission(SecondMenu menu) throws Exception;
	/**
	 * @Title: findSecondMenuList
	 * @Description: 获取权限列表,firstId为-1或者null查询全部
	 * @param menu
	 * @return
	 * @throws Exception
	 * List<SecondMenu>
	 * @author dmw
	 * @date 2016年3月1日 下午2:39:34
	 */
	public List<SecondMenu> findSecondMenuList(SecondMenu menu) throws Exception;
	
	/**
	 * @Title: findSecondMenuListByUser
	 * @Description: 根据用户Id获取权限列表
	 * @param menu
	 * @return
	 * @throws Exception
	 * List<SecondMenu>
	 * @author dmw
	 * @date 2016年3月1日 下午2:39:34
	 */
	public List<SecondMenu> findSecondMenuListByUserID(Integer userid) throws Exception;
	
	/**
	 * @Title: findSecondMenuById
	 * @Description: 根据ID获取
	 * @param id
	 * @return
	 * @throws Exception
	 * SecondMenu
	 * @author dmw
	 * @date 2016年3月1日 下午2:43:24
	 */
	public SecondMenu findSecondMenuById(Integer id) throws Exception;
	
	/**
	 * @Title: findSecondMenuByMenu
	 * @Description: 根据条件查询SecondMenu
	 * @param menu
	 * @return
	 * @throws Exception
	 * SecondMenu
	 * @author dmw
	 * @date 2016年3月1日 下午2:43:56
	 */
	public SecondMenu findSecondMenuByMenu(SecondMenu menu) throws Exception;
	
	/**
	 * @Title: addSecondMenu
	 * @Description: 添加SecondMenu
	 * @param menu
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年3月1日 下午2:44:16
	 */
	public BaseMessage addSecondMenu(SecondMenu menu) throws Exception;
	
	/**
	 * @Title: deleteSecondMenu
	 * @Description: 删除
	 * @param id
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年3月1日 下午2:44:39
	 */
	public BaseMessage deleteSecondMenu(Integer id) throws Exception;
	
	/**
	 * @Title: editSecondMenu
	 * @Description: 修改
	 * @param menu
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年3月1日 下午2:44:50
	 */
	public BaseMessage editSecondMenu(SecondMenu menu) throws Exception;
}
