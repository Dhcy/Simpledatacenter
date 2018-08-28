package com.good.plat.datacenter.datastat.mapper.balanceplat;

import com.good.plat.datacenter.datastat.entity.balanceplat.UscFinancialIosProduct;

public interface UscFinancialIosProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UscFinancialIosProduct record);

    int insertSelective(UscFinancialIosProduct record);

    UscFinancialIosProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UscFinancialIosProduct record);

    int updateByPrimaryKey(UscFinancialIosProduct record);
}