package com.good.plat.datacenter.datastat.mapper.sdkdata;

import com.good.plat.datacenter.datastat.entity.sdkdata.SdkNewsStat;

public interface SdkNewsStatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SdkNewsStat record);

    int insertSelective(SdkNewsStat record);

    SdkNewsStat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SdkNewsStat record);

    int updateByPrimaryKey(SdkNewsStat record);
}