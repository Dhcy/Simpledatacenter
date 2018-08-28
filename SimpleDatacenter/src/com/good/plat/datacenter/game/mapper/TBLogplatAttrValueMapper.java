package com.good.plat.datacenter.game.mapper;

import com.good.plat.datacenter.game.entity.TBLogplatAttrValue;

public interface TBLogplatAttrValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBLogplatAttrValue record);

    int insertSelective(TBLogplatAttrValue record);

    TBLogplatAttrValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBLogplatAttrValue record);

    int updateByPrimaryKey(TBLogplatAttrValue record);
}