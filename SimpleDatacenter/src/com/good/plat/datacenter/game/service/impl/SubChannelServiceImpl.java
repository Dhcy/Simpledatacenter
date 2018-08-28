package com.good.plat.datacenter.game.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.game.entity.Channel;
import com.good.plat.datacenter.game.entity.GameChannel;
import com.good.plat.datacenter.game.entity.SubChannel;
import com.good.plat.datacenter.game.mapper.ChannelMapper;
import com.good.plat.datacenter.game.mapper.SubChannelMapper;
import com.good.plat.datacenter.game.service.SubChannelService;

@Service(value="subChannelService")
public class SubChannelServiceImpl implements SubChannelService {

	public SubChannelServiceImpl() {
	}
	
	@Autowired 
	private SubChannelMapper subChannelMapper;
	@Autowired
	private ChannelMapper channelMapper;

	@Override
	public List<SubChannel> findSubChannelList(SubChannel subChannel)
			throws Exception {
		List<SubChannel> list=subChannelMapper.selectSubChannelList(subChannel);
		if(list!=null&&!list.isEmpty()){
			for(SubChannel sub:list){
				sub.setSubId(String.valueOf(sub.getChannelId())+sub.getSubchannelId());
			}
		}
		return list;
	}
	

	@Override
	public SubChannel findSubChannelById(Integer id) throws Exception {
		
		return subChannelMapper.selectByPrimaryKey(id);
	}

	@Override
	public BaseMessage addSubChannel(SubChannel subChannel) throws Exception {
		BaseMessage mess = new BaseMessage();
		
		String subchannelid = subChannel.getSubchannelId();
		int channelid = subChannel.getChannelId();
		String subId = String.valueOf(channelid)+subchannelid;
		subChannel.setSubId(subId);
		//bind game and channel automatically
		GameChannel gameChannel = new GameChannel();
		gameChannel.setGameid(subChannel.getGameid());
		
		List<Channel> channelist = new LinkedList<Channel>();
		Channel channel = new Channel();
		channel.setId(channelid);
		channelist.add(channel );
		gameChannel.setChannelList(channelist );
		List<GameChannel> gclist = channelMapper.selectGameChannelPair(gameChannel);//size = 0
		if(gclist == null || gclist.size() == 0 
				|| (gclist.size() != 0 
					&& (gclist.get(0).getChannelList() == null || gclist.get(0).getChannelList().size() == 0))){
			channelMapper.insertGameChannel(gameChannel);
		}
		//
		SubChannel sub = subChannelMapper.selectBySubChannel(subChannel);
		
		if (sub == null) {
			int status = subChannelMapper.insertSelective(subChannel);
			//game_channel
			if (status == 1) {
				mess.setStatus(status);
				mess.setMessage("添加成功");
			} else {
				mess.setStatus(0);
				mess.setMessage("添加子包失败");
			}
		} else {
			mess.setStatus(0);
			mess.setMessage("添加子包失败:在此游戏下已有相同ID的子包");
		}
		return mess;
	}

	@Override
	public BaseMessage deleteSubChannel(Integer id) throws Exception {
		BaseMessage mess = new BaseMessage();
		int status = subChannelMapper.deleteByPrimaryKey(id);
		if (status == 1) {
			//删除成功的时候记得清空redis中数据
			mess.setStatus(status);
			mess.setMessage("删除成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("删除子包失败");
		}
		return mess;
	}

	@Override
	public BaseMessage editSubChannel(SubChannel subChannel) throws Exception {
		BaseMessage mess = new BaseMessage();
		int channelId=subChannel.getChannelId();
		String subChannelId=subChannel.getSubchannelId();
		subChannel.setSubId(String.valueOf(channelId)+subChannelId);
		SubChannel sub = subChannelMapper.selectBySubChannel(subChannel);
		if(sub==null){
			int status = subChannelMapper.updateByPrimaryKeySelective(subChannel);
			if (status != 0) {
				mess.setStatus(status);
				mess.setMessage("修改成功");
			} else {
				mess.setStatus(0);
				mess.setMessage("修改失败");
			}
		}else{
			mess.setStatus(0);
			mess.setMessage("修改子包失败:在此游戏下已有相同ID的子包");
		}
		return mess;
	}

	@Override
	public SubChannel findSubChannelBySubChannel(SubChannel subChannel)
			throws Exception {
		return subChannelMapper.selectBySubChannel(subChannel);
	}

}
