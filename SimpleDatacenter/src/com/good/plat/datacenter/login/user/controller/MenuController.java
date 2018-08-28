package com.good.plat.datacenter.login.user.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.login.service.impl.LoginServiceImpl;
import com.good.plat.datacenter.login.user.entity.FirstMenu;
import com.good.plat.datacenter.login.user.entity.SecondMenu;
import com.good.plat.datacenter.login.user.service.impl.FirstMenuServiceImpl;
import com.good.plat.datacenter.login.user.service.impl.SecondMenuServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{

	public MenuController() {
	}
	
	@Autowired 
	private FirstMenuServiceImpl firstMenuService;
	
	@Autowired
	private SecondMenuServiceImpl secondMenuService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	
	@ResponseBody
	@RequestMapping(value="/firstMenuList", method={RequestMethod.POST, RequestMethod.GET})
	public List<FirstMenu> firstMenuList() throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "firstMenuList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<FirstMenu> menuList = firstMenuService.findFirstMenuList();
		return menuList;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/getMenuList", method={RequestMethod.POST, RequestMethod.GET})
	public List<FirstMenu> getMenuList(@RequestParam Integer userId) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "getMenuList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, userId);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<FirstMenu> menuList = firstMenuService.findMenuListByUserId(userId);
		return menuList;
	}

	@ResponseBody
	@RequestMapping(value="/findFirstMenu", method={RequestMethod.POST, RequestMethod.GET})
	public FirstMenu findFirstMenu(@RequestBody FirstMenu menu) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "findFirstMenu";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, menu);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return firstMenuService.findFirstMenuById(menu.getId());
	}
	
	@ResponseBody
	@RequestMapping(value="/addFirstMenu", method={RequestMethod.POST, RequestMethod.GET})
	public BaseMessage addFirstMenu(@Validated @RequestBody FirstMenu menu, 
			BindingResult result) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "addFirstMenu";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, menu);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = new BaseMessage();
		if (result.hasErrors()) {
			mess.setStatus(0);
			mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return mess;
		} else {
			mess = firstMenuService.addFirstMenu(menu);
		}

		return mess;
	}
	
	@ResponseBody
	@RequestMapping(value="/editFirstMenu", method={RequestMethod.POST, RequestMethod.GET})
	public BaseMessage editFirstMenu(@Validated @RequestBody FirstMenu menu, 
			BindingResult result) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "editFirstMenu";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, menu);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = new BaseMessage();
		if (result.hasErrors()) {
			mess.setStatus(0);
			mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return mess;
		} else {
			mess = firstMenuService.editFirstMenu(menu);
		}

		return mess;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteFirstMenu", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseMessage deleteFirstMenu(@RequestBody FirstMenu menu) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "deleteFirstMenu";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, menu);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = firstMenuService.deleteFirstMenu(menu.getId());
		return mess;
	}
	
	@ResponseBody
	@RequestMapping(value="/secondMenuList", method={RequestMethod.POST, RequestMethod.GET})
	public List<SecondMenu> secondMenuList(@RequestBody SecondMenu menu) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "secondMenuList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, menu);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<SecondMenu> menuList = secondMenuService.findSecondMenuList(menu);
		return menuList;
	}
	
	@ResponseBody
	@RequestMapping(value="/getPermissionList", method={RequestMethod.POST, RequestMethod.GET})
	public List<SecondMenu> getPermissionList(@RequestBody SecondMenu menu) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "getPermissionList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, menu);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<SecondMenu> menuList = secondMenuService.findPermission(menu);
		return menuList;
	}
	
	@ResponseBody
	@RequestMapping(value="/findSecondMenu", method={RequestMethod.POST, RequestMethod.GET})
	public SecondMenu findSecondMenu(@RequestBody SecondMenu menu) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "findSecondMenu";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, menu);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return secondMenuService.findSecondMenuById(menu.getId());
	}
	
	
	@ResponseBody
	@RequestMapping(value="/addSecondMenu", method={RequestMethod.POST, RequestMethod.GET})
	public BaseMessage addSecondMenu(@Validated @RequestBody SecondMenu menu, 
			BindingResult result) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "addSecondMenu";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, menu);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = new BaseMessage();
		if (result.hasErrors()) {
			mess.setStatus(0);
			mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return mess;
		} else {
			mess = secondMenuService.addSecondMenu(menu);
		}

		return mess;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteSecondMenu", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseMessage deleteSecondMenu(@RequestBody SecondMenu menu) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "deleteSecondMenu";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, menu);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = secondMenuService.deleteSecondMenu(menu.getId());
		return mess;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/editSecondMenu", method={RequestMethod.POST, RequestMethod.GET})
	public BaseMessage editSecondMenu(@Validated @RequestBody SecondMenu menu, 
			BindingResult result) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "editSecondMenu";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, menu);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = new BaseMessage();
		if (result.hasErrors()) {
			mess.setStatus(0);
			mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return mess;
		} else {
			mess = secondMenuService.editSecondMenu(menu);
		}

		return mess;
	}
	
	@ResponseBody
	@RequestMapping(value="/firstUserMenuListByPlatId", method={RequestMethod.POST, RequestMethod.GET})
	public List<FirstMenu> firstUserMenuListByPlatId(@RequestBody BaseSearchData baseSearchData,HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "firstUserMenuListByPlatId";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null);
		sysAccessLogService.saveAccessLog(accessLog);
		
		//
		int userid = LoginServiceImpl.getUserID(session);
		baseSearchData.setChecktype1(userid + "");
		int platid = Integer.valueOf(baseSearchData.getChecktype3());
		List<FirstMenu> menuList = firstMenuService.firstUserMenuListByPlatId(baseSearchData);
		return menuList;
	}
}
