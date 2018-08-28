package com.good.plat.datacenter.datastat.service.revenue;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.revenue.IncomeData;

/**
 * @ClassName: IncomeDataMapper
 * @Description: 收入数据
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface IncomeDataService {
	/**
	 * @Title: selectIncomeNumList
	 * @Description: 收入金额
	 * @param searchData
	 * @return List<IncomeData>
	 * @author dmw
	 * @date 2016年3月16日 下午2:04:31
	 */
	List<IncomeData> selectIncomeNumList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportIncomeNumList
	 * @Description: 收入金额导出
	 * @param searchData
	 * @return List<IncomeData>
	 * @author dmw
	 * @date 2016年3月16日 下午2:11:10
	 */
	List<IncomeData> exportIncomeNumList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectChargeTimesList
	 * @Description: 充值次数
	 * @param searchData
	 * @return List<IncomeData>
	 * @author dmw
	 * @date 2016年3月16日 下午2:11:24
	 */
	List<IncomeData> selectChargeTimesList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * 
	 * @Title: exportChargeTimesList
	 * @Description: 充值次数导出
	 * @param searchData
	 * @return List<IncomeData>
	 * @author dmw
	 * @date 2016年3月16日 下午2:11:58
	 */
	List<IncomeData> exportChargeTimesList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectChargePlayerNumList
	 * @Description: 充值人数
	 * @param searchData
	 * @return List<IncomeData>
	 * @author dmw
	 * @date 2016年3月16日 下午2:12:10
	 */
	List<IncomeData> selectChargePlayerNumList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportChargePlayerNumList
	 * @Description: 充值人数导出
	 * @param searchData
	 * @return List<IncomeData>
	 * @author dmw
	 * @date 2016年3月16日 下午2:12:25
	 */
	List<IncomeData> exportChargePlayerNumList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectLevelIncomeNumDistribution
	 * @Description: 收入金额等级分布
	 * @param searchData
	 * @return List<IncomeData>
	 * @author dmw
	 * @date 2016年3月16日 下午2:12:34
	 */
	List<IncomeData> selectLevelIncomeNumDistribution(
			BaseSearchData searchData, HttpSession session) throws Exception;

	/**
	 * @Title: exportLevelIncomeNumDistribution
	 * @Description: 收入金额等级分布导出
	 * @param searchData
	 * @return List<IncomeData>
	 * @author dmw
	 * @date 2016年3月16日 下午2:12:56
	 */
	List<IncomeData> exportLevelIncomeNumDistribution(
			BaseSearchData searchData, HttpSession session) throws Exception;

	/**
	 * @Title: selectLevelChargePlayerNumDistribution
	 * @Description: 充值人次等级分布
	 * @param searchData
	 * @return List<IncomeData>
	 * @author dmw
	 * @date 2016年3月16日 下午2:13:52
	 */
	List<IncomeData> selectLevelChargePlayerNumDistribution(
			BaseSearchData searchData, HttpSession session) throws Exception;

	/**
	 * @Title: exportLevelChargePlayerNumDistribution
	 * @Description: 充值人次等级分布导出
	 * @param searchData
	 * @return List<IncomeData>
	 * @author dmw
	 * @date 2016年3月16日 下午2:14:06
	 */
	List<IncomeData> exportLevelChargePlayerNumDistribution(
			BaseSearchData searchData, HttpSession session) throws Exception;

	/**
	 * @Title: selectLocationIncomeDistribution
	 * @Description: 各地区收入
	 * @param searchData
	 * @return List<IncomeData>
	 * @author dmw
	 * @date 2016年3月16日 下午2:14:22
	 */
	List<IncomeData> selectLocationIncomeDistribution(
			BaseSearchData searchData, HttpSession session) throws Exception;

	/**
	 * @Title: exportLocationIncomeDistribution
	 * @Description: 各地区收入导出
	 * @param searchData
	 * @return List<IncomeData>
	 * @author dmw
	 * @date 2016年3月16日 下午2:14:50
	 */
	List<IncomeData> exportLocationIncomeDistribution(
			BaseSearchData searchData, HttpSession session) throws Exception;

	/**
	 * @Title: selectChannelIncomeDistribution
	 * @Description: 各渠道收入
	 * @param searchData
	 * @return List<IncomeData>
	 * @author dmw
	 * @date 2016年3月16日 下午2:15:02
	 */
	List<IncomeData> selectChannelIncomeDistribution(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportChannelIncomeDistribution
	 * @Description: 各渠道收入导出
	 * @param searchData
	 * @return List<IncomeData>
	 * @author dmw
	 * @date 2016年3月16日 下午2:15:15
	 */
	List<IncomeData> exportChannelIncomeDistribution(BaseSearchData searchData,
			HttpSession session) throws Exception;
	
	/**
	 * 
	 * @Title: selectFirstRechargeActor
	 * @Description: 
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<IncomeData>
	 * @author dmw
	 * @date 2016年5月24日 上午9:44:57
	 */
	List<IncomeData> selectFirstRechargeActor(BaseSearchData searchData,
			HttpSession session) throws Exception;
}
