package com.good.plat.datacenter.game.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.good.plat.datacenter.game.entity.PayChannel;

public interface PayChannelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PayChannel record);

    int insertSelective(PayChannel record);

    PayChannel selectByPrimaryKey(Integer id);
    
    PayChannel selectByChannelId(@Param("channelId") Integer channelId);

    int updateByPrimaryKeySelective(PayChannel record);

    int updateByPrimaryKey(PayChannel record);
    
    List<PayChannel> selectPayChannelList();
}