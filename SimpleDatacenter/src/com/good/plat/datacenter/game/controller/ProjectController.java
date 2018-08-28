package com.good.plat.datacenter.game.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.game.entity.Project;
import com.good.plat.datacenter.game.service.impl.ProjectServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

/**
 * @ClassName: ProjectController
 * @Description: 项目管理
 * @author dmw
 * @date 2016年1月11日 上午11:18:32
 */

@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController{

	@Autowired
	private ProjectServiceImpl projectService;
	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	
	
	
	/**
	 * @Title: findProjectList
	 * @Description: 查询project
	 * @throws Exception
	 * List<Project>
	 * @author dmw
	 * @date 2016年1月12日 下午5:49:53
	 */
	@RequestMapping(value = "/projectList", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<Project> findProjectList(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "findProjectList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		List<Project> projectList = projectService.findProjectList();
		
		return projectList;
	}

	/**
	 * @Title: deleteProject
	 * @Description: 删除
	 * @param project
	 * @throws Exception
	 * BaseMessage
	 * @author dmw
	 * @date 2016年1月12日 下午5:50:12
	 */
	@RequestMapping(value = "/deleteProject", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage deleteProject(@RequestBody Project project) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "deleteProject";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, project);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		BaseMessage mess = null;
		if(project.getPlatformid() == null){
			mess = projectService.dedeleteByProjectId(project.getId());
		}else{
		    mess = projectService.deleteProjectPair(project);
		}
		return mess;
	}
	
	@RequestMapping(value = "/addProject", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public BaseMessage addProject(@Validated @RequestBody Project project, 
			BindingResult result) throws Exception {
		try{
			//log
			Date opDate = new Date();
			String methodName = "addProject";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, project);
			sysAccessLogService.saveAccessLog(accessLog);
			//
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
		
		BaseMessage mess = new BaseMessage();
		if (result.hasErrors()) {
			mess.setStatus(0);
			mess.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return mess;
		} else {
			mess = projectService.addProject(project);
		}

		return mess;
	}
	
	
	@RequestMapping(value = "/getProject", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Project getProject(@RequestBody Project project) throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "getProject";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, project);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		
		Project pro = projectService.findProjectById(project.getId());

		return pro;
	}
	
	
}
