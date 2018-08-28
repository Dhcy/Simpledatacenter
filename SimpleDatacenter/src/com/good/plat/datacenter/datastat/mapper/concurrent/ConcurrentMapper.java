package com.good.plat.datacenter.datastat.mapper.concurrent;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.concurrent.ConcurrentData;

public interface ConcurrentMapper {
	List<ConcurrentData> selectConcurrentList(BaseSearchData searchData) throws Exception;
	
	/**
	 * 时段分布
	 * @Title: selectTimeDistributeList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<ConcurrentData>
	 * @author hwj
	 * @date 2017-2-9 下午02:55:28
	 */
	List<ConcurrentData>  selectTimeDistributeList(BaseSearchData searchData) throws Exception;
	/**
	 * 间隔分布
	 * @Title: selectDurationDistributeList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<ConcurrentData>
	 * @author hwj
	 * @date 2017-2-9 下午02:55:54
	 */
	List<ConcurrentData>  selectDurationDistributeList(BaseSearchData searchData) throws Exception;
	/**
	 * 今天跟昨天的在线数据
	 * @Title: selectTodayAndYesterdayHourList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<ConcurrentData>
	 * @author hwj
	 * @date 2017-3-1 下午03:22:25
	 */
	List<ConcurrentData> selectTodayAndYesterdayHourList(BaseSearchData searchData) throws Exception;
	/**
	 * 今天前30天内pcu最大值
	 * @Title: getMaxOfPcu
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * ConcurrentData
	 * @author hwj
	 * @date 2017-3-1 下午04:09:01
	 */
	ConcurrentData getMaxOfPcu(BaseSearchData searchData) throws Exception;
	
	/**
	 * 游戏接口基本配置
	 * @Title: selectBaseGameConfig
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * ConcurrentData
	 * @author hwj
	 * @date 2017-3-2 上午11:04:42
	 */
	ConcurrentData selectBaseGameConfig(BaseSearchData searchData) throws Exception;
	/**
	 * 每个时间段的acu人数
	 * @Title: selectEveryTimePointAcuList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<ConcurrentData>
	 * @author hwj
	 * @date 2018-1-19 下午02:23:16
	 */
	public List<ConcurrentData> selectDurationAcuList(BaseSearchData searchData) throws Exception;
	
	
}
