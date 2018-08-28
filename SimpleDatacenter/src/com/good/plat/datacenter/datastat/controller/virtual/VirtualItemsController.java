package com.good.plat.datacenter.datastat.controller.virtual;

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
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.virtual.ItemExpense;
import com.good.plat.datacenter.datastat.entity.virtual.VirtualItems;
import com.good.plat.datacenter.datastat.service.impl.virtual.VirtualItemsServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: VirtualItemsController
 * @Description: 消费点
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Controller
@RequestMapping("/virtual/virtualItems")
public class VirtualItemsController extends BaseController {

	@Autowired
	private VirtualItemsServiceImpl virtualItemsService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping(value = "/selectItemPointList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<VirtualItems> selectItemPointList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectItemPointList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualItemsService.selectItemPointList(searchData, session);
	}

	
	@RequestMapping(value = "/selectDailyItemPointDetailList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<VirtualItems> selectDailyItemPointDetailList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectDailyItemPointDetailList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualItemsService.selectDailyItemPointDetailList(searchData, session);
	}
	
	@RequestMapping(value = "/selectLevelItemPointDetailList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<VirtualItems> selectLevelItemPointDetailList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectLevelItemPointDetailList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<VirtualItems> list = null;
		list = virtualItemsService.selectLevelItemPointDetailList(searchData, session);
		if(list != null){
			double total1 = 0.0 , total2 = 0.0 , total3 = 0.0 , total4 = 0.0;
			for(VirtualItems o : list){
				if(o != null){
					total1 += o.getCurrencyPurchase();
					total2 += o.getCurrencySum();
					total3 += o.getNumberOfExpense();
					total4 += o.getSpendingAccounts();
				}
			}
			for(VirtualItems o : list){
				if(o != null){
					o.setRate1(NumberUtil.mul(NumberUtil.div(o.getCurrencyPurchase() == null ? 0.0 : o.getCurrencyPurchase().doubleValue(), total1), 100.0,NumberUtil.DEFAULT_SCALE));
					o.setRate2(NumberUtil.mul(NumberUtil.div(o.getCurrencySum() == null ? 0.0 : o.getCurrencySum().doubleValue(), total2), 100.0,NumberUtil.DEFAULT_SCALE));
					o.setRate3(NumberUtil.mul(NumberUtil.div(o.getNumberOfExpense() == null ? 0.0 : o.getNumberOfExpense().doubleValue(), total3), 100.0,NumberUtil.DEFAULT_SCALE));
					o.setRate4(NumberUtil.mul(NumberUtil.div(o.getSpendingAccounts() == null ? 0.0 : o.getSpendingAccounts().doubleValue(), total4), 100.0,NumberUtil.DEFAULT_SCALE));
				}
			}
		}
		return list;
	}
	

	@RequestMapping(value = "/selectPurchaseItemNumberList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<VirtualItems> selectPurchaseItemNumberList(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPurchaseItemNumberList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualItemsService.selectPurchaseItemNumberList(searchData,
				session);
	}

	@RequestMapping(value = "/selectPurchaseVirtualCurrencyList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<VirtualItems> selectPurchaseVirtualCurrencyList(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPurchaseVirtualCurrencyList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualItemsService.selectPurchaseVirtualCurrencyList(
				searchData, session);
	}

	@RequestMapping(value = "/selectUseinfoItemNumberList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<VirtualItems> selectUseinfoItemNumberList(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectUseinfoItemNumberList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualItemsService.selectUseinfoItemNumberList(searchData,
				session);
	}

	@RequestMapping(value = "/selectVcConsumeNumOfPeople", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<VirtualItems> selectVcConsumeNumOfPeople(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectVcConsumeNumOfPeople";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return virtualItemsService.selectVcConsumeNumOfPeople(searchData,
				session);
	}
	@RequestMapping(value = "/exportItemPointList", method = {
			RequestMethod.POST})
	public void exportItemPointList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportItemPointList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "热门物品.xlsx";
		//
		
		List<VirtualItems> itemPointList = selectItemPointList(searchData, session);
		//
		if(itemPointList != null){
			String[] title = new String[] {"物品名称","购买数量","虚拟币总值","消耗数量","按日趋势","等级"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(VirtualItems e : itemPointList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getItemName();
				items[count++] = e.getCurrencyPurchase();
				items[count++] = e.getCurrencySum();
				items[count++] = e.getNumberOfExpense();
				datalist.add(items);
			}
			sheetNameList.add("热门物品-按消费点");
			list.add(datalist);
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
