package com.good.plat.datacenter.login.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlAdchannel;
import com.good.plat.datacenter.game.entity.Channel;
import com.good.plat.datacenter.game.entity.Game;
import com.good.plat.datacenter.game.entity.Project;
import com.good.plat.datacenter.game.entity.TBLogplatSysPlat;
import com.good.plat.datacenter.login.user.entity.FirstMenu;
import com.good.plat.datacenter.login.user.entity.User;


/**
 * @ClassName: LoginService
 * @Description: 登录接口
 * @author dmw
 * @date 2016年3月1日 上午10:31:48
 */
public interface LoginService {
	
	/**
	 * @Title: login
	 * @Description: 用户登录
	 * @param user
	 * @param session
	 * @param request
	 * @param response
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年3月1日 上午10:32:09
	 */
	public BaseMessage login(User user, 
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session) throws Exception; 

	/**
	 * @Title: logout
	 * @Description: 用户登出
	 * @param user
	 * @param session
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年3月1日 上午10:32:21
	 */
	public BaseMessage logout(HttpServletRequest request, 
			HttpSession session) throws Exception;
	
	/**
	 * @Title: editPassword
	 * @Description: 修改密码
	 * @param session
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年3月3日 下午5:20:34
	 */
	public BaseMessage editPassword(User user, HttpServletRequest request, 
			HttpSession session) throws Exception;
	
	/**
	 * @Title: getUserName
	 * @Description: 获取用户名
	 * @param session
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年3月7日 下午3:08:05
	 */
	public BaseMessage getUserName(HttpSession session) throws Exception;


	/**
	 * @Title: getMenu
	 * @Description: 获取用户菜单
	 * @param session
	 * @param response
	 * @throws Exception
	 * List<FirstMenu>
	 * @author dmw
	 * @date 2016年3月7日 下午3:09:42
	 */
	public List<FirstMenu> getMenu(HttpSession session, 
			HttpServletResponse response,BaseSearchData baseSearchData) throws Exception;
	
	
	/**
	 * @Title: getUserProjectList
	 * @Description: 获取用户项目权限
	 * @param session
	 * @param response
	 * @throws Exception
	 * List<Project>
	 * @author dmw
	 * @date 2016年3月7日 下午3:12:57
	 */
	public List<Project> getUserProjectList(HttpSession session, 
			HttpServletResponse response) throws Exception;
	
	
	/**
	 * @Title: getUserGameList
	 * @Description: 获取用户游戏权限
	 * @param session
	 * @param response
	 * @throws Exception
	 * List<Game>
	 * @author dmw
	 * @date 2016年3月7日 下午3:13:18
	 */
	public List<Game> getUserGameList(HttpSession session, 
			HttpServletResponse response) throws Exception;
	
	
	/**
	 * @Title: getUserChannelList
	 * @Description: 获取用户渠道权限
	 * @param session
	 * @param response
	 * @throws Exception
	 * List<Channel>
	 * @author dmw
	 * @date 2016年3月7日 下午3:13:47
	 */
	public List<Channel> getUserChannelList(HttpSession session, 
			HttpServletResponse response) throws Exception;

	List<TBLogplatSysPlat> getUserPlatformList(HttpSession session, HttpServletResponse response) throws Exception;
	List<TBLogplatMlchnlAdchannel> getUserAdvertiseChannelList(HttpSession session,
			HttpServletResponse response) throws Exception;
	
}
