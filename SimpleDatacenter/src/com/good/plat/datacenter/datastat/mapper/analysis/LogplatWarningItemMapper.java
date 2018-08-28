package com.good.plat.datacenter.datastat.mapper.analysis;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningItem;

public interface LogplatWarningItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogplatWarningItem record);

    int insertSelective(LogplatWarningItem record);

    LogplatWarningItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogplatWarningItem record);

    int updateByPrimaryKeyWithBLOBs(LogplatWarningItem record);

    int updateByPrimaryKey(LogplatWarningItem record);

	List<LogplatWarningItem> selectItems(BaseSearchData searchData);

    List<LogplatWarningItem> selectItemsByEventID(Integer id);

	List<LogplatWarningItem> selectAllItems(BaseSearchData searchData);
}