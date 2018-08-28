package com.good.plat.datacenter.independ.service.yhindex;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhStageStat;

public interface YhStageStatService {
	/**
	 * 关卡统计
	 * @Title: selectYhStageList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhStageStat>
	 * @author hwj
	 * @date 2017-12-27 下午12:41:24
	 */
	public List<YhStageStat> selectYhStageList(IndependBaseSearchData searchData)throws Exception;
	
	/**
	 * 关卡数据
	 * @Title: selectYhStageDataList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhStageStat>
	 * @author hwj
	 * @date 2017-12-27 下午01:56:13
	 */
	public List<YhStageStat> selectYhStageDataList(IndependBaseSearchData searchData)throws Exception;
	
	/**
	 * 关卡星数
	 * @Title: selectYhStageStarNumList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhStageStat>
	 * @author hwj
	 * @date 2018-5-16 下午12:41:46
	 */
	public List<YhStageStat> selectYhStageStarNumList(IndependBaseSearchData searchData)throws Exception;
	
	/**
	 * 联合作战关卡玩家数情况
	 * @Title: selectUnitStageActUserNumList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhStageStat>
	 * @author hwj
	 * @date 2018-5-17 下午03:01:32
	 */
	public List<YhStageStat> selectUnitStageActUserNumList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 联合作战关卡次数情况
	 * @Title: selectUnitStageCountList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhStageStat>
	 * @author hwj
	 * @date 2018-5-17 下午03:03:03
	 */
	public List<YhStageStat> selectUnitStageCountList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 演戏统计关卡玩家数情况
	 * @Title: selectActingStageActUserNumList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhStageStat>
	 * @author hwj
	 * @date 2018-5-17 下午03:03:45
	 */
	public List<YhStageStat> selectActingStageActUserNumList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 演戏统计关卡次数情况
	 * @Title: selectActingStageCountList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhStageStat>
	 * @author hwj
	 * @date 2018-5-17 下午03:05:36
	 */
	public List<YhStageStat> selectActingStageCountList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 关卡统计排重
	 * @Title: selectYhStageDisList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhStageStat>
	 * @author hwj
	 * @date 2018-6-1 上午09:45:14
	 */
	public List<YhStageStat> selectYhStageDisList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 银河关卡数据排重
	 * @Title: selectYhStageDataDisList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhStageStat>
	 * @author hwj
	 * @date 2018-6-1 上午09:46:02
	 */
	public List<YhStageStat> selectYhStageDataDisList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 银河关卡星数排重
	 * @Title: selectYhStageStarNumDisList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhStageStat>
	 * @author hwj
	 * @date 2018-6-1 上午09:46:46
	 */
	public List<YhStageStat> selectYhStageStarNumDisList(IndependBaseSearchData searchData)throws Exception;
	
}
