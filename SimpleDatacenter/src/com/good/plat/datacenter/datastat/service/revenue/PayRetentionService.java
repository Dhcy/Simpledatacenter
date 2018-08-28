package com.good.plat.datacenter.datastat.service.revenue;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.revenue.PayRetention;

public interface PayRetentionService {
	/**
	 * 
	 * @Title: selectRetationOfPayPlayer
	 * @Description: 付费玩家留存
	 * @param searchData
	 * @return
	 * List<PayRetention>
	 * @author hwj
	 * @date 2017-1-19 上午11:00:57
	 */
	List<PayRetention> selectRetationOfPayPlayerList(BaseSearchData searchData) throws Exception;
	
	/**
	 * 
	 * @Title: selectPayRateOfFollow
	 * @Description: 后续付费率
	 * @param searchData
	 * @return
	 * List<PayRetention>
	 * @author hwj
	 * @date 2017-1-19 上午11:01:22
	 */
	List<PayRetention> selectPayRateOfFollowList(BaseSearchData searchData)throws Exception;
}
