package com.good.plat.datacenter.datastat.mapper.project;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.project.ProjectGameDetail;

/**
 * 
 * @ClassName: ProjectGameDetailMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-3-31 上午09:57:51
 */
public interface ProjectGameDetailMapper {
	
	/**
	 * 项目下的游戏列表
	 * @Title: selectGameList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<ProjectGameDetail>
	 * @author hwj
	 * @date 2017-3-31 上午10:00:48
	 */
	List<ProjectGameDetail> selectGameListByProjectId(BaseSearchData searchData)throws Exception;
	
	/**
	 * 每个游戏的数据
	 * @Title: selectEveryGameDataList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<ProjectGameDetail>
	 * @author hwj
	 * @date 2017-3-31 上午11:14:41
	 */
	List<ProjectGameDetail> selectEveryGameDataList(BaseSearchData searchData)throws Exception;
	/**
	 * 每个游戏付费次数
	 * @Title: selectEveryGamePayTime
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<ProjectGameDetail>
	 * @author hwj
	 * @date 2017-3-31 上午11:15:26
	 */
	List<ProjectGameDetail> selectEveryGamePayTime(BaseSearchData searchData)throws Exception;
	
	
}
