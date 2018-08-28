package com.good.plat.datacenter.datastat.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class QuartzHelloJob implements Job{

	public static void main(String[] args) throws SchedulerException, InterruptedException  {
		demo2();
	}
	
	public static void demo2() throws SchedulerException, InterruptedException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();
		
		String jobName = "job1";
		String groupName = "group1";
		String triggerName = "trigger1";
		
		JobDetail job = newJob(QuartzHelloJob.class)
				.withIdentity(jobName,groupName)
				.storeDurably(true)
				.build();
		scheduler.addJob(job, true);
		JobDataMap map = new JobDataMap();
		map.put("data", "demo2");
		Trigger trigger = newTrigger()
				.withIdentity(triggerName,groupName)
				.startNow()
				.forJob(job)
				.withSchedule(simpleSchedule().withIntervalInSeconds(2)
						.repeatForever())
				.usingJobData(map)
				.build();
		
		scheduler.scheduleJob(trigger);
		
		map.put("data", "another trigger");
		Trigger trigger2 = newTrigger()
				.withIdentity(triggerName+"2",groupName)
				.startNow()
				.forJob(job)
				.withSchedule(simpleSchedule().withIntervalInSeconds(2)
						.repeatForever())
				.usingJobData(map)
				.build();
		
		scheduler.scheduleJob(trigger2);
		//Exception in thread "main" org.quartz.ObjectAlreadyExistsException: Unable to store Trigger with name: 'trigger1' and group: 'group1', because one already exists with this identification.
		Thread.sleep(1000 * 10);
		scheduler.shutdown();
	}
	
	public static void demo1() throws SchedulerException, InterruptedException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();
		String jobName = "job1";
		String groupName = "group1";
		String triggerName = "trigger1";
		JobDataMap map = new JobDataMap();
		map.put("data", "demo2");
		JobDetail job = newJob(QuartzHelloJob.class)
				.withIdentity(jobName,groupName)
				.build();
		Trigger trigger = newTrigger()
				.withIdentity(triggerName,groupName)
				.startNow()
				.withSchedule(simpleSchedule().withIntervalInSeconds(2)
						.repeatForever())
				.usingJobData(map)
				.build();
		scheduler.scheduleJob(job, trigger);
		
		Thread.sleep(1000 * 10);
		scheduler.shutdown();
	}
	
	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		Trigger trigger = ctx.getTrigger();
		JobDataMap map = ctx.getMergedJobDataMap();
		System.out.println("Quartz Hello World!");
		System.out.println("======================= MergedJobDataMap: " + map.get("data"));
		System.out.println("======================= triggerKey: " + trigger.getKey());
		
	}
}
