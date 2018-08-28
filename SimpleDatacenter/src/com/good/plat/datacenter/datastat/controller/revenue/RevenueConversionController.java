package com.good.plat.datacenter.datastat.controller.revenue;

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
import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.base.Validator;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.revenue.Conversion;
import com.good.plat.datacenter.datastat.entity.revenue.IncomeData;
import com.good.plat.datacenter.datastat.service.impl.revenue.ConversionServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: ConversionController
 * @Description: 付费渗透
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Controller
@RequestMapping("/revenue/conversion")
public class RevenueConversionController extends BaseController {
	Logger log = Logger.getLogger(getClass());

	@Autowired
	private ConversionServiceImpl revenueConversionService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping(value = "/exportPayRate", method = {
			RequestMethod.POST})
	public void exportPayRate(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportPayRate", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "付费率.xlsx";
		//
		Map<String, Object> dayPayRate = selectDayPayRate(searchData, session);
		Map<String, Object> weekPayRate = selectWeekPayRate(searchData, session);
		Map<String, Object> monthPayRate = selectMonthPayRate(searchData, session);
		List<Conversion> dayPayRateList = (List<Conversion>) dayPayRate.get("dayPayRateList");
		List<Conversion> weekPayRateList = (List<Conversion>) weekPayRate.get("weekPayRateList");
		List<Conversion> monthPayRateList = (List<Conversion>) monthPayRate.get("monthPayRateList");
		
		//
		if(dayPayRateList != null){
			String[] title = new String[] {"日期","日付费率(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : dayPayRateList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getDailyPayRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("日付费率");
		}
		if(weekPayRateList != null){
			String[] title = new String[] {"日期","周付费率(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : weekPayRateList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getWeeklyPayRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("周付费率");
		}
		if(monthPayRateList != null){
			String[] title = new String[] {"日期","月付费率(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : monthPayRateList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getMonthPayRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("月付费率");
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
	
	@RequestMapping(value = "/selectDayPayRate", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectDayPayRate(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		//log
		Date opDate = new Date();
		String methodName = "selectDayPayRate";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		try{
			log.debug(searchData);
		
			
			List<Conversion> dayPayRateList = null;
			
			
			Double avg = 0.0;
			//
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
			
			//
			dayPayRateList = selectDayPayRateList(searchData, session);
			if(dayPayRateList != null){
				Double total = 0.0;
				for(Conversion c : dayPayRateList){
					if(c.getDailyPayRate() != null){
						c.setDailyPayRate(NumberUtil.mul(c.getDailyPayRate(), 100.0,NumberUtil.DEFAULT_SCALE));
						total += c.getDailyPayRate();
					}
				}
				avg = NumberUtil.div(total, dayscount,NumberUtil.DEFAULT_SCALE);
			}
			
			//
			result.put("dayPayRateList", dayPayRateList);
			result.put("avg", avg);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "/selectDayPayRateList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectDayPayRateList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		List<Conversion> list = new ArrayList<Conversion>();
		//log
		Date opDate = new Date();
		String methodName = "selectDayPayRateList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		try{
			log.debug(searchData);
			
			
			list = revenueConversionService.selectDayPayRateList(searchData, session);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	
	@RequestMapping(value = "/exportDayPayRateList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportDayPayRateList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportDayPayRateList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.exportDayPayRateList(searchData, session);
	}

	@RequestMapping(value = "/selectWeekPayRate", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectWeekPayRate(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
 		Map<String,Object> result = new HashMap<String, Object>();
 		//log
		Date opDate = new Date();
		String methodName = "selectWeekPayRate";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		try{
			
			
			log.debug(searchData);
			List<Conversion> weekPayRateList = null;
			//
			Double weekscount = 0.0;
			List<Date[]> datePairList = DateUtil.getContainingWeekList(DateUtil.parse_yyyy_MM_dd(searchData.getStartdate())
					, DateUtil.parse_yyyy_MM_dd(searchData.getEnddate()));
			if(datePairList != null){
				weekscount = (double) datePairList.size();
			}
			//
			weekPayRateList = selectWeekPayRateList(searchData, session);
			
			Double avg = 0.0;
			//
			
			if(weekPayRateList != null){
				Double total = 0.0;
				for(Conversion c : weekPayRateList){
					if(c.getWeeklyPayRate() != null){
						total += c.getWeeklyPayRate();
					}
				}
				avg = NumberUtil.div(total, weekscount * DateUtil.DAYS_OF_WEEK,NumberUtil.DEFAULT_SCALE);
				//avg = NumberUtil.div(total, (double) weekPayRateList.size(),NumberUtil.DEFAULT_SCALE);
			}
			
			//
			result.put("weekPayRateList", weekPayRateList);
			
			result.put("avg", avg);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "/selectWeekPayRateList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectWeekPayRateList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		List<Conversion> list = new ArrayList<Conversion>();
		//log
		Date opDate = new Date();
		String methodName = "selectWeekPayRateList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		try{
			
			
			List<Date[]> datePairList = DateUtil.getContainingWeekList(DateUtil.parse_yyyy_MM_dd(searchData.getStartdate())
					, DateUtil.parse_yyyy_MM_dd(searchData.getEnddate()));
			if(datePairList != null){
				//dates 最后一个 Date 是 有效周的下一个自然周的第一天
				for(int i = 0;i < datePairList.size();i++){
					searchData.setStartdate(DateUtil.format_yyyy_MM_dd(datePairList.get(i)[0]));//mysql between a and b 包含 a 和 b;
					searchData.setEnddate(DateUtil.format_yyyy_MM_dd(datePairList.get(i)[1]));
					List<Conversion> list2 = revenueConversionService.selectWeekPayRateList(searchData, session); 
					if(list2 != null){
						for(Conversion c : list2){
							if(c != null){
								c.setStatdate(DateUtil.format_yyyy_MM_dd(datePairList.get(i)[0]) + "~" + DateUtil.format_yyyy_MM_dd(datePairList.get(i)[1]));
								list.add(c);
							}
						}
					}
				}
				if(list != null){
					for(Conversion c : list){
						if(c.getWeeklyPayRate() != null){
							c.setWeeklyPayRate(NumberUtil.mul(c.getWeeklyPayRate(), 100.0, NumberUtil.DEFAULT_SCALE));
						}
					}
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list; 
	}

	
	@RequestMapping(value = "/exportWeekPayRateList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportWeekPayRateList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportWeekPayRateList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.exportWeekPayRateList(searchData, session);
	}
	
	@RequestMapping(value = "/selectMonthPayRate", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectMonthPayRate(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		log.debug(searchData);
		//log
		Date opDate = new Date();
		String methodName = "selectMonthPayRate";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		try{
			
			
			List<Conversion> monthPayRateList = null;
			Double avg = 0.0;
			
			//
			Double dayscount = (double) DateUtil.daysCount(DateUtil.parse_yyyy_MM_dd(searchData.getEnddate())
					,DateUtil.parse_yyyy_MM_dd(searchData.getStartdate()));
			//
			monthPayRateList = selectMonthPayRateList(searchData, session);
			if(monthPayRateList != null){
				Double total = 0.0;
				for(Conversion c : monthPayRateList){
					if(c.getMonthPayRate() != null){
						total += c.getMonthPayRate();
					}
				}
				avg = NumberUtil.div(total, dayscount,NumberUtil.DEFAULT_SCALE);
			}
			
			//
			result.put("monthPayRateList", monthPayRateList);
			
			result.put("avg", avg);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "/selectMonthPayRateList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectMonthPayRateList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		List<Conversion> list = new ArrayList<Conversion>();
		//log
		Date opDate = new Date();
		String methodName = "selectMonthPayRateList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		try{
		
			
			List<Date[]> dateList = DateUtil.getContainingMonth(DateUtil.parse_yyyy_MM_dd(searchData.getStartdate())
					, DateUtil.parse_yyyy_MM_dd(searchData.getEnddate())); 
			if(dateList != null){
				for(int i = 0;i < dateList.size();i++){
					List<Conversion> tlist = null;
					searchData.setStartdate(DateUtil.format_yyyy_MM_dd(dateList.get(i)[0]));
					searchData.setEnddate(DateUtil.format_yyyy_MM_dd(dateList.get(i)[1]));
					tlist = revenueConversionService.selectMonthPayRateList(searchData, session);
					//
					for(Conversion c : tlist){
						if(c != null){
							c.setStatdate(DateUtil.format_yyyy_MM(dateList.get(i)[0]));
							list .add(c);
						}
					}
				}
				if(list != null){
					for(Conversion c : list){
						if(c.getMonthPayRate() != null){
							c.setMonthPayRate(NumberUtil.mul(c.getMonthPayRate(), 100.0, NumberUtil.DEFAULT_SCALE));
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	
	@RequestMapping(value = "/exportMonthPayRateList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportMonthPayRateList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportMonthPayRateList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.exportMonthPayRateList(searchData, session);
	}
	
	/**
	 * 付费等级
	 * @Title: selectActorLevelPaymentList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author moxw
	 * @date 2016年8月4日 下午7:02:02
	 */
	@RequestMapping(value = "/selectActorLevelPaymentList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectActorLevelPaymentList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectActorLevelPaymentList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.selectActorLevelPaymentList(searchData, session);
	}
	
	/**
	 * 付费等级
	 * @Title: exportActorLevelPaymentList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author moxw
	 * @date 2016年8月4日 下午7:02:06
	 */
	@RequestMapping(value = "/exportActorLevelPaymentList", method = { RequestMethod.POST })
	@ResponseBody
	public void exportActorLevelPaymentList(@RequestBody BaseSearchData searchData,
			HttpSession session,HttpServletResponse response) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportActorLevelPaymentList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "付费等级.xlsx";
		//
		List<Conversion> actorLevelPaymentList = selectActorLevelPaymentList(searchData, session);
		//
		if(actorLevelPaymentList != null){
			String[] title = new String[] {"等级","收入金额(￥)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : actorLevelPaymentList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getActorLevel();
				items[count++] = e.getMoney();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("收入金额");
		}
		if(actorLevelPaymentList != null){
			String[] title = new String[] {"等级","充值人次"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : actorLevelPaymentList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getQuantity();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("充值人次");
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
	
	@RequestMapping(value = "/selectActorLevelRechargeTimeList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectActorLevelRechargeTimeList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectActorLevelRechargeTimeList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.selectActorLevelRechargeTimeList(searchData, session);
	}
	
	@RequestMapping(value = "/exportActorLevelRechargeTimeList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportActorLevelRechargeTimeList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportActorLevelRechargeTimeList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.exportActorLevelRechargeTimeList(searchData, session);
	}
	
	@RequestMapping(value = "/selectChannelIncomeList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectChannelIncomeList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		List<Conversion> list = new ArrayList<Conversion>();
		//log
		Date opDate = new Date();
		String methodName = "selectChannelIncomeList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		try{
			
			
			list = revenueConversionService.selectChannelIncomeList(searchData, session);
			if(list != null){
				Double total = 0.0;
				for(Conversion c : list){
					if(c != null){
						total += c.getMoney();
					}
				}
				for(Conversion c : list){
					if(c != null){
						c.setRate(NumberUtil.mul(NumberUtil.div(c.getMoney(), total, NumberUtil.DEFAULT_SCALE), 100.0, NumberUtil.DEFAULT_SCALE));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = "/exportChannelIncomeList", method = { RequestMethod.POST})
	public void exportChannelIncomeList(@RequestBody BaseSearchData searchData,
			HttpSession session,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportChannelIncomeList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "渠道收入.xlsx";
		//
		List<Conversion> firstChargeLevel = selectChannelIncomeList(searchData, session);
		
		//
		if(firstChargeLevel != null){
			String[] title = new String[] {"渠道","收入(￥)","百分比(账户)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : firstChargeLevel){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getChannelName();
				items[count++] = e.getMoney();
				items[count++] = e.getRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("渠道收入");
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

	@RequestMapping(value = "/selectCityIncomeList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectCityIncomeList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		List<Conversion> list = new ArrayList<Conversion>();
		//log
		Date opDate = new Date();
		String methodName = "selectCityIncomeList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		try{
			
			
			list = revenueConversionService.selectCityIncomeList(searchData, session);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	@RequestMapping(value = "/selectCityAccountsList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectCityAccountsList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		List<Conversion> list = new ArrayList<Conversion>();
		//log
		Date opDate = new Date();
		String methodName = "selectCityAccountsList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		try{
			list = revenueConversionService.selectCityAccountsList(searchData, session);
			if(list != null){
				Double total = 0.0;
				for(Conversion c : list){
					if(c != null){
						total += c.getQuantity();
					}
				}
				for(Conversion c : list){
					if(c != null){
						c.setRate(NumberUtil.mul(NumberUtil.div(c.getQuantity(), total, NumberUtil.DEFAULT_SCALE), 100.0, NumberUtil.DEFAULT_SCALE));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = "/exportCityAccountsList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportCityAccountsList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportCityAccountsList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.exportCityAccountsList(searchData, session);
	}
	

	@RequestMapping(value = "/exportCityIncomeList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportCityIncomeList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportCityIncomeList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.exportChannelIncomeList(searchData, session);
	}
	@RequestMapping(value = "/selectDayARPUList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectDayARPUList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectDayARPUList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.selectDayARPUList(searchData, session);
	}

	
	@RequestMapping(value = "/exportDayARPUList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportDayARPUList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportDayARPUList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.exportDayARPUList(searchData, session);
	}

	
	@RequestMapping(value = "/selectMonthARPUList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectMonthARPUList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectMonthARPUList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.selectMonthARPUList(searchData, session);
	}

	
	@RequestMapping(value = "/exportMonthARPUList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportMonthARPUList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportMonthARPUList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.exportMonthARPUList(searchData, session);
	}

	
	@RequestMapping(value = "/selectDayARPPUList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectDayARPPUList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectDayARPPUList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.selectDayARPPUList(searchData, session);
	}

	
	@RequestMapping(value = "/exportDayARPPUList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportDayARPPUList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportDayARPPUList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.exportDayARPPUList(searchData, session);
	}

	
	@RequestMapping(value = "/selectMonthARPPUList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectMonthARPPUList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectMonthARPPUList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.selectMonthARPPUList(searchData, session);
	}

	
	@RequestMapping(value = "/exportMonthARPPUList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportMonthARPPUList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportMonthARPPUList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.exportMonthARPPUList(searchData, session);
	}

	
	@RequestMapping(value = "/selectLocationOfDailyRateOfPay", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectLocationOfDailyRateOfPay(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectLocationOfDailyRateOfPay";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.selectLocationOfDailyRateOfPay(searchData, session);
	}

	
	@RequestMapping(value = "/exportLocationOfDailyRateOfPay", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportLocationOfDailyRateOfPay(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportLocationOfDailyRateOfPay";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.exportLocationOfDailyRateOfPay(searchData, session);
	}

	
	@RequestMapping(value = "/selectLocationOfDailyARPU", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectLocationOfDailyARPU(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectLocationOfDailyARPU";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.selectLocationOfDailyARPU(searchData, session);
	}

	
	@RequestMapping(value = "/exportLocationOfDailyARPU", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportLocationOfDailyARPU(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportLocationOfDailyARPU";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.exportLocationOfDailyARPU(searchData, session);
	}

	
	@RequestMapping(value = "/selectLocationOfDailyARPPU", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectLocationOfDailyARPPU(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectLocationOfDailyARPPU";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.selectLocationOfDailyARPPU(searchData, session);
	}

	
	@RequestMapping(value = "/exportLocationOfDailyARPPU", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportLocationOfDailyARPPU(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportLocationOfDailyARPPU";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.exportLocationOfDailyARPPU(searchData, session);
	}

	
	@RequestMapping(value = "/selectChannelOfDailyRateOfPay", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectChannelOfDailyRateOfPay(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectChannelOfDailyRateOfPay";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.selectChannelOfDailyRateOfPay(searchData, session);
	}

	
	@RequestMapping(value = "/exportChannelOfDailyRateOfPay", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportChannelOfDailyRateOfPay(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportChannelOfDailyRateOfPay";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.exportChannelOfDailyRateOfPay(searchData, session);
	}

	
	@RequestMapping(value = "/selectChannelOfDailyARPU", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectChannelOfDailyARPU(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectChannelOfDailyARPU";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.selectChannelOfDailyARPU(searchData, session);
	}

	
	@RequestMapping(value = "/exportChannelOfDailyARPU", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportChannelOfDailyARPU(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportChannelOfDailyARPU";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.exportChannelOfDailyARPU(searchData, session);
	}

	
	@RequestMapping(value = "/selectChannelOfDailyARPPU", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> selectChannelOfDailyARPPU(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectChannelOfDailyARPPU";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.selectChannelOfDailyARPPU(searchData, session);
	}

	
	@RequestMapping(value = "/exportChannelOfDailyARPPU", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Conversion> exportChannelOfDailyARPPU(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportChannelOfDailyARPPU";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return revenueConversionService.exportChannelOfDailyARPPU(searchData, session);
	}
	/**
	 * 日付费率（按账号）
	 * @Title: selectDayPayRateOfAccoutList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-9-29 下午01:36:24
	 */
	@RequestMapping(value = "/selectDayPayRateOfAccoutList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectDayPayRateOfAccoutList(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectDayPayRateOfAccoutList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		Map<String,Object> result=new HashMap<String, Object>();
		List<Conversion> dayPayRateList=null;
		Double avg = 0.0;
		//
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
		//
		dayPayRateList=revenueConversionService.selectDayPayRateOfAccoutList(searchData);
		if(dayPayRateList != null){
			Double total = 0.0;
			for(Conversion c : dayPayRateList){
				if(c.getDailyPayRate() != null){
					c.setDailyPayRate(NumberUtil.mul(c.getDailyPayRate(), 100.0,NumberUtil.DEFAULT_SCALE));
					total += c.getDailyPayRate();
				}else{
					c.setDailyPayRate(0.0);
				}
			}
			avg = NumberUtil.div(total, dayscount,NumberUtil.DEFAULT_SCALE);
		}
		//
		result.put("dayPayRateList", dayPayRateList);
		result.put("avg", avg);
		return result;
	}
	
	/**
	 * 周付费率(按账号)
	 * @Title: selectWeekPayRateOfAccoutList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 *List<Conversion>
	 * @author hwj
	 * @date 2017-10-9 上午09:43:39
	 */
	@RequestMapping(value = "/selectWeekPayRateOfAccoutList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> selectWeekPayRateOfAccoutList(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectWeekPayRateOfAccoutList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<Conversion> weekPayRateList=new ArrayList<Conversion>();
		Double avg = 0.0;
		Map<String, Object> result=new HashMap<String, Object>();
		//
		try {
			List<Date[]> datePairList = DateUtil.getContainingWeekList(DateUtil.parse_yyyy_MM_dd(searchData.getStartdate())
					, DateUtil.parse_yyyy_MM_dd(searchData.getEnddate()));
			//
			Double weekscount = 0.0;
			if(datePairList != null){
				weekscount = (double) datePairList.size();
			}
			if(datePairList != null){
				//dates 最后一个 Date 是 有效周的下一个自然周的第一天
				for(int i = 0;i < datePairList.size();i++){
					searchData.setStartdate(DateUtil.format_yyyy_MM_dd(datePairList.get(i)[0]));//mysql between a and b 包含 a 和 b;
					searchData.setEnddate(DateUtil.format_yyyy_MM_dd(datePairList.get(i)[1]));
					List<Conversion> list2 = revenueConversionService.selectWeekPayRateOfAccoutList(searchData);
					if(list2 != null){
						for(Conversion c : list2){
							if(c != null){
								c.setStatdate(DateUtil.format_yyyy_MM_dd(datePairList.get(i)[0]) + "~" + DateUtil.format_yyyy_MM_dd(datePairList.get(i)[1]));
								weekPayRateList.add(c);
							}
						}
					}
				}
				//
				if(weekPayRateList != null){
					for(Conversion c : weekPayRateList){
						if(c.getWeeklyPayRate() != null){
							c.setWeeklyPayRate(NumberUtil.mul(c.getWeeklyPayRate(), 100.0, NumberUtil.DEFAULT_SCALE));
						}
					}
				}
			}
			if(weekPayRateList != null){
				Double total = 0.0;
				for(Conversion c : weekPayRateList){
					if(c.getWeeklyPayRate() != null){
						total += c.getWeeklyPayRate();
					}
				}
				avg = NumberUtil.div(total, weekscount * DateUtil.DAYS_OF_WEEK,NumberUtil.DEFAULT_SCALE);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		result.put("weekPayRateList", weekPayRateList);
		result.put("avg", avg);
		return result;
	}
	
	/**
	 * 月付费率（按账号）
	 * @Title: selectMonthPayRateOfAccoutList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-10-9 上午10:44:37
	 */
	@RequestMapping(value = "/selectMonthPayRateOfAccoutList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> selectMonthPayRateOfAccoutList(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectMonthPayRateOfAccoutList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<Conversion> monthPayRateList=new ArrayList<Conversion>();
		Double avg = 0.0;
		Map<String, Object> result=new HashMap<String, Object>();
		//
		try {
			//自然月对数
			List<Date[]> datePairList = DateUtil.getContainingMonth(DateUtil.parse_yyyy_MM_dd(searchData.getStartdate())
					, DateUtil.parse_yyyy_MM_dd(searchData.getEnddate()));
			//天数
			Double dayscount = (double) DateUtil.daysCount(DateUtil.parse_yyyy_MM_dd(searchData.getEnddate())
					,DateUtil.parse_yyyy_MM_dd(searchData.getStartdate()));
			//
			if(datePairList != null){
				//dates 最后一个 Date 是 有效月的下一个自然月的第一天
				for(int i = 0;i < datePairList.size();i++){
					searchData.setStartdate(DateUtil.format_yyyy_MM_dd(datePairList.get(i)[0]));//mysql between a and b 包含 a 和 b;
					searchData.setEnddate(DateUtil.format_yyyy_MM_dd(datePairList.get(i)[1]));
					List<Conversion> list2 = revenueConversionService.selectMonthPayRateOfAccoutList(searchData);
					if(list2 != null){
						for(Conversion c : list2){
							if(c != null){
								c.setStatdate(DateUtil.format_yyyy_MM(datePairList.get(i)[0]));
								monthPayRateList.add(c);
							}
						}
					}
				}
				//
				if(monthPayRateList != null){
					for(Conversion c : monthPayRateList){
						if(c.getWeeklyPayRate() != null){
							c.setWeeklyPayRate(NumberUtil.mul(c.getWeeklyPayRate(), 100.0, NumberUtil.DEFAULT_SCALE));
						}
					}
				}
			}
			if(monthPayRateList != null){
				Double total = 0.0;
				for(Conversion c : monthPayRateList){
					if(c.getWeeklyPayRate() != null){
						total += c.getWeeklyPayRate();
					}
				}
				avg = NumberUtil.div(total, dayscount,NumberUtil.DEFAULT_SCALE);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		result.put("monthPayRateList", monthPayRateList);
		result.put("avg", avg);
		return result;
	}
	/**
	 * 导出付费率（基于账号）
	 * @Title: exportPayRateOfAccout
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-10-9 上午11:10:27
	 */
	@RequestMapping(value = "/exportPayRateOfAccout", method = {
			RequestMethod.POST})
	public void exportPayRateOfAccout(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportPayRateOfAccout", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "付费率.xlsx";
		//
		Map<String, Object> dayPayRate = selectDayPayRateOfAccoutList(searchData, session);
		Map<String, Object> weekPayRate = selectWeekPayRateOfAccoutList(searchData, session);
		Map<String, Object> monthPayRate = selectMonthPayRateOfAccoutList(searchData, session);
		List<Conversion> dayPayRateList = (List<Conversion>) dayPayRate.get("dayPayRateList");
		List<Conversion> weekPayRateList = (List<Conversion>) weekPayRate.get("weekPayRateList");
		List<Conversion> monthPayRateList = (List<Conversion>) monthPayRate.get("monthPayRateList");
		
		//
		if(dayPayRateList != null){
			String[] title = new String[] {"日期","日付费率(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : dayPayRateList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getDailyPayRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("日付费率");
		}
		if(weekPayRateList != null){
			String[] title = new String[] {"日期","周付费率(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : weekPayRateList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getWeeklyPayRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("周付费率");
		}
		if(monthPayRateList != null){
			String[] title = new String[] {"日期","月付费率(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Conversion e : monthPayRateList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getMonthPayRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("月付费率");
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
