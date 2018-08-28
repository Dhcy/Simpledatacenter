package com.good.plat.datacenter.datastat.controller.players;


import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.datastat.entity.players.RollServer;
import com.good.plat.datacenter.datastat.service.players.RollServService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * 
 * @ClassName: RollServController
 * @Description: 滚服分析
 * @author hwj
 * @date 2017-1-11 下午04:00:16
 */
@Controller
@RequestMapping("/players/RollServ")
public class RollServController extends BaseController {
	Logger log = Logger.getLogger(getClass());
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private RollServService rollServService;
	
	/**
	 * 
	 * @Title: selectRollServAndRateList
	 * @Description: TODO查询滚服列表（含人数占比、充值占比）
	 * @param searchData
	 * @return
	 * List<RollServer>
	 * @author hwj
	 * @date 2017-1-11 下午02:34:55
	 */
	@RequestMapping(value = "/selectRollServList", method = {RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<RollServer> selectRollServList(@RequestBody BaseSearchData searchData){
		//log
		Date opDate = new Date();
		String methodName = "selectRollServList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<RollServer> rollServList=rollServService.selectRollServerList(searchData);
		return rollServList;
	}
	
	/**
	 * 
	 * @Title: exportRollServList
	 * @Description: 导出滚服分析
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-1-13 下午03:44:45
	 */
	@RequestMapping(value = "/exportRollServList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public void exportRollServList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportRollServList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "滚服分析.xlsx";
		//
		List<RollServer> rollServList = selectRollServList(searchData);
		//
		if(rollServList != null){
			String title[] = new String[]{"日期","新增滚服数","总充值人数","滚服充值人数","人数占比(%)","总充值金额","滚服充值金额","充值占比%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(RollServer e : rollServList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = DateUtil.format_yyyy_MM_dd(e.getStatDate());
				items[count++] = e.getAcross_cnt();
				items[count++] = e.getRecharge_cnt();
				items[count++] = e.getAcr_rcg_cnt();
				items[count++] = e.getCntRate();
				items[count++] = e.getRecharge_money();
				items[count++] = e.getAcross_recharge_money();
				items[count++] = e.getMoneyRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("滚服分析");
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
