package com.good.plat.datacenter.datastat.service.players;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.players.NewPlayers;

/**
 * @ClassName: NewPlayersMapper
 * @Description: 新增玩家
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface NewPlayersService {

	/**
	 * @Title: selectNewPlayerList
	 * @Description: 新增激活和账户
	 * @param searchData
	 * @return List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午3:27:24
	 */
	List<NewPlayers> selectNewPlayerList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportNewPlayerList
	 * @Description: 新增激活和账户导出
	 * @param searchData
	 * @return List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午3:30:16
	 */
	List<NewPlayers> exportNewPlayerList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectConversionList
	 * @Description: 玩家转化
	 * @param searchData
	 * @return List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午3:39:49
	 */
	List<NewPlayers> selectConversionList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportConversionList
	 * @Description: 玩家转化导出
	 * @param searchData
	 * @return List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午3:40:03
	 */
	List<NewPlayers> exportConversionList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectNewPlayerOfCityList
	 * @Description: 地区新增玩家
	 * @param searchData
	 * @return List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午3:48:58
	 */
	List<NewPlayers> selectNewPlayerOfCityList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportNewPlayerOfCityList
	 * @Description: 地区新增玩家导出
	 * @param searchData
	 * @return List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午3:50:45
	 */
	List<NewPlayers> exportNewPlayerOfCityList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectNewPlayerOfChannelList
	 * @Description: 渠道新增玩家
	 * @param searchData
	 * @return List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午3:51:42
	 */
	List<NewPlayers> selectNewPlayerOfChannelList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportNewPlayerOfChannelList
	 * @Description: 渠道新增玩家导出
	 * @param searchData
	 * @return List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午3:52:22
	 */
	List<NewPlayers> exportNewPlayerOfChannelList(BaseSearchData searchData,
			HttpSession session) throws Exception;
	
	/**
	 * 单设备账号小号
	 * @Title: selectNewPlayerOfSubsidiaryList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月29日 下午8:53:38
	 */
	List<NewPlayers> selectNewPlayerOfSubsidiaryList(BaseSearchData searchData,
			HttpSession session) throws Exception;
	/**
	 * 导出单设备账号小号
	 * @Title: exportNewPlayerOfSubsidiaryList
	 * @Description: TODO
	 * @param searchData
	 * @param session
	 * @return
	 * @throws Exception
	 * List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月29日 下午8:57:27
	 */
	List<NewPlayers> exportNewPlayerOfSubsidiaryList(BaseSearchData searchData,
			HttpSession session) throws Exception;
	/**
	 * 新增玩家区域列表
	 * @Title: selectNewPlayerCityList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<NewPlayers>
	 * @author hwj
	 * @date 2017-8-29 下午03:13:57
	 */
	List<NewPlayers> selectNewPlayerCityList (BaseSearchData searchData)throws Exception;
	/**
	 * 城市下新增玩家
	 * @Title: selectNewPlayerListOfCity
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<NewPlayers>
	 * @author hwj
	 * @date 2017-8-29 下午05:49:18
	 */
	List<NewPlayers> selectNewPlayerListOfCity(BaseSearchData searchData) throws Exception;
	/**
	 *  新增玩家(按小时)
	 * @Title: selectNewPlayerOfHourList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<NewPlayers>
	 * @author hwj
	 * @date 2017-11-28 下午05:12:43
	 */
	List<NewPlayers> selectNewPlayerListOfHour(BaseSearchData searchData)throws Exception;
}
