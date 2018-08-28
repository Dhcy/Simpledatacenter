package com.good.plat.datacenter.datastat.mapper.sdkdata;

import com.good.plat.datacenter.datastat.entity.sdkdata.SdkVipStat;

public interface SdkVipStatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SdkVipStat record);

    int insertSelective(SdkVipStat record);

    SdkVipStat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SdkVipStat record);

    int updateByPrimaryKey(SdkVipStat record);
}