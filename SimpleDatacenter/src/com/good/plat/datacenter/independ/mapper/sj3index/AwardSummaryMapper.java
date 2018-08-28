package com.good.plat.datacenter.independ.mapper.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.AwardSummary;

/**
 * 悬赏统计
 * @ClassName: AwardSummaryMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-3-3 下午06:11:47
 */
public interface AwardSummaryMapper {
	
	/**
	 * 悬赏统计
	 * @Title: selectAwardSummaryList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<AwardSummary>
	 * @author hwj
	 * @date 2017-3-3 下午06:21:02
	 */
	List<AwardSummary> selectAwardSummaryList(BaseSearchData searchData)throws Exception;

}
