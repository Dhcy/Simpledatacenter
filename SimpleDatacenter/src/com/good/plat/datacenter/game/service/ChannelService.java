package com.good.plat.datacenter.game.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.game.entity.Channel;
import com.good.plat.datacenter.game.entity.GameChannel;
import com.good.plat.datacenter.game.entity.GameChannelDetail;
import com.good.plat.datacenter.game.entity.GameChannels;
import com.good.plat.datacenter.game.entity.TBLogplatGameChannel;
import com.good.plat.datacenter.game.mapper.TBLogplatGameChannelMapper;

/**
 * @ClassName: ChannelService
 * @Description: 渠道管理操作
 * @author dmw
 * @date 2016年1月19日 上午10:22:09
 */
public interface ChannelService {
	/**
	 * @Title: findChannelList
	 * @Description: 查询列表
	 * @throws Exception
	 * List<Channel>
	 * @author dmw
	 * @date 2016年1月19日 上午10:22:09
	 */
	public List<Channel> findChannelList() throws Exception;
	
	
	/**
	 * @Title: getSelectChannels
	 * @Description: 获取筛选框渠道列表
	 * @param channel
	 * @param session
	 * @throws Exception
	 * List<Channel>
	 * @author dmw
	 * @date 2016年3月18日 下午2:58:10
	 */
	List<Channel> getSelectChannels(HttpSession session, Channel channel) throws Exception;
	
	
	/**
	 * @Title: findChannelSubChannelList
	 * @Description: 获取公司列表,关联查询子渠道(channelId为-1或则null时查询全部)
	 * @throws Exception
	 * List<Channel>
	 * @author dmw
	 * @date 2016年1月19日 上午10:22:09
	 */
	public List<Channel> findChannelSubChannelListByChannelId(Integer channelId) throws Exception;
	
	/**
	 * @Title: findChannelSubChannelListByChannel
	 * @Description: 根据条件获取筛选列表
	 * @param channel
	 * @throws Exception
	 * List<Channel>
	 * @author dmw
	 * @date 2016年1月19日 下午3:07:43
	 */
	public List<Channel> findChannelSubChannelListByChannel(Channel channel) throws Exception;

	
	/**
	 * @Title: findChannelById
	 * @Description: 查询
	 * @param id
	 * @throws Exception
	 * Channel
	 * @author dmw
	 * @date 2016年1月19日 上午10:22:09
	 */
	public Channel findChannelById(Integer id) throws Exception;
	
	
	/**
	 * @Title: findChannelByChannelId
	 * @Description: 查询
	 * @param channelId
	 * @throws Exception
	 * Channel
	 * @author dmw
	 * @date 2016年1月19日 上午10:24:33
	 */
	public Channel findChannelByChannelId(Integer channelId) throws Exception;
	
	
	/**
	 * @Title: addChannel
	 * @Description: 增加
	 * @param channel
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年1月19日 上午10:24:33
	 */
	public BaseMessage addChannel(Channel channel) throws Exception;
	
	
	/**
	 * @Title: deleteChannel
	 * @Description: 删除
	 * @param id
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年1月19日 上午10:24:33
	 */
	public BaseMessage deleteChannel(Integer id) throws Exception;
	
	
	/**
	 * @Title: editChannel
	 * @Description: 修改
	 * @param channel
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年1月19日 上午10:24:33
	 */
	public BaseMessage editChannel(Channel channel) throws Exception;
	
	/**
	 * @Title: findSubChannelIdListByChannels
	 * @Description: 获取subids
	 * @param ChannelIds
	 * List<String>
	 * @author dmw
	 * @date 2016年1月19日 下午4:10:13
	 */
	public List<String> findSubChannelIdListByChannels(List<Integer> ChannelIds) throws Exception;
	
	
	/**
	 * @Title: gameChannelUI
	 * @Description: 获取回选信息
	 * @param gameChannel
	 * @param session
	 * @throws Exception
	 * GameChannel
	 * @author dmw
	 * @date 2016年3月11日 上午10:14:06
	 */
	public GameChannel gameChannelUI(GameChannel gameChannel,HttpSession session) throws Exception;
	
	
	/**
	 * @Title: gameChannelManage
	 * @Description: 游戏渠道对应表操作
	 * @param gameChannel
	 * @return
	 * BaseMessage
	 * @author dmw
	 * @date 2016年3月9日 下午6:26:54
	 */
	public BaseMessage gameChannelManage(GameChannel gameChannel)  throws Exception;

	/**
	 * 
	 * @Title: selectGameChannel
	 * @Description: 
	 * @param gameChannel
	 * @return
	 * TBLogplatGameChannel
	 * @author moxw
	 * @date 2016年6月14日 上午10:00:41
	 */
	List<TBLogplatGameChannel> selectGameChannel(TBLogplatGameChannel gameChannel);


	/**
	 * 
	 * @Title: updateGameChannel
	 * @Description: TODO
	 * @param gameChannel
	 * @return
	 * BaseMessage
	 * @author moxw
	 * @date 2016年6月14日 上午10:06:39
	 */
	BaseMessage updateGameChannel(TBLogplatGameChannel gameChannel);


	/**
	 * 
	 * @Title: selectGameChannels
	 * @Description: TODO
	 * @param gameChannel
	 * @return
	 * List<GameChannels>
	 * @author moxw
	 * @date 2016年6月14日 上午10:14:42
	 */
	List<GameChannels> selectGameChannels(TBLogplatGameChannel gameChannel);


	TBLogplatGameChannel selectGameChannelById(int gameChannelID);

	/**
	 * 查询游戏下相关渠道和子渠道以及游戏渠道关联详细信息(如接入参数)
	 * @Title: selectGameChannelSubchannelRelationshipDetail
	 * @Description: TODO
	 * @param gameChannel
	 * @return
	 * List<GameChannelDetail>
	 * @author moxw
	 * @date 2016年8月10日 下午3:04:08
	 */
	List<GameChannelDetail> selectGameChannelSubchannelRelationshipDetail(TBLogplatGameChannel gameChannel);
	
 	/**
	    * 游戏下渠道列表(带权限)
	    * @Title: getGameChannelList
	    * @Description: TODO
	    * @param channel
	    * @return
	    * List<Channel>
	    * @author hwj
	    * @date 2017-6-8 上午10:46:26
	    */
	    List<Channel> selectGameChannelList(HttpSession session,Channel channel) throws Exception;
	
}
