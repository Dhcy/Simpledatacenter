package com.good.plat.datacenter.datastat.quartz.jobs;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.TriggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningItem;
import com.good.plat.datacenter.datastat.service.analysis.IWarningEventService;
import com.good.plat.datacenter.datastat.service.analysis.IWarningItemService;
import com.good.plat.datacenter.datastat.service.analysis.IWarningNoticeService;

/**
 * 
 * @ClassName: NewActorWarningJob
 * @Description: 新增玩家预警通知
 * @author moxw
 * @date 2016年5月18日 上午9:41:19
 */
public class WarningJob extends QuartzJobBean {
	public static final Integer[] WATCHING_ITEMS = new Integer[]{};
	
	public static final Integer WARNING_EVENT_OF_DEVICE_ACTIVATION = 1;		//设备激活
	public static final Integer WARNING_EVENT_OF_NEW_ACCOUNT_RETAIN = 2;	//新增账户留存
	public static final Integer WARNING_EVENT_OF_ACTOR_LOST = 3;			//每日流失
	public static final Integer WARNING_EVENT_OF_INCOME = 4;				//收入金额
	public static final Integer WARNING_EVENT_OF_PAY_ACCOUNT_COUNT = 5;		//充值人数
	public static final Integer WARNING_EVENT_OF_DAILY_PAY_RATE = 6;		//日付费率
	public static final Integer WARNING_EVENT_OF_DAILY_ACTIVE_USER = 7;		//DAU
	public static final Integer WARNING_EVENT_OF_NEW_PAY_ACTOR = 8;			//新增付费玩家  ? 第一次充值 ? 当天注册当天充值? ? 
	public static final Integer WARNING_EVENT_OF_ACTOR_TRANSFORM = -1;		//玩家转化
	
	static Logger logger = LoggerFactory.getLogger(WarningJob.class);
	
	private static IWarningItemService warningItemService;
	
	public void setWarningItemService(IWarningItemService warningItemService) {
		WarningJob.warningItemService = warningItemService;
	}

	@Override
	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
		logger.debug("quartz..... {} ",new Date().toString());
		List<LogplatWarningItem> eventItemlist = null;
		BaseSearchData searchData = new BaseSearchData();
		searchData.setChecktype1("0");
		searchData.searchDataFilter(searchData);
 		eventItemlist = warningItemService.selectAllItems(searchData);
		eventItemlist = eventItemlist == null ? new LinkedList<LogplatWarningItem>() : eventItemlist;
		if(eventItemlist != null){
			Scheduler scheduler = ctx.getScheduler();
			JobDetail job = null;
			String jobName = WarningNoticeJob.class.getName();
			String jobGroup = WarningJob.class.getName();
			job = (JobDetail) newJob(WarningNoticeJob.class)
					.withIdentity(jobName, jobGroup)
					.storeDurably(true)
					.build();
			// durable, 指明任务就算没有绑定Trigger仍保留在Quartz的JobStore中,  
			try {
				if(!scheduler.checkExists(job.getKey())){
					//scheduler.addJob(job, false);
					scheduler.addJob(job, true);
				}
			} catch (SchedulerException e1) {
				e1.printStackTrace();
				logger.error("============================={}",e1.getMessage());
			}
			Calendar runTime = Calendar.getInstance(),now = Calendar.getInstance();
			Trigger trigger = null;
			for(LogplatWarningItem item : eventItemlist){
				if(Arrays.asList(new Integer[]{260,262,263}).contains(item.getId())){
					//logger.info("=============== gameid={},item_id={},itemName={}",item.getGameid(),item.getId(),item.getName());
				}
				//
				Integer id = item.getId();
				Integer gameid = item.getGameid();
				Integer projectid = item.getProjectid();
				
				logger.info("============================= trigger on itemid={},gameid={},projectid={}",id,gameid,projectid);
				TriggerKey triggerKey = new TriggerKey(id.toString(), WarningNoticeJob.class.getName());
				try {
					if(scheduler.checkExists(triggerKey)){
						continue;
					}
				} catch (SchedulerException e1) {
					e1.printStackTrace();
					logger.error("===================={} " , e1.getMessage());
				}
				
				runTime.setTime(item.getLaunch_time());
				if(Arrays.asList(WATCHING_ITEMS).contains(item.getId())){
					runTime = now;
				}
				runTime.set(Calendar.YEAR, now.get(Calendar.YEAR));
				runTime.set(Calendar.MONTH, now.get(Calendar.MONTH));
				runTime.set(Calendar.DAY_OF_YEAR, now.get(Calendar.DAY_OF_YEAR));
				if(runTime.before(now)){
					continue;
				}
				trigger = newTrigger().usingJobData("id", id).usingJobData("gameid", gameid).usingJobData("projectid",projectid)
						.forJob(job.getKey())
						.withIdentity(triggerKey)
						.startAt(runTime.getTime())//在指定的时间点执行
						//.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(1000*15).repeatForever())
						.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24).repeatForever())//每隔24小时执行一次
						.build();
				try{
					//logger.info("==================== scheduler [{},{}]",trigger.getKey() ,job.getKey() );
					if(!scheduler.checkExists(trigger.getKey())){
						if(scheduler.checkExists(job.getKey())){
							scheduler.scheduleJob(trigger);
						}else{
							scheduler.scheduleJob(job,trigger);
						} 
					}else{
						logger.info("==================== trigger [{},{}] already exists!" ,trigger.getKey() ,job.getKey());
					}
				}catch (SchedulerException e){
					//e.printStackTrace();
					logger.info("==================== {}", e.getMessage());
				}
			}
		}
		/*
		Scheduler scheduler = ctx.getScheduler();
		JobDetail job = (JobDetail) newJob(NewActorWarningNoticeJob.class)
				.withIdentity(NewActorWarningNoticeJob.class.getName())
				.build();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 10);
		Date runTime = cal.getTime();
		Trigger trigger = null;
		runTime = cal.getTime();
		trigger = newTrigger().usingJobData("id",System.currentTimeMillis())
				.forJob(job)
				.startAt(runTime)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).withRepeatCount(0))
				.build();
		try{
			scheduler.scheduleJob(job,trigger);
		}catch (SchedulerException e){
			logger.error(e.getMessage());
		}*/
	}
}
