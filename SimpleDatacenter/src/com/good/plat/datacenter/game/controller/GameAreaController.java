
package com.good.plat.datacenter.game.controller;

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
import com.good.plat.datacenter.game.entity.GameArea;
import com.good.plat.datacenter.game.service.impl.GameAreaServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: BaseController
 * @Description: 游戏区服管理
 * @author dmw
 * @date 2016年1月14日 上午10:10:01
 */
@Controller
@RequestMapping("/gamearea")
public class GameAreaController extends BaseController{

	public GameAreaController() {
	}
	
	@Autowired
	private GameAreaServiceImpl gameAreaService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	
	
	@RequestMapping(value = "/gameareaList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<GameArea> findGameAreaList(@RequestBody GameArea area) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "findGameAreaList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, area);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<GameArea> areaList = gameAreaService.findAreaList(area.getGameid());
		
		return areaList;
	}

	
	@RequestMapping(value = "/deleteGameArea", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage deleteGameArea(@RequestBody GameArea area) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "deleteGameArea";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, area);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = gameAreaService.delGameArea(area);

		return mess;
	}
	
	@RequestMapping(value = "/addGameArea", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage addGame(@Validated @RequestBody GameArea area, 
			BindingResult result) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "addGame";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, area);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = new BaseMessage();
		if (result.hasErrors()) {
			mess.setStatus(0);
			mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return mess;
		} else {
			mess = gameAreaService.addGameArea(area);
		}

		return mess;
	}
	
	
	@RequestMapping(value = "/getGameArea", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public GameArea getGameArea(@Validated @RequestBody GameArea area) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "getGameArea";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, area);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		GameArea gameArea = gameAreaService.findGameAreaById(area.getId());

		return gameArea;
	}
	

}
