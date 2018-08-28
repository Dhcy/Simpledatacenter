package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3ModelStat;
import com.good.plat.datacenter.independ.mapper.sj3index.Sj3ModelStatMapper;
import com.good.plat.datacenter.independ.service.sj3index.Sj3ModelStatService;
@Service(value="sj3ModelStatService")
public class Sj3ModelStatServiceImpl implements Sj3ModelStatService {
	@Autowired
	private Sj3ModelStatMapper sj3ModelStatMapper;
	@Override
	public List<Sj3ModelStat> selectModelStatList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<Sj3ModelStat> list=sj3ModelStatMapper.selectModelStatList(searchData);
		return list;
	}

}
