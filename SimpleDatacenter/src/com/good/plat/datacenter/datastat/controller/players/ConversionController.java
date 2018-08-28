package com.good.plat.datacenter.datastat.controller.players;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.datastat.entity.players.Conversion;
import com.good.plat.datacenter.datastat.entity.players.PlayersRetention;
import com.good.plat.datacenter.datastat.service.impl.players.ConversionServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: ConversionMapper
 * @Description: 付费转化
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Controller
@RequestMapping("/players/conversion")
public class ConversionController extends BaseController {

	@Autowired
	private ConversionServiceImpl playerConversionService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	/**
	 * 新增付费玩家
	 * @Title: selectNewChargeUserNumList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author moxw
	 * @date 2016年8月3日 下午6:12:35
	 */
	@RequestMapping(value = "/selectNewChargeUserNumList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectNewChargeUserNumList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectNewChargeUserNumList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerConversionService.selectNewChargeUserNumList(searchData,
				session);
	}

	/**
	 * 付费转化
	 * @Title: exportPayTransform
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @throws Exception
	 * void
	 * @author moxw
	 * @date 2016年8月3日 下午6:19:08
	 */
	@RequestMapping(value = "/exportPayTransform", method = {
			RequestMethod.POST, RequestMethod.GET })
	public void exportPayTransform(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportPayTransform", new Date(), searchData);
		
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "付费转化率.xlsx";
		//
		List<Conversion> newPaidPlayerList = selectNewChargeUserNumList(searchData, session);
		List<Conversion> accumulatedPaidPlayerList = selectChargeUserNumList(searchData, session);
		List<Conversion> totalPaidRateList = selectTotalPaidRate(searchData, session);
		//
		if(newPaidPlayerList != null){
			String[] title = new String[] {"日期","新增付费玩家(角色)","新增付费玩家(账号)","付费率(账号)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : newPaidPlayerList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getAccounts();
				items[count++] = e.getNewPayAccounts();
				items[count++] = e.getNewPayAccountsRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("新增付费玩家");
		}
		if(accumulatedPaidPlayerList != null){
			String[] title = new String[] {"日期","累计付费玩家(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : accumulatedPaidPlayerList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getAccounts();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("累计付费玩家");
		}
		if(totalPaidRateList != null){
			String[] title = new String[] {"日期","总体付费率(角色)(%)","总体付费率(账号)(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : totalPaidRateList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getRate();
				items[count++] = e.getPayRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("总体付费率");
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
	 * 付费转化率
	 * @Title: exportPayTransformRate
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @throws Exception
	 * void
	 * @author moxw
	 * @date 2016年8月3日 下午6:18:57
	 */
	@RequestMapping(value = "/exportPayTransformRate", method = {
			RequestMethod.POST, RequestMethod.GET })
	public void exportPayTransformRate(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportPayTransformRate", new Date(), searchData);
		
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "付费转化率.xlsx";
		//
		List<Conversion> firstDayPayRateList = selectFirstDayChargeRate(searchData, session);
		List<Conversion> firstWeekPayRateList = selectFirstWeekChargeRate(searchData, session);
		List<Conversion> firstMonthPayRateList = selectFirstMonthChargeRate(searchData, session);
		
		//
		if(firstDayPayRateList != null){
			String[] title = new String[] {"日期","首日付费玩家数(角色)","首日付费率(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : firstDayPayRateList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getAccounts();
				items[count++] = e.getRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("首日付费率");
		}
		if(firstWeekPayRateList != null){
			String[] title = new String[] {"日期","首周付费玩家数(角色)","首周付费率(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : firstWeekPayRateList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getAccounts();
				items[count++] = e.getRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("首周付费率");
		}
		if(firstMonthPayRateList != null){
			String[] title = new String[] {"日期","首月付费玩家数(角色)","首月付费率(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : firstMonthPayRateList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getAccounts();
				items[count++] = e.getRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("首月付费率");
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
	 * 玩家首付周期
	 * @Title: exportFirstPayPeriod
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @throws Exception
	 * void
	 * @author moxw
	 * @date 2016年8月3日 下午6:18:25
	 */
	@RequestMapping(value = "/exportFirstPayPeriod", method = {
			RequestMethod.POST, RequestMethod.GET })
	public void exportFirstPayPeriod(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportFirstPayPeriod", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "玩家首付周期.xlsx";
		//
		List<Conversion> FirstChargeTotalDayNumList = selectFirstChargeTotalDayNumList(searchData, session);
		FirstChargeTotalDayNumList = FirstChargeTotalDayNumList == null || FirstChargeTotalDayNumList.size() == 0 ? new LinkedList() : FirstChargeTotalDayNumList.get(0).getGamePlaytittle();
		
		//
		if(FirstChargeTotalDayNumList != null){
			String[] title = new String[] {"游戏天数","付费玩家(角色)","百分比"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : FirstChargeTotalDayNumList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getTitle();
				items[count++] = e.getAccounts();
				items[count++] = e.getPercentage();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("游戏天数");
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
	 * 玩家首付等级
	 * @Title: exportFirstPayLevl
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @throws Exception
	 * void
	 * @author moxw
	 * @date 2016年8月3日 下午6:18:39
	 */
	@RequestMapping(value = "/exportFirstPayLevel", method = {
			RequestMethod.POST, RequestMethod.GET })
	public void exportFirstPayLevel(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportFirstPayLevel", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "玩家首付等级.xlsx";
		//
		List<Conversion> firstChargeLevel = selectFirstChargeLevel(searchData, session);
		firstChargeLevel = firstChargeLevel == null || firstChargeLevel.size() == 0 ? new LinkedList() : firstChargeLevel.get(0).getFirstPayByLevel();

		
		//
		if(firstChargeLevel != null){
			String[] title = new String[] {"玩家首付等级","付费玩家(角色)","百分比"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : firstChargeLevel){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getTitle();
				items[count++] = e.getAccounts();
				items[count++] = e.getPercentage();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("游戏天数");
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
	
	@RequestMapping(value = "/exportNewChargeUserNumList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportNewChargeUserNumList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportNewChargeUserNumList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerConversionService.exportNewChargeUserNumList(searchData,
				session);
	}

	
	/**
	 * 累计付费玩家
	 * @Title: selectChargeUserNumList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author moxw
	 * @date 2016年8月3日 下午6:14:45
	 */
	@RequestMapping(value = "/selectChargeUserNumList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectChargeUserNumList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectChargeUserNumList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerConversionService.selectChargeUserNumList(searchData,
				session);
	}

	
	
	@RequestMapping(value = "/exportChargeUserNumList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportChargeUserNumList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportChargeUserNumList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerConversionService.exportChargeUserNumList(searchData,
				session);
	}

	
	
	@RequestMapping(value = "/selectTotalPaidRate", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectTotalPaidRate(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectTotalPaidRate";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerConversionService.selectTotalPaidRate(searchData, session);
	}

	
	
	@RequestMapping(value = "/exportTotalPaidRate", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportTotalPaidRate(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportTotalPaidRate";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerConversionService.exportTotalPaidRate(searchData, session);
	}

	
	/**
	 * 首日付费率
	 * @Title: selectFirstDayChargeRate
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author moxw
	 * @date 2016年8月3日 下午6:19:43
	 */
	@RequestMapping(value = "/selectFirstDayChargeRate", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectFirstDayChargeRate(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectFirstDayChargeRate";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerConversionService.selectFirstDayChargeRate(searchData,
				session);
	}

	
	
	@RequestMapping(value = "/exportFirstDayChargeRate", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportFirstDayChargeRate(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportFirstDayChargeRate";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerConversionService.exportFirstDayChargeRate(searchData,
				session);
	}

	
	/**
	 * 首周付费率
	 * @Title: selectFirstWeekChargeRate
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author moxw
	 * @date 2016年8月3日 下午6:21:00
	 */
	@RequestMapping(value = "/selectFirstWeekChargeRate", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectFirstWeekChargeRate(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectFirstWeekChargeRate";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerConversionService.selectFirstWeekChargeRate(searchData,
				session);
	}

	
	
	@RequestMapping(value = "/exportFirstWeekChargeRate", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportFirstWeekChargeRate(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportFirstWeekChargeRate";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerConversionService.exportFirstWeekChargeRate(searchData,
				session);
	}

	
	
	@RequestMapping(value = "/selectFirstMonthChargeRate", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectFirstMonthChargeRate(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectFirstMonthChargeRate";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerConversionService.selectFirstMonthChargeRate(searchData,
				session);
	}

	
	
	@RequestMapping(value = "/exportFirstMonthChargeRate", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportFirstMonthChargeRate(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportFirstMonthChargeRate";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerConversionService.exportFirstMonthChargeRate(searchData,
				session);
	}

	
	/**
	 * 首付周期-游戏天数
	 * @Title: selectFirstChargeTotalDayNumList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author moxw
	 * @date 2016年8月3日 下午6:52:43
	 */
	@RequestMapping(value = "/selectFirstChargeTotalDayNumList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectFirstChargeTotalDayNumList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectFirstChargeTotalDayNumList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerConversionService.selectFirstChargeTotalDayNumList(
				searchData, session);
	}

	
	
	@RequestMapping(value = "/exportFirstChargeTotalDayNumList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportFirstChargeTotalDayNumList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportFirstChargeTotalDayNumList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerConversionService.exportFirstChargeTotalDayNumList(
				searchData, session);
	}

	
	
	@RequestMapping(value = "/selectFirstChargeTotalTimeList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectFirstChargeTotalTimeList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectFirstChargeTotalTimeList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerConversionService.selectFirstChargeTotalTimeList(
				searchData, session);
	}

	
	
	@RequestMapping(value = "/exportFirstChargeTotalTimeList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportFirstChargeTotalTimeList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportFirstChargeTotalTimeList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerConversionService.exportFirstChargeTotalTimeList(
				searchData, session);
	}

	
	
	@RequestMapping(value = "/selectFirstChargeLevel", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectFirstChargeLevel(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectFirstChargeLevel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<Conversion> list = playerConversionService.selectFirstChargeLevel(searchData,
				session); 
		return list;
	}

	
	
	@RequestMapping(value = "/exportFirstChargeLevel", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportFirstChargeLevel(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportFirstChargeLevel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerConversionService.exportFirstChargeLevel(searchData,
				session);
	}

	
	
	@RequestMapping(value = "/selectFirstChargeMoney", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectFirstChargeMoney(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectFirstChargeMoney";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerConversionService.selectFirstChargeMoney(searchData,
				session);
	}

	
	
	@RequestMapping(value = "/exportFirstChargeMoney", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportFirstChargeMoney(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportFirstChargeMoney";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playerConversionService.exportFirstChargeMoney(searchData,
				session);
	}
	
	/**
	 * 
	 * @Title: selectNumOfFirstPayMoney
	 * @Description: 首付金额用户数
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author hwj
	 * @date 2017-1-12 下午03:57:59
	 */
	@RequestMapping(value = "/selectNumOfFirstPayMoney", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectNumOfFirstPayMoney(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectNumOfFirstPayMoney";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<Conversion> list=null;
		list=playerConversionService.selectNumOfFirstPayMoney(searchData);
		return list;
	}
	/**
	 * 
	 * @Title: selectNumOfFirstPayLevel
	 * @Description: 首付等级分布
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author hwj
	 * @date 2017-1-12 下午04:00:51
	 */
	@RequestMapping(value = "/selectNumOfFirstPayLevel", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectNumOfFirstPayLevel(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectNumOfFirstPayLevel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<Conversion> list=null;
		list=playerConversionService.selectNumOfFirstPayLevel(searchData);
		return list;
	}
	
	/**
	 * 
	 * @Title: selectFirstPayWay
	 * @Description: 首付方式
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author hwj
	 * @date 2017-1-12 下午04:03:09
	 */
	@RequestMapping(value = "/selectFirstPayWay", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectFirstPayWay(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectFirstPayWay";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<Conversion> list=null;
		list=playerConversionService.selectFirstPayWay(searchData);
		return list;
	}
	
	/**
	 * 
	 * @Title: exportFirstPayBehavior
	 * @Description: 导出首付行为
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-1-16 上午10:20:11
	 */
	@RequestMapping(value = "/exportFirstPayBehavior", method = {
			RequestMethod.POST, RequestMethod.GET })
	public void exportFirstPayBehavior(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportFirstPayBehavior", new Date(), searchData);
		
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "首付行为.xlsx";
		//
		List<Conversion> firstPayMoneyList = selectNumOfFirstPayMoney(searchData, session);
		List<Conversion> firstPayLevelList = selectNumOfFirstPayLevel(searchData, session);
		List<Conversion> firstPayWayList =selectFirstPayWay(searchData, session);
		
		//
		if(firstPayMoneyList != null){
			String[] title = new String[] {"额度","0-5","5.01-10","10.01-20","20.01-30","30.01-40","40.01-50","50.01-100","100+"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : firstPayMoneyList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = "首付玩家数(角色)";
				items[count++] =e.getCnt0To5();
				items[count++] =e.getCnt10To20();
				items[count++] =e.getCnt20To30();
				items[count++] =e.getCnt30To40();
				items[count++] =e.getCnt40To50();
				items[count++] =e.getCnt50To100();
				items[count++] =e.getCntHigh100();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("首付金额");
		}
		if(firstPayLevelList != null){
			String[] title = new String[] {"首付等级","1-10","11-20","21-30","31-40","41-50","51-60","61-70","71+"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : firstPayLevelList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = "首付玩家数";
				items[count++] = e.getCntOfLevel1To10();
				items[count++] = e.getCntOfLevel11To20();
				items[count++] = e.getCntOfLevel21To30();
				items[count++] = e.getCntOfLevel31To40();
				items[count++] = e.getCntOfLevel41To50();
				items[count++] = e.getCntOfLevel51To60();
				items[count++] = e.getCntOfLevel61To70();
				items[count++] = e.getCntOfLevelHigh71();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("首付等级分布");
		}
		if(firstPayWayList != null){
			String[] title = new String[] {"首付方式","首付用户数(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : firstPayWayList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getChannelName();
				items[count++] = e.getAccounts();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("首付方式");
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

}
