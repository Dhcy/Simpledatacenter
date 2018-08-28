package com.good.plat.datacenter.datastat.controller.revenue;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
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
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.revenue.Conversion;
import com.good.plat.datacenter.datastat.entity.revenue.NewPlayerValue;
import com.good.plat.datacenter.datastat.service.impl.revenue.NewPlayerValueServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: NewPlayerValueController
 * @Description: 新玩家价值
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Controller
@RequestMapping("/revenue/newPlayerValue")
public class NewPlayerValueController extends BaseController {
	@Autowired
	private NewPlayerValueServiceImpl newPlayerValueService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	
	@RequestMapping(value = "/selectXdaysAvgContributionList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<NewPlayerValue> selectXdaysAvgContributionList(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectXdaysAvgContributionList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return newPlayerValueService.selectXdaysAvgContributionList(searchData, session);
	}
	
	@RequestMapping(value = "/exportNewPlayerIncomeContribute", method = {
			RequestMethod.POST})
	public void exportNewPlayerIncomeContribute(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportNewPlayerIncomeContribute", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "付费率.xlsx";
		//
		searchData.setChecktype1("7");
		Map<String, Object> X7daysAvgContribution = selectXdaysAvgContributionList2(searchData, session);
		List<NewPlayerValue> X7daysAvgContributionList = (List<NewPlayerValue>) X7daysAvgContribution.get("data");
		searchData.setChecktype1("14");
		Map<String, Object> X14daysAvgContribution = selectXdaysAvgContributionList2(searchData, session);
		List<NewPlayerValue> X14daysAvgContributionList = (List<NewPlayerValue>) X14daysAvgContribution.get("data");
		searchData.setChecktype1("30");
		Map<String, Object> X30daysAvgContribution = selectXdaysAvgContributionList2(searchData, session);
		List<NewPlayerValue> X30daysAvgContributionList = (List<NewPlayerValue>) X30daysAvgContribution.get("data");
		searchData.setChecktype1("60");
		Map<String, Object> X60daysAvgContribution = selectXdaysAvgContributionList2(searchData, session);
		List<NewPlayerValue> X60daysAvgContributionList = (List<NewPlayerValue>) X60daysAvgContribution.get("data");
		searchData.setChecktype1("90");
		Map<String, Object> X90daysAvgContribution = selectXdaysAvgContributionList2(searchData, session);
		List<NewPlayerValue> X90daysAvgContributionList = (List<NewPlayerValue>) X90daysAvgContribution.get("data");
		
		
		//
		if(X7daysAvgContributionList != null){
			String[] title = new String[] {"日期","平均贡献"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(NewPlayerValue e : X7daysAvgContributionList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getData();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("7日内平均贡献");
		}
		if(X14daysAvgContributionList != null){
			String[] title = new String[] {"日期","平均贡献"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(NewPlayerValue e : X14daysAvgContributionList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getData();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("14日内平均贡献");
		}
		if(X30daysAvgContributionList != null){
			String[] title = new String[] {"日期","平均贡献"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(NewPlayerValue e : X30daysAvgContributionList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getData();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("30日内平均贡献");
		}
		if(X60daysAvgContributionList != null){
			String[] title = new String[] {"日期","平均贡献"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(NewPlayerValue e : X60daysAvgContributionList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getData();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("60日内平均贡献");
		}
		if(X90daysAvgContributionList != null){
			String[] title = new String[] {"日期","平均贡献"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(NewPlayerValue e : X90daysAvgContributionList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getData();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("90日内平均贡献");
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
	
	@RequestMapping(value = "/selectXdaysAvgContributionList2", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectXdaysAvgContributionList2(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectXdaysAvgContributionList2";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		try{
			List<NewPlayerValue> list = selectXdaysAvgContributionList(searchData, session);
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("avg", 0);
			int total = 0;
			for(int i=0;list!=null && i<list.size();i++){
				if(list.get(i).getData() == null){
					list.get(i).setData(0.0);
				}
				total += toInteger(list.get(i).getData());
			}
			if(list != null && list.size()!=0){
				result.put("avg", total/list.size());
			}
			result.put("data", list);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	
	private int toInteger(Double data) {
		return (int) (data == null ? 0 : data.doubleValue());
	}

	@RequestMapping(value = "/exportXdaysAvgContributionList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<NewPlayerValue> exportXdaysAvgContributionList(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportXdaysAvgContributionList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return newPlayerValueService.exportXdaysAvgContributionList(searchData, session);
	}
	
	/**
	 * 单日付费金额
	 * @Title: selectSimpleDayPayMoneyList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<NewPlayerValue>
	 * @author hwj
	 * @date 2017-6-29 下午02:08:51
	 */
	@RequestMapping(value = "/selectSimpleDayPayMoneyList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<NewPlayerValue> selectSimpleDayPayMoneyList(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectSimpleDayPayMoneyList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<NewPlayerValue> simDayPayMoneyList=null;
		try {
			simDayPayMoneyList= newPlayerValueService.selectSimpleDayPayMoneyList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return simDayPayMoneyList;
	}
	
	/**
	 * 累计付费金额
	 * @Title: selectPerDayTotalPayMoneyList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-6-29 下午02:30:44
	 */
	@RequestMapping(value = "/selectPerDayTotalPayMoneyList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectPerDayTotalPayMoneyList(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPerDayTotalPayMoneyList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<NewPlayerValue> perDayTotalPayMoneyList=null;
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,Object> total=new HashMap<String, Object>();
		Integer totalAccount=0;
		Double day1TotalPayMoney=0.00;
		Double day2TotalPayMoney=0.00;
		Double day3TotalPayMoney=0.00;
		Double day4TotalPayMoney=0.00;
		Double day5TotalPayMoney=0.00;
		Double day6TotalPayMoney=0.00;
		Double day7TotalPayMoney=0.00;
		Double day14TotalPayMoney=0.00;
		Double day30TotalPayMoney=0.00;
		Double day60TotalPayMoney=0.00;
		Double day90TotalPayMoney=0.00;
		try {
			perDayTotalPayMoneyList= newPlayerValueService.selectPerDayTotalPayMoneyList(searchData);
			if(perDayTotalPayMoneyList!=null&&perDayTotalPayMoneyList.size()>0){
				for(NewPlayerValue e:perDayTotalPayMoneyList){
					totalAccount+=e.getAccounts();
					day1TotalPayMoney+=e.getMoney1();
					day2TotalPayMoney+=e.getMoney2();
					day3TotalPayMoney+=e.getMoney3();
					day4TotalPayMoney+=e.getMoney4();
					day5TotalPayMoney+=e.getMoney5();
					day6TotalPayMoney+=e.getMoney6();
					day7TotalPayMoney+=e.getMoney7();
					day14TotalPayMoney+=e.getMoney14();
					day30TotalPayMoney+=e.getMoney30();
					day60TotalPayMoney+=e.getMoney60();
					day90TotalPayMoney+=e.getMoney90();
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		total.put("totalAccount", totalAccount);
		total.put("day1TotalPayMoney", day1TotalPayMoney);
		total.put("day2TotalPayMoney", day2TotalPayMoney);
		total.put("day3TotalPayMoney", day3TotalPayMoney);
		total.put("day4TotalPayMoney", day4TotalPayMoney);
		total.put("day5TotalPayMoney", day5TotalPayMoney);
		total.put("day6TotalPayMoney", day6TotalPayMoney);
		total.put("day7TotalPayMoney", day7TotalPayMoney);
		total.put("day14TotalPayMoney", day14TotalPayMoney);
		total.put("day30TotalPayMoney", day30TotalPayMoney);
		total.put("day60TotalPayMoney", day60TotalPayMoney);
		total.put("day90TotalPayMoney", day90TotalPayMoney);
		map.put("data", perDayTotalPayMoneyList);
		map.put("total", total);
		return map;
	}
	/**
	 * N日账号平均贡献
	 * @Title: selectNDayAvgConList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-6-29 下午02:43:56
	 */
	@RequestMapping(value = "/selectNDayAvgConList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectNDayAvgConList(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectNDayAvgConList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<NewPlayerValue> perDayTotalPayMoneyList=null;
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,Object> total=new HashMap<String, Object>();//汇总
		List<NewPlayerValue> NDayConList=new ArrayList<NewPlayerValue>();
		Integer totalAccount=0;//总新增账号数
		//某日累计付费的总和
		Double day1TotalPayMoney=0.00;
		Double day2TotalPayMoney=0.00;
		Double day3TotalPayMoney=0.00;
		Double day4TotalPayMoney=0.00;
		Double day5TotalPayMoney=0.00;
		Double day6TotalPayMoney=0.00;
		Double day7TotalPayMoney=0.00;
		Double day14TotalPayMoney=0.00;
		Double day30TotalPayMoney=0.00;
		Double day60TotalPayMoney=0.00;
		Double day90TotalPayMoney=0.00;
		//
		Double day1TotalAVGCon=0.00;//总1日平均贡献
		Double day2TotalAVGCon=0.00;//总2日平均贡献
		Double day3TotalAVGCon=0.00;//总3日平均贡献
		Double day4TotalAVGCon=0.00;//总4日平均贡献
		Double day5TotalAVGCon=0.00;//总5日平均贡献
		Double day6TotalAVGCon=0.00;//总6日平均贡献
		Double day7TotalAVGCon=0.00;//总7日平均贡献
		Double day14TotalAVGCon=0.00;//总14日平均贡献
		Double day30TotalAVGCon=0.00;//总30日平均贡献
		Double day60TotalAVGCon=0.00;//总60日平均贡献
		Double day90TotalAVGCon=0.00;//总90日平均贡献
		try {
			perDayTotalPayMoneyList= newPlayerValueService.selectPerDayTotalPayMoneyList(searchData);
			//求Nday的平均贡献列表
			if(perDayTotalPayMoneyList!=null&&perDayTotalPayMoneyList.size()>0){
				for(NewPlayerValue e:perDayTotalPayMoneyList){
					NewPlayerValue con=new NewPlayerValue();
					con.setStatdate(e.getStatdate());
					con.setAccounts(e.getAccounts());
					con.setDay1Con(e.getAccounts().intValue()==0?0.00:NumberUtil.ajustScale((e.getMoney1()/e.getAccounts()), 2));
					con.setDay2Con(e.getAccounts().intValue()==0?0.00:NumberUtil.ajustScale((e.getMoney2()/e.getAccounts()), 2));
					con.setDay3Con(e.getAccounts().intValue()==0?0.00:NumberUtil.ajustScale((e.getMoney3()/e.getAccounts()), 2));
					con.setDay4Con(e.getAccounts().intValue()==0?0.00:NumberUtil.ajustScale((e.getMoney4()/e.getAccounts()), 2));
					con.setDay5Con(e.getAccounts().intValue()==0?0.00:NumberUtil.ajustScale((e.getMoney5()/e.getAccounts()), 2));
					con.setDay6Con(e.getAccounts().intValue()==0?0.00:NumberUtil.ajustScale((e.getMoney6()/e.getAccounts()), 2));
					con.setDay7Con(e.getAccounts().intValue()==0?0.00:NumberUtil.ajustScale((e.getMoney7()/e.getAccounts()), 2));
					con.setDay14Con(e.getAccounts().intValue()==0?0.00:NumberUtil.ajustScale((e.getMoney14()/e.getAccounts()), 2));
					con.setDay30Con(e.getAccounts().intValue()==0?0.00:NumberUtil.ajustScale((e.getMoney30()/e.getAccounts()), 2));
					con.setDay60Con(e.getAccounts().intValue()==0?0.00:NumberUtil.ajustScale((e.getMoney60()/e.getAccounts()), 2));
					con.setDay90Con(e.getAccounts().intValue()==0?0.00:NumberUtil.ajustScale((e.getMoney90()/e.getAccounts()), 2));
					NDayConList.add(con);
					totalAccount+=e.getAccounts();
					//某日累计付费的总和
					day1TotalPayMoney+=e.getMoney1();
					day2TotalPayMoney+=e.getMoney2();
					day3TotalPayMoney+=e.getMoney3();
					day4TotalPayMoney+=e.getMoney4();
					day5TotalPayMoney+=e.getMoney5();
					day6TotalPayMoney+=e.getMoney6();
					day7TotalPayMoney+=e.getMoney7();
					day14TotalPayMoney+=e.getMoney14();
					day30TotalPayMoney+=e.getMoney30();
					day60TotalPayMoney+=e.getMoney60();
					day90TotalPayMoney+=e.getMoney90();
				}
			}
			//某日平均贡献的总和
			day1TotalAVGCon=NumberUtil.div(day1TotalPayMoney, totalAccount.doubleValue(), 2);
			day2TotalAVGCon=NumberUtil.div(day2TotalPayMoney, totalAccount.doubleValue(), 2);
			day3TotalAVGCon=NumberUtil.div(day3TotalPayMoney, totalAccount.doubleValue(), 2);
			day4TotalAVGCon=NumberUtil.div(day4TotalPayMoney, totalAccount.doubleValue(), 2);
			day5TotalAVGCon=NumberUtil.div(day5TotalPayMoney, totalAccount.doubleValue(), 2);
			day6TotalAVGCon=NumberUtil.div(day6TotalPayMoney, totalAccount.doubleValue(), 2);
			day7TotalAVGCon=NumberUtil.div(day7TotalPayMoney, totalAccount.doubleValue(), 2);
			day14TotalAVGCon=NumberUtil.div(day14TotalPayMoney, totalAccount.doubleValue(), 2);
			day30TotalAVGCon=NumberUtil.div(day30TotalPayMoney, totalAccount.doubleValue(), 2);
			day60TotalAVGCon=NumberUtil.div(day60TotalPayMoney, totalAccount.doubleValue(), 2);
			day90TotalAVGCon=NumberUtil.div(day90TotalPayMoney, totalAccount.doubleValue(), 2);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		map.put("data", NDayConList);
		total.put("totalAccount", totalAccount);
		total.put("day1TotalAVGCon", day1TotalAVGCon);
		total.put("day2TotalAVGCon", day2TotalAVGCon);
		total.put("day3TotalAVGCon", day3TotalAVGCon);
		total.put("day4TotalAVGCon", day4TotalAVGCon);
		total.put("day5TotalAVGCon", day5TotalAVGCon);
		total.put("day6TotalAVGCon", day6TotalAVGCon);
		total.put("day7TotalAVGCon", day7TotalAVGCon);
		total.put("day14TotalAVGCon", day14TotalAVGCon);
		total.put("day30TotalAVGCon", day30TotalAVGCon);
		total.put("day60TotalAVGCon", day60TotalAVGCon);
		total.put("day90TotalAVGCon", day90TotalAVGCon);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 导出N日账号平均贡献
	 * @Title: exportNDayAccountAvgContribute
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-6-30 下午02:03:35
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/exportNDayAccountAvgContribute", method = {
			RequestMethod.POST})
	public void exportNDayAccountAvgContribute(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportNDayAccountAvgContribute", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "新玩家收入贡献-多日汇总.xlsx";
		//单日付费金额
		List<NewPlayerValue> simpleDayPayMoneyList= selectSimpleDayPayMoneyList(searchData,session);
		//累计付费金额
		Map<String,Object> payMoneyMap=selectPerDayTotalPayMoneyList(searchData,session);
		List<NewPlayerValue> totalPayMoneyList=(List<NewPlayerValue>) payMoneyMap.get("data");
		Map<String,Object> totalMap=(Map<String, Object>) payMoneyMap.get("total");
		//N日账号平均贡献
		Map<String,Object> avgConMap=selectNDayAvgConList(searchData,session);
		List<NewPlayerValue> NdayAvgConList=(List<NewPlayerValue>) avgConMap.get("data");
		Map<String,Object> totalAvgConMap=(Map<String, Object>) avgConMap.get("total");
		//单日付费金额
		if(simpleDayPayMoneyList != null){
			String[] title = new String[] {"日期","新增账号数","1日付费金额","2日付费金额","3日付费金额","4日付费金额","5日付费金额","6日付费金额","7日付费金额"
					,"8-14日付费金额","15-30日付费金额","31-60日付费金额","61-90日付费金额"};
			List<Object[]> simpleDaydatalist = new LinkedList();
			simpleDaydatalist.add(title);
			for(NewPlayerValue e : simpleDayPayMoneyList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getAccounts();
				items[count++] = e.getMoney1();
				items[count++] = e.getMoney2();
				items[count++] = e.getMoney3();
				items[count++] = e.getMoney4();
				items[count++] = e.getMoney5();
				items[count++] = e.getMoney6();
				items[count++] = e.getMoney7();
				items[count++] = e.getMoney14();
				items[count++] = e.getMoney30();
				items[count++] = e.getMoney60();
				items[count++] = e.getMoney90();
				simpleDaydatalist.add(items);
			}
			list.add(simpleDaydatalist);
			sheetNameList.add("单日付费金额");
		}
		//累计付费金额
		if(totalPayMoneyList != null){
			String[] title = new String[] {"日期","新增账号数","1日累计付费","2日累计付费","3日累计付费","4日累计付费","5日累计付费","6日累计付费","7日累计付费"
					,"14日累计付费","30日累计付费","60日累计付费","90日累计付费"};
			List<Object[]> totalPayDatalist = new LinkedList();
			totalPayDatalist.add(title);
			if(totalPayMoneyList!=null&&totalPayMoneyList.size()>0){
				for(NewPlayerValue e : totalPayMoneyList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = e.getStatdate();
					items[count++] = e.getAccounts();
					items[count++] = e.getMoney1();
					items[count++] = e.getMoney2();
					items[count++] = e.getMoney3();
					items[count++] = e.getMoney4();
					items[count++] = e.getMoney5();
					items[count++] = e.getMoney6();
					items[count++] = e.getMoney7();
					items[count++] = e.getMoney14();
					items[count++] = e.getMoney30();
					items[count++] = e.getMoney60();
					items[count++] = e.getMoney90();
					totalPayDatalist.add(items);
				}
				Object[] item = new  Object[title.length];
				int count = 0;
				item[count++] ="汇总";
				item[count++] =totalMap.get("totalAccount");
				item[count++] =totalMap.get("day1TotalPayMoney");
				item[count++] =totalMap.get("day2TotalPayMoney");
				item[count++] =totalMap.get("day3TotalPayMoney");
				item[count++] =totalMap.get("day4TotalPayMoney");
				item[count++] =totalMap.get("day5TotalPayMoney");
				item[count++] =totalMap.get("day6TotalPayMoney");
				item[count++] =totalMap.get("day7TotalPayMoney");
				item[count++] =totalMap.get("day14TotalPayMoney");
				item[count++] =totalMap.get("day30TotalPayMoney");
				item[count++] =totalMap.get("day60TotalPayMoney");
				item[count++] =totalMap.get("day90TotalPayMoney");
				totalPayDatalist.add(item);
			}
			list.add(totalPayDatalist);
			sheetNameList.add("累计付费金额");
		}
		//N日平均贡献
		if(NdayAvgConList != null){
			String[] title = new String[] {"日期","新增账号数","1日平均贡献","2日平均贡献","3日平均贡献","4日平均贡献","5日平均贡献","6日平均贡献","7日平均贡献"
					,"14日平均贡献","30日平均贡献","60日平均贡献","90日平均贡献"};
			List<Object[]> totalConDatalist = new LinkedList();
			totalConDatalist.add(title);
			if(NdayAvgConList!=null&&NdayAvgConList.size()>0){
				for(NewPlayerValue e : NdayAvgConList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = e.getStatdate();
					items[count++] = e.getAccounts();
					items[count++] = e.getDay1Con();
					items[count++] = e.getDay2Con();
					items[count++] = e.getDay3Con();
					items[count++] = e.getDay4Con();
					items[count++] = e.getDay5Con();
					items[count++] = e.getDay6Con();
					items[count++] = e.getDay7Con();
					items[count++] = e.getDay14Con();
					items[count++] = e.getDay30Con();
					items[count++] = e.getDay60Con();
					items[count++] = e.getDay90Con();
					totalConDatalist.add(items);
				}
				Object[] item = new  Object[title.length];
				int count = 0;
				item[count++] ="汇总";
				item[count++] =totalAvgConMap.get("totalAccount");
				item[count++] =totalAvgConMap.get("day1TotalAVGCon");
				item[count++] =totalAvgConMap.get("day2TotalAVGCon");
				item[count++] =totalAvgConMap.get("day3TotalAVGCon");
				item[count++] =totalAvgConMap.get("day4TotalAVGCon");
				item[count++] =totalAvgConMap.get("day5TotalAVGCon");
				item[count++] =totalAvgConMap.get("day6TotalAVGCon");
				item[count++] =totalAvgConMap.get("day7TotalAVGCon");
				item[count++] =totalAvgConMap.get("day14TotalAVGCon");
				item[count++] =totalAvgConMap.get("day30TotalAVGCon");
				item[count++] =totalAvgConMap.get("day60TotalAVGCon");
				item[count++] =totalAvgConMap.get("day90TotalAVGCon");
				totalConDatalist.add(item);
			}
			list.add(totalConDatalist);
			sheetNameList.add("N日账号平均贡献");
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
	 * 充值区域
	 * @Title: selectRechargeCityList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<NewPlayerValue>
	 * @author hwj
	 * @date 2017-8-25 下午12:36:57
	 */
	@RequestMapping(value="/selectRechargeCityList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<NewPlayerValue> selectRechargeCityList(@RequestBody BaseSearchData searchData, HttpSession session) throws Exception{
		//log
		Date opDate = new Date();
		String methodName = "selectRechargeCityList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<NewPlayerValue> cityList=null;
		cityList=newPlayerValueService.selectRechargeCityList(searchData);
		return cityList;
		
	}
	
	
	


}
