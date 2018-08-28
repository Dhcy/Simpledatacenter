package com.good.plat.datacenter.datastat.mapper.players;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.players.RollServer;

/**
 * 
 * @ClassName: RollServerMapper
 * @Description: 滚服分析
 * @author hwj
 * @date 2017-1-11 下午01:59:10
 */
public interface RollServerMapper {
	/**
	 * 
	 * @Title: selectRollServerList
	 * @Description: 查询滚服列表
	 * @param searchData
	 * @return
	 * List<RollServer>
	 * @author hwj
	 * @date 2017-1-11 下午01:59:57
	 */
	List<RollServer> selectRollServerList(BaseSearchData searchData);
}
