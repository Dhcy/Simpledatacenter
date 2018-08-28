package com.good.plat.datacenter.datastat.mapper.players;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.players.ActiveByChnnel;
import com.good.plat.datacenter.datastat.entity.players.ActiveByTime;
import com.good.plat.datacenter.datastat.entity.players.ActivePlayers;

/**
 * @ClassName: ActivePlayersMapper
 * @Description: 活跃玩家
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface ActivePlayersMapper {
	/**
	 * @Title: selectDauList
	 * @Description: DAU
	 * @param searchData
	 * @return
	 * List<ActivePlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午4:16:33
	 */
	List<ActivePlayers> selectDauList (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportDauList
	 * @Description: 导出dau
	 * @param searchData
	 * @return
	 * List<ActivePlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午4:17:27
	 */
	List<ActivePlayers> exportDauList (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectWauList
	 * @Description: wau
	 * @param searchData
	 * @return
	 * List<ActivePlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午4:18:48
	 */
	List<ActivePlayers> selectWauList (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportWauList
	 * @Description: 导出wau
	 * @param searchData
	 * @return
	 * List<ActivePlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午4:19:47
	 */
	List<ActivePlayers> exportWauList (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectMauList
	 * @Description: mau
	 * @param searchData
	 * @return
	 * List<ActivePlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午4:20:14
	 */
	List<ActivePlayers> selectMauList (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportMauList
	 * @Description: 导出mau
	 * @param searchData
	 * @return
	 * List<ActivePlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午4:20:37
	 */
	List<ActivePlayers> exportMauList (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectDauDivideMauList
	 * @Description: dau/mau
	 * @param searchData
	 * @return
	 * List<ActivePlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午4:24:09
	 */
	List<ActivePlayers> selectDauDivideMauList (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportDauDivideMauList
	 * @Description: 导出dau/mau
	 * @param searchData
	 * @return
	 * List<ActivePlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午4:26:10
	 */
	List<ActivePlayers> exportDauDivideMauList (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectDaysOfActivePlayer
	 * @Description: 活跃玩家已玩天数
	 * @param searchData
	 * @return
	 * List<ActivePlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午4:28:46
	 */
	List<ActivePlayers> selectDaysOfActivePlayer (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportDaysOfActivePlayer
	 * @Description: 活跃玩家已玩天数导出
	 * @param searchData
	 * @return
	 * List<ActivePlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午4:30:09
	 */
	List<ActivePlayers> exportDaysOfActivePlayer (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectLevelOfActivePlayer
	 * @Description: 活跃玩家等级
	 * @param searchData
	 * @return
	 * List<ActivePlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午4:29:09
	 */
	List<ActivePlayers> selectLevelOfActivePlayer (BaseSearchData searchData);
	
	/**
	 * @Title: exportLevelOfActivePlayer
	 * @Description: 活跃玩家等级导出
	 * @param searchData
	 * @return
	 * List<ActivePlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午4:30:41
	 */
	List<ActivePlayers> exportLevelOfActivePlayer (BaseSearchData searchData);
	
	/**
	 * 
	 * @Title: selectCityOfActivePlayer
	 * @Description: 活跃玩家地区
	 * @param searchData
	 * @return
	 * List<ActivePlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午4:29:26
	 */
	List<ActivePlayers> selectCityOfActivePlayer (BaseSearchData searchData);
	
	
	
	/**
	 * @Title: exportCityOfActivePlayer
	 * @Description: 活跃玩家地区导出
	 * @param searchData
	 * @return
	 * List<ActivePlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午4:31:16
	 */
	List<ActivePlayers> exportCityOfActivePlayer (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectChannelOfActivePlayer
	 * @Description: 渠道活跃玩家
	 * @param searchData
	 * @return
	 * List<ActivePlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午4:29:30
	 */
	List<ActivePlayers> selectChannelOfActivePlayer (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportChannelOfActivePlayer
	 * @Description: 渠道活跃玩家导出
	 * @param searchData
	 * @return
	 * List<ActivePlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午4:31:50
	 */
	List<ActivePlayers> exportChannelOfActivePlayer (BaseSearchData searchData);
	/**
	 * 活跃玩家区域列表
	 * @Title: selectActUserCityList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<ActivePlayers>
	 * @author hwj
	 * @date 2017-11-13 下午02:07:21
	 */
	List<ActivePlayers> selectActUserCityList(BaseSearchData searchData);

	/**
	 * 活跃玩家按时登陆
	 * @Title: selectActUserByTime
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<ActiveByTime>
	 * @author hcy
	 * @date 2018-07-25
	 */
	List<ActiveByTime> selectActUserByTimeList(BaseSearchData searchData);
	/**
	 * 活跃玩家按渠道登录
	 * @Title: selectActUserByTime
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<ActiveByChinnel>
	 * @author hcy
	 * @date 2018-07-25
	 */
	List<ActiveByChnnel> selectActUserChannelList(BaseSearchData searchData);
	
}
