package com.good.plat.datacenter.independ.service.ccz.index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.czz.index.CzzDiamond;
import com.good.plat.datacenter.independ.entity.czz.index.CzzEctypeStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieLottoStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieMoneyStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieShoppingStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieThingStat;

public interface CzzCostDataService {
	
	List<CzzDiamond> queryMoneyStatList(BaseSearchData searchData);

	List<HieLottoStat> queryLottoStatList(BaseSearchData searchData);

	List<CzzEctypeStat> queryEctypeStatList(BaseSearchData searchData);

	List<HieShoppingStat> queryShoppingStatList(BaseSearchData searchData);

	List<HieMoneyStat> queryGoldStatList(BaseSearchData searchData);

	List<HieThingStat> queryThingStatList(BaseSearchData searchData);

}
