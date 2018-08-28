package com.good.plat.datacenter.game.controller;

import java.util.ArrayList;
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
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.game.entity.Game;
import com.good.plat.datacenter.game.entity.GameLanguage;
import com.good.plat.datacenter.game.entity.TBLogplatAttrValue;
import com.good.plat.datacenter.game.entity.TBLogplatGameVersion;
import com.good.plat.datacenter.game.service.impl.GameServiceImpl;
import com.good.plat.datacenter.login.service.LoginService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: BaseController
 * @Description: 游戏管理
 * @author dmw
 * @date 2016年1月14日 上午10:10:01
 */
@Controller
@RequestMapping("/game")
public class GameController extends BaseController{

	public GameController() {
	}
	
	@Autowired
	private GameServiceImpl gameService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/gameList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Game> findGameList(@RequestBody Game game) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "findGameList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, game);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<Game> gameList = gameService.findGameList(game.getProjectid());
		
		return gameList;
	}

	@RequestMapping(value = "/projectGameList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Game> findProjectGameList(@RequestBody Game game) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "findProjectGameList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, game);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<Game> gameList = gameService.findProjectGameList(game.getProjectid());
		
		return gameList;
	}
	
	@RequestMapping(value = "/deleteGame", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage deleteGame(@RequestBody Game game) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "deleteGame";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, game);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = gameService.deleteGame(game.getId());

		return mess;
	}
	
	@RequestMapping(value = "/addGame", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage addGame(@Validated @RequestBody Game game, 
			BindingResult result) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "addGame";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, game);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = new BaseMessage();
		if (result.hasErrors()) {
			mess.setStatus(0);
			mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return mess;
		} else {
			mess = gameService.addGame(game);
		}

		return mess;
	}
	
	
	@RequestMapping(value = "/getGame", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Game getGame(@Validated @RequestBody Game game) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "getGame";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, game);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		Game ga = gameService.findGameById(game.getId());

		return ga;
	}
	
	@RequestMapping(value = "/getUserGameList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Game> getUserGameList(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "getUserGameList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<Game> glist = loginService.getUserGameList(session, response);
		return glist;
	}
	
	@RequestMapping(value = "/getLanguageList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<GameLanguage> getLanguageList(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "getLanguageList", new Date(),null);
		//
		List<GameLanguage> languageList = null;
		List<TBLogplatAttrValue> llist = gameService.findLanguageList();
		if(llist != null){
			languageList = new ArrayList();
			for(TBLogplatAttrValue e : llist){
				GameLanguage o = new GameLanguage();
				o.setLanguage_id(e.getId());
				o.setLanguage_name(e.getAttr_value());
				languageList.add(o);
			}
		}
		return languageList;
	}
	//select * from tb_logplat_attr_value where attr_id=13;
	
	@RequestMapping(value = "/getGameVersionList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<TBLogplatGameVersion> getGameVersionList(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "getGameVersionList", new Date(),searchData);
		//
		List<TBLogplatGameVersion> languageList = new ArrayList();
		if(searchData.getGameid() == null){
			return null;
		}
		int gameid = searchData.getGameid();
		languageList = gameService.findGameVersionList(gameid);
		return languageList;
	}
	
	@RequestMapping(value = "/deleteGameVersion", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage deleteGameVersion(@RequestBody TBLogplatGameVersion version,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "deleteGameVersion", new Date(),version);
		//
		BaseMessage result = gameService.deleteGameVersion(version.getVersion_id());
		return result;
	}
	
	@RequestMapping(value = "/addGameVersion", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage addGameVersion(@RequestBody TBLogplatGameVersion version,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "addGameVersion", new Date(),version);
		//
		BaseMessage result = gameService.addGameVersion(version);
		return result;
	}
	
	@RequestMapping(value = "/addGameLanguage", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage addGameLanguage(@RequestBody TBLogplatAttrValue gl,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "addGameLanguage", new Date(),gl);
		//
		BaseMessage result = null;
		gl.setAttr_value(gl.getGameid().toString());
		
		result = gameService.addGameLanguage(gl);
		return result;
	}
	
	@RequestMapping(value = "/deleteGameLanguage", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage deleteGameLanguage(@RequestBody TBLogplatAttrValue gl,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "deleteGameLanguage", new Date(),gl);
		//
		BaseMessage result = null;
		result = gameService.deleteGameLanguageById(gl.getId());
		return result;
	}
	
	@RequestMapping(value = "/getGameLanguageList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<GameLanguage> getGameLanguageList(@RequestBody GameLanguage gl,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "getGameLanguageList", new Date(),gl);
		//
		List<GameLanguage> gllist = null;
		gllist = gameService.getGameLanguageList(gl);
		return gllist;
	}
	/**
	 * 获取游戏信息
	 * @Title: getGameInfo
	 * @Description: TODO
	 * @param game
	 * @return
	 * @throws Exception
	 * Game
	 * @author hwj
	 * @date 2017-11-20 下午03:21:04
	 */
	@RequestMapping(value = "/getGameInfo", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Game getGameInfo(@RequestBody Game game) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "getGameInfo";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, game);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		Game ga = gameService.findGameById(game.getId());

		return ga;
	}
	/**
	 * 修改游戏信息
	 * @Title: updateGame
	 * @Description: TODO
	 * @param game
	 * @param result
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2017-11-21 上午10:02:03
	 */
	@RequestMapping(value = "/updateGame", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage updateGame(@Validated @RequestBody Game game, 
			BindingResult result) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "updateGame";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, game);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = new BaseMessage();
		if (result.hasErrors()) {
			mess.setStatus(0);
			mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return mess;
		} else {
			mess = gameService.updateGame(game);
		}

		return mess;
	}
}
