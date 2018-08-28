package com.good.plat.datacenter.independ.service.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.ResourceSummary;

/**
 * 资源服务
 * @ClassName: ResourceService
 * @Description: TODO
 * @author hwj
 * @date 2017-3-6 上午10:53:35
 */
public interface ResourceService {

	/**
	 * 资源统计
	 * @Title: selectResourceSummaryList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<ResourceSummary>
	 * @author hwj
	 * @date 2017-3-6 上午10:52:39
	 */
	List<ResourceSummary> selectResourceSummaryList(BaseSearchData searchData)throws Exception;
	/**
	 * 资源类型
	 * @Title: selectResourceTypeList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<ResourceSummary>
	 * @author hwj
	 * @date 2017-3-24 上午10:09:27
	 */
	List<ResourceSummary> selectResourceTypeList(BaseSearchData searchData)throws Exception;
	/**
	 * 资源方式
	 * @Title: selectResourceWayList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<ResourceSummary>
	 * @author hwj
	 * @date 2017-3-24 上午10:10:22
	 */
	List<ResourceSummary> selectResourceWayList(BaseSearchData searchData)throws Exception;
}
