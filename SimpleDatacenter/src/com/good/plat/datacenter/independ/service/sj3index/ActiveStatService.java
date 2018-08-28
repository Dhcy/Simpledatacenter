package com.good.plat.datacenter.independ.service.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.ActiveStat;

public interface ActiveStatService {
	/**
	 * 活跃度分数及占比
	 * @Title: selectActiveCntAndRateList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<ActiveStat>
	 * @author hwj
	 * @date 2017-9-21 上午10:23:51
	 */
	public List<ActiveStat> selectActiveCntAndRateList(IndependBaseSearchData searchData);
	/**
	 * 回购物品列表
	 * @Title: selectbugBackGoodsList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<ActiveStat>
	 * @author hwj
	 * @date 2017-9-21 上午10:24:52
	 */
	public List<ActiveStat> selectbugBackGoodsList(IndependBaseSearchData searchData);

}
