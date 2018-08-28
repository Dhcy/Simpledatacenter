package com.good.plat.datacenter.independ.mapper.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Officer;

/**
 * 官职分布
 * @ClassName: OfficerMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-2-28 下午05:04:37
 */
public interface OfficerMapper {
	
	/**
	 * 官职分布
	 * @Title: selectOfficerDistributionList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Officer>
	 * @author hwj
	 * @date 2017-2-28 下午05:16:22
	 */
	List<Officer> selectOfficerDistributionList(BaseSearchData searchData)throws Exception;
}
