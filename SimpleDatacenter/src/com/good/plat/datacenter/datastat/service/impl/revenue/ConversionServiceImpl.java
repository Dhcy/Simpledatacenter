package com.good.plat.datacenter.datastat.service.impl.revenue;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.revenue.Conversion;
import com.good.plat.datacenter.datastat.mapper.revenue.RevenueConversionMapper;
import com.good.plat.datacenter.datastat.service.revenue.ConversionService;

/**
 * @ClassName: ConversionMapper
 * @Description: 付费渗透
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Service(value="revenueConversionService")
public class ConversionServiceImpl implements ConversionService{

	@Autowired
	private RevenueConversionMapper conversionMapper;
	@Override
	public List<Conversion> selectDayPayRateList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectDayPayRateList(searchData);
	}

	@Override
	public List<Conversion> exportDayPayRateList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportDayPayRateList(searchData);
	}

	@Override
	public List<Conversion> selectWeekPayRateList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectWeekPayRateList(searchData);
	}

	@Override
	public List<Conversion> exportWeekPayRateList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportWeekPayRateList(searchData);
	}

	@Override
	public List<Conversion> selectMonthPayRateList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectMonthPayRateList(searchData);
	}

	@Override
	public List<Conversion> exportMonthPayRateList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportMonthPayRateList(searchData);
	}
	

	@Override
	public List<Conversion> selectActorLevelPaymentList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectActorLevelPaymentList(searchData);
	}

	@Override
	public List<Conversion> exportActorLevelPaymentList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportActorLevelPaymentList(searchData);
	}

	@Override
	public List<Conversion> selectActorLevelRechargeTimeList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectActorLevelRechargeTimeList(searchData);
	}

	@Override
	public List<Conversion> exportActorLevelRechargeTimeList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportActorLevelRechargeTimeList(searchData);
	}
	
	@Override
	public List<Conversion> selectChannelIncomeList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectChannelIncomeList(searchData);
	}
	@Override
	public List<Conversion> exportChannelIncomeList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportChannelIncomeList(searchData);
	}
	
	@Override
	public List<Conversion> selectCityIncomeList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectCityIncomeList(searchData);
	}
	@Override
	public List<Conversion> selectCityAccountsList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectCityAccountsList(searchData);
	}
	
	@Override
	public List<Conversion> exportCityAccountsList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportCityAccountsList(searchData);
	}
	@Override
	public List<Conversion> exportCityIncomeList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportCityIncomeList(searchData);
	}


	@Override
	public List<Conversion> selectDayARPUList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectDayARPUList(searchData);
	}

	@Override
	public List<Conversion> exportDayARPUList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportDayARPUList(searchData);
	}

	@Override
	public List<Conversion> selectMonthARPUList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectMonthARPUList(searchData);
	}

	@Override
	public List<Conversion> exportMonthARPUList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportMonthARPUList(searchData);
	}

	@Override
	public List<Conversion> selectDayARPPUList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectDayARPPUList(searchData);
	}

	@Override
	public List<Conversion> exportDayARPPUList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportDayARPPUList(searchData);
	}

	@Override
	public List<Conversion> selectMonthARPPUList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectMonthARPPUList(searchData);
	}

	@Override
	public List<Conversion> exportMonthARPPUList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportMonthARPPUList(searchData);
	}

	@Override
	public List<Conversion> selectLocationOfDailyRateOfPay(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectLocationOfDailyRateOfPay(searchData);
	}

	@Override
	public List<Conversion> exportLocationOfDailyRateOfPay(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportLocationOfDailyRateOfPay(searchData);
	}

	@Override
	public List<Conversion> selectLocationOfDailyARPU(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectLocationOfDailyARPU(searchData);
	}

	@Override
	public List<Conversion> exportLocationOfDailyARPU(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportLocationOfDailyARPU(searchData);
	}

	@Override
	public List<Conversion> selectLocationOfDailyARPPU(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectLocationOfDailyARPPU(searchData);
	}

	@Override
	public List<Conversion> exportLocationOfDailyARPPU(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportLocationOfDailyARPPU(searchData);
	}

	@Override
	public List<Conversion> selectChannelOfDailyRateOfPay(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectChannelOfDailyRateOfPay(searchData);
	}

	@Override
	public List<Conversion> exportChannelOfDailyRateOfPay(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportChannelOfDailyRateOfPay(searchData);
	}

	@Override
	public List<Conversion> selectChannelOfDailyARPU(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectChannelOfDailyARPU(searchData);
	}

	@Override
	public List<Conversion> exportChannelOfDailyARPU(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportChannelOfDailyARPU(searchData);
	}

	@Override
	public List<Conversion> selectChannelOfDailyARPPU(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectChannelOfDailyARPPU(searchData);
	}

	@Override
	public List<Conversion> exportChannelOfDailyARPPU(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportChannelOfDailyARPPU(searchData);
	}

	@Override
	public List<Conversion> selectDayPayRateOfAccoutList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData = searchData.searchDataFilter(searchData);
		List<Conversion> list=conversionMapper.selectDayPayRateOfAccoutList(searchData);
		return list;
	}

	@Override
	public List<Conversion> selectWeekPayRateOfAccoutList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData = searchData.searchDataFilter(searchData);
		List<Conversion> list=conversionMapper.selectWeekPayRateOfAccoutList(searchData);
		return list;
	}

	@Override
	public List<Conversion> selectMonthPayRateOfAccoutList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData = searchData.searchDataFilter(searchData);
		List<Conversion> list=conversionMapper.selectMonthPayRateOfAccoutList(searchData);
		return list;
	}


}
