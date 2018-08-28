package com.good.plat.datacenter.datastat.controller.realtimedata;

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
import com.good.plat.datacenter.datastat.entity.players.AccountChurn;
import com.good.plat.datacenter.datastat.entity.realtimedata.RealTimeSummaryData;
import com.good.plat.datacenter.datastat.service.realtimedata.RealTimeSummaryDataService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;
/**
 * 实时——游戏概况
 * @ClassName: RealTimeSummaryDataController
 * @Description: TODO
 * @author hwj
 * @date 2017-12-11 下午03:55:23
 */
@Controller
@RequestMapping(value="/realTimedata")
public class RealTimeSummaryDataController extends BaseController {
	@Autowired
	private RealTimeSummaryDataService realTimeSummaryDataService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	/**
	 * 玩家总览数据
	 * @Title: selectPlayerSummaryData
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * SummaryData
	 * @author hwj
	 * @date 2017-12-11 下午03:50:54
	 */
	@RequestMapping(value="/selectPlayerSummaryData", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<RealTimeSummaryData> selectPlayerSummaryData (@RequestBody BaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectPlayerSummaryData";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<RealTimeSummaryData> list =null;
		try {
			list=realTimeSummaryDataService.selectRalTimeSummaryDataList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 导出玩家总览数据
	 * @Title: exportPlayerSummaryData
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-12-12 下午02:25:17
	 */
	@RequestMapping(value="/exportPlayerSummaryData",method={RequestMethod.POST})
	public void exportPlayerSummaryData(@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response) throws Exception{
		//log
		sysAccessLogService.log(request, getClass(), "exportPlayerSummaryData", new Date(), searchData);		
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "按实时统计玩家.xlsx";
		//
		List<RealTimeSummaryData> summaryList=realTimeSummaryDataService.selectRalTimeSummaryDataList(searchData);
		//
		if(summaryList != null){
			String[] title = new String[] {"日期","激活设备数","活跃玩家(角色)","付费玩家(角色)","收入"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(RealTimeSummaryData e : summaryList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getDeviceActivation();
				items[count++] = e.getActUser();
				items[count++] = e.getPayUser();
				items[count++] = e.getIncome();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("按实时统计玩家");
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
