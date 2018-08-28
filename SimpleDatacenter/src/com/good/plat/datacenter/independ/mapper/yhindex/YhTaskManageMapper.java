package com.good.plat.datacenter.independ.mapper.yhindex;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhTaskManage;

/**
 * 课程统计
 * @ClassName: YhCourseStatMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-12-27 下午03:44:31
 */
public interface YhTaskManageMapper {
	/**
	 * 课程统计
	 * @Title: selectCourseStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhCourseStat>
	 * @author hwj
	 * @date 2017-12-27 下午03:46:10
	 */
	public List<YhTaskManage> selectYhCourseStatList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 系统参与度
	 * @Title: selectYhSystemJoinList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhCourseStat>
	 * @author hwj
	 * @date 2017-12-28 下午12:53:13
	 */
	public List<YhTaskManage> selectYhSystemJoinList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 课程步数排重
	 * @Title: selectYhCourseDisStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhTaskManage>
	 * @author hwj
	 * @date 2018-5-31 下午06:16:34
	 */
	public List<YhTaskManage> selectYhCourseDisStatList(IndependBaseSearchData searchData)throws Exception;
	
}
	
