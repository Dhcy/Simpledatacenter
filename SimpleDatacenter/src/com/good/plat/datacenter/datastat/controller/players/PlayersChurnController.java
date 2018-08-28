package com.good.plat.datacenter.datastat.controller.players;

import java.io.ByteArrayOutputStream;
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
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.datastat.entity.players.Conversion;
import com.good.plat.datacenter.datastat.entity.players.PlayersChurn;
import com.good.plat.datacenter.datastat.service.impl.players.PlayersChurnServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: PlayersChurnController
 * @Description: 玩家流失
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Controller
@RequestMapping("/players/playersChurn")
public class PlayersChurnController extends BaseController {

	@Autowired
	private PlayersChurnServiceImpl playersChurnService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping(value = "/selectPerDayLostNumAndRate", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<PlayersChurn> selectPerDayLostNumAndRate(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPerDayLostNumAndRate";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playersChurnService.selectPerDayLostNumAndRate(searchData,
				session);
	}
	
	/**
	 * 每日流失&回流
	 * @Title: exportLostAndReturnPerDay
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author moxw
	 * @throws Exception 
	 * @date 2016年8月4日 上午10:12:39
	 */
	@RequestMapping(value="exportLostAndReturnPerDay",method={RequestMethod.POST})
	public void exportLostAndReturnPerDay(@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response) throws Exception{
		//log
		sysAccessLogService.log(request, getClass(), "exportLostUserAnalysNumList", new Date(), searchData);		
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "每日流失.xlsx";
		//
		List<PlayersChurn> perDayLostNumAndRateList = selectPerDayLostNumAndRate(searchData, session);
		List<PlayersChurn> perDayReturnNumList = selectPerDayReturnNumList(searchData, session);
		//
		if(perDayLostNumAndRateList != null){
			String[] title = new String[] {"日期","全部玩家(角色数)","每日流失率(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(PlayersChurn e : perDayLostNumAndRateList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getAccounts();
				items[count++] = e.getRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("每日流失");
		}
		if(perDayReturnNumList != null){
			String[] title = new String[] {"日期","全部玩家(角色数)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(PlayersChurn e : perDayReturnNumList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getAccounts();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("每日回流");
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
	 * 流失用户分析
	 * @Title: exportLostUserAnalysNumList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author moxw
	 * @date 2016年8月4日 上午10:16:04
	 */
	@RequestMapping(value="exportLostUserAnalysNumList",method={RequestMethod.POST})
	public void exportLostUserAnalysNumList(@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response) throws Exception{
		//log
		sysAccessLogService.log(request, getClass(), "exportLostUserAnalysNumList", new Date(), searchData);
		
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "流失用户分析.xlsx";
		//
		List<PlayersChurn> lostUserAnalysNumList = selectLostUserAnalysNumList(searchData, session);
		//
		if(lostUserAnalysNumList != null){
			String subject = "";
			switch(Integer.valueOf(searchData.getChecktype3())){
			case 1:
				subject = "流失前等级";
				break;
			case 2:
				subject = "用户生命期";
				break;
			case 3:
				subject = "付费金额";
				break;
			case 4:
				subject = "付费次数";
				break;
			}
			String[] title = new String[] {subject,"每日流失数(角色)","每日流失比例(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(PlayersChurn e : lostUserAnalysNumList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getAccounts();
				items[count++] = e.getRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("流失用户分析");
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
	@RequestMapping(value = "/exportPerDayLostNumAndRate", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<PlayersChurn> exportPerDayLostNumAndRate(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportPerDayLostNumAndRate";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playersChurnService.exportPerDayLostNumAndRate(searchData,
				session);
	}

	@RequestMapping(value = "/selectPerDayReturnNumList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<PlayersChurn> selectPerDayReturnNumList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPerDayReturnNumList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playersChurnService.selectPerDayReturnNumList(searchData,
				session);
	}

	@RequestMapping(value = "/exportPerDayReturnNumList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<PlayersChurn> exportPerDayReturnNumList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportPerDayReturnNumList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playersChurnService.exportPerDayReturnNumList(searchData,
				session);
	}

	@RequestMapping(value = "/selectLostUserAnalysNumList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<PlayersChurn> selectLostUserAnalysNumList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectLostUserAnalysNumList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<PlayersChurn> list = playersChurnService.selectLostUserAnalysNumList(searchData,
				session);
		switch (Integer.valueOf(searchData.getChecktype3())) {
		case 1:
			try{
			Collections.sort(list, new Comparator<PlayersChurn>() {
				public int compare(PlayersChurn o1, PlayersChurn o2) {
					Integer v1 = Integer.valueOf(o1.getStatdate());
					int v2 = Integer.valueOf(o2.getStatdate());
					return v1.compareTo(v2);
				}
			});
			}catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		return list;
	}
/*
	@RequestMapping(value = "/exportLostUserAnalysNumList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<PlayersChurn> exportLostUserAnalysNumList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportLostUserAnalysNumList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return playersChurnService.exportLostUserAnalysNumList(searchData,
				session);
	}*/
}
