package com.good.plat.datacenter.game.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.good.plat.datacenter.game.entity.Channel;
import com.good.plat.datacenter.game.entity.GameChannel;
import com.good.plat.datacenter.game.entity.GameChannelDetail;
import com.good.plat.datacenter.game.entity.SubChannel;
import com.good.plat.datacenter.game.entity.TBLogplatGameChannel;

public interface ChannelMapper {
	int deleteByPrimaryKey(Integer id);

    int insert(Channel record);

    int insertSelective(Channel record);

    /**
     * @Title: selectByPrimaryKey
     * @Description: 用主键ID查询Channel
     * @param id
     * Channel
     * @author dmw
     * @date 2016年1月19日 上午9:25:41
     */
    Channel selectByPrimaryKey(Integer id);
    
    /**
     * @Title: selectByChannelId
     * @Description: 用ChannelId查询Channel
     * @param ChannelId
     * Channel
     * @author dmw
     * @date 2016年1月19日 上午9:26:05
     */
    Channel selectByChannelId(Integer id);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);
    
    /**
     * @Title: selectChannelList
     * @Description: 获取公司列表,不关联查询子渠道
     * List<Channel>
     * @author dmw
     * @date 2016年1月19日 上午9:26:36
     */
    List<Channel> selectChannelList();
    
    /**
     * @Title: getSelectChannels
     * @Description: 获取筛选框channel数据
     * @param channel
     * @return
     * List<Channel>
     * @author dmw
     * @date 2016年3月18日 下午3:10:02
     */
    List<Channel> getSelectChannels(Channel channel);
    
    /**
     * @Title: selectChannelSubChannelList
     * @Description: 获取公司列表,关联查询子渠道(channelId为-1或则null时查询全部)
     * @param channelId
     * List<Channel>
     * @author dmw
     * @date 2016年1月19日 上午9:28:12
     */
    List<Channel> selectChannelSubChannelByChannelId(@Param("channelId")Integer channelId);
    
    
    /**
     * @Title: selectChannelSubChannelByChannel
     * @Description: 根据条件获取筛选列表
     * @param channel
     * @return
     * List<Channel>
     * @author dmw
     * @date 2016年1月19日 下午2:43:49
     */
    List<Channel> selectChannelSubChannelByChannel(Channel channel);
    
    
    /**
     * @Title: selectSubIdListByChannels
     * @Description: 获取subIds
     * @param channelList
     * List<String>
     * @author dmw
     * @date 2016年1月19日 下午3:30:06
     */
    List<String> selectSubIdListByChannels(@Param("channelList") List<Integer> channelList);
    
    //获取用户渠道权限
    List<Channel> selectChannelByUserid(@Param("userid")Integer userid);
    
    //获取GameChannel回显信息
    GameChannel selectGameChannelUI(GameChannel gameChannel);
    
    //删除tb_logplat_game_channel表数据
    int deleteGameChannel(GameChannel gameChannel);
    
    //删除子渠道表数据
    int deleteSubChannel(GameChannel gameChannel);
    
    int insertGameChannel(GameChannel gameChannel);
    
    int insertSubChannel(@Param("subList")List<SubChannel> subList);

	List<GameChannel> selectGameChannelPair(GameChannel gameChannel);
	
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
	    List<Channel> selectGameChannelList(Channel channel);
    
}