package com.good.plat.datacenter.game.mapper;

import java.util.List;

import com.good.plat.datacenter.game.entity.RechgGainWarn;

/**
 * 充值获取警告
 * @ClassName: RechgGainWarnMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-8-15 上午11:24:08
 */
public interface RechgGainWarnMapper {
	/**
	 * 查询充值获取警告列表
	 * @Title: rechgGainWarnList
	 * @Description: TODO
	 * @param warn
	 * @return
	 * List<RechgGainWarn>
	 * @author hwj
	 * @date 2017-8-15 上午11:27:08
	 */
	List<RechgGainWarn> selectRechgGainWarnList(RechgGainWarn warn) throws Exception; 
	/**
	 * 添加充值获取警告
	 * @Title: insertRechgGainWarn
	 * @Description: TODO
	 * @param warn
	 * @return
	 * @throws Exception
	 * int
	 * @author hwj
	 * @date 2017-8-15 上午11:31:37
	 */
	int insertRechgGainWarn(RechgGainWarn warn) throws Exception;
	/**
	 * 根据id删除充值预警
	 * @Title: deleteByPrimary
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * int
	 * @author hwj
	 * @date 2017-8-15 上午11:33:47
	 */
	int deleteByPrimaryKey(Integer id) throws Exception;
	/**
	 * 根据id修改 充值预警
	 * @Title: updateByPrimary
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * int
	 * @author hwj
	 * @date 2017-8-15 上午11:34:51
	 */
	int updateByPrimaryKey(RechgGainWarn warn) throws Exception;
	/**
	 * 通过id查询充值警告
	 * @Title: selectByPrimaryKey
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * RechgGainWarn
	 * @author hwj
	 * @date 2017-8-15 下午05:45:07
	 */
	RechgGainWarn selectByPrimaryKey(Integer id) throws Exception;
	/**
	 * 根据游戏id查找充值警告
	 * @Title: selectByGameId
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * RechgGainWarn
	 * @author hwj
	 * @date 2017-8-17 下午07:23:06
	 */
	RechgGainWarn selectByGameId(Integer id) throws Exception;
}
