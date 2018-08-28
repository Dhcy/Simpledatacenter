package com.good.plat.datacenter.datastat.controller.concurrent;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.base.Validator;
import com.good.plat.datacenter.common.tool.Tool;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.concurrent.ConcurrentData;
import com.good.plat.datacenter.datastat.entity.revenue.Ltv;
import com.good.plat.datacenter.datastat.service.concurrent.IConcurrentService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

@Controller
@RequestMapping(value="/concurrent")
public class ConcurrentController  extends BaseController{
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	IConcurrentService concurrentService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping(value= "/selectConcurrentData", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectConcurrentData(@RequestBody BaseSearchData searchData,
			HttpSession session){
		Map<String,Object> result = new HashMap<String, Object>();
		List<ConcurrentData> list = null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectConcurrentData";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list = concurrentService.selectConcurrentList(searchData);
			String acu_max_date = null,pcu_max_date = null;
			double acu_max = 0.0 ,pcu_max = 0.0,acu_avg = 0.0,pcu_avg = 0.0;
			//
			if(list != null){
				double acu_total = 0.0,pcu_total = 0.0;
				for(ConcurrentData o : list){
					if(o != null){
						if(o.getAcu() == 0){
							o.setAcu(0);
						}
						if(o.getPcu() == 0){
							o.setPcu(0);
						}
						if(o.getRate() == null){
							o.setRate(0.0);
						}
						
						o.setRate(NumberUtil.mul(NumberUtil.div(o.getAcu().doubleValue(), o.getPcu().doubleValue()), 100.0,NumberUtil.DEFAULT_SCALE));
						
						if(o.getAcu() > acu_max){
							acu_max = o.getAcu();
							acu_max_date = o.getStatdate();
						}
						if(o.getPcu() > pcu_max){
							pcu_max = o.getPcu();
							pcu_max_date = o.getStatdate();
						}
						acu_total += o.getAcu();
						pcu_total += o.getPcu();
					}
				}
				
				pcu_avg = NumberUtil.div(pcu_total, (double) list.size());
				acu_avg = NumberUtil.div(acu_total, (double) list.size());
			}
			//
			result.put("data", list);
			result.put("acu_max", acu_max);
			result.put("acu_max_date", acu_max_date);
			result.put("acu_avg", acu_avg);
			result.put("pcu_max", pcu_max);
			result.put("pcu_max_date", pcu_max_date);
			result.put("pcu_avg", pcu_avg);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value= "/selectConcurrentList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<ConcurrentData> selectConcurrentList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		List<ConcurrentData> list = null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectConcurrentList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list = concurrentService.selectConcurrentList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		return list;
	}
	/**
	 * 时段分布
	 * @Title: selectTimeDistributeList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<ConcurrentData>
	 * @author hwj
	 * @date 2017-2-9 下午03:34:32
	 */
	@RequestMapping(value= "/selectTimeDistributeList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<ConcurrentData> selectTimeDistributeList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		List<ConcurrentData> list = null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectTimeDistributeList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list = concurrentService.selectTimeDistributeList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		return list;
	}
	
	/**
	 * 启动间隔分布
	 * @Title: selectDurationDistributeList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<ConcurrentData>
	 * @author hwj
	 * @date 2017-2-9 下午03:32:48
	 */
	@RequestMapping(value= "/selectDurationDistributeList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<ConcurrentData> selectDurationDistributeList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		List<ConcurrentData> list = null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectDurationDistributeList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list = concurrentService.selectDurationDistributeList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		return list;
	}
	
	/**
	 * 导出时段分布
	 * @Title: exportTimeDistributeList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-2-10 上午10:12:35
	 */
	@RequestMapping(value = "/exportTimeDistributeList", method = {
			RequestMethod.POST})
	public void exportTimeDistributeList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportTimeDistributeList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "时段分布.xlsx";
		//时段分布列表
		List<ConcurrentData> timeDistributeList=selectTimeDistributeList(searchData,session);
		//
		if(timeDistributeList != null){
			String[] title = new String[] {"时段","平均启动次数"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(ConcurrentData e : timeDistributeList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getDescription();
				items[count++] =e.getAvg_time_count();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("付费金额");
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
	 * 导出启动间隔分布
	 * @Title: exportDurationDistributeList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2017-2-10 上午10:30:14
	 */
	@RequestMapping(value = "/exportDurationDistributeList", method = {
			RequestMethod.POST})
	public void exportDurationDistributeList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportDurationDistributeList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "间隔分布.xlsx";
		//时段分布列表
		List<ConcurrentData> durationDistributeList=selectDurationDistributeList(searchData, session);
		//
		if(durationDistributeList != null){
			String[] title = new String[] {"间隔","人数"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(ConcurrentData e : durationDistributeList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getDescription();
				items[count++] =e.getStart_count();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("启动间隔分布");
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
	
	private List<Ltv> ajustScaleOrMul100(List<Ltv> list){
		if(!Validator.isNull(list)){
			for(Ltv e : list){
				//e.set(NumberUtil.ajustScale(e.get,NumberUtil.DEFAULT_SCALE));
				e.setLtv3(NumberUtil.ajustScale(e.getLtv3(),NumberUtil.DEFAULT_SCALE));
				e.setLtv7(NumberUtil.ajustScale(e.getLtv7(),NumberUtil.DEFAULT_SCALE));
				e.setLtv30(NumberUtil.ajustScale(e.getLtv30(),NumberUtil.DEFAULT_SCALE));
				e.setLtv60(NumberUtil.ajustScale(e.getLtv60(),NumberUtil.DEFAULT_SCALE));
			}
		}
		return list;
	}
	
	/**
	 * 今天跟昨天的在线数据
	 * @Title: selectTodayAndYesterdayList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<ConcurrentData>
	 * @author hwj
	 * @date 2017-3-1 下午03:34:02
	 */
	@RequestMapping(value= "/selectTodayAndYesterdayHourList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectTodayAndYesterdayHourList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		//昨天数据列表
		List<ConcurrentData> hourlist = null;
		//今天数据列表
		List<ConcurrentData> yesHourlist = null;
		List<ConcurrentData> todayHourList=new ArrayList<ConcurrentData>();
		List<ConcurrentData> yesterdayHourList=new ArrayList<ConcurrentData>();
		Map<String,Object> map=new HashMap<String, Object>(); 
		Date date=new Date();
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectTodayAndYesterdayHourList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			//昨天
			String yesterDay=DateUtil.addDay(searchData.getCurrDate(), -1);
			//今天在线数据
			hourlist = concurrentService.selectTodayAndYesterdayHourList(searchData);
			//昨天在线数据
			searchData.setCurrDate(yesterDay);
			yesHourlist=concurrentService.selectTodayAndYesterdayHourList(searchData);
			//pcu最大值
			ConcurrentData pcuData=concurrentService.getMaxOfPcu(searchData);
			String currDate=DateUtil.format_yyyy_MM_dd_HH_mm_ss(date);
			//当前日期
			date=DateUtil.parse_yyyy_MM_dd_HH_mm_ss(currDate);
			//当天的小时
			int hourOfDay=DateUtil.getHourOrMinOrMm(date, Calendar.HOUR_OF_DAY);
			
			if(yesHourlist!=null&&yesHourlist.size()==0){
				//昨天没数据填充为0
				for(int i=0;i<=23;i++){
					ConcurrentData hour=new ConcurrentData();
					hour.setHour(i);
					changeHourStr(hour);
					hour.setAcu(0);
					yesterdayHourList.add(hour);
				}
			}else{
				//有数据
				if(yesHourlist!=null&&yesHourlist.size()!=0){
					int j=0;
					for(int i=0;i<=23;i++){
							if(j<yesHourlist.size()){
								ConcurrentData data=yesHourlist.get(j);
								if(data!=null&&data.getHour().intValue()!=i){
								ConcurrentData data1=new ConcurrentData();
								data1.setHour(i);
								data1.setAcu(data.getAcu());
								changeHourStr(data1);
								yesterdayHourList.add(data1);
								}else{
									if(data!=null&&data.getHour().intValue()==i){
										changeHourStr(data);
										yesterdayHourList.add(data);
										j++;
									}
								}
							}else{
								ConcurrentData data2=new ConcurrentData();
								data2.setHour(i);
								data2.setAcu(0);
								changeHourStr(data2);
								yesterdayHourList.add(data2);
								}
					}
				}
			}
			if(hourlist!=null&&hourlist.size()==0){
				//今天没数据以当前小时前时间点填充为0;
				for(int i=0;i<=23;i++){
					ConcurrentData hour=new ConcurrentData();
					hour.setHour(i);
					changeHourStr(hour);
					hour.setAcu(0);
					todayHourList.add(hour);
				}
			}else{
				//今天有数据,但部分时间点丢失，填充0
				if(hourlist!=null&&hourlist.size()!=0){
					int j=0;
					for(int i=0;i<=23;i++){
							if(j<hourlist.size()){
								ConcurrentData data=hourlist.get(j);
								if(data!=null&&data.getHour().intValue()!=i){
								ConcurrentData data1=new ConcurrentData();
								data1.setHour(i);
								data1.setAcu(0);
								changeHourStr(data1);
								todayHourList.add(data1);
								}else{
									if(data!=null&&data.getHour().intValue()==i){
										changeHourStr(data);
										todayHourList.add(data);
										j++;
									}
								}
							}else{
								ConcurrentData data2=new ConcurrentData();
								data2.setHour(i);
								data2.setAcu(0);
								changeHourStr(data2);
								todayHourList.add(data2);
								}
					}
				}
			}
			map.put("todayHourList", todayHourList);//今天在线小时
			map.put("yesterdayHourList", yesterdayHourList);//昨天在线小时
			map.put("maxPcu",pcuData.getPcu());//最大值
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		return map;
	}
	/**
	 * 游戏在线人数
	 * @Title: selectGameOnlineCntList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-3-2 上午11:08:37
	 */
	@RequestMapping(value= "/selectGameOnlineCntList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectGameOnlineCntList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		Map<String,Object> map=new HashMap<String, Object>(); 
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectGameOnlineCntList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//游戏接口基本配置
			ConcurrentData config=concurrentService.selectBaseGameConfig(searchData);
			String gameUrl=config.getUrl();
			String publicKey=config.getKey();
			int cmdId=config.getCmdId();
			JSONObject sendData=new JSONObject();//提交数据
			JSONObject data=new JSONObject();//参数
			sendData.put("cmdId", cmdId);
			sendData.put("data", data);
			String resultStr=Tool.loadJSON(sendData, gameUrl, publicKey);
			JSONObject json=JSONObject.parseObject(resultStr);
			int totalCount=0;
			if(json!=null&&json.getInteger("result").intValue()==0){
				JSONArray arr=json.getJSONArray("data");
				List<Integer> areas=searchData.getAreas();
				if(areas!=null&&areas.size()!=0){//有区服时
					for(Integer i:areas){
						for(Object e:arr){
							JSONObject object=(JSONObject)e;
							if(object.getInteger("areaid").intValue()==i){
								totalCount+=object.getInteger("count").intValue();
							}
						}
					}
				}else{//没区服时
					for(Object e:arr){
						JSONObject object=(JSONObject)e;
							totalCount+=object.getInteger("count").intValue();
					}
				}
				map.put("count", totalCount);//在线人数
			}else{
				new Exception("调取接口数据失败");
			}
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		return map;
	}
	
	
	//小时点转为字符串
	private void changeHourStr(ConcurrentData e){
		if(e.getHour()==0){
			e.setTime("00:00");
		}
		if(e.getHour()==1){
			e.setTime("01:00");
		}
		if(e.getHour()==2){
			e.setTime("02:00");
		}
		if(e.getHour()==3){
			e.setTime("03:00");
		}
		if(e.getHour()==4){
			e.setTime("04:00");
		}
		if(e.getHour()==5){
			e.setTime("05:00");
		}
		if(e.getHour()==6){
			e.setTime("06:00");
		}
		if(e.getHour()==7){
			e.setTime("07:00");
		}
		if(e.getHour()==8){
			e.setTime("08:00");
		}
		if(e.getHour()==9){
			e.setTime("09:00");
		}
		if(e.getHour()==10){
			e.setTime("10:00");
		}
		if(e.getHour()==11){
			e.setTime("11:00");
		}
		if(e.getHour()==12){
			e.setTime("12:00");
		}
		if(e.getHour()==13){
			e.setTime("13:00");
		}
		if(e.getHour()==14){
			e.setTime("14:00");
		}
		if(e.getHour()==15){
			e.setTime("15:00");
		}
		if(e.getHour()==16){
			e.setTime("16:00");
		}
		if(e.getHour()==17){
			e.setTime("17:00");
		}
		if(e.getHour()==18){
			e.setTime("18:00");
		}
		if(e.getHour()==19){
			e.setTime("19:00");
		}
		if(e.getHour()==20){
			e.setTime("20:00");
		}
		if(e.getHour()==21){
			e.setTime("21:00");
		}
		if(e.getHour()==22){
			e.setTime("22:00");
		}
		if(e.getHour()==23){
			e.setTime("23:00");
		}
	}
	/**
	 * 各时段的在线人数acu
	 * @Title: selectDurationAcuList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * List<ConcurrentData>
	 * @author hwj
	 * @date 2018-1-19 下午02:29:55
	 */
	@RequestMapping(value= "/selectDurationAcuList", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<ConcurrentData> selectDurationAcuList(@RequestBody BaseSearchData searchData,
			HttpSession session){
		List<ConcurrentData> list = null;
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectDurationAcuList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list = concurrentService.selectDurationAcuList(searchData);
		}catch(Exception e){
			logger.error("{}",e.getMessage());
		}
		return list;
	}
	/**
	 * 导出各时段的acu
	 * @Title: exportDurationAcuList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-1-19 下午02:38:03
	 */
	@RequestMapping(value = "/exportDurationAcuList", method = {
			RequestMethod.POST})
	public void exportDurationAcuList(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportDurationAcuList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "历史在线acu.xlsx";
		//时段在线人数acu
		List<ConcurrentData> durationAcuList=concurrentService.selectDurationAcuList(searchData);
		//
		if(durationAcuList != null){
			String[] title = new String[] {"日期","0:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00",
					"11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","21:00","22:00","23:00"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(ConcurrentData e : durationAcuList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getHour0_acu();
				items[count++] =e.getHour1_acu();
				items[count++] =e.getHour2_acu();
				items[count++] =e.getHour3_acu();
				items[count++] =e.getHour4_acu();
				items[count++] =e.getHour5_acu();
				items[count++] =e.getHour6_acu();
				items[count++] =e.getHour7_acu();
				items[count++] =e.getHour8_acu();
				items[count++] =e.getHour9_acu();
				items[count++] =e.getHour10_acu();
				items[count++] =e.getHour11_acu();
				items[count++] =e.getHour12_acu();
				items[count++] =e.getHour13_acu();
				items[count++] =e.getHour14_acu();
				items[count++] =e.getHour15_acu();
				items[count++] =e.getHour16_acu();
				items[count++] =e.getHour17_acu();
				items[count++] =e.getHour18_acu();
				items[count++] =e.getHour19_acu();
				items[count++] =e.getHour20_acu();
				items[count++] =e.getHour21_acu();
				items[count++] =e.getHour22_acu();
				items[count++] =e.getHour23_acu();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("历史在线acu");
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
