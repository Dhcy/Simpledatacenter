package com.good.plat.datacenter.datastat.mapper.balanceplat;

import com.good.plat.datacenter.datastat.entity.balanceplat.TBLogplatChannelrate;

public interface TBLogplatChannelrateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBLogplatChannelrate record);

    int insertSelective(TBLogplatChannelrate record);

    TBLogplatChannelrate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBLogplatChannelrate record);

    int updateByPrimaryKey(TBLogplatChannelrate record);
}