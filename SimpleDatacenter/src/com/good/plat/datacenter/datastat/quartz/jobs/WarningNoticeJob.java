package com.good.plat.datacenter.datastat.quartz.jobs;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.base.EmailPlatTool;
import com.good.plat.datacenter.common.base.PropertiesUtil;
import com.good.plat.datacenter.common.base.Validator;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.controller.analysis.AnalysisController;
import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningEvent;
import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningItem;
import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningNoticeWithBLOBs;
import com.good.plat.datacenter.datastat.entity.players.ActivePlayers;
import com.good.plat.datacenter.datastat.entity.players.NewPlayers;
import com.good.plat.datacenter.datastat.entity.players.PlayersChurn;
import com.good.plat.datacenter.datastat.entity.players.PlayersRetention;
import com.good.plat.datacenter.datastat.entity.revenue.Conversion;
import com.good.plat.datacenter.datastat.entity.revenue.IncomeData;
import com.good.plat.datacenter.datastat.service.analysis.IWarningEventService;
import com.good.plat.datacenter.datastat.service.analysis.IWarningItemService;
import com.good.plat.datacenter.datastat.service.analysis.IWarningNoticeService;
import com.good.plat.datacenter.datastat.service.impl.players.ActivePlayersServiceImpl;
import com.good.plat.datacenter.datastat.service.impl.players.PlayersChurnServiceImpl;
import com.good.plat.datacenter.datastat.service.players.NewPlayersService;
import com.good.plat.datacenter.datastat.service.players.PlayersRetentionService;
import com.good.plat.datacenter.datastat.service.revenue.ConversionService;
import com.good.plat.datacenter.datastat.service.revenue.IncomeDataService;
import com.good.plat.datacenter.game.entity.Game;
import com.good.plat.datacenter.game.service.GameService;

/**
 * 
 * @ClassName: NewActorWarningJob
 * @Description: 新增玩家预警通知
 * @author moxw
 * @date 2016年5月18日 上午9:41:19
 */
public class WarningNoticeJob extends QuartzJobBean {
	static Logger logger = LoggerFactory.getLogger(WarningNoticeJob.class);

	private static IWarningEventService warningEventService;
	private static IWarningItemService warningItemService;
	private static IWarningNoticeService warningNoticeService;
	private static GameService gameService;
	//
	private static NewPlayersService newPlayersService;
	private static PlayersRetentionService playersRetentionService;
	private static PlayersChurnServiceImpl playersChurnService;
	private static IncomeDataService incomeDataService;
	private static ConversionService conversionService;
	private static ActivePlayersServiceImpl activePlayersService;
	
	public void setWarningEventService(IWarningEventService warningEventService) {
		this.warningEventService = warningEventService;
	}

	public void setWarningItemService(IWarningItemService warningItemService) {
		this.warningItemService = warningItemService;
	}

	public void setWarningNoticeService(IWarningNoticeService warningNoticeService) {
		this.warningNoticeService = warningNoticeService;
	}
	
	
	
