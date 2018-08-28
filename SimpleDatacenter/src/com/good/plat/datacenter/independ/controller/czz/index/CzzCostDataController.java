package com.good.plat.datacenter.independ.controller.czz.index;

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
import com.good.plat.datacenter.independ.entity.czz.index.CzzDiamond;
import com.good.plat.datacenter.independ.entity.czz.index.CzzEctypeStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieLottoStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieShoppingStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieThingStat;
import com.good.plat.datacenter.independ.service.ccz.index.CzzCostDataService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

@Controller
@RequestMapping(value="/czzCost")
public class CzzCostDataController {
	@Resource
	private CzzCostDataService czzCostDataService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private  HttpServletRequest request;
	
	//货币消耗&存量统计
	@RequestMapping(value="/queryMoneyStatList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object>queryMoneyStatList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<CzzDiamond>list=new ArrayList<CzzDiamond>();
		try {
			saveLog(request, searchData,"queryMoneyStatList");
			list=czzCostDataService.queryMoneyStatList(searchData);
			map = ResultUtils.OK(map,list);
		} catch (Exception e) {
			e.printStackTrace();
			map = ResultUtils.Error(map);
		}
		return map; 
		
	}
	//货币消耗&存量统计导出
	@RequestMapping(value = "/exportMoneyStatList", method = {RequestMethod.POST})
	public void exportMoneyStatList(@RequestBody BaseSearchData searchData,HttpServletResponse response)throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportMoneyStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<CzzDiamond> moneyList = czzCostDataService.queryMoneyStatList(searchData);
		//导出list
		if(moneyList != null){
			String[] title = new String[] {"类型","有偿钻石数","无偿钻石数","其他货币名称&量","次数","人数","充值（消耗/产出）占比","赠送（消耗/产出）占比","货币（消耗/产出）占比"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(CzzDiamond e : moneyList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getApproach();
				items[count++] =e.getYpay();
				items[count++] =e.getNpay();
				items[count++] =e.getOpay();
				items[count++] =e.getNum();
				items[count++] =e.getPersonnum();
				items[count++] =e.getYpercent();
				items[count++] =e.getNpercent();
				items[count++] =e.getOpercent();
				datalist.add(items);
			}
			list.add(datalist);
		}
		work(response, list, "货币消耗&存量统计", "货币消耗&存量统计.xlsx");
	}


	//抽奖统计
	@RequestMapping(value="/queryLottoStatList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object>queryLottoStatList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<HieLottoStat>list=new ArrayList<HieLottoStat>();
		try {
			saveLog(request, searchData,"queryLottoStatList");
			list=czzCostDataService.queryLottoStatList(searchData);
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
		List<HieLottoStat> lottoList = czzCostDataService.queryLottoStatList(searchData);
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
		List<CzzEctypeStat>list=new ArrayList<CzzEctypeStat>();
		try {
			saveLog(request, searchData,"queryEctypeStatList");
			list=czzCostDataService.queryEctypeStatList(searchData);
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
		List<CzzEctypeStat> ectypeList = czzCostDataService.queryEctypeStatList(searchData);
		//导出list
		if(ectypeList != null){
			String[] title = new String[] {"副本名称","进入次数","通关次数","通过率","参与人数","通关人数","平均用时"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(CzzEctypeStat e : ectypeList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getEctypename();
				items[count++] =e.getAccessnum();
				items[count++] =e.getPassnum();
				items[count++] =e.getPassrate();
				items[count++] =e.getAccesspersonnum();
				items[count++] =e.getPasspersonnum();
				items[count++] =e.getAvgtime();
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
			list=czzCostDataService.queryShoppingStatList(searchData);
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
		List<HieShoppingStat> shoppingList = czzCostDataService.queryShoppingStatList(searchData);
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
			list=czzCostDataService.queryThingStatList(searchData);
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
		List<HieThingStat> thingList = czzCostDataService.queryThingStatList(searchData);
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
