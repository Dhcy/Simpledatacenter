package com.good.plat.datacenter.independ.service.impl.yhindex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhAchieveStat;
import com.good.plat.datacenter.independ.mapper.yhindex.YhAchieveStatMapper;
import com.good.plat.datacenter.independ.service.yhindex.YhAchieveStatService;
@Service(value="yhAchieveStatService")
public class YhAchieveStatServiceImpl implements YhAchieveStatService {
	@Autowired
	private YhAchieveStatMapper yhAchieveStatMapper;
	@Override
	public List<YhAchieveStat> selectyhAchieveStatList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhAchieveStat> list=yhAchieveStatMapper.selectyhAchieveStatList(searchData);
		return list;
	}

}
