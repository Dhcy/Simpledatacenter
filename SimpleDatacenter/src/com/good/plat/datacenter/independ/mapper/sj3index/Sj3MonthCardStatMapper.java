package com.good.plat.datacenter.independ.mapper.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3MonthCardStat;

/**
 * 世界3月卡统计
 * @ClassName: Sj3MonthCardStatMapper
 * @Description: TODO
 * @author hwj
 * @date 2018-4-3 上午11:43:31
 */
public interface Sj3MonthCardStatMapper {
	/**
	 * 月卡统计
	 * @Title: selectMonthCardStatList
	 * @Description: TODO
	 * @return
	 * List<Sj3MonthCardStat>
	 * @author hwj
	 * @date 2018-4-3 上午11:50:32
	 */
	public List<Sj3MonthCardStat> selectMonthCardStatList(IndependBaseSearchData searchData);
}
