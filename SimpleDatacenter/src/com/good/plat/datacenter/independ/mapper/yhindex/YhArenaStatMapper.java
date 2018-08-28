package com.good.plat.datacenter.independ.mapper.yhindex;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhArenaStat;

/**
 * 竞技场统计
 * @ClassName: YhArenaStatMapper
 * @Description: TODO
 * @author hwj
 * @date 2018-5-22 上午11:17:12
 */
public interface YhArenaStatMapper {
	/**
	 * 段位统计
	 * @Title: selectYhDanStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhArenaStat>
	 * @author hwj
	 * @date 2018-5-22 上午11:38:47
	 */
	public List<YhArenaStat> selectYhDanStatList(IndependBaseSearchData searchData)throws Exception;
	/**
	 *  各分段匹配时长统计
	 * @Title: selectPerMateTimeStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhArenaStat>
	 * @author hwj
	 * @date 2018-5-22 上午11:40:25
	 */
	public List<YhArenaStat> selectPerMateTimeStatList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 各时段在线人数统计
	 * @Title: selectPerHourOnlineCountStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhArenaStat>
	 * @author hwj
	 * @date 2018-5-22 上午11:41:41
	 */
	public List<YhArenaStat> selectPerHourOnlineCountStatList(IndependBaseSearchData searchData)throws Exception;

}
