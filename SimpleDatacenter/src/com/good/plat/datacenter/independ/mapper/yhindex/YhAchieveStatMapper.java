package com.good.plat.datacenter.independ.mapper.yhindex;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhAchieveStat;

public interface YhAchieveStatMapper {
	/**
	 * 成就统计
	 * @Title: selectyhAchieveStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhAchieveStat>
	 * @author hwj
	 * @date 2018-5-21 下午05:54:09
	 */
	public List<YhAchieveStat> selectyhAchieveStatList(IndependBaseSearchData searchData)throws Exception;

}
