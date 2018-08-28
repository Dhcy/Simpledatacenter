package com.good.plat.datacenter.independ.service.impl.yhindex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhLevelStat;
import com.good.plat.datacenter.independ.mapper.yhindex.YhLevelStatMapper;
import com.good.plat.datacenter.independ.service.yhindex.YhLevelStatService;
@Service(value="yhLevelStatService")
public class YhLevelStatServiceImpl implements YhLevelStatService {
	@Autowired
	private YhLevelStatMapper YhLevelStatMapper;
	/* (非 Javadoc) 
	* <p>Title: selectYhLevelDistributeList</p> 
	* <p>Description: </p> 
	* @param searchData
	* @return
	* @throws Exception 
	* @see com.good.plat.datacenter.independ.service.yhindex.YhLevelStatService#selectYhLevelDistributeList(com.good.plat.datacenter.common.base.IndependBaseSearchData) 
	*/ 
	@Override
	public List<YhLevelStat> selectYhLevelDistributeList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhLevelStat> list=YhLevelStatMapper.selectYhLevelDistributeList(searchData);
		if(list!=null&&!list.isEmpty()){
			for(YhLevelStat e:list){
				if(e.getLvl_1to10UserRate()==null){
					e.setLvl_1to10UserRate(0.0);
				}
				if(e.getLvl_11to20UserRate()==null){
					e.setLvl_11to20UserRate(0.0);
				}
				if(e.getLvl_21to30UserRate()==null){
					e.setLvl_21to30UserRate(0.0);
				}
				if(e.getLvl_31to40UserRate()==null){
					e.setLvl_31to40UserRate(0.0);
				}
				if(e.getLvl_41to50UserRate()==null){
					e.setLvl_41to50UserRate(0.0);
				}
				if(e.getLvl_51to60UserRate()==null){
					e.setLvl_51to60UserRate(0.0);
				}
			}
		}
		return list;
	}

}
