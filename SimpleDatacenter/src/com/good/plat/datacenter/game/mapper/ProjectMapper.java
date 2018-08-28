package com.good.plat.datacenter.game.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.good.plat.datacenter.game.entity.Project;

public interface ProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
    
    List<Project> selectProjectList();
    
    List<Project> selectProjectGameList();
    
    List<Project> selectProjectByUserid(@Param("userid")Integer userid);

	List<Project> selectPlatProjectList();
	
	List<Project> selectProjectMayWithoutPlatList();
    
}