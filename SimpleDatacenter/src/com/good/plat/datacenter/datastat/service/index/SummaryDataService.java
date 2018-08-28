package com.good.plat.datacenter.datastat.service.index;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.index.SummaryData;

public interface SummaryDataService {
	/**
	 * @Title: selectSummaryData
	 * @Description: 概览
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 *             SummaryData
	 * @author dmw
	 * @date 2016年3月14日 下午11:25:11
	 */
	SummaryData selectSummaryData(BaseSearchData searchData, HttpSession session)
			throws Exception;

	/**
	 * @Title: selectNewPlayerList
	 * @Description: 新增激活和账户
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:25:30
	 */
	List<SummaryData> selectNewPlayerList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportNewPlayerList
	 * @Description: 导出新增激活和账户
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:33:00
	 */
	List<SummaryData> exportNewPlayerList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectActivePlayerList
	 * @Description: 活跃玩家
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:25:54
	 */
	List<SummaryData> selectActivePlayerList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportActivePlayerList
	 * @Description: 导出活跃玩家
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:45:35
	 */
	List<SummaryData> exportActivePlayerList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectPaidPlayerList
	 * @Description: 付费玩家
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:26:13
	 */
	List<SummaryData> selectPaidPlayerList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportPaidPlayerList
	 * @Description: 导出付费玩家
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:37:42
	 */
	List<SummaryData> exportPaidPlayerList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectIncomeNumList
	 * @Description: 收入
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:26:26
	 */
	List<SummaryData> selectIncomeNumList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportIncomeNumList
	 * @Description: 导出收入
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:37:57
	 */
	List<SummaryData> exportIncomeNumList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectDailyRateOfPay
	 * @Description: 日付费率
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:27:25
	 */
	List<SummaryData> selectDailyRateOfPay(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportDailyRateOfPay
	 * @Description: 导出日付费率
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:37:07
	 */
	List<SummaryData> exportDailyRateOfPay(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectDailyARPU
	 * @Description: 日ARPU
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:27:59
	 */
	List<SummaryData> selectDailyARPU(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportDailyARPU
	 * @Description: 导出日ARPU
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:36:40
	 */
	List<SummaryData> exportDailyARPU(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectDailyARPPU
	 * @Description: 日ARPPU
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:28:53
	 */
	List<SummaryData> selectDailyARPPU(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportDailyARPPU
	 * @Description: 导出日ARPPU
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:35:34
	 */
	List<SummaryData> exportDailyARPPU(BaseSearchData searchData,
			HttpSession session) throws Exception;

	
	/**
	 * @Title: selectNewUserRetention
	 * @Description: 新增账户留存
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:38:23
	 */
	List<SummaryData> selectNewUserRetention(BaseSearchData searchData,
			HttpSession session) throws Exception;

	
	/**
	 * @Title: exportNewUserRetention
	 * @Description: 导出新增账户留存
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:35:14
	 */
	List<SummaryData> exportNewUserRetention(BaseSearchData searchData,
			HttpSession session) throws Exception;
	
	
	/**
	 * @Title: selectDeviceRetention
	 * @Description: 设备留存
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:38:23
	 */
	List<SummaryData> selectDeviceRetention(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportDeviceRetention
	 * @Description: 导出设备留存
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:35:14
	 */
	List<SummaryData> exportDeviceRetention(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectAvgGameTimesList
	 * @Description: 平均游戏时长与次数
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月14日 下午11:38:35
	 */
	List<SummaryData> selectAvgGameTimesList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportAvgGameTimesList
	 * @Description: 导出平均游戏时长与次数
	 * @param searchData
	 * @return List<SummaryData>
	 * @author dmw
	 * @date 2016年3月15日 上午9:34:46
	 */
	List<SummaryData> exportAvgGameTimesList(BaseSearchData searchData,
			HttpSession session) throws Exception;

}
