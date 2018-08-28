package com.good.plat.datacenter.independ.service.ccz.index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.czz.index.CzzRegistStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieActorRecharge;
import com.good.plat.datacenter.independ.entity.hie.index.HieOnTimeRecharge;
import com.good.plat.datacenter.independ.entity.hie.index.HieSingleRecharge;
import com.good.plat.datacenter.independ.entity.hie.index.HieTaskStat;

public interface CzzOperateDataService {

	List<HieTaskStat> queryTaskStatList(BaseSearchData searchData);


	List<CzzRegistStat> queryRegistStatList(BaseSearchData searchData);


	List<HieActorRecharge> queryFirstRechargeStatList(BaseSearchData searchData);


	List<HieSingleRecharge> querySingleRechargeList(BaseSearchData searchData);


	List<HieOnTimeRecharge> queryOnTimeRechargeList(BaseSearchData searchData);

}
