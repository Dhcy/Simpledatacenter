package com.good.plat.datacenter.datastat.service.impl.virtual;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.virtual.VirtualCurrency;
import com.good.plat.datacenter.datastat.mapper.virtual.VirtualCurrencyMapper;
import com.good.plat.datacenter.datastat.service.virtual.VirtualCurrencyService;

/**
 * @ClassName: VirtualCurrencyMapper
 * @Description: 虚拟币
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Service(value="virtualCurrencyService")
public class VirtualCurrencyServiceImpl implements VirtualCurrencyService{

	@Autowired
	private VirtualCurrencyMapper virtualCurrencyMapper;
	
	
	@Override
	public List<VirtualCurrency> selectVcPurchase(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualCurrencyMapper.selectVcPurchase(searchData);
	}

	@Override
	public List<VirtualCurrency> exportVcPurchase(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualCurrencyMapper.exportVcPurchase(searchData);
	}

	@Override
	public List<VirtualCurrency> selectVcConsumeTimes(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualCurrencyMapper.selectVcConsumeTimes(searchData);
	}

	@Override
	public List<VirtualCurrency> exportVcConsumeTimes(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualCurrencyMapper.exportVcConsumeTimes(searchData);
	}

	@Override
	public List<VirtualCurrency> selectVcConsumeNumOfPeople(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualCurrencyMapper.selectVcConsumeNumOfPeople(searchData);
	}

	@Override
	public List<VirtualCurrency> exportVcConsumeNumOfPeople(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualCurrencyMapper.exportVcConsumeNumOfPeople(searchData);
	}

	@Override
	public List<VirtualCurrency> selectTotalRetentionVc(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualCurrencyMapper.selectTotalRetentionVc(searchData);
	}

	@Override
	public List<VirtualCurrency> exportTotalRetentionVc(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualCurrencyMapper.exportTotalRetentionVc(searchData);
	}

	@Override
	public List<VirtualCurrency> selectVcLevelConsume(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualCurrencyMapper.selectVcLevelConsume(searchData);
	}

	@Override
	public List<VirtualCurrency> exportVcLevelConsume(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualCurrencyMapper.exportVcLevelConsume(searchData);
	}

	@Override
	public List<VirtualCurrency> selectVcLevelConsumeTimes(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualCurrencyMapper.selectVcLevelConsumeTimes(searchData);
	}

	@Override
	public List<VirtualCurrency> exportVcLevelConsumeTimes(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualCurrencyMapper.exportVcLevelConsumeTimes(searchData);
	}

	@Override
	public List<VirtualCurrency> selectVcLevelConsumeNumOfPeople(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualCurrencyMapper.selectVcLevelConsumeNumOfPeople(searchData);
	}

	@Override
	public List<VirtualCurrency> exportVcLevelConsumeNumOfPeople(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualCurrencyMapper.exportVcLevelConsumeNumOfPeople(searchData);
	}
	
	
}
