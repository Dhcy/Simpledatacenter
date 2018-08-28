package com.good.plat.datacenter.datastat.service.impl.realtimedata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.realtimedata.RealTimeSummaryData;
import com.good.plat.datacenter.datastat.mapper.realtimedata.RealTimeSummaryDataMapper;
import com.good.plat.datacenter.datastat.service.realtimedata.RealTimeSummaryDataService;

@Service(value="realTimeSummaryDataService")
public class RealTimeSummaryDataServiceImpl implements RealTimeSummaryDataService{
	@Autowired
	private RealTimeSummaryDataMapper realTimeSummaryDataMapper;
	@Override
	public List<RealTimeSummaryData> selectRalTimeSummaryDataList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stubs
		searchData=searchData.searchDataFilter(searchData);
		List<RealTimeSummaryData> list=realTimeSummaryDataMapper.selectRalTimeSummaryDataList(searchData);
		return list;
	}

}
