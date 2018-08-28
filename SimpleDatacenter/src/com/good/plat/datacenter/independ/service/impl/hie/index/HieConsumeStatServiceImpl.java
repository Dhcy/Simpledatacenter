package com.good.plat.datacenter.independ.service.impl.hie.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.hie.index.HieEctypeStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieLottoStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieMoneyStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieShoppingStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieTaskStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieThingStat;
import com.good.plat.datacenter.independ.mapper.hie.index.HieConsumeStatMapper;
import com.good.plat.datacenter.independ.service.hie.index.HieConsumeStatService;

@Service(value = "HieCountStatService")
public class HieConsumeStatServiceImpl implements HieConsumeStatService{
	@Autowired
	private HieConsumeStatMapper hieCountStatMapper;
	
	@Override
	public List<HieMoneyStat> queryMoneyStatList(BaseSearchData searchData) {
		
		List<HieMoneyStat> resultList=hieCountStatMapper.queryMoneyStatList(searchData);

		return resultList;
	}

	@Override
	public List<HieLottoStat> queryLottoStatList(BaseSearchData searchData) {
		
		List<HieLottoStat> resultList=hieCountStatMapper.queryLottoStatList(searchData);

		return resultList;
	}

	@Override
	public List<HieEctypeStat> queryEctypeStatList(BaseSearchData searchData) {
		
		List<HieEctypeStat> resultList=hieCountStatMapper.queryEctypeStatList(searchData);

		return resultList;
	}

	@Override
	public List<HieShoppingStat> queryShoppingStatList(BaseSearchData searchData) {
		
		List<HieShoppingStat> resultList=hieCountStatMapper.queryShoppingStatList(searchData);

		return resultList;
	}

	@Override
	public List<HieMoneyStat> queryGoldStatList(BaseSearchData searchData) {
		
		List<HieMoneyStat> resultList=hieCountStatMapper.queryGoldStatList(searchData);

		return resultList;
	}

	@Override
	public List<HieThingStat> queryThingStatList(BaseSearchData searchData) {
		
		List<HieThingStat> resultList=hieCountStatMapper.queryThingStatList(searchData);

		return resultList;
	}

}
