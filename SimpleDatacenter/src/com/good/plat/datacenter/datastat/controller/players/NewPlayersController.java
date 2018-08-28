package com.good.plat.datacenter.datastat.controller.players;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.Validator;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.index.SummaryData;
import com.good.plat.datacenter.datastat.entity.players.NewPlayers;
import com.good.plat.datacenter.datastat.service.impl.players.NewPlayersServiceImpl;
import com.good.plat.datacenter.game.entity.Game;
import com.good.plat.datacenter.game.service.GameService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: NewPlayersController
 * @Description: 新增玩家
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Controller
@RequestMapping("/players/newPlayers")
public class NewPlayersController extends BaseController {

	@Autowired
	private NewPlayersServiceImpl newPlayersService;
	@Autowired
	private GameService gameService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	/**
	 * 根据id获取游戏
	 * @Title: getGame
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 * Game
	 * @author hwj
	 * @date 2017-5-8 下午01:59:41
	 */
	@RequestMapping(value = "/getGame", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Game getGame(@RequestBody BaseSearchData searchData,
			HttpSession session,HttpServletRequest request)throws Exception{
		Game game =gameService.findGameById(searchData.getGameid());
		return game;
	}
	
	@RequestMapping(value = "/selectNewPlayerList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectNewPlayerList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectNewPlayerList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			
			Map<String,Object> newAccounts = new HashMap<String,Object>();
			List<NewPlayers> newPlayerList = null;
			List<String> cityList=searchData.getCityList();
//			String city=searchData.getChecktype1();
			Long deviceSUM = 0L;
			Long accountSUM = 0L;
			Long devicAVG = 0L;
			Long accountAVG = 0L;
			if(cityList!=null&&cityList.size()>0){
				//筛选了区域（没有激活台数）
				newPlayerList=newPlayersService.selectNewPlayerListOfCity(searchData);
				if(newPlayerList != null){
					for(NewPlayers p : newPlayerList){
						accountSUM += p.getNewPlayers();
					}
				}
			}else{
				//没有区域则按原来条件筛选全部
				newPlayerList = newPlayersService.selectNewPlayerList(searchData, session);
				if(newPlayerList != null){
					for(NewPlayers p : newPlayerList){
						deviceSUM += p.getDevices();
						accountSUM += p.getNewPlayers();
					}
				}
			}
			//城市筛选选择全部
//			if(city!=null&&city.equals("-1")){
//				newPlayerList = newPlayersService.selectNewPlayerList(searchData, session);
//				if(newPlayerList != null){
//					for(NewPlayers p : newPlayerList){
//						deviceSUM += p.getDevices();
//						accountSUM += p.getNewPlayers();
//					}
//				}
//			}else{
//				//某城市
//				newPlayerList=newPlayersService.selectNewPlayerListOfCity(searchData);
//				if(newPlayerList != null){
//					for(NewPlayers p : newPlayerList){
//						accountSUM += p.getNewPlayers();
//					}
//				}
//			}
			
			newAccounts.put("deviceSUM", deviceSUM);
			newAccounts.put("accountSUM", accountSUM);
			Double dayscount = 0.0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(!Validator.isNullOrBlank(searchData.getStartdate())){
				try{
					Date d1 = sdf.parse(searchData.getStartdate());
					dayscount = 1.0;
					Date d2 = sdf.parse(searchData.getEnddate());
					dayscount = (double) (Math.ceil((d2.getTime() - d1.getTime())/ (1000 * 3600 * 24.0)));
				}catch(ParseException pe){}
			}
			newAccounts.put("devicAVG", NumberUtil.div(deviceSUM.doubleValue(), dayscount));
			newAccounts.put("accountAVG", NumberUtil.div(accountSUM.doubleValue(), dayscount));
			newAccounts.put("data",newPlayerList);
			
//			List<NewPlayers> userTransform = new ArrayList();
//			userTransform = newPlayersService.selectConversionList(searchData, session);
			
			List<NewPlayers> accountscountList = new ArrayList();
			//accountscountList =newPlayersService.selectNewPlayerOfSubsidiaryList(searchData, session);
			
			List channelList = new ArrayList();
			channelList = newPlayersService.selectNewPlayerOfChannelList(searchData, session);
			
			result.put("newAccounts", newAccounts);
			result.put("newAccountsData", newPlayerList);
//			result.put("userTransform", userTransform);
			result.put("accountscount", accountscountList);
			result.put("channelList", channelList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "/exportNewPlayerList", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void exportNewPlayerList(@RequestBody BaseSearchData searchData,
			HttpSession session,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportNewPlayerList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "新增玩家.xlsx";
		//
		Map<String, Object> map = selectNewPlayerList(searchData,session);
		Map<String, Object> newAccounts = (Map<String, Object>) map.get("newAccounts"); 
		List<NewPlayers> newPlayerList = (List<NewPlayers>) newAccounts.get("data");
//		List<NewPlayers> userTransform = (List<NewPlayers>) map.get("userTransform");
		//
		if(newPlayerList != null){
			String title[] = new String[]{"日期","激活台数(台)","新增玩家(账户)","新增玩家(角色)","首次创角数","玩家转化率"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(NewPlayers e : newPlayerList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getDevices();
				items[count++] = e.getNewUser();
				items[count++] = e.getNewPlayers();
				items[count++] = e.getRegCnt();
				items[count++] =e.getPlayerConv()+"%";
				
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("新增激活和账户");
		}
		//
		Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
		ExcelGenerator.fillWorkBook(w,sheetNameList, list);
		
		//
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		w.write(baos);
		String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
		HTTPUtil.responseFile(response, request, contentType , filename, baos);
	}

	
	
	@RequestMapping(value = "/selectConversionList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<NewPlayers> selectConversionList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectConversionList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return newPlayersService.selectConversionList(searchData, session);
	}

	
	
	@RequestMapping(value = "/exportConversionList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<NewPlayers> exportConversionList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportConversionList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return newPlayersService.exportConversionList(searchData, session);
	}

	
	
	@RequestMapping(value = "/selectNewPlayerOfCityList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<NewPlayers> selectNewPlayerOfCityList(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectNewPlayerOfCityList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return newPlayersService.selectNewPlayerOfCityList(searchData, session);
	}

	
	
	@RequestMapping(value = "/exportNewPlayerOfCityList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<NewPlayers> exportNewPlayerOfCityList(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportNewPlayerOfCityList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return newPlayersService.exportNewPlayerOfCityList(searchData, session);
	}

	
	
	@RequestMapping(value = "/selectNewPlayerOfChannelList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<NewPlayers> selectNewPlayerOfChannelList(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectNewPlayerOfChannelList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return newPlayersService.selectNewPlayerOfChannelList(searchData, session);
	}

	
	
	@RequestMapping(value = "/exportNewPlayerOfChannelList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public void exportNewPlayerOfChannelList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportNewPlayerOfChannelList", new Date(), searchData);
		//
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "渠道.xlsx";
		//
		List<NewPlayers> newPlayersList = selectNewPlayerOfChannelList(searchData,session);
		//
		if(newPlayersList != null){
			String title[] = new String[]{"渠道","激活台数","注册账号","新增玩家(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(NewPlayers e : newPlayersList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getChannelName();
				items[count++] = e.getAccountSUM();
				items[count++] = e.getNewUser();
				items[count++] = e.getNewPlayers();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("渠道新增玩家");
		}
		//
		Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
		ExcelGenerator.fillWorkBook(w,sheetNameList, list);
		
		//
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		w.write(baos);
		String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
		HTTPUtil.responseFile(response, request, contentType , filename, baos);
		//return newPlayersService.exportNewPlayerOfChannelList(searchData, session);
	}
	
	/**
	 * 新增玩家城市列表
	 * @Title: selectNewPlayerCityList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<NewPlayers>
	 * @author hwj
	 * @date 2017-8-29 下午03:24:34
	 */
	@RequestMapping(value = "/selectNewPlayerCityList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<NewPlayers> selectNewPlayerCityList(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectNewPlayerCityList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<NewPlayers> list=newPlayersService.selectNewPlayerCityList(searchData);
		return list;
	}
	/**
	 * 新增玩家(按小时)
	 * @Title: selectNewPlayerOfHour
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<NewPlayers>
	 * @author hwj
	 * @date 2017-11-28 下午05:17:09
	 */
	@RequestMapping(value = "/selectNewPlayerOfHour", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectNewPlayerOfHour(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectNewPlayerOfHour";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		Map<String,Object> map=new HashMap<String, Object>();
		List<NewPlayers> newPlayerListOfHour=null;
		try {
			newPlayerListOfHour=newPlayersService.selectNewPlayerListOfHour(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		map.put("newPlayerListOfHour", newPlayerListOfHour);
		return map;
	}
	
	@RequestMapping(value = "/exportNewPlayerOfHour", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public void exportNewPlayerOfHour(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportNewPlayerOfHour", new Date(), searchData);
		//
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "新增玩家(按小时).xlsx";
		//
		List<NewPlayers> newPlayerListOfHour=newPlayersService.selectNewPlayerListOfHour(searchData);
		//
		if(newPlayerListOfHour != null){
			String title[] = new String[]{"时间段","激活台数","新增玩家(账号)","新增玩家(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(NewPlayers e : newPlayerListOfHour){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getHourDateDesc();
				items[count++] = e.getDevices();
				items[count++] = e.getNewUser();
				items[count++] = e.getNewPlayers();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("新增玩家(按小时)");
		}
		//
		Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
		ExcelGenerator.fillWorkBook(w,sheetNameList, list);
		
		//
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		w.write(baos);
		String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
		HTTPUtil.responseFile(response, request, contentType , filename, baos);
		//return newPlayersService.exportNewPlayerOfChannelList(searchData, session);
	}
	


}
