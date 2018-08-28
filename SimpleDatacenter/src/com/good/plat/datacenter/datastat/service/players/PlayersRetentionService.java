package com.good.plat.datacenter.datastat.service.players;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.players.PlayersRetention;

/**
 * @ClassName: PlayersRetentionMapper
 * @Description: 玩家留存
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface PlayersRetentionService {
	/**
	 * @Title: selectRetentionOfNewUser
	 * @Description: 新增账户留存
	 * @param searchData
	 * @return List<PlayersRetention>
	 * @author dmw
	 * @date 2016年3月15日 下午5:32:27
	 */
	List<PlayersRetention> selectRetentionOfNewUser(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportRetentionOfNewUser
	 * @Description: 新增账户留存导出
	 * @param searchData
	 * @return List<PlayersRetention>
	 * @author dmw
	 * @date 2016年3月15日 下午5:32:49
	 */
	List<PlayersRetention> exportRetentionOfNewUser(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectRetentionOfDevice
	 * @Description: 激活设备留存
	 * @param searchData
	 * @return List<PlayersRetention>
	 * @author dmw
	 * @date 2016年3月15日 下午5:33:14
	 */
	List<PlayersRetention> selectRetentionOfDevice(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportRetentionOfDevice
	 * @Description: 激活设备留存导出
	 * @param searchData
	 * @return List<PlayersRetention>
	 * @author dmw
	 * @date 2016年3月15日 下午5:33:29
	 */
	List<PlayersRetention> exportRetentionOfDevice(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectRetentionUserAnalys
	 * @Description: 留存用户分析
	 * @param searchData
	 * @return List<PlayersRetention>
	 * @author dmw
	 * @date 2016年3月15日 下午5:33:46
	 */
	List<PlayersRetention> selectRetentionUserAnalys(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportRetentionUserAnalys
	 * @Description: 留存用户分析导出
	 * @param searchData
	 * @return List<PlayersRetention>
	 * @author dmw
	 * @date 2016年3月15日 下午5:34:04
	 */
	List<PlayersRetention> exportRetentionUserAnalys(BaseSearchData searchData,
			HttpSession session) throws Exception;
	
	/**
	 * 
	 * @Title: selectRetentionOfActUser
	 * @Description: 活跃玩家留存
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<PlayersRetention>
	 * @author hwj
	 * @date 2017-1-22 下午04:58:01
	 */
	List<PlayersRetention> selectRetentionOfActUser(BaseSearchData searchData) throws Exception;
	
	/**
	 * 
	 * @Title: selectRetentionOfreturnUser
	 * @Description: 回流玩家留存
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<PlayersRetention>
	 * @author hwj
	 * @date 2017-1-22 下午04:58:56
	 */
	List<PlayersRetention> selectRetentionOfreturnUser(BaseSearchData searchData) throws Exception;
	
	/**
	 * 新增账号留存（首次创建角色数）
	 * @Title: selectRetentionOfNewAccount
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<PlayersRetention>
	 * @author hwj
	 * @date 2017-4-26 下午06:21:47
	 */
	List<PlayersRetention> selectRetentionOfNewAccount(BaseSearchData searchData) throws Exception;
	
	/**
	 * 玩家留存区域列表
	 * @Title: selectUserRetainCityList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<PlayersRetention>
	 * @author hwj
	 * @date 2017-11-10 下午03:17:23
	 */
	List<PlayersRetention> selectUserRetainCityList(BaseSearchData searchData) throws Exception;

}
