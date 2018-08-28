package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3MonthCardStat;
import com.good.plat.datacenter.independ.mapper.sj3index.Sj3MonthCardStatMapper;
import com.good.plat.datacenter.independ.service.sj3index.Sj3MonthCardStatService;
@Service(value="sj3MonthCardStatService")
public class Sj3MonthCardStatImpl implements Sj3MonthCardStatService {
	@Autowired
	private Sj3MonthCardStatMapper sj3MonthCardStatMapper;

	@Override
	public List<Sj3MonthCardStat> selectMonthCardStatList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<Sj3MonthCardStat> list=sj3MonthCardStatMapper.selectMonthCardStatList(searchData);
		if(list!=null&&!list.isEmpty()){
			for(Sj3MonthCardStat monthcard:list){
				if(monthcard.getActCnt()==0){
					monthcard.setAvgCount(0.0);
				}else{
					Double avgCount=NumberUtil.div(monthcard.getCount().doubleValue(), monthcard.getActCnt().doubleValue(), 2);
					monthcard.setAvgCount(avgCount);
				}
			}
		}
		return list;
	}

}
