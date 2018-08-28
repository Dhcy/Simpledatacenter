package com.good.plat.datacenter.datastat.controller.index;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.datastat.entity.index.DataCompare;
import com.good.plat.datacenter.datastat.service.index.DataCompareService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;


/**
 * 数据对比
 * @ClassName: DataCompareController
 * @Description: TODO
 * @author hwj
 * @date 2017-2-17 下午05:26:30
 */
@Controller
@RequestMapping("/index/dataCompare")
public class DataCompareController {
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private DataCompareService dataCompareService;
	
	/**
	 * 新增激活和账号
	 * @Title: selectNewPlayerList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017-2-16 上午11:24:59
	 */
	@RequestMapping(value="/selectNewPlayerList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<DataCompare> selectNewPlayerList (@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectNewPlayerList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<DataCompare> list=dataCompareService.selectNewPlayerList(searchData);
		return list;
	}
	
	/***
	 * 活跃用户（dau,wau,mau）
	 * @Title: selectAUList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017-2-16 上午11:25:49
	 */
	@RequestMapping(value="/selectAUList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<DataCompare> selectAUList (@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectAUList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<DataCompare> list=dataCompareService.selectAUList(searchData);
		return list;
	}
	
	/**
	 * 收入（收入金额，充值次数）
	 * @Title: selectIncomeList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017-2-16 上午11:26:47
	 */
	@RequestMapping(value="/selectIncomeList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<DataCompare> selectIncomeList (@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectIncomeList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<DataCompare> list=dataCompareService.selectIncomeList(searchData);
		return list;
	}
	
	/**
	 * 付费玩家(新增付费玩家，总付费玩家)
	 * @Title: selectPayPlayerList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017-2-16 上午11:28:08
	 */
	@RequestMapping(value="/selectPayPlayerList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<DataCompare> selectPayPlayerList (@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPayPlayerList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<DataCompare> list=dataCompareService.selectPayPlayerList(searchData);
		return list;
	}
	
	/**
	 * 在线acu,pcu
	 * @Title: selectAcuAndPcuList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017-2-16 上午11:28:54
	 */
	@RequestMapping(value="/selectAcuAndPcuList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<DataCompare> selectAcuAndPcuList (@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPayPlayerList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<DataCompare> list=dataCompareService.selectAcuAndPcuList(searchData);
		return list;
	}
	
	/**
	 * 付费率
	 * @Title: selectPayRateList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017年2月19日 下午11:19:20
	 */
	@RequestMapping(value="/selectPayRateList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<DataCompare> selectPayRateList (@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPayRateList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<DataCompare> list=dataCompareService.selectPayRateList(searchData);
		return list;
	}
	
	/**
	 * 次日留存
	 * @Title: selectDay1RetainRateList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017年2月20日 上午12:43:49
	 */
	@RequestMapping(value="/selectDay1RetainRateList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<DataCompare> selectDay1RetainRateList (@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectDay1RetainRateList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<DataCompare> list=dataCompareService.selectDay1RetainRateList(searchData);
		return list;
	}
	
	/**
	 * acu与pcu的比
	 * @Title: selectAcuDivPcuList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017年2月20日 上午1:21:51
	 */
	@RequestMapping(value="/selectAcuDivPcuList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<DataCompare> selectAcuDivPcuList (@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectAcuDivPcuList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<DataCompare> list=dataCompareService.selectAcuDivPcuList(searchData);
		return list;
	}
	
	
	
	
	
}
