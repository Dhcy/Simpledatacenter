package com.good.plat.datacenter.datastat.quartz.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class HelloQuartzJobBean extends QuartzJobBean {
	static Logger logger = LoggerFactory.getLogger(HelloQuartzJobBean.class);
	private static Object object2BeInjected;
	private int timeout;

    /**
     * Setter called after the ExampleJob is instantiated
     * with the value from the JobDetailFactoryBean (5)
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
	
	public static void setObject2BeInjected(Object object2BeInjected) {
		HelloQuartzJobBean.object2BeInjected = object2BeInjected;
	}
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		logger.debug("{}",timeout);
		System.out.println(object2BeInjected);
	}
}
