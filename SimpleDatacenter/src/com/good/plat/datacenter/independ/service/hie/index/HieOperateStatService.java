package com.good.plat.datacenter.independ.service.hie.index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.hie.index.HieActorRecharge;
import com.good.plat.datacenter.independ.entity.hie.index.HieOnLine;
import com.good.plat.datacenter.independ.entity.hie.index.HieOnTimeRecharge;
import com.good.plat.datacenter.independ.entity.hie.index.HieRegistStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieTaskStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieSingleRecharge;

public interface HieOperateStatService {

	List<HieTaskStat> queryTaskStatList(BaseSearchData searchData);


	List<HieRegistStat> queryRegistStatList(BaseSearchData searchData);


	List<HieActorRecharge> queryFirstRechargeStatList(BaseSearchData searchData);


	List<HieSingleRecharge> querySingleRechargeList(BaseSearchData searchData);


	List<HieOnTimeRecharge> queryOnTimeRechargeList(BaseSearchData searchData);


	List<HieOnLine> queryOnlineList(BaseSearchData searchData);

}
