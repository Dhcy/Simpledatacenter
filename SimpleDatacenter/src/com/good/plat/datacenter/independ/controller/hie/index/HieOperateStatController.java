package com.good.plat.datacenter.independ.controller.hie.index;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.common.util.ResultUtils;
import com.good.plat.datacenter.independ.entity.hie.index.HieActorRecharge;
import com.good.plat.datacenter.independ.entity.hie.index.HieOnLine;
import com.good.plat.datacenter.independ.entity.hie.index.HieOnTimeRecharge;
import com.good.plat.datacenter.independ.entity.hie.index.HieRegistStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieSingleRecharge;
import com.good.plat.datacenter.independ.entity.hie.index.HieTaskStat;
import com.good.plat.datacenter.independ.service.hie.index.HieOperateStatService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

@Controller
@RequestMapping(value="/hieOperate")
public class HieOperateStatController {
	@Autowired
	private HieOperateStatService hieService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private  HttpServletRequest request;
	
	//任务打点
	@RequestMapping(value="/queryTaskStatList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object>queryTaskStatList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<HieTaskStat>list=new ArrayList<HieTaskStat>();
		try {
			saveLog(request, searchData, "queryTaskStatList");
			list=hieService.queryTaskStatList(searchData);
			map = ResultUtils.OK(map,list);
		} catch (Exception e) {
			e.printStackTrace();
			map = ResultUtils.Error(map);
		}
		return map;
	}
	//任务打点导出
	@RequestMapping(value = "/exportTaskStatList", method = {RequestMethod.POST})
	public void exportTaskStatList(@RequestBody BaseSearchData searchData,HttpServletResponse response)throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportTaskStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<HieTaskStat> taskList = hieService.queryTaskStatList(searchData);
		//导出list
		if(taskList != null){
			String[] title = new String[] {"任务ID","任务类型","任务名称","任务等级","接受数","完成数","平均花费时间","完成率"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(HieTaskStat e : taskList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getTaskid();
				items[count++] =e.getTasktype();
				items[count++] =e.getTaskname();
				items[count++] =e.getTasklevel();
				items[count++] =e.getAccepttotal();
				items[count++] =e.getCompletetotal();
				items[count++] =e.getAvgtime();
				items[count++] =e.getCompleterate();
				datalist.add(items);
			}
			list.add(datalist);
		}
		work(response, list, "任务打点", "任务打点.xlsx");
	}
	
	//首充等级
	@RequestMapping(value="/queryFirstRechargeStatList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object>queryRechargeStatList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<HieActorRecharge>list=new ArrayList<HieActorRecharge>();
		try {
			saveLog(request, searchData, "queryFirstRechargeStatList");
			list=hieService.queryFirstRechargeStatList(searchData);
			map = ResultUtils.OK(map,list);
		} catch (Exception e) {
			e.printStackTrace();
			map = ResultUtils.Error(map);
		}
		return map;
	}
	//首充等级导出
	@RequestMapping(value = "/exportFirstRechargeList", method = {RequestMethod.POST})
	public void exportFirstRechargeList(@RequestBody BaseSearchData searchData,HttpServletResponse response)throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportFirstRechargeList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<HieActorRecharge> rechargeList = hieService.queryFirstRechargeStatList(searchData);
		//导出list
		if(rechargeList != null){
			String[] title = new String[] {"等级","首充人数","6元","12元","30元","68元","128元","328元","648元"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(HieActorRecharge e : rechargeList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getActorlevel();
				items[count++] =e.getPersons();
				items[count++] =e.getSix();
				items[count++] =e.getTwelve();
				items[count++] =e.getThirty();
				items[count++] =e.getSe();
				items[count++] =e.getOte();
				items[count++] =e.getTte();
				items[count++] =e.getSte();
				datalist.add(items);
			}
			list.add(datalist);
		}
		work(response, list, "首冲等级", "首冲等级.xlsx");
	}
	
	//注册打点
	@RequestMapping(value="/queryRegistStatList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object>queryRegistStatList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<HieRegistStat>list=new ArrayList<HieRegistStat>();
		try {
			saveLog(request, searchData, "queryRegistStatList");
			list=hieService.queryRegistStatList(searchData);
			map = ResultUtils.OK(map,list);
		} catch (Exception e) {
			e.printStackTrace();
			map = ResultUtils.Error(map);
		}
		return map;
		
	}
	
