package com.good.plat.datacenter.datastat.mapper.players;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.players.NewPlayers;

/**
 * @ClassName: NewPlayersMapper
 * @Description: 新增玩家
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface NewPlayersMapper {
	
	/**
	 * @Title: selectNewPlayerList
	 * @Description: 新增激活和账户
	 * @param searchData
	 * @return
	 * List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午3:27:24
	 */
	List<NewPlayers> selectNewPlayerList (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportNewPlayerList
	 * @Description: 新增激活和账户导出
	 * @param searchData
	 * @return
	 * List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午3:30:16
	 */
	List<NewPlayers> exportNewPlayerList (BaseSearchData searchData);
	

	/**
	 * @Title: selectConversionList
	 * @Description: 玩家转化
	 * @param searchData
	 * @return
	 * List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午3:39:49
	 */
	List<NewPlayers> selectConversionList (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportConversionList
	 * @Description: 玩家转化导出
	 * @param searchData
	 * @return
	 * List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午3:40:03
	 */
	List<NewPlayers> exportConversionList (BaseSearchData searchData);

	
	/**
	 * @Title: selectNewPlayerOfCityList
	 * @Description: 地区新增玩家
	 * @param searchData
	 * @return
	 * List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午3:48:58
	 */
	List<NewPlayers> selectNewPlayerOfCityList (BaseSearchData searchData);


	/**
	 * @Title: exportNewPlayerOfCityList
	 * @Description: 地区新增玩家导出
	 * @param searchData
	 * @return
	 * List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午3:50:45
	 */
	List<NewPlayers> exportNewPlayerOfCityList (BaseSearchData searchData);


	/**
	 * @Title: selectNewPlayerOfChannelList
	 * @Description: 渠道新增玩家
	 * @param searchData
	 * @return
	 * List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午3:51:42
	 */
	List<NewPlayers> selectNewPlayerOfChannelList (BaseSearchData searchData);

	
	/**
	 * @Title: exportNewPlayerOfChannelList
	 * @Description: 渠道新增玩家导出
	 * @param searchData
	 * @return
	 * List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月15日 下午3:52:22
	 */
	List<NewPlayers> exportNewPlayerOfChannelList (BaseSearchData searchData);

	/**
	 * 单设备账号小号
	 * @Title: selectNewPlayerOfSubsidiaryList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月29日 下午8:55:37
	 */
	List<NewPlayers> selectNewPlayerOfSubsidiaryList (BaseSearchData searchData);
	/**
	 * 导出单设备账号小号
	 * @Title: exportNewPlayerOfSubsidiaryList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<NewPlayers>
	 * @author dmw
	 * @date 2016年3月29日 下午8:56:37
	 */
	List<NewPlayers> exportNewPlayerOfSubsidiaryList (BaseSearchData searchData);
	/**
	 * 新增玩家的区域列表
	 * @Title: selectNewPlayerCityList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<NewPlayers>
	 * @author hwj
	 * @date 2017-8-29 下午03:09:48
	 */
	List<NewPlayers> selectNewPlayerCityList (BaseSearchData searchData);
	/**
	 * 城市下的新增玩家
	 * @Title: selectNewPlayerListOfCity
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<NewPlayers>
	 * @author hwj
	 * @date 2017-8-29 下午05:48:37
	 */
	List<NewPlayers> selectNewPlayerListOfCity(BaseSearchData searchData);
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
	List<NewPlayers> selectNewPlayerListOfHour(BaseSearchData searchData);
}
