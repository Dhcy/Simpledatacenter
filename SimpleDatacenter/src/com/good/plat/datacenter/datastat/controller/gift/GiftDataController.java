package com.good.plat.datacenter.datastat.controller.gift;

import java.io.ByteArrayOutputStream;
import java.util.Date;
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

import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.gift.GiftData;
import com.good.plat.datacenter.datastat.entity.virtual.VirtualCurrency;
import com.good.plat.datacenter.datastat.service.gift.IGiftService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

@Controller
@RequestMapping("/gift")
public class GiftDataController  extends BaseController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private IGiftService giftService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping(value= "/giftReceive", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<GiftData> selectGiftReceive(@RequestBody BaseSearchData searchData,
			HttpSession session){
		List<GiftData> list = null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectGiftReceive";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list = giftService.selectGiftReceive(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value= "/giftReceiveDetail", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<GiftData> selectGiftReceiveDetail(@RequestBody BaseSearchData searchData,
			HttpSession session){
		List<GiftData> list = null,result = new LinkedList<GiftData>();
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectGiftReceiveDetail";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list = giftService.selectGiftReceiveDetail(searchData);
			if(list != null){
				double total = 0.0;
				for(GiftData g : list){
					if(g != null){
						total += g.getCount();
						result.add(g);
					}
				}
				if(result != null){
					for(GiftData g : result){
						if(g != null){
							g.setRate(NumberUtil.mul(NumberUtil.div(g.getCount() == null ? 0.0 : g.getCount().doubleValue(), total), 100.0,NumberUtil.DEFAULT_SCALE));
						}
					}	
				}
			}
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/exportGiftReceive", method = {
			RequestMethod.POST})
	public void exportGiftReceive(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportGiftReceive", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "礼包领取.xlsx";
		//
		List<GiftData> giftReceiveList = selectGiftReceive(searchData, session);
		
		//
		if(giftReceiveList != null){
			String[] title = new String[] {"日期","礼包领取总数"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(GiftData e : giftReceiveList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getCount();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("礼包领取");
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
	
	@RequestMapping(value = "/exportReceiveDetail", method = {
			RequestMethod.POST})
	public void exportReceiveDetail(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportReceiveDetail", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "礼包详情.xlsx";
		//
		List<GiftData> giftReceiveDetailList = selectGiftReceiveDetail(searchData, session);
		
		//
		if(giftReceiveDetailList != null){
			String[] title = new String[] {"礼包名称","领取数量","百分比"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(GiftData e : giftReceiveDetailList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getGiftName();
				items[count++] = e.getCount();
				items[count++] = e.getRate();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("礼包详情");
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
