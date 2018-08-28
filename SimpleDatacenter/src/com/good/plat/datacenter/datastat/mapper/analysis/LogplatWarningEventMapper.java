package com.good.plat.datacenter.datastat.mapper.analysis;

import java.util.List;

import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningEvent;

public interface LogplatWarningEventMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogplatWarningEvent record);

    int insertSelective(LogplatWarningEvent record);

    LogplatWarningEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogplatWarningEvent record);

    int updateByPrimaryKey(LogplatWarningEvent record);
    List<LogplatWarningEvent> selectAll();
}