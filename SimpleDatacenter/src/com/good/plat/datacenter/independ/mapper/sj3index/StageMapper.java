package com.good.plat.datacenter.independ.mapper.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Stage;

/**
 * 关卡
 * @ClassName: StageMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-2-23 下午03:42:06
 */
public interface StageMapper {
	/**
	 * 关卡任务
	 * @Title: selectStageTaskList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Stage>
	 * @author hwj
	 * @date 2017-9-28 下午03:29:31
	 */
	List<Stage> selectStageTaskList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 关卡统计
	 * @Title: selectStageList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Stage>
	 * @author hwj
	 * @date 2018-4-9 下午02:39:05
	 */
	List<Stage> selectStageList(IndependBaseSearchData searchData)throws Exception;
}
