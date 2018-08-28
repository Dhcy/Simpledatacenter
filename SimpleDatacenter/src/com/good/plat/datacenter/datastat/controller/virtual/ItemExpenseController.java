package com.good.plat.datacenter.datastat.controller.virtual;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.Iterator;
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
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.virtual.ItemExpense;
import com.good.plat.datacenter.datastat.entity.virtual.VirtualCurrency;
import com.good.plat.datacenter.datastat.service.impl.virtual.ItemExpenseServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: ItemExpenseController
 * @Description: 消费喜好
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Controller
@RequestMapping("/virtual/itemExpense")
public class ItemExpenseController extends BaseController {
	
	@Autowired
	private ItemExpenseServiceImpl itemExpenseService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping(value = "/exportItemPoint", method = {
			RequestMethod.POST})
	public void exportItemPoint(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportItemPoint", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "各品类销售数量与收入比重.xlsx";
		//
		
		List<ItemExpense> itemPointList = selectItemPoint(searchData, session);
		//
		if(itemPointList != null){
			String[] title = new String[] {"物品名称","消费次数","消费次数-百分比","消费额度","消费额度-百分比"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(ItemExpense e : itemPointList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatType();
				items[count++] = e.getNumberOfSpending();
				items[count++] = e.getNumberOfSpendingPercentage();
				items[count++] = e.getSumOfSpending();
				items[count++] = e.getSumOfSpendingPercentage();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("各品类销售数量与收入比重-按消费点");
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
	
	@RequestMapping(value = "/selectItemPoint", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ItemExpense> selectItemPoint(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectItemPoint";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<ItemExpense> list = itemExpenseService.selectItemPointList(searchData, session);
		//移除消费次数跟额度同时为0
		if(list != null&&list.size()>0){
			Iterator<ItemExpense>  it = list.iterator();
			while(it.hasNext()){
				ItemExpense item = it.next();
				if(item.getNumberOfSpending().intValue()==0&&item.getSumOfSpending().intValue()==0){
					it.remove();
				}
			}
		}
		if(list != null&&list.size()!=0){
			double time_total = 0.0,amount_total=0.0;
			for(ItemExpense ie : list){
				if(ie != null){
					if(ie.getNumberOfSpending() != null){
						time_total += ie.getNumberOfSpending();
					}
					if(ie.getSumOfSpending() != null){
						amount_total += ie.getSumOfSpending();
					}
				}
			}
			
			for(ItemExpense ie : list){
				if(ie != null){
					if(ie.getNumberOfSpending() != null){
						ie.setNumberOfSpendingPercentage(NumberUtil.mul(NumberUtil.div(ie.getNumberOfSpending().doubleValue(), time_total), 100.0,NumberUtil.DEFAULT_SCALE));
					}
					if(ie.getSumOfSpending() != null){
						ie.setSumOfSpendingPercentage(NumberUtil.mul(NumberUtil.div(ie.getSumOfSpending().doubleValue(), amount_total), 100.0,NumberUtil.DEFAULT_SCALE));
					}
				}
			}
		}
		return list;
	}

	@RequestMapping(value = "/selectItemPointList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ItemExpense> selectItemPointList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectItemPointList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return itemExpenseService.selectItemPointList(searchData, session);
	}

	
	
	@RequestMapping(value = "/selectPurchaseItemNumberList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ItemExpense> selectPurchaseItemNumberList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPurchaseItemNumberList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return itemExpenseService.selectPurchaseItemNumberList(searchData,
				session);
	}

	@RequestMapping(value = "/exportPurchaseItemNumberList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ItemExpense> exportPurchaseItemNumberList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportPurchaseItemNumberList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return itemExpenseService.exportPurchaseItemNumberList(searchData,
				session);
	}

	@RequestMapping(value = "/selectPurchaseVirtualCurrencyList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ItemExpense> selectPurchaseVirtualCurrencyList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPurchaseVirtualCurrencyList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return itemExpenseService.selectPurchaseVirtualCurrencyList(searchData,
				session);
	}

	@RequestMapping(value = "/selectUseinfoItemNumberList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ItemExpense> selectUseinfoItemNumberList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectUseinfoItemNumberList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return itemExpenseService.selectUseinfoItemNumberList(searchData,
				session);
	}

	@RequestMapping(value = "/selectVcConsumeNumOfPeople", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ItemExpense> selectVcConsumeNumOfPeople(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectVcConsumeNumOfPeople";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return itemExpenseService.selectVcConsumeNumOfPeople(searchData,
				session);
	}

}
