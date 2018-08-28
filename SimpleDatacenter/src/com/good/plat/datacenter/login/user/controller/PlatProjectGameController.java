package com.good.plat.datacenter.login.user.controller;

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
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.game.entity.TBLogplatGameChannel;
import com.good.plat.datacenter.game.entity.TBLogplatSysPlat;
import com.good.plat.datacenter.game.service.PlatProjectService;
import com.good.plat.datacenter.login.service.impl.LoginServiceImpl;
import com.good.plat.datacenter.login.user.entity.FirstMenu;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

@Controller
@RequestMapping("/platprojectgame")
public class PlatProjectGameController extends BaseController{
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private PlatProjectService platProjectService;
	
	@ResponseBody
	@RequestMapping(value="/selectUserPlatProjectGameList", method={RequestMethod.POST, RequestMethod.GET})
	public List<TBLogplatSysPlat> selectUserPlatProjectGameList(HttpSession httpSession) throws Exception{
		List<TBLogplatSysPlat> list = null;
		int userid = LoginServiceImpl.getUserID(httpSession);
		list = platProjectService.selectUserPlatProjectGameList(userid);
		return list;
	}
	
	@RequestMapping(value = "/selectUserPlatProjectGameMenuList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<FirstMenu> selectUserPlatProjectGameMenuList(@RequestBody BaseSearchData baseSearchData,HttpServletRequest request, 
			HttpServletResponse response,HttpSession httpSession) throws Exception {
		//log
		Date opDate = new Date();
//		String methodName = "selectUserPlatProjectGameMenuList";
//		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
//		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null);
//		sysAccessLogService.saveAccessLog(accessLog);
		sysAccessLogService.log(request, getClass(), "selectUserPlatProjectGameMenuList", opDate, baseSearchData);
		//
		int userid = LoginServiceImpl.getUserID(httpSession);
		baseSearchData.setUserid(userid);
		List<FirstMenu> list = platProjectService.selectUserPlatProjectGameMenuList(baseSearchData);
		
		return list;
	}
	
	@RequestMapping(value = "/selectPlatList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<TBLogplatSysPlat> selectPlatList(HttpServletRequest request, 
			HttpServletResponse response,HttpSession httpSession) throws Exception {
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
}
