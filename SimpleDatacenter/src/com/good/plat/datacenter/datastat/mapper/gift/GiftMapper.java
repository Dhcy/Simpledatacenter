package com.good.plat.datacenter.datastat.mapper.gift;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.gift.GiftData;

public interface GiftMapper {
	/**
	 * 
	 * @Title: selectGiftReceive
	 * @Description: 礼包领取
	 * @param searchData
	 * @return
	 * List<GiftData>
	 * @author moxw
	 * @date 2016年4月25日 下午4:05:33
	 */
	List<GiftData> selectGiftReceive(BaseSearchData searchData);
	
	/**
	 * 
	 * @Title: selectGiftReceiveDetail
	 * @Description: 礼包详情
	 * @param searchData
	 * @return
	 * List<GiftData>
	 * @author moxw
	 * @date 2016年4月25日 下午4:05:33
	 */
	List<GiftData> selectGiftReceiveDetail(BaseSearchData searchData);
}
