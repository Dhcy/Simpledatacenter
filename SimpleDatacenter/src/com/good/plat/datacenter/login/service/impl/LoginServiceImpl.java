package com.good.plat.datacenter.login.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlAdchannel;
import com.good.plat.datacenter.datastat.service.buyplat.IBuyPlatService;
import com.good.plat.datacenter.game.entity.Channel;
import com.good.plat.datacenter.game.entity.Game;
import com.good.plat.datacenter.game.entity.Project;
import com.good.plat.datacenter.game.entity.TBLogplatSysPlat;
import com.good.plat.datacenter.game.mapper.ChannelMapper;
import com.good.plat.datacenter.game.mapper.GameMapper;
import com.good.plat.datacenter.game.mapper.ProjectMapper;
import com.good.plat.datacenter.game.service.PlatProjectService;
import com.good.plat.datacenter.login.service.LoginService;
import com.good.plat.datacenter.login.user.entity.FirstMenu;
import com.good.plat.datacenter.login.user.entity.User;
import com.good.plat.datacenter.login.user.mapper.FirstMenuMapper;
import com.good.plat.datacenter.login.user.service.ResourceCommon;
import com.good.plat.datacenter.login.user.service.impl.UserServiceImpl;

@Service(value="loginService")
public class LoginServiceImpl implements LoginService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	public LoginServiceImpl() {
	}
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private FirstMenuMapper firstMenuMapper;
	
	@Autowired
	private ProjectMapper projectMapper;
	
	@Autowired
	private GameMapper gameMapper;
	
	@Autowired
	private ChannelMapper channelMapper;
	
	@Autowired
	private PlatProjectService platProjectService;
	@Autowired
	private IBuyPlatService buyPlatService; 
	public static Integer getUserID(HttpSession session){
		return (Integer)getSessionObj(session,"USERID");
	}
	
	public static String getSessionUserName(HttpSession session){
		return (String)getSessionObj(session,"USERNAME");
	}
	
	public static List<TBLogplatMlchnlAdchannel> getSessionAdvertiseChannel(HttpSession session){
		return (List<TBLogplatMlchnlAdchannel>)getSessionObj(session,ResourceCommon.CACHE_SESSION_NAME_OF_ADVERTISE_CHANNEL);
	}
	
	public static Object getSessionObj(HttpSession session,String key){
		return session.getAttribute(key);
	}
	
	/**
	 * 用户登录
	 */
	@Override
	public BaseMessage login(User user, 
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		BaseMessage mess = new BaseMessage();
		
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(
					user.getUsername(),user.getPassword());
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.login(token);
			
			Iterator<?> its = currentUser.getPrincipals().fromRealm("ShiroDataBaseRealm").iterator();
	        if (its.hasNext() == false) {
	            throw new AuthorizationException("找不到User啊，登录失败？");
	        }
	        User loginUser = (User) its.next();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			logger.info("用户<"+loginUser.getUsername()+">在"+sdf.format(new Date())+"登录成功."+
			"IP: "+request.getRemoteHost());
			
			//先缓存USERID
			session.setAttribute("USERID", loginUser.getId());
			session.setAttribute("USERNAME", loginUser.getUsername());
			session.setAttribute("REALNAME", loginUser.getRealname());
			session.setAttribute("LOGINDATE", loginUser.getLogindate());
			
			List<Project> projectList = getUserProjectList(session, response);
			List<Game> gameList = getUserGameList(session, response);
			List<Channel> channelList = getUserChannelList(session, response);
			List<TBLogplatSysPlat> platformList = getUserPlatformList(session, response);
			List<TBLogplatMlchnlAdchannel> adchannelList = getUserAdvertiseChannelList(session,response);
			session.setAttribute(ResourceCommon.CACHE_SESSION_NAME_OF_PROJECT, projectList);
			session.setAttribute(ResourceCommon.CACHE_SESSION_NAME_OF_GAME, gameList);
			session.setAttribute(ResourceCommon.CACHE_SESSION_NAME_OF_CHANNEL, channelList);
			session.setAttribute(ResourceCommon.CACHE_SESSION_NAME_OF_PLATFORM, platformList);
			session.setAttribute(ResourceCommon.CACHE_SESSION_NAME_OF_ADVERTISE_CHANNEL, adchannelList);
			mess.setStatus(1);
			mess.setMessage("登录成功");
			return mess;
		} catch (Exception e) {
			logger.info("用户登录错误:"+e.getMessage());
			mess.setStatus(0);
			mess.setMessage("用户登录错误:请联系管理员");
			return mess;
		}
	}
	
	@Override
	public List<TBLogplatMlchnlAdchannel> getUserAdvertiseChannelList(HttpSession session,
			HttpServletResponse response) throws Exception {
		List<TBLogplatMlchnlAdchannel> adchannelList = null;
		if (session.getAttribute("USERID") == null) {
			response.setStatus(500);
			return adchannelList;
		} else {
			//先从缓存中拿
			if (session.getAttribute(ResourceCommon.CACHE_SESSION_NAME_OF_ADVERTISE_CHANNEL) != null) {
				adchannelList = (List<TBLogplatMlchnlAdchannel>) session.getAttribute(ResourceCommon.CACHE_SESSION_NAME_OF_ADVERTISE_CHANNEL);
			} else {
				Integer userid = (Integer) session.getAttribute("USERID");
				String userName = (String) session.getAttribute("USERNAME");
				BaseSearchData searchData = new BaseSearchData();
				searchData.setUserid(userid);
				adchannelList = buyPlatService.selectUserAdchannelList(searchData);
				session.setAttribute(ResourceCommon.CACHE_SESSION_NAME_OF_ADVERTISE_CHANNEL, adchannelList);
				return adchannelList;
			}
		}
		return adchannelList;
	}

	/**
	 * 用户退出
	 */
	@Override
	public BaseMessage logout(HttpServletRequest request, 
			HttpSession session) throws Exception {
		Subject currentUser = SecurityUtils.getSubject();
		logger.info("用户<"+session.getAttribute("USERNAME")+">退出平台." 
				+"IP: "+request.getRemoteHost());	
		
		currentUser.logout();
		
		//session.invalidate();
		return null;
	}

	
	/**
	 * 修改密码
	 */
	@Override
	public BaseMessage editPassword(User user, HttpServletRequest request, 
			HttpSession session) throws Exception {
		BaseMessage mess = new BaseMessage();
		if (session.getAttribute("USERID") == null) {
			mess.setStatus(2);
			mess.setMessage("请先登录");
		} else {
			int userid = (Integer) session.getAttribute("USERID");
			user.setId(userid);
			mess = userService.editPassword(user);
			
			logger.info("用户<"+session.getAttribute("USERNAME")+">修改密码成功");
		}
		
		return mess;
	}

	
	/**
	 * 获取用户名
	 */
	@Override
	public BaseMessage getUserName(HttpSession session) throws Exception {
		BaseMessage mess = new BaseMessage();
		if (session.getAttribute("REALNAME") == null) {
			mess.setStatus(0);
			mess.setMessage("请先登录");
		} else {
			mess.setStatus(1);
			mess.setMessage((String)session.getAttribute("REALNAME"));
		}
		return mess;
	}

	
	/**
	 * 获取用户权限菜单
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FirstMenu> getMenu(HttpSession session,
			HttpServletResponse response,BaseSearchData baseSearchData) throws Exception {
		baseSearchData.searchDataFilter(baseSearchData);
		//
		if (session.getAttribute("USERID") == null) {
			response.setStatus(500);
			return null;
		}else if(baseSearchData == null || baseSearchData.getGameid() == null ){
			response.setStatus(500);
			return null;
		} else {
			//先从缓存中拿
			if (session.getAttribute("GAME_MENULIST_" + baseSearchData.getGameid()) != null) {
				return (List<FirstMenu>) session.getAttribute("GAME_MENULIST_" + baseSearchData.getGameid());
			} else {
				Integer userid = (Integer) session.getAttribute("USERID");
				//List<FirstMenu> menuList = firstMenuMapper.selectMenuListByUserId(userid);
				//return menuList;
				//
				List<Game> glist = getUserGameList(session, response);
				if(glist != null){
					for(Game g : glist){
						if(g != null && baseSearchData.getGameid().equals(g.getId()) ){
							int menuType = parseMenuTypeByGameType(g.getGameType().intValue());
							baseSearchData.setChecktype1(userid+"");
							baseSearchData.setChecktype2(menuType+"");
							List<FirstMenu> menuList = firstMenuMapper.selectMenuListByUserIdAndMenuType(baseSearchData);
							session.setAttribute("GAME_MENULIST_" + baseSearchData.getGameid(), menuList);
							return menuList;
						}
					}
				}
			}
			return null;
		}
	}
	
	private int parseMenuTypeByGameType(int gameType){
		int menuType = -1;
		switch(gameType){
		case 0:
			menuType = 0;
			break;
		case 1://单机版菜单
			menuType = 1;
			break;
		case 2://联网版菜单
			menuType = 2;
			break;
		default :
			break;
		}
		return menuType;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getUserProjectList(HttpSession session,
			HttpServletResponse response) throws Exception {
		if (session.getAttribute("USERID") == null) {
			response.setStatus(500);
			return null;
		} else {
			//先从缓存中拿
			if (session.getAttribute(ResourceCommon.CACHE_SESSION_NAME_OF_PROJECT) != null) {
				return (List<Project>) session.getAttribute(ResourceCommon.CACHE_SESSION_NAME_OF_PROJECT);
			} else {
				Integer userid = (Integer) session.getAttribute("USERID");
				String userName = (String) session.getAttribute("USERNAME");
				//超级管理员获取所以权限
				if (userName.equals("GMAdmin")) {
					List<Project> projectList = projectMapper.selectProjectList();
					session.setAttribute(ResourceCommon.CACHE_SESSION_NAME_OF_PROJECT, projectList);
					return projectList;
				} else {
					List<Project> projectList = projectMapper.selectProjectByUserid(userid);
					session.setAttribute(ResourceCommon.CACHE_SESSION_NAME_OF_PROJECT, projectList);
					return projectList;
				}
			}
			
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Game> getUserGameList(HttpSession session,
			HttpServletResponse response) throws Exception {
		if (session.getAttribute("USERID") == null) {
			response.setStatus(500);
			return null;
		} else {
			//先从缓存中拿
			if (session.getAttribute("RESOURCE_GAME") != null) {
				return (List<Game>) session.getAttribute("RESOURCE_GAME");
			} else {
				Integer userid = (Integer) session.getAttribute("USERID");
				String userName = (String) session.getAttribute("USERNAME");
				//超级管理员获取所以权限
				if (userName.equals("GMAdmin")) {
					List<Game> gameList = gameMapper.selectGameListByProjectId(-1);
					session.setAttribute("RESOURCE_GAME", gameList);
					return gameList;
				} else {
					List<Game> gameList = gameMapper.selectGameByUserid(userid);
					session.setAttribute("RESOURCE_GAME", gameList);
					return gameList;
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Channel> getUserChannelList(HttpSession session,
			HttpServletResponse response) throws Exception {
		if (session.getAttribute("USERID") == null) {
			response.setStatus(500);
			return null;
		} else {
			//先从缓存中拿
			if (session.getAttribute("RESOURCE_CHANNEL") != null) {
				return (List<Channel>) session.getAttribute("RESOURCE_CHANNEL");
			} else {
				Integer userid = (Integer) session.getAttribute("USERID");
				String userName = (String) session.getAttribute("USERNAME");
				//超级管理员获取所以权限
				if (userName.equals("GMAdmin")) {
					List<Channel> channelList = channelMapper.selectChannelSubChannelByChannelId(-1);
					session.setAttribute("RESOURCE_CHANNEL", channelList);
					return channelList;
				} else {
					List<Channel> channelList = channelMapper.selectChannelByUserid(userid);
					session.setAttribute("RESOURCE_CHANNEL", channelList);
					return channelList;
				}
			}
		}
	}

	@Override
	public List<TBLogplatSysPlat> getUserPlatformList(HttpSession session,
			HttpServletResponse response) throws Exception {
		List<TBLogplatSysPlat> platformList = null;
		if (session.getAttribute("USERID") == null) {
			response.setStatus(500);
			return platformList;
		} else {
			//先从缓存中拿
			if (session.getAttribute("RESOURCE_PLATFORM") != null) {
				platformList = (List<TBLogplatSysPlat>) session.getAttribute("RESOURCE_PLATFORM");
			} else {
				Integer userid = (Integer) session.getAttribute("USERID");
				String userName = (String) session.getAttribute("USERNAME");
				platformList = platProjectService.selectUserPlatList(userid);
				session.setAttribute("RESOURCE_PLATFORM", platformList);
				return platformList;
			}
		}
		return platformList;
	}
}
