package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.independ.entity.sj3index.IllusStat;
import com.good.plat.datacenter.independ.mapper.sj3index.IllusStatMapper;
import com.good.plat.datacenter.independ.service.sj3index.IllusStatService;
@Service(value="illusStatService")
public class IllusStatServiceImpl implements IllusStatService {
	@Autowired
	private IllusStatMapper illusStatMapper;
	@Override
	public List<IllusStat> selectIllusLuckNumList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<IllusStat> list=illusStatMapper.selectIllusLuckNumList(searchData);
		if(list!=null&&!list.isEmpty()){
			for(IllusStat e:list){
				if(e.getAreamaxluckcnt()==null){
					e.setAreamaxluckcnt(0);
				}
				if(e.getAvgLuckCnt()==null){
					e.setAvgLuckCnt(0);
				}
				//平均激活缘分完成度
				Double avgLuckFinishRate=e.getAreamaxluckcnt()==0?0.0:NumberUtil.div(e.getAvgLuckCnt().doubleValue(), e.getAreamaxluckcnt().doubleValue(),4);
				e.setAvgLuckFinishRate(NumberUtil.multi100(avgLuckFinishRate, 2));
			}
		}
		return list;
	}

}
