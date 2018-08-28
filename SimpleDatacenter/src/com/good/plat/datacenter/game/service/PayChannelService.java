package com.good.plat.datacenter.game.service;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.game.entity.PayChannel;


public interface PayChannelService {

	public List<PayChannel> findPayChannelList() throws Exception;


	public PayChannel findPayChannelById(Integer id) throws Exception;
	
	public PayChannel findPayChannelByChannelId(Integer channelId) throws Exception;

	public BaseMessage addPayChannel(PayChannel payChannel) throws Exception;

	
	public BaseMessage deletePayChannel(Integer id) throws Exception;

	
	public String editPayChannel(PayChannel payChannel) throws Exception;

}
