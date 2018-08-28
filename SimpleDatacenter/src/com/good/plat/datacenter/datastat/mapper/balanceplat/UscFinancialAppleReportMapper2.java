package com.good.plat.datacenter.datastat.mapper.balanceplat;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.balanceplat.AppleBalance;

public interface UscFinancialAppleReportMapper2 {
	List<AppleBalance> selectAppleRechargeBalanceList(BaseSearchData searchData);
}