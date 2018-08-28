package com.good.plat.datacenter.login.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.game.entity.Channel;
import com.good.plat.datacenter.game.entity.Game;
import com.good.plat.datacenter.game.entity.Project;
import com.good.plat.datacenter.login.service.impl.LoginServiceImpl;
import com.good.plat.datacenter.login.user.entity.FirstMenu;
import com.good.plat.datacenter.login.user.entity.User;
import com.good.plat.datacenter.login.user.service.impl.FirstMenuServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

@Controller
public class LoginController extends BaseController{

	public LoginController() {
	}
	
	@Autowired 
	private LoginServiceImpl loginService;
	
	@Autowired 
	private FirstMenuServiceImpl firstMenuService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	
	@ResponseBody
	@RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseMessage login(@RequestBody User user, 
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) throws Exception {
		BaseMessage bm = loginService.login(user, request, response, session); 
		if(bm != null && bm.getStatus() != null && bm.getStatus().equals(1)){
			
			//log
			Date opDate = new Date();
			sysAccessLogService.log(request, getClass(), "login", opDate, user);
			//
			
		}
		return bm;
	}
	
	@ResponseBody
	@RequestMapping(value = "/logout", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseMessage logout(HttpServletRequest request, HttpSession session) throws Exception {
		
		return loginService.logout(request, session);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/editPassword", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseMessage editPassword(@RequestBody User user, HttpServletRequest request, 
			HttpSession session) throws Exception {
		
		return loginService.editPassword(user, request, session);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUsername", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseMessage getUsername(HttpSession session) throws Exception {
		
		return loginService.getUserName(session);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getMenu", method={RequestMethod.POST, RequestMethod.GET})
	public List<FirstMenu> getMenu(HttpSession session, 
			HttpServletResponse response,@RequestBody BaseSearchData baseSearchData) throws Exception {
		
		return loginService.getMenu(session, response,baseSearchData);
	}
	
	@ResponseBody
	@RequestMapping(value="/getUserProjectList", method={RequestMethod.POST, RequestMethod.GET})
	public List<Project> getUserProjectList(HttpSession session, 
			HttpServletResponse response) throws Exception {
		
		return loginService.getUserProjectList(session, response);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getUserGameList", method={RequestMethod.POST, RequestMethod.GET})
	public List<Game> getUserGameList(HttpSession session, 
			HttpServletResponse response) throws Exception {
		
		return loginService.getUserGameList(session, response);
	}
	
	@ResponseBody
	@RequestMapping(value="/getUserChannelList", method={RequestMethod.POST, RequestMethod.GET})
	public List<Channel> getUserChannelList(HttpSession session, 
			HttpServletResponse response) throws Exception {
		
		return loginService.getUserChannelList(session, response);
	}
	
	
}
