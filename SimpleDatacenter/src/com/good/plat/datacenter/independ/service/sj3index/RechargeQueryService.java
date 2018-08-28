package com.good.plat.datacenter.independ.service.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.RechargeQuery;

/**
 * 充值查询服务
 * @ClassName: RechargeQueryService
 * @Description: TODO
 * @author hwj
 * @date 2017-3-9 上午09:27:12
 */
public interface RechargeQueryService {

	/**
	 * 充值查询
	 * @Title: selectRechargeList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<RechargeQuery>
	 * @author hwj
	 * @date 2017-3-9 上午09:25:58
	 */
	List<RechargeQuery> selectRechargeList(BaseSearchData searchData)throws Exception;
}
