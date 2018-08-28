package com.good.plat.datacenter.datastat.service.channels;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.channels.ChannelData;

/**
 * @ClassName: ChannelDataService
 * @Description: 渠道数据
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface ChannelDataService {
	
	/**
	 * @Title: selectPartnerAmount
	 * @Description: 数量指标
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月17日 上午11:50:10
	 */
	List<ChannelData> selectPartnerAmount (BaseSearchData searchData, 
			HttpSession session) throws Exception;
	
	/**
	 * 
	 * @Title: selectPartnerAmountDetail
	 * @Description: 数量指标明细
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<ChannelData>
	 * @author moxw
	 * @date 2016年4月20日 上午11:51:20
	 */
	List<ChannelData> selectPartnerAmountDetail (BaseSearchData searchData, 
			HttpSession session) throws Exception;
	
	/**
	 * 
	 * @Title: selectChannelDau
	 * @Description: 渠道日活跃数
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<ChannelData>
	 * @author moxw
	 * @date 2016年4月20日 上午11:51:20
	 */
	List<ChannelData> selectChannelDau (BaseSearchData searchData, 
			HttpSession session) throws Exception;
	/**
	 * 
	 * @Title: selectChannelDayRetention
	 * @Description:  渠道日留存率 
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<ChannelData>
	 * @author moxw
	 * @date 2016年4月20日 上午11:51:20
	 */
	List<ChannelData> selectChannelDayRetention (BaseSearchData searchData, 
			HttpSession session) throws Exception;
	/**
	 * 
	 * @Title: selectChannelActorFirstRechargeInTime
	 * @Description: 渠道玩家在指定期限内首次充值数(率)
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<ChannelData>
	 * @author moxw
	 * @date 2016年4月20日 上午11:51:20
	 */
	List<ChannelData> selectChannelActorFirstRechargeInTime (BaseSearchData searchData, 
			HttpSession session) throws Exception;
	
	
	/**
	 * @Title: exportPartnerAmount
	 * @Description: 导出数量指标
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:33:14
	 */
	List<ChannelData> exportPartnerAmount (BaseSearchData searchData, 
			HttpSession session) throws Exception;
	
	
	/**
	 * 首周付费率: 渠道首周充值玩家数 / 该渠道该周玩家数<br/>
	 * 平均日活跃 : 渠道日活跃数取平均值 <br/>
	 * 平均次日留存率 : 渠道次日留存数/渠道改日活跃率 再取平均 <br/>
	 * @Title: selectPartnerQuality
	 * @Description: 质量指标
	 * @param searchData
	 * @param session
	 * @throws Exception
	 * @return
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:35:06
	 */
	List<ChannelData> selectPartnerQuality (BaseSearchData searchData, 
			HttpSession session) throws Exception;
	
	
	/**
	 * @Title: exportPartnerQuality
	 * @Description: 导出质量指标
	 * @param searchData
	 * @param session
	 * @return
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:35:00
	 */
	List<ChannelData> exportPartnerQuality (BaseSearchData searchData, 
			HttpSession session) throws Exception;
	
	
	/**
	 * @Title: selectPartnerIncome
	 * @Description: 收入指标
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:35:58
	 */
	List<ChannelData> selectPartnerIncome (BaseSearchData searchData, 
			HttpSession session) throws Exception;
	
	
	/**
	 * @Title: selectPartnerDailyIncome
	 * @Description:渠道日收入
	 * @param searchData
	 * @return
	 * List<ChannelData>
	 * @author moxw
	 * @date 2016年3月15日 上午10:35:58
	 */
	List<ChannelData> selectPartnerDailyIncome (BaseSearchData searchData, 
			HttpSession session) throws Exception;
	/**
	 * @Title: exportPartnerIncome
	 * @Description: 导出收入指标
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月17日 下午3:10:32
	 */
	List<ChannelData> exportPartnerIncome (BaseSearchData searchData, 
			HttpSession session) throws Exception;
	
	
	/**
	 * @Title: selectInstallList
	 * @Description: 设备激活
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月17日 下午3:27:32
	 */
	List<ChannelData> selectInstallList (BaseSearchData searchData, 
			HttpSession session) throws Exception;
	
	
	/**
	 * @Title: selectNewPlayerList
	 * @Description: 新增玩家
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:41:48
	 */
	List<ChannelData> selectNewPlayerList (BaseSearchData searchData, 
			HttpSession session) throws Exception;
	
	
	/**
	 * @Title: selectDauList
	 * @Description: 日活跃
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:41:54
	 */
	List<ChannelData> selectDauList (BaseSearchData searchData, 
			HttpSession session) throws Exception;
	
	/**
	 * @Title: selectIncomeNumList
	 * @Description: 收入
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:41:58
	 */
	List<ChannelData> selectIncomeNumList (BaseSearchData searchData, 
			HttpSession session) throws Exception;
	
	
	/**
	 * @Title: selectChargePlayerNumList
	 * @Description: 日付费人数
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:42:02
	 */
	List<ChannelData> selectChargePlayerNumList (BaseSearchData searchData, 
			HttpSession session) throws Exception;
	
	/**
	 * @Title: selectDailyARPU
	 * @Description: ARPU(日)
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<ChannelData>
	 * @author dmw
	 * @date 2016年3月15日 上午10:42:06
	 */
	List<ChannelData> selectDailyARPU (BaseSearchData searchData, 
			HttpSession session) throws Exception;
	
}
