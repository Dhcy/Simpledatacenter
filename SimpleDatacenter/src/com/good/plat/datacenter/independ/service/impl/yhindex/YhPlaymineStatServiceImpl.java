package com.good.plat.datacenter.independ.service.impl.yhindex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhPlaymineStat;
import com.good.plat.datacenter.independ.mapper.yhindex.YhPlaymineStatMapper;
import com.good.plat.datacenter.independ.service.yhindex.YhPlaymineStatService;
@Service(value="yhPlaymineStatService")
public class YhPlaymineStatServiceImpl implements YhPlaymineStatService {
	@Autowired
	private YhPlaymineStatMapper yhPlaymineStatMapper;
	@Override
	public List<YhPlaymineStat> selectPlaymineSuccRateList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhPlaymineStat> list=yhPlaymineStatMapper.selectPlaymineSuccRateList(searchData);
		return list;
	}

}
