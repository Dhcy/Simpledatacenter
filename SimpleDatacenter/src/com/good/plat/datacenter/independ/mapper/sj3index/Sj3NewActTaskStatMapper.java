package com.good.plat.datacenter.independ.mapper.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3NewActTaskStat;

/**
 * 新手任务
 * @ClassName: Sj3NewActTaskStatMapper
 * @Description: TODO
 * @author hwj
 * @date 2018-1-11 下午02:37:58
 */
public interface Sj3NewActTaskStatMapper {
	/**
	 * 新手任务
	 * @Title: selectNewActTaskList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Sj3NewActTaskStat>
	 * @author hwj
	 * @date 2018-1-11 下午02:45:35
	 */
	public List<Sj3NewActTaskStat> selectNewActTaskList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 新手任务总的完成次数
	 * @Title: selectNewActTotalFinishCountList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Sj3NewActTaskStat>
	 * @author hwj
	 * @date 2018-1-11 下午03:13:20
	 */
	public List<Sj3NewActTaskStat> selectNewActTotalFinishCountList(IndependBaseSearchData searchData)throws Exception;
}
