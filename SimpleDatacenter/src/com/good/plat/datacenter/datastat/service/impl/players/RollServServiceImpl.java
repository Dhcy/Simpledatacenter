package com.good.plat.datacenter.datastat.service.impl.players;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.players.RollServer;
import com.good.plat.datacenter.datastat.mapper.players.RollServerMapper;
import com.good.plat.datacenter.datastat.service.players.RollServService;
@Service(value="rollServService")
public class RollServServiceImpl implements RollServService {
	@Autowired
	private RollServerMapper rollServerMapper;
	@Override
	public List<RollServer> selectRollServerList(BaseSearchData searchData) {
		// TODO Auto-generated method stub
		searchData = searchData.searchDataFilter(searchData);
		List<RollServer> rollServList= rollServerMapper.selectRollServerList(searchData);
		if(rollServList!=null){
			for(RollServer r:rollServList){
				if(r.getCntRate()==null){
					r.setCntRate(0.0);
				} 
				if(r.getMoneyRate()==null){
					r.setMoneyRate(0.0);
				} 
			}
		}
		 return rollServList;
	}

}
