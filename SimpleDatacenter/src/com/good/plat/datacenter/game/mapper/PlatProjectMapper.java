package com.good.plat.datacenter.game.mapper;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.game.entity.Project;
import com.good.plat.datacenter.game.entity.TBLogplatSysPlat;
import com.good.plat.datacenter.game.entity.TBLogplatSysPlatProject;
import com.good.plat.datacenter.login.user.entity.FirstMenu;

public interface PlatProjectMapper {

	List<TBLogplatSysPlat> selectPlatList();

	List<Project> selectProjectByPlatID(Integer pid);

	List<TBLogplatSysPlat> selectUserPlatProjectGameList(int userid);

	List<FirstMenu> selectUserPlatProjectGameMenuList(BaseSearchData baseSearchData);

	List<TBLogplatSysPlat> selectUserPlatList(Integer userid);
	Integer deleteByProjectId(int projectid);
	Integer insertProjPlatPairs(Project proj);

	Integer deleteByPlatId(Integer platid);

	Integer deletePlatProjectPair(Project project);

	List<TBLogplatSysPlatProject> selectPlatProjectPairByProjectId(Integer id);

	Integer deleteProjPlatPairs(Project project);
}