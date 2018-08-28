package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.DailySummary;
import com.good.plat.datacenter.independ.mapper.sj3index.DailySummaryMapper;
import com.good.plat.datacenter.independ.service.sj3index.DailySummaryService;
@Service(value="dailyService")
public class DailySummaryServiceImpl implements DailySummaryService {
	@Autowired
	private DailySummaryMapper dailyMapper;
	@Override
	public List<DailySummary> selectActiveList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		 List<DailySummary> list=dailyMapper.selectActiveList(searchData);
		return list;
	}

}
