package com.good.plat.datacenter.game.mapper;

import java.util.List;

import com.good.plat.datacenter.game.entity.GameChannels;
import com.good.plat.datacenter.game.entity.TBLogplatGameChannel;

public interface TBLogplatGameChannelMapper2 {
    List<TBLogplatGameChannel> selectGameChannel(TBLogplatGameChannel record);

	List<GameChannels> selectGameChannels(TBLogplatGameChannel gameChannel);
}