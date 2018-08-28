package com.good.plat.datacenter.datastat.mapper.version;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.version.VersionAnalysis;

/**
 * @ClassName: VersionMapper
 * @Description: 版本分析
 * @author moxw
 * @date 2016年3月25日 下午4:17:23
 */
public interface VersionMapper {
	
	/**
	 * @Title: selectNewUserList
	 * @Description: 新增用户
	 * @param searchData
	 * @return
	 * List<VersionAnalysis>
	 * @author moxw
	 * @date 2016年3月25日 下午4:18:57
	 */
	List<VersionAnalysis> selectNewUserList (BaseSearchData searchData);
	
	//导出
	List<VersionAnalysis> exportNewUserList (BaseSearchData searchData);
	

	/**
	 * @Title: selectActiveUserList
	 * @Description: 活跃用户
	 * @param searchData
	 * @return
	 * List<VersionAnalysis>
	 * @author moxw
	 * @date 2016年4月26日 下午4:19:08
	 */
	List<VersionAnalysis> selectActiveUserList (BaseSearchData searchData);
	
	//导出
	List<VersionAnalysis> exportActiveUserList (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectLoginUserList
	 * @Description: 登录用户
	 * @param searchData
	 * @return
	 * List<VersionAnalysis>
	 * @author moxw
	 * @date 2016年3月25日 下午4:19:18
	 */
	List<VersionAnalysis> selectLoginUserList (BaseSearchData searchData);
	
	//导出
	List<VersionAnalysis> exportLoginUserList (BaseSearchData searchData);
	
}
