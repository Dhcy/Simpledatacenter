package com.good.plat.datacenter.game.mapper;

import com.good.plat.datacenter.game.entity.TBLogplatGameChannel;

public interface TBLogplatGameChannelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBLogplatGameChannel record);

    int insertSelective(TBLogplatGameChannel record);

    TBLogplatGameChannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBLogplatGameChannel record);

    int updateByPrimaryKey(TBLogplatGameChannel record);
}