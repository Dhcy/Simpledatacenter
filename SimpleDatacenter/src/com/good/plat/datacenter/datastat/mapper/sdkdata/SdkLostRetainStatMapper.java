package com.good.plat.datacenter.datastat.mapper.sdkdata;

import com.good.plat.datacenter.datastat.entity.sdkdata.SdkLostRetainStat;

public interface SdkLostRetainStatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SdkLostRetainStat record);

    int insertSelective(SdkLostRetainStat record);

    SdkLostRetainStat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SdkLostRetainStat record);

    int updateByPrimaryKey(SdkLostRetainStat record);
}