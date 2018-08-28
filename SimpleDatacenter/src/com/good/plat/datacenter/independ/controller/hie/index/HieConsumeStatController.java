package com.good.plat.datacenter.independ.controller.hie.index;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
import com.good.plat.datacenter.independ.entity.hie.index.HieEctypeStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieLottoStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieMoneyStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieShoppingStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieThingStat;
import com.good.plat.datacenter.independ.service.hie.index.HieConsumeStatService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

@Controller
@RequestMapping(value="/hieConsume")
public class HieConsumeStatController {
	@Resource
	private HieConsumeStatService hieCountStatService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private  HttpServletRequest request;
	
	//砖石统计
	@RequestMapping(value="/queryMoneyStatList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object>queryMoneyStatList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<HieMoneyStat>list=new ArrayList<HieMoneyStat>();
		try {
			saveLog(request, searchData,"queryMoneyStatList");
			list=hieCountStatService.queryMoneyStatList(searchData);
			//添加总计
			addDiamondCount(list);
			map = ResultUtils.OK(map,list);
		} catch (Exception e) {
			e.printStackTrace();
			map = ResultUtils.Error(map);
		}
		return map; 
		
	}
	//钻石统计导出
	@RequestMapping(value = "/exportMoneyStatList", method = {RequestMethod.POST})
	public void exportMoneyStatList(@RequestBody BaseSearchData searchData,HttpServletResponse response)throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportMoneyStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<HieMoneyStat> moneyList = hieCountStatService.queryMoneyStatList(searchData);
		//添加"总计"
		addDiamondCount(moneyList);
		//导出list
		if(moneyList != null){
			String[] title = new String[] {"类型","货币总数","次数","人数","货币占比","人数占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(HieMoneyStat e : moneyList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getApproach();
				items[count++] =e.getMoneynum();
				items[count++] =e.getNum();
				items[count++] =e.getPersonnum();
				items[count++] =e.getMoneypre();
				items[count++] =e.getPersonpre();
				datalist.add(items);
			}
			list.add(datalist);
		}
		work(response, list, "钻石统计", "钻石统计.xlsx");
	}

	
	//金币统计
	@RequestMapping(value="/queryGoldStatList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object>queryGoldStatList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<HieMoneyStat>list=new ArrayList<HieMoneyStat>();
		try {
			saveLog(request, searchData,"queryGoldStatList");
			list=hieCountStatService.queryGoldStatList(searchData);
			//添加总计
			addDiamondCount(list);
			map = ResultUtils.OK(map,list);
		} catch (Exception e) {
			e.printStackTrace();
			map = ResultUtils.Error(map);
		}
		return map;
		
	}
	//金币统计导出
	@RequestMapping(value = "/exportGoldStatList", method = {RequestMethod.POST})
	public void exportGoldStatList(@RequestBody BaseSearchData searchData,HttpServletResponse response)throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportGoldStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<HieMoneyStat> moneyList = hieCountStatService.queryGoldStatList(searchData);
		//添加"总计"
		addDiamondCount(moneyList);
		//导出list
		if(moneyList != null){
			String[] title = new String[] {"类型","货币总数","次数","人数","货币占比","人数占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(HieMoneyStat e : moneyList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getApproach();
				items[count++] =e.getMoneynum();
				items[count++] =e.getNum();
				items[count++] =e.getPersonnum();
				items[count++] =e.getMoneypre();
				items[count++] =e.getPersonpre();
				datalist.add(items);
			}
			list.add(datalist);
		}
		work(response, list, "金币统计", "金币统计.xlsx");
	}
	
	
	//抽奖统计
	@RequestMapping(value="/queryLottoStatList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object>queryLottoStatList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<HieLottoStat>list=new ArrayList<HieLottoStat>();
		try {
			saveLog(request, searchData,"queryLottoStatList");
			list=hieCountStatService.queryLottoStatList(searchData);
			map = ResultUtils.OK(map,list);
		} catch (Exception e) {
			e.printStackTrace();
			map=ResultUtils.Error(map);
		}
		return map;
	}
	//抽奖统计导出
	@RequestMapping(value = "/exportLottoStatList", method = {RequestMethod.POST})
	public void exportLottoStatList(@RequestBody BaseSearchData searchData,HttpServletResponse response)throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportLottoStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<HieLottoStat> lottoList = hieCountStatService.queryLottoStatList(searchData);
		//导出list
		if(lottoList != null){
			String[] title = new String[] {"渠道","区服","内容","类型","数量"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(HieLottoStat e : lottoList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getChannelSimName();
				items[count++] =e.getName();
				items[count++] =e.getItemname();
				items[count++] =e.getLotterytype();
				items[count++] =e.getItemcount();
				datalist.add(items);
			}
			list.add(datalist);
		}
		work(response, list, "抽奖统计", "抽奖统计.xlsx");
	}
	//副本统计
	@RequestMapping(value="/queryEctypeStatList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object>queryEctypeStatList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<HieEctypeStat>list=new ArrayList<HieEctypeStat>();
		try {
			saveLog(request, searchData,"queryEctypeStatList");
			list=hieCountStatService.queryEctypeStatList(searchData);
			map = ResultUtils.OK(map,list);
		} catch (Exception e) {
			e.printStackTrace();
			map = ResultUtils.Error(map);
		}
		return map;
		
	}
	
	//副本统计导出
	@RequestMapping(value = "/exportEctypeStatList", method = {RequestMethod.POST})
	public void exportEctypeStatList(@RequestBody BaseSearchData searchData,HttpServletResponse response)throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportEctypeStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<HieEctypeStat> ectypeList = hieCountStatService.queryEctypeStatList(searchData);
		//导出list
		if(ectypeList != null){
			String[] title = new String[] {"副本名称","进入次数","通关次数","通过率","参与人数","通关人数","平均用时","蕾西亚","红霞","维奥拉","雪花莲","梅忒黛","玛利亚裘"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(HieEctypeStat e : ectypeList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getEctypename();
				items[count++] =e.getAccessnum();
				items[count++] =e.getPassnum();
				items[count++] =e.getPasspro();
				items[count++] =e.getAccesspersonnum();
				items[count++] =e.getPasspersonnum();
				items[count++] =e.getAvgtime();
				items[count++] =e.getLxynum();
				items[count++] =e.getHxnum();
				items[count++] =e.getWalnum();
				items[count++] =e.getXhlnum();
				items[count++] =e.getMxdnum();
				items[count++] =e.getMlyqnum();
				datalist.add(items);
			}
			list.add(datalist);
		}
		work(response, list, "副本统计", "副本统计.xlsx");
	}
	
	
	//商城统计
	@RequestMapping(value="/queryShoppingStatList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object>queryShoppingStatList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<HieShoppingStat>list=new ArrayList<HieShoppingStat>();
		
		try {
			saveLog(request, searchData,"queryShoppingStatList");
			list=hieCountStatService.queryShoppingStatList(searchData);
			addShoppingCount(list);
			map = ResultUtils.OK(map,list);
		} catch (Exception e) {
			e.printStackTrace();
			map = ResultUtils.Error(map);
		}
		return map;
		
	}
	//商城统计导出
	@RequestMapping(value = "/exportShoppingStatList", method = {RequestMethod.POST})
	public void exportShoppingStatList(@RequestBody BaseSearchData searchData,HttpServletResponse response)throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportShoppingStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<HieShoppingStat> shoppingList = hieCountStatService.queryShoppingStatList(searchData);
		addShoppingCount(shoppingList);
		//导出list
		if(shoppingList != null){
			String[] title = new String[] {"商品名称","销售总额","销售数量","购买人数","销售占比","人数占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(HieShoppingStat e : shoppingList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getName();
				items[count++] =e.getTotalsales();
				items[count++] =e.getSalesnum();
				items[count++] =e.getPersonnum();
				items[count++] =e.getSalesaf();
				items[count++] =e.getPeraf();
				datalist.add(items);
			}
			list.add(datalist);
		}
		work(response, list, "商城统计", "商城统计.xlsx");
	}
	
	

	//道具统计
	@RequestMapping(value="/queryThingStatList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object>queryThingStatList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<HieThingStat>list=new ArrayList<HieThingStat>();

		try {
			saveLog(request, searchData,"queryThingStatList");
			list=hieCountStatService.queryThingStatList(searchData);
			addThingCount(list);
			map = ResultUtils.OK(map,list);
		} catch (Exception e) {
			e.printStackTrace();
			map = ResultUtils.Error(map);
		}
		return map;
		
	}
	
	//道具统计导出
	@RequestMapping(value = "/exportThingStatList", method = {RequestMethod.POST})
	public void exportThingStatList(@RequestBody BaseSearchData searchData,HttpServletResponse response)throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportThingStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<HieThingStat> thingList = hieCountStatService.queryThingStatList(searchData);
		addThingCount(thingList);
		//导出list
		if(thingList != null){
			String[] title = new String[] {"道具名","道具总数","次数","人数"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(HieThingStat e : thingList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getItemname();
				items[count++] =e.getItemcount();
				items[count++] =e.getCount();
				items[count++] =e.getPersonnum();
				datalist.add(items);
			}
			list.add(datalist);
		}
		work(response, list, "道具统计", "道具统计.xlsx");
	}
