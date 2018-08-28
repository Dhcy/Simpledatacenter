package com.good.plat.datacenter.datastat.mapper.sdkdata;

import com.good.plat.datacenter.datastat.entity.sdkdata.SdkdownloadSrcStat;

public interface SdkdownloadSrcStatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SdkdownloadSrcStat record);

    int insertSelective(SdkdownloadSrcStat record);

    SdkdownloadSrcStat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SdkdownloadSrcStat record);

    int updateByPrimaryKey(SdkdownloadSrcStat record);
}