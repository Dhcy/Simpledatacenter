package com.good.plat.datacenter.independ.service.impl.yhindex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhArenaStat;
import com.good.plat.datacenter.independ.mapper.yhindex.YhArenaStatMapper;
import com.good.plat.datacenter.independ.service.yhindex.YhArenaStatService;
@Service(value="yhArenaStatService")
public class YhArenaStatServiceImpl implements YhArenaStatService {
	@Autowired
	private YhArenaStatMapper yhArenaStatMapper;
	@Override
	public List<YhArenaStat> selectPerHourOnlineCountStatList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhArenaStat> list=yhArenaStatMapper.selectPerHourOnlineCountStatList(searchData);
		return list;
	}

	@Override
	public List<YhArenaStat> selectPerMateTimeStatList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhArenaStat> list=yhArenaStatMapper.selectPerMateTimeStatList(searchData);
		return list;
	}

	@Override
	public List<YhArenaStat> selectYhDanStatList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhArenaStat> list=yhArenaStatMapper.selectYhDanStatList(searchData);
		return list;
	}

}
