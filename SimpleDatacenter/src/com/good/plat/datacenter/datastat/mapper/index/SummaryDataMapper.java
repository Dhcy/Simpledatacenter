package com.good.plat.datacenter.datastat.mapper.index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.index.SummaryData;

public interface SummaryDataMapper {
	
	/**
	 * @Title: selectSummaryData
	 * @Description: 概览
	 * @param searchData
	 * @return
	 * SummaryData
	 * @author dmw
	 * @date 2016年3月14日 下午11:25:11
	 */
	SummaryData selectSummaryData (BaseSearchData searchData);
	
	/**
	 * @Title: selectNewPlayerList
	 * @Description: 新增激活和账户
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:25:30
	 */
	List<SummaryData> selectNewPlayerList (BaseSearchData searchData);
	
	/**
	 * @Title: exportNewPlayerList
	 * @Description: 导出新增激活和账户
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:33:00
	 */
	List<SummaryData> exportNewPlayerList (BaseSearchData searchData);
	
	/**
	 * @Title: selectActivePlayerList
	 * @Description: 活跃玩家
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:25:54
	 */
	List<SummaryData> selectActivePlayerList (BaseSearchData searchData);
	
	/**
	 * @Title: exportActivePlayerList
	 * @Description: 导出活跃玩家
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:45:35
	 */
	List<SummaryData> exportActivePlayerList (BaseSearchData searchData);
	
	/**
	 * @Title: selectPaidPlayerList
	 * @Description: 付费玩家
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:26:13
	 */
	List<SummaryData> selectPaidPlayerList (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportPaidPlayerList
	 * @Description: 导出付费玩家
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:37:42
	 */
	List<SummaryData> exportPaidPlayerList (BaseSearchData searchData);
	
	/**
	 * @Title: selectIncomeNumList
	 * @Description: 收入
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:26:26
	 */
	List<SummaryData> selectIncomeNumList (BaseSearchData searchData);
	
	/**
	 * @Title: exportIncomeNumList
	 * @Description: 导出收入
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:37:57
	 */
	List<SummaryData> exportIncomeNumList (BaseSearchData searchData);
	
	/**
	 * @Title: selectDailyRateOfPay
	 * @Description: 日付费率
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:27:25
	 */
	List<SummaryData> selectDailyRateOfPay (BaseSearchData searchData);
	
	/**
	 * @Title: exportDailyRateOfPay
	 * @Description: 导出日付费率
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:37:07
	 */
	List<SummaryData> exportDailyRateOfPay (BaseSearchData searchData);
	
	/**
	 * @Title: selectDailyARPU
	 * @Description: 日ARPU
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:27:59
	 */
	List<SummaryData> selectDailyARPU (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportDailyARPU
	 * @Description: 导出日ARPU
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:36:40
	 */
	List<SummaryData> exportDailyARPU (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectDailyARPPU
	 * @Description: 日ARPPU
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:28:53
	 */
	List<SummaryData> selectDailyARPPU (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportDailyARPPU
	 * @Description: 导出日ARPPU
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:35:34
	 */
	List<SummaryData> exportDailyARPPU (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectNewUserRetention
	 * @Description: 新增账户留存
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:38:23
	 */
	List<SummaryData> selectNewUserRetention (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportNewUserRetention
	 * @Description: 导出新增账户留存
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:35:14
	 */
	List<SummaryData> exportNewUserRetention (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectDeviceRetention
	 * @Description: 设备留存
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:38:23
	 */
	List<SummaryData> selectDeviceRetention (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportDeviceRetention
	 * @Description: 导出设备留存
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:35:14
	 */
	List<SummaryData> exportDeviceRetention (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectAvgGameTimesList
	 * @Description: 平均游戏时长与次数
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:38:35
	 */
	List<SummaryData> selectAvgGameTimesList (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportAvgGameTimesList
	 * @Description: 导出平均游戏时长与次数
	 * @param searchData
	 * @return
	 * List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:34:46
	 */
	List<SummaryData> exportAvgGameTimesList (BaseSearchData searchData);
	
	
}
