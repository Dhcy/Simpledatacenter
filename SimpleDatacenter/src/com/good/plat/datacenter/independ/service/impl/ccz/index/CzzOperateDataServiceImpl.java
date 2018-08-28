package com.good.plat.datacenter.independ.service.impl.ccz.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.czz.index.CzzRegistStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieActorRecharge;
import com.good.plat.datacenter.independ.entity.hie.index.HieOnTimeRecharge;
import com.good.plat.datacenter.independ.entity.hie.index.HieSingleRecharge;
import com.good.plat.datacenter.independ.entity.hie.index.HieTaskStat;
import com.good.plat.datacenter.independ.mapper.ccz.index.CzzOperateDataMapper;
import com.good.plat.datacenter.independ.service.ccz.index.CzzOperateDataService;

@Service(value = "czzOperateDataService")
public class CzzOperateDataServiceImpl implements CzzOperateDataService {
	@Autowired
	private CzzOperateDataMapper czzOperateDataMapper;

	@Override
	public List<HieTaskStat> queryTaskStatList(BaseSearchData searchData) {
		
		List<HieTaskStat> resultList=czzOperateDataMapper.queryTaskStatList(searchData);

		return resultList;
	}



	@Override
	public List<CzzRegistStat> queryRegistStatList(BaseSearchData searchData) {
		
		List<CzzRegistStat> resultList=czzOperateDataMapper.queryRegistStatList(searchData);

		return resultList;
	}



	@Override
	public List<HieActorRecharge> queryFirstRechargeStatList(BaseSearchData searchData) {
		
		List<HieActorRecharge> resultList=czzOperateDataMapper.queryFirstRechargeStatList(searchData);

		return resultList;
	}



	@Override
	public List<HieSingleRecharge> querySingleRechargeList(BaseSearchData searchData) {
		List<HieSingleRecharge> resultList=czzOperateDataMapper.querySingleRechargeList(searchData);

		return resultList;
	}



	@Override
	public List<HieOnTimeRecharge> queryOnTimeRechargeList(BaseSearchData searchData) {
		List<HieOnTimeRecharge> resultList=czzOperateDataMapper.queryOnTimeRechargeList(searchData);

		return resultList;
	}

}
