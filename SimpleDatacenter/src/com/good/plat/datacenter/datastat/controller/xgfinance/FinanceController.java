package com.good.plat.datacenter.datastat.controller.xgfinance;

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
import com.good.plat.datacenter.common.base.XgFinanceBaseSearchData;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.datastat.entity.whales.Whales;
import com.good.plat.datacenter.datastat.entity.xgfinance.FinanceCost;
import com.good.plat.datacenter.datastat.entity.xgfinance.FinanceGameUser;
import com.good.plat.datacenter.datastat.mapper.xgfinance.FinanceGameUserMapper;
import com.good.plat.datacenter.datastat.service.xgfinance.FinanceCostService;
import com.good.plat.datacenter.datastat.service.xgfinance.FinanceGameUserService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;
@Controller
@RequestMapping(value="/xgfinace")
public class FinanceController extends BaseController {
	@Autowired
	private FinanceCostService financeCostServiceImpl;
	@Autowired
	private FinanceGameUserService financeGameUserServiceImpl;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	/**
	 * 财务费用及汇总
	 * @Title: selectFinanceCostList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-8-12 上午11:46:12
	 */
	@RequestMapping(value = "/selectFinanceCostList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectFinanceCostList(@RequestBody XgFinanceBaseSearchData searchData){
		Map<String,Object> costMap=null;
		List<FinanceCost> costList=null;
		Map<String,Object> totalData=null;
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectFinanceCostList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			costList=financeCostServiceImpl.selectFinanceCostList(searchData);//财务费用
			costMap=new HashMap<String,Object>();
			totalData=new HashMap<String, Object>();
			//汇总
			Integer totalUserNum=0;//总用户数
			Double totalPrice=0.0;//总单价
			Double totalMoney=0.0;//总金额
			Double totalRate=0.0;//总汇率
			Double totalDollar=0.0;//总折算美元值
			if(costList!=null&&costList.size()>0){
				for(FinanceCost e:costList){
					totalUserNum+=e.getUserNum();
					totalPrice+=e.getPrice();
					totalMoney+=e.getMoney();
					totalRate+=e.getRate();
					totalDollar+=e.getDollar();
				}
			}
			totalData.put("totalUserNum", totalUserNum);
			totalData.put("totalPrice", totalPrice);
			totalData.put("totalMoney", totalMoney);
			totalData.put("totalRate", totalRate);
			totalData.put("totalDollar", totalDollar);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		costMap.put("costList", costList);
		costMap.put("totalData", totalData);
		return costMap;
	}
	/**
	 * 财务游戏用户数统计
	 * @Title: selectFinanceGameUserList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-8-12 下午02:12:00
	 */
	@RequestMapping(value = "/selectFinanceGameUserNumList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectFinanceGameUserNumList(@RequestBody XgFinanceBaseSearchData searchData){
		Map<String,Object> userNumMap=null;
		List<FinanceGameUser> gameUserNumList=null;
		Map<String,Object> totalData=null;
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectFinanceGameUserNumList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			gameUserNumList=financeGameUserServiceImpl.selectGameUserNumList(searchData);
			userNumMap=new HashMap<String,Object>();
			totalData=new HashMap<String, Object>();
			//汇总
			Integer totalNewUserNum=0;//总新增用户数
			if(gameUserNumList!=null&&gameUserNumList.size()>0){
				for(FinanceGameUser e:gameUserNumList){
					totalNewUserNum+=e.getNewUser();
				}
			}
			totalData.put("totalNewUserNum", totalNewUserNum);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		userNumMap.put("gameUserNumList", gameUserNumList);
		userNumMap.put("totalData", totalData);
		return userNumMap;
	}
	
	/**
	 * 导出财务费用
	 * @Title: exportFinanceCostList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-8-14 上午10:21:51
	 */
	@RequestMapping(value = "/exportFinanceCostList", method = {
			RequestMethod.POST})
	public void exportFinanceCostList(
			@RequestBody XgFinanceBaseSearchData searchData, HttpSession session,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportFinanceCostList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "财务费用.xlsx";
		//
		List<FinanceCost> costList=financeCostServiceImpl.selectFinanceCostList(searchData);
		
		//汇总
		Integer totalUserNum=0;//总用户数
		Double totalPrice=0.0;//总单价
		Double totalMoney=0.0;//总金额
		Double totalRate=0.0;//总汇率
		Double totalDollar=0.0;//总折算美元值
		//
		if(costList != null){
			String[] title = new String[] {"客户名称","游戏","用户数","单价（港币）","推广费金额（港币）","汇兑率","折算成美金"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			if(costList.size()>0){
				for(FinanceCost e : costList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = e.getCustomerName();
					items[count++] = e.getGameName();
					items[count++] = e.getUserNum();
					items[count++] = e.getPrice();
					items[count++] = e.getMoney();
					items[count++] = e.getRate();
					items[count++] = e.getDollar();
					totalUserNum+=e.getUserNum();
					totalPrice+=e.getPrice();
					totalMoney+=e.getMoney();
					totalRate+=e.getRate();
					totalDollar+=e.getDollar();
					datalist.add(items);
				}
				Object[] totalNum=new Object[title.length];
				int count = 0;
				totalNum[count++]="汇总";
				totalNum[count++]="";
				totalNum[count++]=totalUserNum;
				totalNum[count++]=totalPrice;
				totalNum[count++]=totalMoney;
				totalNum[count++]=totalRate;
				totalNum[count++]=totalDollar;
				datalist.add(totalNum);
			}
			list.add(datalist);
			sheetNameList.add("财务费用");
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
	 * 导出游戏用户数
	 * @Title: exportFinanceGameUserNumList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-8-14 上午10:35:32
	 */
	@RequestMapping(value = "/exportFinanceGameUserNumList", method = {
			RequestMethod.POST})
	public void exportFinanceGameUserNumList(
			@RequestBody XgFinanceBaseSearchData searchData, HttpSession session,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportFinanceGameUserNumList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "游戏用户数.xlsx";
		//
		List<FinanceGameUser> userNumList=financeGameUserServiceImpl.selectGameUserNumList(searchData);
		
		//汇总
		Integer totalNewUserNum=0;//总新增用户数
		if(userNumList!=null&&userNumList.size()>0){
			for(FinanceGameUser e:userNumList){
				totalNewUserNum+=e.getNewUser();
			}
		}
		//
		if(userNumList != null){
			String[] title = new String[] {"日期","游戏","新增用户总数"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			if(userNumList.size()>0){
				for(FinanceGameUser e : userNumList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = e.getStatdate();
					items[count++] = e.getGameName();
					items[count++] = e.getNewUser();
					datalist.add(items);
				}
				Object[] totalNum=new Object[title.length];
				int count = 0;
				totalNum[count++]="汇总";
				totalNum[count++]="";
				totalNum[count++]=totalNewUserNum;
				datalist.add(totalNum);
			}
			list.add(datalist);
			sheetNameList.add("游戏用户数");
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
