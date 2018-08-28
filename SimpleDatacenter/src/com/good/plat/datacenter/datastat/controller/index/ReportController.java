package com.good.plat.datacenter.datastat.controller.index;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.concurrent.ConcurrentData;
import com.good.plat.datacenter.datastat.entity.index.Report;
import com.good.plat.datacenter.datastat.entity.index.SummaryData;
import com.good.plat.datacenter.datastat.service.index.ReportService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * 运营报表
 * @ClassName: ReportController
 * @Description: TODO
 * @author hwj
 * @date 2017-2-10 下午05:28:03
 */
@Controller
@RequestMapping("/index/report")
public class ReportController {
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value="/selectDailyReportList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Report> selectDailyReportList (@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectDailyReportList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		Map<String,Report> map=reportService.selectDailyReport(searchData);
		List<Report> reportList=new ArrayList<Report>();
		List<String> list=sort(map);
		for(String date:list){
			reportList.add(map.get(date));
		}
		return reportList;
	}
	
	@RequestMapping(value = "/exportDailyReportList", method = {
			RequestMethod.POST})
	public void exportDailyReportList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportDailyReportList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "基础数据.xlsx";
		List<Report> dailyReportList=selectDailyReportList(searchData,session);
		//
		if(dailyReportList != null){
			String[] title = new String[] {"日期","新增设备","新增账号","新增角色","玩家转化率（％）","活跃玩家(角色)","ACU","PCU","游戏次数","平均日游戏时长（分）","平均日游戏次数","设备次日留存","设备3日留存","设备7日留存","新增玩家次日留存","新增玩家3日留存","新增玩家7日留存","活跃玩家次日留存","活跃玩家3日留存","活跃玩家7日留存"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Report e : dailyReportList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getNew_device();
				items[count++] =e.getNewUser();
				items[count++] =e.getNewActor();
				items[count++] =e.getPlayerConv();
				items[count++] =e.getActv_user();
				items[count++] =e.getAcu();
				items[count++] =e.getPcu();
				items[count++] =e.getGameCounts();
				items[count++] =e.getAvgGameTime();
				items[count++] =e.getAvgGameCount();
				items[count++] =e.getDeviceDay2();
				items[count++] =e.getDeviceDay3();
				items[count++] =e.getDeviceDay7();
				items[count++] =e.getNewuserDay2();
				items[count++] =e.getNewuserDay3();
				items[count++] =e.getActvuserDay7();
				items[count++] =e.getActvuserDay2();
				items[count++] =e.getActvuserDay3();
				items[count++] =e.getActvuserDay7();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("基础数据");
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
	 * 付费数据
	 * @Title: selectPayDataList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Report>
	 * @author hwj
	 * @date 2017-2-13 下午03:26:11
	 */
	@RequestMapping(value="/selectPayDataList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Report> selectPayDataList (@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPayDataList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<Report> payDataList=reportService.selectPayDataList(searchData);
		return payDataList;
	}
	
	/**
	 * 导出付费数据
	 * @Title: exportPayDataList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-2-13 下午04:10:30
	 */
	@RequestMapping(value = "/exportPayDataList", method = {
			RequestMethod.POST})
	public void exportPayDataList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportPayDataList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "付费数据.xlsx";
		List<Report> payDataList=selectPayDataList(searchData, session);
		//
		if(payDataList != null){
			String[] title = new String[] {"日期","收入（¥）","新增付费玩家数量(角色)","付费玩家数量(角色)","活跃付费率（％）","新增ARPU","活跃ARPU","新增ARPPU","活跃ARPPU"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Report e : payDataList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getMoney();
				items[count++] =e.getNpayact();
				items[count++] =e.getPayact();
				items[count++] =e.getdPayRate();
				items[count++] =e.getNew_arpu();
				items[count++] =e.getAct_arpu();
				items[count++] =e.getNew_arppu();
				items[count++] =e.getAct_arppu();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("付费数据");
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
	 * 累计数据
	 * @Title: selectTotalDataList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Report>
	 * @author hwj
	 * @date 2017-2-13 下午03:28:14
	 */
	@RequestMapping(value="/selectTotalDataList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Report> selectTotalDataList (@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectTotalDataList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<Report> totalDataList=reportService.selectTotalDataList(searchData);
		return totalDataList;
	}
	
	/**
	 * 导出累计数据
	 * @Title: exportTotalDataList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-2-13 下午04:02:28
	 */
	@RequestMapping(value = "/exportTotalDataList", method = {
			RequestMethod.POST})
	public void exportTotalDataList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportTotalDataList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "累计数据.xlsx";
		List<Report> totalDataList=selectTotalDataList(searchData, session);
		//
		if(totalDataList != null){
			String[] title = new String[] {"日期","累计激活设备","累计新增玩家(账号)","累计付费玩家(角色数)","总体付费率（％）","累计收入金额（¥）","累计ARPU","累计ARPPU"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Report e : totalDataList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getNew_device();
				items[count++] =e.getNewUser();
				items[count++] =e.getPayact();
				items[count++] =e.getdPayRate();
				items[count++] =e.getMoney();
				items[count++] =e.getAct_arpu();
				items[count++] =e.getAct_arppu();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("累计数据");
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
	 * 周报（基础数据）
	 * @Title: selectWeekBaseDataList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Report>
	 * @author hwj
	 * @date 2017-2-14 下午02:53:01
	 */
	@RequestMapping(value="/selectWeekBaseDataList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Report> selectWeekBaseDataList (@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectWeekBaseDataList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<Report> weekDataList=reportService.selectWeekBaseDataList(searchData);
		Report currReport=null;
		if(weekDataList!=null){
			for(int i=0;i<weekDataList.size();i++){
				if(i==0){
					currReport=weekDataList.get(i);
				}
				if(i==1){
					Report preReport=weekDataList.get(i);
					preReport.setFlag(0);
					Report result=ComparePre(currReport,preReport);
					weekDataList.add(i, result);
				}
			}
		}
		return weekDataList;
	}
	
	/**
	 * 导出周报或者月报累计数据
	 * @Title: exportWeekBaseDataList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-2-20 上午10:49:59
	 */
	@RequestMapping(value = "/exportWeekOrMonthBaseDataList", method = {
			RequestMethod.POST})
	public void exportWeekOrMonthBaseDataList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportWeekOrMonthBaseDataList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "周报累计数据.xlsx";
		//导出周报
		if(Integer.parseInt(searchData.getChecktype1())==0){
			filename="周报累计数据.xlsx";
			List<Report> totalDataList=selectWeekBaseDataList(searchData, session);
			//
			if(totalDataList != null){
				String[] title = new String[] {"日期","周设备激活设备","周新增玩家数(账号)","周活跃玩家数(角色)","周新增玩家次日留存率","周平均游戏时长","周平均游戏次数","周平均每次游戏时长","周流失玩家数量(角色)","周流失率","周回流玩家数","周总收入金额","周充值玩家数（角色）","周玩家付费率","周ARPU","周ARPPU"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(Report e : totalDataList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getWeek_total_device_Cnt();
					items[count++] =e.getWeek_new_play_count();
					items[count++] =e.getWeek_act_play_count();
					items[count++] =e.getWeek_day2ret();
					items[count++] =e.getWeek_avg_game_time();
					items[count++] =e.getWeek_avg_game_count();
					items[count++] =e.getWeek_avg_daily_game_time();
					items[count++] =e.getWeek_lostcnt();
					items[count++] =e.getWeek_lostrate();
					items[count++] =e.getWeek_returncnt();
					items[count++] =e.getWeek_income();
					items[count++] =e.getWeek_recharge_cnt();
					items[count++] =e.getWeek_pay_rate();
					items[count++] =e.getWeek_arpu();
					items[count++] =e.getWeek_arppu();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("周报累计数据");
			}
		}
		//导出月报
		if(Integer.parseInt(searchData.getChecktype1())==1){
			filename="月报累计数据.xlsx";
			List<Report> totalDataList=selectMonthBaseDataList(searchData, session);
			//
			if(totalDataList != null){
				String[] title = new String[] {"日期","月设备激活设备","月新增玩家数(账号)","月活跃玩家数(角色)","月新增玩家次日留存率","月平均游戏时长","月平均游戏次数","月平均每次游戏时长","月流失玩家数量(角色)","月流失率","月回流玩家数","月总收入金额","月充值玩家数量(角色)","月玩家付费率","月ARPU","月ARPPU"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(Report e : totalDataList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getWeek_total_device_Cnt();
					items[count++] =e.getWeek_new_play_count();
					items[count++] =e.getWeek_act_play_count();
					items[count++] =e.getWeek_day2ret();
					items[count++] =e.getWeek_avg_game_time();
					items[count++] =e.getWeek_avg_game_count();
					items[count++] =e.getWeek_avg_daily_game_time();
					items[count++] =e.getWeek_lostcnt();
					items[count++] =e.getWeek_lostrate();
					items[count++] =e.getWeek_returncnt();
					items[count++] =e.getWeek_income();
					items[count++] =e.getWeek_recharge_cnt();
					items[count++] =e.getWeek_pay_rate();
					items[count++] =e.getWeek_arpu();
					items[count++] =e.getWeek_arppu();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("月报累计数据");
			}
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
	 * 月基础数据
	 * @Title: selectMonthBaseDataList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Report>
	 * @author hwj
	 * @date 2017-2-15 下午01:58:58
	 */
	@RequestMapping(value="/selectMonthBaseDataList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Report> selectMonthBaseDataList (@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectMonthBaseDataList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<Report> monthDataList=reportService.selectMonthBaseDataList(searchData);
		Report currReport=null;
		if(monthDataList!=null){
			for(int i=0;i<monthDataList.size();i++){
				if(i==0){
					currReport=monthDataList.get(i);
				}
				if(i==1){
					Report preReport=monthDataList.get(i);
					preReport.setFlag(1);
					Report result=ComparePre(currReport,preReport);
					monthDataList.add(i, result);
				}
			}
		}
		return monthDataList;
	}
	
	
	
	
	
	//对比这周跟上周或者这月跟上月
	private Report ComparePre(Report r1,Report r2){
		double c_device=0.0;
		double c_nplaycount=0.0;
		double c_actplaycount=0.0;
		double c_avg_game_time=0.0;
		double c_avg_daily_game_time=0.0;
		double c_avg_game_count=0.0;
		double c_newday2ret=0.0;
		double c_lostcnt=0.0;
		double c_lostrate=0.0;
		double c_returncnt=0.0;
		double c_income=0.0;
		double c_rechage_cnt=0.0;
		double c_payrate=0.0;
		double c_arpu=0.0;
		if(r2.getWeek_total_device_Cnt()!=0){
			c_device=(r1.getWeek_total_device_Cnt()-r2.getWeek_total_device_Cnt())/r2.getWeek_total_device_Cnt().doubleValue();
		}
		if(r2.getWeek_new_play_count()!=0){
			c_nplaycount=(r1.getWeek_new_play_count()-r2.getWeek_new_play_count())/r2.getWeek_act_play_count().doubleValue();
		}
		if(r2.getWeek_act_play_count()!=0){
			c_actplaycount=(r1.getWeek_act_play_count()-r2.getWeek_act_play_count())/r2.getWeek_act_play_count().doubleValue();
		}
		if(r2.getWeek_avg_game_time()!=0||r2.getWeek_avg_game_time()!=0.00){
			c_avg_game_time=(r1.getWeek_avg_game_time()-r2.getWeek_avg_game_time())/r2.getWeek_avg_game_time();
		}
		if(r2.getWeek_avg_daily_game_time()!=0||r2.getWeek_avg_daily_game_time()!=0.00){
			c_avg_daily_game_time=(r1.getWeek_avg_daily_game_time()-r2.getWeek_avg_daily_game_time())/r2.getWeek_avg_daily_game_time();
		}
		
		if(r2.getWeek_avg_game_count()!=0||r2.getWeek_avg_game_count()!=0.00){
			c_avg_game_count=(r1.getWeek_avg_game_count()-r2.getWeek_avg_game_count())/r2.getWeek_avg_game_count();
		}
		if(r2.getWeek_day2ret()!=0||r2.getWeek_day2ret()!=0.00){
			c_newday2ret=(r1.getWeek_day2ret()-r2.getWeek_day2ret())/r2.getWeek_day2ret();
		}
		
		if(r2.getWeek_lostcnt()!=0){
			c_lostcnt=(r1.getWeek_lostcnt()-r2.getWeek_lostcnt())/r2.getWeek_lostcnt().doubleValue();
		}
		if(r2.getWeek_lostrate()!=0||r2.getWeek_lostrate()!=0.00){
			c_lostrate=(r1.getWeek_lostrate()-r2.getWeek_lostrate())/r2.getWeek_lostrate();
		}
		if(r2.getWeek_returncnt()!=0){
			c_returncnt=(r1.getWeek_returncnt()-r2.getWeek_returncnt())/r2.getWeek_returncnt().doubleValue();
		}
		if(r2.getWeek_income()!=0||r2.getWeek_income()!=0.00){
			c_income=(r1.getWeek_income()-r2.getWeek_income())/r2.getWeek_income();
		}
		if(r2.getWeek_recharge_cnt()!=0){
			c_rechage_cnt=(r1.getWeek_recharge_cnt()-r2.getWeek_recharge_cnt())/r2.getWeek_recharge_cnt().doubleValue();
		}
		if(r2.getWeek_pay_rate()!=0||r2.getWeek_pay_rate()!=0.00){
			c_payrate=(r1.getWeek_pay_rate()-r2.getWeek_pay_rate())/r2.getWeek_pay_rate();
		}
		if(r2.getWeek_arpu()!=0||r2.getWeek_arpu()!=0.00){
			c_arpu=(r1.getWeek_arpu()-r2.getWeek_arpu())/r2.getWeek_arpu();
		}
		
		Report result =new Report();
		if(r2.getFlag()==0){
			result.setStatdate("对比上周数据"+r2.getStatdate());
		}
		if(r2.getFlag()==1){
			result.setStatdate("对比上月数据"+r2.getStatdate());
		}
		result.setWeek_total_device_Cnt(NumberUtil.multi100(c_device, 0).intValue());
		result.setWeek_new_play_count(NumberUtil.multi100(c_nplaycount,0).intValue());
		result.setWeek_act_play_count(NumberUtil.multi100(c_actplaycount,0).intValue());
		result.setWeek_avg_game_time(NumberUtil.multi100(c_avg_game_time,0));
		result.setWeek_avg_daily_game_time(NumberUtil.multi100(c_avg_daily_game_time,0));
		result.setWeek_avg_game_count(NumberUtil.multi100(c_avg_game_count,0));
		result.setWeek_day2ret(NumberUtil.multi100(c_newday2ret,0));
		result.setWeek_lostcnt(NumberUtil.multi100(c_lostcnt,0).intValue());
		result.setWeek_lostrate(NumberUtil.multi100(c_lostrate,0));
		result.setWeek_returncnt(NumberUtil.multi100(c_returncnt,0).intValue());
		result.setWeek_income(NumberUtil.multi100(c_income,0));
		result.setWeek_recharge_cnt(NumberUtil.multi100(c_rechage_cnt,0).intValue());
		result.setWeek_pay_rate(NumberUtil.multi100(c_payrate,0));
		result.setWeek_arpu(NumberUtil.multi100(c_arpu,0));
		return result;
	}
	
	
	
	
	//对map的key排序
    private List<String> sort(Map<String,Report> map){ 
        List<String> list= new ArrayList<String>(map.keySet()); 
        Collections.sort(list,new Comparator<String>(){ 
                public int compare(String a, String b) { 
                        return a.toString().toLowerCase().compareTo(b.toString().toLowerCase()); 
                } 
        }); 
        return list;
} 

	
}
