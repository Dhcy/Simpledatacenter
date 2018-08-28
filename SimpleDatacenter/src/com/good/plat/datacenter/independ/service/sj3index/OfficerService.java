package com.good.plat.datacenter.independ.service.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Officer;

/**
 * 官职分布
 * @ClassName: OfficerService
 * @Description: TODO
 * @author hwj
 * @date 2017-2-28 下午05:17:08
 */
public interface OfficerService {

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
