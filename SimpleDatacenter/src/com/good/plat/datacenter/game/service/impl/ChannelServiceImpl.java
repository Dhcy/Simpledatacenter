package com.good.plat.datacenter.game.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.redis.service.impl.RedisServiceImpl;
import com.good.plat.datacenter.game.entity.Channel;
import com.good.plat.datacenter.game.entity.GameChannel;
import com.good.plat.datacenter.game.entity.GameChannelDetail;
import com.good.plat.datacenter.game.entity.GameChannels;
import com.good.plat.datacenter.game.entity.SubChannel;
import com.good.plat.datacenter.game.entity.TBLogplatGameChannel;
import com.good.plat.datacenter.game.mapper.ChannelMapper;
import com.good.plat.datacenter.game.mapper.TBLogplatGameChannelMapper;
import com.good.plat.datacenter.game.mapper.TBLogplatGameChannelMapper2;
import com.good.plat.datacenter.game.service.ChannelService;

@Service(value="channelService")
public class ChannelServiceImpl implements ChannelService {
	
	@Autowired
	private RedisServiceImpl redisService;
	
	@Autowired
	private ChannelMapper channelMapper;
	
	@Autowired
	private TBLogplatGameChannelMapper tBLogplatGameChannelMapper;
	
	@Autowired
	private TBLogplatGameChannelMapper2 tBLogplatGameChannelMapper2;
	
	public ChannelServiceImpl() {
	}
	
	
	@Override
	public List<Channel> getSelectChannels(HttpSession session, 
			Channel channel) throws Exception {
		
		if (session.getAttribute("USERID") == null) {
			return null;
		} else {
			
			Integer userid = (Integer) session.getAttribute("USERID");
			String userName = (String) session.getAttribute("USERNAME");
			
			channel.setUserid(userid);
			channel.setUserName(userName);
			List<Channel> list=channelMapper.getSelectChannels(channel);
			if(list!=null&&!list.isEmpty()){
				for(Channel chn:list){
					List<SubChannel> subChannelList=chn.getSubChannelList();
					if(subChannelList!=null){
						for(SubChannel subchannel:subChannelList){
							//subId=channelId+subChannelId
							subchannel.setSubId(String.valueOf(subchannel.getChannelId())+subchannel.getSubchannelId());
						}
					}
					chn.setSubChannelList(subChannelList);
				}
			}
			return list;
		}
		
	}
	

	@Override
	public List<Channel> findChannelList() throws Exception {
		List<Channel> channelList = new ArrayList<Channel>();
		channelList = channelMapper.selectChannelList();
		/*if (redisService.getBytes("CHANNEL_LIST") != null) {
			channelList = (List<Channel>) redisService
					.byteArrayToObject(redisService.getBytes("CHANNEL_LIST"));
		} else {
			channelList = channelMapper.selectChannelList();
			if (channelList.size() > 0) {
				redisService.set("CHANNEL_LIST".getBytes(), 
						redisService.objectToByteArray(channelList));
			}
		}*/
		
		return channelList;
	}

	@Override
	public List<Channel> findChannelSubChannelListByChannelId(Integer channelId) throws Exception {
		List<Channel> channelList = new ArrayList<Channel>();
		channelList = channelMapper.selectChannelSubChannelByChannelId(channelId);
		/*if (redisService.getBytes("CHANNEL_SUBCHANNEL_LIST") != null) {
			channelList = (List<Channel>) redisService
					.byteArrayToObject(redisService.getBytes("CHANNEL_SUBCHANNEL_LIST"));
		} else {
			channelList = channelMapper.selectChannelSubChannelByChannelId(channelId);
			if (channelList.size() > 0) {
				redisService.set("CHANNEL_SUBCHANNEL_LIST".getBytes(), 
						redisService.objectToByteArray(channelList));
			}
		}*/
		
		return channelList;
	}
	
	
	@Override
	public List<Channel> findChannelSubChannelListByChannel(Channel channel)
			throws Exception {
		List<Channel> channelList = new ArrayList<Channel>();
		channelList = channelMapper.selectChannelSubChannelByChannel(channel);
		
		return channelList;
	}
	

	@Override
	public Channel findChannelById(Integer id) throws Exception {
		
		return channelMapper.selectByPrimaryKey(id);
	}

	@Override
	public Channel findChannelByChannelId(Integer channelId) throws Exception {
		return channelMapper.selectByChannelId(channelId);
	}

	@Override
	public BaseMessage addChannel(Channel channel) throws Exception {
		BaseMessage mess = new BaseMessage();
		
		Channel com = channelMapper.selectByChannelId(channel.getId());
		
		if (com == null) {
			int status = channelMapper.insertSelective(channel);
			if (status == 1) {
				//添加成功的时候记得清空redis中数据
				//redisService.del("CHANNEL_LIST","CHANNEL_SUBCHANNEL_LIST");
				mess.setStatus(status);
				mess.setMessage("添加成功");
			} else {
				mess.setStatus(0);
				mess.setMessage("添加失败");
			}
		} else {
			mess.setStatus(0);
			mess.setMessage("添加失败:已有相同ID的公司");
		}
		return mess;
	}

