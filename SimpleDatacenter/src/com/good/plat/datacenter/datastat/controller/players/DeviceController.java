package com.good.plat.datacenter.datastat.controller.players;

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
import com.good.plat.datacenter.datastat.entity.players.Conversion;
import com.good.plat.datacenter.datastat.entity.players.Device;
import com.good.plat.datacenter.datastat.service.players.PlayerDeviceService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

@Controller
@RequestMapping("/players/device")
public class DeviceController extends BaseController {
	
	@Autowired
	private PlayerDeviceService playerDeviceService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping(value = "/selectPlayerMachineModel", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Device> selectPlayerMachineModel(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		List<Device> list = null;
		//log
		Date opDate = new Date();
		String methodName = "selectPlayerMachineModel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		list = playerDeviceService.selectPlayerMachineModel(searchData);
		return list;
	}
	
	@RequestMapping(value = "/exportPlayerMachineModel", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void exportPlayerMachineModel(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportPlayerMachineModel", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "设备机型.xlsx";
		//
		List<Device> playerMachineModelList = selectPlayerMachineModel(searchData, session);
		//
		if(playerMachineModelList != null){
			String[] title = new String[] {"机型","新增玩家(设备)","占比(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Device e : playerMachineModelList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getMachineModel();
				items[count++] = e.getCount();
				items[count++] = e.getRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("设备机型");
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
	
	@RequestMapping(value = "/exportPlayerOperationSystem", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void exportPlayerOperationSystem(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportPlayerOperationSystem", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "操作系统.xlsx";
		//
		List<Device> playerOperationSystemList = selectPlayerOperationSystem(searchData, session);
		//
		if(playerOperationSystemList != null){
			String[] title = new String[] {"系统","新增玩家(设备)","百分比(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Device e : playerOperationSystemList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getOperationSystem();
				items[count++] = e.getCount();
				items[count++] = e.getRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("操作系统");
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
	
	@RequestMapping(value = "/selectPlayerOperationSystem", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<Device> selectPlayerOperationSystem(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPlayerOperationSystem";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<Device> list = null;
		list = playerDeviceService.selectPlayerOperationSystem(searchData);
		return list;
	}
}
