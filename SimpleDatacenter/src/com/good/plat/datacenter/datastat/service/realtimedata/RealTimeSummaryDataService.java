package com.good.plat.datacenter.datastat.service.realtimedata;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.realtimedata.RealTimeSummaryData;

/**
 * 实时数据——游戏概览
 * @ClassName: RealTimeSummaryDataService
 * @Description: TODO
 * @author hwj
 * @date 2017-12-11 下午03:36:14
 */
public interface RealTimeSummaryDataService {
	/**
	 * 实时总览数据
	 * @Title: selectRalTimeSummaryDataList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<RealTimeSummaryData>
	 * @author hwj
	 * @date 2017-12-11 下午03:40:00
	 */
	public List<RealTimeSummaryData> selectRalTimeSummaryDataList(BaseSearchData searchData)throws Exception;

}
