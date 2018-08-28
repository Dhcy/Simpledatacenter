package com.good.plat.datacenter.independ.controller.sj3index;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.good.plat.datacenter.independ.entity.sj3index.Achieve;
import com.good.plat.datacenter.independ.entity.sj3index.ActiveStat;
import com.good.plat.datacenter.independ.entity.sj3index.Altar;
import com.good.plat.datacenter.independ.entity.sj3index.AwardSummary;
import com.good.plat.datacenter.independ.entity.sj3index.CityStat;
import com.good.plat.datacenter.independ.entity.sj3index.DailySummary;
import com.good.plat.datacenter.independ.entity.sj3index.Diamond;
import com.good.plat.datacenter.independ.entity.sj3index.FamSummary;
import com.good.plat.datacenter.independ.entity.sj3index.Gold;
import com.good.plat.datacenter.independ.entity.sj3index.GoodsMaterials;
import com.good.plat.datacenter.independ.entity.sj3index.IllusStat;
import com.good.plat.datacenter.independ.entity.sj3index.MwSummary;
import com.good.plat.datacenter.independ.entity.sj3index.Officer;
import com.good.plat.datacenter.independ.entity.sj3index.RechargeQuery;
import com.good.plat.datacenter.independ.entity.sj3index.ResourceSummary;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3HeroStat;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3ModelStat;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3MonthCardStat;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3NewActTaskStat;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3VowStat;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3Wzzb;
import com.good.plat.datacenter.independ.entity.sj3index.Stage;
import com.good.plat.datacenter.independ.entity.sj3index.StageNode;
import com.good.plat.datacenter.independ.entity.sj3index.SyMapSummary;
import com.good.plat.datacenter.independ.entity.sj3index.SySummary;
import com.good.plat.datacenter.independ.entity.sj3index.YuanLing;
import com.good.plat.datacenter.independ.entity.sj3index.ZcqyCombat;
import com.good.plat.datacenter.independ.service.sj3index.AchieveService;
import com.good.plat.datacenter.independ.service.sj3index.ActiveStatService;
import com.good.plat.datacenter.independ.service.sj3index.AltarService;
import com.good.plat.datacenter.independ.service.sj3index.AwardService;
import com.good.plat.datacenter.independ.service.sj3index.CityStatService;
import com.good.plat.datacenter.independ.service.sj3index.DailySummaryService;
import com.good.plat.datacenter.independ.service.sj3index.DiamondService;
import com.good.plat.datacenter.independ.service.sj3index.FamService;
import com.good.plat.datacenter.independ.service.sj3index.GoldService;
import com.good.plat.datacenter.independ.service.sj3index.GoodsMaterialsService;
import com.good.plat.datacenter.independ.service.sj3index.IllusStatService;
import com.good.plat.datacenter.independ.service.sj3index.MwSummaryService;
import com.good.plat.datacenter.independ.service.sj3index.OfficerService;
import com.good.plat.datacenter.independ.service.sj3index.RechargeQueryService;
import com.good.plat.datacenter.independ.service.sj3index.ResourceService;
import com.good.plat.datacenter.independ.service.sj3index.Sj3HeroStatService;
import com.good.plat.datacenter.independ.service.sj3index.Sj3ModelStatService;
import com.good.plat.datacenter.independ.service.sj3index.Sj3MonthCardStatService;
import com.good.plat.datacenter.independ.service.sj3index.Sj3NewActTaskStatService;
import com.good.plat.datacenter.independ.service.sj3index.Sj3VowStatService;
import com.good.plat.datacenter.independ.service.sj3index.Sj3WzzbService;
import com.good.plat.datacenter.independ.service.sj3index.StageNodeService;
import com.good.plat.datacenter.independ.service.sj3index.StageService;
import com.good.plat.datacenter.independ.service.sj3index.SyMapSummaryService;
import com.good.plat.datacenter.independ.service.sj3index.SySummaryService;
import com.good.plat.datacenter.independ.service.sj3index.YuanLingService;
import com.good.plat.datacenter.independ.service.sj3index.ZcqyCombatService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * 总览控制器
 * @ClassName: AchieveController
 * @Description: TODO
 * @author hwj
 * @date 2017-2-23 上午10:29:01
 */
