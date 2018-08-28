package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.independ.entity.sj3index.AwardSummary;
import com.good.plat.datacenter.independ.mapper.sj3index.AwardSummaryMapper;
import com.good.plat.datacenter.independ.service.sj3index.AwardService;
@Service(value="awardService")
public class AwardServiceImpl implements AwardService {
	@Autowired
	private AwardSummaryMapper awardSummaryMapper;
	@Override
	public List<AwardSummary> selectAwardSummaryList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<AwardSummary> list=awardSummaryMapper.selectAwardSummaryList(searchData);
		if(list!=null&&list.size()>0){
			for(AwardSummary e:list){
				if(e.getRate()==null){
					e.setRate(0.0);
				}
				e.setRate(NumberUtil.multi100(e.getRate(), NumberUtil.DEFAULT_SCALE));
			}
		}
		return list;
	}

}
