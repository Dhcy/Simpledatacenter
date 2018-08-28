package com.good.plat.datacenter.datastat.mapper.sdkdata;

import com.good.plat.datacenter.datastat.entity.sdkdata.SdkVipDist;

public interface SdkVipDistMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SdkVipDist record);

    int insertSelective(SdkVipDist record);

    SdkVipDist selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SdkVipDist record);

    int updateByPrimaryKey(SdkVipDist record);
}