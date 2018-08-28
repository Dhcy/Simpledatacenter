package com.good.plat.datacenter.datastat.controller.revenue;

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

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.Validator;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.revenue.Ltv;
import com.good.plat.datacenter.datastat.entity.revenue.PayRetention;
import com.good.plat.datacenter.datastat.service.revenue.LtvService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * Ltv
 * @ClassName: LtvController
 * @Description: TODO
 * @author hwj
 * @date 2017-2-8 下午03:13:37
 */
@Controller
@RequestMapping("/revenue/ltv")
public class LtvController {
	@Autowired
	private LtvService ltvService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	/**
	 * 付费金额、人数及比例
	 * @Title: selectRegPayMoneyAndCountAndRate
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Ltv>
	 * @author hwj
	 * @date 2017-2-8 下午06:04:24
	 */
	@RequestMapping(value = "/selectRegPayMoneyAndCountAndRate", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Ltv> selectRegPayMoneyAndCountAndRate(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectRegPayMoneyAndCountAndRate";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<Ltv> payMoneylist =ltvService.selectRegPayMoneyAndCountAndRate(searchData);
		return payMoneylist;
	}
	
	/**
	 * 生命周期ltv列表
	 * @Title: selectLtvofCurrDateList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Ltv>
	 * @author hwj
	 * @date 2017-2-8 下午06:05:17
	 */
	@RequestMapping(value = "/selectLtvofCurrDateList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Ltv> selectLtvofCurrDateList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectLtvofCurrDateList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<Ltv> ltvList =ltvService.selectLtvofCurrDateList(searchData);
		ltvList=ajustScaleOrMul100(ltvList);
		return ltvList;
	}
	
	/**
	 * 导出注收比及生命周期价值
	 * @Title: exportPayMoneyRateAndLtvList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-2-10 上午10:10:34
	 */
	@RequestMapping(value = "/exportPayMoneyRateAndLtvList", method = {
			RequestMethod.POST})
	public void exportPayMoneyRateAndLtvList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportPayMoneyRateAndLtvList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "注收比及生命周期价值.xlsx";
		//
		//注收比(付费额及占比)
		List<Ltv> PayMoneyAndRatelist=selectRegPayMoneyAndCountAndRate(searchData,session);
		//生命周期价值(ltv值)
		List<Ltv> ltvofCurrDateList=selectLtvofCurrDateList(searchData, session);
		//
		if(PayMoneyAndRatelist != null){
			String[] title = new String[] {"日期","新增玩家数(角色数)","当日付费","1日付费","2日付费","3日付费","4日付费","5日付费","6日付费","7日付费","8日付费","9日付费","10日付费","11日付费","12日付费"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Ltv e : PayMoneyAndRatelist){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getRegdate();
				items[count++] = e.getNewact();
				items[count++] = e.getDay0_pay();
				items[count++] = e.getDay1_pay();
				items[count++] = e.getDay2_pay();
				items[count++] = e.getDay3_pay();
				items[count++] = e.getDay4_pay();
				items[count++] = e.getDay5_pay();
				items[count++] = e.getDay6_pay();
				items[count++] = e.getDay7_pay();
				items[count++] = e.getDay8_pay();
				items[count++] = e.getDay9_pay();
				items[count++] = e.getDay10_pay();
				items[count++] = e.getDay11_pay();
				items[count++] = e.getDay12_pay();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("付费金额");
		}
		if(PayMoneyAndRatelist != null){
			String[] title = new String[] {"日期","新增玩家(角色数)","当日占比","1日占比","2日占比","3日占比","4日占比","5日占比","6日占比","7日占比","8日占比","9日占比","10日占比","11日占比","12日占比"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Ltv e : PayMoneyAndRatelist){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getRegdate();
				items[count++] = e.getNewact();
				items[count++] = e.getDay0_pay_rate();
				items[count++] = e.getDay1_pay_rate();
				items[count++] = e.getDay2_pay_rate();
				items[count++] = e.getDay3_pay_rate();
				items[count++] = e.getDay4_pay_rate();
				items[count++] = e.getDay5_pay_rate();
				items[count++] = e.getDay6_pay_rate();
				items[count++] = e.getDay7_pay_rate();
				items[count++] = e.getDay8_pay_rate();
				items[count++] = e.getDay9_pay_rate();
				items[count++] = e.getDay10_pay_rate();
				items[count++] = e.getDay11_pay_rate();
				items[count++] = e.getDay12_pay_rate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("注收比例");
		}
		
		if(ltvofCurrDateList != null){
			String[] title = new String[] {"日期","ltv3","ltv7,","ltv30","ltv60"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Ltv e : ltvofCurrDateList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getLtv3();
				items[count++] = e.getLtv7();
				items[count++] = e.getLtv30();
				items[count++] = e.getLtv60();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("用户生命周期价值");
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
	
	private List<Ltv> ajustScaleOrMul100(List<Ltv> list){
		if(!Validator.isNull(list)){
			for(Ltv e : list){
				//e.set(NumberUtil.ajustScale(e.get,NumberUtil.DEFAULT_SCALE));
				e.setLtv3(NumberUtil.ajustScale(e.getLtv3(),NumberUtil.DEFAULT_SCALE));
				e.setLtv7(NumberUtil.ajustScale(e.getLtv7(),NumberUtil.DEFAULT_SCALE));
				e.setLtv30(NumberUtil.ajustScale(e.getLtv30(),NumberUtil.DEFAULT_SCALE));
				e.setLtv60(NumberUtil.ajustScale(e.getLtv60(),NumberUtil.DEFAULT_SCALE));
			}
		}
		return list;
	}
	
	
}
