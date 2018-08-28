package com.good.plat.datacenter.datastat.service.players;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.players.AccountChurn;

/**
 * 账号流失
 * @ClassName: AccountChurnService
 * @Description: TODO
 * @author hwj
 * @date 2017-4-26 上午11:15:29
 */
public interface AccountChurnService {
	
	/**
	 * 每日流失
	 * @Title: selectPerDayLostNumAndRate
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<PlayersChurn>
	 * @author hwj
	 * @date 2017-4-26 上午11:09:50
	 */
	List<AccountChurn> selectPerDayLostNumAndRate (BaseSearchData searchData);
	/**
	 * 流失用户分析
	 * @Title: selectLostUserAnalysNumList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<PlayersChurn>
	 * @author hwj
	 * @date 2017-4-26 上午11:10:05
	 */
	List<AccountChurn> selectLostUserAnalysNumList (BaseSearchData searchData);

}
