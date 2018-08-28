package com.good.plat.datacenter.datastat.service.players;

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
public interface BehaviorService {
	
	/**
	 * 
	 * @Title: selectDailyGameHourAndTimes
	 * @Description: 日玩家游戏时间与游戏次数
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Behavior>
	 * @author moxw
	 * @date 2016年4月5日 下午2:43:28
	 */
	List<Behavior> selectDailyGameHourAndTimes(BaseSearchData searchData,
			HttpSession session) throws Exception;
	
	/**
	 * 
	 * @Title: selectWeeklyGameHourAndTimes
	 * @Description: 周玩家游戏时间与游戏次数
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Behavior>
	 * @author moxw
	 * @date 2016年4月5日 下午2:43:28
	 */
	List<Behavior> selectWeeklyGameHourAndTimes(BaseSearchData searchData,
			HttpSession session) throws Exception;
	
	/**
	 * 
	 * @Title: selectMonthlyGameHourAndTimes
	 * @Description: 月玩家游戏时间与游戏次数
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Behavior>
	 * @author moxw
	 * @date 2016年4月5日 下午2:43:28
	 */
	List<Behavior> selectMonthlyGameHourAndTimes(BaseSearchData searchData,
			HttpSession session) throws Exception;
}
