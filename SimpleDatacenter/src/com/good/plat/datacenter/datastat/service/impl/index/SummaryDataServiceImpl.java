package com.good.plat.datacenter.datastat.service.impl.index;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.index.SummaryData;
import com.good.plat.datacenter.datastat.mapper.index.SummaryDataMapper;
import com.good.plat.datacenter.datastat.service.index.SummaryDataService;

@Service(value="summaryDataService")
public class SummaryDataServiceImpl implements SummaryDataService{

	@Autowired
	private SummaryDataMapper summaryDataMapper;
	
	@Override
	public SummaryData selectSummaryData(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		SummaryData data = summaryDataMapper.selectSummaryData(searchData);
		return data;
	}

	@Override
	public List<SummaryData> selectNewPlayerList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		
		List<SummaryData> resultList = new ArrayList<SummaryData>();
		List<SummaryData> dataList = new ArrayList<SummaryData>();
		dataList =  summaryDataMapper.selectNewPlayerList(searchData);
		
		if (dataList != null && dataList.size() > 0) {
			SummaryData summaryData = new SummaryData();
			for (SummaryData data : dataList) {
				summaryData.setDeviceSUM(data.getDeviceCount() + summaryData.getDeviceSUM());
				summaryData.setAccountSUM(data.getUserCount() + summaryData.getAccountSUM());
			}
			summaryData.setDevicAVG(summaryData.getDeviceSUM()/dataList.size());
			summaryData.setAccountAVG(summaryData.getAccountSUM()/dataList.size());
			
			summaryData.setDataList(dataList);
			
			resultList.add(summaryData);
		}
		
