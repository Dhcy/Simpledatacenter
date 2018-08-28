package com.good.plat.datacenter.datastat.controller.analysis;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.base.WarningBaseSearchData;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningEvent;
import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningItem;
import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningNoticeWithBLOBs;
import com.good.plat.datacenter.datastat.service.analysis.IWarningEventService;
import com.good.plat.datacenter.datastat.service.analysis.IWarningItemService;
import com.good.plat.datacenter.datastat.service.analysis.IWarningNoticeService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

@Controller
@RequestMapping(value="/analysis")
public class AnalysisController  extends BaseController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private IWarningEventService warningEventService;
	@Autowired
	private IWarningItemService warningItemService;
	
	@Autowired
	private IWarningNoticeService warningNoticeService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	public static void main(String args[]){
		ScheduledExecutorService service = Executors  
                .newSingleThreadScheduledExecutor();
		
	}
	
	@RequestMapping(value="/selectItems", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<LogplatWarningItem> selectItems(@RequestBody BaseSearchData searchData,HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectItems";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		searchData.setChecktype1("0");
		List<LogplatWarningItem> list = warningItemService.selectItems(searchData);
		if(list != null){
			Calendar cal = Calendar.getInstance();
			for(LogplatWarningItem o : list){
				cal.setTime(o.getLaunch_time());
				o.setActivationTime(cal.get(Calendar.HOUR_OF_DAY));
				List<String> emails = new LinkedList<String>();
				String ems[] = o.getSubscriber() == null ? new String[0]: o.getSubscriber().split(",");
				if(ems != null){
					for(String s : ems){
						emails.add(s.trim());
					}
				}
				o.setEmails(emails);
				o.setValue(o.getValve() == null ? 0.0 : o.getValve().doubleValue());
			}
		}
		return list;
	}
	
	public static String parseUnit(String unit){
		String unitName = null;
		if("0".equals(unit)){
			unitName = "%";
		}else{
			unitName = "";
		}
		return unitName;
	}
	
	public static String parseCmpType(Integer cmpType){
		String cmpName = null;
		switch(cmpType){
		case 1:
			cmpName = "大于";
			break;
		case -1:
			cmpName = "小于";
			break;
		case 0:
			cmpName = "等于";
			break;
		default:
			cmpName = "未定义";
			break;
		}
		return cmpName;
	}
	
	@RequestMapping(value="/addItem", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage addItem (@Validated @RequestBody LogplatWarningItem logplatWarningItem,
			HttpSession session,HttpServletRequest request,BindingResult result) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "addItem";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, logplatWarningItem);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		Calendar cal = Calendar.getInstance();
		//
		logplatWarningItem.setCtime(cal.getTime());
		cal.set(Calendar.HOUR_OF_DAY, logplatWarningItem.getActivationTime());
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		//
		logplatWarningItem.setLaunch_time(cal.getTime());
		logplatWarningItem.setStat(0);
		BaseMessage mess = new BaseMessage();
		if (result.hasErrors()) {
			mess.setStatus(0);
			mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return mess;
		}else{
			mess = warningItemService.add(logplatWarningItem);
		}
		return mess;
	}
	
	@RequestMapping(value="/deleteItem", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage deleteItem (@RequestBody BaseSearchData searchData,
			HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "deleteItem";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		BaseMessage bm = null;
		//bm = this.warningItemService.deleteByPrimaryKey(Integer.valueOf(searchData.getChecktype1()));
		LogplatWarningItem item = warningItemService.selectItemsByPrimaryID(Integer.valueOf(searchData.getChecktype1()));
		item.setStat(1);
		bm = warningItemService.update(item);
		return bm;
	}
	
	@RequestMapping(value="/modifyItem", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage modifyItem (@Validated @RequestBody LogplatWarningItem logplatWarningItem,
			HttpSession session,HttpServletRequest request,BindingResult result) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "modifyItem";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, logplatWarningItem);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		Calendar cal = Calendar.getInstance();
		//
		cal.set(Calendar.HOUR_OF_DAY, logplatWarningItem.getActivationTime());
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		//
		logplatWarningItem.setLaunch_time(cal.getTime());
		BaseMessage mess = new BaseMessage();
		if (result.hasErrors()) {
			mess.setStatus(0);
			mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return mess;
		}else{
			mess = warningItemService.update(logplatWarningItem);
		}
		return mess;
	}
	
	@RequestMapping(value="/selectAllWarningNotice", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<LogplatWarningNoticeWithBLOBs> selectAllWarningNotice (@RequestBody BaseSearchData searchData,
			HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectAllWarningNotice";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<LogplatWarningNoticeWithBLOBs> list = null;
		list = this.warningNoticeService.selectAllWarningNotice(100);
		
		return list;
	}
	
	@RequestMapping(value="/selectWarningNotice", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<LogplatWarningNoticeWithBLOBs> selectWarningNotice (@RequestBody WarningBaseSearchData searchData,
			HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectWarningNotice";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<LogplatWarningNoticeWithBLOBs> list = null;
		list = this.warningNoticeService.selectWarningNotice(searchData);
		return list;
	}
	
	@RequestMapping(value="/updateReadWarningNotice", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage updateReadWarningNotice (@RequestBody BaseSearchData searchData,
			HttpSession session,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "updateReadWarningNotice";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		BaseMessage bm = null;
		bm = this.warningNoticeService.updateReadWarningNotice(Integer.valueOf(searchData.getChecktype1()));
		return bm;
	}
	
	@RequestMapping(value="/selectEventList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<LogplatWarningEvent> selectEventList (@RequestBody BaseSearchData searchData,
			HttpSession session,HttpServletRequest request) throws Exception {
		sysAccessLogService.log(request, getClass(), "selectEventList", new Date(), searchData);
		//
		List<LogplatWarningEvent> list = warningEventService.findAll();
		return list;
	}
	
	
	
	@RequestMapping(value="/addEven", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage addEven (@RequestBody BaseSearchData searchData,
			HttpSession session,HttpServletRequest request) throws Exception {
		return null;
	}
	
	@RequestMapping(value="/deleteEven", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage deleteEven (@RequestBody BaseSearchData searchData,
			HttpSession session,HttpServletRequest request) throws Exception {
		return null;
	}
	
	@RequestMapping(value="/modifyEven", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage modifyEven (@RequestBody BaseSearchData searchData,
			HttpSession session,HttpServletRequest request) throws Exception {
		return null;
	}
	
	@RequestMapping(value = "/exportWarningItems", method = {
			RequestMethod.POST})
	public void exportWarningItems(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportWarningItems", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "已建立告警项.xlsx";
		//
		List<LogplatWarningItem> warningItemList = selectItems(searchData, session, request);
		
		//
		if(warningItemList != null){
			String[] title = new String[] {"警告名称","IF","THEN","使用状态","操作"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(LogplatWarningItem e : warningItemList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getName();
				items[count++] = e.getWarning_event() + " " + parsetOp(e.getCmptype()) + ": " + e.getValve() + parseUnit(e.getUnit()) + " , 扩展值: " + e.getValve2();
				items[count++] = e.getEmails();		
				items[count++] = e.getStat();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("已建立告警项");
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
	
	private String parseUnit(Integer unit){
		return unit != null && unit == 0 ? "%" : "";
	}
	
	private String parsetOp(Integer cmpType){
		cmpType = cmpType == null ? Integer.MAX_VALUE : cmpType;
		String cmp = "未定义";
		switch (cmpType) {
		case -1:
			cmp = "小于";
			break;
		case 0 :
			cmp = "等于";
			break;
		case 1:
			cmp = "大于";
			break;
		default:
			break;
		}
		return cmp;
	}
	
	@RequestMapping(value = "/exportWarningNotice", method = {
			RequestMethod.POST})
	public void exportWarningNotice(
			@RequestBody WarningBaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportWarningNotice", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "告警通知.xlsx";
		//
		List<LogplatWarningNoticeWithBLOBs> warningNoticeList = selectWarningNotice(searchData, session, request);
		
		//
		if(warningNoticeList != null){
			
			String[] title = new String[] {"日期","警告名称","原因","操作"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(LogplatWarningNoticeWithBLOBs e : warningNoticeList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = DateUtil.format(e.getNotice_datetime(), "yyyy-MM-dd HH:mm:ss");
				items[count++] = e.getTitle();
				items[count++] = e.getContent();
				items[count++] = "";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("告警通知");
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
