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
import com.good.plat.datacenter.independ.entity.yhindex.YhIframeStat;
import com.good.plat.datacenter.independ.service.yhindex.YhIframeStatService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;
@Controller
@RequestMapping(value="/yhIframeStat")
public class YhIframeStatController extends BaseController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private YhIframeStatService yhIframeStatService;
	/**
	 * 查询头像数据
	 * @Title: selectFramDetailList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhAchieveStat>
	 * @author hwj
	 * @date 2018-5-21 下午06:42:39
	 */
	@RequestMapping(value="/selectHeadPortraitList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhIframeStat> selectHeadPortraitList(@RequestBody IndependBaseSearchData searchData) throws Exception{
		List<YhIframeStat> list=new ArrayList<YhIframeStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectHeadPortraitList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			searchData.setChecktype1("1");//头像
			list=yhIframeStatService.selectYhIframeStatList(searchData);
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	/**
	 * 导出头像数据
	 * @Title: exportAchieveStatList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-21 下午06:49:01
	 */
	@RequestMapping(value = "/exportHeadPortraitList", method = {
			RequestMethod.POST})
	public void exportAchieveStatList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportHeadPortraitList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "头像统计.xlsx";
		searchData.setChecktype1("1");//头像
		List<YhIframeStat> headList =yhIframeStatService.selectYhIframeStatList(searchData);
		//导出list
		if(headList != null){
			String[] title = new String[] {"日期","区服","头像id","使用此头像的玩家数量","解锁了此头像的玩家数量","使用占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhIframeStat e : headList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getIframeid();
				items[count++] =e.getWearActCnt();
				items[count++] =e.getUnlockActCnt();
				items[count++] =e.getWearActCntRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("头像统计");
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
	 * 查询头框数据
	 * @Title: selectHeadFrameList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhIframeStat>
	 * @author hwj
	 * @date 2018-5-21 下午06:45:37
	 */
	@RequestMapping(value="/selectHeadFrameList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhIframeStat> selectHeadFrameList(@RequestBody IndependBaseSearchData searchData) throws Exception{
		List<YhIframeStat> list=new ArrayList<YhIframeStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectHeadFrameList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			searchData.setChecktype1("2");//头框
			list=yhIframeStatService.selectYhIframeStatList(searchData);
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	/**
	 * 导出头框
	 * @Title: exportHeadFrameList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-21 下午06:49:58
	 */
	@RequestMapping(value = "/exportHeadFrameList", method = {
			RequestMethod.POST})
	public void exportHeadFrameList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportHeadFrameList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "头框统计.xlsx";
		searchData.setChecktype1("2");//头框
		List<YhIframeStat> headList =yhIframeStatService.selectYhIframeStatList(searchData);
		//导出list
		if(headList != null){
			String[] title = new String[] {"日期","区服","头像框id","使用此头像框的玩家数量","解锁了此头像框的玩家数量","使用占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhIframeStat e : headList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getIframeid();
				items[count++] =e.getWearActCnt();
				items[count++] =e.getUnlockActCnt();
				items[count++] =e.getWearActCntRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("头像统计");
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
