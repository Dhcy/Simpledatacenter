package com.good.plat.datacenter.datastat.mapper.analysis;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.WarningBaseSearchData;
import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningNotice;
import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningNoticeWithBLOBs;

public interface LogplatWarningNoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogplatWarningNoticeWithBLOBs record);

    int insertSelective(LogplatWarningNoticeWithBLOBs record);

    LogplatWarningNoticeWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogplatWarningNoticeWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(LogplatWarningNoticeWithBLOBs record);

    int updateByPrimaryKey(LogplatWarningNotice record);
    
    //
    List<LogplatWarningNoticeWithBLOBs> selectAllWarningNotice(int topNum);
	List<LogplatWarningNoticeWithBLOBs> selectWarningNotice(WarningBaseSearchData searchData);
}