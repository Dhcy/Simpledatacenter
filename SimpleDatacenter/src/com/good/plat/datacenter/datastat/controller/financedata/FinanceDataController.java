package com.good.plat.datacenter.datastat.controller.financedata;

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
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.financedata.FinanceData;
import com.good.plat.datacenter.datastat.entity.xgfinance.FinanceGameUser;
import com.good.plat.datacenter.datastat.service.financedata.FinanceDataService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;
@Controller
@RequestMapping(value="/financeData")
public class FinanceDataController extends BaseController {
	@Autowired
	private FinanceDataService financeDataService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	/**
	 * 财务游戏用户
	 * @Title: selectFinanceGameUserNumList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2018-3-2 下午04:48:18
	 */
	@RequestMapping(value = "/selectFinanceGameUserNumList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectFinanceGameUserNumList(@RequestBody BaseSearchData searchData){
		Map<String,Object> userNumMap=new HashMap<String,Object>();
		List<FinanceData> gameUserNumList=null;
		Map<String,Object> totalMap=new HashMap<String, Object>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectFinanceGameUserNumList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			gameUserNumList=financeDataService.selectfinanceGameUserList(searchData);
			if(gameUserNumList!=null&&!gameUserNumList.isEmpty()){
				//汇总
				Integer totalNewUserNum=0;//总新增用户数
				Double totalPrice=10.00;//总单价
				Double totalMoney=0.00;
				for(FinanceData e:gameUserNumList){
					totalNewUserNum+=e.getNewUser();
					totalMoney+=e.getMoney();
				}
				totalMap.put("newUser", totalNewUserNum);
				totalMap.put("price", totalPrice);
				totalMap.put("money", totalMoney);
				totalMap.put("statdate", "汇总");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		userNumMap.put("gameUserNumList", gameUserNumList);
		userNumMap.put("total", totalMap);
		return userNumMap;
	}
	
	@RequestMapping(value = "/exportFinanceGameUserNumList", method = {
			RequestMethod.POST})
	public void exportFinanceGameUserNumList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response) throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportFinanceGameUserNumList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "游戏用户数.xlsx";
		//
		List<FinanceData> userNumList=financeDataService.selectfinanceGameUserList(searchData);
		
		if(userNumList!=null&&!userNumList.isEmpty()){
			//汇总
			Integer totalNewUserNum=0;//总新增用户数
			Double totalPrice=10.00;//总单价
			Double totalMoney=0.00;
			for(FinanceData e:userNumList){
				totalNewUserNum+=e.getNewUser();
				totalMoney+=e.getMoney();
			}
			FinanceData totalData=new FinanceData();
			totalData.setNewUser(totalNewUserNum);
			totalData.setPrice(totalPrice);
			totalData.setMoney(totalMoney);
			totalData.setStatdate("汇总");
			userNumList.add(totalData);
		}
		//
		if(userNumList != null){
			String[] title = new String[] {"日期","游戏","新增用户总数","单价（港币）","推广费金额（港币）"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			if(!userNumList.isEmpty()){
				for(FinanceData e : userNumList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] = e.getStatdate();
					items[count++] = e.getGameName();
					items[count++] = e.getNewUser();
					items[count++] = e.getPrice();
					items[count++] = e.getMoney();
					datalist.add(items);
				}
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
