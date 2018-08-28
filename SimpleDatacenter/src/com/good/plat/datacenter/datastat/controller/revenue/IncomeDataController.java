package com.good.plat.datacenter.datastat.controller.revenue;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.good.plat.datacenter.common.util.DateUtil;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.datastat.entity.revenue.IncomeData;
import com.good.plat.datacenter.datastat.service.impl.revenue.IncomeDataServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: IncomeDataController
 * @Description: 收入数据
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Controller
@RequestMapping("/revenue/incomeData")
public class IncomeDataController extends BaseController {

	@Autowired
	private IncomeDataServiceImpl incomeDataService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping(value = "/selectIncomeNumList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<IncomeData> selectIncomeNumList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectIncomeNumList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<IncomeData> list = incomeDataService.selectIncomeNumList(searchData, session);
		List<String> isChargeDateList=new ArrayList<String>();//有充值的日期列表
		List<String> dateList=DateUtil.getDateInterval(searchData.getStartdate(), searchData.getEnddate());//日期期间
		//
		if(list!=null&&!list.isEmpty()){
			for(IncomeData e:list){
				if(e.getStatdate()!=null){
					isChargeDateList.add(e.getStatdate());
				}
			}
			if(dateList!=null&&!dateList.isEmpty()){
				for(String date:dateList){
					if(!isChargeDateList.contains(date)){
						IncomeData income=new IncomeData();
						income.setStatdate(date);
						income.setIncomes(0.0);
						list.add(income);
					}
				}
			}
		}
		Collections.sort(list, new Comparator<IncomeData>() {
			@Override
			public int compare(IncomeData o1, IncomeData o2) {
				// TODO Auto-generated method stub
				return o1.getStatdate().compareTo(o2.getStatdate());
			}
		});
		return list;
	}
	
	@RequestMapping(value = "/exportIncomeNumList", method = {
			RequestMethod.POST})
	public void exportIncomeNumList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportIncomeNumList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "收入数据.xlsx";
		//
		List<IncomeData> incomeNumList = selectIncomeNumList(searchData, session);
		List<IncomeData> chargeTimesList = selectChargeTimesList(searchData, session);
		List<IncomeData> chargePlayerNumList = selectChargePlayerNumList(searchData, session);
		//
		if(incomeNumList != null){
			String[] title = new String[] {"日期","收入金额(￥)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(IncomeData e : incomeNumList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getIncomes();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("收入金额");
		}
		if(chargeTimesList != null){
			String[] title = new String[] {"日期","充值次数(次)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(IncomeData e : chargeTimesList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getPayCounts();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("充值次数");
		}
		if(chargePlayerNumList != null){
			String[] title = new String[] {"日期","充值人数(角色数)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(IncomeData e : chargePlayerNumList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getPayAccounts();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("充值人数");
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


	@RequestMapping(value = "/selectChargeTimesList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<IncomeData> selectChargeTimesList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectChargeTimesList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return incomeDataService.selectChargeTimesList(searchData, session);
	}

	@RequestMapping(value = "/exportChargeTimesList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<IncomeData> exportChargeTimesList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportChargeTimesList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return incomeDataService.exportChargeTimesList(searchData, session);
	}

	@RequestMapping(value = "/selectChargePlayerNumList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<IncomeData> selectChargePlayerNumList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectChargePlayerNumList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return incomeDataService.selectChargePlayerNumList(searchData, session);
	}

	@RequestMapping(value = "/exportChargePlayerNumList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<IncomeData> exportChargePlayerNumList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportChargePlayerNumList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return incomeDataService.exportChargePlayerNumList(searchData, session);
	}

	@RequestMapping(value = "/selectLevelIncomeNumDistribution", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<IncomeData> selectLevelIncomeNumDistribution(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectLevelIncomeNumDistribution";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return incomeDataService.selectLevelIncomeNumDistribution(searchData,
				session);
	}

	@RequestMapping(value = "/exportLevelIncomeNumDistribution", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<IncomeData> exportLevelIncomeNumDistribution(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportLevelIncomeNumDistribution";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return incomeDataService.exportLevelIncomeNumDistribution(searchData,
				session);
	}

	@RequestMapping(value = "/selectLevelChargePlayerNumDistribution", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<IncomeData> selectLevelChargePlayerNumDistribution(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectLevelChargePlayerNumDistribution";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return incomeDataService.selectLevelChargePlayerNumDistribution(
				searchData, session);
	}

	@RequestMapping(value = "/exportLevelChargePlayerNumDistribution", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<IncomeData> exportLevelChargePlayerNumDistribution(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportLevelChargePlayerNumDistribution";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return incomeDataService.exportLevelChargePlayerNumDistribution(
				searchData, session);
	}

	@RequestMapping(value = "/selectLocationIncomeDistribution", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<IncomeData> selectLocationIncomeDistribution(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectLocationIncomeDistribution";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return incomeDataService.selectLocationIncomeDistribution(searchData,
				session);
	}

	@RequestMapping(value = "/exportLocationIncomeDistribution", method = {
			RequestMethod.POST})
	public void exportLocationIncomeDistribution(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportLocationIncomeDistribution";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "各地区收入.xlsx";
		//
		List<IncomeData> locationIncomeDistributionList = selectLocationIncomeDistribution(searchData, session);
		//
		if(locationIncomeDistributionList != null){
			String[] title = new String[] {"省份","收入","百分比","充值人数(角色数)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(IncomeData e : locationIncomeDistributionList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatType();
				items[count++] = e.getIncomes();
				items[count++] = e.getRate();
				items[count++] = e.getPayAccounts();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("各地区收入");
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

	@RequestMapping(value = "/selectChannelIncomeDistribution", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<IncomeData> selectChannelIncomeDistribution(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectChannelIncomeDistribution";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return incomeDataService.selectChannelIncomeDistribution(searchData,
				session);
	}

	@RequestMapping(value = "/exportChannelIncomeDistribution", method = {
			RequestMethod.POST})
	public void exportChannelIncomeDistribution(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportChannelIncomeDistribution";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "渠道收入.xlsx";
		//
		List<IncomeData> channelIncomeDistributionList = selectChannelIncomeDistribution(searchData, session);
		//
		if(channelIncomeDistributionList != null){
			String[] title = new String[] {"渠道","收入","百分比"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(IncomeData e : channelIncomeDistributionList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatType();
				items[count++] = e.getIncomes();
				items[count++] = e.getRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("各地区收入");
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
