package com.good.plat.datacenter.datastat.controller.revenue;

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
import com.good.plat.datacenter.datastat.entity.revenue.PayRetention;
import com.good.plat.datacenter.datastat.service.revenue.PayRetentionService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * 
 * @ClassName: PayRetentionController
 * @Description: 付费玩家留存
 * @author hwj
 * @date 2017-1-19 上午11:14:44
 */
@Controller
@RequestMapping("/revenue/payRetention")
public class PayRetentionController extends BaseController{

	@Autowired
	private PayRetentionService payRetentionService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	
	/**
	 * 
	 * @Title: selectRatationRateList
	 * @Description: 付费玩家留存率
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<PayRetention>
	 * @author hwj
	 * @date 2017-1-19 下午02:43:55
	 */
	@RequestMapping(value = "/selectRatationRateList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<PayRetention> selectRatationRateList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectRatationRateList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<PayRetention> retationRatelist = payRetentionService.selectRetationOfPayPlayerList(searchData);
		return retationRatelist;
	}
	
	/**
	 * 
	 * @Title: selectPayRateList
	 * @Description: 后续玩家付费率
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<PayRetention>
	 * @author hwj
	 * @date 2017-1-19 下午02:46:01
	 */
	@RequestMapping(value = "/selectPayRateList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<PayRetention> selectPayRateList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPayRateList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<PayRetention> payRatelist = payRetentionService.selectPayRateOfFollowList(searchData);
		return payRatelist;
	}
	
	/**
	 * 
	 * @Title: selectRateOfRetationAndPayList
	 * @Description: 付费玩家留存率及后续付费率
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-1-19 下午02:57:10
	 */
	@RequestMapping(value = "/selectRateOfRetationAndPayList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectRateOfRetationAndPayList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectRateOfRetationAndPayList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		Map<String,Object> map=new HashMap<String, Object>();
		//付费玩家留存率
		List<PayRetention> retationRatelist=selectRatationRateList(searchData,session);
		//后续付费率
		List<PayRetention> payRatelist=selectPayRateList(searchData, session);
		map.put("retationRatelist", retationRatelist);
		map.put("payRatelist", payRatelist);
		return map;
	}
	
	@RequestMapping(value = "/exportRateOfRetationAndPayList", method = {
			RequestMethod.POST})
	public void exportRateOfRetationAndPayList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportRateOfRetationAndPayList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "付费用户留存及付费率.xlsx";
		//
		//付费玩家留存率
		List<PayRetention> retationRatelist=selectRatationRateList(searchData,session);
		//后续付费率
		List<PayRetention> payRatelist=selectPayRateList(searchData, session);
		//
		if(retationRatelist != null){
			String[] title = new String[] {"日期","新增付费玩家数(角色)","1日留存率,","2日留存率","3日留存率","4日留存率","5日留存率","6日留存率","7日留存率","14日留存率","30日留存率"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(PayRetention e : retationRatelist){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getAccounts();
				items[count++] = e.getDay1();
				items[count++] = e.getDay2();
				items[count++] = e.getDay3();
				items[count++] = e.getDay4();
				items[count++] = e.getDay5();
				items[count++] = e.getDay6();
				items[count++] = e.getDay7();
				items[count++] = e.getDay14();
				items[count++] = e.getDay30();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("付费玩家留存");
		}
		if(payRatelist != null){
			String[] title = new String[] {"日期","新增付费玩家数(角色)","1日付费率,","2日付费率","3日付费率","4日付费率","5日付费率","6日付费率","7日付费率","14日付费率","30日付费率"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(PayRetention e : payRatelist){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getAccounts();
				items[count++] = e.getDay1();
				items[count++] = e.getDay2();
				items[count++] = e.getDay3();
				items[count++] = e.getDay4();
				items[count++] = e.getDay5();
				items[count++] = e.getDay6();
				items[count++] = e.getDay7();
				items[count++] = e.getDay14();
				items[count++] = e.getDay30();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("后续付费");
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
