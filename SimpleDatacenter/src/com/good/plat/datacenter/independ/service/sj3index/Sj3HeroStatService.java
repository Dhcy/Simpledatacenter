package com.good.plat.datacenter.independ.service.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3HeroStat;

/**
 * 英雄冢
 * @ClassName: Sj3HeroStatService
 * @Description: TODO
 * @author hwj
 * @date 2018-1-11 上午11:26:59
 */
public interface Sj3HeroStatService {
	/**
	 * 每天以前的关卡统计
	 * @Title: selectStageStatBoforePerday
	 * @Description: TODO
	 * @return
	 * @throws Exception
	 * List<Sj3HeroStat>
	 * @author hwj
	 * @date 2018-1-11 上午11:25:02
	 */
	public List<Sj3HeroStat> selectStageStatBoforePerday(IndependBaseSearchData searchData)throws Exception;
	
	/**
	 * 关卡统计
	 * @Title: selectStageStat
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Sj3HeroStat>
	 * @author hwj
	 * @date 2018-1-11 下午12:01:22
	 */
	public List<Sj3HeroStat> selectStageStat(IndependBaseSearchData searchData)throws Exception;
}
