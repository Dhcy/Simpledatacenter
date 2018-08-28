package com.good.plat.datacenter.datastat.service.xgfinance;

import java.util.List;

import com.good.plat.datacenter.common.base.XgFinanceBaseSearchData;
import com.good.plat.datacenter.datastat.entity.xgfinance.FinanceCost;
/**
 * 财务费用业务
 * @ClassName: FinanceCostService
 * @Description: TODO
 * @author hwj
 * @date 2017-8-12 上午11:08:46
 */
public interface FinanceCostService {

	List<FinanceCost> selectFinanceCostList(XgFinanceBaseSearchData searchData)throws Exception;
}