	@Override
	public BaseMessage deleteChannel(Integer id) throws Exception {
		BaseMessage mess = new BaseMessage();
		int status = channelMapper.deleteByPrimaryKey(id);
		if (status == 1) {
			//删除成功的时候记得清空redis中数据
			//redisService.del("CHANNEL_LIST","CHANNEL_SUBCHANNEL_LIST");
			mess.setStatus(status);
			mess.setMessage("删除成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("删除失败");
		}
		return mess;
	}

	@Override
	public BaseMessage editChannel(Channel channel) throws Exception {
		BaseMessage mess = new BaseMessage();
		int status = channelMapper.updateByPrimaryKeySelective(channel);
		if (status != 0) {
			//添加成功的时候记得清空redis中数据
			//redisService.del("CHANNEL_LIST","CHANNEL_SUBCHANNEL_LIST");
			mess.setStatus(status);
			mess.setMessage("修改成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("修改失败");
		}
		return mess;
	}


	@Override
	public List<String> findSubChannelIdListByChannels(List<Integer> channelIds) {
		
		List<String> resultList =  channelMapper.selectSubIdListByChannels(channelIds);
		
		return resultList;
	}
	
	@Override
	public GameChannel gameChannelUI(GameChannel gameChannel,
			HttpSession session) throws Exception {
		int gameid = gameChannel.getGameid();
		
		GameChannel result = new GameChannel();
		result.setGameid(gameid);
		
		Channel cha = new Channel();
		cha.setGameid(gameid);
		
		List<Channel> channelList  = channelMapper.selectChannelSubChannelByChannel(cha);
		
		if (channelList != null) {
			for (Channel channel : channelList) {
				List<SubChannel> subList = channel.getSubChannelList();
				String[] subids = new String[subList.size()];
				for (int i = 0; i < subList.size(); i++) {
					subids[i] = subList.get(i).getSubchannelId();
				}
				channel.setSubChannels(subids);
			}
		}
		result.setChannelList(channelList);
		
		return result;
	}


	@Override
	public BaseMessage gameChannelManage(GameChannel gameChannel) throws Exception {
		BaseMessage mess = new BaseMessage();
		
		List<Channel> channelList = gameChannel.getChannelList();
		List<SubChannel> subList = new ArrayList<SubChannel>();
		
		if (channelList != null && channelList.size() > 0) {
			for (Channel channel : channelList) {
				String[] subChannels = channel.getSubChannels();
				String[] subIDs = new String[subChannels.length];
				if (subChannels.length > 0) {
					for (int i = 0; i < subChannels.length; i++) {
						subIDs[i] = channel.getId()+subChannels[i];
					}
				}
				channel.setSubIDs(subIDs);
			}
			//gameChannel.setChannelList(channelList);
			for (Channel channel : channelList) {
				if (channel.getSubChannels() != null) {
					for (int i = 0; i < channel.getSubChannels().length; i++) {
						SubChannel sub = new SubChannel();
						sub.setGameid(gameChannel.getGameid());
						sub.setChannelId(channel.getId());
						sub.setSubchannelId(channel.getSubChannels()[i]);
						sub.setSubId(channel.getSubIDs()[i]);
						
						subList.add(sub);
					}
					
				}
				
			}
			
		}
		
		try {
			channelMapper.deleteGameChannel(gameChannel);
			channelMapper.deleteSubChannel(gameChannel);
		} catch(Exception e) {
			mess.setStatus(0);
			mess.setMessage("操作失败");
			return mess;
		}
		
		int status1 = channelMapper.insertGameChannel(gameChannel);
		int status2 = channelMapper.insertSubChannel(subList);
		
		if (status1 > 0 && status2 > 0) {
			mess.setStatus(1);
			mess.setMessage("操作成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("操作失败");
		}
		return mess;
	}

	@Override
	public List<TBLogplatGameChannel> selectGameChannel(TBLogplatGameChannel gameChannel) {
		return tBLogplatGameChannelMapper2.selectGameChannel(gameChannel);
	}

	@Override
	public BaseMessage updateGameChannel(TBLogplatGameChannel gameChannel) {
		BaseMessage bm = new BaseMessage();
		TBLogplatGameChannel gameChannel1 = tBLogplatGameChannelMapper.selectByPrimaryKey(gameChannel.getId());
		if(gameChannel1 == null){
			bm.setStatus(0);
			bm.setMessage("NOT FOUND");
		}else{
			tBLogplatGameChannelMapper.updateByPrimaryKeySelective(gameChannel);
			bm.setStatus(1);
		}
		return bm;
	}

	@Override
	public List<GameChannels> selectGameChannels(TBLogplatGameChannel gameChannel) {
		return tBLogplatGameChannelMapper2.selectGameChannels(gameChannel);
	}

	@Override
	public TBLogplatGameChannel selectGameChannelById(int gameChannelID) {
		TBLogplatGameChannel gameChamme = tBLogplatGameChannelMapper.selectByPrimaryKey(gameChannelID);
		return gameChamme;
	}

	@Override
	public List<GameChannelDetail> selectGameChannelSubchannelRelationshipDetail(TBLogplatGameChannel gameChannel) {
		List<GameChannelDetail> list = channelMapper.selectGameChannelSubchannelRelationshipDetail(gameChannel);
		return list;
	}


	@Override
	public List<Channel> selectGameChannelList(HttpSession session, Channel channel) {
		// TODO Auto-generated method stub
		if (session.getAttribute("USERID") == null) {
			return new ArrayList<Channel>();
		} else {
			Integer userid = (Integer) session.getAttribute("USERID");
			String userName = (String) session.getAttribute("USERNAME");
			
			channel.setUserid(userid);
			channel.setUserName(userName);
			
			return channelMapper.selectGameChannelList(channel);
		}
	}


}
