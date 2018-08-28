package com.good.plat.datacenter.datastat.service.impl.revenue;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.revenue.IncomeData;
import com.good.plat.datacenter.datastat.mapper.revenue.IncomeDataMapper;
import com.good.plat.datacenter.datastat.service.revenue.IncomeDataService;

/**
 * @ClassName: IncomeDataMapper
 * @Description: 收入数据
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Service(value="incomeDataService")
public class IncomeDataServiceImpl implements IncomeDataService {

	@Autowired
	private IncomeDataMapper incomeDataMapper;
	
	@Override
	public List<IncomeData> selectIncomeNumList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return incomeDataMapper.selectIncomeNumList(searchData);
	}

	@Override
	public List<IncomeData> exportIncomeNumList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return incomeDataMapper.exportIncomeNumList(searchData);
	}

	@Override
	public List<IncomeData> selectChargeTimesList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return incomeDataMapper.selectChargeTimesList(searchData);
	}

	@Override
	public List<IncomeData> exportChargeTimesList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return incomeDataMapper.exportChargeTimesList(searchData);
	}

	@Override
	public List<IncomeData> selectChargePlayerNumList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return incomeDataMapper.selectChargePlayerNumList(searchData);
	}

	@Override
	public List<IncomeData> exportChargePlayerNumList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return incomeDataMapper.exportChargePlayerNumList(searchData);
	}

	@Override
	public List<IncomeData> selectLevelIncomeNumDistribution(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return incomeDataMapper.selectLevelIncomeNumDistribution(searchData);
	}

	@Override
	public List<IncomeData> exportLevelIncomeNumDistribution(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return incomeDataMapper.exportLevelIncomeNumDistribution(searchData);
	}

	@Override
	public List<IncomeData> selectLevelChargePlayerNumDistribution(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return incomeDataMapper.selectLevelChargePlayerNumDistribution(searchData);
	}

	@Override
	public List<IncomeData> exportLevelChargePlayerNumDistribution(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return incomeDataMapper.exportLevelChargePlayerNumDistribution(searchData);
	}

	@Override
	public List<IncomeData> selectLocationIncomeDistribution(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return incomeDataMapper.selectLocationIncomeDistribution(searchData);
	}

	@Override
	public List<IncomeData> exportLocationIncomeDistribution(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return incomeDataMapper.exportLocationIncomeDistribution(searchData);
	}

	@Override
	public List<IncomeData> selectChannelIncomeDistribution(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return incomeDataMapper.selectChannelIncomeDistribution(searchData);
	}

	@Override
	public List<IncomeData> exportChannelIncomeDistribution(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return incomeDataMapper.exportChannelIncomeDistribution(searchData);
	}

	@Override
	public List<IncomeData> selectFirstRechargeActor(BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return incomeDataMapper.selectFirstRechargeActor(searchData);
	}
}
