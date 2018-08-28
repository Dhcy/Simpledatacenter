package com.good.plat.datacenter.independ.controller.yhindex;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.independ.entity.yhindex.YhArenaStat;
import com.good.plat.datacenter.independ.service.yhindex.YhArenaStatService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;
@Controller
@RequestMapping(value="/yhArenastat")
public class YhArenastatController extends BaseController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private YhArenaStatService yhArenaStatService;
	/**
	 * 段位统计
	 * @Title: selectYhDanStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhArenaStat>
	 * @author hwj
	 * @date 2018-5-22 上午11:50:42
	 */
	@RequestMapping(value="/selectYhDanStatList",method=RequestMethod.POST)
	@ResponseBody
	public List<YhArenaStat> selectYhDanStatList(@RequestBody IndependBaseSearchData searchData)throws Exception{
		List<YhArenaStat> list=new ArrayList<YhArenaStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectYhDanStatList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list=yhArenaStatService.selectYhDanStatList(searchData);
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	/**
	 * 导出段位统计
	 * @Title: exportYhDanStatList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-22 上午11:59:04
	 */
	@RequestMapping(value = "/exportYhDanStatList", method = {
			RequestMethod.POST})
	public void exportYhDanStatList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYhDanStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "段位统计.xlsx";
		List<YhArenaStat> danList =yhArenaStatService.selectYhDanStatList(searchData);
		//导出list
		if(danList != null){
			String[] title = new String[] {"日期","区服","段位","玩家数量","此段位玩家占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhArenaStat e : danList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getDan();
				items[count++] =e.getCount();
				items[count++] =e.getRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("段位统计");
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
	 * 各分段匹配时长统计
	 * @Title: selectPerMateTimeStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhArenaStat>
	 * @author hwj
	 * @date 2018-5-22 下午12:33:54
	 */
	@RequestMapping(value="/selectPerMateTimeStatList",method=RequestMethod.POST)
	@ResponseBody
	public List<YhArenaStat> selectPerMateTimeStatList(@RequestBody IndependBaseSearchData searchData)throws Exception{
		List<YhArenaStat> list=new ArrayList<YhArenaStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectPerMateTimeStatList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list=yhArenaStatService.selectPerMateTimeStatList(searchData);
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	/**
	 * 导出各分段匹配时长统计
	 * @Title: exportPerMateTimeStatList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-22 下午12:38:13
	 */
	@RequestMapping(value = "/exportPerMateTimeStatList", method = {
			RequestMethod.POST})
	public void exportPerMateTimeStatList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportPerMateTimeStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "各分段匹配时长统计.xlsx";
		List<YhArenaStat> mateList =yhArenaStatService.selectPerMateTimeStatList(searchData);
		//导出list
		if(mateList != null){
			String[] title = new String[] {"日期","区服","匹配时间区间","匹配结果","次数","次数占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhArenaStat e : mateList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getTime();
				items[count++] =e.getTypeDesc();
				items[count++] =e.getTypeDesc();
				items[count++] =e.getCount();
				items[count++] =e.getRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("各分段匹配时长统计");
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
	 * 各时段在线人数统计
	 * @Title: selectPerHourOnlineCountStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhArenaStat>
	 * @author hwj
	 * @date 2018-5-22 下午12:38:57
	 */
	@RequestMapping(value="/selectPerHourOnlineCountStatList",method=RequestMethod.POST)
	@ResponseBody
	public List<YhArenaStat> selectPerHourOnlineCountStatList(@RequestBody IndependBaseSearchData searchData)throws Exception{
		List<YhArenaStat> list=new ArrayList<YhArenaStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectPerHourOnlineCountStatList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list=yhArenaStatService.selectPerHourOnlineCountStatList(searchData);
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	/**
	 * 导出各时段在线人数统计
	 * @Title: exportPerHourOnlineCountStatList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-22 下午12:45:53
	 */
	@RequestMapping(value = "/exportPerHourOnlineCountStatList", method = {
			RequestMethod.POST})
	public void exportPerHourOnlineCountStatList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportPerHourOnlineCountStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "各时段在线人数统计.xlsx";
		List<YhArenaStat> mateList =yhArenaStatService.selectPerHourOnlineCountStatList(searchData);
		//导出list
		if(mateList != null){
			String[] title = new String[] {"渠道","日期","时段","在线人数","参与竞技场人数","参与军团战人数"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhArenaStat e : mateList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getChannelName();
				items[count++] =e.getStatdate();
				items[count++] =e.getHourDesc();
				items[count++] =e.getCount();
				items[count++] =e.getTypeDesc();
				items[count++] =e.getJjcCnt();
				items[count++] =e.getJtzCnt();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("各时段在线人数统计");
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