//*******************************************************************************************************************************
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
	//钻石、金币
	private void addDiamondCount(List<HieMoneyStat> list) {
		HieMoneyStat h1=new HieMoneyStat();
		if(list!=null&&!list.isEmpty()){
			Integer totalMoney=0;
			Integer totalCount=0;
			Integer totalPerson=0;
			Double totalMoneyP=0.00;
			Double totalPersonP=0.00;
		for (HieMoneyStat h : list) {
			totalMoney+=h.getMoneynum();
			totalCount+=h.getNum();
			totalPerson+=h.getPersonnum();
			totalMoneyP+=h.getMoneypre();
			totalPersonP+=h.getPersonpre();
		}
		h1.setApproach("总计");
		h1.setMoneynum(totalMoney);
		h1.setNum(totalCount);
		h1.setPersonnum(totalPerson);
		
		//h1.setMoneypre(totalMoneyP);
		//h1.setPersonpre(totalPersonP);
		h1.setMoneypre(100);
		h1.setPersonpre(100);
		
		list.add(h1);
		}
	}
	//道具
	private void addThingCount(List<HieThingStat> list) {
		HieThingStat h1=new HieThingStat();
		if(list!=null&&!list.isEmpty()){
			Integer totalItems=0;
			Integer totalCount=0;
			Integer totalPersons=0;
			for (HieThingStat h : list) {
				totalItems+=h.getItemcount();
				totalCount+=h.getCount();
				totalPersons+=h.getPersonnum();
			}
			h1.setItemname("总计");
			h1.setItemcount(totalItems);
			h1.setCount(totalCount);
			h1.setPersonnum(totalPersons);
			list.add(h1);
		}
	}
	//商城
	private void addShoppingCount(List<HieShoppingStat> list) {
		HieShoppingStat h1=new HieShoppingStat();
		if(list!=null&&!list.isEmpty()){
			Integer totalMoney=0;
			Integer totalCount=0;
			Integer totalPersons=0;
			Double totalSaleP=0.00;
			Double totalPersonP=0.00;
			for (HieShoppingStat h : list) {
				totalMoney+=h.getTotalsales();
				totalCount+=h.getSalesnum();
				totalPersons+=h.getPersonnum();
				totalSaleP+=h.getSalesaf();
				totalPersonP+=h.getPeraf();
			}
			h1.setName("总计");
			h1.setTotalsales(totalMoney);
			h1.setSalesnum(totalCount);
			h1.setPersonnum(totalPersons);
			
			//h1.setSalesaf(totalSaleP);
			//h1.setPeraf(totalPersonP);
			h1.setSalesaf(100);
			h1.setPeraf(100);
			
			list.add(h1);
		}
	}

}
