package com.good.plat.datacenter.datastat.controller.version;

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
import com.good.plat.datacenter.datastat.entity.version.VersionAnalysis;
import com.good.plat.datacenter.datastat.entity.virtual.VirtualCurrency;
import com.good.plat.datacenter.datastat.service.impl.version.VersionServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: WhalesController
 * @Description: 版本分析
 * @author dmw
 * @date 2016年3月25日 下午4:44:28
 */
@Controller
@RequestMapping("/version")
public class VersionController extends BaseController {

	@Autowired
	private VersionServiceImpl versionService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	
	@RequestMapping(value = "/selectNewUserList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<VersionAnalysis> selectNewUserList(@RequestBody BaseSearchData searchData) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectNewUserList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return versionService.selectNewUserList(searchData);
	}
	
	
	@RequestMapping(value = "/exportNewUserList", method = { RequestMethod.POST})
	@ResponseBody
	public void exportNewUserList(@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportNewUserList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "版本分析-新增玩家.xlsx";
		//
		List<VersionAnalysis> newUserList = selectNewUserList(searchData);
		//
		if(newUserList != null){
			String[] title = new String[] {"版本","新增玩家","占比"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(VersionAnalysis e : newUserList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getVersion();
				items[count++] = e.getNewUser();
				items[count++] = e.getRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("新增玩家");
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

	
	@RequestMapping(value = "/selectActiveUserList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<VersionAnalysis> selectActiveUserList(@RequestBody BaseSearchData searchData)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectActiveUserList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return versionService.selectActiveUserList(searchData);
	}
	
	
	@RequestMapping(value = "/exportActiveUserList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public void exportActiveUserList(@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportActiveUserList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "版本分析-活跃玩家.xlsx";
		//
		List<VersionAnalysis> activeUserList = selectActiveUserList(searchData);
		//
		if(activeUserList != null){
			String[] title = new String[] {"版本","活跃玩家","占比"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(VersionAnalysis e : activeUserList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getVersion();
				items[count++] = e.getActiveUser();
				items[count++] = e.getRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("活跃玩家");
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

	
	@RequestMapping(value = "/selectLoginUserList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<VersionAnalysis> selectLoginUserList(@RequestBody BaseSearchData searchData)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectLoginUserList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return versionService.selectLoginUserList(searchData);
	}

	
	@RequestMapping(value = "/exportLoginUserList", method = { RequestMethod.POST })
	public void exportLoginUserList(@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportLoginUserList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "版本分析-登录玩家.xlsx";
		//
		List<VersionAnalysis> loginUserList = selectLoginUserList(searchData);
		//
		if(loginUserList != null){
			String[] title = new String[] {"版本","登录玩家","占比"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(VersionAnalysis e : loginUserList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getVersion();
				items[count++] = e.getLoginUser();
				items[count++] = e.getRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("登录玩家");
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
