package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.independ.entity.sj3index.MwSummary;
import com.good.plat.datacenter.independ.mapper.sj3index.MwSummaryMapper;
import com.good.plat.datacenter.independ.service.sj3index.MwSummaryService;
@Service(value="mwSummaryService")
public class MwSummaryServiceImpl implements MwSummaryService {
	@Autowired
	private MwSummaryMapper mwSummaryMapper;
	@Override
	public List<MwSummary> selectMwActList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<MwSummary> list=mwSummaryMapper.selectMwActList(searchData);
		if(list!=null&&list.size()!=0){
			for(MwSummary e:list){
				Double rate=e.getTotalActor().intValue()==0?0.0:e.getUserCnt().doubleValue()/e.getTotalActor();
				Double unActiveRate=e.getTotalActor().intValue()==0?0.0:e.getUnUserCnt().doubleValue()/e.getTotalActor();
				e.setRate(NumberUtil.multi100(rate, NumberUtil.DEFAULT_SCALE));
				e.setUnActiveRate(NumberUtil.multi100(unActiveRate, NumberUtil.DEFAULT_SCALE));
			}
		}
		return list;
	}

}
