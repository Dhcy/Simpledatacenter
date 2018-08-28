package com.good.plat.datacenter.datastat.service.levels;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.levels.LevelDetail;
import com.good.plat.datacenter.datastat.entity.levels.LevelDistribute;
import com.good.plat.datacenter.datastat.entity.levels.NewPlayerProgress;

/**
 * @ClassName: LevelAnalyticsMapper
 * @Description: 等级分析
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface LevelAnalyticsService {

	/**
	 * @Title: selectLevelStageData
	 * @Description: 等级详情
	 * @param searchData
	 * @return List<LevelDetail>
	 * @author dmw
	 * @date 2016年3月15日 上午11:50:28
	 */
	List<LevelDetail> selectLevelStageData(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportLevelStageData
	 * @Description: 导出等级详情
	 * @param searchData
	 * @return List<LevelDetail>
	 * @author dmw
	 * @date 2016年3月15日 上午11:50:57
	 */
	List<LevelDetail> exportLevelStageData(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectLevelUpTimeDistribution
	 * @Description: 升级时长
	 * @param searchData
	 * @return List<LevelDetail>
	 * @author dmw
	 * @date 2016年3月15日 上午11:52:20
	 */
	List<LevelDetail> selectLevelUpTimeDistribution(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectRemarkList
	 * @Description: 按日趋势
	 * @param searchData
	 * @return List<LevelDetail>
	 * @author dmw
	 * @date 2016年3月15日 上午11:53:08
	 */
	List<LevelDetail> selectRemarkList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * 
	 * @Title: selectDailyUpgradeList
	 * @Description: 每日升级时长
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<LevelDetail>
	 * @author moxw
	 * @date 2016年4月28日 下午7:12:03
	 */
	List<LevelDetail> selectDailyUpgradeList(BaseSearchData searchData,
			HttpSession session) throws Exception;
	
	/**
	 * 
	 * @Title: selectLevelPlateauList
	 * @Description: 等级停滞
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<LevelDetail>
	 * @author moxw
	 * @date 2016年4月28日 下午7:12:03
	 */
	List<LevelDetail> selectLevelPlateauList(BaseSearchData searchData,
			HttpSession session) throws Exception;
	
	/**
	 * 
	 * @Title: selectPayTimeAndMoneyList
	 * @Description: 等级付费次数与金额
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<LevelDetail>
	 * @author moxw
	 * @date 2016年4月28日 下午7:12:03
	 */
	List<LevelDetail> selectPayTimeAndMoneyList(BaseSearchData searchData,
			HttpSession session) throws Exception;
	/**
	 * @Title: selectLevelDistributionOfActivePlayer
	 * @Description: 玩家等级分布
	 * @param searchData
	 * @return List<LevelDistribute>
	 * @author dmw
	 * @date 2016年3月15日 下午1:56:16
	 */
	List<LevelDistribute> selectLevelDistributionOfActivePlayer(
			BaseSearchData searchData, HttpSession session) throws Exception;

	/**
	 * @Title: exportLevelDistributionOfActivePlayer
	 * @Description: 玩家等级分布导出
	 * @param searchData
	 * @return List<LevelDistribute>
	 * @author dmw
	 * @date 2016年3月15日 下午1:56:56
	 */
	List<LevelDistribute> exportLevelDistributionOfActivePlayer(
			BaseSearchData searchData, HttpSession session) throws Exception;

	/**
	 * @Title: selectLevelDistributionOfPlayTimes
	 * @Description: 游戏次数等级分布
	 * @param searchData
	 * @return List<LevelDistribute>
	 * @author dmw
	 * @date 2016年3月15日 下午2:00:40
	 */
	List<LevelDistribute> selectLevelDistributionOfPlayTimes(
			BaseSearchData searchData, HttpSession session) throws Exception;

	/**
	 * @Title: exportLevelDistributionOfPlayTimes
	 * @Description: 游戏次数等级分布导出
	 * @param searchData
	 * @return List<LevelDistribute>
	 * @author dmw
	 * @date 2016年3月15日 下午2:02:21
	 */
	List<LevelDistribute> exportLevelDistributionOfPlayTimes(
			BaseSearchData searchData, HttpSession session) throws Exception;

	/**
	 * @Title: selectLevelOfLossPlayer
	 * @Description: 流失玩家停留等级
	 * @param searchData
	 * @return List<LevelDistribute>
	 * @author dmw
	 * @date 2016年3月15日 下午2:04:07
	 */
	List<LevelDistribute> selectLevelOfLossPlayer(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportLevelOfLossPlayer
	 * @Description: 流失玩家停留等级导出
	 * @param searchData
	 * @return List<LevelDistribute>
	 * @author dmw
	 * @date 2016年3月15日 下午2:04:07
	 */
	List<LevelDistribute> exportLevelOfLossPlayer(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectFirstNDayLevelPlayer
	 * @Description: 首个n日等级玩家
	 * @param searchData
	 * @return List<LevelDistribute>
	 * @author moxw
	 * @date 2016年3月15日 下午2:04:07
	 */
	List<NewPlayerProgress> selectFirstNDayLevelPlayer(BaseSearchData searchData, HttpSession session) throws Exception;
	
	/**
	 * 
	 * @Title: selectNDayPlayerLevelChange
	 * @Description: n日玩家等级变迁
	 * @param searchData
	 * @param session
	 * @return
	 * List<NewPlayerProgress>
	 * @author moxw
	 * @date 2016年4月29日 下午2:03:48
	 */
	List<NewPlayerProgress> selectNDayPlayerLevelChange(BaseSearchData searchData, HttpSession session);
	
	/**
	 * 
	 * @Title: selectPlayerCntOfLevel
	 * @Description: 等级玩家数
	 * @param searchData
	 * @return
	 * List<LevelDistribute>
	 * @author hwj
	 * @date 2017-1-17 上午09:28:33
	 */
	List<LevelDistribute> selectPlayerCntOfLevel(BaseSearchData searchData)throws Exception;;

}
