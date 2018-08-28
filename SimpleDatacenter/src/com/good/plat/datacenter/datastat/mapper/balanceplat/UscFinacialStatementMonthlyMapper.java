package com.good.plat.datacenter.datastat.mapper.balanceplat;

import com.good.plat.datacenter.datastat.entity.balanceplat.UscFinacialStatementMonthly;

public interface UscFinacialStatementMonthlyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UscFinacialStatementMonthly record);

    int insertSelective(UscFinacialStatementMonthly record);

    UscFinacialStatementMonthly selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UscFinacialStatementMonthly record);

    int updateByPrimaryKey(UscFinacialStatementMonthly record);
}