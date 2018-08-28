package com.good.plat.datacenter.sys.mapper.log;

import com.good.plat.datacenter.sys.entity.log.SysAccessLog;

public interface SysAccessLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysAccessLog record);

    int insertSelective(SysAccessLog record);

    SysAccessLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysAccessLog record);

    int updateByPrimaryKeyWithBLOBs(SysAccessLog record);

    int updateByPrimaryKey(SysAccessLog record);
}