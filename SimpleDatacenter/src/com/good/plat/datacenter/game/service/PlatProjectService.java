package com.good.plat.datacenter.game.service;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.game.entity.Project;
import com.good.plat.datacenter.game.entity.TBLogplatSysPlat;
import com.good.plat.datacenter.login.user.entity.FirstMenu;

/**
 * @ClassName: PlatProjectService
 * @Description: 平台管理Service
 * @author moxw
 * @date 2016年6月16日 上午11:20:46
 */
public interface PlatProjectService {
	public List<TBLogplatSysPlat> selectPlatList() throws Exception;
	
	public List<Project> selectProjectByPlatID(Integer pid) throws Exception;

	BaseMessage addPlat(TBLogplatSysPlat plat) throws Exception;

	BaseMessage deletePlat(TBLogplatSysPlat plat) throws Exception;

	BaseMessage updatePlat(TBLogplatSysPlat plat) throws Exception;

	List<FirstMenu> selectUserPlatProjectGameMenuList(BaseSearchData baseSearchData) throws Exception;

	List<TBLogplatSysPlat> selectUserPlatProjectGameList(int userid) throws Exception;

	public List<TBLogplatSysPlat> selectUserPlatList(Integer userid) throws Exception;
}
