package com.good.plat.datacenter.datastat.mapper.players;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.players.Behavior;

/**
 * @ClassName: BehaviorMapper
 * @Description: 游戏习惯
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface BehaviorMapper {
	/**
	 * 新增玩家(checktype=1)\活跃玩家(checktype=2) 游戏时间与游戏次数
	 * @Title: selectDailyGameHourAndTimes
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<Behavior>
	 * @author moxw
	 * @date 2016年8月29日 下午3:07:56
	 */
	List<Behavior> selectDailyGameHourAndTimes(BaseSearchData searchData);
	/**
	 * logplat_gamect_daily.wau 为 logplat_gamect_daily.statdate = 周日的日期
	 * @Title: selectWeeklyGameHourAndTimes
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<Behavior>
	 * @author moxw
	 * @date 2016年8月29日 下午3:53:10
	 */
	List<Behavior> selectWeeklyGameHourAndTimes(BaseSearchData searchData);
	/**
	 * mau 为 logplat_gamect_daily.statdate = 月末的日期
	 * @Title: selectMonthlyGameHourAndTimes
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<Behavior>
	 * @author moxw
	 * @date 2016年8月29日 下午3:53:25
	 */
	List<Behavior> selectMonthlyGameHourAndTimes(BaseSearchData searchData);
	
	/**
	 * 付费玩家 日游戏时间与游戏次数
	 * @Title: selectPayPlayerDailyGameHourAndGamePlayTimes
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<Behavior>
	 * @author moxw
	 * @date 2016年8月29日 下午3:07:29
	 */
	List<Behavior> selectPayPlayerDailyGameHourAndGamePlayTimes(BaseSearchData searchData);
	/**
	 * 付费玩家月游戏时间与次数
	 * @Title: selectPayPlayerMonthlyGameHourAndTimes
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<Behavior>
	 * @author moxw
	 * @date 2016年8月29日 下午4:46:12
	 */
	List<Behavior> selectPayPlayerMonthlyGameHourAndTimes(BaseSearchData searchData);
	/**
	 * 付费玩家周游戏时间与次数
	 * @Title: selectPayPlayerWeeklyGameHourAndTimes
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<Behavior>
	 * @author moxw
	 * @date 2016年8月29日 下午4:46:16
	 */
	List<Behavior> selectPayPlayerWeeklyGameHourAndTimes(BaseSearchData searchData);
}
