package com.good.plat.datacenter.game.service;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.game.entity.RechgGainWarn;

/**
 * 充值获取警告业务
 * @ClassName: RechgGainWarnService
 * @Description: TODO
 * @author hwj
 * @date 2017-8-15 下午12:36:16
 */
public interface RechgGainWarnService {
	/**
	 * 查找充值获取警告列表
	 * @Title: selectRechgGainWarnList
	 * @Description: TODO
	 * @param warn
	 * @return
	 * @throws Exception
	 * List<RechgGainWarn>
	 * @author hwj
	 * @date 2017-8-15 下午12:38:02
	 */
	List<RechgGainWarn> selectRechgGainWarnList(RechgGainWarn warn) throws Exception;
	
	/**
	 * 添加充值获取警告
	 * @Title: addRechgGainWarn
	 * @Description: TODO
	 * @param warn
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2017-8-15 下午12:39:35
	 */
	BaseMessage addRechgGainWarn(RechgGainWarn warn) throws Exception;
	
	/**
	 * 修改充值获取警告
	 * @Title: editRechgGainWarn
	 * @Description: TODO
	 * @param warn
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2017-8-15 下午12:42:44
	 */
	BaseMessage editRechgGainWarn(RechgGainWarn warn) throws Exception;
	/**
	 * 删除充值获取警告
	 * @Title: deleteRechgGainWarn
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2017-8-15 下午12:44:31
	 */
	BaseMessage deleteRechgGainWarn(Integer id) throws Exception;
	/**
	 * 获取充值警告
	 * @Title: getRechgGainWarn
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * RechgGainWarn
	 * @author hwj
	 * @date 2017-8-15 下午05:39:00
	 */
	RechgGainWarn getRechgGainWarn(Integer id)throws Exception;
	
	RechgGainWarn getRechgGainWarnByGameId(Integer id)throws Exception;
	

}
