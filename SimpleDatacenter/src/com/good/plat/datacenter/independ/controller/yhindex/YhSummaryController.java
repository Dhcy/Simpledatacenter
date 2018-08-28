package com.good.plat.datacenter.independ.controller.yhindex;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.independ.entity.yhindex.YhLightHouceData;
import com.good.plat.datacenter.independ.entity.yhindex.YhPlaymineStat;
import com.good.plat.datacenter.independ.entity.yhindex.YhTaskManage;
import com.good.plat.datacenter.independ.entity.yhindex.YhCurrencyStat;
import com.good.plat.datacenter.independ.entity.yhindex.YhGoodsStat;
import com.good.plat.datacenter.independ.entity.yhindex.YhLevelStat;
import com.good.plat.datacenter.independ.entity.yhindex.YhOnlineStat;
import com.good.plat.datacenter.independ.entity.yhindex.YhStageStat;
import com.good.plat.datacenter.independ.service.yhindex.YhPlaymineStatService;
import com.good.plat.datacenter.independ.service.yhindex.YhTaskManageService;
import com.good.plat.datacenter.independ.service.yhindex.YhCurrencyStatService;
import com.good.plat.datacenter.independ.service.yhindex.YhGoodsStatService;
import com.good.plat.datacenter.independ.service.yhindex.YhLevelStatService;
import com.good.plat.datacenter.independ.service.yhindex.YhOnlineStatService;
import com.good.plat.datacenter.independ.service.yhindex.YhStageStatService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;
/**
 * 银河独立数据控制器
 * @ClassName: YhSummaryController
 * @Description: TODO
 * @author hwj
 * @date 2017-12-27 下午12:53:12
 */
