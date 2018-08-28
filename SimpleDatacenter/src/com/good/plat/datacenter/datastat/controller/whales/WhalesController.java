package com.good.plat.datacenter.datastat.controller.whales;

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
import com.good.plat.datacenter.datastat.entity.virtual.VirtualCurrency;
import com.good.plat.datacenter.datastat.entity.whales.Whales;
import com.good.plat.datacenter.datastat.service.impl.whales.WhalesServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: WhalesController
 * @Description: 鲸鱼用户
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Controller
@RequestMapping("/whales")
public class WhalesController extends BaseController {

	@Autowired
	private WhalesServiceImpl whalesService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	
	@RequestMapping(value = "/selectWhaleUserList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Whales> selectWhaleUserList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectWhaleUserList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return whalesService.selectWhaleUserList(searchData, session);
	}
	
	@RequestMapping(value = "/exportWhaleUserList", method = {
			RequestMethod.POST})
	public void exportWhaleUserList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportWhaleUserList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "充值玩家.xlsx";
		//
		List<Whales> whaleUserList = whalesService.exportWhaleUserList(searchData, session);//selectWhaleUserList(searchData, session);
		//
		if(whaleUserList != null){
			String[] title = new String[] {"排名","账号","角色名","渠道","区服","充值金额","充入虚拟币","当前剩余氪金","剩余绑定氪金","新增日期","首冲日期","最后充值日期","当前等级"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Whales e : whaleUserList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getRanking();
				items[count++] = e.getAccount();
				items[count++] = e.getActorName();
				items[count++] = e.getChannelName();
				items[count++] = e.getGameAreaName();
				items[count++] = e.getSumOfPayment();
				items[count++] = e.getCurrencyPurchased();
				items[count++] = e.getCurrLeftCount();
				items[count++] = e.getLeftBindCount();
				items[count++] = e.getFirstLogin();
				items[count++] = e.getFirstRechargeDate();
				items[count++] = e.getLastRechargeDate();
				items[count++] = e.getCurrentLevel();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("充值玩家");
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

	@RequestMapping(value = "/selectChargeUserList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Whales> selectChargeUserList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectChargeUserList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return whalesService.selectChargeUserList(searchData, session);
	}

	@RequestMapping(value = "/selectChargeInfoList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Whales> selectChargeInfoList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectChargeInfoList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return whalesService.selectChargeInfoList(searchData, session);
	}
	
}
