package com.good.plat.datacenter.datastat.service.impl.concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.concurrent.ConcurrentData;
import com.good.plat.datacenter.datastat.mapper.concurrent.ConcurrentMapper;
import com.good.plat.datacenter.datastat.service.concurrent.IConcurrentService;

@Service(value="concurrentService")
public class ConcurrentServiceImpl implements IConcurrentService {
	@Autowired
	ConcurrentMapper concurrentMapper;
	
	@Override
	public List<ConcurrentData> selectConcurrentList(BaseSearchData searchData) throws Exception {
		searchData.searchDataFilter(searchData);
		return concurrentMapper.selectConcurrentList(searchData);
	}

	@Override
	public List<ConcurrentData> selectDurationDistributeList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<ConcurrentData> durationList=new ArrayList<ConcurrentData>();
		durationList=concurrentMapper.selectDurationDistributeList(searchData);
		return durationList;
	}

	@Override
	public List<ConcurrentData> selectTimeDistributeList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<ConcurrentData> timeList=new ArrayList<ConcurrentData>();
		//日期间隔+1
		int day=DateUtil.getDateInterval(searchData.getStartdate(), searchData.getEnddate())+1;
		timeList=concurrentMapper.selectTimeDistributeList(searchData);
		if(timeList!=null){
			for(ConcurrentData c:timeList){
				double avg_value=(c.getStart_count().doubleValue()/day);//时段的均值
				c.setAvg_time_count(NumberUtil.ajustScale(avg_value, 2));
			}
		}
		return timeList;
	}

	@Override
	public List<ConcurrentData> selectTodayAndYesterdayHourList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<ConcurrentData>  hourList=concurrentMapper.selectTodayAndYesterdayHourList(searchData);
		return hourList;
	}

	@Override
	public ConcurrentData getMaxOfPcu(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		ConcurrentData maxPcu=concurrentMapper.getMaxOfPcu(searchData);
		if(maxPcu!=null){
			if(maxPcu.getPcu()==null){
				maxPcu.setPcu(0);
			}
		}
		return maxPcu;
	}

	@Override
	public ConcurrentData selectBaseGameConfig(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		ConcurrentData data=concurrentMapper.selectBaseGameConfig(searchData);
		return data;
	}

	@Override
	public List<ConcurrentData> selectDurationAcuList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<ConcurrentData> acuList=concurrentMapper.selectDurationAcuList(searchData);
		return acuList;
	}
	
}
