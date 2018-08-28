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

import org.apache.log4j.Logger;
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
import com.good.plat.datacenter.datastat.entity.players.ActivePlayers;
import com.good.plat.datacenter.datastat.entity.players.PlayersRetention;
import com.good.plat.datacenter.datastat.service.impl.players.PlayersRetentionServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: PlayersRetentionController
 * @Description: 玩家留存
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Controller
@RequestMapping("/players/playersRetention")
public class PlayersRetentionController extends BaseController {
	Logger log = Logger.getLogger(getClass());
	@Autowired
	private PlayersRetentionServiceImpl playersRetentionService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping(value = "/selectRetentionOfUser", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> selectRetentionOfUser(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectRetentionOfUser";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			
			List<PlayersRetention> newUserRetentionList = null;
			Double day1AVG = 0.0, day7AVG = 0.0, day30AVG = 0.0;
			//
			newUserRetentionList = selectRetentionOfNewUser(searchData, session);
			Double dayscount = 0.0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(!Validator.isNullOrBlank(searchData.getStartdate())){
				try{
					Date d1 = sdf.parse(searchData.getStartdate());
					dayscount = 1.0;
					Date d2 = sdf.parse(searchData.getEnddate());
					dayscount = (double) (Math.ceil((d2.getTime() - d1.getTime())/ (1000 * 3600 * 24.0)))+1;
				}catch(ParseException pe){}
			}
			Double day1TotalNewAct=0.0;//
			Double day7TotalNewAct=0.0;//
			Double day30TotalNewAct=0.0;//
			for(PlayersRetention pr : newUserRetentionList){
				if(pr.getDay1() == null){
					pr.setDay1(0.0);
				}
				if(pr.getDay7() == null){
					pr.setDay7(0.0);
				}
				if(pr.getDay30() == null){
					pr.setDay30(0.0);
				}
//				day1AVG += pr.getDay1();
//				day7AVG += pr.getDay7();
//				day30AVG += pr.getDay30();
				if(pr.getAccounts()!=0&&pr.getDay1RetNum()!=0){
					day1AVG += pr.getDay1RetNum();
					day7AVG += pr.getDay7RetNum();
					day30AVG += pr.getDay30RetNum();
				}
				
				if(pr.getDay1()!=0){
					day1TotalNewAct+=pr.getAccounts();
				}
				if(pr.getDay7()!=0){
					day7TotalNewAct+=pr.getAccounts();
				}
				if(pr.getDay30()!=0){
					day30TotalNewAct+=pr.getAccounts();
				}
				
			}
//			day1AVG = NumberUtil.div(day1AVG, dayscount);
//			day7AVG = NumberUtil.div(day7AVG, dayscount);
//			day30AVG = NumberUtil.div(day30AVG, dayscount);
			day1AVG = NumberUtil.div(day1AVG, day1TotalNewAct,4);
			day7AVG = NumberUtil.div(day7AVG, day7TotalNewAct,4);
			day30AVG = NumberUtil.div(day30AVG, day30TotalNewAct,4);
			//
			result.put("newUserRetention", newUserRetentionList);
			result.put("day1AVG", NumberUtil.multi100(day1AVG, 2));
			result.put("day7AVG", NumberUtil.multi100(day7AVG, 2));
			result.put("day30AVG", NumberUtil.multi100(day30AVG, 2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/selectRetentionOfNewUser", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<PlayersRetention> selectRetentionOfNewUser(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectRetentionOfNewUser";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playersRetentionService.selectRetentionOfNewUser(searchData,
				session);
	}
	
	@RequestMapping(value = "/exportRetentionOfNewUser", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public void exportRetentionOfNewUser(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportRetentionOfNewUser";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "留存率.xlsx";
		//
		Map<String, Object> playersRetention = selectRetentionOfUser(searchData, session);
		List<PlayersRetention> newUserRetentionList = (List<PlayersRetention>) playersRetention.get("newUserRetention");
		List<PlayersRetention> actUserRetentionList = playersRetentionService.selectRetentionOfActUser(searchData);
		List<PlayersRetention> returnUserRetentionList=playersRetentionService.selectRetentionOfreturnUser(searchData);
		List<PlayersRetention> newAccountRetentionList=playersRetentionService.selectRetentionOfNewAccount(searchData);
		
		//
		if(newUserRetentionList != null){
			String title[] = new String[]{"日期","角色数","1日留存率(%)","2日留存率(%)","3日留存率(%)","4日留存率(%)","5日留存率(%)","6日留存率(%)","7日留存率(%)","14日留存率(%)","30日留存率(%)",
					"60日留存率(%)","90日留存率(%)","120日留存率(%)","150日留存率(%)","180日留存率(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(PlayersRetention e : newUserRetentionList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getAccounts();
				items[count++] = e.getDay1();
				items[count++] = e.getDay2();
				items[count++] = e.getDay3();
				items[count++] = e.getDay4();
				items[count++] = e.getDay5();
				items[count++] = e.getDay6();
				items[count++] = e.getDay7();
				items[count++] = e.getDay14();
				items[count++] = e.getDay30();
				items[count++] = e.getDay60();
				items[count++] = e.getDay90();
				items[count++] = e.getDay120();
				items[count++] = e.getDay150();
				items[count++] = e.getDay180();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("新增玩家留存");
		}
		
		if(actUserRetentionList != null){
			String title[] = new String[]{"日期","角色数","1日留存率(%)","2日留存率(%)","3日留存率(%)","4日留存率(%)","5日留存率(%)","6日留存率(%)","7日留存率(%)","14日留存率(%)","30日留存率(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(PlayersRetention e : actUserRetentionList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getAccounts();
				items[count++] = e.getDay1();
				items[count++] = e.getDay2();
				items[count++] = e.getDay3();
				items[count++] = e.getDay4();
				items[count++] = e.getDay5();
				items[count++] = e.getDay6();
				items[count++] = e.getDay7();
				items[count++] = e.getDay14();
				items[count++] = e.getDay30();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("活跃玩家留存");
		}
		
		if(returnUserRetentionList != null){
			String title[] = new String[]{"日期","角色数","1日留存率(%)","2日留存率(%)","3日留存率(%)","4日留存率(%)","5日留存率(%)","6日留存率(%)","7日留存率(%)","14日留存率(%)","30日留存率(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(PlayersRetention e : returnUserRetentionList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getAccounts();
				items[count++] = e.getDay1();
				items[count++] = e.getDay2();
				items[count++] = e.getDay3();
				items[count++] = e.getDay4();
				items[count++] = e.getDay5();
				items[count++] = e.getDay6();
				items[count++] = e.getDay7();
				items[count++] = e.getDay14();
				items[count++] = e.getDay30();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("回流玩家留存");
		}
		if(newAccountRetentionList != null){
			String title[] = new String[]{"日期","账号数","1日留存率(%)","2日留存率(%)","3日留存率(%)","4日留存率(%)","5日留存率(%)","6日留存率(%)","7日留存率(%)","14日留存率(%)","30日留存率(%)",
					"60日留存率(%)","90日留存率(%)","120日留存率(%)","150日留存率(%)","180日留存率(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(PlayersRetention e : newAccountRetentionList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getAccounts();
				items[count++] = e.getDay1();
				items[count++] = e.getDay2();
				items[count++] = e.getDay3();
				items[count++] = e.getDay4();
				items[count++] = e.getDay5();
				items[count++] = e.getDay6();
				items[count++] = e.getDay7();
				items[count++] = e.getDay14();
				items[count++] = e.getDay30();
				items[count++] = e.getDay60();
				items[count++] = e.getDay90();
				items[count++] = e.getDay120();
				items[count++] = e.getDay150();
				items[count++] = e.getDay180();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("新增账号留存");
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
	
	@RequestMapping(value = "/selectRetentionOfDevice", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<PlayersRetention> selectRetentionOfDevice(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectRetentionOfDevice";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playersRetentionService.selectRetentionOfDevice(searchData,
				session);
	}

	@RequestMapping(value = "/exportRetentionOfDevice", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<PlayersRetention> exportRetentionOfDevice(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportRetentionOfDevice";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playersRetentionService.exportRetentionOfDevice(searchData,
				session);
	}

	@RequestMapping(value = "/selectRetentionUserAnalys", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<PlayersRetention> selectRetentionUserAnalys(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		log.debug(searchData);
		List<PlayersRetention> list = new ArrayList<PlayersRetention>();
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectRetentionUserAnalys";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			
			list = playersRetentionService.selectRetentionUserAnalys(searchData,session);
			if(list != null){
				Double total = 0.0;
				for(PlayersRetention pr : list){
					if(pr!=null){
						total += pr.getAccounts();
					}
				}
				for(PlayersRetention pr : list){
					pr.setRate(NumberUtil.mul(NumberUtil.div(pr.getAccounts().doubleValue(), total)	, 100.0, NumberUtil.DEFAULT_SCALE));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		} 
		return list;
				
	}

	@RequestMapping(value = "/exportRetentionUserAnalys", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public void exportRetentionUserAnalys(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportRetentionUserAnalys";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "留存用户分析.xlsx";
		//
		List<PlayersRetention> playersRetentionAnalysList = selectRetentionUserAnalys(searchData, session);
		
		//
		if(playersRetentionAnalysList != null){
			String title[] = null;
			String analysisType = "";
			switch (Integer.parseInt(searchData.getChecktype1())) {
			case 1:
				analysisType = "新增日等级";
				break;
			case 2:
				analysisType = "新增日游戏次数";
				break;
			case 3:
				analysisType = "新增日游戏时长";
				break;
			case 4:
				analysisType = "新增日是否付费";
				break;
			}
			String userGroup = "";
			switch (Integer.parseInt(searchData.getChecktype2())) {
			case 1:
				userGroup = "Day1 留存用户";
				break;
			case 2:
				userGroup = "Day7 留存用户";
				break;
			case 3:
				userGroup = "Day30 留存用户";
				break;
			}
			title = new String[]{analysisType,userGroup,"百分比"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(PlayersRetention e : playersRetentionAnalysList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatType();
				items[count++] = e.getAccounts();
				items[count++] = e.getRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("留存用户分析");
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
	
	/**
	 * 
	 * @Title: selectRetentionOfActUser
	 * @Description: 活跃玩家留存
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<PlayersRetention>
	 * @author hwj
	 * @date 2017-1-22 下午05:10:20
	 */
	@RequestMapping(value = "/selectRetentionOfActUser", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<PlayersRetention> selectRetentionOfActUser(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectRetentionOfActUser";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<PlayersRetention> list=playersRetentionService.selectRetentionOfActUser(searchData);
		return list;
	}
	
	/**
	 * 
	 * @Title: selectRetentionOfReturnUser
	 * @Description: 回流玩家留存
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<PlayersRetention>
	 * @author hwj
	 * @date 2017-1-22 下午05:10:20
	 */
	@RequestMapping(value = "/selectRetentionOfReturnUser", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<PlayersRetention> selectRetentionOfReturnUser(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectRetentionOfReturnUser";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<PlayersRetention> list=playersRetentionService.selectRetentionOfreturnUser(searchData);
		return list;
	}
	
	/**
	 * 新增账号留存（首次创建角色数）
	 * @Title: selectRetentionOfNewAccount
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<PlayersRetention>
	 * @author hwj
	 * @date 2017-4-26 下午06:26:59
	 */
	@RequestMapping(value = "/selectRetentionOfNewAccount", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<PlayersRetention> selectRetentionOfNewAccount(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectRetentionOfNewAccount";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<PlayersRetention> list=playersRetentionService.selectRetentionOfNewAccount(searchData);
		return list;
	}
	/**
	 * 留存区域列表
	 * @Title: selectRetentionCityList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<PlayersRetention>
	 * @author hwj
	 * @date 2017-11-10 下午03:25:34
	 */
	@RequestMapping(value = "/selectUserRetentionCityList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<PlayersRetention> selectUserRetentionCityList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectUserRetentionCityList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<PlayersRetention> list=playersRetentionService.selectUserRetainCityList(searchData);
		return list;
	}
	
	
}
