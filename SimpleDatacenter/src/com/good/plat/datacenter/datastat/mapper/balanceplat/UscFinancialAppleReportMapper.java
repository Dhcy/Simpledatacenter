package com.good.plat.datacenter.datastat.mapper.balanceplat;

import com.good.plat.datacenter.datastat.entity.balanceplat.UscFinancialAppleReport;

public interface UscFinancialAppleReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UscFinancialAppleReport record);

    int insertSelective(UscFinancialAppleReport record);

    UscFinancialAppleReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UscFinancialAppleReport record);

    int updateByPrimaryKey(UscFinancialAppleReport record);
}