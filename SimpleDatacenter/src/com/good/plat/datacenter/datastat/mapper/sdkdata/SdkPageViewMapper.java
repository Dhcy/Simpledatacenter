package com.good.plat.datacenter.datastat.mapper.sdkdata;

import com.good.plat.datacenter.datastat.entity.sdkdata.SdkPageView;

public interface SdkPageViewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SdkPageView record);

    int insertSelective(SdkPageView record);

    SdkPageView selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SdkPageView record);

    int updateByPrimaryKey(SdkPageView record);
}