@Controller
@RequestMapping(value="/yhSummary")
public class YhSummaryController extends BaseController {
	Logger logger = LoggerFactory.getLogger(getClass());
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private YhStageStatService yhStageStatService;
	@Autowired
	private YhTaskManageService yhTaskManageService;
	@Autowired
	private YhOnlineStatService yhOnlineStatService;
	@Autowired
	private YhLevelStatService yhLevelStatService;
	@Autowired
	private YhCurrencyStatService yhCurrencyStatService;
	@Autowired
	private YhGoodsStatService yhGoodsStatService;
	@Autowired
	private YhPlaymineStatService yhPlaymineStatService;
	/**
	 * 关卡统计
	 * @Title: selectStageList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<YhStageStat>
	 * @author hwj
	 * @date 2017-12-27 下午01:35:11
	 */
	@RequestMapping(value="/selectYhStageList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhStageStat> selectYhStageList(@RequestBody IndependBaseSearchData searchData){
		List<YhStageStat> list=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectYhStageList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =yhStageStatService.selectYhStageList(searchData);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("{}",e.getMessage());
		}
		return list;
	}
	/**导出关卡统计
	 * 
	 * @Title: exportYhStageList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-12-27 下午03:26:20
	 */
	@RequestMapping(value = "/exportYhStageList", method = {
			RequestMethod.POST})
	public void exportYhStageList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYhStageList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "关卡统计.xlsx";
		List<YhStageStat> stageList =yhStageStatService.selectYhStageList(searchData);
		//
		if(stageList != null){
			String[] title = new String[] {"日期","渠道","关卡ID","符合进入玩家数量","进入人数","通关人数","通关玩家占比","通关玩家总比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhStageStat e : stageList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getStageid();
				items[count++] =e.getFitActUser();
				items[count++] =e.getEnterActUser();
				items[count++] =e.getSuccessActUser();
				items[count++] =e.getSuccActUserRate()+"%";
				items[count++] =e.getSuccActUserTotalRate()+"%";
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
	 * 关卡数据
	 * @Title: selectStageDataList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<YhStageStat>
	 * @author hwj
	 * @date 2017-12-27 下午01:58:00
	 */
	@RequestMapping(value="/selectYhStageDataList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhStageStat> selectYhStageDataList(@RequestBody IndependBaseSearchData searchData){
		List<YhStageStat> list=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectYhStageDataList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =yhStageStatService.selectYhStageDataList(searchData);
			if(list!=null&&!list.isEmpty()){
				for(YhStageStat e:list){
					e.setAvgSuccessTimeStr(e.getAvgSuccessTime()==null?null:DateUtil.toLocalTimeDesc(e.getAvgSuccessTime().longValue()));
					e.setAvgFailTimeStr(e.getAvgFailTime()==null?null:DateUtil.toLocalTimeDesc(e.getAvgFailTime().longValue()));
					e.setAvgFightTimeStr(e.getAvgFightTime()==null?null:DateUtil.toLocalTimeDesc(e.getAvgFightTime().longValue()));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("{}",e.getMessage());
		}
		return list;
	}
	/**
	 * 导出关卡数据
	 * @Title: exportYhStageDataList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-12-27 下午03:32:40
	 */
	@RequestMapping(value = "/exportYhStageDataList", method = {
			RequestMethod.POST})
	public void exportYhStageDataList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYhStageDataList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "关卡数据.xlsx";
		List<YhStageStat> stageList =yhStageStatService.selectYhStageDataList(searchData);
		if(stageList!=null&&!stageList.isEmpty()){
			for(YhStageStat e:stageList){
				e.setAvgSuccessTimeStr(e.getAvgSuccessTime()==null?null:DateUtil.toLocalTimeDesc(e.getAvgSuccessTime().longValue()));
				e.setAvgFailTimeStr(e.getAvgFailTime()==null?null:DateUtil.toLocalTimeDesc(e.getAvgFailTime().longValue()));
				e.setAvgFightTimeStr(e.getAvgFightTime()==null?null:DateUtil.toLocalTimeDesc(e.getAvgFightTime().longValue()));
			}
		}
		//
		if(stageList != null){
			String[] title = new String[] {"渠道","关卡ID","挑战次数","胜利次数","胜利概率","平均胜利时间","平均战败时间","平均战斗时间","首次通关平均等级","首次通关最低等级"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhStageStat e : stageList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getChannelName();
				items[count++] =e.getStageid();
				items[count++] =e.getCount();
				items[count++] =e.getSuccessCount();
				items[count++] =e.getSuccessRate()+"%";
				items[count++] =e.getAvgSuccessTimeStr();
				items[count++] =e.getAvgFightTimeStr();
				items[count++] =e.getAvgFightTimeStr();
				items[count++] =e.getFirstAvgSuccLvl();
				items[count++] =e.getFirstMinLvl();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("关卡数据");
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
	 * 课程统计
	 * @Title: selectYhCourseStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<YhCourseStat>
	 * @author hwj
	 * @date 2017-12-27 下午05:10:07
	 */
	@RequestMapping(value="/selectYhCourseStatList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhTaskManage> selectYhCourseStatList(@RequestBody IndependBaseSearchData searchData){
		List<YhTaskManage> list=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectYhCourseStatList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//1课程步数;2课程步数排重
			if("1".equals(searchData.getChecktype1())){
				list =yhTaskManageService.selectYhCourseStatList(searchData);
			}else if("2".equals(searchData.getChecktype1())){
				list =yhTaskManageService.selectYhCourseDisStatList(searchData);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("{}",e.getMessage());
		}
		return list;
	}
	/**
	 * 导出课程统计
	 * @Title: exportYhCourseStatListt
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-12-27 下午05:27:33
	 */
	@RequestMapping(value = "/exportYhCourseStatList", method = {
			RequestMethod.POST})
	public void exportYhCourseStatList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYhCourseStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "课程统计.xlsx";
		List<YhTaskManage> courseList =new ArrayList<YhTaskManage>();
		//1课程步数;2课程步数排重
		if("1".equals(searchData.getChecktype1())){
			courseList =yhTaskManageService.selectYhCourseStatList(searchData);
		}else if("2".equals(searchData.getChecktype1())){
			courseList =yhTaskManageService.selectYhCourseDisStatList(searchData);
		}
		//
		if(courseList != null){
			String[] title = new String[] {"日期","渠道","课程ID","停留在此教程的人数","停留人数占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhTaskManage e : courseList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getCourseid();
				items[count++] =e.getCount();
				items[count++] =e.getRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("课程统计");
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
	 * 在线时长
	 * @Title: selectYhOnlineStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<YhOnlineStat>
	 * @author hwj
	 * @date 2017-12-27 下午06:43:35
	 */
	@RequestMapping(value="/selectYhOnlineStatList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhOnlineStat> selectYhOnlineStatList(@RequestBody IndependBaseSearchData searchData){
		List<YhOnlineStat> list=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectYhOnlineStatList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =yhOnlineStatService.selectYhOnlineStatList(searchData);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("{}",e.getMessage());
		}
		return list;
	}
	/**
	 * 导出在线时长
	 * @Title: exportYhOnlineStatList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-12-28 上午09:23:25
	 */
	@RequestMapping(value = "/exportYhOnlineStatList", method = {
			RequestMethod.POST})
	public void exportYhOnlineStatList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYhOnlineStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "在线时长.xlsx";
		List<YhOnlineStat> onlineList =yhOnlineStatService.selectYhOnlineStatList(searchData);
		//
		if(onlineList != null){
			String[] title = new String[] {"日期","渠道","0~9min用户数量","0~9min占比","10~19min用户数量","10~19min占比","20~29min用户数量","20~29min占比","30~59min用户数量",
					"30~59min占比","60~89min用户数量","60~89min占比","90~119min用户数量","90~119min占比","120~149min用户数量","120~149min占比","150~179min用户数量",
					"150~179min占比","180~239min用户数量","180~239min占比","240min以上用户数量","240min以上占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhOnlineStat e : onlineList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getMinutes_0to9User();
				items[count++] =e.getMinutes_0to9UserRate()+"%";
				items[count++] =e.getMinutes_10to19User();
				items[count++] =e.getMinutes_10to19UserRate()+"%";
				items[count++] =e.getMinutes_20to29User();
				items[count++] =e.getMinutes_20to29UserRate()+"%";
				items[count++] =e.getMinutes_30to59User();
				items[count++] =e.getMinutes_30to59UserRate()+"%";
				items[count++] =e.getMinutes_60to89User();
				items[count++] =e.getMinutes_60to89UserRate()+"%";
				items[count++] =e.getMinutes_90to119User();
				items[count++] =e.getMinutes_90to119UserRate()+"%";
				items[count++] =e.getMinutes_120to149User();
				items[count++] =e.getMinutes_120to149UserRate()+"%";
				items[count++] =e.getMinutes_150to179User();
				items[count++] =e.getMinutes_150to179UserRate()+"%";
				items[count++] =e.getMinutes_180to239User();
				items[count++] =e.getMinutes_180to239UserRate()+"%";
				items[count++] =e.getMinutes_240toUser();
				items[count++] =e.getMinutes_240toUserRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("在线时长");
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
	 * 等级分布
	 * @Title: selectYhLevelDistributeList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<YhLevelStat>
	 * @author hwj
	 * @date 2017-12-28 上午10:32:51
	 */
	@RequestMapping(value="/selectYhLevelDistributeList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhLevelStat> selectYhLevelDistributeList(@RequestBody IndependBaseSearchData searchData){
		List<YhLevelStat> list=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectYhLevelDistributeList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =yhLevelStatService.selectYhLevelDistributeList(searchData);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 导出等级分布
	 * @Title: exportYhLevelDistributeList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-12-28 上午11:02:55
	 */
	@RequestMapping(value = "/exportYhLevelDistributeList", method = {
			RequestMethod.POST})
	public void exportYhLevelDistributeList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYhLevelDistributeList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "等级分布.xlsx";
		List<YhLevelStat> levelList =yhLevelStatService.selectYhLevelDistributeList(searchData);
		//
		if(levelList != null){
			String[] title = new String[] {"日期","渠道","1-10级用户数量","1-10级占比","11-20级用户数量","11-20级占比","21-30级用户数量","21-30级占比","31-40级用户数量",
					"31-40级占比","41-50级用户数量","41-50级用户占比","51-60级用户数量","51-60级占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhLevelStat e : levelList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getLvl_1to10User();
				items[count++] =e.getLvl_1to10UserRate()+"%";
				items[count++] =e.getLvl_11to20User();
				items[count++] =e.getLvl_11to20UserRate()+"%";
				items[count++] =e.getLvl_21to30User();
				items[count++] =e.getLvl_21to30UserRate()+"%";
				items[count++] =e.getLvl_31to40User();
				items[count++] =e.getLvl_31to40UserRate()+"%";
				items[count++] =e.getLvl_41to50User();
				items[count++] =e.getLvl_41to50UserRate()+"%";
				items[count++] =e.getLvl_51to60User();
				items[count++] =e.getLvl_51to60UserRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("等级分布");
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
	 * 系统参与度
	 * @Title: selectYhSystemJoinList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<YhCourseStat>
	 * @author hwj
	 * @date 2017-12-28 下午12:56:22
	 */
	@RequestMapping(value="/selectYhSystemJoinList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhTaskManage> selectYhSystemJoinList(@RequestBody IndependBaseSearchData searchData){
		List<YhTaskManage> list=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectYhSystemJoinList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =yhTaskManageService.selectYhSystemJoinList(searchData);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 导出系统参与度
	 * @Title: exportYhSystemJoinList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-12-28 下午01:40:33
	 */
	@RequestMapping(value = "/exportYhSystemJoinList", method = {
			RequestMethod.POST})
	public void exportYhSystemJoinList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYhSystemJoinList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "系统参与度.xlsx";
		List<YhTaskManage> sysList =yhTaskManageService.selectYhSystemJoinList(searchData);
		//
		if(sysList != null){
			String[] title = new String[] {"日期","渠道","系统","参与人数","参与人数占比","平均参与人次","平均首次参与等级"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhTaskManage e : sysList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getSysname();
				items[count++] =e.getJoinUser();
				items[count++] =e.getJoinUserRate()+"%";
				items[count++] =e.getAvgJoinTimes();
				items[count++] =e.getAvgFirstJoinLvl();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("系统参与度");
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
	 * 登入次数
	 * @Title: selectYhLoginTimesList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<YhOnlineStat>
	 * @author hwj
	 * @date 2017-12-28 下午02:43:38
	 */
	@RequestMapping(value="/selectYhLoginTimesList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhOnlineStat> selectYhLoginTimesList(@RequestBody IndependBaseSearchData searchData){
		List<YhOnlineStat> list=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectYhLoginTimesList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =yhOnlineStatService.selectYhLoginTimesList(searchData);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 导出登入次数
	 * @Title: exportYhLoginTimesList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-12-28 下午02:55:06
	 */
	@RequestMapping(value = "/exportYhLoginTimesList", method = {
			RequestMethod.POST})
	public void exportYhLoginTimesList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYhLoginTimesList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "登入次数.xlsx";
		List<YhOnlineStat> loginTimesList =yhOnlineStatService.selectYhLoginTimesList(searchData);
		//
		if(loginTimesList != null){
			String[] title = new String[] {"日期","渠道","0次用户数量","0次占比","1-2次用户数量","1-2次占比","3-5次用户数量","3-5次占比",
					"6-10次用户数量","6-10次占比","11次以上用户数量","11次以上占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhOnlineStat e : loginTimesList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getLoginTimes0User();
				items[count++] =e.getLoginTimes0UserRate()+"%";
				items[count++] =e.getLoginTimes1to2User();
				items[count++] =e.getLoginTimes1to2UserRate()+"%";
				items[count++] =e.getLoginTimes3to5User();
				items[count++] =e.getLoginTimes3to5UserRate()+"%";
				items[count++] =e.getLoginTimes6to10User();
				items[count++] =e.getLoginTimes6to10UserRate()+"%";
				items[count++] =e.getLoginTimes11toUser();
				items[count++] =e.getLoginTimes11toUserRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("登入次数");
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
	 * 体力统计
	 * @Title: selectYhPowerStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<YhCurrencyStat>
	 * @author hwj
	 * @date 2017-12-28 下午05:23:00
	 */
	@RequestMapping(value="/selectYhPowerStatList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhCurrencyStat> selectYhPowerStatList(@RequestBody IndependBaseSearchData searchData){
		List<YhCurrencyStat> list=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectYhPowerStatList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =yhCurrencyStatService.selectYhPowerStatList(searchData);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 导出体力统计
	 * @Title: exportYhPowerStatList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-12-28 下午05:32:42
	 */
	@RequestMapping(value = "/exportYhPowerStatList", method = {
			RequestMethod.POST})
	public void exportYhPowerStatList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYhPowerStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "体力统计.xlsx";
		List<YhCurrencyStat> currencyList =yhCurrencyStatService.selectYhPowerStatList(searchData);
		//
		if(currencyList != null){
			String[] title = new String[] {"日期","渠道","平均消耗体力值","0次用户数量","0次占比","1次用户数量","1次占比","2次用户数量","2次占比",
					"3次用户数量","3次占比","4次用户数量","4次占比","5次以上用户数量","5次以上占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhCurrencyStat e : currencyList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getAvgPowerValue();
				items[count++] =e.getBuyPower0TimesActUser();
				items[count++] =e.getBuyPower0TimesActUserRate()+"%";
				items[count++] =e.getBuyPower1TimesActUser();
				items[count++] =e.getBuyPower1TimesActUserRate()+"%";
				items[count++] =e.getBuyPower2TimesActUser();
				items[count++] =e.getBuyPower2TimesActUserRate()+"%";
				items[count++] =e.getBuyPower3TimesActUser();
				items[count++] =e.getBuyPower3TimesActUserRate()+"%";
				items[count++] =e.getBuyPower4TimesActUser();
				items[count++] =e.getBuyPower4TimesActUserRate()+"%";
				items[count++] =e.getBuyPowerHigh5TimesActUser();
				items[count++] =e.getBuyPowerHigh5TimesActUserRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("体力统计");
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
	 * 氪金消耗
	 * @Title: selectYhKrypGoldConsumpList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<YhCurrencyStat>
	 * @author hwj
	 * @date 2017-12-28 下午05:52:28
	 */
	@RequestMapping(value="/selectYhKrypGoldConsumpList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhCurrencyStat> selectYhKrypGoldConsumpList(@RequestBody IndependBaseSearchData searchData){
		List<YhCurrencyStat> list=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectYhKrypGoldConsumpList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =yhCurrencyStatService.selectYhKrypGoldConsumpList(searchData);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 导出氪金消耗
	 * @Title: exportYhKrypGoldConsumpList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-12-28 下午05:56:59
	 */
	@RequestMapping(value = "/exportYhKrypGoldConsumpList", method = {
			RequestMethod.POST})
	public void exportYhKrypGoldConsumpList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYhKrypGoldConsumpList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "氪金消耗.xlsx";
		List<YhCurrencyStat> goldConsumpList =yhCurrencyStatService.selectYhKrypGoldConsumpList(searchData);
		//
		if(goldConsumpList != null){
			String[] title = new String[] {"日期","渠道","消耗氪金总值","人均消耗氪金值"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhCurrencyStat e : goldConsumpList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getCount();
				items[count++] =e.getAvgConsumRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("氪金消耗");
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
	 * 氪金存量
	 * @Title: selectYhKrypGoldLeftList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<YhCurrencyStat>
	 * @author hwj
	 * @date 2017-12-28 下午06:57:27
	 */
	@RequestMapping(value="/selectYhKrypGoldLeftList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhCurrencyStat> selectYhKrypGoldLeftList(@RequestBody IndependBaseSearchData searchData){
		List<YhCurrencyStat> list=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectYhKrypGoldLeftList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =yhCurrencyStatService.selectYhKrypGoldLeftList(searchData);
			if(list!=null&&!list.isEmpty()){
				for(YhCurrencyStat e:list){
					String lvlsection=e.getLvlsection()==null?null:switchLvl(e.getLvlsection().intValue());
					e.setLvlSectionStr(lvlsection);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	private String switchLvl(int type){
		String lvlsection="";
		switch (type) {
		case 1:
			lvlsection="1-10级";
			break;
		case 11:
			lvlsection="11-20级";
			break;
		case 21:
			lvlsection="21-30级";
			break;
		case 31:
			lvlsection="31-40级";
			break;
		case 41:
			lvlsection="41-50级";
			break;
		case 51:
			lvlsection="51-60级";
			break;
		}
		return lvlsection;
	}
	/**
	 * 氪金存量
	 * @Title: exportYhKrypGoldLeftList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-12-28 下午07:15:16
	 */
	@RequestMapping(value = "/exportYhKrypGoldLeftList", method = {
			RequestMethod.POST})
	public void exportYhKrypGoldLeftList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYhKrypGoldLeftList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "氪金存量.xlsx";
		List<YhCurrencyStat> goldLeftlist =yhCurrencyStatService.selectYhKrypGoldLeftList(searchData);
		if(list!=null&&!list.isEmpty()){
			for(YhCurrencyStat e:goldLeftlist){
				String lvlsection=e.getLvlsection()==null?null:switchLvl(e.getLvlsection().intValue());
				e.setLvlSectionStr(lvlsection);
			}
		}
		//
		if(goldLeftlist != null){
			String[] title = new String[] {"日期","渠道","等级段","0-100氪金占比","101-1000氪金占比","1001-3000氪金占比","3001-10000氪金占比","10001氪金以上占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhCurrencyStat e : goldLeftlist){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getLvlSectionStr();
				items[count++] =e.getSect0CoutRate()+"%";
				items[count++] =e.getSect101CoutRate()+"%";
				items[count++] =e.getSect1001CoutRate()+"%";
				items[count++] =e.getSect3001CoutRate()+"%";
				items[count++] =e.getSect10001CoutRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("氪金存量");
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
	 * 商品贩卖
	 * @Title: selectYhGoodsSoldList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<YhGoodsStat>
	 * @author hwj
	 * @date 2017-12-29 上午10:09:04
	 */
	@RequestMapping(value="/selectYhGoodsSoldList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhGoodsStat> selectYhGoodsSoldList(@RequestBody IndependBaseSearchData searchData){
		List<YhGoodsStat> list=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectYhGoodsSoldList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =yhGoodsStatService.selectYhGoodsSoldList(searchData);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 导出商品贩卖
	 * @Title: exportYhGoodsSoldList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-12-29 上午10:12:56
	 */
	@RequestMapping(value = "/exportYhGoodsSoldList", method = {
			RequestMethod.POST})
	public void exportYhGoodsSoldList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYhGoodsSoldList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "商品贩卖.xlsx";
		List<YhGoodsStat> goodslist =yhGoodsStatService.selectYhGoodsSoldList(searchData);
		//
		if(goodslist != null){
			String[] title = new String[] {"日期","渠道","商店名称","商品ID","商品名称","购买数量","购买占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhGoodsStat e : goodslist){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getShopname();
				items[count++] =e.getGoodsid();
				items[count++] =e.getGoodsname();
				items[count++] =e.getCount();
				items[count++] =e.getRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("商品贩卖");
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
	 * 货币消耗统计
	 * @Title: selectCurrencyConsStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<YhCurrencyStat>
	 * @author hwj
	 * @date 2017-12-29 下午04:29:08
	 */
	@RequestMapping(value="/selectCurrencyConsStatList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhCurrencyStat> selectCurrencyConsStatList(@RequestBody IndependBaseSearchData searchData){
		List<YhCurrencyStat> list=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectCurrencyConsStatList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =yhCurrencyStatService.selectCurrencyConsStatList(searchData);
			if(!list.isEmpty()){
				for(YhCurrencyStat e:list){
					String currType=e.getCurType()==null?"":switchCurrType(e.getCurType().intValue());
					e.setCurrencyType(currType);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 导出货币消耗统计
	 * @Title: exportCurrencyConsStatList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-12-29 下午04:34:58
	 */
	@RequestMapping(value = "/exportCurrencyConsStatList", method = {
			RequestMethod.POST})
	public void exportCurrencyConsStatList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportCurrencyConsStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "货币消耗统计.xlsx";
		List<YhCurrencyStat> conslist =yhCurrencyStatService.selectCurrencyConsStatList(searchData);
		if(!conslist.isEmpty()){
			for(YhCurrencyStat e:conslist){
				String currType=e.getCurType()==null?"":switchCurrType(e.getCurType().intValue());
				e.setCurrencyType(currType);
			}
		}
		//
		if(conslist != null){
			String[] title = new String[] {"货币种类","消耗方式","消耗次数","合计货币","平均每次消耗","使用人数","人均次数","人均消耗"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhCurrencyStat e : conslist){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getCurrencyType();
				items[count++] =e.getConsumeType();
				items[count++] =e.getConsumeCnt();
				items[count++] =e.getCurrencyCount();
				items[count++] =e.getAvgPerTimeCount();
				items[count++] =e.getActUser();
				items[count++] =e.getAvgPerUserConsumeCnt();
				items[count++] =e.getAvgPerUserCount();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("货币消耗统计");
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
	 * 货币产出与消耗统计
	 * @Title: selectCurOutAndConsList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-12-29 下午04:47:21
	 */
	@RequestMapping(value="/selectCurOutAndConsList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectCurOutAndConsList(@RequestBody IndependBaseSearchData searchData){
		Map<String,Object> resultMap=new HashMap<String, Object>();
		List<YhCurrencyStat> list=null;
		Map<String, Object> totalSum=new HashMap<String, Object>();
		Integer outCurrSum=0;
		Integer consCurrSum=0;
		Integer leftCurrSum=0;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectCurrencyConsStatList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =yhCurrencyStatService.selectCurOutAndConsList(searchData);
			if(!list.isEmpty()){
				for(YhCurrencyStat e:list){
					String currType=e.getCurType()==null?"":switchCurrType(e.getCurType().intValue());
					e.setConsumeType(currType);
					outCurrSum+=e.getGainCnt();
					consCurrSum+=e.getConsumeCnt();
					leftCurrSum+=e.getLeftCount();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		totalSum.put("outCurrSum", outCurrSum);
		totalSum.put("consCurrSum", consCurrSum);
		totalSum.put("leftCurrSum", leftCurrSum);
		resultMap.put("totalSum", totalSum);
		resultMap.put("outConsCurrsList", list);
		return resultMap;
	}
	private String switchCurrType(int type){
		String result="";
		switch (type) {
		case 1:
			result="氪金";
			break;
		case 2:
			result="金币";
			break;
		default:
			break;
		}
		return result;
	}
	/**
	 * 导出货币产出，消耗统计
	 * @Title: exportCurOutAndConsList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-12-29 下午05:08:40
	 */
	@RequestMapping(value = "/exportCurOutAndConsList", method = {
			RequestMethod.POST})
	public void exportCurOutAndConsList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportCurOutAndConsList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "货币产出消耗统计.xlsx";
		List<YhCurrencyStat> outConslist =yhCurrencyStatService.selectCurOutAndConsList(searchData);
		Integer outCurrSum=0;
		Integer consCurrSum=0;
		Integer leftCurrSum=0;
		//汇总
		if(!outConslist.isEmpty()){
			for(YhCurrencyStat e:outConslist){
				String currType=e.getCurType()==null?"":switchCurrType(e.getCurType().intValue());
				e.setConsumeType(currType);
				//
				outCurrSum+=e.getGainCnt();
				consCurrSum+=e.getConsumeCnt();
				leftCurrSum+=e.getLeftCount();
			}
		}
		//导出list
		if(outConslist != null){
			String[] title = new String[] {"日期","货币类型","产出","消费","结余"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			if(!outConslist.isEmpty()){
				for(YhCurrencyStat e : outConslist){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getConsumeType();
					items[count++] =e.getGainCnt();
					items[count++] =e.getConsumeCnt();
					items[count++] =e.getLeftCount();
					datalist.add(items);
				}
				//总计
				Object[] items=new Object[title.length];
				int count=0;
				items[count++]="总计";
				items[count++]=null;
				items[count++]=outCurrSum;//产出
				items[count++]=consCurrSum;//消耗
				items[count++]=leftCurrSum;//结余
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("货币产出消耗统计");
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
	 * 货币消耗的人数跟次数
	 * @Title: selectCurConsUserTimesList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-12-29 下午05:26:16
	 */
	@RequestMapping(value="/selectCurConsUserTimesList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectCurConsUserTimesList(@RequestBody IndependBaseSearchData searchData){
		Map<String,Object> resultMap=new HashMap<String, Object>();
		List<YhCurrencyStat> list=null;
		Map<String, Object> totalSum=new HashMap<String, Object>();
		Integer actUserSum=0;
		Integer consumeCntSum=0;
		Double avgPerUserConsumeSum=0.0;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectCurConsUserTimesList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =yhCurrencyStatService.selectCurConsUserTimesList(searchData);
			
			if(!list.isEmpty()){
				for(YhCurrencyStat e:list){
					String currType=e.getCurType()==null?"":switchCurrType(e.getCurType().intValue());
					e.setConsumeType(currType);
					actUserSum+=e.getActUser();
					consumeCntSum+=e.getConsumeCnt();
					avgPerUserConsumeSum+=e.getAvgPerUserConsumeCnt();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		totalSum.put("actUserSum", actUserSum);
		totalSum.put("consumeCntSum", consumeCntSum);
		totalSum.put("avgPerUserConsumeSum", avgPerUserConsumeSum);
		resultMap.put("totalSum", totalSum);
		resultMap.put("outConsCurrsList", list);
		return resultMap;
	}
	/**
	 * 导出货币消耗人数，次数
	 * @Title: exportCurConsUserTimesList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-12-29 下午05:36:50
	 */
	@RequestMapping(value = "/exportCurConsUserTimesList", method = {
			RequestMethod.POST})
	public void exportCurConsUserTimesList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportCurConsUserTimesList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "货币消耗人数次数.xlsx";
		List<YhCurrencyStat> userTimeslist =yhCurrencyStatService.selectCurConsUserTimesList(searchData);
		Integer actUserSum=0;
		Integer consumeCntSum=0;
		Double avgPerUserConsumeSum=0.0;
		//汇总
		if(!userTimeslist.isEmpty()){
			for(YhCurrencyStat e:userTimeslist){
				String currType=e.getCurType()==null?"":switchCurrType(e.getCurType().intValue());
				e.setConsumeType(currType);
				actUserSum+=e.getActUser();
				consumeCntSum+=e.getConsumeCnt();
				avgPerUserConsumeSum+=e.getAvgPerUserConsumeCnt();
			}
		}
		//导出list
		if(userTimeslist != null){
			String[] title = new String[] {"日期","货币类型","消耗人数","消耗次数","人均消耗次数"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			if(!userTimeslist.isEmpty()){
				for(YhCurrencyStat e : userTimeslist){
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =e.getStatdate();
					items[count++] =e.getConsumeType();
					items[count++] =e.getActUser();
					items[count++] =e.getConsumeCnt();
					items[count++] =e.getAvgPerUserConsumeCnt();
					datalist.add(items);
				}
				//总计
				Object[] items=new Object[title.length];
				int count=0;
				items[count++]="总计";
				items[count++]=null;
				items[count++]=actUserSum;//消耗人数总和
				items[count++]=consumeCntSum;//消耗次数总和
				items[count++]=avgPerUserConsumeSum;//人均消耗次数总和
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("货币产出消耗统计");
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
	 * 打矿成功率统计
	 * @Title: selectPlaymineSuccRateList
	 * @Description: TODO
	 * @param searchData
	 * @param result
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2018-1-17 上午10:46:19
	 */
	@RequestMapping(value="/selectPlaymineSuccRateList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectPlaymineSuccRateList(@Validated @RequestBody IndependBaseSearchData searchData,BindingResult result){
		Map<String,Object> resultMap=new HashMap<String, Object>();
		List<YhPlaymineStat> playmineList=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectPlaymineSuccRateList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			if (result.hasErrors()) {
				resultMap.put("status", 0);
				resultMap.put("message", result.getAllErrors().get(0).getDefaultMessage());
				resultMap.put("playmineList", playmineList);
			}else{
				playmineList=yhPlaymineStatService.selectPlaymineSuccRateList(searchData);
				resultMap.put("status", 1);
				resultMap.put("message","成功");
				resultMap.put("playmineList", playmineList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}
	/**
	 * 导出打矿成功率
	 * @Title: exportPlaymineSuccRateList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-1-17 下午12:54:23
	 */
	@RequestMapping(value = "/exportPlaymineSuccRateList", method = {
			RequestMethod.POST})
	public void exportPlaymineSuccRateList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportPlaymineSuccRateList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "打矿成功率统计.xlsx";
		List<YhPlaymineStat> playminelist =yhPlaymineStatService.selectPlaymineSuccRateList(searchData);
		//导出list
		if(playminelist != null){
			String[] title = new String[] {"守矿情况","打矿人次","打矿成功人次","成功时进攻人次","打矿失败人次","失败时进攻人次"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhPlaymineStat e : playminelist){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getKeeptypeStr();
				items[count++] =e.getCount();
				items[count++] =e.getSuccnt();
				items[count++] =e.getSuctimes();
				items[count++] =e.getFailcnt();
				items[count++] =e.getFailtimes();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("打矿成功率统计");
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
	 * 关卡星数
	 * @Title: selectYhStageStarNumList
	 * @Description: TODO
	 * @param request
	 * @param searchData
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2018-5-16 下午12:56:28
	 */
	@RequestMapping(value="/selectYhStageStarNumList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectYhStageStarNumList(@RequestBody IndependBaseSearchData searchData,HttpServletResponse response){
		Map<String,Object> map=new HashMap<String, Object>();
		List<YhStageStat> list=new ArrayList<YhStageStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectYhStageStarNumList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			list=yhStageStatService.selectYhStageStarNumList(searchData);
			map.put("status", 1);
			map.put("msg", "成功");
			map.put("result", list);
		} catch(Exception e){
			e.printStackTrace();
			response.setStatus(500);
			map.put("status", 0);
			map.put("msg", "系统出错!");
			map.put("result",list);
		}
		return map;
	}
	/**
	 * 导出关卡星数统计
	 * @Title: exportYhStageStarNumList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-16 下午01:41:17
	 */
	@RequestMapping(value = "/exportYhStageStarNumList", method = {
			RequestMethod.POST})
	public void exportYhStageStarNumList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYhStageStarNumList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "关卡星数.xlsx";
		List<YhStageStat> stageStarNumlist =yhStageStatService.selectYhStageStarNumList(searchData);
		//导出list
		if(stageStarNumlist != null){
			String[] title = new String[] {"日期","区服","渠道","关卡ID","1星玩家数","2星玩家数","3星玩家数","1星玩家占比","2星玩家占比","3星玩家占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhStageStat e : stageStarNumlist){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getChannelName();
				items[count++] =e.getStageid();
				items[count++] =e.getOnestarcnt();
				items[count++] =e.getTwostarcnt();
				items[count++] =e.getThreestarcnt();
				items[count++] =e.getOnestarcntRate()+"%";
				items[count++] =e.getTwostarcntRate()+"%";
				items[count++] =e.getThreestarcntRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("关卡星数统计");
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
	 * 联合作战关卡玩家数情况
	 * @Title: selectUnitStageActUserNumList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2018-5-17 下午03:31:24
	 */
	@RequestMapping(value="/selectUnitStageActUserNumList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectUnitStageActUserNumList(@RequestBody IndependBaseSearchData searchData,HttpServletResponse response){
		Map<String,Object> map=new HashMap<String, Object>();
		List<YhStageStat> list=new ArrayList<YhStageStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectUnitStageActUserNumList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			list=yhStageStatService.selectUnitStageActUserNumList(searchData);
			map.put("status", 1);
			map.put("msg", "成功");
			map.put("result", list);
		} catch(Exception e){
			e.printStackTrace();
			response.setStatus(500);
			map.put("status", 0);
			map.put("msg", "系统出错,请联系管理员!");
			map.put("result",list);
		}
		return map;
	}
	/**
	 * 导出联合作战玩家数情况
	 * @Title: exportUnitStageActUserNumList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-17 下午03:37:17
	 */
	@RequestMapping(value = "/exportUnitStageActUserNumList", method = {
			RequestMethod.POST})
	public void exportUnitStageActUserNumList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportUnitStageActUserNumList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "联合作战统计.xlsx";
		List<YhStageStat> stageActUserNumlist =yhStageStatService.selectUnitStageActUserNumList(searchData);
		//导出list
		if(stageActUserNumlist != null){
			String[] title = new String[] {"日期","区服","联合作战关卡ID","符合进入玩家数量","进入人数","通关人数","通关玩家占比","通关玩家总比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhStageStat e : stageActUserNumlist){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getStageid();
				items[count++] =e.getFitActUser();
				items[count++] =e.getEnterActUser();
				items[count++] =e.getSuccessActUser();
				items[count++] =e.getSuccActUserRate()+"%";
				items[count++] =e.getSuccActUserTotalRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("联合作战统计");
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
	 * 联合作战关卡次数情况
	 * @Title: selectUnitStageCountList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2018-5-17 下午03:39:24
	 */
	@RequestMapping(value="/selectUnitStageCountList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectUnitStageCountList(@RequestBody IndependBaseSearchData searchData,HttpServletResponse response){
		Map<String,Object> map=new HashMap<String, Object>();
		List<YhStageStat> list=new ArrayList<YhStageStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectUnitStageCountList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			list=yhStageStatService.selectUnitStageCountList(searchData);
			map.put("status", 1);
			map.put("msg", "成功");
			map.put("result", list);
		} catch(Exception e){
			e.printStackTrace();
			response.setStatus(500);
			map.put("status", 0);
			map.put("msg", "系统出错,请联系管理员!");
			map.put("result",list);
		}
		return map;
	}
	/**
	 * 导出联合作战关卡次数情况
	 * @Title: exportUnitStageCountList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-17 下午03:49:09
	 */
	@RequestMapping(value = "/exportUnitStageCountList", method = {
			RequestMethod.POST})
	public void exportUnitStageCountList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportUnitStageCountList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "联合作战关卡次数.xlsx";
		List<YhStageStat> stageCountist =yhStageStatService.selectUnitStageCountList(searchData);
		//导出list
		if(stageCountist != null){
			String[] title = new String[] {"区服","联合作战关卡ID","挑战次数","胜利次数","胜利概率","平均胜利时间",
					"平均战败时间","平均战斗时间","首次通关平均等级","首次通关最低等级","军团助战次数","好友助战次数","随机玩家助战次数","助战选择占比（军团:好友:随机）"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhStageStat e : stageCountist){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getAreaid();
				items[count++] =e.getStageid();
				items[count++] =e.getCount();
				items[count++] =e.getSuccessCount();
				items[count++] =e.getSuccessRate()+"%";
				items[count++] =e.getAvgSuccessTime();
				items[count++] =e.getAvgFailTime();
				items[count++] =e.getAvgFightTime();
				items[count++] =e.getFirstAvgSuccLvl();
				items[count++] =e.getFirstMinLvl();
				items[count++] =e.getJtCnt();
				items[count++] =e.getFriendCnt();
				items[count++] =e.getRandomCnt();
				items[count++] =e.getRandomCnt();
				items[count++] =e.getJtRate()+"%:"+e.getFriendRate()+"%:"+e.getRandomRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("联合作战关卡次数");
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
	 * 演习关卡玩家数情况
	 * @Title: selectActingStageActUserNumList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2018-5-17 下午03:53:03
	 */
	@RequestMapping(value="/selectActingStageActUserNumList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectActingStageActUserNumList(@RequestBody IndependBaseSearchData searchData,HttpServletResponse response){
		Map<String,Object> map=new HashMap<String, Object>();
		List<YhStageStat> list=new ArrayList<YhStageStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectActingStageActUserNumList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			list=yhStageStatService.selectActingStageActUserNumList(searchData);
			map.put("status", 1);
			map.put("msg", "成功");
			map.put("result", list);
		} catch(Exception e){
			e.printStackTrace();
			response.setStatus(500);
			map.put("status", 0);
			map.put("msg", "系统出错,请联系管理员!");
			map.put("result",list);
		}
		return map;
	}
	/**
	 * 导出演习关卡玩家数情况
	 * @Title: exportActingStageActUserNumList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-17 下午04:00:53
	 */
	@RequestMapping(value = "/exportActingStageActUserNumList", method = {
			RequestMethod.POST})
	public void exportActingStageActUserNumList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportActingStageActUserNumList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "演习统计玩家数情况.xlsx";
		List<YhStageStat> stageActUserList =yhStageStatService.selectActingStageActUserNumList(searchData);
		//导出list
		if(stageActUserList != null){
			String[] title = new String[] {"日期","区服","联合作战关卡ID","符合进入玩家数量","进入人数","通关人数","通关玩家占比","通关玩家总比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhStageStat e : stageActUserList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getStageid();
				items[count++] =e.getFitActUser();
				items[count++] =e.getEnterActUser();
				items[count++] =e.getSuccessActUser();
				items[count++] =e.getSuccActUserRate()+"%";
				items[count++] =e.getSuccActUserTotalRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("演习统计玩家数情况");
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
	 * 演习关卡次数情况
	 * @Title: selectActingStageCountList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2018-5-17 下午04:02:02
	 */
	@RequestMapping(value="/selectActingStageCountList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectActingStageCountList(@RequestBody IndependBaseSearchData searchData,HttpServletResponse response){
		Map<String,Object> map=new HashMap<String, Object>();
		List<YhStageStat> list=new ArrayList<YhStageStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectActingStageCountList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			list=yhStageStatService.selectActingStageCountList(searchData);
			map.put("status", 1);
			map.put("msg", "成功");
			map.put("result", list);
		} catch(Exception e){
			e.printStackTrace();
			response.setStatus(500);
			map.put("status", 0);
			map.put("msg", "系统出错,请联系管理员!");
			map.put("result",list);
		}
		return map;
	}
	
	/**
	 * 导出演习统计关卡次数情况
	 * @Title: exportActingStageCountList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-17 下午04:03:50
	 */
	@RequestMapping(value = "/exportActingStageCountList", method = {
			RequestMethod.POST})
	public void exportActingStageCountList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportActingStageCountList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "演习统计关卡次数.xlsx";
		List<YhStageStat> stageCountist =yhStageStatService.selectUnitStageCountList(searchData);
		//导出list
		if(stageCountist != null){
			String[] title = new String[] {"区服","联合作战关卡ID","挑战次数","胜利次数","胜利概率","平均胜利时间",
					"平均战败时间","平均战斗时间","首次通关平均等级","首次通关最低等级"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhStageStat e : stageCountist){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getAreaid();
				items[count++] =e.getStageid();
				items[count++] =e.getCount();
				items[count++] =e.getSuccessCount();
				items[count++] =e.getSuccessRate()+"%";
				items[count++] =e.getAvgSuccessTime();
				items[count++] =e.getAvgFailTime();
				items[count++] =e.getAvgFightTime();
				items[count++] =e.getFirstAvgSuccLvl();
				items[count++] =e.getFirstMinLvl();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("演习统计关卡次数");
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
	 * 关卡统计排重
	 * @Title: selectYhStageDisList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<YhStageStat>
	 * @author hwj
	 * @date 2018-06-01 下午01:35:11
	 */
	@RequestMapping(value="/selectYhStageDisList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhStageStat> selectYhStageDisList(@RequestBody IndependBaseSearchData searchData){
		List<YhStageStat> list=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectYhStageDisList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =yhStageStatService.selectYhStageDisList(searchData);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("{}",e.getMessage());
		}
		return list;
	}
	/**导出关卡统计排重
	 * 
	 * @Title: exportYhStageDisList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-06-01 下午01:35:11
	 */
	@RequestMapping(value = "/exportYhStageDisList", method = {
			RequestMethod.POST})
	public void exportYhStageDisList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYhStageDisList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "关卡统计排重.xlsx";
		List<YhStageStat> stageList =yhStageStatService.selectYhStageDisList(searchData);
		//
		if(stageList != null){
			String[] title = new String[] {"日期","渠道","关卡ID","符合进入玩家数量","进入人数","通关人数","通关玩家占比","通关玩家总比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhStageStat e : stageList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getChannelName();
				items[count++] =e.getStageid();
				items[count++] =e.getFitActUser();
				items[count++] =e.getEnterActUser();
				items[count++] =e.getSuccessActUser();
				items[count++] =e.getSuccActUserRate()+"%";
				items[count++] =e.getSuccActUserTotalRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("关卡统计排重");
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
	 * 关卡数据排重
	 * @Title: selectYhStageDataDisList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<YhStageStat>
	 * @author hwj
	 * @date 2018-06-01 下午01:35:11
	 */
	@RequestMapping(value="/selectYhStageDataDisList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhStageStat> selectYhStageDataDisList(@RequestBody IndependBaseSearchData searchData){
		List<YhStageStat> list=null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectYhStageDataDisList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list =yhStageStatService.selectYhStageDataDisList(searchData);
			if(list!=null&&!list.isEmpty()){
				for(YhStageStat e:list){
					e.setAvgSuccessTimeStr(e.getAvgSuccessTime()==null?null:DateUtil.toLocalTimeDesc(e.getAvgSuccessTime().longValue()));
					e.setAvgFailTimeStr(e.getAvgFailTime()==null?null:DateUtil.toLocalTimeDesc(e.getAvgFailTime().longValue()));
					e.setAvgFightTimeStr(e.getAvgFightTime()==null?null:DateUtil.toLocalTimeDesc(e.getAvgFightTime().longValue()));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("{}",e.getMessage());
		}
		return list;
	}
	/**
	 * 导出关卡数据排重
	 * @Title: exportYhStageDataDisList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-06-01 下午01:35:11
	 */
	@RequestMapping(value = "/exportYhStageDataDisList", method = {
			RequestMethod.POST})
	public void exportYhStageDataDisList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYhStageDataDisList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "关卡数据排重.xlsx";
		List<YhStageStat> stageList =yhStageStatService.selectYhStageDataDisList(searchData);
		if(stageList!=null&&!stageList.isEmpty()){
			for(YhStageStat e:stageList){
				e.setAvgSuccessTimeStr(e.getAvgSuccessTime()==null?null:DateUtil.toLocalTimeDesc(e.getAvgSuccessTime().longValue()));
				e.setAvgFailTimeStr(e.getAvgFailTime()==null?null:DateUtil.toLocalTimeDesc(e.getAvgFailTime().longValue()));
				e.setAvgFightTimeStr(e.getAvgFightTime()==null?null:DateUtil.toLocalTimeDesc(e.getAvgFightTime().longValue()));
			}
		}
		//
		if(stageList != null){
			String[] title = new String[] {"渠道","关卡ID","挑战次数","胜利次数","胜利概率","平均胜利时间","平均战败时间","平均战斗时间","首次通关平均等级","首次通关最低等级"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhStageStat e : stageList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getChannelName();
				items[count++] =e.getStageid();
				items[count++] =e.getCount();
				items[count++] =e.getSuccessCount();
				items[count++] =e.getSuccessRate()+"%";
				items[count++] =e.getAvgSuccessTimeStr();
				items[count++] =e.getAvgFightTimeStr();
				items[count++] =e.getAvgFightTimeStr();
				items[count++] =e.getFirstAvgSuccLvl();
				items[count++] =e.getFirstMinLvl();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("关卡数据排重");
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
	 * 银河关卡星数排重
	 * @Title: selectYhStageStarNumDisList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @return
	 * List<YhStageStat>
	 * @author hwj
	 * @date 2018-6-1 上午10:20:45
	 */
	@RequestMapping(value="/selectYhStageStarNumDisList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhStageStat> selectYhStageStarNumDisList(@RequestBody IndependBaseSearchData searchData,HttpServletResponse response){
		List<YhStageStat> list=new ArrayList<YhStageStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectYhStageStarNumDisList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			list=yhStageStatService.selectYhStageStarNumDisList(searchData);
		} catch(Exception e){
			e.printStackTrace();
			response.setStatus(500);
		}
		return list;
	}
	/**
	 * 导出银河关卡星数排重
	 * @Title: exportYhStageStarNumDisList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-6-1 上午10:21:21
	 */
	@RequestMapping(value = "/exportYhStageStarNumDisList", method = {
			RequestMethod.POST})
	public void exportYhStageStarNumDisList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportYhStageStarNumDisList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "关卡星数排重.xlsx";
		List<YhStageStat> stageStarNumlist =yhStageStatService.selectYhStageStarNumDisList(searchData);
		//导出list
		if(stageStarNumlist != null){
			String[] title = new String[] {"日期","区服","渠道","关卡ID","1星玩家数","2星玩家数","3星玩家数","1星玩家占比","2星玩家占比","3星玩家占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhStageStat e : stageStarNumlist){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getChannelName();
				items[count++] =e.getStageid();
				items[count++] =e.getOnestarcnt();
				items[count++] =e.getTwostarcnt();
				items[count++] =e.getThreestarcnt();
				items[count++] =e.getOnestarcntRate()+"%";
				items[count++] =e.getTwostarcntRate()+"%";
				items[count++] =e.getThreestarcntRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("关卡星数排重");
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
