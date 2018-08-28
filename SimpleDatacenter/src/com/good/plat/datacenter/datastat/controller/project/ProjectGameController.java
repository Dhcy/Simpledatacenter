package com.good.plat.datacenter.datastat.controller.project;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.datastat.entity.project.ProjectGameDetail;
import com.good.plat.datacenter.datastat.service.project.ProjectGameDetailsService;
import com.good.plat.datacenter.login.service.impl.LoginServiceImpl;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;


/**
 * 项目游戏控制器
 * @ClassName: ProjectGameController
 * @Description: TODO
 * @author hwj
 * @date 2017-4-10 上午09:32:57
 */
@Controller
@RequestMapping("/projectGame")
public class ProjectGameController {

	//log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private ProjectGameDetailsService projectGameDetailsService;
	
	/**
	 * 项目下游戏数据
	 * @Title: selectGameDataList
	 * @Description: TODO
	 * @param searchData
	 * @param request
	 * @return
	 * @throws Exception
	 * List<ProjectGameDetail>
	 * @author hwj
	 * @date 2017-3-31 下午02:05:04
	 */
	@RequestMapping(value = "/selectGameDataList", method = { RequestMethod.POST,RequestMethod.GET })
	@ResponseBody
	public Map<String,Object> selectGameDataList(@RequestBody BaseSearchData searchData,HttpServletRequest request,HttpSession session)throws Exception {
		//log
		Date opDate = new Date();
		String methodName = "selectGameDataList";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		request.getSession().getAttribute("userid");
		int userid = LoginServiceImpl.getUserID(session);
		searchData.setUserid(userid);
		//
		Map<String,Object> map=new HashMap<String, Object>();
		List<ProjectGameDetail> gameDataList=null;
		//总计
		Integer total_actvCount=0;//总激活设备数
		Integer total_registerCount=0;//总注册用户
		Integer total_actUser=0;//总活跃用户
		Integer total_payUser=0;//总付费用户
		Integer total_payTime=0;//总付费次数
		Double total_income=0.0;//总收入
		Double total_arpu=0.0;//总arpu
		gameDataList=projectGameDetailsService.selectGameDataListByProject(searchData);
		if(gameDataList!=null&&gameDataList.size()>0){
			for(ProjectGameDetail p:gameDataList){
				total_actvCount+=p.getActvCount();
				total_registerCount+=p.getRegisterCount();
				total_actUser+=p.getActUser();
				total_payUser+=p.getPayUser();
				total_payTime+=p.getPayTime();
				total_income+=p.getIncome();
				total_arpu+=p.getArpu();
			}
		}
		map.put("total_actvCount", total_actvCount);
		map.put("total_registerCount", total_registerCount);
		map.put("total_actUser", total_actUser);
		map.put("total_payUser", total_payUser);
		map.put("total_payTime", total_payTime);
		map.put("total_income", total_income);
		map.put("total_arpu", total_arpu);
		map.put("gameList", gameDataList);
		return map;
	}
	
}
