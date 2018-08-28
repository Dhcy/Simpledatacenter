package com.good.plat.datacenter.game.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.redis.service.impl.RedisServiceImpl;
import com.good.plat.datacenter.game.entity.Project;
import com.good.plat.datacenter.game.entity.TBLogplatSysPlat;
import com.good.plat.datacenter.game.entity.TBLogplatSysPlatProject;
import com.good.plat.datacenter.game.mapper.PlatProjectMapper;
import com.good.plat.datacenter.game.mapper.ProjectMapper;
import com.good.plat.datacenter.game.mapper.TBLogplatSysPlatProjectMapper;
import com.good.plat.datacenter.game.service.ProjectService;

@Service(value = "projectService")
public class ProjectServiceImpl implements ProjectService {
	
	public ProjectServiceImpl() {
	}
	
	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private TBLogplatSysPlatProjectMapper tBLogplatSysPlatProjectMapper;
	@Autowired
	private PlatProjectMapper platProjectMapper;
	@Autowired
	private RedisServiceImpl redisService;
	
	@Override
	public List<Project> findProjectGameList() throws Exception {
		List<Project> projectList = new ArrayList<Project>();
		projectList = projectMapper.selectProjectGameList();
		/*if (redisService.getBytes("PROJECT_GAME_LIST") != null) {
			projectList = (List<Project>) redisService
					.byteArrayToObject(redisService.getBytes("PROJECT_GAME_LIST"));
		} else {
			projectList = projectMapper.selectProjectGameList();
			if (projectList.size() > 0) {
				redisService.set("PROJECT_GAME_LIST".getBytes(), 
						redisService.objectToByteArray(projectList));
			}
		}*/
		
		return projectList;
	}
	
	@Override
	public List<Project> findProjectList() throws Exception {
		List<Project> projectList = new ArrayList<Project>();
		//projectList = projectMapper.selectProjectList();
		//projectList = projectMapper.selectPlatProjectList();
		projectList = projectMapper.selectProjectMayWithoutPlatList();
		/*if (redisService.getBytes("PROJECT_LIST") != null) {
			projectList = (List<Project>) redisService
					.byteArrayToObject(redisService.getBytes("PROJECT_LIST"));
		} else {
			projectList = projectMapper.selectProjectList();
			if (projectList.size() > 0) {
				redisService.set("PROJECT_LIST".getBytes(), 
						redisService.objectToByteArray(projectList));
			}
		}*/
		
		return projectList;
		
	}
	
	@Override
	public Project findProjectById(Integer id) throws Exception {
		Project project = projectMapper.selectByPrimaryKey(id);
		
		return project;
	}

	@Override
	public BaseMessage addProject(Project project) throws Exception {
		BaseMessage mess = new BaseMessage();
		try{
		
		
		Project pro = projectMapper.selectByPrimaryKey(project.getId());
		if (pro == null) {
			int status = projectMapper.insertSelective(project);
			List<Integer> platids = project.getPlatformids();
			TBLogplatSysPlatProject platproj = new TBLogplatSysPlatProject();
			platproj.setProjectid(project.getId());
			platProjectMapper.deleteProjPlatPairs(project);
			platProjectMapper.insertProjPlatPairs(project);
			if (status == 1) {
				//添加成功的时候记得清空redis中数据
				//redisService.del("PROJECT_LIST","PROJECT_GAME_LIST");
				mess.setStatus(status);
				mess.setMessage("添加成功");
			} else {
				mess.setStatus(0);
				mess.setMessage("添加项目失败");
			}
		} else {
			mess.setStatus(0);
			mess.setMessage("添加项目失败:已有相同ID的项目");
		}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return mess;
	}

	@Override
	public BaseMessage deleteProjectPair(Project project) throws Exception {
		BaseMessage mess = new BaseMessage();
		int status = 0;
		//int status = projectMapper.deleteByPrimaryKey(project.getId());
		//platProjectMapper.deleteByProjectId(id);
		List<TBLogplatSysPlatProject> platProjList = platProjectMapper.selectPlatProjectPairByProjectId(project.getId());
		if(platProjList != null && platProjList.size() == 1){
			//delete project & pair
			if(projectMapper.deleteByPrimaryKey(project.getId()) == 0){
				mess.setStatus(0);
				mess.setMessage("删除项目失败");
				return mess;
			}
		}
		//delete pair
		status = platProjectMapper.deletePlatProjectPair(project);
		if (status >= 1) {
			//删除成功的时候记得清空redis中数据
			//redisService.del("PROJECT_LIST","PROJECT_GAME_LIST");
			mess.setStatus(status);
			mess.setMessage("删除成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("删除项目失败");
		}
		return mess;
	}

	@Override
	public String editProject(Project project) throws Exception {
		return null;
	}

	@Override
	public BaseMessage dedeleteByProjectId(Integer id) throws Exception {
		BaseMessage mess = new BaseMessage();
		int status = projectMapper.deleteByPrimaryKey(id);
		platProjectMapper.deleteByProjectId(id);
		if (status == 1) {
			//删除成功的时候记得清空redis中数据
			//redisService.del("PROJECT_LIST","PROJECT_GAME_LIST");
			mess.setStatus(status);
			mess.setMessage("删除成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("删除项目失败");
		}
		return mess;
	}


}
