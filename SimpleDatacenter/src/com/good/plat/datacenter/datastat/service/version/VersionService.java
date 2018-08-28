package com.good.plat.datacenter.datastat.service.version;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.version.VersionAnalysis;

public interface VersionService {

	/**
	 * @Title: selectNewUserList
	 * @Description: 新增玩家
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<VersionAnalysis>
	 * @author moxw
	 * @date 2016年4月26日 下午4:11:56
	 */
	List<VersionAnalysis> selectNewUserList(BaseSearchData searchData) throws Exception;
	
	List<VersionAnalysis> exportNewUserList(BaseSearchData searchData) throws Exception;

	
	/**
	 * @Title: selectActiveUserList
	 * @Description: 活跃玩家
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<VersionAnalysis>
	 * @author moxw
	 * @date 2016年4月26日 下午4:12:23
	 */
	List<VersionAnalysis> selectActiveUserList(BaseSearchData searchData) throws Exception;

	List<VersionAnalysis> exportActiveUserList(BaseSearchData searchData) throws Exception;

	
	/**
	 * 
	 * @Title: selectLoginUserList
	 * @Description: 登录玩家
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<VersionAnalysis>
	 * @author moxw
	 * @date 2016年4月26日 下午4:12:43
	 */
	List<VersionAnalysis> selectLoginUserList(BaseSearchData searchData) throws Exception;

	List<VersionAnalysis> exportLoginUserList(BaseSearchData searchData) throws Exception;



}
