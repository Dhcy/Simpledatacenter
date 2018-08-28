package com.good.plat.datacenter.datastat.controller.revenue;

import java.io.ByteArrayOutputStream;
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

import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.revenue.Behavior;
import com.good.plat.datacenter.datastat.entity.revenue.Conversion;
import com.good.plat.datacenter.datastat.service.impl.revenue.BehaviorServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: BehaviorController
 * @Description: 渠道数据
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Controller
@RequestMapping("/revenue/behavior")
public class RevenueBehaviorController extends BaseController {

	@Autowired
	private BehaviorServiceImpl behaviorService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	
	@RequestMapping(value = "/selectChargeWayOfAmount", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Behavior> selectChargeWayOfAmount(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectChargeWayOfAmount";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return behaviorService.selectChargeWayOfAmount(searchData, session);
	}

	@RequestMapping(value = "/exportChargeWay", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void exportChargeWay(@RequestBody BaseSearchData searchData,
			HttpSession session,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportChargeWay", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "充值方式.xlsx";
		//
		List<Behavior> chargeWayOfAmountList = selectChargeWayOfAmount(searchData, session);
		List<Behavior> chargeWayOfNumList = selectChargeWayOfNum(searchData, session);
		//
		if(chargeWayOfAmountList != null){
			String[] title = new String[] {"充值方式","收入金额(￥)","百分比(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Behavior e : chargeWayOfAmountList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getPayChannelName();
				items[count++] = e.getIncomes();
				items[count++] = e.getRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("收入金额");
		}
		if(chargeWayOfNumList != null){
			String[] title = new String[] {"充值方式","充值人次(次)","百分比(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Behavior e : chargeWayOfNumList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getPayChannelName();
				items[count++] = e.getPayUser();
				items[count++] = e.getRate();
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

	
	@RequestMapping(value = "/exportChargeWayOfAmount", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Behavior> exportChargeWayOfAmount(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportChargeWayOfAmount";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return behaviorService.exportChargeWayOfAmount(searchData, session);
	}

	
	@RequestMapping(value = "/selectChargeWayOfNum", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Behavior> selectChargeWayOfNum(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectChargeWayOfNum";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return behaviorService.selectChargeWayOfNum(searchData, session);
	}

	
	@RequestMapping(value = "/exportChargeWayOfNum", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Behavior> exportChargeWayOfNum(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportChargeWayOfNum";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return behaviorService.exportChargeWayOfNum(searchData, session);
	}

	
	@RequestMapping(value = "/selectConsumePackgeAmount", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Behavior> selectConsumePackgeAmount(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectConsumePackgeAmount";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<Behavior> list = null;
		list = behaviorService.selectConsumePackgeAmount(searchData, session);
		if(list != null){
			Double total = 0.0;
			for(Behavior b : list){
				total += b.getIncomes();
			}
			for(Behavior b : list){
				b.setRate(NumberUtil.div(b.getIncomes(), total));
			}
		}
		return list;
	}

	
	@RequestMapping(value = "/exportConsumePackgeAmount", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Behavior> exportConsumePackgeAmount(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportConsumePackgeAmount";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return behaviorService.exportConsumePackgeAmount(searchData, session);
	}

	
	@RequestMapping(value = "/selectConsumePackgeNum", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Behavior> selectConsumePackgeNum(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectConsumePackgeNum";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return behaviorService.selectConsumePackgeNum(searchData, session);
	}

	
	@RequestMapping(value = "/exportConsumePackgeNum", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Behavior> exportConsumePackgeNum(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportConsumePackgeNum";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return behaviorService.exportConsumePackgeNum(searchData, session);
	}

	
	@RequestMapping(value = "/selectChargeNumDis", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Behavior> selectChargeNumDis(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectChargeNumDis";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return behaviorService.selectChargeNumDis(searchData, session);
	}

	
	@RequestMapping(value = "/exportChargeNumDis", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Behavior> exportChargeNumDis(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportChargeNumDis";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return behaviorService.exportChargeNumDis(searchData, session);
	}

	
	@RequestMapping(value = "/selectChargeAmountDis", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Behavior> selectChargeAmountDis(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectChargeAmountDis";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return behaviorService.selectChargeAmountDis(searchData, session);
	}

	
	@RequestMapping(value = "/exportChargeAmountDis", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Behavior> exportChargeAmountDis(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportChargeAmountDis";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return behaviorService.exportChargeAmountDis(searchData, session);
	}

	
	@RequestMapping(value = "/selectChargeInterval", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Behavior> selectChargeInterval(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectChargeInterval";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return behaviorService.selectChargeInterval(searchData, session);
	}

	@RequestMapping(value = "/exportChargeInterval", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Behavior> exportChargeInterval(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportChargeInterval";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return behaviorService.exportChargeInterval(searchData, session);
	}
	
}
