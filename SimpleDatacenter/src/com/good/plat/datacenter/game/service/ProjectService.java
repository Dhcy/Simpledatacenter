package com.good.plat.datacenter.game.service;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.game.entity.Project;

/**
 * @ClassName: ProjectService
 * @Description: 项目管理Service
 * @author dmw
 * @date 2016年1月11日 上午11:20:46
 */
public interface ProjectService {
	/**
	 * @Title: findProjectList
	 * @Description: 查询列表
	 * @throws Exception
	 * List<Project>
	 * @author dmw
	 * @date 2016年1月11日 上午11:29:00
	 */
	public List<Project> findProjectList() throws Exception;
	
	
	/**
	 * @Title: findProjectGameList
	 * @Description: 关联查询game
	 * @throws Exception
	 * List<Project>
	 * @author dmw
	 * @date 2016年1月12日 下午5:26:56
	 */
	public List<Project> findProjectGameList() throws Exception;

	
	/**
	 * @Title: findProjectById
	 * @Description: 查询
	 * @param id
	 * @throws Exception
	 * Project
	 * @author dmw
	 * @date 2016年1月11日 上午11:29:48
	 */
	public Project findProjectById(Integer id) throws Exception;
	
	
	/**
	 * @Title: addProject
	 * @Description: 增加
	 * @param project
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年1月11日 上午11:30:46
	 */
	public BaseMessage addProject(Project project) throws Exception;
	
	
	/**
	 * @Title: editProject
	 * @Description: 修改
	 * @param project
	 * @throws Exception
	 * String
	 * @author dmw
	 * @date 2016年1月11日 上午11:31:13
	 */
	public String editProject(Project project) throws Exception;

	/**
	 * @Title: 
	 * @Description: 删除
	 * @param 
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年1月11日 上午11:31:00
	 */
	BaseMessage deleteProjectPair(Project project) throws Exception;


	BaseMessage dedeleteByProjectId(Integer id) throws Exception;


	
	
	
	
}
