package com.good.plat.datacenter.independ.mapper.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.DailySummary;

/**
 * 日常统计
 * @ClassName: DailySummary
 * @Description: TODO
 * @author hwj
 * @date 2017-3-6 下午01:45:26
 */
public interface DailySummaryMapper {
	
	/**
	 * 活跃度
	 * @Title: selectActiveList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DailySummary>
	 * @author hwj
	 * @date 2017-3-6 下午01:52:47
	 */
	List<DailySummary> selectActiveList(BaseSearchData searchData)throws Exception;

}
