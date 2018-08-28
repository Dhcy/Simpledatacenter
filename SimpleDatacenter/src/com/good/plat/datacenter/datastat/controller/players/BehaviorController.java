package com.good.plat.datacenter.datastat.controller.players;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.players.Behavior;
import com.good.plat.datacenter.datastat.entity.players.GameFrequency;
import com.good.plat.datacenter.datastat.service.impl.players.BehaviorServiceImpl;
import com.good.plat.datacenter.datastat.service.players.GameFrequencyService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: BehaviorMapper
 * @Description: 游戏习惯
 * @author moxw
 * @date 2016年4月5日 上午10:32:49
 */
@Controller
@RequestMapping("/players/behavior")
public class BehaviorController extends BaseController{
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BehaviorServiceImpl playerBehaviorService;
	@Autowired
	private GameFrequencyService gameFrequencyService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping(value = "/exportAVGTimesAndTime", method = { RequestMethod.POST })
	public void exportAVGTimesAndTime(@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportAVGTimesAndTime", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "平均游戏时长与次数.xlsx";
		//
		Map<String, Object> dailyGameHour_Times = selectDailyGameHour_Times(searchData, session);
		Map<String, Object> weeklyGameHour_Times = selectWeeklyGameHour_Times(searchData, session);
		Map<String, Object> monthlyGameHour_Times = selectMonthlyGameHour_Times(searchData, session);
		
		List<Behavior> dailyGameHourTimesList = (List<Behavior>) dailyGameHour_Times.get("data");
		List<Behavior> weeklyGameHour_TimesList = (List<Behavior>) weeklyGameHour_Times.get("data");
		List<Behavior> monthlyGameHour_TimesList = (List<Behavior>) monthlyGameHour_Times.get("data");
		//
		if(dailyGameHourTimesList != null){
			String[] title = new String[] {"日期","每日游戏时长(分)","每日游戏次数(次)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Behavior e : dailyGameHourTimesList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getGameTimes();
				items[count++] = e.getGameCounts();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("每日");
		}
		if(weeklyGameHour_TimesList != null){
			String[] title = new String[] {"日期","每周游戏时长","每周游戏次数"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Behavior e : weeklyGameHour_TimesList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getGameTimes();
				items[count++] = e.getGameCounts();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("每周");
		}
		if(monthlyGameHour_TimesList != null){
			String[] title = new String[] {"日期","每月游戏时长","每月游戏次数"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Behavior e : monthlyGameHour_TimesList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getGameTimes();
				items[count++] = e.getGameCounts();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("每月");
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
	
	@RequestMapping(value = "/exportGameFrequency", method = { RequestMethod.POST })
	public void exportGameFrequency(@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportGameFrequency", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "游戏频次.xlsx";//	
		//
		List<GameFrequency> monthlyGameDaysList = selectMonthlyGameDays(searchData, session);
		List<GameFrequency> weeklyGameDaysList = selectWeeklyGameDays(searchData, session);
		List<GameFrequency> weeklyGameCountsList = selectWeeklyGameCounts(searchData, session);
		List<GameFrequency> dailyGameCountsList = selectDailyGameCounts(searchData, session);
		
		//
		if(monthlyGameDaysList != null){
			String[] title = new String[] {"月游戏天数","新增玩家(角色)","百分比(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(GameFrequency e : monthlyGameDaysList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getFrequency();
				items[count++] = e.getAccounts();
				items[count++] = e.getRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("月游戏天数");
		}
		if(weeklyGameDaysList != null){
			String[] title = new String[] {"周游戏天数","新增玩家(角色)","百分比(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(GameFrequency e : weeklyGameDaysList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getFrequency();
				items[count++] = e.getAccounts();
				items[count++] = e.getRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("周游戏天数");
		}
		if(weeklyGameCountsList != null){
			String[] title = new String[] {"周游戏次数","新增玩家(角色)","百分比(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(GameFrequency e : weeklyGameCountsList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getFrequency();
				items[count++] = e.getAccounts();
				items[count++] = e.getRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("周游戏次数");
		}
		if(dailyGameCountsList != null){
			String[] title = new String[] {"日游戏天数","新增玩家(角色)","百分比(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(GameFrequency e : dailyGameCountsList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getFrequency();
				items[count++] = e.getAccounts();
				items[count++] = e.getRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("日游戏次数");
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
	
	@RequestMapping(value = "/exportGamePeriod", method = { RequestMethod.POST })
	public void exportGamePeriod(@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportGamePeriod", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "游戏时段.xlsx";//	
		//
		List<GameFrequency> playerHoursList = selectPlayerHoursList(searchData, session);
		
		//
		if(playerHoursList != null){
			String[] title = new String[] {"游戏时段","新增玩家(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(GameFrequency e : playerHoursList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatType();
				items[count++] = e.getAccounts();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("游戏时段");
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
	
	@RequestMapping(value = "/selectDailyGameHour_Times", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectDailyGameHour_Times(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		logger.debug(searchData.toString());
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectDailyGameHour_Times";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			
			Double avg_times = 0.0,avg_total_minutes = 0.0;
			List<Behavior> list = null;
			list = selectDailyGameHourAndTimes(searchData, session);
			Double daysCount = 0.0;
			
			if(list != null){
				for(Behavior b : list){
					if(b != null){
						if(b.getAccounts() == null){
							b.setAccounts(0);
						}
						if(b.getGameCounts() == null){
							b.setGameCounts(0);
						}
						
						avg_times +=  NumberUtil.div(b.getGameCounts() == null ? 0.0 : b.getGameCounts().doubleValue()
								,b.getAccounts() == null ? 0.0 : b.getAccounts(),NumberUtil.DEFAULT_SCALE );
						avg_total_minutes +=  NumberUtil.div(b.getGameTimes() == null ? 0.0 : b.getGameTimes().doubleValue()
								,b.getAccounts() == null ? 0.0 : b.getAccounts(), NumberUtil.DEFAULT_SCALE);
//							b.setGameCounts(b.getAccounts()==0?0:b.getGameCounts()/b.getAccounts());
//							b.setGameTimes(b.getAccounts()==0?0:b.getGameTimes()/b.getAccounts());
//							b.setGameTimes(b.getGameTimes()==0?0:b.getGameTimes()/60);
					}
				}
			}
//			daysCount = DateUtil.daysCount(DateUtil.parse_yyyy_MM_dd(searchData.getStartdate()), DateUtil.parse_yyyy_MM_dd(searchData.getEnddate())).doubleValue();
			daysCount=Integer.valueOf(list.size()).doubleValue();
			avg_times = NumberUtil.div(avg_times, daysCount, NumberUtil.DEFAULT_SCALE);
			avg_total_minutes = NumberUtil.div(avg_total_minutes, daysCount, NumberUtil.DEFAULT_SCALE);
			
			result.put("data",list);
			result.put("avg_times",avg_times);
			result.put("avg_total_minutes",avg_total_minutes);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	@RequestMapping(value = "/selectDailyGameHourAndTimes", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Behavior> selectDailyGameHourAndTimes(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		logger.debug(searchData.toString());
		//log
		Date opDate = new Date();
		String methodName = "selectDailyGameHourAndTimes";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerBehaviorService.selectDailyGameHourAndTimes(searchData, session);
	}
	
	@RequestMapping(value = "/selectWeeklyGameHour_Times", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectWeeklyGameHour_Times(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		logger.debug(searchData.toString());
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectWeeklyGameHour_Times";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			
			Double avg_times = 0.0,avg_total_minutes = 0.0;
			List<Behavior> list = new LinkedList<Behavior>();
			Double weeksCount = 0.0;
			List<Date[]> weekslist = DateUtil.getContainingWeekList(DateUtil.parse_yyyy_MM_dd(searchData.getStartdate()), DateUtil.parse_yyyy_MM_dd(searchData.getEnddate()));
			if(weekslist != null){
				weeksCount = Integer.valueOf(weekslist.size()).doubleValue();
				for(Date[] dates : weekslist){
					searchData.setStartdate(DateUtil.format_yyyy_MM_dd(dates[0]));
					searchData.setEnddate(DateUtil.format_yyyy_MM_dd(dates[1]));
					List<Behavior> tlist = selectWeeklyGameHourAndTimes(searchData, session);
					if(tlist != null){
						for(Behavior b : tlist){
							if(b != null){
								if(b.getAccounts() == null){
									b.setAccounts(0);
								}
								if(b.getGameCounts() == null){
									b.setGameCounts(0);
								}
								if(b.getGameTimes() == null){
									b.setGameTimes(0);
								}
								list.add(b);
								
								b.setStatdate(DateUtil.format_yyyy_MM_dd(dates[0]) + "~" + DateUtil.format_yyyy_MM_dd(dates[1]));
								avg_times +=  NumberUtil.div(b.getGameCounts() == null ? 0.0 : b.getGameCounts().doubleValue()
										,b.getAccounts() == null ? 0.0 : b.getAccounts(),NumberUtil.DEFAULT_SCALE );
								avg_total_minutes +=  NumberUtil.div(b.getGameTimes() == null ? 0.0 : b.getGameTimes().doubleValue()
										,b.getAccounts() == null ? 0.0 : b.getAccounts(), NumberUtil.DEFAULT_SCALE);
							}
						}
					}
				}
			}
			
			avg_times = NumberUtil.div(avg_times, weeksCount, NumberUtil.DEFAULT_SCALE);
			avg_total_minutes = NumberUtil.div(avg_total_minutes, weeksCount, NumberUtil.DEFAULT_SCALE);
			
			result.put("data",list);
			result.put("avg_times",avg_times);
			result.put("avg_total_minutes",avg_total_minutes);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "/selectWeeklyGameHourAndTimes", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Behavior> selectWeeklyGameHourAndTimes(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		logger.debug(searchData.toString());
		//log
		Date opDate = new Date();
		String methodName = "selectWeeklyGameHourAndTimes";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerBehaviorService.selectWeeklyGameHourAndTimes(searchData, session);
	}
	
	
	@RequestMapping(value = "/selectMonthlyGameHour_Times", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectMonthlyGameHour_Times(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		logger.debug(searchData.toString());
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectMonthlyGameHour_Times";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			
			Double avg_times = 0.0,avg_total_minutes = 0.0;
			List<Behavior> list = new LinkedList<Behavior>();
			Double monthsCount = 0.0;
			List<Date[]> monthslist = DateUtil.getContainingMonth(DateUtil.parse_yyyy_MM_dd(searchData.getStartdate()), DateUtil.parse_yyyy_MM_dd(searchData.getEnddate()));
			if(monthslist != null){
				monthsCount = Integer.valueOf(monthslist.size()).doubleValue();
				for(Date[] dates : monthslist){
					searchData.setStartdate(DateUtil.format_yyyy_MM_dd(dates[0]));
					searchData.setEnddate(DateUtil.format_yyyy_MM_dd(dates[1]));
					List<Behavior> tlist = selectMonthlyGameHourAndTimes(searchData, session);
					if(tlist != null){
						for(Behavior b : tlist){
							if(b != null){
								list.add(b);
								if(b.getAccounts() == null){
									b.setAccounts(0);
								}
								b.setStatdate(DateUtil.format_yyyy_MM(dates[0]));
								avg_times +=  NumberUtil.div(b.getGameCounts() == null ? 0.0 : b.getGameCounts().doubleValue()
										,b.getAccounts() == null ? 0.0 : b.getAccounts(),NumberUtil.DEFAULT_SCALE );
								avg_total_minutes +=  NumberUtil.div(b.getGameTimes() == null ? 0.0 : b.getGameTimes().doubleValue()
										,b.getAccounts() == null ? 0.0 : b.getAccounts(), NumberUtil.DEFAULT_SCALE);
							}
						}
					}
				}
			}
			
			avg_times = NumberUtil.div(avg_times, monthsCount, NumberUtil.DEFAULT_SCALE);
			avg_total_minutes = NumberUtil.div(avg_total_minutes, monthsCount, NumberUtil.DEFAULT_SCALE);
			
			result.put("data",list);
			result.put("avg_times",avg_times);
			result.put("avg_total_minutes",avg_total_minutes);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "/selectMonthlyGameHourAndTimes", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Behavior> selectMonthlyGameHourAndTimes(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		logger.debug(searchData.toString());
		//log
		Date opDate = new Date();
		String methodName = "selectMonthlyGameHourAndTimes";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerBehaviorService.selectMonthlyGameHourAndTimes(searchData, session);
	}
	
	
	@RequestMapping(value = "/selectDailyGameCounts", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<GameFrequency> selectDailyGameCounts(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		logger.debug(searchData.toString());
		//log
		Date opDate = new Date();
		String methodName = "selectDailyGameCounts";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<GameFrequency> list = null;
		list = gameFrequencyService.selectDailyGameCounts(searchData);
		if(list != null){
			Double totalAccount = 0.0;
			for(GameFrequency gf : list){
				if(gf != null){
					totalAccount += gf.getAccounts() == null ? 0 : gf.getAccounts();
				}
			}
			for(GameFrequency gf : list){
				if(gf != null){
					gf.setRate(NumberUtil.mul(NumberUtil.div(gf.getAccounts() == null ? 0.0 : gf.getAccounts().doubleValue(), totalAccount, NumberUtil.DEFAULT_SCALE)
							, 100.0, NumberUtil.DEFAULT_SCALE));
				}
			}
		}
		return list;
	}
	
	@RequestMapping(value = "/selectWeeklyGameCounts", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<GameFrequency> selectWeeklyGameCounts(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		logger.debug(searchData.toString());
		//log
		Date opDate = new Date();
		String methodName = "selectWeeklyGameCounts";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<GameFrequency> list = null;
		list = gameFrequencyService.selectWeeklyGameCounts(searchData);
		if(list != null){
			Double totalAccount = 0.0;
			for(GameFrequency gf : list){
				if(gf != null){
					totalAccount += gf.getAccounts() == null ? 0 : gf.getAccounts();
				}
			}
			for(GameFrequency gf : list){
				if(gf != null){
					gf.setRate(NumberUtil.mul(NumberUtil.div(gf.getAccounts() == null ? 0.0 : gf.getAccounts().doubleValue(), totalAccount, NumberUtil.DEFAULT_SCALE)
							, 100.0, NumberUtil.DEFAULT_SCALE));
				}
			}
		}
		return list;
	}
	
	@RequestMapping(value = "/selectWeeklyGameDays", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<GameFrequency> selectWeeklyGameDays(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		logger.debug(searchData.toString());
		//log
		Date opDate = new Date();
		String methodName = "selectWeeklyGameDays";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<GameFrequency> list = null;
		list = gameFrequencyService.selectWeeklyGameDays(searchData);
		if(list != null){
			double total = 0.0;
			for(GameFrequency g : list){
				if(g != null){
					total += g.getAccounts() == null ? 0.0 : g.getAccounts();
				}
			}
			for(GameFrequency g : list){
				if(g != null){
					g.setRate(NumberUtil.mul(NumberUtil.div(g.getAccounts() == null ? 0.0 : g.getAccounts().doubleValue(), total), 100.0,NumberUtil.DEFAULT_SCALE));
				}
			}
		}
		return list;
	}
	
	@RequestMapping(value = "/selectMonthlyGameDays", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<GameFrequency> selectMonthlyGameDays(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		logger.debug(searchData.toString());
		//log
		Date opDate = new Date();
		String methodName = "selectMonthlyGameDays";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<GameFrequency> list = null;
		list = gameFrequencyService.selectMonthlyGameDays(searchData);
		if(list != null){
			double total = 0.0;
			for(GameFrequency g : list){
				if(g != null){
					total += g.getAccounts() == null ? 0.0 : g.getAccounts();
				}
			}
			for(GameFrequency g : list){
				if(g != null){
					g.setRate(NumberUtil.mul(NumberUtil.div(g.getAccounts() == null ? 0.0 : g.getAccounts().doubleValue(), total), 100.0,NumberUtil.DEFAULT_SCALE));
				}
			}
		}
		return list;
	}
	
	@RequestMapping(value = "/selectPlayerHoursList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<GameFrequency> selectPlayerHoursList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		logger.debug(searchData.toString());
		//log
		Date opDate = new Date();
		String methodName = "selectPlayerHoursList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<GameFrequency> list = null;
		list = gameFrequencyService.selectPlayerHoursList(searchData);
		if(list != null){
			for(GameFrequency g : list){
				if(g != null){
					g.setStatType(g.getStatdate() +" " + (g.getTimezone() - 1) + "~" + (g.getTimezone() - 1) + ":59");
				}
			}
		}
		return list;
	}
	
}
