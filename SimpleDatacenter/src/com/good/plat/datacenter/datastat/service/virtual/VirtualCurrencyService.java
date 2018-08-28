package com.good.plat.datacenter.datastat.service.virtual;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.virtual.VirtualCurrency;

/**
 * @ClassName: VirtualCurrencyMapper
 * @Description: 虚拟币
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface VirtualCurrencyService {

	/**
	 * @Title: selectVcPurchase
	 * @Description: 虚拟币产出和消耗
	 * @param searchData
	 * @return List<VirtualCurrency>
	 * @author dmw
	 * @date 2016年3月16日 下午4:42:09
	 */
	List<VirtualCurrency> selectVcPurchase(BaseSearchData searchData,
			HttpSession session) throws Exception;

	List<VirtualCurrency> exportVcPurchase(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectVcConsumeTimes
	 * @Description: 消耗次数
	 * @param searchData
	 * @return List<VirtualCurrency>
	 * @author dmw
	 * @date 2016年3月16日 下午4:42:17
	 */
	List<VirtualCurrency> selectVcConsumeTimes(BaseSearchData searchData,
			HttpSession session) throws Exception;

	// 导出
	List<VirtualCurrency> exportVcConsumeTimes(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectVcConsumeNumOfPeople
	 * @Description: 消耗人数
	 * @param searchData
	 * @return List<VirtualCurrency>
	 * @author dmw
	 * @date 2016年3月16日 下午4:42:22
	 */
	List<VirtualCurrency> selectVcConsumeNumOfPeople(BaseSearchData searchData,
			HttpSession session) throws Exception;

	// 导出
	List<VirtualCurrency> exportVcConsumeNumOfPeople(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectTotalRetentionVc
	 * @Description: 累计留存虚拟币
	 * @param searchData
	 * @return List<VirtualCurrency>
	 * @author dmw
	 * @date 2016年3月16日 下午4:42:26
	 */
	List<VirtualCurrency> selectTotalRetentionVc(BaseSearchData searchData,
			HttpSession session) throws Exception;

	// 导出
	List<VirtualCurrency> exportTotalRetentionVc(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectVcLevelConsume
	 * @Description: 虚拟币消耗-等级
	 * @param searchData
	 * @return List<VirtualCurrency>
	 * @author dmw
	 * @date 2016年3月16日 下午4:42:30
	 */
	List<VirtualCurrency> selectVcLevelConsume(BaseSearchData searchData,
			HttpSession session) throws Exception;

	// 导出
	List<VirtualCurrency> exportVcLevelConsume(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectVcLevelConsumeTimes
	 * @Description: 消耗次数-等级
	 * @param searchData
	 * @return List<VirtualCurrency>
	 * @author dmw
	 * @date 2016年3月16日 下午4:42:34
	 */
	List<VirtualCurrency> selectVcLevelConsumeTimes(BaseSearchData searchData,
			HttpSession session) throws Exception;

	// 导出
	List<VirtualCurrency> exportVcLevelConsumeTimes(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectVcLevelConsumeNumOfPeople
	 * @Description: 消耗人数-等级
	 * @param searchData
	 * @return List<VirtualCurrency>
	 * @author dmw
	 * @date 2016年3月16日 下午4:42:38
	 */
	List<VirtualCurrency> selectVcLevelConsumeNumOfPeople(
			BaseSearchData searchData, HttpSession session) throws Exception;

	// 导出
	List<VirtualCurrency> exportVcLevelConsumeNumOfPeople(
			BaseSearchData searchData, HttpSession session) throws Exception;

}
