package com.good.plat.datacenter.datastat.mapper.revenue;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.revenue.PayRetention;


/**
 * 付费留存
 * @ClassName: PayRetentionMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-1-19 上午10:58:39
 */
public interface PayRetentionMapper {
	
	/**
	 * 
	 * @Title: selectRetationOfPayPlayer
	 * @Description: 付费玩家留存或者后续付费率
	 * @param searchData
	 * @return
	 * List<PayRetention>
	 * @author hwj
	 * @date 2017-1-19 上午11:00:57
	 */
	List<PayRetention> selectRateOfRatateAndPay(BaseSearchData searchData);
	
	
}
