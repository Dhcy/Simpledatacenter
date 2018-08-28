package com.good.plat.datacenter.independ.service.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Stage;


/**
 * 关卡
 * @ClassName: StageService
 * @Description: TODO
 * @author hwj
 * @date 2017-2-23 下午03:57:54
 */
public interface StageService {
	
	/**
	 * 关卡任务统计
	 * @Title: selectStageTaskList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Stage>
	 * @author hwj
	 * @date 2017-9-28 下午03:31:12
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
