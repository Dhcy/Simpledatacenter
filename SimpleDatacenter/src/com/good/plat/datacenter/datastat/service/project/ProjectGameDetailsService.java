package com.good.plat.datacenter.datastat.service.project;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.project.ProjectGameDetail;

/**
 * 项目游戏详情
 * @ClassName: ProjectGameDetailsService
 * @Description: TODO
 * @author hwj
 * @date 2017-3-31 上午11:20:17
 */
public interface ProjectGameDetailsService {
	
	/**
	 * 项目下游戏数据
	 * @Title: selectGameDataListByProject
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * Map<String,Object>
	 * @author hwj
	 * @date 2017-3-31 上午11:22:42
	 */
	public List<ProjectGameDetail> selectGameDataListByProject(BaseSearchData searchData)throws Exception;

}
