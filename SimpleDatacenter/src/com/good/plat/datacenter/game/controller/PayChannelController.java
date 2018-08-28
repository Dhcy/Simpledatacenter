package com.good.plat.datacenter.game.controller;

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
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.game.entity.PayChannel;
import com.good.plat.datacenter.game.service.impl.PayChannelServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

@Controller
@RequestMapping("/payChannel")
public class PayChannelController extends BaseController{

	public PayChannelController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private PayChannelServiceImpl payChannelService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	
	@RequestMapping(value = "/payChannelList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<PayChannel> payChannelList() throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "payChannelList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<PayChannel> payChannelList = payChannelService.findPayChannelList();
		return payChannelList;
	}
	
	
	@RequestMapping(value = "/deletePayChannel", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage deletePayChannel(@RequestBody PayChannel channel) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "deletePayChannel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, channel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = payChannelService.deletePayChannel(channel.getId());

		return mess;
	}
	
	@RequestMapping(value = "/addPayChannel", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage addPayChannel(@Validated @RequestBody PayChannel channel, 
			BindingResult result) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "addPayChannel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, channel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		BaseMessage mess = new BaseMessage();
		if (result.hasErrors()) {
			mess.setStatus(0);
			mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return mess;
		} else {
			mess = payChannelService.addPayChannel(channel);
		}

		return mess;
	}
	
	
	@RequestMapping(value = "/getPayChannel", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public PayChannel getPayChannel(@Validated @RequestBody PayChannel channel) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "getPayChannel";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, channel);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		PayChannel payChannel = payChannelService.findPayChannelByChannelId(channel.getChannelid());

		return payChannel;
	}
	
	

}
