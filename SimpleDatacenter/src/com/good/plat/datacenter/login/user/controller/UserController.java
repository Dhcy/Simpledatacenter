package com.good.plat.datacenter.login.user.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlAdchannel;
import com.good.plat.datacenter.datastat.service.buyplat.IBuyPlatService;
import com.good.plat.datacenter.login.service.impl.LoginServiceImpl;
import com.good.plat.datacenter.login.user.entity.User;
import com.good.plat.datacenter.login.user.entity.UserResource;
import com.good.plat.datacenter.login.user.service.ResourceCommon;
import com.good.plat.datacenter.login.user.service.impl.UserServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	public UserController() {
	}

	@Autowired
	private UserServiceImpl userService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	//
	
	@Autowired
	private IBuyPlatService buyPlatService;
	@Autowired
	private LoginServiceImpl loginService;
	@ResponseBody
	@RequestMapping(value="/userList", method={RequestMethod.POST, RequestMethod.GET})
	public List<User> userList(@RequestBody User user) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "userList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, user);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<User> userList = userService.userList(user);
		
		return userList;
	}
	
	@ResponseBody
	@RequestMapping(value="/addUser", method={RequestMethod.POST, RequestMethod.GET})
	public BaseMessage addUser(@Validated @RequestBody User user, 
			BindingResult result) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "addUser";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, user);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = new BaseMessage();
		if (result.hasErrors()) {
			mess.setStatus(0);
			mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return mess;
		} else {
			mess = userService.addUser(user);
		}

		return mess;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/deleteUser", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseMessage deleteUser(@RequestBody User user) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "deleteUser";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, user);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = userService.deleteUser(user);
		return mess;
	}
	
	@ResponseBody
	@RequestMapping(value = "/editUserUI", method = {RequestMethod.POST, RequestMethod.GET})
	public User editUserUI(@RequestBody User user) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "editUserUI";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, user);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		User userUI = userService.editUserUI(user);
		return userUI;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/editUser", method={RequestMethod.POST, RequestMethod.GET})
	public BaseMessage editUser(@Validated @RequestBody User user, 
			BindingResult result) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "editUser";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, user);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = new BaseMessage();
		if (result.hasErrors()) {
			mess.setStatus(0);
			mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return mess;
		} else {
			mess = userService.editUser(user);
		}

		return mess;
	}
	
	@ResponseBody
	@RequestMapping(value = "/editUserResourceUI", method = {RequestMethod.POST, RequestMethod.GET})
	public UserResource editUserResourceUI(@RequestBody User user, HttpSession session,HttpServletResponse response) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "editUserResourceUI";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, user);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		UserResource userResource = userService.editUserResourceUI(user, session,response);
		return userResource;
	}
	
	/**
	 * 更新用户资源
	 * @Title: editUserResource
	 * @Description: TODO
	 * @param userResource
	 * @param session
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年6月21日 下午4:08:12
	 */
	@ResponseBody
	@RequestMapping(value="/editUserResource", method={RequestMethod.POST, RequestMethod.GET})
	public BaseMessage editUserResource(@RequestBody UserResource userResource,HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "editUserResource";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, userResource);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = new BaseMessage();
		User user = userResource.getUser();
		mess = userService.editUserResource(userResource);
		String username = loginService.getSessionUserName(session);
		if ("GMAdmin".equals(username)) {
			session.setAttribute(ResourceCommon.CACHE_SESSION_NAME_OF_PROJECT,null);
			session.setAttribute(ResourceCommon.CACHE_SESSION_NAME_OF_GAME,null);
			session.setAttribute(ResourceCommon.CACHE_SESSION_NAME_OF_CHANNEL,null);
			session.setAttribute(ResourceCommon.CACHE_SESSION_NAME_OF_PLATFORM,null);
			session.setAttribute(ResourceCommon.CACHE_SESSION_NAME_OF_ADVERTISE_CHANNEL,null);
		}
		return mess;
	}
}
