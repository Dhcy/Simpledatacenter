package com.good.plat.datacenter.independ.service.hie.index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.hie.index.HieEctypeStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieLottoStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieMoneyStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieShoppingStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieTaskStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieThingStat;

public interface HieConsumeStatService {
	
	List<HieMoneyStat> queryMoneyStatList(BaseSearchData searchData);

	List<HieLottoStat> queryLottoStatList(BaseSearchData searchData);

	List<HieEctypeStat> queryEctypeStatList(BaseSearchData searchData);

	List<HieShoppingStat> queryShoppingStatList(BaseSearchData searchData);

	List<HieMoneyStat> queryGoldStatList(BaseSearchData searchData);

	List<HieThingStat> queryThingStatList(BaseSearchData searchData);

}
