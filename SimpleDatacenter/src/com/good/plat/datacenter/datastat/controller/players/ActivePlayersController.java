package com.good.plat.datacenter.datastat.controller.players;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.players.ActiveByChnnel;
import com.good.plat.datacenter.datastat.entity.players.ActiveByTime;
import com.good.plat.datacenter.datastat.entity.players.ActivePlayers;
import com.good.plat.datacenter.datastat.entity.players.NewPlayers;
import com.good.plat.datacenter.datastat.service.impl.players.ActivePlayersServiceImpl;
import com.good.plat.datacenter.independ.entity.hie.index.HieLottoStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieMoneyStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieTaskStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieThingStat;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: ActivePlayersMapper
 * @Description: 活跃玩家
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Controller
@RequestMapping("/players/activePlayers")
public class ActivePlayersController extends BaseController {

	@Autowired
	private ActivePlayersServiceImpl activePlayersService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping(value = "/selectAUList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectAUList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			//log
			Date opDate = new Date();
			String methodName = "selectAUList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			
			List<ActivePlayers> dauList = null,wauList = null,mauList = null,dau_divide_mauList = null;
			dauList = selectDauList(searchData, session);
			wauList = selectWauList(searchData, session);
			mauList = selectMauList(searchData, session);
			dau_divide_mauList = selectDauDivideMauList(searchData, session);
			//
			Double dayscount = 0.0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(!Validator.isNullOrBlank(searchData.getStartdate())){
				try{
					Date d1 = sdf.parse(searchData.getStartdate());
					dayscount = 1.0;
					Date d2 = sdf.parse(searchData.getEnddate());
					dayscount = (double) (Math.ceil((d2.getTime() - d1.getTime())/ (1000 * 3600 * 24.0)));
				}catch(ParseException pe){}
			}
			//MD 是中位数,暂时不求 2016-03-29
			Double dau_AVG = 0.0,dau_MD = null;
			Double wau_AVG = 0.0,wau_MD = null;
			Double mau_AVG = 0.0,mau_MD = null;
			Double dao_divide_wau_AVG = 0.0,dao_MD = null;
			if(dauList != null){
				for(ActivePlayers p : dauList){
					dau_AVG += p.getCount();
				}
				dau_AVG = NumberUtil.div(dau_AVG, dayscount);
			}
			if(wauList != null){
				for(ActivePlayers p : wauList){
					wau_AVG += p.getWauAccount();
				}
				wau_AVG = NumberUtil.div(wau_AVG, dayscount);
			}
			if(mauList != null){
				for(ActivePlayers p : mauList){
					mau_AVG += p.getMauAccount();
				}
				mau_AVG = NumberUtil.div(mau_AVG, dayscount);
			}
			if(dau_divide_mauList != null){
				for(ActivePlayers p : dau_divide_mauList){
					dao_divide_wau_AVG += p.getDmauAccount();
				}
				dao_divide_wau_AVG = NumberUtil.div(dao_divide_wau_AVG, dayscount);
			}
			//
			Map<String,Object> activeUser = new HashMap<String,Object>();
			Map<String,Object> DAU = new HashMap<String,Object>();
			Map<String,Object> WAU = new HashMap<String,Object>();
			Map<String,Object> MAU = new HashMap<String,Object>();
			Map<String,Object> DAU_DIVIDE_MAU = new HashMap<String,Object>();
			//
			DAU.put("AVG", dau_AVG);DAU.put("MD", dau_MD);DAU.put("data", dauList);
			WAU.put("AVG", wau_AVG);WAU.put("MD", wau_MD);WAU.put("data", wauList);
			MAU.put("AVG", mau_AVG);MAU.put("MD", mau_MD);MAU.put("data", mauList);
			DAU_DIVIDE_MAU.put("AVG", dao_divide_wau_AVG);DAU_DIVIDE_MAU.put("MD", null);DAU_DIVIDE_MAU.put("data", dau_divide_mauList);
			activeUser.put("DAU", DAU);
			activeUser.put("WAU", WAU);
			activeUser.put("MAU", MAU);
			activeUser.put("DAU_DIVIDE_MAU", DAU_DIVIDE_MAU);
			result.put("activeUser", activeUser);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping(value = "/exportAUList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public void exportAUList(@RequestBody BaseSearchData searchData, HttpSession session
			,HttpServletResponse response) throws Exception {
		//
		sysAccessLogService.log(request, getClass(), "exportAUList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList();
		List<String> sheetNameList = new LinkedList();
		String filename = "渠道.xlsx";
		//
		Map<String,Object> map = selectAUList(searchData, session);
		Map<String,Object> activeUser = (Map<String, Object>) map.get("activeUser");
		Map<String,Object> DAU = (Map<String, Object>) activeUser.get("DAU");
		Map<String,Object> WAU = (Map<String, Object>) activeUser.get("WAU");
		Map<String,Object> MAU = (Map<String, Object>) activeUser.get("MAU");
		Map<String,Object> DAU_DIVIDE_MAU = (Map<String, Object>) activeUser.get("DAU_DIVIDE_MAU");
		
		List<ActivePlayers> dauList = null,wauList = null,mauList = null,dau_divide_mauList = null;
		dauList = (List<ActivePlayers>) DAU.get("data");
		wauList = (List<ActivePlayers>) WAU.get("data");
		mauList = (List<ActivePlayers>) MAU.get("data");
		dau_divide_mauList = (List<ActivePlayers>) DAU_DIVIDE_MAU.get("data");
		//
		if(dauList != null){
			String title[] = new String[]{"日期","新玩家(角色)","老玩家(角色)","总计(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(ActivePlayers e : dauList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getNewUser();
				items[count++] = e.getOldUser();
				items[count++] = e.getCount();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("DAU");
		}
		if(wauList != null){
			String title[] = new String[]{"日期","WAU(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(ActivePlayers e : wauList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getWauAccount();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("WAU");
		}
		if(mauList != null){
			String title[] = new String[]{"日期","MAU(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(ActivePlayers e : mauList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getMauAccount();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("MAU");
		}
		if(dau_divide_mauList != null){
			String title[] = new String[]{"日期","DAU/MAU(角色)"};
			List<Object[]> datalist = new LinkedList();
			datalist.add(title);
			for(ActivePlayers e : dau_divide_mauList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] = e.getStatdate();
				items[count++] = e.getDmauAccount();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("DAU_DIV_MAU");
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
	
	@RequestMapping(value = "/selectDauList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<ActivePlayers> selectDauList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectDauList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<ActivePlayers> dauList = null;
		dauList = activePlayersService.selectDauList(searchData, session);
		return dauList;
	}

	@RequestMapping(value = "/exportDauList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<ActivePlayers> exportDauList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportDauList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return activePlayersService.exportDauList(searchData, session);
	}

	@RequestMapping(value = "/selectWauList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<ActivePlayers> selectWauList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectWauList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return activePlayersService.selectWauList(searchData, session);
	}

	@RequestMapping(value = "/exportWauList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<ActivePlayers> exportWauList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportWauList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return activePlayersService.exportWauList(searchData, session);
	}

	@RequestMapping(value = "/selectMauList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<ActivePlayers> selectMauList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectMauList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return activePlayersService.selectMauList(searchData, session);
	}

	@RequestMapping(value = "/exportMauList", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<ActivePlayers> exportMauList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportMauList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return activePlayersService.exportMauList(searchData, session);
	}

	@RequestMapping(value = "/selectDauDivideMauList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ActivePlayers> selectDauDivideMauList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectDauDivideMauList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return activePlayersService.selectDauDivideMauList(searchData, session);
	}

	@RequestMapping(value = "/exportDauDivideMauList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ActivePlayers> exportDauDivideMauList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportDauDivideMauList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return activePlayersService.exportDauDivideMauList(searchData, session);
	}

	@RequestMapping(value = "/selectDaysOfActivePlayer", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ActivePlayers> selectDaysOfActivePlayer(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectDaysOfActivePlayer";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return activePlayersService.selectDaysOfActivePlayer(searchData,
				session);
	}

	@RequestMapping(value = "/exportDaysOfActivePlayer", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ActivePlayers> exportDaysOfActivePlayer(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportDaysOfActivePlayer";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return activePlayersService.exportDaysOfActivePlayer(searchData,
				session);
	}

	@RequestMapping(value = "/selectLevelOfActivePlayer", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ActivePlayers> selectLevelOfActivePlayer(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectLevelOfActivePlayer";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return activePlayersService.selectLevelOfActivePlayer(searchData,
				session);
	}

	@RequestMapping(value = "/exportLevelOfActivePlayer", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ActivePlayers> exportLevelOfActivePlayer(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportLevelOfActivePlayer";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return activePlayersService.exportLevelOfActivePlayer(searchData,
				session);
	}

	@RequestMapping(value = "/selectCityOfActivePlayer", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ActivePlayers> selectCityOfActivePlayer(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectCityOfActivePlayer";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return activePlayersService.selectCityOfActivePlayer(searchData,
				session);
	}

	@RequestMapping(value = "/exportCityOfActivePlayer", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ActivePlayers> exportCityOfActivePlayer(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportCityOfActivePlayer";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return activePlayersService.exportCityOfActivePlayer(searchData,
				session);
	}

	@RequestMapping(value = "/selectChannelOfActivePlayer", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ActivePlayers> selectChannelOfActivePlayer(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectChannelOfActivePlayer";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return activePlayersService.selectChannelOfActivePlayer(searchData,
				session);
	}

	@RequestMapping(value = "/exportChannelOfActivePlayer", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ActivePlayers> exportChannelOfActivePlayer(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "exportChannelOfActivePlayer";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		return activePlayersService.exportChannelOfActivePlayer(searchData,
				session);
	}
	/**
	 * 活跃用户区域列表
	 * @Title: selectActUerCityList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<ActivePlayers>
	 * @author hwj
	 * @date 2017-11-13 下午02:14:12
	 */
	@RequestMapping(value = "/selectActUserCityList", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ActivePlayers> selectActUserCityList(
			@RequestBody BaseSearchData searchData, HttpSession session)
			throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectActUserCityList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<ActivePlayers> cityList=activePlayersService.selectActUserCityList(searchData);
		return cityList;
	}
	
	//按时登陆
	@RequestMapping(value="/selectActUserByTimeList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object>selectActUserByTimeList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<ActiveByTime>list=new ArrayList<ActiveByTime>();
		
		try {
			saveLog(request, searchData,"selectActUserByTimeList");
			list=activePlayersService.selectActUserByTimeList(searchData);
			addOnTimeCount(list);
			map.put("status", 1);
			map.put("msg", "成功");
			map.put("result", list);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "失败");
		}
		return map;
	}

	//按时登陆导出
	@RequestMapping(value = "/exportActUserByTimeList", method = {RequestMethod.POST})
	public void exportActUserByTimeList(@RequestBody BaseSearchData searchData,HttpServletResponse response)throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportActUserByTimeList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<ActiveByTime> activeList = activePlayersService.selectActUserByTimeList(searchData);
		addOnTimeCount(activeList);
		//导出list
		if(activeList != null){
			String[] title = new String[] {"时间段","新玩家","新玩家登陆次数","老玩家登陆次数"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(ActiveByTime e : activeList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getHourdate();
				items[count++] =e.getNewact();
				items[count++] =e.getNewlogintimes();
				items[count++] =e.getOldlogintimes();
				datalist.add(items);
			}
			list.add(datalist);
		}
		work(response, list, "按时登陆", "按时登陆.xlsx");
	}
	
	//按渠道登录
	@RequestMapping(value="/selectActUserChannelList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object>selectActUserChannelList(HttpServletRequest request,@RequestBody BaseSearchData searchData){
		Map<String,Object> map=new HashMap<String, Object>();
		List<ActiveByChnnel>list=new ArrayList<ActiveByChnnel>();
		try {
			saveLog(request, searchData,"selectActUserChannelList");
			list=activePlayersService.selectActUserChannelList(searchData);
			addByChnnelCount(list);
			map.put("status", 1);
			map.put("msg", "成功");
			map.put("result", list);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "失败");
		}
		return map;
	}
	//按渠道登陆导出
	@RequestMapping(value = "/exportActUserChannelList", method = {RequestMethod.POST})
	public void exportActUserChannelList(@RequestBody BaseSearchData searchData,HttpServletResponse response)throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportActUserChannelList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<ActiveByChnnel> activeList =activePlayersService.selectActUserChannelList(searchData);
		addByChnnelCount(activeList);
		//导出list
		if(activeList != null){
			String[] title = new String[] {"渠道","新玩家","新玩家登陆次数","老玩家登陆次数"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(ActiveByChnnel e : activeList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getChinnel();
				items[count++] =e.getNewact();
				items[count++] =e.getNewlogintimes();
				items[count++] =e.getOldlogintimes();
				datalist.add(items);
			}
			list.add(datalist);
		}
		work(response, list, "按渠道登陆", "按渠道登陆.xlsx");
	}

//*****************************************************************************************************************************
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
	//log
	private void saveLog(HttpServletRequest request, BaseSearchData searchData,String methodName) {
		Date opDate = new Date();
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
	}
	//按时
	private void addOnTimeCount(List<ActiveByTime> list) {
		ActiveByTime h1=new ActiveByTime();
		if(list!=null&&!list.isEmpty()){
			Integer newPlayer=0;
			Integer newPlayerLoginTimes=0;
			Integer oldPlayerLoginTimes=0;
		for (ActiveByTime h : list) {
			newPlayer+=h.getNewact();
			newPlayerLoginTimes+=h.getNewlogintimes();
			oldPlayerLoginTimes+=h.getOldlogintimes();
		}
		h1.setHourdate("总计");
		h1.setNewact(newPlayer);
		h1.setNewlogintimes(newPlayerLoginTimes);
		h1.setOldlogintimes(oldPlayerLoginTimes);
		list.add(h1);
		}
	}
	//按渠道
	private void addByChnnelCount(List<ActiveByChnnel> list) {
		ActiveByChnnel h1=new ActiveByChnnel();
		if(list!=null&&!list.isEmpty()){
			Integer newPlayer=0;
			Integer newPlayerLoginTimes=0;
			Integer oldPlayerLoginTimes=0;
		for (ActiveByChnnel h : list) {
			newPlayer+=h.getNewact();
			newPlayerLoginTimes+=h.getNewlogintimes();
			oldPlayerLoginTimes+=h.getOldlogintimes();
		}
		h1.setChinnel("总计");
		h1.setNewact(newPlayer);
		h1.setNewlogintimes(newPlayerLoginTimes);
		h1.setOldlogintimes(oldPlayerLoginTimes);
		list.add(h1);
		}
	}
}
