package com.good.plat.datacenter.datastat.controller.channels;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.Validator;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.JSONUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.controller.players.PlayersRetentionController;
import com.good.plat.datacenter.datastat.entity.channels.ChannelData;
import com.good.plat.datacenter.datastat.entity.levels.LevelDetail;
import com.good.plat.datacenter.datastat.entity.players.PlayersRetention;
import com.good.plat.datacenter.datastat.entity.virtual.VirtualCurrency;
import com.good.plat.datacenter.datastat.service.impl.channels.ChannelDataServiceImpl;
import com.good.plat.datacenter.datastat.service.impl.players.PlayersRetentionServiceImpl;
import com.good.plat.datacenter.game.entity.Channel;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: ChannelDataController
 * @Description: 渠道数据
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Controller
@RequestMapping("/channels")
public class ChannelDataController extends BaseController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ChannelDataServiceImpl channelDataService;
	@Autowired
	private PlayersRetentionServiceImpl playersRetentionService;
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping(value="/selectPartnerAmountMap", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map<String,List<ChannelData>> selectPartnerAmountMap (@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPartnerAmountMap";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		Map<String,List<ChannelData>> resultMap = new HashMap<String, List<ChannelData>>();
		List<ChannelData> list = selectPartnerAmount(searchData, session);
		resultMap.put("data", list);
		return resultMap;
	}
	
	@RequestMapping(value="/selectPartnerAmount", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<ChannelData> selectPartnerAmount (@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		logger.debug(searchData.toString());
		List<ChannelData> list = null;
		//log
		Date opDate = new Date();
		String methodName = "selectPartnerAmount";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		list = channelDataService.selectPartnerAmount(searchData, session);
		
		list = list == null ?  new ArrayList<ChannelData>() : list;
		if(list != null){
			double newPlayer_total = 0.0;
			for(ChannelData cd : list){
				if(cd != null && cd.getNewPlayers() != null){
					newPlayer_total += cd.getNewPlayers();
				}
			}
			for(ChannelData cd : list){
				if(cd != null && cd.getNewPlayers() != null){
					cd.setRate(NumberUtil.mul(NumberUtil.div(cd.getNewPlayers().doubleValue(), newPlayer_total),100.0,NumberUtil.DEFAULT_SCALE));
					cd.setRegCon(NumberUtil.mul(NumberUtil.div(cd.getNewPlayers().doubleValue(), cd.getDevices() == null ? 0.0 : cd.getDevices()), 100.0,NumberUtil.DEFAULT_SCALE));
				}
				//
				Map<String,List<Map<String,Object>>> trend = new HashMap<String, List<Map<String,Object>>>();
				List<Map<String,Object>> devices = new ArrayList<Map<String,Object>>();
				List<Map<String,Object>> newPlayers = new ArrayList<Map<String,Object>>();
				List<Map<String,Object>> dailyActive = new ArrayList<Map<String,Object>>();
				List<Map<String,Object>> income = new ArrayList<Map<String,Object>>();
				List<Map<String,Object>> dailyPaidPlayers = new ArrayList<Map<String,Object>>();
				List<Map<String,Object>> ARPU = new ArrayList<Map<String,Object>>();
				trend.put("devices", devices);
				trend.put("newPlayers", newPlayers);
				trend.put("dailyActive", dailyActive);
				trend.put("income", income);
				trend.put("dailyPaidPlayers", dailyPaidPlayers);
				trend.put("ARPU", ARPU);
				cd.setTrend(trend);
			}
		}
		
		
		return list;
	}
	
	@RequestMapping(value="/selectPartnerAmountDetail", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map<String,List<Map<String,Object>>> selectPartnerAmountDetail (@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		logger.debug(searchData.toString());
		Map<String,List<Map<String,Object>>> resultTrend = new HashMap<String, List<Map<String,Object>>>();
		List<ChannelData> channelDataList = null;
		List<PlayersRetention> newUserRetentionList = null;
		try{
//			Map<String,List<Map<String,Object>>> trend = new HashMap<String, List<Map<String,Object>>>();
//			List<Map<String,Object>> devices = new ArrayList<Map<String,Object>>();
//			List<Map<String,Object>> newPlayers = new ArrayList<Map<String,Object>>();
//			List<Map<String,Object>> dailyActive = new ArrayList<Map<String,Object>>();
//			List<Map<String,Object>> income = new ArrayList<Map<String,Object>>();
//			List<Map<String,Object>> dailyPaidPlayers = new ArrayList<Map<String,Object>>();
//			List<Map<String,Object>> ARPU = new ArrayList<Map<String,Object>>();
//			trend.put("devices", devices);
//			trend.put("newPlayers", newPlayers);
//			trend.put("dailyActive", dailyActive);
//			trend.put("income", income);
//			trend.put("dailyPaidPlayers", dailyPaidPlayers);
//			trend.put("ARPU", ARPU);
			
			//log
			Date opDate = new Date();
			String methodName = "selectPartnerAmountDetail";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//			
			channelDataList = channelDataService.selectPartnerAmountDetail(searchData, session);
			
			channelDataList = channelDataList == null ?  new ArrayList<ChannelData>() : channelDataList;
			if(channelDataList != null){
				for(int i=0,length = channelDataList.size();i < length;i++){
					ChannelData cd = (ChannelData) channelDataList.get(i);
					if(cd != null){
						cd.setDailyAvgARPU(NumberUtil.div(cd.getRevenue() == null ? 0.0 : cd.getRevenue().doubleValue(), cd.getActiveAccounts() == null ? 0.0 : cd.getActiveAccounts()));
					}
				}
			}
			
			//
			Channel channel = new Channel();
			channel.setId(Integer.valueOf(searchData.getChecktype1()));
			searchData.setChecktype1(null);
			List<Channel> channelList = new LinkedList<Channel>();
			channelList.add(channel);
			searchData.setChannelList(channelList);
			//
			newUserRetentionList = playersRetentionService.selectRetentionOfNewUser(searchData, session);
			
			
			resultTrend.put("channelDataList",(List) channelDataList);
			resultTrend.put("newUserRetentionList",(List) newUserRetentionList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultTrend;
	}

	
	@RequestMapping(value="/exportPartnerAmount", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<ChannelData> exportPartnerAmount(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportPartnerAmount";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return channelDataService.exportPartnerAmount(searchData, session);
	}

	
	@RequestMapping(value="/selectPartnerQuality", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<ChannelData> selectPartnerQuality(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//checktype1,jdbcType=INTEGER} and #{checktype2
		searchData.setChecktype1("0");
		searchData.setChecktype2("7");
		//log
		Date opDate = new Date();
		String methodName = "selectPartnerQuality";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<ChannelData> list = channelDataService.selectPartnerQuality(searchData, session);

		return list;
	}

	
	@RequestMapping(value="/exportPartnerQuality", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<ChannelData> exportPartnerQuality(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportPartnerQuality";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return channelDataService.exportPartnerQuality(searchData, session);
	}

	
	@RequestMapping(value="/selectPartnerIncome", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<ChannelData> selectPartnerIncome(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectPartnerIncome";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<ChannelData> list = new LinkedList<ChannelData>() , dailyList = null;
		//list = channelDataService.selectPartnerIncome(searchData, session);
		dailyList = channelDataService.selectPartnerDailyIncome(searchData, session);
		
		if(dailyList != null){
			Collections.sort(dailyList,new Comparator<ChannelData>() {
				@Override
				public int compare(ChannelData o1, ChannelData o2) {
					try{
						return o1.getChannelid().compareTo(o2.getChannelid());
					}catch(Exception e){e.printStackTrace();}
					return 0;
				}
			});
			for(int i = 0,size = dailyList.size(); i < size; i++){
				ChannelData c = dailyList.get(i);
				c.setDailyCon(NumberUtil.mul(NumberUtil.div(c.getPaidAccounts() == null ? 0.0 : c.getPaidAccounts().doubleValue()
						,c.getActiveAccounts() == null ? 0.0 : c.getActiveAccounts().doubleValue()),100.0,NumberUtil.DEFAULT_SCALE));
				c.setDailyAvgARPU(NumberUtil.div(c.getRevenue() == null ? 0 : c.getRevenue(), c.getActiveAccounts() == null ? 0.0 : c.getActiveAccounts()));
				c.setDailyAvgARPPU(NumberUtil.div(c.getRevenue() == null ? 0.0 : c.getRevenue(),c.getPaidAccounts() == null ? 0.0 : c.getPaidAccounts()));
			}
			int dayscount = 0;
			for(int i = 0,size = dailyList.size(); i < size; i++){
				dayscount = 1;
				ChannelData c = dailyList.get(i);
				if(c != null && c.getChannelid() != null){
					while(i + 1 < size){
						ChannelData n = dailyList.get(i + 1);
						if(c.getChannelid().equals(n.getChannelid())){
							c.setRevenue((c.getRevenue() == null ? 0.0 : c.getRevenue()) + (n.getRevenue() == null ? 0.0 : n.getRevenue()));
							c.setDailyCon((c.getDailyCon() == null ? 0.0 : c.getDailyCon()) + (n.getDailyCon() == null ? 0.0 : n.getDailyCon()));
							c.setDailyAvgARPU((c.getDailyAvgARPU() == null ? 0.0 : c.getDailyAvgARPU()) + (n.getDailyAvgARPU() == null ? 0.0 : n.getDailyAvgARPU()));
							c.setDailyAvgARPPU((c.getDailyAvgARPPU() == null ? 0.0 : c.getDailyAvgARPPU()) + (n.getDailyAvgARPPU() == null ? 0.0 : n.getDailyAvgARPPU()));
							c.setPaidAccounts((c.getPaidAccounts() == null ? 0 : c.getPaidAccounts() ) + (n.getPaidAccounts() == null ? 0 : n.getPaidAccounts()));
							dayscount++;
							i++;
						}else{
							break;
						}
					}
				}
				c.setDailyCon(NumberUtil.div(c.getDailyCon(), (double) dayscount));
				c.setDailyAvgARPU(NumberUtil.div(c.getDailyAvgARPU(), (double)dayscount));
				c.setDailyAvgARPPU(NumberUtil.div(c.getDailyAvgARPPU(), (double)dayscount));
				
				list.add(c);
			}
		}
		return list;
	}

	
	@RequestMapping(value="/exportPartnerIncome", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<ChannelData> exportPartnerIncome(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportPartnerIncome";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return channelDataService.exportPartnerIncome(searchData, session);
	}

	
	@RequestMapping(value="/selectInstallList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<ChannelData> selectInstallList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectInstallList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return channelDataService.selectInstallList(searchData, session);
	}

	
	@RequestMapping(value="/selectNewPlayerList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<ChannelData> selectNewPlayerList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectNewPlayerList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return channelDataService.selectNewPlayerList(searchData, session);
	}

	
	@RequestMapping(value="/selectDauList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<ChannelData> selectDauList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectDauList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return channelDataService.selectDauList(searchData, session);
	}

	@RequestMapping(value="/selectIncomeNumList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<ChannelData> selectIncomeNumList(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectIncomeNumList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return channelDataService.selectIncomeNumList(searchData, session);
	}
	
	
	
	@RequestMapping(value="/selectChargePlayerNumList", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<ChannelData> selectChargePlayerNumList(
			@RequestBody BaseSearchData searchData, HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectChargePlayerNumList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return channelDataService.selectChargePlayerNumList(searchData, session);
	}

	@RequestMapping(value="/selectDailyARPU", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<ChannelData> selectDailyARPU(@RequestBody BaseSearchData searchData,
			HttpSession session) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectDailyARPU";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		return channelDataService.selectDailyARPU(searchData, session);
	}
	
	@RequestMapping(value = "/exportChannelData", method = {
			RequestMethod.POST})
	public void exportChannelData(
			@RequestBody BaseSearchData searchData, HttpSession session,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportChannelData", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "渠道数据.xlsx";
		//
		
		Map<String, List<ChannelData>> partnerAmountMap = selectPartnerAmountMap(searchData, session);
		List<ChannelData> partnerAmountList = partnerAmountMap.get("data");
		List<ChannelData> partnerQualityList = selectPartnerQuality(searchData, session);
		List<ChannelData> partnerIncomeList = selectPartnerIncome(searchData, session);
		
		//
		if(partnerAmountList != null){
			String[] title = new String[] {"渠道名称","设备激活","新增玩家（角色数）","注册转化率","渠道比重","详情"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(ChannelData e : partnerAmountList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getChannelid() + "-" +  e.getChannelName();
				items[count++] = e.getDevices();
				items[count++] = e.getNewPlayers();
				items[count++] = e.getRegCon();
				items[count++] = e.getRate();
				items[count++] = "";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("数量指标");
		}
		if(partnerQualityList != null){
			String[] title = new String[] {"渠道名称","平均日活跃(角色数)","平均次日留存率","首周付费比例","详情"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(ChannelData e : partnerQualityList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getChannelid() + "-"  +  e.getChannelName();
				items[count++] = e.getActiveAccounts();
				items[count++] = e.getRetentionRate();
				items[count++] = e.getPayRate();
				items[count++] = "";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("质量指标");
		}
		if(partnerIncomeList != null){
			String[] title = new String[] {"渠道名称","收入","付费账户（每日加和）","日均付费率","日均ARPU","日均ARPPU","详情"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(ChannelData e : partnerIncomeList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getChannelid() + "-" +  e.getChannelName();
				items[count++] = e.getRevenue();
				items[count++] = e.getPaidAccounts();
				items[count++] = e.getDailyCon();
				items[count++] = e.getDailyAvgARPU();
				items[count++] = e.getDailyAvgARPPU();
				items[count++] = "";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("收入指标");
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
