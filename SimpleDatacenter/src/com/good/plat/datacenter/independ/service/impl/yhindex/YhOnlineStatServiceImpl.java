package com.good.plat.datacenter.independ.service.impl.yhindex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhOnlineStat;
import com.good.plat.datacenter.independ.mapper.yhindex.YhOnlineStatMapper;
import com.good.plat.datacenter.independ.service.yhindex.YhOnlineStatService;
@Service(value="yhOnlineStatService")
public class YhOnlineStatServiceImpl implements YhOnlineStatService {
	@Autowired
	private YhOnlineStatMapper yhOnlineStatMapper;
	@Override
	public List<YhOnlineStat> selectYhOnlineStatList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhOnlineStat> list=yhOnlineStatMapper.selectYhOnlineStatList(searchData);
		if(list!=null&&!list.isEmpty()){
			for(YhOnlineStat e:list){
				if(e.getMinutes_0to9UserRate()==null){
					e.setMinutes_0to9UserRate(0.0);
				}
				if(e.getMinutes_10to19UserRate()==null){
					e.setMinutes_10to19UserRate(0.0);
				}
				if(e.getMinutes_20to29UserRate()==null){
					e.setMinutes_20to29UserRate(0.0);
				}
				if(e.getMinutes_30to59UserRate()==null){
					e.setMinutes_30to59UserRate(0.0);
				}
				if(e.getMinutes_60to89UserRate()==null){
					e.setMinutes_60to89UserRate(0.0);
				}
				if(e.getMinutes_90to119UserRate()==null){
					e.setMinutes_90to119UserRate(0.0);
				}
				if(e.getMinutes_120to149UserRate()==null){
					e.setMinutes_120to149UserRate(0.0);
				}
				if(e.getMinutes_150to179UserRate()==null){
					e.setMinutes_150to179UserRate(0.0);
				}
				if(e.getMinutes_180to239UserRate()==null){
					e.setMinutes_180to239UserRate(0.0);
				}
				if(e.getMinutes_240toUserRate()==null){
					e.setMinutes_240toUserRate(0.0);
				}
			}
		}
		return list;
	}
	@Override
	public List<YhOnlineStat> selectYhLoginTimesList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhOnlineStat> list=yhOnlineStatMapper.selectYhLoginTimesList(searchData);
		if(list!=null&&!list.isEmpty()){
			for(YhOnlineStat e:list){
				if(e.getLoginTimes0UserRate()==null){
					e.setLoginTimes0UserRate(0.0);
				}
				if(e.getLoginTimes1to2UserRate()==null){
					e.setLoginTimes1to2UserRate(0.0);
				}
				if(e.getLoginTimes3to5UserRate()==null){
					e.setLoginTimes3to5UserRate(0.0);
				}
				if(e.getLoginTimes6to10UserRate()==null){
					e.setLoginTimes6to10UserRate(0.0);
				}
				if(e.getLoginTimes11toUserRate()==null){
					e.setLoginTimes11toUserRate(0.0);
				}
			}
		}
		return list;
	}

}
