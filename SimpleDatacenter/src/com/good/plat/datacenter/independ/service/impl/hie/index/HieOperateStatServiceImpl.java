package com.good.plat.datacenter.independ.service.impl.hie.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.hie.index.HieActorRecharge;
import com.good.plat.datacenter.independ.entity.hie.index.HieOnLine;
import com.good.plat.datacenter.independ.entity.hie.index.HieOnTimeRecharge;
import com.good.plat.datacenter.independ.entity.hie.index.HieRegistStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieTaskStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieSingleRecharge;
import com.good.plat.datacenter.independ.mapper.hie.index.HieOperateStatMapper;
import com.good.plat.datacenter.independ.service.hie.index.HieOperateStatService;

@Service(value = "HieStatService")
public class HieOperateStatServiceImpl implements HieOperateStatService {
	@Autowired
	private HieOperateStatMapper hieStatMapper;

	@Override
	public List<HieTaskStat> queryTaskStatList(BaseSearchData searchData) {
		
		List<HieTaskStat> resultList=hieStatMapper.queryTaskStatList(searchData);

		return resultList;
	}



	@Override
	public List<HieRegistStat> queryRegistStatList(BaseSearchData searchData) {
		
		List<HieRegistStat> resultList=hieStatMapper.queryRegistStatList(searchData);

		return resultList;
	}



	@Override
	public List<HieActorRecharge> queryFirstRechargeStatList(BaseSearchData searchData) {
		
		List<HieActorRecharge> resultList=hieStatMapper.queryFirstRechargeStatList(searchData);

		return resultList;
	}



	@Override
	public List<HieSingleRecharge> querySingleRechargeList(BaseSearchData searchData) {
		List<HieSingleRecharge> resultList=hieStatMapper.querySingleRechargeList(searchData);

		return resultList;
	}



	@Override
	public List<HieOnTimeRecharge> queryOnTimeRechargeList(BaseSearchData searchData) {
		List<HieOnTimeRecharge> resultList=hieStatMapper.queryOnTimeRechargeList(searchData);

		return resultList;
	}



	@Override
	public List<HieOnLine> queryOnlineList(BaseSearchData searchData) {
		List<HieOnLine> resultList=hieStatMapper.queryOnlineList(searchData);

		return resultList;
	}

}
