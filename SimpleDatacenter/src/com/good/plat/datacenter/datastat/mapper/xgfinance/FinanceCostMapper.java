package com.good.plat.datacenter.datastat.mapper.xgfinance;

import java.util.List;

import com.good.plat.datacenter.common.base.XgFinanceBaseSearchData;
import com.good.plat.datacenter.datastat.entity.xgfinance.FinanceCost;

/**
 * 财务费用
 * @ClassName: FinanceCostMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-8-12 上午10:44:40
 */
public interface FinanceCostMapper {
	/**
	 * 财务费用
	 * @Title: selectFinanceCost
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<HongKongfinanceCost>
	 * @author hwj
	 * @date 2017-8-12 上午10:51:21
	 */
	List<FinanceCost> selectFinanceCostList(XgFinanceBaseSearchData searchData)throws Exception;
}
