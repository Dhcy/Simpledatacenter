package com.good.plat.datacenter.datastat.controller.balanceplat;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.datastat.entity.balanceplat.UscAdvertIncome;
import com.good.plat.datacenter.datastat.service.balanceplat.UscVideoAdvertiserService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;
@Controller
@RequestMapping(value="/uscVideoAdvertiser")
public class UscVideoAdvertiserController extends BaseController {
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private UscVideoAdvertiserService uscVideoAdvertiserService;
	/**
	 * 视频广告商列表
	 * @Title: selectUscVideoAdvertiserList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<UscAdvertIncome>
	 * @author hwj
	 * @date 2018-2-28 下午05:36:15
	 */
	@RequestMapping(value="/selectUscVideoAdvertiserList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<UscAdvertIncome> selectUscVideoAdvertiserList(
			@RequestBody BaseSearchData searchData,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectUscVideoAdvertiserList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<UscAdvertIncome> list=uscVideoAdvertiserService.selectUscVideoAdvertiserList(searchData);
		return list;
	}
	/**
	 * 添加视频广告商
	 * @Title: addUscVideoAdvertiser
	 * @Description: TODO
	 * @param searchData
	 * @param request
	 * @param result
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2018-2-28 下午06:12:09
	 */
	@RequestMapping(value="/addUscVideoAdvertiser",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public BaseMessage addUscVideoAdvertiser(@Validated @RequestBody UscAdvertIncome searchData,HttpServletRequest request,BindingResult result)throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "addUscVideoAdvertiser";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		BaseMessage msg=new BaseMessage();
		if (result.hasErrors()) {
			msg.setStatus(0);
			msg.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return msg;
		} else {
			msg=uscVideoAdvertiserService.insertUscVideoAdvertiser(searchData);
		}
		return msg;
	}
	/**
	 * 修改视频广告商
	 * @Title: updateUscVideoAdvertiser
	 * @Description: TODO
	 * @param searchData
	 * @param request
	 * @param result
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2018-2-28 下午06:18:35
	 */
	@RequestMapping(value="/updateUscVideoAdvertiser",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public BaseMessage updateUscVideoAdvertiser(@Validated @RequestBody UscAdvertIncome searchData,HttpServletRequest request,BindingResult result)throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "updateUscVideoAdvertiser";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		BaseMessage msg=new BaseMessage();
		if (result.hasErrors()) {
			msg.setStatus(0);
			msg.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return msg;
		} else {
			msg=uscVideoAdvertiserService.updateByPrimaryKeySelective(searchData);
		}
		return msg;
	}
	/**
	 * 删除视频广告商
	 * @Title: deleteUscVideoAdvertiser
	 * @Description: TODO
	 * @param searchData
	 * @param request
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2018-2-28 下午06:23:01
	 */
	@RequestMapping(value="/deleteUscVideoAdvertiser",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public BaseMessage deleteUscVideoAdvertiser(@RequestBody UscAdvertIncome searchData,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "deleteUscVideoAdvertiser";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		BaseMessage msg=new BaseMessage();
		msg=uscVideoAdvertiserService.deleteByPrimaryKey(searchData.getId());
		return msg;
	}
	/**
	 * 获取视频广告商
	 * @Title: getVideoAdvertiser
	 * @Description: TODO
	 * @param searchData
	 * @param request
	 * @return
	 * @throws Exception
	 * UscAdvertIncome
	 * @author hwj
	 * @date 2018-3-1 上午09:48:49
	 */
	@RequestMapping(value="/getVideoAdvertiser",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public UscAdvertIncome getVideoAdvertiser(@RequestBody UscAdvertIncome searchData,HttpServletRequest request) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "getVideoAdvertiser";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		UscAdvertIncome advertiser=uscVideoAdvertiserService.getVideoAdvertiser(searchData.getId());
		return advertiser;
	}
	

}
