package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.independ.entity.sj3index.ActiveStat;
import com.good.plat.datacenter.independ.mapper.sj3index.ActiveStatMapper;
import com.good.plat.datacenter.independ.service.sj3index.ActiveStatService;
@Service(value="activeStatService")
public class ActiveStatServiceImpl implements ActiveStatService {
	@Autowired
	private ActiveStatMapper activeStatMapper;
	@Override
	public List<ActiveStat> selectActiveCntAndRateList(
			IndependBaseSearchData searchData) {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<ActiveStat> list= activeStatMapper.selectActiveCntAndRateList(searchData);
		if(list!=null&&list.size()>0){
			for(ActiveStat e:list){
				e.setActiveRate(e.getActiveRate()==null?0.0:NumberUtil.multi100(e.getActiveRate(), 2));
			}
		}
		return list;
	}

	@Override
	public List<ActiveStat> selectbugBackGoodsList(
			IndependBaseSearchData searchData) {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<ActiveStat> list= activeStatMapper.selectbugBackGoodsList(searchData);
		return list;
	}

}
