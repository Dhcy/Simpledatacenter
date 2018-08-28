package com.good.plat.datacenter.independ.mapper.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.MwSummary;

/**
 * 魔物统计
 * @ClassName: MwSummaryMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-3-6 下午04:30:25
 */
public interface MwSummaryMapper {
	/**
	 * 魔物激活
	 * @Title: selectMwActList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<MwSummary>
	 * @author hwj
	 * @date 2017-3-6 下午04:59:01
	 */
	List<MwSummary> selectMwActList(BaseSearchData searchData)throws Exception;
}
