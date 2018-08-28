package com.good.plat.datacenter.datastat.mapper.index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.index.DataCompare;


/**
 * 数据对比
 * @ClassName: DataCompareMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-2-16 上午10:03:14
 */
public interface DataCompareMapper {
	
	/**
	 * 日新增激活,新增账号
	 * @Title: selectNewPlayerList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017-2-16 上午10:09:21
	 */
	List<DataCompare> selectDailyNewPlayerList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 周或者月（新增激活,新增账号)
	 * @Title: selectNewPlayerList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017-2-16 上午10:09:21
	 */
	List<DataCompare> selectWeekOrMonthNewPlayerList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 活跃用户(dau,wau,mau)
	 * @Title: selectAUList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017-2-16 上午10:17:02
	 */
	List<DataCompare> selectAUList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 周或者月活跃用户
	 * @Title: selectWeekOrMonthAUList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017-2-17 下午05:53:54
	 */
	List<DataCompare> selectWeekOrMonthAUList(BaseSearchData searchData)throws Exception;
	
	
	/**
	 * 收入(收入金额，充值次数)
	 * @Title: selectIncomeList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017-2-16 上午10:44:30
	 */
	List<DataCompare> selectIncomeList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 周或者月收入(收入金额，充值次数)
	 * @Title: selectWeekOrMonthIncomeList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017-2-17 下午06:02:36
	 */
	List<DataCompare> selectWeekOrMonthIncomeList(BaseSearchData searchData)throws Exception;
	
	
	/**
	 * 日付费玩家（新增付费玩家，总付费玩家）
	 * @Title: selectPayPlayerList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017-2-16 上午11:01:17
	 */
	List<DataCompare> selectDailyPayPlayerList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 周或者月付费玩家（新增付费玩家，总付费玩家）
	 * @Title: selectWeekAndMonthPayPlayerList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017年2月19日 下午4:24:45
	 */
	List<DataCompare> selectWeekAndMonthPayPlayerList(BaseSearchData searchData)throws Exception;
	
	
	
	
	/**
	 * 日acu,pcu
	 * @Title: selectAcuAndPcuList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017-2-16 上午11:05:25
	 */
	List<DataCompare> selectDailyAcuAndPcuList(BaseSearchData searchData)throws Exception;
	/**
	 * 周或者月acu,pcu
	 * @Title: selectWeekAndMonthAcuAndPcuList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017年2月19日 下午9:55:11
	 */
	List<DataCompare> selectWeekAndMonthAcuAndPcuList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 日付费率
	 * @Title: selectDailyPayRateList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017年2月19日 下午11:05:17
	 */
	List<DataCompare> selectDailyPayRateList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 周或者月付费率
	 * @Title: selectWeekAndMonthPayRateList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017年2月19日 下午11:06:26
	 */
	List<DataCompare> selectWeekAndMonthPayRateList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 次日留存率
	 * @Title: selectDailyDay1RetRateList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017年2月20日 上午12:29:13
	 */
	List<DataCompare> selectDailyDay1RetRateList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 周或者月的次日留存率
	 * @Title: selectWeekAndMonthDay1RetRateList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017年2月20日 上午12:30:03
	 */
	List<DataCompare> selectWeekAndMonthDay1RetRateList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 日acu与puc的比
	 * @Title: selectDailyAcuDivPcuList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017年2月20日 上午1:12:09
	 */
	List<DataCompare> selectDailyAcuDivPcuList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 周或者月acu与puc的比
	 * @Title: selectWeekAndMonthAcuDivPcuList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017年2月20日 上午1:13:08
	 */
	List<DataCompare> selectWeekAndMonthAcuDivPcuList(BaseSearchData searchData)throws Exception;
	
	
	
	
	
	
}
