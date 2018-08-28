package com.good.plat.datacenter.datastat.mapper.channels;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.channels.ChannelData;

/**
 * @ClassName: ChannelDataMapper
 * @Description: 渠道数据
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface ChannelDataMapper {
	
	/**
	 * @Title: selectPartnerAmount
	 * @Description: 数量指标
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:32:40
	 */
	List<ChannelData> selectPartnerAmount (BaseSearchData searchData);
	
	/**
	 * 
	 * @Title: selectPartnerAmountDetail
	 * @Description: 数量指标明细
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author moxw
	 * @date 2016年4月20日 上午11:52:31
	 */
	List<ChannelData> selectPartnerAmountDetail (BaseSearchData searchData);
	
	/**
	 * group by channel,statdate 
	 * @Title: selectChannelDau
	 * @Description: 渠道日活跃数
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author moxw
	 * @date 2016年4月22日 下午3:40:36
	 */
	List<ChannelData> selectChannelDau (BaseSearchData searchData);
	/**
	 * group by channel,statdate
	 * @Title: selectChannelDayRetention
	 * @Description: 渠道日留存率
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author moxw
	 * @date 2016年4月22日 下午3:40:36
	 */
	List<ChannelData> selectChannelDayRetention (BaseSearchData searchData);
	/**
	 * 渠道玩家在指定期限内首次充值数(率)
	 * group by channel<br/>
	 * checktype1 \ checktype2  天数<br/>
	 * 
	 * @Title: selectChannelActorFirstRechargeInTime
	 * @Description: 
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author moxw
	 * @date 2016年4月22日 下午3:40:36
	 */
	List<ChannelData> selectChannelActorFirstRechargeInTime (BaseSearchData searchData);
	
	/**
	 * @Title: exportPartnerAmount
	 * @Description: 导出数量指标
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:33:14
	 */
	List<ChannelData> exportPartnerAmount (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectPartnerQuality
	 * @Description: 质量指标
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:35:06
	 */
	List<ChannelData> selectPartnerQuality (BaseSearchData searchData);
	
	/**
	 * 渠道次日留存数与次日留存率
	 * @Title: selectChannelRetentionAnd1DayRetRate
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author moxw
	 * @date 2016年8月30日 下午2:18:05
	 */
	List<ChannelData> selectChannelRetentionAnd1DayRetRate(BaseSearchData searchData);
	/**
	 * @Title: exportPartnerQuality
	 * @Description: 导出质量指标
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:35:00
	 */
	List<ChannelData> exportPartnerQuality (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectPartnerIncome
	 * @Description: 收入指标
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:35:58
	 */
	List<ChannelData> selectPartnerIncome (BaseSearchData searchData);
	
	/**
	 * @Title: selectPartnerDailyIncome
	 * @Description: 渠道每日收入
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author moxw
	 * @date 2016年3月15日 上午10:35:58
	 */
	List<ChannelData> selectPartnerDailyIncome (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportPartnerIncome
	 * @Description: 导出收入指标
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:36:29
	 */
	List<ChannelData> exportPartnerIncome (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectInstallList
	 * @Description: 设备激活
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:38:02
	 */
	List<ChannelData> selectInstallList (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectNewPlayerList
	 * @Description: 新增玩家
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:41:48
	 */
	List<ChannelData> selectNewPlayerList (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectDauList
	 * @Description: 日活跃
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:41:54
	 */
	List<ChannelData> selectDauList (BaseSearchData searchData);
	
	/**
	 * @Title: selectIncomeNumList
	 * @Description: 收入
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:41:58
	 */
	List<ChannelData> selectIncomeNumList (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectChargePlayerNumList
	 * @Description: 日付费人数
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:42:02
	 */
	List<ChannelData> selectChargePlayerNumList (BaseSearchData searchData);
	
	/**
	 * @Title: selectDailyARPU
	 * @Description: ARPU(日)
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:42:06
	 */
	List<ChannelData> selectDailyARPU (BaseSearchData searchData);
	
}
