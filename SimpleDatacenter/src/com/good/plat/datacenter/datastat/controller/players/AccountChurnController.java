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
import com.good.plat.datacenter.datastat.entity.players.AccountChurn;
import com.good.plat.datacenter.datastat.service.players.AccountChurnService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * 账号流失
 * @ClassName: AccountChurnController
 * @Description: TODO
 * @author hwj
 * @date 2017-4-26 下午02:36:03
 */
@Controller
@RequestMapping("/players/accountChurn")
public class AccountChurnController extends BaseController {

	@Autowired
	private AccountChurnService accountChurnService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	/**
	 * 每日流失数&每日流失率
	 * @Title: selectPerDayLostNumAndRate
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<AccountChurn>
	 * @author hwj
	 * @date 2017-4-26 下午02:37:27
	 */
	@RequestMapping(value = "/selectPerDayLostNumAndRate", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<AccountChurn> selectPerDayLostNumAndRate(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPerDayLostNumAndRate";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<AccountChurn> list=accountChurnService.selectPerDayLostNumAndRate(searchData);
		return list;
	}
	
	/**
	 * 流失用户分析
	 * @Title: selectLostUserAnalysNumList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<PlayersChurn>
	 * @author hwj
	 * @date 2017-4-26 下午02:38:40
	 */
	@RequestMapping(value = "/selectLostUserAnalysNumList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<AccountChurn> selectLostUserAnalysNumList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectLostUserAnalysNumList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<AccountChurn> list = accountChurnService.selectLostUserAnalysNumList(searchData);
		switch (Integer.valueOf(searchData.getChecktype3())) {
		case 1:
			try{
			Collections.sort(list, new Comparator<AccountChurn>() {
				public int compare(AccountChurn o1, AccountChurn o2) {
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
	/**
	 * 导出每日流失
	 * @Title: exportLostAndReturnPerDay
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-4-26 下午03:55:21
	 */
	@RequestMapping(value="exportPerDayLostNumAndRateList",method={RequestMethod.POST})
	public void exportLostAndReturnPerDay(@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response) throws Exception{
		//log
		sysAccessLogService.log(request, getClass(), "exportPerDayLostNumAndRateList", new Date(), searchData);		
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "每日流失.xlsx";
		//
		List<AccountChurn> perDayLostNumAndRateList = selectPerDayLostNumAndRate(searchData,session);
		//
		if(perDayLostNumAndRateList != null){
			String[] title = new String[] {"日期","全部玩家(账户数)","每日流失率(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(AccountChurn e : perDayLostNumAndRateList){
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
	 * 导出流失用户分析
	 * @Title: exportLostUserAnalysNumList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-4-26 下午04:05:30
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
		List<AccountChurn> lostUserAnalysNumList = selectLostUserAnalysNumList(searchData, session);
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
			String[] title = new String[] {subject,"每日流失数(账户)","每日流失比例(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(AccountChurn e : lostUserAnalysNumList){
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
}
