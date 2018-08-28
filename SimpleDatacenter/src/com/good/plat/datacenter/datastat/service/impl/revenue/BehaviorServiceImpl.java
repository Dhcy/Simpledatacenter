package com.good.plat.datacenter.datastat.service.impl.revenue;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.revenue.Behavior;
import com.good.plat.datacenter.datastat.mapper.revenue.RevenueBehaviorMapper;
import com.good.plat.datacenter.datastat.service.revenue.BehaviorService;

/**
 * @ClassName: BehaviorMapper
 * @Description: 渠道数据
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Service(value="behaviorService")
public class BehaviorServiceImpl implements BehaviorService {

	@Autowired
	private RevenueBehaviorMapper behaviorMapper;
	
	@Override
	public List<Behavior> selectChargeWayOfAmount(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return behaviorMapper.selectChargeWayOfAmount(searchData);
	}

	@Override
	public List<Behavior> exportChargeWayOfAmount(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return behaviorMapper.exportChargeWayOfAmount(searchData);
	}

	@Override
	public List<Behavior> selectChargeWayOfNum(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return behaviorMapper.selectChargeWayOfNum(searchData);
	}

	@Override
	public List<Behavior> exportChargeWayOfNum(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return behaviorMapper.exportChargeWayOfNum(searchData);
	}

	@Override
	public List<Behavior> selectConsumePackgeAmount(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return behaviorMapper.selectConsumePackgeAmount(searchData);
	}

	@Override
	public List<Behavior> exportConsumePackgeAmount(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return behaviorMapper.exportConsumePackgeAmount(searchData);
	}

	@Override
	public List<Behavior> selectConsumePackgeNum(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return behaviorMapper.selectConsumePackgeNum(searchData);
	}

	@Override
	public List<Behavior> exportConsumePackgeNum(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return behaviorMapper.exportConsumePackgeNum(searchData);
	}

	@Override
	public List<Behavior> selectChargeNumDis(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return behaviorMapper.selectChargeNumDis(searchData);
	}

	@Override
	public List<Behavior> exportChargeNumDis(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return behaviorMapper.exportChargeNumDis(searchData);
	}

	@Override
	public List<Behavior> selectChargeAmountDis(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return behaviorMapper.selectChargeAmountDis(searchData);
	}

	@Override
	public List<Behavior> exportChargeAmountDis(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return behaviorMapper.exportChargeAmountDis(searchData);
	}

	@Override
	public List<Behavior> selectChargeInterval(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return behaviorMapper.selectChargeInterval(searchData);
	}

	@Override
	public List<Behavior> exportChargeInterval(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return behaviorMapper.exportChargeInterval(searchData);
	}
	
}
