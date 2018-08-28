package com.good.plat.datacenter.datastat.mapper.balanceplat;

import com.good.plat.datacenter.datastat.entity.balanceplat.UscFinacialStatementWeekly;

public interface UscFinacialStatementWeeklyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UscFinacialStatementWeekly record);

    int insertSelective(UscFinacialStatementWeekly record);

    UscFinacialStatementWeekly selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UscFinacialStatementWeekly record);

    int updateByPrimaryKey(UscFinacialStatementWeekly record);
}