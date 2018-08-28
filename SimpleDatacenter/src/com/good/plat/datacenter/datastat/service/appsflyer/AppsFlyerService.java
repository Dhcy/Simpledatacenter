package com.good.plat.datacenter.datastat.service.appsflyer;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.datastat.entity.appsflyer.AppsFlyer;

public interface AppsFlyerService {
	
	/**
	 * 添加/记录af推送数据
	 * @Title: addAppsFlyer
	 * @Description: TODO
	 * @param af
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2017-6-2 下午03:52:50
	 */
	public BaseMessage addAppsFlyer(AppsFlyer af)throws Exception;
	
	/**
	 * 查询af_purchase的推送数据
	 * @Title: selectAfPurchaseList
	 * @Description: TODO
	 * @param af
	 * @return
	 * @throws Exception
	 * List<AppsFlyer>
	 * @author hwj
	 * @date 2017-6-8 下午03:47:11
	 */
	public List<AppsFlyer> selectAfPurchaseList(AppsFlyer af)throws Exception;
	

}
