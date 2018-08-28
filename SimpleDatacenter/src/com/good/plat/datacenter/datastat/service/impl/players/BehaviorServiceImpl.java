package com.good.plat.datacenter.datastat.service.impl.players;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.players.Behavior;
import com.good.plat.datacenter.datastat.mapper.players.BehaviorMapper;
import com.good.plat.datacenter.datastat.service.players.BehaviorService;

/**
 * @ClassName: BehaviorMapper
 * @Description: 游戏习惯
 * @author moxw
 * @date 2016年4月5日 上午10:32:49
 */
@Service(value = "playerBehaviorService")
public class BehaviorServiceImpl implements BehaviorService {
	@Autowired
	BehaviorMapper behaviorMapper;

	@Override
	public List<Behavior> selectDailyGameHourAndTimes(BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<Behavior> list = new ArrayList();
		switch (Integer.valueOf(searchData.getChecktype1())) {
		case 1:
			// 新增玩家
			list = behaviorMapper.selectDailyGameHourAndTimes(searchData);
			break;
		case 2:
			// 活跃玩家
			list = behaviorMapper.selectDailyGameHourAndTimes(searchData);
			break;
		case 3:// 付费玩家
			list = behaviorMapper.selectPayPlayerDailyGameHourAndGamePlayTimes(searchData);
			break;
		}
		return list;
	}

	@Override
	public List<Behavior> selectWeeklyGameHourAndTimes(BaseSearchData searchData, HttpSession session)
			throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<Behavior> list = new ArrayList();
		switch (Integer.valueOf(searchData.getChecktype1())) {
		case 1:
			// 新增玩家
			list = behaviorMapper.selectWeeklyGameHourAndTimes(searchData);
			break;
		case 2:
			// 活跃玩家
			list = behaviorMapper.selectWeeklyGameHourAndTimes(searchData);
			break;
		case 3:// 付费玩家
			list = behaviorMapper.selectPayPlayerWeeklyGameHourAndTimes(searchData);
			break;
		}
		return list;
	}
	
	@Override
	public List<Behavior> selectMonthlyGameHourAndTimes(BaseSearchData searchData, HttpSession session)
			throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<Behavior> list = new ArrayList();
		switch (Integer.valueOf(searchData.getChecktype1())) {
		case 1:
			// 新增玩家
			list = behaviorMapper.selectMonthlyGameHourAndTimes(searchData);
			break;
		case 2:
			// 活跃玩家
			list = behaviorMapper.selectMonthlyGameHourAndTimes(searchData);
			break;
		case 3:// 付费玩家
			list = behaviorMapper.selectPayPlayerMonthlyGameHourAndTimes(searchData);
			break;
		}
		return list;
	}

}
