package com.good.plat.datacenter.independ.mapper.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.RechargeQuery;

/**
 * 充值查询
 * @ClassName: RechargeQueryMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-3-8 下午06:37:37
 */
public interface RechargeQueryMapper {
	
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
