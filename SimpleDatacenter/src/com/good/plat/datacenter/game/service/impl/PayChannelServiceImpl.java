package com.good.plat.datacenter.game.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.redis.service.impl.RedisServiceImpl;
import com.good.plat.datacenter.game.entity.PayChannel;
import com.good.plat.datacenter.game.mapper.PayChannelMapper;
import com.good.plat.datacenter.game.service.PayChannelService;

@Service(value="payChannelService")
public class PayChannelServiceImpl implements PayChannelService{

	public PayChannelServiceImpl() {
	}
	
	@Autowired
	private RedisServiceImpl redisService;
	
	@Autowired
	private PayChannelMapper payChannelMapper;
	
	@Override
	public List<PayChannel> findPayChannelList() throws Exception {
		List<PayChannel> payChannelList = new ArrayList<PayChannel>();
		payChannelList = payChannelMapper.selectPayChannelList();
		/*if (redisService.getBytes("PAYCHANNEL_LIST") != null) {
			payChannelList = (List<PayChannel>) redisService
					.byteArrayToObject(redisService.getBytes("PAYCHANNEL_LIST"));
		} else {
			payChannelList = payChannelMapper.selectPayChannelList();
			if (payChannelList.size() > 0) {
				redisService.set("PAYCHANNEL_LIST".getBytes(), 
						redisService.objectToByteArray(payChannelList));
			}
		}*/
		
		return payChannelList;
	}

	@Override
	public PayChannel findPayChannelById(Integer id) throws Exception {
		
		return payChannelMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public PayChannel findPayChannelByChannelId(Integer channelId)
			throws Exception {
		
		return payChannelMapper.selectByChannelId(channelId);
	}

	@Override
	public BaseMessage addPayChannel(PayChannel payChannel) throws Exception {
		BaseMessage mess = new BaseMessage();
		PayChannel channel = payChannelMapper.selectByChannelId(payChannel.getChannelid());
		
		if (channel == null) {
			int status = payChannelMapper.insertSelective(payChannel);
			if (status == 1) {
				//添加成功的时候记得清空redis中数据
				//redisService.del("PAYCHANNEL_LIST");
				mess.setStatus(status);
				mess.setMessage("添加成功");
			} else {
				mess.setStatus(0);
				mess.setMessage("添加支付渠道失败");
			}
		} else {
			mess.setStatus(0);
			mess.setMessage("添加支付渠道失败:已有相同ID的支付渠道");
		}
		
		
		return mess;
	}

	@Override
	public BaseMessage deletePayChannel(Integer id) throws Exception {
		BaseMessage mess = new BaseMessage();
		int status = payChannelMapper.deleteByPrimaryKey(id);
		if (status == 1) {
			//删除成功的时候记得清空redis中数据
			//redisService.del("PAYCHANNEL_LIST");
			mess.setStatus(status);
			mess.setMessage("删除成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("删除支付渠道失败");
		}
		return mess;
	}

	@Override
	public String editPayChannel(PayChannel payChannel) throws Exception {
		return null;
	}

}
