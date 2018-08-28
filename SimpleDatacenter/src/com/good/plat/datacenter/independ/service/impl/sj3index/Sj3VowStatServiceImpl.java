package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3VowStat;
import com.good.plat.datacenter.independ.mapper.sj3index.Sj3VowStatMapper;
import com.good.plat.datacenter.independ.service.sj3index.Sj3VowStatService;
@Service(value="sj3VowStatService")
public class Sj3VowStatServiceImpl implements Sj3VowStatService {
	@Autowired
	private Sj3VowStatMapper sj3VowStatMapper;
	@Override
	public List<Sj3VowStat> selectVowStatList(IndependBaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<Sj3VowStat> list=sj3VowStatMapper.selectVowStatList(searchData);
		return list;
	}

}
