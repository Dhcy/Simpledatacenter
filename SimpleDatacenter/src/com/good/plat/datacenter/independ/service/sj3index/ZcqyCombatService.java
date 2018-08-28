package com.good.plat.datacenter.independ.service.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.ZcqyCombat;

public interface ZcqyCombatService {
	/**
	 * 主城战斗
	 * @Title: selectZcCombatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<ZcqyCombat>
	 * @author hwj
	 * @date 2017-9-22 下午06:18:28
	 */
	public List<ZcqyCombat> selectZcCombatList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 主城玩法战斗详情
	 * @Title: selectPlayTypeCombatDetailList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<ZcqyCombat>
	 * @author hwj
	 * @date 2017-9-22 下午06:20:05
	 */
	public List<ZcqyCombat> selectPlayTypeCombatDetailList(IndependBaseSearchData searchData)throws Exception;

}
