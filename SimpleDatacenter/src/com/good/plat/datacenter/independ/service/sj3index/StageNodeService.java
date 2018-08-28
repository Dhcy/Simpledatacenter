package com.good.plat.datacenter.independ.service.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.StageNode;

/**
 * 关卡节点
 * @ClassName: StageNodeService
 * @Description: TODO
 * @author hwj
 * @date 2017-6-16 下午02:51:17
 */
public interface StageNodeService {

	/**
	 * 时间段内游戏的关卡列表
	 * @Title: selectTimeGameStageList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<StageNode>
	 * @author hwj
	 * @date 2017-6-16 下午02:48:10
	 */
	public List<StageNode> selectTimeGameStageList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 关卡节点
	 * @Title: selectStageNodeSuccessList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<StageNode>
	 * @author hwj
	 * @date 2017-6-16 下午02:49:16
	 */
	public List<StageNode> selectStageNodeSuccessList(IndependBaseSearchData searchData)throws Exception;
}
