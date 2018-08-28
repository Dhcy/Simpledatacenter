package com.good.plat.datacenter.datastat.service.players;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.players.PlayersChurn;

/**
 * @ClassName: PlayersChurnMapper
 * @Description: 玩家流失
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface PlayersChurnService {
	/**
	 * @Title: selectPerDayLostNumAndRate
	 * @Description: 每日流失
	 * @param searchData
	 * @return List<PlayersChurn>
	 * @author dmw
	 * @date 2016年3月15日 下午9:32:17
	 */
	List<PlayersChurn> selectPerDayLostNumAndRate(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportPerDayLostNumAndRate
	 * @Description: 每日流失导出
	 * @param searchData
	 * @return List<PlayersChurn>
	 * @author dmw
	 * @date 2016年3月15日 下午9:32:37
	 */
	List<PlayersChurn> exportPerDayLostNumAndRate(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectPerDayReturnNumList
	 * @Description: 每日回流
	 * @param searchData
	 * @return List<PlayersChurn>
	 * @author dmw
	 * @date 2016年3月15日 下午9:33:35
	 */
	List<PlayersChurn> selectPerDayReturnNumList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportPerDayReturnNumList
	 * @Description: 每日回流导出
	 * @param searchData
	 * @return List<PlayersChurn>
	 * @author dmw
	 * @date 2016年3月15日 下午9:33:56
	 */
	List<PlayersChurn> exportPerDayReturnNumList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectLostUserAnalysNumList
	 * @Description: 流失用户分析
	 * @param searchData
	 * @return List<PlayersChurn>
	 * @author dmw
	 * @date 2016年3月15日 下午9:36:18
	 */
	List<PlayersChurn> selectLostUserAnalysNumList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: exportLostUserAnalysNumList
	 * @Description: 流失用户分析导出
	 * @param searchData
	 * @return List<PlayersChurn>
	 * @author dmw
	 * @date 2016年3月15日 下午9:36:41
	 */
	List<PlayersChurn> exportLostUserAnalysNumList(BaseSearchData searchData,
			HttpSession session) throws Exception;

}
