package com.good.plat.datacenter.game.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.good.plat.datacenter.game.entity.Project;
import com.good.plat.datacenter.game.entity.TBLogplatSysPlat;
import com.good.plat.datacenter.game.service.PlatProjectService;
import com.good.plat.datacenter.game.service.impl.PlatProjectServiceImpl;
import com.good.plat.datacenter.game.service.impl.ProjectServiceImpl;
import com.good.plat.datacenter.login.service.impl.LoginServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: PlatController
 * @Description: 平台管理
 * @author moxw
 * @date 2016年6月16日 上午11:18:32
 */

@Controller
@RequestMapping("/platproject")
public class PlatProjectController extends BaseController{

	@Autowired
	private PlatProjectServiceImpl platProjectService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	
	
	/**
	 * 
	 * @Title: selectPlatList
	 * @Description: 查询平台列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * List<TBLogplatSysPlat>
	 * @author moxw
	 * @date 2016年6月16日 下午4:18:31
	 */
	@RequestMapping(value = "/selectPlatList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<TBLogplatSysPlat> selectPlatList(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPlatList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<TBLogplatSysPlat> list = platProjectService.selectPlatList();
		
		return list;
	}

	@RequestMapping(value = "/selectProjectByPlatID", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Project> selectProjectByPlatID(Integer pid,HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectProjectByPlatID";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<Project> list = platProjectService.selectProjectByPlatID(pid);
		
		return list;
	}
	
	@RequestMapping(value = "/addPlat", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage addPlat(@RequestBody TBLogplatSysPlat plat,HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "addPlat";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage bm = platProjectService.addPlat(plat);
		
		return bm;
	}
	
	@RequestMapping(value = "/updatePlat", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage updatePlat(@RequestBody TBLogplatSysPlat plat,HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "updatePlat";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage bm = platProjectService.updatePlat(plat);
		
		return bm;
	}
	
	@RequestMapping(value = "/deletePlat", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage deletePlat(@RequestBody TBLogplatSysPlat plat,HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "deletePlat";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage bm = platProjectService.deletePlat(plat);
		
		return bm;
	}
}
