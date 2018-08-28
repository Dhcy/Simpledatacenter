package com.good.plat.datacenter.datastat.mapper.balanceplat;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.balanceplat.AppleBalance;

public interface UscActorRechargeMapper2 {
	/**
	 * 
	 * @Title: selectUscActorRechargeBalanceList
	 * @Description: TODO
	 * @param searchData
	 * enddate 需要加  时分秒 '23:59:59'
	 * @return
	 * List<AppleBalance>
	 * @author moxw
	 * @date 2016年8月25日 上午10:48:05
	 */
   List<AppleBalance> selectUscActorRechargeBalanceList(BaseSearchData searchData);
}