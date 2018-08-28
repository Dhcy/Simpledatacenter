package com.good.plat.datacenter.game.mapper;

import java.util.List;

import com.good.plat.datacenter.game.entity.SubChannel;

public interface SubChannelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SubChannel record);

    int insertSelective(SubChannel record);

    SubChannel selectByPrimaryKey(Integer id);
    
    SubChannel selectBySubChannel(SubChannel subChannel);

    int updateByPrimaryKeySelective(SubChannel record);

    int updateByPrimaryKey(SubChannel record);
    
    /**
     * @Title: subChannelList
     * @Description: 查询SubChannel列表
     * @param channel
     * List<SubChannel>
     * @author dmw
     * @date 2016年1月19日 下午2:32:28
     */
    List<SubChannel> selectSubChannelList(SubChannel subChannel);
    
}