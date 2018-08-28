package com.good.plat.datacenter.datastat.mapper.index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.index.Report;

/**
 * 运营报表
 * @ClassName: reportMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-2-10 下午03:06:47
 */
public interface ReportMapper {
	
	/**
	 * <!--新增设备,新增玩家,转化率,活跃玩家,acu,pcu -->
	 * @Title: selectDailyBaseDataList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<report>
	 * @author hwj
	 * @date 2017-2-10 下午03:13:01
	 */
	List<Report> selectDailyBaseDataList(BaseSearchData searchData)throws Exception;
	/**
	 * <!--游戏次数,平均日游戏时长（分）,平均日游戏次数 -->
	 * @Title: selectDailyGameData
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<report>
	 * @author hwj
	 * @date 2017-2-10 下午03:16:08
	 */
	List<Report> selectDailyGameDataList(BaseSearchData searchData)throws Exception;
	/**
	 *新增玩家次日留存,新增玩家3日留存,新增玩家7日留存
	 * @Title: selectDailyNewUserRetationList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<report>
	 * @author hwj
	 * @date 2017-2-10 下午03:24:40
	 */
	List<Report> selectDailyNewUserRetationList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 设备留存率(设备次日留存,设备3日留存,设备7日留存)
	 * @Title: selectDailyDeviceRetationRate
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Report>
	 * @author hwj
	 * @date 2017-2-10 下午05:08:34
	 */
	List<Report> selectDailyDeviceRetationRate(BaseSearchData searchData)throws Exception;
	
	/**
	 * 活跃玩家留存率(活跃玩家次日留存,活跃玩家3日留存,活跃玩家7日留存)
	 * @Title: selectActUserRetationRate
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Report>
	 * @author hwj
	 * @date 2017-2-10 下午08:31:51
	 */
	List<Report> selectActUserRetationRate (BaseSearchData searchData)throws Exception;
	/**
	 * 付费数据(收入（¥）,新增付费玩家数量,付费玩家数量,日付费率,活跃ARPU,活跃ARPPU,新增ARPU,新增ARPPU)
	 * @Title: selectpayDataList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Report>
	 * @author hwj
	 * @date 2017-2-10 下午09:42:56
	 */
	List<Report> selectpayDataList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 累计数据
	 * @Title: selectTotalDataList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * Report
	 * @author hwj
	 * @date 2017-2-13 下午03:00:51
	 */
	Report getTotalData (BaseSearchData searchData)throws Exception;
	
	/**
	 * 
	周基础数据(周新增设备数,周总新增玩家数量（WNU）,周活跃玩家数（WAU）,周平均游戏时长,周平均游戏次数,平均每次游戏时长,ARPU,周总收入金额,周充值玩家数量,周玩家付费率)
	 * @Title: getWeekBaseData
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * Report
	 * @author hwj
	 * @date 2017-2-14 下午12:58:25
	 */
	Report getBaseData(BaseSearchData searchData)throws Exception;
	
	/**
	 * 周流失玩家数量,周流失率,周新增玩家次日留存率,周回流玩家数
	 * @Title: getWeekLostAndRet
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * Report
	 * @author hwj
	 * @date 2017-2-14 下午01:10:20
	 */
	Report getLostAndRet(BaseSearchData searchData)throws Exception;
	
	/**
	 * 在线数据(没区分操作系统，语言，版本)
	 * @Title: selectAcuAndPcuList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Report>
	 * @author hwj
	 * @date 2017-3-27 上午11:26:29
	 */
	List<Report> selectAcuAndPcuList(BaseSearchData searchData)throws Exception;
	
	
}
