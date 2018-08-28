package com.good.plat.datacenter.datastat.controller.levels;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.levels.LevelDetail;
import com.good.plat.datacenter.datastat.entity.levels.LevelDistribute;
import com.good.plat.datacenter.datastat.entity.levels.NewPlayerProgress;
import com.good.plat.datacenter.datastat.entity.players.Conversion;
import com.good.plat.datacenter.datastat.service.impl.levels.LevelAnalyticsServiceImpl;
import com.good.plat.datacenter.independ.entity.hie.index.HieMoneyStat;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: LevelAnalyticsController
 * @Description: 等级分析
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Controller
@RequestMapping("/level")
public class LevelAnalyticsController extends BaseController {

	@Autowired
	private LevelAnalyticsServiceImpl levelAnalyticsService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping(value = "/selectLevelStageData", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<LevelDetail> selectLevelStageData(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectLevelStageData";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<LevelDetail> list = levelAnalyticsService.selectLevelStageData(searchData, session);
		if(list != null){
			for(LevelDetail e : list){
				if(e.getTimes() == null){
					e.setTimes(0L);
				}
				e.setTimesDesc(toLocalTimeDesc(e.getTimes()));
				//
				e.setRate(e.getRate()!=null?NumberUtil.div(e.getRate(), 1.0,2):null);
			}
		}
		return list;
	}
	
	public static String toLocalTimeDesc(Long time){
		int hh = 0,mm = 0, ss = 0;
		//ss = e.getTimes() % 60;
		ss = BigInteger.valueOf(time).mod(BigInteger.valueOf(60L)).intValue();
		
		//mm = e.getTimes() % 3600 / 60;
		mm = BigInteger.valueOf(time).mod(BigInteger.valueOf(3600L)).intValue()/60;
		//hh = e.getTimes() / 3600;
		hh = BigInteger.valueOf(time).divide(BigInteger.valueOf(3600L)).intValue();
		return (hh + "h" + mm + "m" + ss+"s");
	}

	@RequestMapping(value = "/exportLevelStageData", method = {
			RequestMethod.POST})
	@ResponseBody
	public void exportLevelStageData(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportLevelStageData", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "等级详情.xlsx";
		//
		List<LevelDetail> levelStageDataList = selectLevelStageData(searchData, session);
		//
		if(levelStageDataList != null){
			String[] title = new String[] {"等级","升级时长","等级停滞人数","等级停滞率(%)","付费次数","付费金额","详情"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(LevelDetail e : levelStageDataList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatType();
				items[count++] = e.getTimesDesc();
				items[count++] = e.getStallcnt();
				items[count++] = e.getRate();
				items[count++] = e.getPayCount();
				items[count++] = e.getPayMoney();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("等级详情");
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

	@RequestMapping(value = "/selectLevelUpTimeDistribution", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<LevelDetail> selectLevelUpTimeDistribution(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectLevelUpTimeDistribution";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return levelAnalyticsService.selectLevelUpTimeDistribution(searchData,
				session);
	}
	
	@RequestMapping(value = "/selectDailyTrend", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String,List<LevelDetail>> selectDailyTrend(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectDailyTrend";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		Map<String,List<LevelDetail>> result = new HashMap<String, List<LevelDetail>>();
		List<LevelDetail> dailyUpgrade,levelPlateau,payTimeAndMoney;
		dailyUpgrade = levelAnalyticsService.selectDailyUpgradeList(searchData,
				session);
		if(dailyUpgrade != null){
			for(LevelDetail e : dailyUpgrade){
				if(e.getTimes() == null){
					e.setTimes(0L);
				}
				e.setTimesDesc(toLocalTimeDesc(e.getTimes()));
			}
		}
		levelPlateau = levelAnalyticsService.selectLevelPlateauList(searchData,
				session);
		if(levelPlateau != null){
			for(LevelDetail e : levelPlateau){
				if(e.getTimes() == null){
					e.setTimes(0L);
				}
				e.setTimesDesc(toLocalTimeDesc(e.getTimes()));
			}
		}
		payTimeAndMoney = levelAnalyticsService.selectPayTimeAndMoneyList(searchData,
				session);
		if(payTimeAndMoney != null){
			for(LevelDetail e : payTimeAndMoney){
				if(e.getTimes() == null){
					e.setTimes(0L);
				}
				e.setTimesDesc(toLocalTimeDesc(e.getTimes()));
			}
		}
		result.put("dailyUpgrade", dailyUpgrade);
		result.put("levelPlateau", levelPlateau);
		result.put("payTimeAndMoney", payTimeAndMoney);
		return result;
	}
	
	@RequestMapping(value = "/selectUpgradeDuration", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<LevelDetail> selectUpgradeDuration(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectUpgradeDuration";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<LevelDetail> list = levelAnalyticsService.selectLevelUpTimeDistribution(searchData,
				session);
		if(list != null){
			double total = 0.0;
			for(LevelDetail o : list){
				if(o.getTimes() == null){
					o.setTimes(0L);
				}
				if(o != null){
					total += o.getTimes();
				}
			}
			for(LevelDetail o : list){
				if(o != null){
					o.setRate(NumberUtil.mul(NumberUtil.div(o.getTimes().doubleValue(), total), 100.0,NumberUtil.DEFAULT_SCALE));
				}
			}
		}
		return list;
	}

	@RequestMapping(value = "/selectRemarkList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<LevelDetail> selectRemarkList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectRemarkList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return levelAnalyticsService.selectRemarkList(searchData, session);
	}
	
	@RequestMapping(value = "/selectDailyUpgradeList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<LevelDetail> selectDailyUpgradeList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectDailyUpgradeList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return levelAnalyticsService.selectDailyUpgradeList(searchData, session);
	}
	
	@RequestMapping(value = "/selectLevelPlateauList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<LevelDetail> selectLevelPlateauList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectLevelPlateauList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return levelAnalyticsService.selectLevelPlateauList(searchData, session);
	}
	
	@RequestMapping(value = "/selectPayTimeAndMoneyList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<LevelDetail> selectPayTimeAndMoneyList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPayTimeAndMoneyList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return levelAnalyticsService.selectPayTimeAndMoneyList(searchData, session);
	}

	@RequestMapping(value = "/selectLevelDistributionOfActivePlayer", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<LevelDistribute> selectLevelDistributionOfActivePlayer(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectLevelDistributionOfActivePlayer";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return levelAnalyticsService.selectLevelDistributionOfActivePlayer(
				searchData, session);
	}
	
	

	@RequestMapping(value = "/exportLevelDistributionOfActivePlayer", method = {
			RequestMethod.POST, RequestMethod.GET })
	public void exportLevelDistributionOfActivePlayer(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportLevelDistributionOfActivePlayer", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "玩家等级分布.xlsx";
		//
		List<LevelDistribute> levelDistributionOfActivePlayerList = selectLevelDistributionOfActivePlayer(searchData, session);
		//
		if(levelDistributionOfActivePlayerList != null){
			String[] title = new String[] {"等级","活跃玩家(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(LevelDistribute e : levelDistributionOfActivePlayerList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatType();
				items[count++] = e.getActivePlayers();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("玩家等级分布");
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

	@RequestMapping(value = "/selectLevelDistributionOfPlayTimes", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<LevelDistribute> selectLevelDistributionOfPlayTimes(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectLevelDistributionOfPlayTimes";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return levelAnalyticsService.selectLevelDistributionOfPlayTimes(
				searchData, session);
	}

	@RequestMapping(value = "/exportLevelDistributionOfPlayTimes", method = {
			RequestMethod.POST })
	public void exportLevelDistributionOfPlayTimes(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
					throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportLevelDistributionOfPlayTimes", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "游戏次数等级分布.xlsx";
		//
		List<LevelDistribute> levelDistributionOfPlayTimesList = selectLevelDistributionOfPlayTimes(searchData, session);
		//
		if(levelDistributionOfPlayTimesList != null){
			String[] title = new String[] {"等级","游戏次数(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(LevelDistribute e : levelDistributionOfPlayTimesList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatType();
				items[count++] = e.getGameCounts();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("游戏次数等级分布");
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
	 * 流失玩家停留等级
	 * @Title: selectLevelOfLossPlayer
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<LevelDistribute>
	 * @author moxw
	 * @date 2016年8月4日 下午5:17:20
	 */
	@RequestMapping(value = "/selectLevelOfLossPlayer", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<LevelDistribute> selectLevelOfLossPlayer(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectLevelOfLossPlayer";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<LevelDistribute> list = null,result = new LinkedList<LevelDistribute>();
		list = levelAnalyticsService.selectLevelOfLossPlayer(searchData,
				session);
		if(list != null){
			for(LevelDistribute o : list){
				if(o != null){
					result.add(o);
				}
			}
		}
		return result;
	}

	@RequestMapping(value = "/exportLevelOfLossPlayer", method = {
			RequestMethod.POST})
	public void exportLevelOfLossPlayer(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
					throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportLevelOfLossPlayer", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "流失玩家停留等级.xlsx";
		//
		List<LevelDistribute> levelOfLossPlayerList = selectLevelOfLossPlayer(searchData, session);
		//
		if(levelOfLossPlayerList != null){
			String[] title = new String[] {"等级","7日未登录玩家(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(LevelDistribute e : levelOfLossPlayerList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatType();
				items[count++] = e.getDay7();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("7日未登录玩家");
		}
		if(levelOfLossPlayerList != null){
			String[] title = new String[] {"等级","7日未登录玩家(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(LevelDistribute e : levelOfLossPlayerList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatType();
				items[count++] = e.getDay14();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("14日未登录玩家");
		}
		if(levelOfLossPlayerList != null){
			String[] title = new String[] {"等级","30日未登录玩家(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(LevelDistribute e : levelOfLossPlayerList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatType();
				items[count++] = e.getDay30();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("30日未登录玩家");
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

	@RequestMapping(value = "/selectFirstNDayLevelPlayerData", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectFirstNDayLevelPlayerData(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectFirstNDayLevelPlayerData";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		Map<String,Object> result = new HashMap<String,Object>();
		List<NewPlayerProgress> list = levelAnalyticsService.selectFirstNDayLevelPlayer(searchData,
				session);
		double avg = 0.0;
		if(list != null){
			double total = 0.0;
			long totalLevelActNum=0l;//(等级*新增玩家数)总和
			for(NewPlayerProgress o : list){
				if(o != null){
					if(o.getCount() == null){
						o.setCount(0);
					}
					long levelAct=o.getLevel()*o.getCount();//等级*新增玩家数
					totalLevelActNum+=levelAct;
					total += o.getCount();
				}
			}
//			avg = NumberUtil.div(total, (double) list.size());
			avg = NumberUtil.div((double)totalLevelActNum,total);
		}
		result.put("data", list);
		result.put("avg", avg);
		return result;
	}
	
	@RequestMapping(value = "/exportNewPlayerLevel", method = {
			RequestMethod.POST})
	public void exportNewPlayerLevel(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
					throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportNewPlayerLevel", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "新玩家等级.xlsx";
		//
		searchData.setChecktype1("0");
		Map<String, Object> Day0LevelPlayerData = selectFirstNDayLevelPlayerData(searchData, session);
		List<NewPlayerProgress> day0LevelPlayerDataList = (List<NewPlayerProgress>) Day0LevelPlayerData.get("data");
		searchData.setChecktype1("6");
		Map<String, Object> Day6LevelPlayerData = selectFirstNDayLevelPlayerData(searchData, session);
		List<NewPlayerProgress> day6LevelPlayerDataList = (List<NewPlayerProgress>) Day6LevelPlayerData.get("data");
		searchData.setChecktype1("13");
		Map<String, Object> Day13LevelPlayerData = selectFirstNDayLevelPlayerData(searchData, session);
		List<NewPlayerProgress> day13LevelPlayerDataList = (List<NewPlayerProgress>) Day13LevelPlayerData.get("data");
		//
		if(day0LevelPlayerDataList != null){
			String[] title = new String[] {"等级","新增付费玩家(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(NewPlayerProgress e : day0LevelPlayerDataList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getLevel();
				items[count++] = e.getCount();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("首日等级");//首周等级 14日等级
		}
		if(day6LevelPlayerDataList != null){
			String[] title = new String[] {"等级","新增付费玩家(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(NewPlayerProgress e : day6LevelPlayerDataList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getLevel();
				items[count++] = e.getCount();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("首周等级");
		}
		if(day13LevelPlayerDataList != null){
			String[] title = new String[] {"等级","新增付费玩家(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(NewPlayerProgress e : day13LevelPlayerDataList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getLevel();
				items[count++] = e.getCount();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("14日等级");
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
	
	@RequestMapping(value = "/selectNDayPlayerLevelChange", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<NewPlayerProgress> selectNDayPlayerLevelChange(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectNDayPlayerLevelChange";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return levelAnalyticsService.selectNDayPlayerLevelChange(searchData,
				session);
	}
	
	//新付费玩家7日内等级变迁
	@RequestMapping(value = "/selectNPayPlayerLevelChange", method = {RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<NewPlayerProgress> selectNPayPlayerLevelChange(@RequestBody BaseSearchData searchData, HttpSession session)throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectNPayPlayerLevelChange";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return levelAnalyticsService.selectNPayPlayerLevelChange(searchData);
	}
	//新付费玩家7日内等级变迁导出
	@RequestMapping(value = "/exportNPayPlayerLevelChangeList", method = {RequestMethod.POST})
	public void exportNPayPlayerLevelChangeList(@RequestBody BaseSearchData searchData,HttpServletResponse response)throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportNPayPlayerLevelChangeList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<NewPlayerProgress> mewPlayerList = levelAnalyticsService.selectNPayPlayerLevelChange(searchData);
		//导出list
		if(mewPlayerList != null){
			String[] title = new String[] {"等级","新增首日","+1日","+2日","+3日","+4日","+5日","+6日"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(NewPlayerProgress e : mewPlayerList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getLevel();
				items[count++] =e.getDay0();
				items[count++] =e.getDay1();
				items[count++] =e.getDay2();
				items[count++] =e.getDay3();
				items[count++] =e.getDay4();
				items[count++] =e.getDay5();
				items[count++] =e.getDay6();
				datalist.add(items);
			}
			list.add(datalist);
		}
		work(response, list, "新付费玩家7日内等级变迁", "新付费玩家7日内等级变迁.xlsx");
	}
	@RequestMapping(value = "/exportNDayPlayerLevelChange", method = {
			RequestMethod.POST})
	public void exportNDayPlayerLevelChange(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
					throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportNDayPlayerLevelChange", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "新玩家7日内等级变迁.xlsx";
		//
		searchData.setChecktype1("0");
		List<NewPlayerProgress> NDayPlayerLevelChangeList = selectNDayPlayerLevelChange(searchData, session);
		//
		if(NDayPlayerLevelChangeList != null){
			String[] title = new String[] {"等级","新增首日","+1日","+2日","+3日","+4日","+5日","+6日"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(NewPlayerProgress e : NDayPlayerLevelChangeList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getLevel();
				items[count++] = e.getDay0();
				items[count++] = e.getDay1();
				items[count++] = e.getDay2();
				items[count++] = e.getDay3();
				items[count++] = e.getDay4();
				items[count++] = e.getDay5();
				items[count++] = e.getDay6();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("新玩家7日内等级变迁");//首周等级 14日等级
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
	 * @Title: selectPlayerCntOfLevel
	 * @Description: 等级玩家数
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<LevelDistribute>
	 * @author hwj
	 * @date 2017-1-17 上午09:32:20
	 */
	@RequestMapping(value = "/selectPlayerCntOfLevelList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<LevelDistribute> selectPlayerCntOfLevelList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPlayerCntOfLevelList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//取当前日期
		Date date=new Date();
		date=DateUtil.add(date, Calendar.DAY_OF_YEAR, -1);
		String currDate=DateUtil.format_yyyy_MM_dd(date);
		searchData.setCurrDate(currDate);
		List<LevelDistribute> list = levelAnalyticsService.selectPlayerCntOfLevel(searchData);
		return list;
	}
	
	@RequestMapping(value = "/exportPlayerCntOfLevelList", method = {
			RequestMethod.POST})
	public void exportPlayerCntOfLevelList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
					throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportPlayerCntOfLevelList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "玩家数等级分布.xlsx";
		//
		List<LevelDistribute> playerCntOfLevelList = selectPlayerCntOfLevelList(searchData, session);
		//
		if(playerCntOfLevelList != null){
			String[] title = new String[] {"等级","时段等级玩家数(角色)","当前等级玩家数(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(LevelDistribute e : playerCntOfLevelList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatType();
				items[count++] = e.getDurationLevPlayCnt();
				items[count++] = e.getCurrLevPlayCnt();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("玩家数等级分布");//首周等级 14日等级
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
//******************************************************************************************************************************
	//导出抽取
	private void work(HttpServletResponse response, List<List<Object[]>> list, String sheetNameListName,
			String filename) throws IOException {
		//
		List<String> sheetNameList = new LinkedList<String>();
		sheetNameList.add(sheetNameListName);
		Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
		ExcelGenerator.fillWorkBook(w,sheetNameList, list);
		//
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		w.write(baos);
		String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
		HTTPUtil.responseFile(response, request, contentType , filename, baos);
	}
}
