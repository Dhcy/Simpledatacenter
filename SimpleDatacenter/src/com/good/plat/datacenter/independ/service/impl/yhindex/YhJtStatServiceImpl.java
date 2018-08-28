package com.good.plat.datacenter.independ.service.impl.yhindex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhJtStat;
import com.good.plat.datacenter.independ.mapper.yhindex.YhJtStatMapper;
import com.good.plat.datacenter.independ.service.yhindex.YhJtStatService;
@Service(value="yhJtStatService")
public class YhJtStatServiceImpl implements YhJtStatService {
	@Autowired
	private YhJtStatMapper yhJtStatMapper;
	@Override
	public List<YhJtStat> selectJtDonateStatList(IndependBaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhJtStat> list=yhJtStatMapper.selectJtDonateStatList(searchData);
		return list;
	}

	@Override
	public List<YhJtStat> selectJtLvlDistributeList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhJtStat> list=yhJtStatMapper.selectJtLvlDistributeList(searchData);
		return list;
	}

	@Override
	public List<YhJtStat> selectJtPurchaseGoodList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhJtStat> list=yhJtStatMapper.selectJtPurchaseGoodList(searchData);
		return list;
	}

	@Override
	public List<YhJtStat> selectJtPurchaseList(IndependBaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhJtStat> list=yhJtStatMapper.selectJtPurchaseList(searchData);
		return list;
	}

	@Override
	public List<YhJtStat> selectJtQdrqStageStatList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhJtStat> list=yhJtStatMapper.selectJtQdrqStageStatList(searchData);
		return list;
	}

	@Override
	public List<YhJtStat> selectJtStatList(IndependBaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhJtStat> list=yhJtStatMapper.selectJtStatList(searchData);
		return list;
	}

	@Override
	public List<YhJtStat> selectJtTotalDataList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhJtStat> list=yhJtStatMapper.selectJtTotalDataList(searchData);
		return list;
	}

	@Override
	public List<YhJtStat> selectJtfbqqDataList(IndependBaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhJtStat> list=yhJtStatMapper.selectJtfbqqDataList(searchData);
		return list;
	}

	@Override
	public List<YhJtStat> selectJtzsDataList(IndependBaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhJtStat> list=yhJtStatMapper.selectJtzsDataList(searchData);
		return list;
	}

}
