package com.good.plat.datacenter.datastat.mapper.balanceplat;

import com.good.plat.datacenter.datastat.entity.balanceplat.DZPTChannelAccounting;

public interface DZPTChannelAccountingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DZPTChannelAccounting record);

    int insertSelective(DZPTChannelAccounting record);

    DZPTChannelAccounting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DZPTChannelAccounting record);

    int updateByPrimaryKey(DZPTChannelAccounting record);
}