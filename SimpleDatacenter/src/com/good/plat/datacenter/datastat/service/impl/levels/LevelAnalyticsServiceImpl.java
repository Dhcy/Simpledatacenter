package com.good.plat.datacenter.datastat.service.impl.levels;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.levels.LevelDetail;
import com.good.plat.datacenter.datastat.entity.levels.LevelDistribute;
import com.good.plat.datacenter.datastat.entity.levels.NewPlayerProgress;
import com.good.plat.datacenter.datastat.mapper.levels.LevelAnalyticsMapper;
import com.good.plat.datacenter.datastat.service.levels.LevelAnalyticsService;

/**
 * @ClassName: LevelAnalyticsMapper
 * @Description: 等级分析
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Service(value="levelAnalyticsService")
public class LevelAnalyticsServiceImpl implements LevelAnalyticsService{

	@Autowired
	private LevelAnalyticsMapper levelAnalyticsMapper;
	
	@Override
	public List<LevelDetail> selectLevelStageData(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return levelAnalyticsMapper.selectLevelStageData(searchData);
	}

	@Override
	public List<LevelDetail> exportLevelStageData(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return levelAnalyticsMapper.exportLevelStageData(searchData);
	}

	@Override
	public List<LevelDetail> selectLevelUpTimeDistribution(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return levelAnalyticsMapper.selectLevelUpTimeDistribution(searchData);
	}

	@Override
	public List<LevelDetail> selectRemarkList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return levelAnalyticsMapper.selectRemarkList(searchData);
	}
	
	@Override
	public List<LevelDetail> selectDailyUpgradeList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return levelAnalyticsMapper.selectDailyUpgradeList(searchData);
	}
	
	@Override
	public List<LevelDetail> selectLevelPlateauList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return levelAnalyticsMapper.selectLevelPlateauList(searchData);
	}
	
	@Override
	public List<LevelDetail> selectPayTimeAndMoneyList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return levelAnalyticsMapper.selectPayTimeAndMoneyList(searchData);
	}

	@Override
	public List<LevelDistribute> selectLevelDistributionOfActivePlayer(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return levelAnalyticsMapper.selectLevelDistributionOfActivePlayer(searchData);
	}

	@Override
	public List<LevelDistribute> exportLevelDistributionOfActivePlayer(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return levelAnalyticsMapper.exportLevelDistributionOfActivePlayer(searchData);
	}

	@Override
	public List<LevelDistribute> selectLevelDistributionOfPlayTimes(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return levelAnalyticsMapper.selectLevelDistributionOfPlayTimes(searchData);
	}

	@Override
	public List<LevelDistribute> exportLevelDistributionOfPlayTimes(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return levelAnalyticsMapper.exportLevelDistributionOfPlayTimes(searchData);
	}

	@Override
	public List<LevelDistribute> selectLevelOfLossPlayer(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return levelAnalyticsMapper.selectLevelOfLossPlayer(searchData);
	}

	@Override
	public List<LevelDistribute> exportLevelOfLossPlayer(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return levelAnalyticsMapper.exportLevelOfLossPlayer(searchData);
	}
	
	@Override
	public List<NewPlayerProgress> selectFirstNDayLevelPlayer(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return levelAnalyticsMapper.selectFirstNDayLevelPlayer(searchData);
	}
	
	@Override
	public List<NewPlayerProgress> selectNDayPlayerLevelChange(BaseSearchData searchData, HttpSession session) {
		searchData = searchData.searchDataFilter(searchData);
		return levelAnalyticsMapper.selectNDayPlayerLevelChange(searchData);
	}

	@Override
	public List<LevelDistribute> selectPlayerCntOfLevel(
			BaseSearchData searchData) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<LevelDistribute> list=levelAnalyticsMapper.selectPlayerCntOfLevel(searchData);
		return list;
	}

	public List<NewPlayerProgress> selectNPayPlayerLevelChange(BaseSearchData searchData) {
		searchData = searchData.searchDataFilter(searchData);
		List<NewPlayerProgress> list=levelAnalyticsMapper.selectNPayPlayerLevelChange(searchData);
		return list;
	}


	
}
