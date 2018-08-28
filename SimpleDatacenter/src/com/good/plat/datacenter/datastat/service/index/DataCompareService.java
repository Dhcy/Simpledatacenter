package com.good.plat.datacenter.datastat.service.index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.index.DataCompare;


/**
 * 数据对比
 * @ClassName: DataCompareService
 * @Description: TODO
 * @author hwj
 * @date 2017-2-16 上午11:07:21
 */
public interface DataCompareService {
	
	/**
	 * 新增激活,新增账号
	 * @Title: selectNewPlayerList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017-2-16 上午10:09:21
	 */
	List<DataCompare> selectNewPlayerList(BaseSearchData searchData)throws Exception;
	
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
	 * 付费玩家（新增付费玩家，总付费玩家）
	 * @Title: selectPayPlayerList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017-2-16 上午11:01:17
	 */
	List<DataCompare> selectPayPlayerList(BaseSearchData searchData)throws Exception;
	
	/**
	 * acu,pcu
	 * @Title: selectAcuAndPcuList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017-2-16 上午11:05:25
	 */
	List<DataCompare> selectAcuAndPcuList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 付费率
	 * @Title: selectPayRateList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017年2月19日 下午11:08:01
	 */
	List<DataCompare> selectPayRateList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 次日留存率
	 * @Title: selectDay1RetainRateList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017年2月20日 上午12:31:35
	 */
	List<DataCompare> selectDay1RetainRateList(BaseSearchData searchData)throws Exception;
	/**
	 * acu比pcu
	 * @Title: selectAcuDivPcuList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DataCompare>
	 * @author hwj
	 * @date 2017年2月20日 上午1:14:29
	 */
	List<DataCompare> selectAcuDivPcuList(BaseSearchData searchData)throws Exception;
	
}
