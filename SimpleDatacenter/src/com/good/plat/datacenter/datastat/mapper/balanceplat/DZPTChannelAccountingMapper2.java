package com.good.plat.datacenter.datastat.mapper.balanceplat;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.balanceplat.ChannelAccounting;
import com.good.plat.datacenter.datastat.entity.balanceplat.DZPTChannelAccounting;
import com.good.plat.datacenter.datastat.entity.balanceplat.PayChannelAccounting;
import com.good.plat.datacenter.datastat.entity.balanceplat.UscActorRecharge;

public interface DZPTChannelAccountingMapper2 {
	List<PayChannelAccounting> selectPayChannelAccountingList(BaseSearchData searchData);

	List<DZPTChannelAccounting> selectChannelAccountingList(BaseSearchData searchData);

	List<ChannelAccounting> selectAccountingList(BaseSearchData searchData);
	
	List<UscActorRecharge> selectSDKRechargeBillList(BaseSearchData searchData);

	List<ChannelAccounting> selectWeeklyAccountingList(BaseSearchData searchData);
	Integer updateChannelAccountingBySelective(DZPTChannelAccounting ca);
	
	/**
	 * 渠道游戏结算
	 * @Title: selectChannelGameAccountingList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<ChannelAccounting>
	 * @author hwj
	 * @date 2017-5-4 下午06:25:08
	 */
	List<ChannelAccounting> selectChannelGameAccountingList(BaseSearchData searchData)throws Exception;
	/**
	 * 将到期提前1个月发通知的合同
	 * @Title: selectBeforeAMonthContractList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<ChannelAccounting>
	 * @author hwj
	 * @date 2017-12-5 下午06:29:49
	 */
	List<DZPTChannelAccounting> selectWarningNoticeContractList(BaseSearchData searchData)throws Exception;
}