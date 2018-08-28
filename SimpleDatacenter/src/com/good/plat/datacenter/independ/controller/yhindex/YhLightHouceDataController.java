package com.good.plat.datacenter.independ.controller.yhindex;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.independ.entity.yhindex.YhLightHouceData;
import com.good.plat.datacenter.independ.entity.yhindex.YhLoginStat;
import com.good.plat.datacenter.independ.service.yhindex.YhLightHouceDataService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;
@Controller
@RequestMapping(value="/yhLightHouceData")
public class YhLightHouceDataController extends BaseController {
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private YhLightHouceDataService yhLightHouceDataService;
	/**
	 * 一天内各时间段的每个环节失败率
	 * @Title: selectPerLinkLoginFailRateList
	 * @Description: TODO
	 * @param request
	 * @param searchData
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2018-5-14 下午03:39:51
	 */
	@RequestMapping(value="/selectPerLinkLoginFailRateList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectPerLinkLoginFailRateList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<YhLightHouceData> list=new ArrayList<YhLightHouceData>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectPerLinkLoginFailRateList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			list=yhLightHouceDataService.selectPerLinkCntForHour(searchData);
			map.put("status", 1);
			map.put("msg", "成功");
			map.put("result", list);
		} catch(Exception e){
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "失败");
			map.put("result",list);
		}
		return map;
	}
	/**
	 * 登录成功转化率
	 * @Title: selectLoginSucceedRateList
	 * @Description: TODO
	 * @param request
	 * @param searchData
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2018-5-14 下午03:45:41
	 */
	@RequestMapping(value="/selectLoginSucceedRateList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectLoginSucceedRateList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<YhLoginStat> list=new ArrayList<YhLoginStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectLoginSucceedRateList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			list=yhLightHouceDataService.selectLoginSuccessTransformRate(searchData);
			map.put("status", 1);
			map.put("msg", "成功");
			map.put("result", list);
		} catch(Exception e){
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "失败");
			map.put("result",list);
		}
		return map;
	}
	/**
	 * fps区间分布占比
	 * @Title: selectFpsDistributeRateList
	 * @Description: TODO
	 * @param request
	 * @param searchData
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2018-5-15 下午01:55:37
	 */
	@RequestMapping(value="/selectFpsDistributeRateList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectFpsDistributeRateList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<YhLightHouceData> list=new ArrayList<YhLightHouceData>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectFpsDistributeRateList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			list=yhLightHouceDataService.selectFpsDistributeRateList(searchData);
			map.put("status", 1);
			map.put("msg", "成功");
			map.put("result", list);
		} catch(Exception e){
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "失败");
			map.put("result",list);
		}
		return map;
	}
	/**
	 * 机型fps区间分布占比
	 * @Title: selectSystemHFpsDistributeRateList
	 * @Description: TODO
	 * @param request
	 * @param searchData
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2018-5-15 下午01:59:38
	 */
	@RequestMapping(value="/selectSystemHFpsDistributeRateList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectSystemHFpsDistributeRateList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<YhLightHouceData> list=new ArrayList<YhLightHouceData>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectSystemHFpsDistributeRateList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			list=yhLightHouceDataService.selectSystemHFpsDistributeRateList(searchData);
			map.put("status", 1);
			map.put("msg", "成功");
			map.put("result", list);
		} catch(Exception e){
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "失败");
			map.put("result",list);
		}
		return map;
	}
	/**
	 * 按时间查询平均延迟时长
	 * @Title: selectAveDelayTimeByHour
	 * @Description: TODO
	 * @param request
	 * @param searchData
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2018-5-15 下午02:01:36
	 */
	@RequestMapping(value="/selectAveDelayTimeByHour",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectAveDelayTimeByHour(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<YhLightHouceData> list=new ArrayList<YhLightHouceData>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectAveDelayTimeByHour";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			list=yhLightHouceDataService.selectAvgDelayTimeByHour(searchData);
			map.put("status", 1);
			map.put("msg", "成功");
			map.put("result", list);
		} catch(Exception e){
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "失败");
			map.put("result",list);
		}
		return map;
	}
	/**
	 * 按区间段查询平均延迟次数比例
	 * @Title: selectAveDelayCountByInterval
	 * @Description: TODO
	 * @param request
	 * @param searchData
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2018-5-15 下午02:03:16
	 */
	@RequestMapping(value="/selectAveDelayCountByInterval",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectAveDelayCountByInterval(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<YhLightHouceData> list=new ArrayList<YhLightHouceData>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectAveDelayCountByInterval";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			list=yhLightHouceDataService.selectAvgDelayCountByInterval(searchData);
			map.put("status", 1);
			map.put("msg", "成功");
			map.put("result", list);
		} catch(Exception e){
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "失败");
			map.put("result",list);
		}
		return map;
	}
	
	
	
	
}
