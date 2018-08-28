package com.good.plat.datacenter.datastat.controller.virtual;

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
import com.good.plat.datacenter.datastat.entity.revenue.Conversion;
import com.good.plat.datacenter.datastat.entity.virtual.VirtualCurrency;
import com.good.plat.datacenter.datastat.service.impl.virtual.VirtualCurrencyServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: VirtualCurrencyController
 * @Description: 虚拟币
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Controller
@RequestMapping("/virtual/virtualCurrency")
public class VirtualCurrencyController extends BaseController {

	@Autowired
	private VirtualCurrencyServiceImpl virtualCurrencyService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping(value = "/selectVcPurchaseInfo", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectVcPurchaseInfo(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectVcPurchaseInfo";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		Map<String,Object> result = new HashMap<String, Object>();
		Double reward = 0.0 , purchase = 0.0, expense = 0.0;
		
		List<VirtualCurrency> list = null;
		list = virtualCurrencyService.selectVcPurchase(searchData, session);
		
		if(list != null){
			for(VirtualCurrency v : list){
				if(v != null){
					purchase += v.getPurchaseSUM() == null ? 0.0 : v.getPurchaseSUM();
					reward += v.getRewardSUM()==null ? 0.0 : v.getRewardSUM();
					expense += v.getExpenseSUM() == null ? 0.0 : v.getExpenseSUM();
				}
			}
		}
		
		result.put("data", list);
		result.put("purchase", purchase);
		result.put("reward", reward);
		result.put("expense", expense);
		return result;
	}
	
	@RequestMapping(value = "/selectVcPurchase", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<VirtualCurrency> selectVcPurchase(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectVcPurchase";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualCurrencyService.selectVcPurchase(searchData, session);
	}

	@RequestMapping(value = "/exportVcPurchase", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<VirtualCurrency> exportVcPurchase(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportVcPurchase";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualCurrencyService.exportVcPurchase(searchData, session);
	}

	@RequestMapping(value = "/exportVCPurchaseAndCosume", method = {
			RequestMethod.POST})
	public void exportVCPurchaseAndCosume(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportVCPurchaseAndCosume", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "虚拟币购入和消耗.xlsx";
		//
		Map<String, Object> vcPurchaseInfo = selectVcPurchaseInfo(searchData, session);
		List<VirtualCurrency> vcPurchaseInfoList = (List<VirtualCurrency>) vcPurchaseInfo.get("data");
		List<VirtualCurrency> vcConsumeTimsList = selectVcConsumeTimes(searchData, session);
		List<VirtualCurrency> vcConsumeNumOfPeopleList = selectVcConsumeNumOfPeople(searchData, session);
		List<VirtualCurrency> totalRetentionVcList = selectTotalRetentionVc(searchData, session);
		//
		if(vcPurchaseInfoList != null){
			String[] title = new String[] {"日期","收入","赠与","消费"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(VirtualCurrency e : vcPurchaseInfoList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getPurchaseSUM();
				items[count++] = e.getRewardSUM();
				items[count++] = e.getExpenseSUM();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("虚拟币产出和消耗");
		}
		if(vcConsumeTimsList != null){
			String[] title = new String[] {"日期","消耗次数"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(VirtualCurrency e : vcConsumeTimsList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getSpendingTimes();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("消耗次数");
		}
		if(vcConsumeNumOfPeopleList != null){
			String[] title = new String[] {"日期","消耗人数"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(VirtualCurrency e : vcConsumeNumOfPeopleList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getSpendingAccounts();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("消耗人数");
		}
		if(totalRetentionVcList != null){
			String[] title = new String[] {"日期","累计留存"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(VirtualCurrency e : totalRetentionVcList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getCurrencyBalance();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("累计留存虚拟币");
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
	
	@RequestMapping(value = "/exportVCLevelCosume", method = {
			RequestMethod.POST})
	public void exportVCLevelCosume(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportVCLevelCosume", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "各等级虚拟币消耗.xlsx";
		//
		List<VirtualCurrency> vcLevelConsumeList = selectVcLevelConsume(searchData, session);
		List<VirtualCurrency> vcLevelConsumeTimesList = selectVcLevelConsumeTimes(searchData, session);
		List<VirtualCurrency> vcLevelConsumeNumOfPeopleList = selectVcLevelConsumeNumOfPeople(searchData, session);
		//
		if(vcLevelConsumeList != null){
			String[] title = new String[] {"等级","虚拟币消耗"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(VirtualCurrency e : vcLevelConsumeList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getActorlevel();
				items[count++] = e.getExpenseSUM();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("虚拟币消耗");
		}
		if(vcLevelConsumeTimesList != null){
			String[] title = new String[] {"等级","消耗次数"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(VirtualCurrency e : vcLevelConsumeTimesList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getActorlevel();
				items[count++] = e.getSpendingTimes();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("消耗次数");
		}
		if(vcLevelConsumeNumOfPeopleList != null){
			String[] title = new String[] {"等级","消耗人数(角色数)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(VirtualCurrency e : vcLevelConsumeNumOfPeopleList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getActorlevel();
				items[count++] = e.getSpendingAccounts();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("消耗人数");
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
	
	@RequestMapping(value = "/selectVcConsumeTimes", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<VirtualCurrency> selectVcConsumeTimes(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectVcConsumeTimes";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualCurrencyService.selectVcConsumeTimes(searchData, session);
	}

	@RequestMapping(value = "/exportVcConsumeTimes", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<VirtualCurrency> exportVcConsumeTimes(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportVcConsumeTimes";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualCurrencyService.exportVcConsumeTimes(searchData, session);
	}

	@RequestMapping(value = "/selectVcConsumeNumOfPeople", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<VirtualCurrency> selectVcConsumeNumOfPeople(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectVcConsumeNumOfPeople";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualCurrencyService.selectVcConsumeNumOfPeople(searchData,
				session);
	}

	@RequestMapping(value = "/exportVcConsumeNumOfPeople", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<VirtualCurrency> exportVcConsumeNumOfPeople(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportVcConsumeNumOfPeople";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualCurrencyService.exportVcConsumeNumOfPeople(searchData,
				session);
	}

	@RequestMapping(value = "/selectTotalRetentionVc", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<VirtualCurrency> selectTotalRetentionVc(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectTotalRetentionVc";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualCurrencyService.selectTotalRetentionVc(searchData,
				session);
	}

	@RequestMapping(value = "/exportTotalRetentionVc", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<VirtualCurrency> exportTotalRetentionVc(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportTotalRetentionVc";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualCurrencyService.exportTotalRetentionVc(searchData,
				session);
	}

	@RequestMapping(value = "/selectVcLevelConsume", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<VirtualCurrency> selectVcLevelConsume(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectVcLevelConsume";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualCurrencyService.selectVcLevelConsume(searchData, session);
	}

	@RequestMapping(value = "/exportVcLevelConsume", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<VirtualCurrency> exportVcLevelConsume(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportVcLevelConsume";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualCurrencyService.exportVcLevelConsume(searchData, session);
	}

	@RequestMapping(value = "/selectVcLevelConsumeTimes", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<VirtualCurrency> selectVcLevelConsumeTimes(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectVcLevelConsumeTimes";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualCurrencyService.selectVcLevelConsumeTimes(searchData,
				session);
	}

	@RequestMapping(value = "/exportVcLevelConsumeTimes", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<VirtualCurrency> exportVcLevelConsumeTimes(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportVcLevelConsumeTimes";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualCurrencyService.exportVcLevelConsumeTimes(searchData,
				session);
	}

	@RequestMapping(value = "/selectVcLevelConsumeNumOfPeople", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<VirtualCurrency> selectVcLevelConsumeNumOfPeople(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectVcLevelConsumeNumOfPeople";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualCurrencyService.selectVcLevelConsumeNumOfPeople(
				searchData, session);
	}

	@RequestMapping(value = "/exportVcLevelConsumeNumOfPeople", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<VirtualCurrency> exportVcLevelConsumeNumOfPeople(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportVcLevelConsumeNumOfPeople";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualCurrencyService.exportVcLevelConsumeNumOfPeople(
				searchData, session);
	}

}
