package com.good.plat.datacenter.game.service;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.game.entity.SubChannel;

/**
 * @ClassName: SubChannelService
 * @Description: 子渠道管理
 * @author dmw
 * @date 2016年1月19日 下午4:12:16
 */
public interface SubChannelService {

	/**
	 * @Title: findSubChannelList
	 * @Description: 获取列表
	 * @param subChannel
	 * @throws Exception
	 * List<SubChannel>
	 * @author dmw
	 * @date 2016年1月19日 下午4:14:08
	 */
	public List<SubChannel> findSubChannelList(SubChannel subChannel) throws Exception;

	
	/**
	 * @Title: findSubChannelById
	 * @Description: 查询
	 * @param id
	 * @throws Exception
	 * SubChannel
	 * @author dmw
	 * @date 2016年1月19日 下午4:15:26
	 */
	public SubChannel findSubChannelById(Integer id) throws Exception;
	
	
	/**
	 * @Title: findSubChannelBySubChannel
	 * @Description: 查询
	 * @param subChannel
	 * @throws Exception
	 * SubChannel
	 * @author dmw
	 * @date 2016年1月19日 下午4:29:43
	 */
	public SubChannel findSubChannelBySubChannel(SubChannel subChannel) throws Exception;

	
	/**
	 * @Title: addSubChannel
	 * @Description: 增加
	 * @param SubChannel
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年1月19日 上午10:24:33
	 */
	public BaseMessage addSubChannel(SubChannel subChannel) throws Exception;

	/**
	 * @Title: deleteSubChannel
	 * @Description: 删除
	 * @param id
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年1月19日 上午10:24:33
	 */
	public BaseMessage deleteSubChannel(Integer id) throws Exception;

	/**
	 * @Title: editSubChannel
	 * @Description: 修改
	 * @param SubChannel
	 * @throws Exception
	 * String
	 * @author dmw
	 * @date 2016年1月19日 上午10:24:33
	 */
	public BaseMessage editSubChannel(SubChannel subChannel) throws Exception;
}
