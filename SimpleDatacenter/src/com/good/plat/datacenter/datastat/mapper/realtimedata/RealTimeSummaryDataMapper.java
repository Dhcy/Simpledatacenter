package com.good.plat.datacenter.datastat.mapper.realtimedata;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.realtimedata.RealTimeSummaryData;

/**
 * 实时数据-游戏概况
 * @ClassName: RealTimeSummaryDataMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-12-11 下午03:18:08
 */
public interface RealTimeSummaryDataMapper {
	/**
	 * 实时统计玩家数据
	 * @Title: selectRalTimeSummaryDataList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<RealTimeSummaryData>
	 * @author hwj
	 * @date 2017-12-11 下午03:22:07
	 */
	public List<RealTimeSummaryData> selectRalTimeSummaryDataList(BaseSearchData searchData)throws Exception;
}
