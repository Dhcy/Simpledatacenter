package com.good.plat.datacenter.game.controller;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
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
import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.datastat.entity.players.AccountChurn;
import com.good.plat.datacenter.game.entity.RechgGainWarn;
import com.good.plat.datacenter.game.service.RechgGainWarnService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;
/**
 * 充值获取警告
 * @ClassName: RechgGainWarnController
 * @Description: TODO
 * @author hwj
 * @date 2017-8-15 下午01:51:09
 */
@Controller
@RequestMapping(value="/rechgWarn")
public class RechgGainWarnController extends BaseController {
	@Autowired
	private RechgGainWarnService rechgGainWarnServiceImpl;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	/**
	 * 查找充值获取警告列表
	 * @Title: findRechgGainWarnList
	 * @Description: TODO
	 * @return
	 * List<RechgGainWarn>
	 * @author hwj
	 * @date 2017-8-15 下午02:02:04
	 */
	@RequestMapping(value = "/rechgGainWarnList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<RechgGainWarn> findRechgGainWarnList(){
		List<RechgGainWarn> list=null;
		try {
			//log
			Date opDate = new Date();
			String methodName = "findRechgGainWarnList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null);
			sysAccessLogService.saveAccessLog(accessLog);
			RechgGainWarn warn=new RechgGainWarn();
			list=rechgGainWarnServiceImpl.selectRechgGainWarnList(warn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 添加充值获取警告
	 * @Title: addRechgGainWarn
	 * @Description: TODO
	 * @param warn
	 * @return
	 * BaseMessage
	 * @author hwj
	 * @date 2017-8-15 下午03:23:15
	 */
	@RequestMapping(value = "/addRechgGainWarn", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage addRechgGainWarn(@Validated @RequestBody RechgGainWarn warn,BindingResult result){
		BaseMessage mess=null;
		try {
			//log
			Date opDate = new Date();
			String methodName = "addRechgGainWarn";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null);
			sysAccessLogService.saveAccessLog(accessLog);
			mess=new BaseMessage();
			if (result.hasErrors()) {
				mess.setStatus(0);
				mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
				return mess;
			} else {
				RechgGainWarn isExit=rechgGainWarnServiceImpl.getRechgGainWarnByGameId(warn.getGameId());
				if(isExit!=null){
					mess.setStatus(0);
					mess.setMessage("已存在要添加的游戏");
					return mess;
				}
				if(warn.getRechgTime()==null){
					warn.setRechgTime(-1);
				}
				if(warn.getRechgMoney()==null){
					warn.setRechgMoney(-1);
				}
				if(warn.getGainTime()==null){
					warn.setGainTime(-1);
				}
				if(warn.getWarmThreShold()==null){
					warn.setWarmThreShold(100);
				}
				mess=rechgGainWarnServiceImpl.addRechgGainWarn(warn);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mess;
	}
	
	/**
	 * 删除充值获取警告
	 * @Title: deleteRechgGainWarn
	 * @Description: TODO
	 * @param warn
	 * @return
	 * BaseMessage
	 * @author hwj
	 * @date 2017-8-15 下午03:26:09
	 */
	@RequestMapping(value = "/deleteRechgGainWarn", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage deleteRechgGainWarn(@RequestBody RechgGainWarn warn){
		BaseMessage msg=null;
		try {
			//log
			Date opDate = new Date();
			String methodName = "deleteRechgGainWarn";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null);
			sysAccessLogService.saveAccessLog(accessLog);
			msg=new BaseMessage();
			msg=rechgGainWarnServiceImpl.deleteRechgGainWarn(warn.getId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 编辑充值获取警告
	 * @Title: editRechgGainWarn
	 * @Description: TODO
	 * @param warn
	 * @return
	 * BaseMessage
	 * @author hwj
	 * @date 2017-8-15 下午03:40:48
	 */
	@RequestMapping(value = "/editRechgGainWarn", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage editRechgGainWarn(@Validated @RequestBody RechgGainWarn warn,BindingResult result){
		BaseMessage mess=null;
		try {
			//log
			Date opDate = new Date();
			String methodName = "editRechgGainWarn";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null);
			sysAccessLogService.saveAccessLog(accessLog);
			
			mess=new BaseMessage();
			if (result.hasErrors()) {
				mess.setStatus(0);
				mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
				return mess;
			} else {
				if(warn.getRechgTime()==null){
					warn.setRechgTime(-1);
				}
				if(warn.getRechgMoney()==null){
					warn.setRechgMoney(-1);
				}
				if(warn.getGainTime()==null){
					warn.setGainTime(-1);
				}
				if(warn.getWarmThreShold()==null){
					warn.setWarmThreShold(100);
				}
				mess=rechgGainWarnServiceImpl.editRechgGainWarn(warn);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mess;
	}
	/**
	 * 获取充值警告
	 * @Title: getRechgGainWarn
	 * @Description: TODO
	 * @param warn
	 * @return
	 * RechgGainWarn
	 * @author hwj
	 * @date 2017-8-15 下午05:54:30
	 */
	@RequestMapping(value = "/getRechgGainWarn", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public RechgGainWarn getRechgGainWarn(@RequestBody RechgGainWarn warn){
		RechgGainWarn rechgWarn=null;
		try {
			//log
			Date opDate = new Date();
			String methodName = "getRechgGainWarn";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null);
			sysAccessLogService.saveAccessLog(accessLog);
			rechgWarn=rechgGainWarnServiceImpl.getRechgGainWarn(warn.getId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rechgWarn;
	}
	/**
	 * 导出充值获取告警列表
	 * @Title: exportRechgGainWarnList
	 * @Description: TODO
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-8-17 下午01:05:03
	 */
	@RequestMapping(value="/exportRechgGainWarnList",method={RequestMethod.POST})
	public void exportRechgGainWarnList(HttpSession session,HttpServletResponse response) throws Exception{
		//log
		sysAccessLogService.log(request, getClass(), "exportRechgGainWarnList", new Date(), null);		
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "充值获取告警.xlsx";
		//
		RechgGainWarn warn=new RechgGainWarn();
		List<RechgGainWarn> rechgWarnList =rechgGainWarnServiceImpl.selectRechgGainWarnList(warn);
		//
		if(rechgWarnList != null){
			String[] title = new String[] {"游戏","充值次数","总充值金额","金币获取次数","金币获取总额","告警阀值","充值比例","告警邮箱"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(RechgGainWarn e : rechgWarnList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getGameName();
				items[count++] = e.getRechgTime();
				items[count++] = e.getRechgMoney();
				items[count++] = e.getGainTime();
				items[count++] = e.getGainCount();
				items[count++] = e.getWarmThreShold();
				items[count++] =e.getRechgRate();
				items[count++] = e.getMsgMail();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("充值获取告警");
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