	@Override
	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
		try{
			logger.debug("quartz..... {} ", new Date().toString());
			JobDataMap data = ctx.getMergedJobDataMap();
			Integer id = (Integer) data.get("id");
			Integer gameid = (Integer) data.get("gameid");
			Integer projectid = (Integer) data.get("projectid");
			logger.debug("id: {}",id);
			logger.debug("gameid: {}",gameid);
			logger.debug("projectid: {}",projectid);
			if(id != null){
				LogplatWarningItem item = null;
				item = warningItemService.selectItemsByPrimaryID(id);
				
				if(item != null&&item.getStat().intValue()==0){
					//check then send notice if neccessary
					Date day = new Date();
					if(Arrays.asList(WarningJob.WATCHING_ITEMS).contains(item.getId())){
						logger.info("=============== gameid={},item_id={},itemName={}",item.getGameid(),item.getId(),item.getName());
						//day = DateUtil.parse("yyyy-MM-dd","2016-12-13");
					}
					//
					day = DateUtil.add(day,Calendar.DAY_OF_YEAR,-1);//昨天
					if(check(item,day)){
						sendNotice(item,day);
						logger.info("发送预警邮件: id={} at {}",item.getId(),new Date());
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
	private void sendNotice(LogplatWarningItem item,Date day) throws Exception{
		//
		LogplatWarningNoticeWithBLOBs notice = new LogplatWarningNoticeWithBLOBs();
		notice.setReceiver(item.getSubscriber());
		StringBuffer title = new StringBuffer();
		Game game = gameService.findGameById(item.getGameid());
		if(game == null){
			logger.warn("=============warning item({}), game({} not found",item.getId(),item.getGameid());
			return ;
		}
		LogplatWarningEvent event = warningEventService.findByPrimaryId(item.getWarning_event_id());
		if(Validator.isNull(event)){
			logger.error("==================warning item event not found:{}",item.getId());
			return ;
		}
		//title
		title.append("预警通知【");
		title.append("游戏:" + game.getName() + "(" + game.getId() + ")");	// gameName
		title.append("-");
		title.append(event.getName()); 										// event name
		title.append("-");
		title.append(item.getName()); 										// event item name
		title.append("-");
		title.append("配置值=(");
		title.append(AnalysisController.parseCmpType(item.getCmptype()) + item.getValve() + AnalysisController.parseUnit(item.getUnit()));
		if(!Validator.isNullOrBlank(item.getValve2())){
			title.append("-" + "扩展值=" + item.getValve2());
		}
		title.append(")");
		title.append("】");
		//content
		StringBuffer content = new StringBuffer();
		content.append("<table>");
		content.append("<tr><td>【游戏】" + game.getName() + "(" + game.getId() + ")" + "</td></tr>");
		content.append("<tr><td>【日期】" + DateUtil.format_yyyy_MM_dd(day) + "</td></tr>");
		content.append("<tr><td>【时间】" + DateUtil.format(day, "HH:mm:ss") + "</td></tr>");
		content.append("<tr><td>【预警】" + event.getName() + "-" + item.getName() + "-" + "配置值" 
		+ "(" + (AnalysisController.parseCmpType(item.getCmptype()) + item.getValve() + AnalysisController.parseUnit(item.getUnit()))
				+ (Validator.isNullOrBlank(item.getValve2()) ? "" : "扩展值=" + item.getValve2()) + ")" + "</td></tr>");
		content.append("</table>");
		content.append("<hr/><b>请及时登录后台查看详情</b>");
		//
		notice.setTitle(title.toString());
		notice.setContent(content.toString());
		notice.setNotice_datetime(new Date());
		notice.setStatus(0);
		notice.setProjectid(item.getProjectid());
		notice.setGameid(item.getGameid());
		notice.setWarning_item_id(item.getId());
		BaseMessage bm = warningNoticeService.saveOrUpdateWarningNotice(notice);
		//
		int connectTimeout = Integer.valueOf(PropertiesUtil.getValue("email_plat_connect_timeout"));
		int readTimeout = Integer.valueOf(PropertiesUtil.getValue("email_plat_read_timeout"));
		String sendRet = EmailPlatTool.sendEmail(notice.getTitle(), notice.getContent(), Arrays.asList(notice.getReceiver().split(",")), connectTimeout, readTimeout);
		logger.info(sendRet);
	}
	
	/**
	 * 计算x天留存
	 * @Title: calXDayRetain
	 * @Description: TODO
	 * @param list
	 * @return
	 * Double
	 * @author moxw
	 * @date 2016年12月14日 上午11:27:05
	 */
	private Double calXDayRetain(List<PlayersRetention> list,Integer nday,boolean isRate){
		if(list == null) return null;
		Double value = 0.0;
		int count = 0;
		for(PlayersRetention it : list){
			if(it == null) continue;
			++count;
			if(it.getAccounts() == null){
				it.setAccounts(0);
			}
			switch (nday) {
			case 1:
				if(it.getDay1() == null){
					it.setDay1(0.0);
				}
				//扩展值:某一天的几天留存
				if(isRate){
					value += it.getDay1();
				}else{
					value += (it.getAccounts() * it.getDay1());
				}
				break;
			case 2:
				if(it.getDay2() == null){
					it.setDay2(0.0);
				}
				if(isRate){
					value += it.getDay2();
				}else{
					value += (it.getAccounts() * it.getDay2());
				}
				break;
			case 3:
				if(it.getDay3() == null){
					it.setDay3(0.0);
				}
				if(isRate){
					value += it.getDay3();
				}else{
					value += (it.getAccounts() * it.getDay3());
				}
				break;
			case 4:
				if(it.getDay4() == null){
					it.setDay4(0.0);
				}
				if(isRate){
					value += it.getDay4();
				}else{
					value += (it.getAccounts() * it.getDay4());
				}
				break;
			case 5:
				if(it.getDay5() == null){
					it.setDay5(0.0);
				}
				if(isRate){
					value += it.getDay5();
				}else{
					value += (it.getAccounts() * it.getDay5());
				}
				break;
			case 6:
				if(it.getDay6() == null){
					it.setDay6(0.0);
				}
				if(isRate){
					value += it.getDay6();
				}else{
					value += (it.getAccounts() * it.getDay6());
				}
				break;
			case 7:
				if(it.getDay7() == null){
					it.setDay7(0.0);
				}
				if(isRate){
					value += it.getDay7();
				}else{
					value += (it.getAccounts() * it.getDay7());
				}
				break;
			case 14:
				if(it.getDay14() == null){
					it.setDay14(0.0);
				}
				if(isRate){
					value += it.getDay14();
				}else{
					value += (it.getAccounts() * it.getDay14());
				}
				break;
			case 30:
				if(it.getDay30() == null){
					it.setDay30(0.0);
				}
				if(isRate){
					value += it.getDay30();
				}else{
					value += (it.getAccounts() * it.getDay30());
				}
				break;
			}
		}
		value = NumberUtil.div(value, (double)count);
		return value;
	}
	
	
	/**
	 * <li>1. 配置触发时间需要跟提供数据方确认几点才能提供指定时间内的数据
	 * 		<p>2016-11-10 : 早上7-8点才出昨天的数据</p>
	 * <li>2. 每日流失要填扩展值,代表是几天的流失,如7,14，30
     * <li>3. 目前只有新增账户和每日付费率可以配百分比的,新增账户可以配百分比(day1平均留存率)或一个整数,每日付费率只能配百分比
	 * @Title: check
	 * @Description: TODO
	 * @param item
	 * @param day
	 * @return
	 * @throws Exception
	 * boolean
	 * @author moxw
	 * @date 2016年11月10日 下午2:41:51
	 */
	private boolean check(LogplatWarningItem item,Date day) throws Exception {
		boolean checkResult = false;
		double value = 0.0;
		BaseSearchData searchData = new BaseSearchData();
		searchData.setGameid(item.getGameid());
		searchData.setStartdate(DateUtil.format2YYYY_MM_DD(DateUtil.beginingOfDay(day)));
		searchData.setEnddate(DateUtil.format2YYYY_MM_DD(DateUtil.endingOfDay(day)));
		HttpSession session = null;
		if(WarningJob.WARNING_EVENT_OF_DEVICE_ACTIVATION.equals(item.getWarning_event_id())) {
			logger.debug("WARNING_EVENT_OF_DEVICE_ACTIVATION checking ... ");
			List<NewPlayers> list = newPlayersService.selectNewPlayerList(searchData, session);
			if(list != null){
				for(NewPlayers np : list){
					if(np != null){
						if(np.getDevices() == null){
							np.setDevices(0);
						}
						value += np.getDevices();
					}
				}
			}
		}else if(WarningJob.WARNING_EVENT_OF_NEW_ACCOUNT_RETAIN.equals(item.getWarning_event_id())){
			logger.debug("WARNING_EVENT_OF_NEW_ACCOUNT_RETAIN checking ... ");
			searchData.searchDataFilter(searchData);
			List<PlayersRetention> list = playersRetentionService.selectRetentionOfNewUser(searchData, session);
			
//			for(PlayersRetention it : list){
//				if(it != null){
//					if(it.getAccounts() == null){
//						it.setAccounts(0);
//					}
//					if(it.getDay1() == null){
//						it.setDay1(0.0);
//					}
//					//扩展值:某一天的几天留存
//					if("0".equals(item.getUnit())){
//						value += it.getDay1();
//						
//					}else{
//						value += it.getAccounts();
//					}
//				}
//			}
//			if("0".equals(item.getUnit())){//day1平均留存率
//				value = NumberUtil.div(value, (double) list.size());
//			}
			boolean isRate = "0".equals(item.getUnit());
			value = calXDayRetain(list,Integer.valueOf(item.getValve2()),isRate);
		}else if(WarningJob.WARNING_EVENT_OF_ACTOR_LOST.equals(item.getWarning_event_id())){
			logger.debug("WARNING_EVENT_OF_ACTOR_LOST checking ... ");
			if(Validator.isNullOrBlank(item.getValve2())){
				logger.error("{}(id={})'s valve2 missing",item.getClass().getSimpleName(),item.getId());
				throw new IllegalArgumentException(item.getClass().getSimpleName() + "(id=" + item.getId() + ")'s valve2 missing");
			}
			searchData.setChecktype1(item.getValve2());//nday
			searchData.setChecktype2("-1");//type
			searchData.searchDataFilter(searchData);
			List<PlayersChurn> list = playersChurnService.selectPerDayLostNumAndRate(searchData, session);
			if(list != null){
				for(PlayersChurn it : list){
//					if(it.getAccounts() == null){
//						it.setAccounts(0);
//					}
					//
//					value += it.getAccounts();
					if(it.getRate() == null){
						it.setRate(0.0);
					}
					value += it.getRate();
				}
			}
			
		}else if(WarningJob.WARNING_EVENT_OF_INCOME.equals(item.getWarning_event_id())){
			logger.debug("WARNING_EVENT_OF_INCOME checking ... ");
			searchData.searchDataFilter(searchData);
			List<IncomeData> list = incomeDataService.selectIncomeNumList(searchData, session);
			if(list != null){
				for(IncomeData it : list){
					if(it.getIncomes() == null){
						it.setIncomes(0.0);
					}
					//
					value += it.getIncomes();
				}
			}
		}else if(WarningJob.WARNING_EVENT_OF_PAY_ACCOUNT_COUNT.equals(item.getWarning_event_id())){
			logger.debug("WARNING_EVENT_OF_PAY_ACCOUNT_COUNT checking ... ");
			searchData.searchDataFilter(searchData);
			List<IncomeData> list = incomeDataService.selectChargePlayerNumList(searchData, session);
			if(list != null){
				for(IncomeData it : list){
					if(it.getPayAccounts() == null){
						it.setPayAccounts(0);
					}
					//
					value += it.getPayAccounts();
				}
			}
		}else if(WarningJob.WARNING_EVENT_OF_DAILY_PAY_RATE.equals(item.getWarning_event_id())){
			logger.debug("WARNING_EVENT_OF_DAILY_PAY_RATE checking ... ");
			List<Conversion> list = conversionService.selectDayPayRateList(searchData, session);
			if(list != null){
				for(Conversion it : list){
					if(it.getDailyPayRate() == null){
						it.setDailyPayRate(0.0);
					}
					it.setDailyPayRate(NumberUtil.mul(it.getDailyPayRate(), 100.0,NumberUtil.DEFAULT_SCALE));
					value += it.getDailyPayRate();
				}
				value = NumberUtil.div(value, (double) list.size());
			}
		}else if(WarningJob.WARNING_EVENT_OF_ACTOR_TRANSFORM.equals(item.getWarning_event_id())){
			logger.debug("WARNING_EVENT_OF_ACTOR_TRANSFORM checking ... ");
			
			return false;
		}else if(WarningJob.WARNING_EVENT_OF_DAILY_ACTIVE_USER.equals(item.getWarning_event_id())){
			logger.debug("WARNING_EVENT_OF_DAILY_ACTIVE_USER checking ... ");
			List<ActivePlayers> list = activePlayersService.selectDauList(searchData, session);
			if(list != null){
				for(ActivePlayers it : list){
					if(it.getCount() == null){
						it.setCount(0);
					}
					value += it.getCount();
				}
			}
		}else if(WarningJob.WARNING_EVENT_OF_NEW_PAY_ACTOR.equals(item.getWarning_event_id())){
			//第一次充值
			logger.debug("WARNING_EVENT_OF_NEW_PAY_ACTOR checking ... ");
			
			List<IncomeData> list = incomeDataService.selectFirstRechargeActor(searchData, session);
			if(list != null){
				for(IncomeData it : list){
					if(it.getPayAccounts() == null){
						it.setPayAccounts(0);
					}
					value += it.getPayAccounts();
					
				}
			}
		}
		int result = ((Double)value).compareTo(item.getValve().doubleValue());
		switch(item.getCmptype()){
		case -1:
			checkResult = result < 0 ? true : false;
			break;
		case 0:
			checkResult = result == 0 ? true : false;
			break;
		case 1:
			checkResult = result > 0 ? true : false;
			break;
		}
		return checkResult;
	}

	public static IWarningEventService getWarningEventService() {
		return warningEventService;
	}

	public static IWarningItemService getWarningItemService() {
		return warningItemService;
	}

	public static IWarningNoticeService getWarningNoticeService() {
		return warningNoticeService;
	}

	public static void setGameService(GameService gameService) {
		WarningNoticeJob.gameService = gameService;
	}

	public static void setNewPlayersService(NewPlayersService newPlayersService) {
		WarningNoticeJob.newPlayersService = newPlayersService;
	}

	public static void setPlayersRetentionService(PlayersRetentionService playersRetentionService) {
		WarningNoticeJob.playersRetentionService = playersRetentionService;
	}

	public static void setPlayersChurnService(PlayersChurnServiceImpl playersChurnService) {
		WarningNoticeJob.playersChurnService = playersChurnService;
	}

	public static void setIncomeDataService(IncomeDataService incomeDataService) {
		WarningNoticeJob.incomeDataService = incomeDataService;
	}

	public static void setLogger(Logger logger) {
		WarningNoticeJob.logger = logger;
	}

	public static void setConversionService(ConversionService conversionService) {
		WarningNoticeJob.conversionService = conversionService;
	}

	public static void setActivePlayersService(ActivePlayersServiceImpl activePlayersService) {
		WarningNoticeJob.activePlayersService = activePlayersService;
	}
	
}
