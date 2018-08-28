package com.good.plat.datacenter.common.controller;

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

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.datastat.entity.revenue.NewPlayerValue;
import com.good.plat.datacenter.datastat.service.impl.revenue.NewPlayerValueServiceImpl;
import com.good.plat.datacenter.game.entity.Channel;
import com.good.plat.datacenter.game.entity.Game;
import com.good.plat.datacenter.game.entity.GameArea;
import com.good.plat.datacenter.game.service.GameService;
import com.good.plat.datacenter.game.service.impl.ChannelServiceImpl;
import com.good.plat.datacenter.game.service.impl.GameAreaServiceImpl;
import com.good.plat.datacenter.login.service.LoginService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * 基本查询条件控制器
 * @ClassName: BaseSearchController
 * @Description: TODO
 * @author hwj
 * @date 2017-6-8 上午11:06:34
 */
@Controller
@RequestMapping(value="/search")
public class BaseSearchController {

	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private ChannelServiceImpl channelService;
	@Autowired
	private GameAreaServiceImpl gameAreaService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private GameService gameService;
	@Autowired
	private NewPlayerValueServiceImpl newPlayerValueService;
	
	/**
	 * 游戏下渠道列表（带权限）
	 * @Title: findGameChannelList
	 * @Description: TODO
	 * @param channel
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Channel>
	 * @author hwj
	 * @date 2017-6-8 上午10:56:24
	 */
	@RequestMapping(value = "/selectGameChannelList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Channel> selectGameChannelList(@RequestBody Channel channel, 
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectGameChannelList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, channel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return channelService.selectGameChannelList(session, channel);
	}
	
	/**
	 * 游戏区服列表
	 * @Title: selectGameAreaList
	 * @Description: TODO
	 * @param area
	 * @return
	 * @throws Exception
	 * List<GameArea>
	 * @author hwj
	 * @date 2017-6-8 上午11:12:09
	 */
	@RequestMapping(value = "/selectGameAreaList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<GameArea> selectGameAreaList(@RequestBody GameArea area) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectGameAreaList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, area);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<GameArea> areaList = gameAreaService.findAreaList(area.getGameid());
		
		return areaList;
	}
	/**
	 * 游戏列表（带权限）
	 * @Title: getUserGameList
	 * @Description: TODO
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 * List<Game>
	 * @author hwj
	 * @date 2017-8-17 下午04:12:20
	 */
	@RequestMapping(value = "/selectUserGameList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Game> getUserGameList(HttpSession session,
			HttpServletResponse response) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectUserGameList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, null);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<Game> list=loginService.getUserGameList(session, response);
		return list;
	}
	
	/**
	 * 游戏列表(不带权限)
	 * @Title: findAllGameList
	 * @Description: TODO
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 * List<Game>
	 * @author hwj
	 * @date 2017-8-23 下午07:21:48
	 */
	@RequestMapping(value = "/findAllGameList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Game> findAllGameList(HttpSession session,
			HttpServletResponse response) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "findAllGameList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, null);
		sysAccessLogService.saveAccessLog(accessLog);
		//-1查找所有游戏
		List<Game> list=gameService.findGameList(-1);
		return list;
	}
	/**
	 * 充值区域列表(对于角色充值表)
	 * @Title: selectRechargeCityList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<NewPlayerValue>
	 * @author hwj
	 * @date 2017-9-18 下午02:00:24
	 */
	@RequestMapping(value="/selectRechargeCityList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<NewPlayerValue> selectRechargeCityList(@RequestBody BaseSearchData searchData, HttpSession session) throws Exception{
		//log
		Date opDate = new Date();
		String methodName = "selectRechargeCityList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<NewPlayerValue> cityList=null;
		cityList=newPlayerValueService.selectRechargeCityList(searchData);
		return cityList;
		
	}
	
}
