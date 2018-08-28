package com.good.plat.datacenter.datastat.mapper.sdkdata;

import com.good.plat.datacenter.datastat.entity.sdkdata.SdkDownloadStat;

public interface SdkDownloadStatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SdkDownloadStat record);

    int insertSelective(SdkDownloadStat record);

    SdkDownloadStat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SdkDownloadStat record);

    int updateByPrimaryKey(SdkDownloadStat record);
}