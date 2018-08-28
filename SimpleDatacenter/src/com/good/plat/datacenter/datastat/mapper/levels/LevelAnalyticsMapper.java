package com.good.plat.datacenter.datastat.mapper.levels;

import java.util.List;

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
public interface LevelAnalyticsMapper {
	
	/**
	 * @Title: selectLevelStageData
	 * @Description: 等级详情
	 * @param searchData
	 * @return
	 * List<LevelDetail>
	 * @author dmw
	 * @date 2016年3月15日 上午11:50:28
	 */
	List<LevelDetail> selectLevelStageData (BaseSearchData searchData);
	
	/**
	 * @Title: exportLevelStageData
	 * @Description: 导出等级详情
	 * @param searchData
	 * @return
	 * List<LevelDetail>
	 * @author dmw
	 * @date 2016年3月15日 上午11:50:57
	 */
	List<LevelDetail> exportLevelStageData (BaseSearchData searchData);
	
	/**
	 * @Title: selectLevelUpTimeDistribution
	 * @Description: 升级时长
	 * @param searchData
	 * @return
	 * List<LevelDetail>
	 * @author dmw
	 * @date 2016年3月15日 上午11:52:20
	 */
	List<LevelDetail> selectLevelUpTimeDistribution (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectRemarkList
	 * @Description: 按日趋势
	 * @param searchData
	 * @return
	 * List<LevelDetail>
	 * @author dmw
	 * @date 2016年3月15日 上午11:53:08
	 */
	List<LevelDetail> selectRemarkList (BaseSearchData searchData);
	
	/**
	 * 
	 * @Title: selectDailyUpgradeList
	 * @Description: 每日升级时长
	 * @param searchData
	 * @return
	 * List<LevelDetail>
	 * @author moxw
	 * @date 2016年4月28日 下午7:10:55
	 */
	List<LevelDetail> selectDailyUpgradeList (BaseSearchData searchData);
	
	/**
	 * 
	 * @Title: selectLevelPlateauList
	 * @Description: 等级停滞
	 * @param searchData
	 * @return
	 * List<LevelDetail>
	 * @author moxw
	 * @date 2016年4月28日 下午7:10:55
	 */
	List<LevelDetail> selectLevelPlateauList (BaseSearchData searchData);
	
	/**
	 * 
	 * @Title: selectPayTimeAndMoneyList
	 * @Description: 等级付费次数与付费金额
	 * @param searchData
	 * @return
	 * List<LevelDetail>
	 * @author moxw
	 * @date 2016年4月28日 下午7:10:55
	 */
	List<LevelDetail> selectPayTimeAndMoneyList (BaseSearchData searchData);
	
	
	
	/**
	 * @Title: selectLevelDistributionOfActivePlayer
	 * @Description: 玩家等级分布
	 * @param searchData
	 * @return
	 * List<LevelDistribute>
	 * @author dmw
	 * @date 2016年3月15日 下午1:56:16
	 */
	List<LevelDistribute> selectLevelDistributionOfActivePlayer (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportLevelDistributionOfActivePlayer
	 * @Description: 玩家等级分布导出
	 * @param searchData
	 * @return
	 * List<LevelDistribute>
	 * @author dmw
	 * @date 2016年3月15日 下午1:56:56
	 */
	List<LevelDistribute> exportLevelDistributionOfActivePlayer (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectLevelDistributionOfPlayTimes
	 * @Description: 游戏次数等级分布
	 * @param searchData
	 * @return
	 * List<LevelDistribute>
	 * @author dmw
	 * @date 2016年3月15日 下午2:00:40
	 */
	List<LevelDistribute> selectLevelDistributionOfPlayTimes (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportLevelDistributionOfPlayTimes
	 * @Description: 游戏次数等级分布导出
	 * @param searchData
	 * @return
	 * List<LevelDistribute>
	 * @author dmw
	 * @date 2016年3月15日 下午2:02:21
	 */
	List<LevelDistribute> exportLevelDistributionOfPlayTimes (BaseSearchData searchData);
	
	
	
	/**
	 * @Title: selectLevelOfLossPlayer
	 * @Description: 流失玩家停留等级
	 * @param searchData
	 * @return
	 * List<LevelDistribute>
	 * @author dmw
	 * @date 2016年3月15日 下午2:04:07
	 */
	List<LevelDistribute> selectLevelOfLossPlayer (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportLevelOfLossPlayer
	 * @Description: 流失玩家停留等级导出
	 * @param searchData
	 * @return
	 * List<LevelDistribute>
	 * @author dmw
	 * @date 2016年3月15日 下午2:04:07
	 */
	List<LevelDistribute> exportLevelOfLossPlayer (BaseSearchData searchData);

	/**
	 * 
	 * @Title: selectFirstNDayLevelPlayer
	 * @Description: 首个n日等级玩家
	 * @param searchData
	 * @return
	 * List<LevelDistribute>
	 * @author moxw
	 * @date 2016年4月29日 下午1:49:12
	 */
	List<NewPlayerProgress> selectFirstNDayLevelPlayer(BaseSearchData searchData);
	
	/**
	 * 
	 * @Title: selectFirstNDayLevelPlayer
	 * @Description: n日玩家等级变迁
	 * @param searchData
	 * @return
	 * List<LevelDistribute>
	 * @author moxw
	 * @date 2016年4月29日 下午1:49:12
	 */
	List<NewPlayerProgress> selectNDayPlayerLevelChange(BaseSearchData searchData);
	
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
	List<LevelDistribute> selectPlayerCntOfLevel(BaseSearchData searchData);
	/**
	 * 
	 * @Title: selectNPayPlayerLevelChange
	 * @Description: 等级玩家数
	 * @param searchData
	 * @return
	 * List<LevelDistribute>
	 * @author hcy
	 * @date 2018-8-1
	 */
	List<NewPlayerProgress> selectNPayPlayerLevelChange(BaseSearchData searchData);

}