@Controller
@RequestMapping(value="/summary")
public class SummaryController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private AchieveService achieveService;
	@Autowired
	private GoldService goldService;
	@Autowired
	private StageService stageService;
	@Autowired
	private DiamondService diamondService;
	@Autowired
	private YuanLingService yuanLingService;
	@Autowired
	private OfficerService officerService;
	@Autowired 
	private FamService famService;
	@Autowired
	private AwardService awardService;
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private DailySummaryService dailyService;
	@Autowired
	private CityStatService cityStatService;
	@Autowired
	private MwSummaryService mwSummaryService;
	@Autowired
	private SySummaryService sySummaryService;
	@Autowired
	private RechargeQueryService rechargeQueryService;
	@Autowired
	private StageNodeService stageNodeService;
	@Autowired
	private ActiveStatService activeStatService;
	@Autowired
	private SyMapSummaryService syMapSummaryService;
	@Autowired
	private ZcqyCombatService zcqyCombatService;
	@Autowired
	private AltarService altarService;
	@Autowired
	private GoodsMaterialsService goodsMaterialsService;
	@Autowired
	private Sj3WzzbService sj3WzzbService;
	@Autowired
	private IllusStatService illusStatService;
	@Autowired
	private Sj3HeroStatService sj3HeroStatService;
	@Autowired
	private Sj3VowStatService sj3VowStatService;
	@Autowired
	private Sj3ModelStatService sj3ModelStatService;
	@Autowired
	private Sj3NewActTaskStatService sj3NewActTaskStatService;
	@Autowired
	private Sj3MonthCardStatService sj3MonthCardStatService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	/**
	 * 成就完成人数
	 * @Title: selectAchieveFinishNumList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Achieve>
	 * @author hwj
	 * @date 2017-2-23 上午11:33:31
	 */
	@RequestMapping(value= "/selectAchieveFinishNumList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<Achieve> selectAchieveFinishNumList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		List<Achieve> list = new ArrayList<Achieve>();
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectAchieveFinishNumList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =achieveService.selectAchieveFinishNumList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		return list;
	}
	
	/**
	 * 导出成就完成人数
	 * @Title: exportDailyReportList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-2-23 下午12:30:36
	 */
	@RequestMapping(value = "/exportAchieveFinishNumList", method = {
			RequestMethod.POST})
	public void exportAchieveFinishNumList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportAchieveFinishNumList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "完成人数.xlsx";
		List<Achieve> List=selectAchieveFinishNumList(searchData,session);
		//
		if(List != null){
			String[] title = new String[] {"日期","渠道","成就(ID)","成就名称","职业","完成人数","完成率(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Achieve e : List){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getAchieveId();
				items[count++] =e.getAchieveName();
				items[count++] =e.getJobs();
				items[count++] =e.getUsercnt();
				items[count++] =e.getFinish_rate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("完成人数");
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
	 * 成就完成总数及人均完成成就数
	 * @Title: selectAchieveFinishTotalNumList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Achieve>
	 * @author hwj
	 * @date 2017-2-23 下午12:35:04
	 */
	@RequestMapping(value= "/selectAchieveFinishTotalNumList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<Achieve> selectAchieveFinishTotalNumList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		List<Achieve> list = new ArrayList<Achieve>();
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectAchieveFinishTotalNumList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =achieveService.selectAchieveFinishTotalNumList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		return list;
	}
	
	/**
	 * 导出成就完成总数
	 * @Title: exportAchieveFinishTotalNumList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-2-23 下午02:05:51
	 */
	@RequestMapping(value = "/exportAchieveFinishTotalNumList", method = {
			RequestMethod.POST})
	public void exportAchieveFinishTotalNumList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportAchieveFinishTotalNumList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "成就总数.xlsx";
		List<Achieve> List=selectAchieveFinishTotalNumList(searchData,session);
		//
		if(List != null){
			String[] title = new String[] {"日期","渠道","职业","完成成就总数","人均完成成就数"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Achieve e : List){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getJobs();
				items[count++] =e.getTotal_cnt();
				items[count++] =e.getAvg_achieve_cnt();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("成就总数");
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
	 * 等级金币
	 * @Title: selectLvlAndGoldNumList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Achieve>
	 * @author hwj
	 * @date 2017-2-23 下午02:28:44
	 */
	@RequestMapping(value= "/selectLvlAndGoldNumList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<Gold> selectLvlAndGoldNumList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		List<Gold> list = new ArrayList<Gold>();
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectLvlAndGoldNumList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =goldService.selectLvlAndGoldNumList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		return list;
	}
	
	/**
	 * 导出等级金币
	 * @Title: exportLvlAndGoldNumList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-2-23 下午02:31:29
	 */
	@RequestMapping(value = "/exportLvlAndGoldNumList", method = {
			RequestMethod.POST})
	public void exportLvlAndGoldNumList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportLvlAndGoldNumList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "等级金币.xlsx";
		List<Gold> List=selectLvlAndGoldNumList(searchData,session);
		//
		if(List != null){
			String[] title = new String[] {"日期","渠道","角色名","等级","库存金币"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Gold e : List){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getActorName();
				items[count++] =e.getLvl();
				items[count++] =e.getLeft_gold();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("等级金币");
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
	 * 关卡任务统计
	 * @Title: selectStageList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Stage>
	 * @author hwj
	 * @date 2017-9-28 下午05:14:09
	 */
	@RequestMapping(value= "/selectStageList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<Stage> selectStageList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		List<Stage> list = new ArrayList<Stage>();
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectStageList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =stageService.selectStageTaskList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		return list;
	}
	
	/**
	 * 导出通关统计
	 * @Title: exportStageList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-2-23 下午06:36:58
	 */
	@RequestMapping(value = "/exportStageList", method = {
			RequestMethod.POST})
	public void exportStageList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportStageList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "关卡统计.xlsx";
		List<Stage> List=selectStageList(searchData,session);
		//
		if(List != null){
			String[] title = new String[] {"日期","渠道","任务类型","任务ID","主线名称","职业","接取主线人数","主线完成数","主线未完成数","主线平均完成用时","接取主线账号数","主线完成账号数","主线未完成账号数"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Stage e : List){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getStageTypeStr();
				items[count++] =e.getStageId();
				items[count++] =e.getStageName();
				items[count++] =e.getJobs();
				items[count++] =e.getEntercnt();
				items[count++] =e.getSuccdcnt();
				items[count++] =e.getFailcnt();
				items[count++] =e.getAvg_succ_time();
				items[count++] =e.getEnter_uid_cnt();
				items[count++] =e.getSucc_uid_cnt();
				items[count++] =e.getFail_uid_cnt();
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
	 * 钻石统计
	 * @Title: selectDiamondList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Diamond>
	 * @author hwj
	 * @date 2017-2-28 下午03:55:21
	 */
	@RequestMapping(value= "/selectDiamondList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<Diamond> selectDiamondList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		List<Diamond> list = new ArrayList<Diamond>();
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectDiamondList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =diamondService.selectDiamondList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		return list;
	}
	
	/**
	 * 导出钻石统计
	 * @Title: exportDiamondList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-3-1 上午10:08:03
	 */
	@RequestMapping(value = "/exportDiamondList", method = {
			RequestMethod.POST})
	public void exportDiamondList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportDiamondList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "钻石统计.xlsx";
		List<Diamond> List=selectDiamondList(searchData,session);
		//
		if(List != null){
			String[] title = new String[] {"日期","渠道","钻石类型","获得总钻石","总消耗钻石"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Diamond e : List){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getCurrTypeText();
				items[count++] =e.getGetCnt();
				items[count++] =e.getComsumeCnt();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("钻石统计");
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
	 * 元灵统计
	 * @Title: selectYlStatList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-2-28 下午04:44:03
	 */
	@RequestMapping(value= "/selectYlStatList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectYlStatList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		Map<String,Object> map=new HashMap<String, Object>();
		List<YuanLing> ylNameList=null;
		List<YuanLing> ylSlcList=null;
		List<YuanLing> ylLevelList=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectYlStatList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//元灵名称及玩家数
			ylNameList=yuanLingService.selectYlNameAndUserCntList(searchData);
			//二十连抽次数
			ylSlcList=yuanLingService.selectYlslcCntList(searchData);
			//元灵等级及对应玩家数
			ylLevelList=yuanLingService.selectYlLevelAndUserCntList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		map.put("ylNameList", ylNameList);
		map.put("ylSlcList", ylSlcList);
		map.put("ylLevelList", ylLevelList);
		return map;
	}
	
	/**
	 * 导出元灵名称及玩家数
	 * @Title: exportYlNameAndCntList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-3-1 上午09:55:24
	 */
	@RequestMapping(value = "/exportYlNameAndCntList", method = {
			RequestMethod.POST})
	public void exportYlNameAndCntList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYlNameAndCntList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "元灵名称.xlsx";
		List<YuanLing> List=yuanLingService.selectYlNameAndUserCntList(searchData);
		//
		if(List != null){
			String[] title = new String[] {"日期","渠道","元灵列表","装备该元灵的玩家人次"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(YuanLing e : List){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getYlName();
				items[count++] =e.getYlUserCnt();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("元灵名称");
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
	 * 导出二十连抽
	 * @Title: exportYlShiLchouList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-3-1 上午09:58:19
	 */
	@RequestMapping(value = "/exportYlShiLchouList", method = {
			RequestMethod.POST})
	public void exportYlShiLchouList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYlShiLchouList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "二十连抽.xlsx";
		List<YuanLing> List=yuanLingService.selectYlslcCntList(searchData);
		//
		if(List != null){
			String[] title = new String[] {"日期","渠道","二十连抽次数"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(YuanLing e : List){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getSlcCnt();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("二十连抽");
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
	 * 导出元灵等级
	 * @Title: exportYlLevelAndCntList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-3-1 上午10:01:19
	 */
	@RequestMapping(value = "/exportYlLevelAndCntList", method = {
			RequestMethod.POST})
	public void exportYlLevelAndCntList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYlLevelAndCntList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "元灵等级.xlsx";
		List<YuanLing> List=yuanLingService.selectYlLevelAndUserCntList(searchData);
		//
		if(List != null){
			String[] title = new String[] {"日期","渠道","元灵等级","对应玩家数量"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(YuanLing e : List){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getYlLevel();
				items[count++] =e.getYlLevelCnt();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("元灵等级");
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
	 * 官职分布
	 * @Title: selectOfficerDistributionList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Officer>
	 * @author hwj
	 * @date 2017-2-28 下午05:41:12
	 */
	@RequestMapping(value= "/selectOfficerDistributionList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<Officer> selectOfficerDistributionList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		List<Officer> list = new ArrayList<Officer>();
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectOfficerDistributionList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =officerService.selectOfficerDistributionList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		return list;
	}
	
	/**
	 * 导出官职分布
	 * @Title: exportOfficerDistributionList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-3-1 上午09:45:21
	 */
	@RequestMapping(value = "/exportOfficerDistributionList", method = {
			RequestMethod.POST})
	public void exportOfficerDistributionList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportOfficerDistributionList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "官职分布.xlsx";
		List<Officer> List=selectOfficerDistributionList(searchData,session);
		//
		if(List != null){
			String[] title = new String[] {"日期","渠道","官职列表","职业","黎明圣火人数","人数占比(%)","幽冥之翼人数","人数占比(%)","永夜明光人数","人数占比(%)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(Officer e : List){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getOfficerName();
				items[count++] =e.getJobs();
				items[count++] =e.getLmshCnt();
				items[count++] =e.getLmshRate();
				items[count++] =e.getYmzyCnt();
				items[count++] =e.getYmzyRate();
				items[count++] =e.getYymgCnt();
				items[count++] =e.getYymgRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("官职分布");
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
	 * 秘境统计
	 * @Title: selectFamSummaryList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<FamSummary>
	 * @author hwj
	 * @date 2017-3-3 下午05:49:24
	 */
	@RequestMapping(value= "/selectFamSummaryList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<FamSummary> selectFamSummaryList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		List<FamSummary> list = new ArrayList<FamSummary>();
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectFamSummaryList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =famService.selectFamSummaryList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		return list;
	}
	/**
	 * 导出秘境统计
	 * @Title: exportFamSummaryList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-11-24 下午01:09:53
	 */
	@RequestMapping(value = "/exportFamSummaryList", method = {
			RequestMethod.POST})
	public void exportFamSummaryList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportFamSummaryList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "秘境统计.xlsx";
		List<FamSummary> famList =famService.selectFamSummaryList(searchData);
		//
		if(famList != null){
			String[] title = new String[] {"日期","渠道","秘境Id","秘境名称","进入秘境次数","进入秘境人数","人均进入秘境次数","通关秘境数","平均通关用时(s)","失败人次","中途退出人次"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(FamSummary e : famList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getMjId();
				items[count++] =e.getMjName();
				items[count++] =e.getEnterTimes();
				items[count++] =e.getEnterCnt();
				items[count++] =e.getAvgEnterTimes();
				items[count++] =e.getSuccdCnt();
				items[count++] =e.getAvgTime();
				items[count++] =e.getFailCnt();
				items[count++] =e.getEnterCnt();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("秘境统计");
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
	 * 悬赏统计
	 * @Title: selectAwardSummaryList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<AwardSummary>
	 * @author hwj
	 * @date 2017-3-3 下午06:29:04
	 */
	@RequestMapping(value= "/selectAwardSummaryList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<AwardSummary> selectAwardSummaryList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		List<AwardSummary> list = new ArrayList<AwardSummary>();
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectAwardSummaryList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =awardService.selectAwardSummaryList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value = "/exportAwardSummaryList", method = {
			RequestMethod.POST})
	public void exportAwardSummaryList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportAwardSummaryList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "悬赏统计.xlsx";
		List<AwardSummary> awardList =awardService.selectAwardSummaryList(searchData);
		//
		if(awardList != null){
			String[] title = new String[] {"日期","渠道","任务类型","任务领取数","任务完成数","完成率"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(AwardSummary e : awardList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getAwardName();
				items[count++] =e.getGetCnt();
				items[count++] =e.getSuccCnt();
				items[count++] =e.getRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("悬赏统计");
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
	 * 资源统计
	 * @Title: selectResourceSummaryList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-3-6 上午11:04:33
	 */
	@RequestMapping(value= "/selectResourceSummaryList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectResourceSummaryList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		Map<String,Object> resourceMap=new HashMap<String, Object>();
		List<ResourceSummary> resourceList=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectResourceSummaryList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			resourceList =resourceService.selectResourceSummaryList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		resourceMap.put("resourceList", resourceList);
		return resourceMap;
	}
	/**
	 * 导出资源统计
	 * @Title: exportResourceSummaryList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-11-24 下午12:56:19
	 */
	@RequestMapping(value = "/exportResourceSummaryList", method = {
			RequestMethod.POST})
	public void exportResourceSummaryList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportResourceSummaryList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "资源统计.xlsx";
		List<ResourceSummary> resourceList =resourceService.selectResourceSummaryList(searchData);
		//
		if(resourceList != null){
			String[] title = new String[] {"日期","渠道","类型","获取方式","次数","对应人数"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(ResourceSummary e : resourceList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getTypeName();
				items[count++] =e.getPurposeName();
				items[count++] =e.getTime();
				items[count++] =e.getCnt();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("资源统计");
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
	 * 活跃度总览
	 * @Title: selectActiveList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-3-6 下午02:09:58
	 */
	@RequestMapping(value= "/selectActiveList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectActiveList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		Map<String,Object> map=new HashMap<String, Object>();
		List<DailySummary> activeList=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectActiveList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			activeList =dailyService.selectActiveList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		map.put("activeList", activeList);
		return map;
	}
	/**
	 * 导出活跃统计
	 * @Title: exportActiveList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-11-24 下午01:49:12
	 */
	@RequestMapping(value = "/exportActiveList", method = {
			RequestMethod.POST})
	public void exportActiveList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportActiveList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "活跃度.xlsx";
		List<DailySummary> activeList =dailyService.selectActiveList(searchData);
		//
		if(activeList != null){
			String[] title = new String[] {"日期","渠道","活跃度分数","达到该分数的玩家数","人数占比"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(DailySummary e : activeList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getActiveScore();
				items[count++] =e.getCnt();
				items[count++] =e.getRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("活跃度统计");
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
	 * 城市争夺次数分布
	 * @Title: selectCityCntDistributeList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-3-6 下午04:23:54
	 */
	@RequestMapping(value= "/selectCityCntDistributeList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectCityCntDistributeList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		Map<String,Object> map=new HashMap<String, Object>();
		List<CityStat> cityList=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectCityCntDistributeList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			cityList =cityStatService.selectCityCntDistributeList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		map.put("cityList", cityList);
		return map;
	}
	/**
	 * 导出城市争夺次数分布
	 * @Title: exportCityCntDistributeList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-11-24 下午01:51:12
	 */
	@RequestMapping(value = "/exportCityCntDistributeList", method = {
			RequestMethod.POST})
	public void exportCityCntDistributeList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportCityCntDistributeList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "城市争夺次数分布.xlsx";
		List<CityStat> cityList =cityStatService.selectCityCntDistributeList(searchData);
		//
		if(cityList != null){
			String[] title = new String[] {"日期","区服","星盟","幽冥","赤焰"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(CityStat e : cityList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getYymgCnt();
				items[count++] =e.getYmzyCnt();
				items[count++] =e.getLmshCnt();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("城市争夺次数分布");
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
	 * 魔物激活分布
	 * @Title: selectMwActDistributeList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-3-6 下午05:10:40
	 */
	@RequestMapping(value= "/selectMwActDistributeList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectMwActDistributeList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		Map<String,Object> map=new HashMap<String, Object>();
		List<MwSummary> mwActList=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectMwActDistributeList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			mwActList =mwSummaryService.selectMwActList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		map.put("mwActList", mwActList);
		return map;
	}
	/**
	 * 导出魔物激活
	 * @Title: exportMwActDistributeList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-11-24 下午02:03:51
	 */
	@RequestMapping(value = "/exportMwActDistributeList", method = {
			RequestMethod.POST})
	public void exportMwActDistributeList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportMwActDistributeList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "魔物激活.xlsx";
		List<MwSummary> mwActList =mwSummaryService.selectMwActList(searchData);
		//
		if(mwActList != null){
			String[] title = new String[] {"日期","渠道","魔物列表","激活玩家数","激活玩家比例","解锁玩家数","解锁玩家比例"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(MwSummary e : mwActList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getMwName();
				items[count++] =e.getUserCnt();
				items[count++] =e.getRate()+"%";
				items[count++] =e.getUnUserCnt();
				items[count++] =e.getUnActiveRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("魔物激活");
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
	 * 圣域封魔团
	 * @Title: selectSyFengMoTuanList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-3-7 下午02:51:19
	 */
	@RequestMapping(value= "/selectSyFengMoTuanList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectSyFengMoTuanList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		Map<String,Object> map=new HashMap<String, Object>();
		List<SySummary> fengMoTuanList=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectSyFengMoTuanList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			fengMoTuanList =sySummaryService.selectSyFengMoTuanList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		map.put("fengMoTuanList", fengMoTuanList);
		return map;
	}
	/**
	 * 导出圣域封魔团
	 * @Title: exportSyFengMoTuanList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-11-24 下午01:14:56
	 */
	@RequestMapping(value = "/exportSyFengMoTuanList", method = {
			RequestMethod.POST})
	public void exportSyFengMoTuanList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportSyFengMoTuanList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "封魔团.xlsx";
		List<SySummary> fengMoTuanList =sySummaryService.selectSyFengMoTuanList(searchData);
		//
		if(fengMoTuanList != null){
			String[] title = new String[] {"日期","渠道","圣域名称","开启战斗数","通关战斗数","人均开启战斗数","平均通关用时(s)","参与玩家数","通关玩家数","失败玩家数","中途退出玩家数"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(SySummary e : fengMoTuanList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getSyName();
				items[count++] =e.getStartCnt();
				items[count++] =e.getSucctCnt();
				items[count++] =e.getAvgStartCnt();
				items[count++] =e.getAvgTime();
				items[count++] =e.getUserCnt();
				items[count++] =e.getUsuccCnt();
				items[count++] =e.getUfailCnt();
				items[count++] =e.getUexitCnt();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("封魔团");
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
	 * 圣域爵位战
	 * @Title: selectSyJueWeiList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-3-7 下午02:54:08
	 */
	@RequestMapping(value= "/selectSyJueWeiList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectSyJueWeiList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		Map<String,Object> map=new HashMap<String, Object>();
		List<SySummary> jueWeiList=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectSyJueWeiList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			jueWeiList =sySummaryService.selectSyJueWeiList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		map.put("jueWeiList", jueWeiList);
		return map;
	}
	/**
	 * 导出爵位战
	 * @Title: exportSyJueWeiList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-11-24 下午01:22:22
	 */
	@RequestMapping(value = "/exportSyJueWeiList", method = {
			RequestMethod.POST})
	public void exportSyJueWeiList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportSyJueWeiList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "爵位战.xlsx";
		List<SySummary> jueWeiList =sySummaryService.selectSyJueWeiList(searchData);
		//
		if(jueWeiList != null){
			String[] title = new String[] {"日期","渠道","圣域名称","开启战斗数","平均战斗用时(s)","参与玩家数","胜利人次","失败人次","平局"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(SySummary e : jueWeiList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getSyName();
				items[count++] =e.getStartCnt();
				items[count++] =e.getAvgTime();
				items[count++] =e.getUserCnt();
				items[count++] =e.getUsuccCnt();
				items[count++] =e.getUfailCnt();
				items[count++] =e.getUexitCnt();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("爵位战");
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
	 * 圣域猎魔记事
	 * @Title: selectSyLieMoList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-3-7 下午02:56:24
	 */
	@RequestMapping(value= "/selectSyLieMoList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectSyLieMoList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		Map<String,Object> map=new HashMap<String, Object>();
		List<SySummary> lieMoList=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectSyLieMoList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			lieMoList =sySummaryService.selectSyLieMoList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		map.put("lieMoList", lieMoList);
		return map;
	}
	/**
	 * 导出圣域猎魔记事
	 * @Title: exportSyLieMoList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-11-24 下午01:28:17
	 */
	@RequestMapping(value = "/exportSyLieMoList", method = {
			RequestMethod.POST})
	public void exportSyLieMoList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportSyLieMoList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "猎魔记事.xlsx";
		List<SySummary> lieMoList =sySummaryService.selectSyLieMoList(searchData);
		//
		if(lieMoList != null){
			String[] title = new String[] {"日期","渠道","圣域名称","开启次数","开启人数","人均开启次数","完成次数","完成平均用时(s)","掠夺次数","成功掠夺次数"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(SySummary e : lieMoList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getSyName();
				items[count++] =e.getStartCnt();
				items[count++] =e.getStartUserCnt();
				items[count++] =e.getAvgStartTimes();
				items[count++] =e.getSucctCnt();
				items[count++] =e.getAvgTime();
				items[count++] =e.getRobCnt();
				items[count++] =e.getSuccrobCnt();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("猎魔记事");
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
	 * 圣域魔神降临
	 * @Title: selectSyMoShenList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-3-7 下午02:58:27
	 */
	@RequestMapping(value= "/selectSyMoShenList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectSyMoShenList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		Map<String,Object> map=new HashMap<String, Object>();
		List<SySummary> moShenList=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectSyMoShenList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			moShenList =sySummaryService.selectSyMoShenList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		map.put("moShenList", moShenList);
		return map;
	}
	/**
	 * 导出魔神降临
	 * @Title: exportSyMoShenList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-11-24 下午01:33:11
	 */
	@RequestMapping(value = "/exportSyMoShenList", method = {
			RequestMethod.POST})
	public void exportSyMoShenList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportSyMoShenList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "魔神降临.xlsx";
		List<SySummary> moShenList =sySummaryService.selectSyMoShenList(searchData);
		//
		if(moShenList != null){
			String[] title = new String[] {"日期","区服","圣域名称","参与玩家数","击杀平均用时","付费复活总次数","BOSS击杀玩家人次"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(SySummary e : moShenList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getSyName();
				items[count++] =e.getUserCnt();
				items[count++] =e.getAvgTime();
				items[count++] =e.getReliveCnt();
				items[count++] =e.getKillCnt();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("猎魔记事");
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
	 * 砖石统计详情
	 * @Title: selectDiamondDetailList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-3-7 下午08:02:45
	 */
	@RequestMapping(value= "/selectDiamondDetailList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectDiamondDetailList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		Map<String,Object> map=new HashMap<String, Object>();
		List<Diamond> detailList=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectDiamondDetailList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			detailList =diamondService.selectDiamondDetailList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		map.put("detailList", detailList);
		return map;
	}
	
	/**
	 * 充值查询
	 * @Title: selectRechargeQueryList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-3-9 上午09:46:01
	 */
	@RequestMapping(value= "/selectRechargeQueryList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectRechargeQueryList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		Map<String,Object> map=new HashMap<String, Object>();
		List<RechargeQuery> rechargeList=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectRechargeQueryList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			rechargeList =rechargeQueryService.selectRechargeList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		map.put("rechargeList", rechargeList);
		return map;
	}
	/**
	 * 导出充值查询
	 * @Title: exportRechargeQueryList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-11-24 下午02:15:16
	 */
	@RequestMapping(value = "/exportRechargeQueryList", method = {
			RequestMethod.POST})
	public void exportRechargeQueryList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportRechargeQueryList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "充值查询.xlsx";
		List<RechargeQuery> rechargeList =rechargeQueryService.selectRechargeList(searchData);
		//
		if(rechargeList != null){
			String[] title = new String[] {"日期","平台","渠道","角色ID","角色名称","充值金额","充值钻石","充值时等级","充值时间","充值订单号"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(RechargeQuery e : rechargeList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getSourceSys();
				items[count++] =e.getChannelName();
				items[count++] =e.getActorId();
				items[count++] =e.getActorName();
				items[count++] =e.getMoney();
				items[count++] =e.getCount();
				items[count++] =e.getActorLevel();
				items[count++] =e.getRechargeDate();
				items[count++] =e.getOrderId();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("充值查询");
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
	 * 资源统计类型及方式
	 * @Title: selectResourceTypeAndWayList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-3-28 上午09:35:19
	 */
	@RequestMapping(value= "/selectResourceTypeAndWayList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectResourceTypeAndWayList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		Map<String,Object> map=new HashMap<String, Object>();
		List<ResourceSummary> typeList=null;
		List<ResourceSummary> wayList=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectResourceTypeAndWayList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			typeList =resourceService.selectResourceTypeList(searchData);
			wayList =resourceService.selectResourceWayList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		map.put("typeList", typeList);
		map.put("wayList", wayList);
		return map;
	}
	/**
	 * 城市争夺细分
	 * @Title: selectCityFightDetailList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @throws Exception 
	 * @date 2017-3-28 上午09:36:27
	 */
	@RequestMapping(value= "/selectCityFightDetailList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<CityStat> selectCityFightDetailList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception{
		//log
		Date opDate = new Date();
		String methodName = "selectCityFightDetailList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<CityStat> cityList=cityStatService.selectCityFightDetailList(searchData);
		return cityList;
	}
	
	/**
	 * 导出城市争夺细分
	 * @Title: exportSyMoShenList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-11-24 下午01:33:11
	 */
	@RequestMapping(value = "/exportCityFightDetailList", method = {
			RequestMethod.POST})
	public void exportCityFightDetailList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportCityFightDetailList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "城市争夺细分.xlsx";
		List<CityStat> cityList=cityStatService.selectCityFightDetailList(searchData);
		//
		if(cityList != null){
			String[] title = new String[] {"日期","区服","星盟","幽冥","赤焰"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(CityStat e : cityList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getYymgCnt();
				items[count++] =e.getYmzyCnt();
				items[count++] =e.getLmshCnt();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("城市争夺细分");
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
	 * 城市争夺战局数情况
	 * @Title: selectCityFightDetail2List
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<CityStat>
	 * @author hwj
	 * @date 2017-4-21 下午04:52:46
	 */
	@RequestMapping(value= "/selectCityFightSetList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<CityStat> selectCityFightDetail2List(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception{
		//log
		Date opDate = new Date();
		String methodName = "selectCityFightSetList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<CityStat> citySetList=cityStatService.selectCityFightSetList(searchData);
		return citySetList;
	}
	/**
	 * 导出城市争夺站局数情况
	 * @Title: exportCityFightSetList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-11-24 下午01:59:11
	 */
	@RequestMapping(value = "/exportCityFightSetList", method = {
			RequestMethod.POST})
	public void exportCityFightSetList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportCityFightSetList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "城市争夺站局数情况.xlsx";
		List<CityStat> citySetList=cityStatService.selectCityFightSetList(searchData);
		//
		if(citySetList != null){
			String[] title = new String[] {"日期","战斗开启数","参与玩家数","付费复活数","每局均时长（秒）"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(CityStat e : citySetList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getFightCnt();
				items[count++] =e.getActorCnt();
				items[count++] =e.getReliveCnt();
				items[count++] =e.getFightTime();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("城市争夺站局数情况");
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
	 * 元灵单次抽取
	 * @Title: selectYlSimExtractCntList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<YuanLing>
	 * @author hwj
	 * @date 2017-5-15 下午04:48:50
	 */
	@RequestMapping(value= "/selectYlSimExtractCntList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YuanLing> selectYlSimExtractCntList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		List<YuanLing> ylSimExtractCntList=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectYlSimExtractCntList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			ylSimExtractCntList=yuanLingService.selectYlSimExtractCntList(searchData);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ylSimExtractCntList;
	}
	
	/**
	 * 导出元灵单次抽取
	 * @Title: exportYlSimExtractCntList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-5-15 下午04:50:09
	 */
	@RequestMapping(value = "/exportYlSimExtractCntList", method = {
			RequestMethod.POST})
	public void exportYlSimExtractCntList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYlSimExtractCntList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "元灵单次抽取.xlsx";
		List<YuanLing> simExtractlist=selectYlSimExtractCntList(searchData,session);
		//
		if(simExtractlist != null){
			String[] title = new String[] {"日期","渠道","杯子1","杯子2","杯子3","杯子4","杯子5"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(YuanLing e : simExtractlist){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getCup1Cnt();
				items[count++] =e.getCup2Cnt();
				items[count++] =e.getCup3Cnt();
				items[count++] =e.getCup4Cnt();
				items[count++] =e.getCup5Cnt();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("元灵单次抽取");
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
	 * 元灵详情
	 * @Title: selectYlDetailList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<YuanLing>
	 * @author hwj
	 * @date 2017-5-16 上午10:28:14
	 */
	@RequestMapping(value="/selectYlDetailList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<YuanLing> selectYlDetailList(@RequestBody BaseSearchData searchData,
			HttpSession session)throws Exception{
		//log
		Date opDate = new Date();
		String methodName = "selectYlDetailList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<YuanLing> list=yuanLingService.selectYlDetailList(searchData);
		return list;
	}
	
	/**
	 * 时间段游戏关卡
	 * @Title: selectTimeGameStageList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<YuanLing>
	 * @author hwj
	 * @date 2017-6-16 下午02:57:05
	 */
	@RequestMapping(value="/selectTimeGameStageList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<StageNode> selectTimeGameStageList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectTimeGameStageList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<StageNode> stageList=null;
		try {
			stageList=stageNodeService.selectTimeGameStageList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return stageList;
	}
	
	/**
	 * 关卡节点列表
	 * @Title: selectStageNodeSuccessList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<StageNode>
	 * @author hwj
	 * @date 2017-6-16 下午03:01:38
	 */
	@RequestMapping(value="/selectStageNodeSuccessList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<StageNode> selectStageNodeSuccessList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectStageNodeSuccessList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<StageNode> nodeList=null;
		try {
			nodeList=stageNodeService.selectStageNodeSuccessList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nodeList;
	}
	
	/**
	 * 导出关卡节点
	 * @Title: exportStageNodeSuccessList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-6-16 下午03:49:10
	 */
	@RequestMapping(value = "/exportStageNodeSuccessList", method = {
			RequestMethod.POST})
	public void exportStageNodeSuccessList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportStageNodeSuccessList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "关卡节点.xlsx";
		List<StageNode> nodeList=null;
		try {
			nodeList=stageNodeService.selectStageNodeSuccessList(searchData);
			if(nodeList != null){
				String[] title = new String[] {"关卡名称","关卡节点","通关人数"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(StageNode e : nodeList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStageName();
					items[count++] =e.getNodeName();
					items[count++] =e.getAmount();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("关卡节点");
			}
			
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
}
	/**
	 * 导出钻石统计细分
	 * @Title: exportDiamondDetailList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author hwj
	 * @date 2017-6-19 上午11:32:53
	 */
	@RequestMapping(value = "/exportDiamondDetailList", method = {
			RequestMethod.POST})
	public void exportDiamondDetailList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportDiamondDetailList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "钻石统计细分.xlsx";
		List<Diamond> diamondList=null;
		try {
			diamondList=diamondService.selectDiamondDetailList(searchData);
			if(diamondList != null){
				String[] title = new String[] {"日期","渠道","钻石类型","用途","消耗钻石数量","占比"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(Diamond e : diamondList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getChannelName();
					items[count++] =e.getCurrTypeText();
					items[count++] =e.getPurpose();
					items[count++] =e.getPurposeNum();
					items[count++] =e.getRate()+"%";
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("钻石统计细分");
			}
			
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
}
	/**
	 * 活跃度人数及占比
	 * @Title: selectActiveCntAndRateList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<ActiveStat>
	 * @author hwj
	 * @date 2017-9-21 上午10:47:05
	 */
	@RequestMapping(value="/selectActiveCntAndRateList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<ActiveStat> selectActiveCntAndRateList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectActiveCntAndRateList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<ActiveStat> activeList=null;
		try {
			activeList=activeStatService.selectActiveCntAndRateList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return activeList;
	}
	/**
	 * 导出活跃度人数及占比
	 * @Title: exportActiveCntAndRateList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author hwj
	 * @date 2017-9-21 上午10:49:22
	 */
	@RequestMapping(value = "/exportActiveCntAndRateList", method = {
			RequestMethod.POST})
	public void exportActiveCntAndRateList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportActiveCntAndRateList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "活跃度人数及占比.xlsx";
		List<ActiveStat> activeList=null;
		try {
			activeList=activeStatService.selectActiveCntAndRateList(searchData);
			if(activeList != null){
				String[] title = new String[] {"日期","区服","国家","渠道","活跃度分数","达到该分数的玩家数","人数占比"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(ActiveStat e : activeList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getAreaName();
					items[count++] =e.getCountry();
					items[count++] =e.getChannelName();
					items[count++] =e.getActiveGrade();
					items[count++] =e.getActiveCount();
					items[count++] =e.getActiveRate();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("活跃度人数及占比");
			}
			
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
}
	/**
	 * 回购物品列表
	 * @Title: selectbugBackGoodsList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<ActiveStat>
	 * @author hwj
	 * @date 2017-9-21 上午10:58:30
	 */
	@RequestMapping(value="/selectbugBackGoodsList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<ActiveStat> selectbugBackGoodsList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectbugBackGoodsList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<ActiveStat> activeList=null;
		try {
			activeList=activeStatService.selectbugBackGoodsList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return activeList;
	}
	/**
	 * 导出回购物品
	 * @Title: exportBugBackGoodsList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author hwj
	 * @date 2017-9-21 上午11:07:13
	 */
	@RequestMapping(value = "/exportBugBackGoodsList", method = {
			RequestMethod.POST})
	public void exportBugBackGoodsList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportBugBackGoodsList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "回购物品.xlsx";
		List<ActiveStat> goodsList=null;
		try {
			goodsList=activeStatService.selectbugBackGoodsList(searchData);
			if(goodsList != null){
				String[] title = new String[] {"日期","区服","国家","渠道","回购物品列表","回购次数","消耗总钻石"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(ActiveStat e : goodsList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getAreaName();
					items[count++] =e.getCountry();
					items[count++] =e.getChannelName();
					items[count++] =e.getGoodsName();
					items[count++] =e.getTimes();
					items[count++] =e.getCostDiamond();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("回购物品");
			}
			
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
}
	/**
	 * 圣域战斗
	 * @Title: selectSyCombatList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<ActiveStat>
	 * @author hwj
	 * @date 2017-9-21 下午03:07:12
	 */
	@RequestMapping(value="/selectSyCombatList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<SyMapSummary> selectSyCombatList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectSyCombatList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<SyMapSummary> syList=null;
		try {
			syList=syMapSummaryService.selectSyCombatList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return syList;
	}
	/**
	 * 导出圣域战斗
	 * @Title: exportSyCombatList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author hwj
	 * @date 2017-9-21 下午03:10:17
	 */
	@RequestMapping(value = "/exportSyCombatList", method = {
			RequestMethod.POST})
	public void exportSyCombatList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportSyCombatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "圣域战斗.xlsx";
		List<SyMapSummary> syList=null;
		try {
			syList=syMapSummaryService.selectSyCombatList(searchData);
			if(syList != null){
				//移除星级
				String[] title = new String[] {"日期","区服","职业","开启战斗数","平均战斗用时","参与玩家数","抢夺玩家数","抢夺次数","离开玩家数量","失败玩家数","中途退出玩家数","已被占领的海岛数量",};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(SyMapSummary e : syList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getAreaid();
					items[count++] =e.getJobs();
					items[count++] =e.getOpenCombatCnt();
					items[count++] =e.getAvgCombatTimeStr();
					items[count++] =e.getActjoincnt();
					items[count++] =e.getActlootcnt();
					items[count++] =e.getLootcnt();
					items[count++] =e.getActleavecnt();
					items[count++] =e.getActfailcnt();
					items[count++] =e.getActexitcnt();
					items[count++] =e.getIslandcnt();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("圣域战斗");
			}
			
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
}
	/**
	 * 主城奇缘战斗
	 * @Title: selectZcCombatList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<ZcqyCombat>
	 * @author hwj
	 * @date 2017-9-25 上午09:39:36
	 */
	@RequestMapping(value="/selectZcCombatList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<ZcqyCombat> selectZcCombatList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectZcCombatList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<ZcqyCombat> zcCombatList=null;
		try {
			zcCombatList=zcqyCombatService.selectZcCombatList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return zcCombatList;
	}
	/**
	 * 导出主城奇遇战斗
	 * @Title: exportZcCombatList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author hwj
	 * @date 2017-9-25 上午11:30:52
	 */
	@RequestMapping(value = "/exportZcCombatList", method = {
			RequestMethod.POST})
	public void exportZcCombatList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportZcCombatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "主城奇缘.xlsx";
		List<ZcqyCombat> zcCombatList=null;
		try {
			zcCombatList=zcqyCombatService.selectZcCombatList(searchData);
			if(zcCombatList != null){
				String[] title = new String[] {"日期","区服","势力","渠道","职业","开启战斗数","平均战斗用时","参与玩家数","胜利人次","失败人次"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(ZcqyCombat e : zcCombatList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getAreaName();
					items[count++] =e.getCountry();
					items[count++] =e.getChannelName();
					items[count++] =e.getJobs();
					items[count++] =e.getOpenCombatCnt();
					items[count++] =e.getAvgCombatTimeStr();
					items[count++] =e.getGamerCnt();
					items[count++] =e.getSuccessCnt();
					items[count++] =e.getFailCnt();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("主城奇缘");
			}
			
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
}
	
	/**
	 * 主城玩法战斗详情
	 * @Title: selectZcPalyTypeCombatDetailList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<ZcqyCombat>
	 * @author hwj
	 * @date 2017-9-25 上午09:49:37
	 */
	@RequestMapping(value="/selectZcPalyTypeCombatDetailList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<ZcqyCombat> selectZcPalyTypeCombatDetailList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectZcPalyTypeCombatDetailList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<ZcqyCombat> detailList=null;
		try {
			detailList=zcqyCombatService.selectPlayTypeCombatDetailList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return detailList;
	}
	/**
	 * 祭坛
	 * @Title: selectAltarList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<ZcqyCombat>
	 * @author hwj
	 * @date 2017-9-25 下午02:33:12
	 */
	@RequestMapping(value="/selectAltarList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<Altar> selectAltarList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectAltarList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<Altar> altarList=null;
		try {
			altarList=altarService.selectAltarList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return altarList;
	}
	/**
	 * 导出祭坛
	 * @Title: exportAltarList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author hwj
	 * @date 2017-9-25 下午02:42:58
	 */
	@RequestMapping(value = "/exportAltarList", method = {
			RequestMethod.POST})
	public void exportAltarList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportAltarList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "祭坛.xlsx";
		List<Altar> altarList=null;
		try {
			altarList=altarService.selectAltarList(searchData);
			if(altarList != null){
				String[] title = new String[] {"日期","区服","渠道","货币类型","十连抽次数","单抽次数","单抽物品总数","十连抽物品总数"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(Altar e : altarList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getAreaName();
					items[count++] =e.getChannelName();
					items[count++] =e.getCurTypeName();
					items[count++] =e.getTenExtractTimes();
					items[count++] =e.getSingleExtractTimes();
					items[count++] =e.getSingleExtractCost();
					items[count++] =e.getTenExtractCost();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("祭坛");
			}
			
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
}
	/**
	 * 物资总览
	 * @Title: selectGoodsSummaryList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Altar>
	 * @author hwj
	 * @date 2017-9-25 下午04:19:25
	 */
	@RequestMapping(value="/selectGoodsSummaryList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<GoodsMaterials> selectGoodsSummaryList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectGoodsSummaryList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<GoodsMaterials> goodsList=null;
		try {
			goodsList=goodsMaterialsService.selectGoodsSummaryList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return goodsList;
	}
	/**
	 * 导出物资总览
	 * @Title: exportGoodsSummaryList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author hwj
	 * @date 2017-9-26 上午09:59:06
	 */
	@RequestMapping(value = "/exportGoodsSummaryList", method = {
			RequestMethod.POST})
	public void exportGoodsSummaryList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportGoodsSummaryList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "物资总览.xlsx";
		List<GoodsMaterials> goodsList=null;
		try {
			goodsList=goodsMaterialsService.selectGoodsSummaryList(searchData);
			if(goodsList != null){
				String[] title = new String[] {"日期","渠道","区服","限时折扣(购买总人数)","阵营物资(购买总人数)"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(GoodsMaterials e : goodsList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getChannelName();
					items[count++] =e.getAreaName();
					items[count++] =e.getLimitTimeDiscountTotalNum();
					items[count++] =e.getCampSuppTotalNum();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("物资总览");
			}
			
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
}
	
	/**
	 * 限时折扣物资
	 * @Title: selectBugMethodGoodsList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<GoodsMaterials>
	 * @author hwj
	 * @date 2017-9-25 下午05:20:07
	 */
	@RequestMapping(value="/selectLimitTimeDiscountGoodsList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<GoodsMaterials> selectLimitTimeDiscountGoodsList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectLimitTimeDiscountGoodsList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<GoodsMaterials> goodsList=null;
		searchData.setChecktype4(searchData.getChecktype1());//限时折扣
		try {
			goodsList=goodsMaterialsService.selectBugMethodGoodsDetailList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return goodsList;
	}
	
//	/**
//	 * 每日物资
//	 * @Title: selectDailySuppGoodsList
//	 * @Description: TODO
//	 * @param searchData
//	 * @param session
//	 * @return
//	 * List<GoodsMaterials>
//	 * @author hwj
//	 * @date 2017-9-26 上午09:45:48
//	 */
//	@RequestMapping(value="/selectDailySuppGoodsList",method={RequestMethod.POST,RequestMethod.GET})
//	@ResponseBody
//	public List<GoodsMaterials> selectDailySuppGoodsList(@RequestBody IndependBaseSearchData searchData,
//			HttpSession session){
//		//log
//		Date opDate = new Date();
//		String methodName = "selectDailySuppGoodsList";
//		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
//		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
//		sysAccessLogService.saveAccessLog(accessLog);
//		List<GoodsMaterials> goodsList=null;
//		searchData.setChecktype4(searchData.getChecktype2());//限时折扣
//		try {
//			goodsList=goodsMaterialsService.selectBugMethodGoodsDetailList(searchData);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return goodsList;
//	}
	
	/**
	 * 阵营物资
	 * @Title: selectCampSuppGoodsList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<GoodsMaterials>
	 * @author hwj
	 * @date 2017-9-26 上午09:47:04
	 */
	@RequestMapping(value="/selectCampSuppGoodsList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<GoodsMaterials> selectCampSuppGoodsList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectCampSuppGoodsList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<GoodsMaterials> goodsList=null;
		searchData.setChecktype4(searchData.getChecktype3());//限时折扣
		try {
			goodsList=goodsMaterialsService.selectBugMethodGoodsDetailList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return goodsList;
	}
	
	/**
	 * 王者争霸
	 * @Title: selectWzzbList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Sj3Wzzb>
	 * @author hwj
	 * @date 2017-10-12 下午04:48:05
	 */
	@RequestMapping(value="/selectWzzbList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<Sj3Wzzb> selectWzzbList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectWzzbList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<Sj3Wzzb> wzzbList=null;
		try {
			wzzbList=sj3WzzbService.selectWzzbList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return wzzbList;
	}
	/**
	 * 导出王者争霸
	 * @Title: exportWzzbList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author hwj
	 * @date 2017-10-13 上午10:36:31
	 */
	@RequestMapping(value = "/exportWzzbList", method = {
			RequestMethod.POST})
	public void exportWzzbList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportWzzbList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "王者争霸.xlsx";
		List<Sj3Wzzb> wzzbList=null;
		try {
			wzzbList=sj3WzzbService.selectWzzbList(searchData);
			if(wzzbList != null){
				String[] title = new String[] {"日期","区服","职业","参与人数","训练场使用人数","技能使用人数","魔物使用人数","灵魂使用人数","查看录像功能人数","查看录像功能人次"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(Sj3Wzzb e : wzzbList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getAreaid();
					items[count++] =e.getJobs();
					items[count++] =e.getJoinCnt();
					items[count++] =e.getTrainCnt();
					items[count++] =e.getJnCnt();
					items[count++] =e.getMwCnt();
					items[count++] =e.getLhCnt();
					items[count++] =e.getWatchCnt();
					items[count++] =e.getWatchTimes();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("王者争霸");
			}
			
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
}
	
	/**
	 * 王者争霸装备详情
	 * @Title: selectWzzbEquipDetailList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Sj3Wzzb>
	 * @author hwj
	 * @date 2017-10-12 下午04:50:13
	 */
	@RequestMapping(value="/selectWzzbEquipDetailList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<Sj3Wzzb> selectWzzbEquipDetailList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectWzzbEquipDetailList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<Sj3Wzzb> equipList=null;
		try {
			equipList=sj3WzzbService.selectWzzbEquipDetailList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return equipList;
	}
	/**
	 * 外围赛战斗次数最多前20名玩家详情
	 * @Title: selectMaxJoinActorDetailList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Sj3Wzzb>
	 * @author hwj
	 * @date 2017-10-12 下午04:51:16
	 */
	@RequestMapping(value="/selectMaxJoinActorDetailList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<Sj3Wzzb> selectMaxJoinActorDetailList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectMaxJoinActorDetailList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<Sj3Wzzb> maxJoinList=null;
		try {
			maxJoinList=sj3WzzbService.selectMaxJoinActorDetailList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return maxJoinList;
	}
	/**
	 * 王者争霸连胜
	 * @Title: selectWzzbLsList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Sj3Wzzb>
	 * @author hwj
	 * @date 2017-10-12 下午04:54:32
	 */
	@RequestMapping(value="/selectWzzbLsList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<Sj3Wzzb> selectWzzbLsList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectWzzbLsList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<Sj3Wzzb> wzzbLsList=null;
		try {
			wzzbLsList=sj3WzzbService.selectWzzbLsList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return wzzbLsList;
	}
	/**
	 * 王者争霸押注
	 * @Title: selectWzzbBetList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Sj3Wzzb>
	 * @author hwj
	 * @date 2017-10-12 下午04:56:47
	 */
	@RequestMapping(value="/selectWzzbBetList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<Sj3Wzzb> selectWzzbBetList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectWzzbBetList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<Sj3Wzzb> betList=null;
		try {
			betList=sj3WzzbService.selectWzzbBetList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return betList;
	}
	
	/**
	 * 导出王者争霸押注
	 * @Title: exportWzzbBetList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author hwj
	 * @date 2017-10-13 上午10:38:03
	 */
	@RequestMapping(value = "/exportWzzbBetList", method = {
			RequestMethod.POST})
	public void exportWzzbBetList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportWzzbBetList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "王者争霸押注.xlsx";
		List<Sj3Wzzb> betList=null;
		try {
			betList=sj3WzzbService.selectWzzbBetList(searchData);
			if(betList != null){
				String[] title = new String[] {"日期","区服","职业","押注参与人数","押注总次数","押注消耗金币总量","押注产出金币总量"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(Sj3Wzzb e : betList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getAreaid();
					items[count++] =e.getJobs();
					items[count++] =e.getBetCnt();
					items[count++] =e.getBetTimes();
					items[count++] =e.getBetCost();
					items[count++] =e.getBetGain();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("王者争霸押注");
			}
			
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
}
	
	/**
	 * 王者争霸押注档次
	 * @Title: selectWzzbBetLevelList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Sj3Wzzb>
	 * @author hwj
	 * @date 2017-10-12 下午04:58:22
	 */
	@RequestMapping(value="/selectWzzbBetLevelList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<Sj3Wzzb> selectWzzbBetLevelList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectWzzbBetLevelList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<Sj3Wzzb> betLevelList=null;
		try {
			betLevelList=sj3WzzbService.selectWzzbBetLevelList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return betLevelList;
	}
	/**
	 * 导出王者争霸押注档次
	 * @Title: exportWzzbBetLevelList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author hwj
	 * @date 2017-10-13 上午10:44:39
	 */
	@RequestMapping(value = "/exportWzzbBetLevelList", method = {
			RequestMethod.POST})
	public void exportWzzbBetLevelList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportWzzbBetLevelList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "王者争霸押注档次.xlsx";
		List<Sj3Wzzb> betLevelList=null;
		try {
			betLevelList=sj3WzzbService.selectWzzbBetLevelList(searchData);
			if(betLevelList != null){
				String[] title = new String[] {"日期","区服","档次","职业","小组赛押注参与人数","小组赛押注总次数","总决赛押注参与人数","总决赛押注总次数"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(Sj3Wzzb e : betLevelList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getAreaid();
					items[count++] =e.getLevel();
					items[count++] =e.getJobs();
					items[count++] =e.getGroupBetCnt();
					items[count++] =e.getGroupBetTimes();
					items[count++] =e.getFinalsBetCnt();
					items[count++] =e.getFinalsBetTimes();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("王者争霸押注档次");
			}
			
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
}
	/**
	 * 图鉴激活缘分数量
	 * @Title: selectIllusLuckNumList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<IllusStat>
	 * @author hwj
	 * @date 2018-1-11 上午10:42:49
	 */
	@RequestMapping(value="/selectIllusLuckNumList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<IllusStat> selectIllusLuckNumList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectIllusLuckNumList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<IllusStat> luckNumList=null;
		try {
			luckNumList=illusStatService.selectIllusLuckNumList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return luckNumList;
	}
	/**
	 * 导出图鉴激活缘分数量
	 * @Title: exportIllusLuckNumList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author hwj
	 * @date 2018-1-11 上午10:46:57
	 */
	@RequestMapping(value = "/exportIllusLuckNumList", method = {
			RequestMethod.POST})
	public void exportIllusLuckNumList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportIllusLuckNumList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "激活缘分数量.xlsx";
		List<IllusStat> luckNumList=null;
		try {
			luckNumList=illusStatService.selectIllusLuckNumList(searchData);
			if(luckNumList != null){
				String[] title = new String[] {"日期","区服","区域","平均激活缘分数量","平均激活缘分完成度","激活缘分最高数量"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(IllusStat e : luckNumList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getAreaid();
					items[count++] =e.getDistrict();
					items[count++] =e.getAvgLuckCnt();
					items[count++] =e.getAvgLuckFinishRate()+'%';
					items[count++] =e.getMaxluckcnt();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("激活缘分数量");
			}
			
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
}
	/**
	 * 历史以来关卡阶级难度统计
	 * @Title: selectStageStatBoforePerday
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Sj3HeroStat>
	 * @author hwj
	 * @date 2018-1-11 上午11:52:27
	 */
	@RequestMapping(value="/selectStageStatBoforePerday",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<Sj3HeroStat> selectStageStatBoforePerday(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectStageStatBoforePerday";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<Sj3HeroStat> stageList=null;
		try {
			stageList=sj3HeroStatService.selectStageStatBoforePerday(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return stageList;
	}
	/**
	 * 导出历史以来的英雄关卡
	 * @Title: exportStageStatBoforePerday
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author hwj
	 * @date 2018-1-11 上午11:56:34
	 */
	@RequestMapping(value = "/exportStageStatBoforePerday", method = {
			RequestMethod.POST})
	public void exportStageStatBoforePerday(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportStageStatBoforePerday", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "英雄冢关卡（历史以来）.xlsx";
		List<Sj3HeroStat> stageList=null;
		try {
			stageList=sj3HeroStatService.selectStageStatBoforePerday(searchData);
			if(stageList != null){
				String[] title = new String[] {"日期","区服","阶级","难度","该阶级难度总人数","占总阶级难度人数的百分比"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(Sj3HeroStat e : stageList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getAreaid();
					items[count++] =e.getOrder();
					items[count++] =e.getDifficult();
					items[count++] =e.getCount();
					items[count++] =e.getRate();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("英雄冢关卡（历史以来）");
			}
			
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 英雄冢关卡统计
	 * @Title: selectHeroStageStatList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Sj3HeroStat>
	 * @author hwj
	 * @date 2018-1-11 下午01:53:28
	 */
	@RequestMapping(value="/selectHeroStageStatList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<Sj3HeroStat> selectHeroStageStatList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectHeroStageStatList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<Sj3HeroStat> stageList=null;
		try {
			stageList=sj3HeroStatService.selectStageStat(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return stageList;
	}
	/**
	 * 导出英雄冢关卡统计
	 * @Title: exportHeroStageStatList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author hwj
	 * @date 2018-1-11 下午01:58:29
	 */
	@RequestMapping(value = "/exportHeroStageStatList", method = {
			RequestMethod.POST})
	public void exportHeroStageStatList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportHeroStageStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "英雄冢关卡统计.xlsx";
		List<Sj3HeroStat> stageList=null;
		try {
			stageList=sj3HeroStatService.selectStageStat(searchData);
			if(stageList != null){
				String[] title = new String[] {"日期","区服","关卡名称","进入关卡玩家数","通关玩家数","平均通关用时","平均通关星数","失败人次","中途退出人次","进入账号数","通关账号数","失败账号数"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(Sj3HeroStat e : stageList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getAreaid();
					items[count++] =e.getLevelDifficultStr();
					items[count++] =e.getActincnt();
					items[count++] =e.getActsuccnt();
					items[count++] =e.getAvgsuctime();
					items[count++] =e.getAvgsucstarcnt();
					items[count++] =e.getFailcnt();
					items[count++] =e.getExitcnt();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("英雄冢英雄冢关卡统计");
			}
			
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 许愿统计
	 * @Title: selectVowStatList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Sj3VowStat>
	 * @author hwj
	 * @date 2018-1-11 下午02:01:14
	 */
	@RequestMapping(value="/selectVowStatList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<Sj3VowStat> selectVowStatList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectVowStatList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<Sj3VowStat> vowList=null;
		try {
			vowList=sj3VowStatService.selectVowStatList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return vowList;
	}
	/**
	 * 导出许愿统计
	 * @Title: exportVowStatList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author hwj
	 * @date 2018-1-11 下午02:23:05
	 */
	@RequestMapping(value = "/exportVowStatList", method = {
			RequestMethod.POST})
	public void exportVowStatList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportVowStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "许愿统计.xlsx";
		List<Sj3VowStat> vowList=null;
		try {
			vowList=sj3VowStatService.selectVowStatList(searchData);
			if(vowList != null){
				String[] title = new String[] {"日期","区服","资源","许愿人数","占比"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(Sj3VowStat e : vowList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getAreaid();
					items[count++] =e.getResource();
					items[count++] =e.getActorcnt();
					items[count++] =e.getRate();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("许愿统计");
			}
			
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 时装统计
	 * @Title: selectModelStatList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Sj3ModelStat>
	 * @author hwj
	 * @date 2018-1-11 下午02:22:36
	 */
	@RequestMapping(value="/selectModelStatList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<Sj3ModelStat> selectModelStatList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectModelStatList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<Sj3ModelStat> modelList=null;
		try {
			modelList=sj3ModelStatService.selectModelStatList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return modelList;
	}
	/**
	 * 导出时装统计
	 * @Title: exportModelStatList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author hwj
	 * @date 2018-1-11 下午02:26:57
	 */
	@RequestMapping(value = "/exportModelStatList", method = {
			RequestMethod.POST})
	public void exportModelStatList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportModelStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "时装统计.xlsx";
		List<Sj3ModelStat> modelList=null;
		try {
			modelList=sj3ModelStatService.selectModelStatList(searchData);
			if(modelList != null){
				String[] title = new String[] {"日期","区服","时装ID","时效","兑换人数","占比"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(Sj3ModelStat e : modelList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getAreaid();
					items[count++] =e.getModelid();
					items[count++] =e.getPeriod();
					items[count++] =e.getActorcnt();
					items[count++] =e.getRate();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("时装统计");
			}
			
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 新手任务
	 * @Title: selectNewActTaskList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Sj3NewActTaskStat>
	 * @author hwj
	 * @date 2018-1-11 下午02:52:35
	 */
	@RequestMapping(value="/selectNewActTaskList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<Sj3NewActTaskStat> selectNewActTaskList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectNewActTaskList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<Sj3NewActTaskStat> taskList=null;
		try {
			taskList=sj3NewActTaskStatService.selectNewActTaskList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return taskList;
	}
	/**
	 * 导出新手任务
	 * @Title: exportNewActTaskList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author hwj
	 * @date 2018-1-11 下午02:59:05
	 */
	@RequestMapping(value = "/exportNewActTaskList", method = {
			RequestMethod.POST})
	public void exportNewActTaskList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportNewActTaskList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "新手任务.xlsx";
		List<Sj3NewActTaskStat> taskList=null;
		try {
			taskList=sj3NewActTaskStatService.selectNewActTaskList(searchData);
			if(taskList != null){
				String[] title = new String[] {"日期","区服","渠道","任务ID","任务名称","完成人数","完成率"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(Sj3NewActTaskStat e : taskList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getAreaid();
					items[count++] =e.getChannelName();
					items[count++] =e.getMissionid();
					items[count++] =e.getMission();
					items[count++] =e.getUsercnt();
					items[count++] =e.getFinish_rate();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("新手任务");
			}
			
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 新手任务总的完成次数
	 * @Title: selectNewActTotalFinishCountList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Sj3NewActTaskStat>
	 * @author hwj
	 * @date 2018-1-11 下午03:21:13
	 */
	@RequestMapping(value="/selectNewActTotalFinishCountList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<Sj3NewActTaskStat> selectNewActTotalFinishCountList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectNewActTotalFinishCountList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<Sj3NewActTaskStat> taskList=null;
		try {
			taskList=sj3NewActTaskStatService.selectNewActTotalFinishCountList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return taskList;
	}
	/**
	 * 导出新手任务总的完成次数
	 * @Title: exportNewActTotalFinishCount
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author hwj
	 * @date 2018-1-11 下午03:24:39
	 */
	@RequestMapping(value = "/exportNewActTotalFinishCount", method = {
			RequestMethod.POST})
	public void exportNewActTotalFinishCount(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportNewActTotalFinishCount", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "新手任务总的完成次数.xlsx";
		List<Sj3NewActTaskStat> taskList=null;
		try {
			taskList=sj3NewActTaskStatService.selectNewActTotalFinishCountList(searchData);
			if(taskList != null){
				String[] title = new String[] {"日期","区服","渠道","任务完成总次数","人均完成任务次数"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(Sj3NewActTaskStat e : taskList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getAreaid();
					items[count++] =e.getChannelid();
					items[count++] =e.getMissioncount();
					items[count++] =e.getAvgMissionCount();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("新手任务总的完成次数");
			}
			
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 月卡统计
	 * @Title: selectMonthCardStatList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Sj3MonthCardStat>
	 * @author hwj
	 * @date 2018-4-3 下午12:58:21
	 */
	@RequestMapping(value="/selectMonthCardStatList",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<Sj3MonthCardStat> selectMonthCardStatList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		//log
		Date opDate = new Date();
		String methodName = "selectMonthCardStatList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		List<Sj3MonthCardStat> monthCardstatList=null;
		try {
			monthCardstatList=sj3MonthCardStatService.selectMonthCardStatList(searchData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return monthCardstatList;
	}
	
	/**
	 * 导出月卡统计
	 * @Title: exportMonthCardStatList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author hwj
	 * @date 2018-4-3 下午01:29:13
	 */
	@RequestMapping(value = "/exportMonthCardStatList", method = {
			RequestMethod.POST})
	public void exportMonthCardStatList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportMonthCardStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "月卡统计.xlsx";
		List<Sj3MonthCardStat> monthCardstatList=null;
		try {
			monthCardstatList=sj3MonthCardStatService.selectMonthCardStatList(searchData);
			if(monthCardstatList != null){
				String[] title = new String[] {"日期","渠道","购买月卡角色数","购买月卡账号数","平均购买次数"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(Sj3MonthCardStat e : monthCardstatList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getChannelName();
					items[count++] =e.getActCnt();
					items[count++] =e.getUidCnt();
					items[count++] =e.getAvgCount();
					datalist.add(items);
				}
				list.add(datalist);
				sheetNameList.add("月卡统计");
			}
			
			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w,sheetNameList, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 关卡统计
	 * @Title: selectStageStatList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<Stage>
	 * @author hwj
	 * @date 2018-4-9 下午03:29:24
	 */
	@RequestMapping(value= "/selectStageStatList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<Stage> selectStageStatList(@RequestBody IndependBaseSearchData searchData,
			HttpSession session){
		List<Stage> list = new ArrayList<Stage>();
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectStageStatList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =stageService.selectStageList(searchData);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 导出关卡统计
	 * @Title: exportStageStatList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * void
	 * @author hwj
	 * @date 2018-4-9 下午03:39:23
	 */
	@RequestMapping(value = "/exportStageStatList", method = {
			RequestMethod.POST})
	public void exportStageStatList(
			@RequestBody IndependBaseSearchData searchData, HttpSession session,HttpServletResponse response){
		//log
		sysAccessLogService.log(request, getClass(), "exportStageStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "关卡统计.xlsx";
		List<Stage> stageList=null;
		try {
			stageList=stageService.selectStageList(searchData);
			if(stageList != null){
				String[] title = new String[] {"日期","渠道","关卡名称","职业","开启关卡人数","进入关卡人数","关卡完成数","关卡未完成数","关卡平均完成用时(s)","中途退出人数","开启关卡账号数","进入关卡账号数","关卡完成账号数","关卡未完成账号数"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(Stage e : stageList){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getChannelName();
					items[count++] =e.getStageName();
					items[count++] =e.getJobs();
					items[count++] =e.getOpencnt();
					items[count++] =e.getEntercnt();
					items[count++] =e.getSuccdcnt();
					items[count++] =e.getFailcnt();
					items[count++] =e.getAvg_succ_time();
					items[count++] =e.getExitcnt();
					items[count++] =e.getOpenuidcnt();
					items[count++] =e.getEnteruidcnt();
					items[count++] =e.getSuccduidcnt();
					items[count++] =e.getFailuidcnt();
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
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}