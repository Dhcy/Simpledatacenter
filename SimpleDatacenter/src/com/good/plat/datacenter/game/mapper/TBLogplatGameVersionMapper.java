package com.good.plat.datacenter.game.mapper;

import com.good.plat.datacenter.game.entity.TBLogplatGameVersion;

public interface TBLogplatGameVersionMapper {
    int deleteByPrimaryKey(Integer version_id);

    int insert(TBLogplatGameVersion record);

    int insertSelective(TBLogplatGameVersion record);

    TBLogplatGameVersion selectByPrimaryKey(Integer version_id);

    int updateByPrimaryKeySelective(TBLogplatGameVersion record);

    int updateByPrimaryKey(TBLogplatGameVersion record);
}