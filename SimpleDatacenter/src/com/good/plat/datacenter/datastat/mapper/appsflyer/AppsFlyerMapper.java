package com.good.plat.datacenter.datastat.mapper.appsflyer;

import java.util.List;

import com.good.plat.datacenter.datastat.entity.appsflyer.AppsFlyer;

/**
 * appsflyer推送数据
 * @ClassName: AppsFlyerMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-6-2 下午03:58:22
 */
public interface AppsFlyerMapper {
	/**
	 * 添加af推送数据
	 * @Title: addAppsFlyer
	 * @Description: TODO
	 * @param af
	 * @return
	 * @throws Exception
	 * int
	 * @author hwj
	 * @date 2017-6-2 下午03:55:52
	 */
	int addAppsFlyer(AppsFlyer af)throws Exception;
	/**
	 * 查询appsFlyer推送数据列表
	 * @Title: selectAppsFlyerList
	 * @Description: TODO
	 * @param af
	 * @return
	 * @throws Exception
	 * List<AppsFlyer>
	 * @author hwj
	 * @date 2017-6-8 下午03:39:40
	 */
	List<AppsFlyer> selectAppsFlyerList(AppsFlyer af)throws Exception;

}
