package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3NewActTaskStat;
import com.good.plat.datacenter.independ.mapper.sj3index.Sj3NewActTaskStatMapper;
import com.good.plat.datacenter.independ.service.sj3index.Sj3NewActTaskStatService;
@Service(value="sj3NewActTaskStatService")
public class Sj3NewActTaskStatServiceImpl implements Sj3NewActTaskStatService {
	@Autowired
	private Sj3NewActTaskStatMapper sj3NewActTaskStatMapper;
	@Override
	public List<Sj3NewActTaskStat> selectNewActTaskList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<Sj3NewActTaskStat> list=sj3NewActTaskStatMapper.selectNewActTaskList(searchData);
		return list;
	}
	@Override
	public List<Sj3NewActTaskStat> selectNewActTotalFinishCountList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<Sj3NewActTaskStat> list=sj3NewActTaskStatMapper.selectNewActTotalFinishCountList(searchData);
		if(list!=null&&!list.isEmpty()){
			for(Sj3NewActTaskStat e:list){
				if(e.getMissioncount()==null){
					e.setMissioncount(0);
				}
				if(e.getUsercnt()==null){
					e.setUsercnt(0);
				}
				e.setAvgMissionCount(e.getUsercnt()==0?0.0:NumberUtil.div(e.getMissioncount().doubleValue(), e.getUsercnt().doubleValue(), 0));
			}
		}
		return list;
	}

}
