package com.good.plat.datacenter.datastat.service.players;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.players.GameFrequency;

public interface GameFrequencyService {
	/**
	 * 日游戏次数
	 * @Title: selectDailyGameCounts
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<GameFrequency>
	 * @author moxw
	 * @date 2016年4月7日 下午5:05:03
	 */
	List<GameFrequency> selectDailyGameCounts(BaseSearchData searchData);
	
	/**
	 * 周游戏次数
	 * @Title: selectWeeklyGameCounts
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<GameFrequency>
	 * @author moxw
	 * @date 2016年4月7日 下午5:10:33
	 */
	List<GameFrequency> selectWeeklyGameCounts(BaseSearchData searchData);
	
	/**
	 * 月游戏天数
	 * @Title: selectDailyGameDays
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<GameFrequency>
	 * @author dmw
	 * @date 2016年4月7日 下午5:10:57
	 */
	List<GameFrequency> selectMonthlyGameDays(BaseSearchData searchData);
	
	/**
	 * 周游戏天数
	 * @Title: selectWeeklyGameDays
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<GameFrequency>
	 * @author dmw
	 * @date 2016年4月7日 下午5:11:39
	 */
	List<GameFrequency> selectWeeklyGameDays(BaseSearchData searchData);

	/**
	 * 
	 * @Title: selectPlayerHoursList
	 * @Description: 玩家游戏时段
	 * @param searchData
	 * @return
	 * List<GameFrequency>
	 * @author dmw
	 * @date 2016年4月29日 下午5:07:03
	 */
	List<GameFrequency> selectPlayerHoursList(BaseSearchData searchData);
}