		return resultList;
	}

	@Override
	public List<SummaryData> exportNewPlayerList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return summaryDataMapper.exportNewPlayerList(searchData);
	}

	@Override
	public List<SummaryData> selectActivePlayerList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		
		List<SummaryData> resultList = new ArrayList<SummaryData>();
		List<SummaryData> dataList = new ArrayList<SummaryData>();
		dataList =  summaryDataMapper.selectActivePlayerList(searchData);
		
		if (dataList != null && dataList.size() > 0) {
			SummaryData summaryData = new SummaryData();
			for (SummaryData data : dataList) {
				summaryData.setActiveUserSUM(data.getActiveUserSUM() + summaryData.getActiveUserSUM());
			}
			summaryData.setActiveUserAVG(summaryData.getActiveUserSUM()/dataList.size());
			
			summaryData.setDataList(dataList);
			
			resultList.add(summaryData);
		}
		
		return resultList;
	}

	@Override
	public List<SummaryData> exportActivePlayerList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return summaryDataMapper.exportActivePlayerList(searchData);
	}

	@Override
	public List<SummaryData> selectPaidPlayerList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		
		List<SummaryData> resultList = new ArrayList<SummaryData>();
		List<SummaryData> dataList = new ArrayList<SummaryData>();
		dataList =  summaryDataMapper.selectPaidPlayerList(searchData);
		
		if (dataList != null && dataList.size() > 0) {
			SummaryData summaryData = new SummaryData();
			for (SummaryData data : dataList) {
				summaryData.setPayUserSUM(data.getPayUserSUM() + summaryData.getPayUserSUM());
			}
			summaryData.setPayUserAVG(summaryData.getPayUserSUM()/dataList.size());
			
			summaryData.setDataList(dataList);
			
			resultList.add(summaryData);
		}
		
		return resultList;
	}

	@Override
	public List<SummaryData> exportPaidPlayerList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return summaryDataMapper.exportPaidPlayerList(searchData);
	}

	@Override
	public List<SummaryData> selectIncomeNumList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		
		List<SummaryData> resultList = new ArrayList<SummaryData>();
		List<SummaryData> dataList = new ArrayList<SummaryData>();
		dataList =  summaryDataMapper.selectIncomeNumList(searchData);
		
		if (dataList != null && dataList.size() > 0) {
			SummaryData summaryData = new SummaryData();
			for (SummaryData data : dataList) {
				summaryData.setIncomeSUM(NumberUtil.add(data.getIncome(), summaryData.getIncomeSUM()));
			}
			summaryData.setIncomeAVG(NumberUtil.div(summaryData.getIncomeSUM(), (double)dataList.size(), 2));
			
			summaryData.setDataList(dataList);
			
			resultList.add(summaryData);
		}
		
		return resultList;
		
	}

	@Override
	public List<SummaryData> exportIncomeNumList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return summaryDataMapper.exportIncomeNumList(searchData);
	}

	@Override
	public List<SummaryData> selectDailyRateOfPay(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		
		List<SummaryData> resultList = new ArrayList<SummaryData>();
		List<SummaryData> dataList = new ArrayList<SummaryData>();
		dataList =  summaryDataMapper.selectDailyRateOfPay(searchData);
		
		if (dataList != null && dataList.size() > 0) {
			SummaryData summaryData = new SummaryData();
			for (SummaryData data : dataList) {
				summaryData.setPayRate(NumberUtil.add(data.getPayRate(), summaryData.getPayRate()));
			}
			summaryData.setPayAVG(NumberUtil.div(summaryData.getPayRate(), (double)dataList.size(), 2));
			
			summaryData.setDataList(dataList);
			
			resultList.add(summaryData);
		}
		
		return resultList;
		
	}

	@Override
	public List<SummaryData> exportDailyRateOfPay(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return summaryDataMapper.exportDailyRateOfPay(searchData);
	}

	@Override
	public List<SummaryData> selectDailyARPU(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		
		List<SummaryData> resultList = new ArrayList<SummaryData>();
		List<SummaryData> dataList = new ArrayList<SummaryData>();
		dataList =  summaryDataMapper.selectDailyARPU(searchData);
		
		if (dataList != null && dataList.size() > 0) {
			SummaryData summaryData = new SummaryData();
			for (SummaryData data : dataList) {
				summaryData.setDayARPU(NumberUtil.add(data.getDayARPU(), summaryData.getDayARPU()));
			}
			summaryData.setPayAVG(NumberUtil.div(summaryData.getDayARPU(), (double)dataList.size(), 2));
			
			summaryData.setDataList(dataList);
			
			resultList.add(summaryData);
		}
		
		return resultList;
	}

	@Override
	public List<SummaryData> exportDailyARPU(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return summaryDataMapper.exportDailyARPU(searchData);
	}

	@Override
	public List<SummaryData> selectDailyARPPU(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		
		List<SummaryData> resultList = new ArrayList<SummaryData>();
		List<SummaryData> dataList = new ArrayList<SummaryData>();
		dataList =  summaryDataMapper.selectDailyARPPU(searchData);
		
		if (dataList != null && dataList.size() > 0) {
			SummaryData summaryData = new SummaryData();
			for (SummaryData data : dataList) {
				summaryData.setDayARPPU(NumberUtil.add(data.getDayARPPU(), summaryData.getDayARPPU()));
			}
			summaryData.setPayAVG(NumberUtil.div(summaryData.getDayARPPU(), (double)dataList.size(), 2));
			
			summaryData.setDataList(dataList);
			
			resultList.add(summaryData);
		}
		
		return resultList;
	}

	@Override
	public List<SummaryData> exportDailyARPPU(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return summaryDataMapper.exportDailyARPPU(searchData);
	}
	
	@Override
	public List<SummaryData> selectNewUserRetention(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		
		List<SummaryData> resultList = new ArrayList<SummaryData>();
		List<SummaryData> dataList = new ArrayList<SummaryData>();
		dataList =  summaryDataMapper.selectNewUserRetention(searchData);
		
		if (dataList != null && dataList.size() > 0) {
			SummaryData summaryData = new SummaryData();
			for (SummaryData data : dataList) {
				summaryData.setUserDay2(NumberUtil.add(data.getUserDay2(), summaryData.getUserDay2()));
				summaryData.setUserDay7(NumberUtil.add(data.getUserDay7(), summaryData.getUserDay7()));
				summaryData.setUserDay30(NumberUtil.add(data.getUserDay30(), summaryData.getUserDay30()));
			}
			
			summaryData.setDay2AVG(NumberUtil.div(summaryData.getUserDay2(), (double)dataList.size(), 2));
			summaryData.setDay7AVG(NumberUtil.div(summaryData.getUserDay7(), (double)dataList.size(), 2));
			summaryData.setDay30AVG(NumberUtil.div(summaryData.getUserDay30(), (double)dataList.size(), 2));
			
			summaryData.setDataList(dataList);
			
			resultList.add(summaryData);
		}
		
		return resultList;
	}

	@Override
	public List<SummaryData> exportNewUserRetention(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return summaryDataMapper.exportNewUserRetention(searchData);
	}

	@Override
	public List<SummaryData> selectDeviceRetention(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return summaryDataMapper.selectDeviceRetention(searchData);
	}

	@Override
	public List<SummaryData> exportDeviceRetention(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return summaryDataMapper.exportDeviceRetention(searchData);
	}

	@Override
	public List<SummaryData> selectAvgGameTimesList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		
		List<SummaryData> resultList = new ArrayList<SummaryData>();
		List<SummaryData> dataList = new ArrayList<SummaryData>();
		dataList =  summaryDataMapper.selectAvgGameTimesList(searchData);
		
		if (dataList != null && dataList.size() > 0) {
			SummaryData summaryData = new SummaryData();
			
			for (SummaryData data : dataList) {
				summaryData.setGameTimes(data.getGameTimes()+summaryData.getGameTimes());
			}
			summaryData.setTimesAVG(NumberUtil.div(summaryData.getGameTimes(), (double)dataList.size(), 2));
			
			summaryData.setDataList(dataList);
			
			resultList.add(summaryData);
		}
		
		return resultList;
	}

	@Override
	public List<SummaryData> exportAvgGameTimesList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return summaryDataMapper.exportAvgGameTimesList(searchData);
	}

	
}
