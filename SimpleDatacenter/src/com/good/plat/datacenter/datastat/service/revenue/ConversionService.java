package com.good.plat.datacenter.datastat.service.revenue;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.revenue.Conversion;

/**
 * @ClassName: ConversionMapper
 * @Description: 付费渗透
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface ConversionService {

	/**
	 * @Title: selectDayPayRateList
	 * @Description: 日付费率
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:33:57
	 */
	List<Conversion> selectDayPayRateList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportDayPayRateList
	 * @Description: 日付费率导出
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:34:19
	 */
	List<Conversion> exportDayPayRateList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectWeekPayRateList
	 * @Description: 周付费率
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:34:29
	 */
	List<Conversion> selectWeekPayRateList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportWeekPayRateList
	 * @Description: 周付费率导出
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:35:02
	 */
	List<Conversion> exportWeekPayRateList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectMonthPayRateList
	 * @Description: 月付费率
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:35:15
	 */
	List<Conversion> selectMonthPayRateList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportMonthPayRateList
	 * @Description: 月付费率导出
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:35:26
	 */
	List<Conversion> exportMonthPayRateList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * 各等级付费金额
	 * @Title: selectActorLevelPaymentList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年4月1日 下午7:43:43
	 */
	List<Conversion> selectActorLevelPaymentList(BaseSearchData searchData,
			HttpSession session) throws Exception;
	/**
	 * 各等级付费金额
	 * @Title: exportActorLevelPaymentList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年4月1日 下午7:43:47
	 */
	List<Conversion> exportActorLevelPaymentList(BaseSearchData searchData,
			HttpSession session) throws Exception;
	/**
	 * 各等级充值次数
	 * @Title: selectActorLevelRechargeTimeList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年4月1日 下午7:43:55
	 */
	List<Conversion> selectActorLevelRechargeTimeList(BaseSearchData searchData,
			HttpSession session) throws Exception;
	/**
	 * 各等级充值次数
	 * @Title: exportActorLevelRechargeTimeList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年4月1日 下午7:43:59
	 */
	List<Conversion> exportActorLevelRechargeTimeList(BaseSearchData searchData,
			HttpSession session) throws Exception;
	
	/**
	 * 渠道收入
	 * @Title: selectChannelIncomeList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author moxw
	 * @date 2016年4月1日 下午8:24:43
	 */
	List<Conversion> selectChannelIncomeList(BaseSearchData searchData,
			HttpSession session) throws Exception;
	/**
	 * 渠道收入
	 * @Title: selectChannelIncomeList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author moxw
	 * @date 2016年4月1日 下午8:24:43
	 */
	List<Conversion> exportChannelIncomeList(BaseSearchData searchData,
			HttpSession session) throws Exception;
	/**
	 * 地区收入
	 * @Title: selectChannelIncomeList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author moxw
	 * @date 2016年4月1日 下午8:24:43
	 */
	List<Conversion> selectCityIncomeList(BaseSearchData searchData,
			HttpSession session) throws Exception;
	/**
	 * 地区收入
	 * @Title: selectChannelIncomeList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author moxw
	 * @date 2016年4月1日 下午8:24:43
	 */
	List<Conversion> exportCityIncomeList(BaseSearchData searchData,
			HttpSession session) throws Exception;
	
	/**
	 * 地区账户
	 * @Title: selectChannelIncomeList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author moxw
	 * @date 2016年4月1日 下午8:24:43
	 */
	List<Conversion> selectCityAccountsList(BaseSearchData searchData,
			HttpSession session) throws Exception;
	
	/**
	 * 地区账户
	 * @Title: selectChannelIncomeList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author moxw
	 * @date 2016年4月1日 下午8:24:43
	 */
	List<Conversion> exportCityAccountsList(BaseSearchData searchData,
			HttpSession session) throws Exception;
	/**
	 * @Title: selectDayARPUList
	 * @Description: 日ARPU
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:35:42
	 */
	List<Conversion> selectDayARPUList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportDayARPUList
	 * @Description: 日ARPU导出
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:36:07
	 */
	List<Conversion> exportDayARPUList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectMonthARPUList
	 * @Description: 月ARPU
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:36:29
	 */
	List<Conversion> selectMonthARPUList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportMonthARPUList
	 * @Description: 月ARPU导出
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:36:54
	 */
	List<Conversion> exportMonthARPUList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * 
	 * @Title: selectDayARPPUList
	 * @Description: 日ARPPU
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:37:12
	 */
	List<Conversion> selectDayARPPUList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportDayARPPUList
	 * @Description: 日ARPPU导出
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:37:16
	 */
	List<Conversion> exportDayARPPUList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectMonthARPPUList
	 * @Description: 月ARPPU
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:37:19
	 */
	List<Conversion> selectMonthARPPUList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportMonthARPPUList
	 * @Description: 月ARPPU导出
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:37:29
	 */
	List<Conversion> exportMonthARPPUList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectLocationOfDailyRateOfPay
	 * @Description: 地区日均付费率
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:37:33
	 */
	List<Conversion> selectLocationOfDailyRateOfPay(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportLocationOfDailyRateOfPay
	 * @Description: 地区日均付费率导出
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:37:36
	 */
	List<Conversion> exportLocationOfDailyRateOfPay(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectLocationOfDailyARPU
	 * @Description: 地区日均ARPU
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:37:40
	 */
	List<Conversion> selectLocationOfDailyARPU(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportLocationOfDailyARPU
	 * @Description: 地区日均ARPU导出
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:37:44
	 */
	List<Conversion> exportLocationOfDailyARPU(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectLocationOfDailyARPPU
	 * @Description: 地区日均ARPPU
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:37:48
	 */
	List<Conversion> selectLocationOfDailyARPPU(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportLocationOfDailyARPPU
	 * @Description: 地区日均ARPPU导出
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:37:56
	 */
	List<Conversion> exportLocationOfDailyARPPU(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectChannelOfDailyRateOfPay
	 * @Description: 渠道日均付费率
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:37:59
	 */
	List<Conversion> selectChannelOfDailyRateOfPay(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportChannelOfDailyRateOfPay
	 * @Description: 渠道日均付费率导出
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:38:04
	 */
	List<Conversion> exportChannelOfDailyRateOfPay(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectChannelOfDailyARPU
	 * @Description: 渠道日均ARPU
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:38:07
	 */
	List<Conversion> selectChannelOfDailyARPU(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportChannelOfDailyARPU
	 * @Description: 渠道日均ARPU导出
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:38:10
	 */
	List<Conversion> exportChannelOfDailyARPU(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectChannelOfDailyARPPU
	 * @Description: 渠道日均ARPPU
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:38:13
	 */
	List<Conversion> selectChannelOfDailyARPPU(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportChannelOfDailyARPPU
	 * @Description: 渠道日均ARPPU导出
	 * @param searchData
	 * @return List<Conversion>
	 * @author dmw
	 * @date 2016年3月16日 下午2:38:18
	 */
	List<Conversion> exportChannelOfDailyARPPU(BaseSearchData searchData,
			HttpSession session) throws Exception;
	/**
	 * 日付费率（按账号）
	 * @Title: selectDayPayRateOfAccoutList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author hwj
	 * @date 2017-9-29 下午01:24:20
	 */
	List<Conversion> selectDayPayRateOfAccoutList(BaseSearchData searchData) throws Exception;
	
	/**
	 * 周付费率（按账号）
	 * @Title: selectWeekPayRateOfAccoutList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author hwj
	 * @date 2017-10-9 上午09:41:39
	 */
	List<Conversion> selectWeekPayRateOfAccoutList(BaseSearchData searchData) throws Exception;
	/**
	 * 月付费率（按账号）
	 * @Title: selectMonthPayRateOfAccoutList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Conversion>
	 * @author hwj
	 * @date 2017-10-9 上午10:28:56
	 */
	List<Conversion> selectMonthPayRateOfAccoutList(BaseSearchData searchData) throws Exception;
	
	

}
