package com.good.plat.datacenter.datastat.service.players;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.players.RollServer;

/**
 * 
 * @ClassName: RollServService
 * @Description: 滚服分析
 * @author hwj
 * @date 2017-1-11 下午02:18:25
 */
public interface RollServService {
	/**
	 * 
	 * @Title: selectRollServerList
	 * @Description: 查询滚服列表(含人数占比、充值占比)
	 * @param searchData
	 * @return
	 * List<RollServer>
	 * @author hwj
	 * @date 2017-1-11 下午02:19:07
	 */
	List<RollServer> selectRollServerList(BaseSearchData searchData);
}
