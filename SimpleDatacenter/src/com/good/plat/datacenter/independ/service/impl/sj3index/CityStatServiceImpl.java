package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.CityStat;
import com.good.plat.datacenter.independ.mapper.sj3index.CityStatMapper;
import com.good.plat.datacenter.independ.service.sj3index.CityStatService;
@Service(value="cityStatService")
public class CityStatServiceImpl implements CityStatService {
	@Autowired
	private CityStatMapper cityStatMapper;
	@Override
	public List<CityStat> selectCityCntDistributeList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<CityStat> list=cityStatMapper.selectCityCntDistributeList(searchData);
		return list;
	}
	@Override
	public List<CityStat> selectCityFightDetailList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<CityStat> list=cityStatMapper.selectCityFightDetailList(searchData);
		return list;
	}
	@Override
	public List<CityStat> selectCityFightSetList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<CityStat> list=cityStatMapper.selectCityFightSetList(searchData);
		if(list!=null&&list.size()>0){
			for(CityStat city:list){
				city.setFightTime(city.getFightCnt().intValue()==0?0:city.getFightTime()/city.getFightCnt());
			}
		}
		return list;
	}

}
