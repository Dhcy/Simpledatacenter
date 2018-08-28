package com.good.plat.datacenter.independ.controller.fwindex;

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

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.independ.entity.fwindex.FWJTBoss;
import com.good.plat.datacenter.independ.entity.fwindex.FWMechaStorm;
import com.good.plat.datacenter.independ.entity.fwindex.FWPrologue;
import com.good.plat.datacenter.independ.entity.fwindex.FWStage;
import com.good.plat.datacenter.independ.entity.fwindex.FWStageWeapon;
import com.good.plat.datacenter.independ.service.fwindex.FWJTBossService;
import com.good.plat.datacenter.independ.service.fwindex.FWMechaStormService;
import com.good.plat.datacenter.independ.service.fwindex.FWPrologueService;
import com.good.plat.datacenter.independ.service.fwindex.FWStageService;
import com.good.plat.datacenter.independ.service.fwindex.FWStageWeaponService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * 未来战争
 * @ClassName: FWSummaryController
 * @Description: TODO
 * @author hwj
 * @date 2017-4-17 下午04:43:33
 */
@Controller
@RequestMapping(value="/fwSummary")
public class FWSummaryController {
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private FWStageService fWStageServiceImpl;
	@Autowired
	private FWPrologueService fWPrologueServiceImpl;
	@Autowired
	private FWMechaStormService fWMechaStormServiceImpl;
	@Autowired
	private FWStageWeaponService fWStageWeaponServiceImpl;
	@Autowired
	private FWJTBossService fWJTBossServiceImpl;
	/**
	 * 关卡统计
	 * @Title: SelectStageList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-4-17 下午04:43:16
	 */
	@RequestMapping(value="/selectStageList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> SelectStageList(@RequestBody BaseSearchData searchData)throws Exception{
			Map<String,Object> stageMap=new HashMap<String, Object>();
			List<FWStage> list=null;
			//log
			Date opDate = new Date();
			String methodName = "selectStageList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =fWStageServiceImpl.selectStageList(searchData);
			stageMap.put("stageList", list);
			return stageMap;
	}
	/**
	 * 导出关卡统计
	 * @Title: exportStageList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-4-18 下午05:12:31
	 */
	@RequestMapping(value = "/exportStageList", method = {
			RequestMethod.POST})
	public void exportStageList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportStageList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "关卡统计.xlsx";
		List<FWStage> List=fWStageServiceImpl.selectStageList(searchData);
		//
		if(List != null){
			String[] title = new String[] {"日期","关卡id","关卡名称","适合玩家","参与玩家","完成人数","完成率(%)","参与次数","完成次数"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(FWStage e : List){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getStageId();
				items[count++] =e.getStageName();
				items[count++] =e.getEligbCnt();
				items[count++] =e.getEnterCnt();
				items[count++] =e.getSuccdCnt();
				items[count++] =e.getSuccdRate();
				items[count++] =e.getEnterTime();
				items[count++] =e.getSuccdTime();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("关卡统计");
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
	 * 序章列表
	 * @Title: SelectStageList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-4-18 下午06:00:36
	 */
	@RequestMapping(value="/selectPrologueList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectPrologueList(@RequestBody BaseSearchData searchData)throws Exception{
			Map<String,Object> map=new HashMap<String, Object>();
			List<FWPrologue> list=null;
			//log
			Date opDate = new Date();
			String methodName = "selectPrologueList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =fWPrologueServiceImpl.selectPrologueList(searchData);
			map.put("prologueList", list);
			return map;
	}
	
	/**
	 * 查询关卡武器列表
	 * @Title: selectStageWeaponList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-6-12 上午11:07:05
	 */
	@RequestMapping(value="/selectStageWeaponList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<FWStageWeapon> selectStageWeaponList(@RequestBody BaseSearchData searchData){
			List<FWStageWeapon> list=null;
			try {
				//log
				Date opDate = new Date();
				String methodName = "selectStageWeaponList";
				String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
				SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
				sysAccessLogService.saveAccessLog(accessLog);
				list =fWStageWeaponServiceImpl.selectStageWeaponList(searchData);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return list;
	}
	
	/**
	 * 导出关卡武器
	 * @Title: exportStageWeaponList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-6-12 上午11:13:42
	 */
	@RequestMapping(value = "/exportStageWeaponList", method = {
			RequestMethod.POST})
	public void exportStageWeaponList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportStageWeaponList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "关卡武器.xlsx";
		List<FWStageWeapon> stageWeaponlist =fWStageWeaponServiceImpl.selectStageWeaponList(searchData);
		//
		if(stageWeaponlist != null){
			String[] title = new String[] {"日期","渠道","武器名称","关卡id","关卡名称","数量"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(FWStageWeapon e : stageWeaponlist){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getWpName();
				items[count++] =e.getStageId();
				items[count++] =e.getStageName();
				items[count++] =e.getAmount();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("关卡武器");
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
	 * 查询机甲风暴完成人数
	 * @Title: selectMechaStormList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<FWMechaStorm>
	 * @author hwj
	 * @date 2017-6-12 下午02:54:33
	 */
	@RequestMapping(value="/selectMechaStormList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<FWMechaStorm> selectMechaStormList(@RequestBody BaseSearchData searchData){
			List<FWMechaStorm> list=null;
			try {
				//log
				Date opDate = new Date();
				String methodName = "selectMechaStormList";
				String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
				SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
				sysAccessLogService.saveAccessLog(accessLog);
				list =fWMechaStormServiceImpl.selectMechaStormList(searchData);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return list;
	}
	/**
	 * 导出机甲风暴关卡完成人数
	 * @Title: exportMechaStormList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-6-12 下午03:03:00
	 */
	@RequestMapping(value = "/exportStageFinishCntList", method = {
			RequestMethod.POST})
	public void exportStageFinishCntList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportStageFinishCntList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "机甲风暴关卡完成人数.xlsx";
		List<FWMechaStorm> finishCntList =fWMechaStormServiceImpl.selectMechaStormList(searchData);
		//
		if(finishCntList != null){
			String[] title = new String[] {"日期","区服","关卡名称","完成人数","占比"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(FWMechaStorm e : finishCntList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaName();
				items[count++] =e.getStageName();
				items[count++] =e.getFinishCnt();
				items[count++] =e.getRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("机甲风暴关卡完成人数");
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
	 * 关卡统计(末世突袭，末日突袭taptap等)
	 * @Title: selectFtwStageList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-6-16 上午10:45:41
	 */
	@RequestMapping(value="/selectFtwStageList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectFtwStageList(@RequestBody BaseSearchData searchData){
			Map<String,Object> stageMap=new HashMap<String, Object>();
			List<FWStage> list=null;
			//log
			Date opDate = new Date();
			String methodName = "selectFtwStageList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			try {
				list =fWStageServiceImpl.selectFtwStageList(searchData);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			stageMap.put("stageList", list);
			return stageMap;
	}
	
	/**
	 * 导出关卡统计(末世突袭，末日突袭taptap等)
	 * @Title: exportFtwStageList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-6-16 上午11:34:13
	 */
	@RequestMapping(value = "/exportFtwStageList", method = {
			RequestMethod.POST})
	public void exportFtwStageList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportFtwStageList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "关卡完成情况.xlsx";
		List<FWStage> ftwStageList =fWStageServiceImpl.selectFtwStageList(searchData);
		//
		if(ftwStageList != null){
			String[] title = new String[] {"日期","关卡id","关卡名称","适合玩家","参与玩家","完成人数","完成率","参与次数","完成次数"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(FWStage e : ftwStageList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getStageId();
				items[count++] =e.getStageName();
				items[count++] =e.getEligbCnt();
				items[count++] =e.getEnterCnt();
				items[count++] =e.getSuccdCnt();
				items[count++] =e.getSuccdRate()+"%";
				items[count++] =e.getEnterTime();
				items[count++] =e.getSuccdTime();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("关卡完成情况");
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
	 * 击杀boss的军团信息
	 * @Title: selectKillBossJTInfoList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<FWJTBoss>
	 * @author hwj
	 * @date 2017-7-28 上午10:41:01
	 */
	@RequestMapping(value="/selectKillBossJTInfoList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<FWJTBoss> selectKillBossJTInfoList(@RequestBody IndependBaseSearchData searchData){
			//log
			Date opDate = new Date();
			String methodName = "selectKillBossJTInfoList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			List<FWJTBoss> list=null;
			try {
				list =fWJTBossServiceImpl.selectKillBossJTInfoList(searchData);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return list;
	}
	/**
	 * 军团击杀boss详情
	 * @Title: selectKillBossDetailList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<FWJTBoss>
	 * @author hwj
	 * @date 2017-7-28 上午10:51:11
	 */
	@RequestMapping(value="/selectKillBossDetailList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<FWJTBoss> selectKillBossDetailList(@RequestBody IndependBaseSearchData searchData){
			//log
			Date opDate = new Date();
			String methodName = "selectKillBossDetailList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			List<FWJTBoss> list=null;
			try {
				list =fWJTBossServiceImpl.selectKillBossDetailList(searchData);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return list;
	}
	/**
	 * 每天军团击杀情况
	 * @Title: selectPerDayJtKillInfoList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<FWJTBoss>
	 * @author hwj
	 * @date 2017-7-28 上午10:55:06
	 */
	@RequestMapping(value="/selectPerDayJtKillInfoList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<FWJTBoss> selectPerDayJtKillInfoList(@RequestBody IndependBaseSearchData searchData){
			//log
			Date opDate = new Date();
			String methodName = "selectPerDayJtKillInfoList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			List<FWJTBoss> list=null;
			try {
				list =fWJTBossServiceImpl.selectPerDayJtKillInfoList(searchData);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return list;
	}
	/**
	 * 导出击杀boss军团信息
	 * @Title: exportKillBossJTInfoList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-7-28 下午03:03:12
	 */
	@RequestMapping(value = "/exportKillBossJTInfoList", method = {
			RequestMethod.POST})
	public void exportKillBossJTInfoList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportKillBossJTInfoList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "军团击杀boss情况.xlsx";
		List<FWJTBoss> ftwKillBossJtList =fWJTBossServiceImpl.selectKillBossJTInfoList(searchData);
		//
		if(ftwKillBossJtList != null){
			String[] title = new String[] {"日期","军团名","参加总人数","刷新时间","BOSS名","BOSS等级","击杀时间","最后一击玩家名"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(FWJTBoss e : ftwKillBossJtList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getJtName();
				items[count++] =e.getTotalJoinNum();
				items[count++] =e.getRefreshTime();
				items[count++] =e.getBossName();
				items[count++] =e.getBossLevel();
				items[count++] =e.getJtKillTime();
				items[count++] =e.getLsName();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("军团击杀boss情况");
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
	 * 导出军团boss每天击杀情况
	 * @Title: exportPerDayJtKillInfoList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-7-28 下午03:17:52
	 */
	@RequestMapping(value = "/exportPerDayJtKillInfoList", method = {
			RequestMethod.POST})
	public void exportPerDayJtKillInfoList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportPerDayJtKillInfoList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "军团boss每天参与情况.xlsx";
		List<FWJTBoss> ftwPerDayJtList =fWJTBossServiceImpl.selectPerDayJtKillInfoList(searchData);
		//
		if(ftwPerDayJtList != null){
			String[] title = new String[] {"日期","刷新次数","击杀次数","未击杀次数","当天最快击杀时间","当天平均击杀时间"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(FWJTBoss e : ftwPerDayJtList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getRefreshNum();
				items[count++] =e.getKillNum();
				items[count++] =e.getNoKillNum();
				items[count++] =e.getCurrentFKillTime();
				items[count++] =e.getCurrentAvgKillTime();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("军团boss每天参与情况");
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
