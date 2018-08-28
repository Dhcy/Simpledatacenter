package com.good.plat.datacenter.datastat.mapper.players;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.players.GameFrequency;

public interface GameFrequencyMapper {
	List<GameFrequency> selectDailyGameCounts(BaseSearchData searchData);
	List<GameFrequency> selectWeeklyGameCounts(BaseSearchData searchData);
	
	List<GameFrequency> selectMonthlyGameDays(BaseSearchData searchData);
	List<GameFrequency> selectWeeklyGameDays(BaseSearchData searchData);
	/**
	 * 
	 * @Title: selectPlayerHoursList
	 * @Description: 玩家游戏时段
	 * @param searchData
	 * @return
	 * List<GameFrequency>
	 * @author dmw
	 * @date 2016年4月29日 下午5:07:43
	 */
	List<GameFrequency> selectPlayerHoursList(BaseSearchData searchData);
}
