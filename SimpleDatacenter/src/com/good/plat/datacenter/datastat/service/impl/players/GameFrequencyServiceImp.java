package com.good.plat.datacenter.datastat.service.impl.players;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.players.GameFrequency;
import com.good.plat.datacenter.datastat.mapper.players.GameFrequencyMapper;
import com.good.plat.datacenter.datastat.service.players.GameFrequencyService;

@Service(value="gameFrequencyService")
public class GameFrequencyServiceImp implements GameFrequencyService {
	@Autowired
	private GameFrequencyMapper gameFrequencyMapper;
	public GameFrequencyServiceImp() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<GameFrequency> selectDailyGameCounts(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		return gameFrequencyMapper.selectDailyGameCounts(searchData);
	}

	@Override
	public List<GameFrequency> selectWeeklyGameCounts(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		return gameFrequencyMapper.selectWeeklyGameCounts(searchData);
	}

	@Override
	public List<GameFrequency> selectMonthlyGameDays(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		return gameFrequencyMapper.selectMonthlyGameDays(searchData);
	}
	
	@Override
	public List<GameFrequency> selectWeeklyGameDays(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		return gameFrequencyMapper.selectWeeklyGameDays(searchData);
	}

	@Override
	public List<GameFrequency> selectPlayerHoursList(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		return gameFrequencyMapper.selectPlayerHoursList(searchData);
	}

}
