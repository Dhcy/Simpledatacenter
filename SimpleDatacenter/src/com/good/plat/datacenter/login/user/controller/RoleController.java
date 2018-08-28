package com.good.plat.datacenter.login.user.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.login.user.entity.Role;
import com.good.plat.datacenter.login.user.service.impl.RoleServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{

	public RoleController() {
	}
	
	@Autowired
	private RoleServiceImpl roleService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	
	
	@ResponseBody
	@RequestMapping(value="/roleList", method={RequestMethod.POST, RequestMethod.GET})
	public List<Role> roleList() throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "roleList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<Role> roleList = roleService.findRoleList();
		return roleList;
	}
	
	@ResponseBody
	@RequestMapping(value="/addRole", method={RequestMethod.POST, RequestMethod.GET})
	public BaseMessage addRole(@Validated @RequestBody Role role, 
			BindingResult result) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "addRole";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, role);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = new BaseMessage();
		if (result.hasErrors()) {
			mess.setStatus(0);
			mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return mess;
		} else {
			mess = roleService.addRole(role);
		}

		return mess;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/deleteRole", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseMessage deleteRole(@RequestBody Role role) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "deleteRole";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, role);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = roleService.deleteRole(role);
		return mess;
	}
	
	@ResponseBody
	@RequestMapping(value = "/editRoleUI", method = {RequestMethod.POST, RequestMethod.GET})
	public Role editRoleUI(@RequestBody Role role) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "editRoleUI";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, role);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		Role roleUI = roleService.editRoleUI(role);
		return roleUI;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/editRole", method={RequestMethod.POST, RequestMethod.GET})
	public BaseMessage editRole(@Validated @RequestBody Role role, 
			BindingResult result) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "editRole";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, role);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = new BaseMessage();
		if (result.hasErrors()) {
			mess.setStatus(0);
			mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return mess;
		} else {
			mess = roleService.editRole(role);
		}

		return mess;
	}
	
}
