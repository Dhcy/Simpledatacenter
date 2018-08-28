package com.good.plat.datacenter.datastat.mapper.sdkdata;

import com.good.plat.datacenter.datastat.entity.sdkdata.SdkLoginStat;

public interface SdkLoginStatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SdkLoginStat record);

    int insertSelective(SdkLoginStat record);

    SdkLoginStat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SdkLoginStat record);

    int updateByPrimaryKey(SdkLoginStat record);
}