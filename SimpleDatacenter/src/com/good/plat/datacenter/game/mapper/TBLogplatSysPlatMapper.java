package com.good.plat.datacenter.game.mapper;

import com.good.plat.datacenter.game.entity.TBLogplatSysPlat;

public interface TBLogplatSysPlatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBLogplatSysPlat record);

    int insertSelective(TBLogplatSysPlat record);

    TBLogplatSysPlat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBLogplatSysPlat record);

    int updateByPrimaryKey(TBLogplatSysPlat record);
}