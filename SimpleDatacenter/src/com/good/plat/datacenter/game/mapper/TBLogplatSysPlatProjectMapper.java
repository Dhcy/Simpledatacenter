package com.good.plat.datacenter.game.mapper;

import com.good.plat.datacenter.game.entity.TBLogplatSysPlatProject;

public interface TBLogplatSysPlatProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBLogplatSysPlatProject record);

    int insertSelective(TBLogplatSysPlatProject record);

    TBLogplatSysPlatProject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBLogplatSysPlatProject record);

    int updateByPrimaryKey(TBLogplatSysPlatProject record);
}