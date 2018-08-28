package com.good.plat.datacenter.datastat.controller.index;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.good.plat.datacenter.common.util.ReflectUtil;
import com.good.plat.datacenter.datastat.entity.index.SummaryData;
import com.good.plat.datacenter.datastat.service.impl.index.SummaryDataServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: SummaryDataController
 * @Description: 概况
 * @author dmw
 * @date 2016年3月17日 下午10:04:12
 */
@Controller
@RequestMapping("/index")
public class SummaryDataController extends BaseController {

	@Autowired
	private SummaryDataServiceImpl summaryDataService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	/*@Autowired
	CommonMapper commonMapper;*/
	@RequestMapping(value="/selectSummaryData", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public SummaryData selectSummaryData (@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectSummaryData";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		SummaryData data = summaryDataService.selectSummaryData(searchData, session);
		return data;
	}

	
	@RequestMapping(value="/selectNewPlayerList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> selectNewPlayerList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		System.out.println(searchData.toString());
		//log
		Date opDate = new Date();
		String methodName = "selectNewPlayerList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return summaryDataService.selectNewPlayerList(searchData, session);
	}

	
	@RequestMapping(value="/exportNewPlayerList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> exportNewPlayerList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportNewPlayerList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return summaryDataService.exportNewPlayerList(searchData, session);
	}

	
	@RequestMapping(value="/selectActivePlayerList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> selectActivePlayerList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectActivePlayerList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return summaryDataService.selectActivePlayerList(searchData, session);
	}

	
	@RequestMapping(value="/exportActivePlayerList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> exportActivePlayerList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportActivePlayerList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return summaryDataService.exportActivePlayerList(searchData, session);
	}

	
	@RequestMapping(value="/selectPaidPlayerList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> selectPaidPlayerList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPaidPlayerList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<SummaryData> list = summaryDataService.selectPaidPlayerList(searchData, session); 
		return list;
	}

	
	@RequestMapping(value="/exportPaidPlayerList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> exportPaidPlayerList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportPaidPlayerList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return summaryDataService.exportPaidPlayerList(searchData, session);
	}

	
	@RequestMapping(value="/selectIncomeNumList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> selectIncomeNumList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectIncomeNumList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return summaryDataService.selectIncomeNumList(searchData, session);
	}

	
	@RequestMapping(value="/exportIncomeNumList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> exportIncomeNumList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportIncomeNumList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return summaryDataService.exportIncomeNumList(searchData, session);
	}

	
	@RequestMapping(value="/selectDailyRateOfPay", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> selectDailyRateOfPay(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectDailyRateOfPay";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return summaryDataService.selectDailyRateOfPay(searchData, session);
	}

	
	@RequestMapping(value="/exportDailyRateOfPay", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> exportDailyRateOfPay(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportDailyRateOfPay";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return summaryDataService.exportDailyRateOfPay(searchData, session);
	}

	
	@RequestMapping(value="/selectDailyARPU", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> selectDailyARPU(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectDailyARPU";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return summaryDataService.selectDailyARPU(searchData, session);
	}

	
	@RequestMapping(value="/exportDailyARPU", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> exportDailyARPU(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportDailyARPU";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return summaryDataService.exportDailyARPU(searchData, session);
	}

	
	@RequestMapping(value="/selectDailyARPPU", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> selectDailyARPPU(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectDailyARPPU";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return summaryDataService.selectDailyARPPU(searchData, session);
	}

	
	@RequestMapping(value="/exportDailyARPPU", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> exportDailyARPPU(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportDailyARPPU";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return summaryDataService.exportDailyARPPU(searchData, session);
	}
	
	
	@RequestMapping(value="/selectNewUserRetention", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> selectNewUserRetention(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectNewUserRetention";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return summaryDataService.selectNewUserRetention(searchData, session);
	}

	
	@RequestMapping(value="/exportNewUserRetention", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> exportNewUserRetention(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportNewUserRetention";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return summaryDataService.exportNewUserRetention(searchData, session);
	}

	
	@RequestMapping(value="/selectDeviceRetention", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> selectDeviceRetention(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectDeviceRetention";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return summaryDataService.selectDeviceRetention(searchData, session);
	}

	
	@RequestMapping(value="/exportDeviceRetention", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> exportDeviceRetention(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportDeviceRetention";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return summaryDataService.exportDeviceRetention(searchData, session);
	}

	
	@RequestMapping(value="/selectAvgGameTimesList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> selectAvgGameTimesList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectAvgGameTimesList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return summaryDataService.selectAvgGameTimesList(searchData, session);
	}

	
	@RequestMapping(value="/exportAvgGameTimesList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<SummaryData> exportAvgGameTimesList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportAvgGameTimesList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return summaryDataService.exportAvgGameTimesList(searchData, session);
	}
	
	
	@RequestMapping(value="/downloadKeyIndicator2", method={RequestMethod.GET})
	public void downloadKeyIndicator2(HttpSession session,HttpServletResponse response) throws Exception {
		BaseSearchData searchData = (BaseSearchData) HTTPUtil.parseRequestParameters(request, BaseSearchData.class);
		//log
		sysAccessLogService.log(request, getClass(), "downloadKeyIndicator", new Date(), searchData);
		//
		SummaryData data = selectSummaryData(searchData, session);
		/*selectNewPlayerList(searchData, session);//
		selectActivePlayerList(searchData, session);
		selectPaidPlayerList(searchData, session);
		selectIncomeNumList(searchData, session);
		selectDailyRateOfPay(searchData, session);
		selectDailyARPU(searchData, session);
		selectDailyARPPU(searchData, session);
		selectNewUserRetention(searchData, session);
		selectAvgGameTimesList(searchData, session);*/
		//
		String title[] = new String[]{"姓名","年龄"};
		
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		List<Object[]> datalist1 = new LinkedList();
		datalist1.add(title);
		list.add(datalist1 );
		String filename = "关键指标.xlsx";
		sheetNameList.add("新增激活和账户");
		Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
		ExcelGenerator.fillWorkBook(w,sheetNameList, list);
		
		//
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		w.write(baos);//file[1].getBytes("UTF-8");
		response.setHeader("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
		response.setHeader("Content-Disposition", "attachment;filename="+ HTTPUtil.buildAttachmentFileName(request, filename));
		response.setHeader("Content-Length",baos.size()+"");
		baos.writeTo(response.getOutputStream());
		
		//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//		headers.setContentDispositionFormData("attachment", new String(filename.getBytes("UTF-8"), "ISO8859-1"));
//		
		//return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @Title: exportKeyIndicator
	 * @Description: 下载-游戏概况-关键指标
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author moxw
	 * @date 2016年7月29日 下午5:45:41
	 */
	@RequestMapping(value="/exportKeyIndicator", method={RequestMethod.POST})
	public void exportKeyIndicator(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportKeyIndicator", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "关键指标.xlsx";
		//
		SummaryData data = selectSummaryData(searchData, session);//汇总
		List<SummaryData> newPlayerList = selectNewPlayerList(searchData, session);//新增激活和账户
		List<SummaryData> activePlayerList = selectActivePlayerList(searchData, session);//活跃玩家
		List<SummaryData> paidPlayerList = selectPaidPlayerList(searchData, session);	//付费玩家
		List<SummaryData> incomeList = selectIncomeNumList(searchData, session);//收入
		//
		if(newPlayerList != null){
			newPlayerList = newPlayerList.size() == 0 ? new LinkedList() : newPlayerList.get(0).getDataList();
			String title[] = new String[]{"日期","激活台数(台)","新增玩家(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(SummaryData e : newPlayerList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getDeviceCount();
				items[count++] = e.getUserCount();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("新增激活和玩家");
		}
		if(activePlayerList != null){
			activePlayerList = activePlayerList.size() == 0 ? new LinkedList() : activePlayerList.get(0).getDataList();
			String title[] = new String[]{"日期","新玩家(角色)","老玩家(角色)","总计(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(SummaryData e : activePlayerList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getNewActiveUser();
				items[count++] = e.getOldActiveUser();
				items[count++] = e.getActiveUserSUM();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("活跃玩家");
		}
		if(paidPlayerList != null ){
			paidPlayerList = paidPlayerList.size() == 0 ? new LinkedList() : paidPlayerList.get(0).getDataList();
			String title[] = new String[]{"日期","新增付费玩家(角色)","老付费玩家(角色)","总计(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(SummaryData e : paidPlayerList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getNewPayUser();
				items[count++] = e.getOldPayUser();
				items[count++] = e.getPayUserSUM();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("付费玩家");
		}
		if(incomeList != null){
			incomeList = incomeList.size() == 0 ? new LinkedList() : incomeList.get(0).getDataList();
			String title[] = new String[]{"日期","收入(￥)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(SummaryData e : incomeList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getIncome();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("收入");
		}
		//
		Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
		ExcelGenerator.fillWorkBook(w,sheetNameList, list);
		
		//
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		w.write(baos);
		String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
		HTTPUtil.responseFile(response, request, contentType , filename, baos);
		//ExcelGenerator.write(list,response.getOutputStream(), ExcelGenerator.XLSX);
		
		//
//      HttpHeaders headers = new HttpHeaders();
//      headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//		headers.setContentDispositionFormData("attachment", new String(filename.getBytes("UTF-8"), "ISO8859-1"));
//		return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @Title: downloadpaidPermeate
	 * @Description: 下载-游戏概况-付费渗透
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author moxw
	 * @date 2016年7月29日 下午6:05:39
	 */
	@RequestMapping(value="/exportPaidPermeate", method={RequestMethod.POST})
	public void exportPaidPermeate(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportPaidPermeate", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "付费渗透.xlsx";
		//
		List<SummaryData> dailyPayRateList = selectDailyRateOfPay(searchData, session);//日付费率
		List<SummaryData> dailyARPUList = selectDailyARPU(searchData, session);//日ARPU
		List<SummaryData> dailyARPPUList = selectDailyARPPU(searchData, session);	//日ARPPU
		//
		if(dailyPayRateList != null){
			dailyPayRateList = dailyPayRateList.size() == 0 ? new LinkedList() : dailyPayRateList.get(0).getDataList();
			String title[] = new String[]{"日期","日付费率(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(SummaryData e : dailyPayRateList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getPayRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("日付费率");
		}
		if(dailyARPUList != null){
			dailyARPUList = dailyARPUList.size() == 0 ? new LinkedList() : dailyARPUList.get(0).getDataList();
			String title[] = new String[]{"日期","日ARPU"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(SummaryData e : dailyARPUList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getDayARPU();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("日ARPU");
		}
		if(dailyARPPUList != null){
			dailyARPPUList = dailyARPPUList.size() == 0 ? new LinkedList() : dailyARPPUList.get(0).getDataList();
			String title[] = new String[]{"日期","日ARPPU"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(SummaryData e : dailyARPPUList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getDayARPPU();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("日ARPPU");
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
