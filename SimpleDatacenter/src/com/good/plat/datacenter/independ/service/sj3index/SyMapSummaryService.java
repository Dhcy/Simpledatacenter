package com.good.plat.datacenter.independ.service.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.SyMapSummary;

/**
 * 圣域地图统计业务
 * @ClassName: SyMapSummaryService
 * @Description: TODO
 * @author hwj
 * @date 2017-9-21 下午02:42:49
 */
public interface SyMapSummaryService {
	
	/**
	 * 圣域战斗
	 * @Title: selectSyCombatList
	 * @Description: TODO
	 * @param searData
	 * @return
	 * @throws Exception
	 * List<SyMapSummary>
	 * @author hwj
	 * @date 2017-9-21 下午02:42:00
	 */
	public List<SyMapSummary> selectSyCombatList(IndependBaseSearchData searchData) throws Exception;
	
}
