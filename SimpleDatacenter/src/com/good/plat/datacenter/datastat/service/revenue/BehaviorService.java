package com.good.plat.datacenter.datastat.service.revenue;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.revenue.Behavior;

/**
 * @ClassName: BehaviorMapper
 * @Description: 渠道数据
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface BehaviorService {
	/**
	 * @Title: selectChargeWayDistribution
	 * @Description: 充值方式收入金额
	 * @param searchData
	 * @return List<Behavior>
	 * @author dmw
	 * @date 2016年3月16日 下午4:04:58
	 */
	List<Behavior> selectChargeWayOfAmount(BaseSearchData searchData,
			HttpSession session) throws Exception;

	// 导出
	List<Behavior> exportChargeWayOfAmount(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectChargeWayOfNum
	 * @Description: 充值方式-充值人次
	 * @param searchData
	 * @return List<Behavior>
	 * @author dmw
	 * @date 2016年3月16日 下午4:07:37
	 */
	List<Behavior> selectChargeWayOfNum(BaseSearchData searchData,
			HttpSession session) throws Exception;

	// 导出
	List<Behavior> exportChargeWayOfNum(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectConsumePackgeAmount
	 * @Description: 消费包类型-收入金额
	 * @param searchData
	 * @return List<Behavior>
	 * @author dmw
	 * @date 2016年3月16日 下午4:19:53
	 */
	List<Behavior> selectConsumePackgeAmount(BaseSearchData searchData,
			HttpSession session) throws Exception;

	// 导出
	List<Behavior> exportConsumePackgeAmount(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectConsumePackgeNum
	 * @Description: 消费包类型-充值人次
	 * @param searchData
	 * @return List<Behavior>
	 * @author dmw
	 * @date 2016年3月16日 下午4:21:10
	 */
	List<Behavior> selectConsumePackgeNum(BaseSearchData searchData,
			HttpSession session) throws Exception;

	// 导出
	List<Behavior> exportConsumePackgeNum(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectChargeNumDis
	 * @Description: 充值次数分布
	 * @param searchData
	 * @return List<Behavior>
	 * @author dmw
	 * @date 2016年3月16日 下午4:23:32
	 */
	List<Behavior> selectChargeNumDis(BaseSearchData searchData,
			HttpSession session) throws Exception;

	// 导出
	List<Behavior> exportChargeNumDis(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectChargeAmountDis
	 * @Description: 充值额度分布
	 * @param searchData
	 * @return List<Behavior>
	 * @author dmw
	 * @date 2016年3月16日 下午4:24:55
	 */
	List<Behavior> selectChargeAmountDis(BaseSearchData searchData,
			HttpSession session) throws Exception;

	// 导出
	List<Behavior> exportChargeAmountDis(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectChargeInterval
	 * @Description: 充值间隔
	 * @param searchData
	 * @return List<Behavior>
	 * @author dmw
	 * @date 2016年3月16日 下午4:25:58
	 */
	List<Behavior> selectChargeInterval(BaseSearchData searchData,
			HttpSession session) throws Exception;

	// 导出
	List<Behavior> exportChargeInterval(BaseSearchData searchData,
			HttpSession session) throws Exception;

}
