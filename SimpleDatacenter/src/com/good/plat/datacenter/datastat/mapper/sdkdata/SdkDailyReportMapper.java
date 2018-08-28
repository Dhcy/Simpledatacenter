package com.good.plat.datacenter.datastat.mapper.sdkdata;

import com.good.plat.datacenter.datastat.entity.sdkdata.SdkDailyReport;

public interface SdkDailyReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SdkDailyReport record);

    int insertSelective(SdkDailyReport record);

    SdkDailyReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SdkDailyReport record);

    int updateByPrimaryKey(SdkDailyReport record);
}