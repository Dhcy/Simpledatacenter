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
import com.good.plat.datacenter.independ.entity.yhindex.YhJtStat;
import com.good.plat.datacenter.independ.service.yhindex.YhJtStatService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;
@Controller
@RequestMapping(value="/yhJt")
public class YhJtStatController extends BaseController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private YhJtStatService yhJtStatService;
	
	/**
	 * 军团统计
	 * @Title: selectJtStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhJtStat>
	 * @author hwj
	 * @date 2018-5-21 下午01:50:09
	 */
	@RequestMapping(value="/selectJtStatList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhJtStat> selectJtStatList(@RequestBody IndependBaseSearchData searchData) throws Exception{
		List<YhJtStat> list=new ArrayList<YhJtStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectJtStatList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list=yhJtStatService.selectJtStatList(searchData);
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	/**
	 * 导出军团统计
	 * @Title: exportJtStatList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-21 下午02:19:53
	 */
	@RequestMapping(value = "/exportJtStatList", method = {
			RequestMethod.POST})
	public void exportJtStatList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportJtStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "军团统计.xlsx";
		List<YhJtStat> jtStatList =yhJtStatService.selectJtStatList(searchData);
		//导出list
		if(jtStatList != null){
			String[] title = new String[] {"日期","区服","军团数量","加入军团总人数","加入军团人数占比","开启强敌入侵的军团数量","开启强敌入侵军团占比","参与采购的军团数量","参与采购军团比例","参与军团战的军团数量","参与军团战军团比例","参与军团战人数","参与军团战人数占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhJtStat e : jtStatList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getJtCnt();
				items[count++] =e.getJoinJtActCnt();
				items[count++] =e.getJoinJtActCntRate()+"%";
				items[count++] =e.getOpenQdrqJtCnt();
				items[count++] =e.getOpenQdrqJtCntRate()+"%";
				items[count++] =e.getJoinPurchaseJtCnt();
				items[count++] =e.getJoinPurchaseJtCntRate()+"%";
				items[count++] =e.getJoinJtFightJtCnt();
				items[count++] =e.getJoinJtFightJtCntRate()+"%";
				items[count++] =e.getJoinJtFightActCnt();
				items[count++] =e.getJoinJtFightActCntRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("军团统计");
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
	 * 军团等级分布
	 * @Title: selectJtLvlDistributeList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhJtStat>
	 * @author hwj
	 * @date 2018-5-21 下午01:51:05
	 */
	@RequestMapping(value="/selectJtLvlDistributeList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhJtStat> selectJtLvlDistributeList(@RequestBody IndependBaseSearchData searchData) throws Exception{
		List<YhJtStat> list=new ArrayList<YhJtStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectJtLvlDistributeList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list=yhJtStatService.selectJtLvlDistributeList(searchData);
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	/**
	 * 导出军团等级分布
	 * @Title: exportJtLvlDistributeList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-21 下午02:41:40
	 */
	@RequestMapping(value = "/exportJtLvlDistributeList", method = {
			RequestMethod.POST})
	public void exportJtLvlDistributeList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportJtLvlDistributeList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "军团等级分布.xlsx";
		List<YhJtStat> lvlList =yhJtStatService.selectJtLvlDistributeList(searchData);
		//导出list
		if(lvlList != null){
			String[] title = new String[] {"日期","区服","军团等级","在此等级的军团数量","在此等级的军团占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhJtStat e : lvlList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getLvl();
				items[count++] =e.getLvlJtCnt();
				items[count++] =e.getLvlJtCntRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("军团等级分布");
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
	 * 军团捐赠统计
	 * @Title: selectJtDonateStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhJtStat>
	 * @author hwj
	 * @date 2018-5-21 下午01:53:24
	 */
	@RequestMapping(value="/selectJtDonateStatList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhJtStat> selectJtDonateStatList(@RequestBody IndependBaseSearchData searchData) throws Exception{
		List<YhJtStat> list=new ArrayList<YhJtStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectJtDonateStatList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list=yhJtStatService.selectJtDonateStatList(searchData);
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	
	@RequestMapping(value = "/exportJtDonateStatList", method = {
			RequestMethod.POST})
	public void exportJtDonateStatList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportJtDonateStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "军团捐赠统计.xlsx";
		List<YhJtStat> donateStatList =yhJtStatService.selectJtDonateStatList(searchData);
		//导出list
		if(donateStatList != null){
			String[] title = new String[] {"日期","区服","当天捐赠过的玩家","捐赠玩家占比","平均捐献军团资金(每人)","金币捐献次数","金币捐献占比","氪金捐献次数","氪金捐献占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhJtStat e : donateStatList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getJtjxActCnt();
				items[count++] =e.getJtjxActCntRate()+"%";
				items[count++] =e.getAvgJtjzMoney();
				items[count++] =e.getJbjxcnt();
				items[count++] =e.getJbjxCntRate()+"%";
				items[count++] =e.getKjjxcnt();
				items[count++] =e.getKjjxCntRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("军团捐赠统计");
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
	 * 强敌入侵关卡统计
	 * @Title: selectJtQdrqStageStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhJtStat>
	 * @author hwj
	 * @date 2018-5-21 下午01:56:55
	 */
	@RequestMapping(value="/selectJtQdrqStageStatList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhJtStat> selectJtQdrqStageStatList(@RequestBody IndependBaseSearchData searchData) throws Exception{
		List<YhJtStat> list=new ArrayList<YhJtStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectJtQdrqStageStatList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list=yhJtStatService.selectJtQdrqStageStatList(searchData);
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	/**
	 * 导出强敌入侵关卡统计
	 * @Title: exportJtQdrqStageStatList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-21 下午03:08:36
	 */
	@RequestMapping(value = "/exportJtQdrqStageStatList", method = {
			RequestMethod.POST})
	public void exportJtQdrqStageStatList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportJtQdrqStageStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "强敌入侵关卡统计.xlsx";
		List<YhJtStat> qdrqstageList =yhJtStatService.selectJtQdrqStageStatList(searchData);
		//导出list
		if(qdrqstageList != null){
			String[] title = new String[] {"日期","区服","强敌入侵关卡id","开启此关卡的军团数","挑战成功军团数","挑战成功占比","达成限时任务军团数","达成限时任务占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhJtStat e : qdrqstageList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getStageid();
				items[count++] =e.getOpenStageJtCnt();
				items[count++] =e.getSuccessJtCnt();
				items[count++] =e.getSuccessJtCntRate()+"%";
				items[count++] =e.getWcxsrwJtCnt();
				items[count++] =e.getWcxsrwJtCntRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("强敌入侵关卡统计");
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
	 * 军团采购统计
	 * @Title: selectJtPurchaseList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhJtStat>
	 * @author hwj
	 * @date 2018-5-21 下午01:57:42
	 */
	@RequestMapping(value="/selectJtPurchaseList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhJtStat> selectJtPurchaseList(@RequestBody IndependBaseSearchData searchData) throws Exception{
		List<YhJtStat> list=new ArrayList<YhJtStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectJtPurchaseList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list=yhJtStatService.selectJtPurchaseList(searchData);
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	/**
	 * 导出军团采购统计
	 * @Title: exportJtPurchaseList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-21 下午03:12:42
	 */
	@RequestMapping(value = "/exportJtPurchaseList", method = {
			RequestMethod.POST})
	public void exportJtPurchaseList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportJtPurchaseList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "军团采购统计.xlsx";
		List<YhJtStat> purchaseList =yhJtStatService.selectJtPurchaseList(searchData);
		//导出list
		if(purchaseList != null){
			String[] title = new String[] {"日期","区服","采购次数","今日采购了此次数的军团数量","今日采购了此次数的军团占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhJtStat e : purchaseList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getTimes();
				items[count++] =e.getJtCnt();
				items[count++] =e.getJtCntRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("军团采购统计");
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
	 * 军团采购商品统计
	 * @Title: selectJtPurchaseGoodList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhJtStat>
	 * @author hwj
	 * @date 2018-5-21 下午01:58:28
	 */
	@RequestMapping(value="/selectJtPurchaseGoodList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhJtStat> selectJtPurchaseGoodList(@RequestBody IndependBaseSearchData searchData) throws Exception{
		List<YhJtStat> list=new ArrayList<YhJtStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectJtPurchaseGoodList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list=yhJtStatService.selectJtPurchaseGoodList(searchData);
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	/**
	 * 导出军团采购商品统计
	 * @Title: exportJtPurchaseGoodList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-21 下午03:16:29
	 */
	@RequestMapping(value = "/exportJtPurchaseGoodList", method = {
			RequestMethod.POST})
	public void exportJtPurchaseGoodList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportJtPurchaseGoodList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "军团采购商品统计.xlsx";
		List<YhJtStat> purchaseGoodList =yhJtStatService.selectJtPurchaseGoodList(searchData);
		//导出list
		if(purchaseGoodList != null){
			String[] title = new String[] {"日期","区服","商品id","今日购买总次数","购买占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhJtStat e : purchaseGoodList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getGoodsid();
				items[count++] =e.getBugCnt();
				items[count++] =e.getBugCntRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("军团采购商品统计");
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
	 * 军团发布请求数据统计
	 * @Title: selectJtfbqqDataList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhJtStat>
	 * @author hwj
	 * @date 2018-5-21 下午02:00:30
	 */
	@RequestMapping(value="/selectJtfbqqDataList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhJtStat> selectJtfbqqDataList(@RequestBody IndependBaseSearchData searchData) throws Exception{
		List<YhJtStat> list=new ArrayList<YhJtStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectJtfbqqDataList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list=yhJtStatService.selectJtfbqqDataList(searchData);
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	/**
	 * 导出军团发布请求数据统计
	 * @Title: exportJtfbqqDataList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-21 下午03:20:40
	 */
	@RequestMapping(value = "/exportJtfbqqDataList", method = {
			RequestMethod.POST})
	public void exportJtfbqqDataList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportJtfbqqDataList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "军团请求数据统计.xlsx";
		List<YhJtStat> fbqqDataList =yhJtStatService.selectJtfbqqDataList(searchData);
		//导出list
		if(fbqqDataList != null){
			String[] title = new String[] {"日期","区服","发布请求人数","发布请求人数占比","发布战舰请求次数","发布战舰请求占比","发布装置请求次数","发布装置请求占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhJtStat e : fbqqDataList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getFbqqActCnt();
				items[count++] =e.getFbqqActCntRate()+"%";
				items[count++] =e.getFbzjqqCnt();
				items[count++] =e.getFbzjqqCntRate()+"%";
				items[count++] =e.getFbzzqqCnt();
				items[count++] =e.getFbzzqqCntRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("军团请求数据统计");
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
	 * 军团赠送数据统计
	 * @Title: selectJtzsDataList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhJtStat>
	 * @author hwj
	 * @date 2018-5-21 下午02:01:08
	 */
	@RequestMapping(value="/selectJtzsDataList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhJtStat> selectJtzsDataList(@RequestBody IndependBaseSearchData searchData) throws Exception{
		List<YhJtStat> list=new ArrayList<YhJtStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectJtzsDataList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list=yhJtStatService.selectJtzsDataList(searchData);
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	/**
	 * 导出军团赠送数据统计
	 * @Title: exportJtzsDataList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-21 下午03:25:49
	 */
	@RequestMapping(value = "/exportJtzsDataList", method = {
			RequestMethod.POST})
	public void exportJtzsDataList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportJtzsDataList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "军团赠送数据统计.xlsx";
		List<YhJtStat> zsDataList =yhJtStatService.selectJtzsDataList(searchData);
		//导出list
		if(zsDataList != null){
			String[] title = new String[] {"日期","区服","赠送人数","赠送人数占比","赠送战舰次数","赠送战舰占比","赠送装置次数","赠送装置占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhJtStat e : zsDataList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getZsActCnt();
				items[count++] =e.getZsActCntRate()+"%";
				items[count++] =e.getZszjCnt();
				items[count++] =e.getZszjCntRate()+"%";
				items[count++] =e.getZszzCnt();
				items[count++] =e.getZszzCntRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("军团赠送数据统计");
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
	 * 军团统计数据
	 * @Title: selectJtTotalDataList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhJtStat>
	 * @author hwj
	 * @date 2018-5-21 下午02:01:51
	 */
	@RequestMapping(value="/selectJtTotalDataList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhJtStat> selectJtTotalDataList(@RequestBody IndependBaseSearchData searchData) throws Exception{
		List<YhJtStat> list=new ArrayList<YhJtStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectJtTotalDataList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list=yhJtStatService.selectJtTotalDataList(searchData);
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	/**
	 * 导出军团总计数据统计
	 * @Title: exportJtTotalDataList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-21 下午03:31:08
	 */
	@RequestMapping(value = "/exportJtTotalDataList", method = {
			RequestMethod.POST})
	public void exportJtTotalDataList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportJtTotalDataList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "军团总计数据统计.xlsx";
		List<YhJtStat> totalDataList =yhJtStatService.selectJtTotalDataList(searchData);
		//导出list
		if(totalDataList != null){
			String[] title = new String[] {"日期","区服","请求总数量","已完成的请求数量","请求完成占比","已感谢的请求数量","可感谢的请求数量","已感谢占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhJtStat e : totalDataList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getQqCnt();
				items[count++] =e.getYwcqqCnt();
				items[count++] =e.getYwcqqCntRate()+"%";
				items[count++] =e.getYgxqqCnt();
				items[count++] =e.getKgxqqCnt();
				items[count++] =e.getYgxqqCntRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("军团总计数据统计");
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
