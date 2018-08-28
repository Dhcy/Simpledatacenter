package com.good.plat.datacenter.datastat.quartz.jobs;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.base.EmailPlatTool;
import com.good.plat.datacenter.common.base.PropertiesUtil;
import com.good.plat.datacenter.common.base.Validator;
import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningNoticeWithBLOBs;
import com.good.plat.datacenter.datastat.entity.balanceplat.DZPTChannelAccounting;
import com.good.plat.datacenter.datastat.service.balanceplat.IBalancePlatService;


/**
 * 合同到期预警通知
 * @ClassName: ContractWarningNoticeJob
 * @Description: TODO
 * @author hwj
 * @date 2017-12-5 下午01:45:48
 */
public class ContractWarningNoticeJob extends QuartzJobBean {
	static Logger logger = LoggerFactory.getLogger(ContractWarningNoticeJob.class);

	private IBalancePlatService balancePlatService;
	
	public IBalancePlatService getBalancePlatService() {
		return balancePlatService;
	}
	public void setBalancePlatService(IBalancePlatService balancePlatService) {
		this.balancePlatService = balancePlatService;
	}
	@Override
	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
		try{
			logger.info("quartz...合同预警开始...");
			BaseSearchData searchData=new BaseSearchData();
			//提前一个月快到期合同列表
			List<DZPTChannelAccounting> channelContractList=balancePlatService.selectWarningNoticeContractList(searchData);
			if(channelContractList!=null&&!channelContractList.isEmpty()){
			logger.info("合同到期预警要发送邮件数量{}",channelContractList.size());
				for(DZPTChannelAccounting contractInfo:channelContractList){
					sendNotice(contractInfo);
					}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	private void sendNotice(DZPTChannelAccounting contract){
		LogplatWarningNoticeWithBLOBs notice = new LogplatWarningNoticeWithBLOBs();
		//title
		StringBuffer title=new StringBuffer();
		title.append("合同快到期预警通知邮件");
		//content
		StringBuffer content = new StringBuffer();
		content.append("合同到期通知【");
		content.append("游戏:" + contract.getGameName());	// gameName
		content.append("-");
		content.append("渠道名:" + contract.getChannelName());	// channelName
		content.append("-");
		content.append("合同到期时间:"+DateUtil.format_yyyy_MM_dd(contract.getConstractenddate())+"】");	// gameName
		//通知项
		String receiver=PropertiesUtil.getValue("contract_notice_receiver");
		notice.setTitle(title.toString());
		notice.setContent(content.toString());
		notice.setReceiver(receiver);	
		logger.info("邮件标题:{};邮件内容:{};收件人:{}", notice.getTitle(),notice.getContent(),notice.getReceiver());
		//
		int connectTimeout = Integer.valueOf(PropertiesUtil.getValue("email_plat_connect_timeout"));
		int readTimeout = Integer.valueOf(PropertiesUtil.getValue("email_plat_read_timeout"));
		//收件人有才发送
		if(!Validator.isNullOrBlank(notice.getReceiver())){
			String sendRet = EmailPlatTool.sendEmail(notice.getTitle(), notice.getContent(), Arrays.asList(notice.getReceiver().split(",")), connectTimeout, readTimeout);
			logger.info(sendRet);
		}
		
	}
	
	
}
