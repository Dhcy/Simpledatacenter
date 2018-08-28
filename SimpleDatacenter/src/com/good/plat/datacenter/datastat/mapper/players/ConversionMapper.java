package com.good.plat.datacenter.datastat.mapper.players;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.players.Conversion;

/**
 * @ClassName: ConversionMapper
 * @Description: 付费转化
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface ConversionMapper {
	/**
	 * @Title: selectNewChargeUserNumList
	 * @Description: 新增付费玩家
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午5:57:26
	 */
	List<Conversion> selectNewChargeUserNumList (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportNewChargeUserNumList
	 * @Description: 新增付费玩家导出
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午5:57:44
	 */
	List<Conversion> exportNewChargeUserNumList (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectChargeUserNumList
	 * @Description: 累计付费玩家
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午5:58:58
	 */
	List<Conversion> selectChargeUserNumList (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportChargeUserNumList
	 * @Description: 累计付费玩家导出
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午6:00:05
	 */
	List<Conversion> exportChargeUserNumList (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectTotalPaidRate
	 * @Description: 总体付费率
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午6:00:56
	 */
	List<Conversion> selectTotalPaidRate (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportTotalPaidRate
	 * @Description: 总体付费率导出
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午6:01:13
	 */
	List<Conversion> exportTotalPaidRate (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectFirstDayChargeRate
	 * @Description: 首日付费率
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午6:03:14
	 */
	List<Conversion> selectFirstDayChargeRate (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportFirstDayChargeRate
	 * @Description: 首日付费率导出
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午6:03:47
	 */
	List<Conversion> exportFirstDayChargeRate (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectFirstWeekChargeRate
	 * @Description: 首周付费率
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午6:04:29
	 */
	List<Conversion> selectFirstWeekChargeRate (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportFirstWeekChargeRate
	 * @Description: 首周付费率导出
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午6:04:33
	 */
	List<Conversion> exportFirstWeekChargeRate (BaseSearchData searchData);
	
	
	
	/**
	 * @Title: selectFirstMonthChargeRate
	 * @Description: 首月付费率导出
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午6:05:29
	 */
	List<Conversion> selectFirstMonthChargeRate (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportFirstMonthChargeRate
	 * @Description: 首月付费率导出
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午6:04:33
	 */
	List<Conversion> exportFirstMonthChargeRate (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectFirstChargeTotalDayNumList
	 * @Description: 游戏天数
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午6:25:30
	 */
	List<Conversion> selectFirstChargeTotalDayNumList (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportFirstChargeTotalDayNumList
	 * @Description: 游戏天数导出
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午6:25:30
	 */
	List<Conversion> exportFirstChargeTotalDayNumList (BaseSearchData searchData);
	
	
	
	/**
	 * @Title: selectFirstChargeTotalTimeList
	 * @Description: 累计游戏时长
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午6:26:51
	 */
	List<Conversion> selectFirstChargeTotalTimeList (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportFirstChargeTotalTimeList
	 * @Description: 累计游戏时长导出
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午6:25:30
	 */
	List<Conversion> exportFirstChargeTotalTimeList (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectFirstChargeLevel
	 * @Description: 玩家首付等级
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午6:28:43
	 */
	List<Conversion> selectFirstChargeLevel (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportFirstChargeLevel
	 * @Description: 玩家首付等级导出
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午6:29:05
	 */
	List<Conversion> exportFirstChargeLevel (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectFirstChargeMoney
	 * @Description: 玩家首付金额
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午6:30:34
	 */
	List<Conversion> selectFirstChargeMoney (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportFirstChargeMoney
	 * @Description: 玩家首付金额导出
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author dmw
	 * @date 2016年3月15日 下午6:30:49
	 */
	List<Conversion> exportFirstChargeMoney (BaseSearchData searchData);
	
	/**
	 * 
	 * @Title: selectFirstPayMoney
	 * @Description: 玩家首付金额用户数
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author hwj
	 * @date 2017-1-12 下午03:08:35
	 */
	List<Conversion> selectNumOfFirstPayMoney (BaseSearchData searchData);
	
	/**
	 * 
	 * @Title: selectFirstPayLevel
	 * @Description: 玩家首付等级用户数
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author hwj
	 * @date 2017-1-12 下午03:29:11
	 */
	List<Conversion> selectNumOfFirstPayLevel (BaseSearchData searchData);
	
	/**
	 * 
	 * @Title: selectFirstPayWay
	 * @Description: 首付方式
	 * @param searchData
	 * @return
	 * List<Conversion>
	 * @author hwj
	 * @date 2017-1-12 下午03:48:21
	 */
	List<Conversion> selectFirstPayWay (BaseSearchData searchData);
	

}
