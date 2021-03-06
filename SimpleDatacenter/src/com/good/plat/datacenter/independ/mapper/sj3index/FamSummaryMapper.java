package com.good.plat.datacenter.independ.mapper.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.FamSummary;

/**
 * 秘境统计
 * @ClassName: FamSummaryMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-3-3 下午05:34:45
 */
public interface FamSummaryMapper {

	/**
	 * 秘境统计
	 * @Title: selectFamSummaryList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<FamSummary>
	 * @author hwj
	 * @date 2017-3-3 下午05:38:31
	 */
	List<FamSummary> selectFamSummaryList(BaseSearchData searchData)throws Exception;
}