	//单笔充值
	@RequestMapping(value="/querySingleRechargeList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object>querySingleRechargeList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<HieSingleRecharge>list=new ArrayList<HieSingleRecharge>();
		try {
			saveLog(request, searchData, "querySingleRechargeList");
			list=hieService.querySingleRechargeList(searchData);
			map = ResultUtils.OK(map,list);
		} catch (Exception e) {
			e.printStackTrace();
			map = ResultUtils.Error(map);
		}
		return map;
	}
	//单笔充值导出
	@RequestMapping(value = "/exportSingleRechargeList", method = {RequestMethod.POST})
	public void exportSingleRechargeList(@RequestBody BaseSearchData searchData,HttpServletResponse response)throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportSingleRechargeList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<HieSingleRecharge> singleList = hieService.querySingleRechargeList(searchData);
		//导出list
		if(singleList != null){
			String[] title = new String[] {"额度","充值人数","人数占比","充值次数","次数占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(HieSingleRecharge e : singleList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getMoney();
				items[count++] =e.getPersons();
				items[count++] =e.getPersonsaf();
				items[count++] =e.getRechargeCount();
				items[count++] =e.getCountaf();
				datalist.add(items);
			}
			list.add(datalist);
		}
		work(response, list, "单笔充值", "单笔充值.xlsx");
	}
	
	//按时充值
	@RequestMapping(value="/queryOnTimeRechargeList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object>queryOnTimeRechargeList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<HieOnTimeRecharge>list=new ArrayList<HieOnTimeRecharge>();
		List<HieOnTimeRecharge>listall=new ArrayList<HieOnTimeRecharge>();

		try {
			saveLog(request, searchData, "queryOnTimeRechargeList");
			list=hieService.queryOnTimeRechargeList(searchData);
			addOnTimeCount(list, listall);
			map = ResultUtils.OK(map,listall);
		} catch (Exception e) {
			e.printStackTrace();
			map = ResultUtils.Error(map);
		}
		return map;
	}
	//按时充值导出
	@RequestMapping(value = "/exportOnTimeRechargeList", method = {RequestMethod.POST})
	public void exportOnTimeRechargeList(@RequestBody BaseSearchData searchData,HttpServletResponse response)throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportOnTimeRechargeList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<HieOnTimeRecharge> onTimeList = hieService.queryOnTimeRechargeList(searchData);
		List<HieOnTimeRecharge>listall=new ArrayList<HieOnTimeRecharge>();
		addOnTimeCount(onTimeList, listall);
		//导出list
		if(listall != null){
			String[] title = new String[] {"时间段","充值人数","人数占比","充值金额","充值占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(HieOnTimeRecharge e : listall){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getDate();
				items[count++] =e.getPersons();
				items[count++] =e.getPersonsaf();
				items[count++] =e.getMoney();
				items[count++] =e.getMoneyaf();
				datalist.add(items);
			}
			list.add(datalist);
		}
		work(response, list, "按时充值", "按时充值.xlsx");
	}
	
	//按时充值
	@RequestMapping(value="/queryOnlineList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object>queryOnlineList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<HieOnLine>list=new ArrayList<HieOnLine>();
		try {
			saveLog(request, searchData, "queryOnlineList");
			list=hieService.queryOnlineList(searchData);
			map = ResultUtils.OK(map,list);
		} catch (Exception e) {
			e.printStackTrace();
			map = ResultUtils.Error(map);
		}
		return map;
	}
	
//*******************************************************************************************************************************
	//按时
	private void addOnTimeCount(List<HieOnTimeRecharge> list, List<HieOnTimeRecharge> listall) {
		HieOnTimeRecharge h1=new HieOnTimeRecharge();
		//生成24个时刻对象
		for(int i=0;i<=23;i++){
			String index;
			if(i<10){index="0"+i;}
			else{index=i+"";};
			HieOnTimeRecharge h=new HieOnTimeRecharge();
			h.setDate(index);
			h.setMoney(0.00);
			h.setMoneyaf(0.00);
			h.setPersons(0);
			h.setPersonsaf(0.00);
			listall.add(h);
		}
		if(list!=null&&!list.isEmpty()){
		//遍历赋值
for (HieOnTimeRecharge h : list) {
		String date1 = h.getDate();
		for (HieOnTimeRecharge l : listall) {
			String date2 = l.getDate();
			if(date1.equals(date2)){
				l.setMoney(h.getMoney());
				l.setMoneyaf(h.getMoneyaf());
				l.setPersons(h.getPersons());
				l.setPersonsaf(h.getPersonsaf());
			}
		}
	}
}
		//总计
		Double persons=0.00;
		Double personsaf=0.00;
		Double money=0.00;
		Double moneyaf=0.00;
		for (HieOnTimeRecharge h : list) {
			persons+=h.getPersons();
			personsaf+=h.getPersonsaf();
			money+=h.getMoney();
			moneyaf+=h.getMoneyaf();
		}
		h1.setDate("总计");
		h1.setPersons(persons.intValue());
		h1.setMoney(money);
		
		//h1.setPersonsaf(NumberUtil.ajustScale(personsaf,2));
		//h1.setMoneyaf(NumberUtil.ajustScale(moneyaf,2));
		if(list!=null&&!list.isEmpty()){
		h1.setPersonsaf(100.00);
		h1.setMoneyaf(100.00);
		}else {
			h1.setPersonsaf(0.00);
			h1.setMoneyaf(0.00);
		}
		
		listall.add(h1);
	}
	//log
	private void saveLog(HttpServletRequest request, BaseSearchData searchData,String methodName) {
		Date opDate = new Date();
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
	}
	//导出抽取
	private void work(HttpServletResponse response, List<List<Object[]>> list, String sheetNameListName,
			String filename) throws IOException {
		//
		List<String> sheetNameList = new LinkedList<String>();
		sheetNameList.add(sheetNameListName);
		Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
		ExcelGenerator.fillWorkBook(w,sheetNameList, list);
		//
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		w.write(baos);
		String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
		HTTPUtil.responseFile(response, request, contentType , filename, baos);
	}

}
