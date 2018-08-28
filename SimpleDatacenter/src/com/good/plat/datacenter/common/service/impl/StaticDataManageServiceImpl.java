package com.good.plat.datacenter.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.good.plat.datacenter.common.service.StaticDataManageService;
import com.good.plat.datacenter.game.entity.Game;
import com.good.plat.datacenter.game.entity.GameArea;
import com.good.plat.datacenter.game.entity.Project;

public class StaticDataManageServiceImpl implements StaticDataManageService {
	//项目
	public Map<String, Project> projectMap = new HashMap<String, Project>();
	public List<Project> projectList = new ArrayList<Project>();
	//游戏
	public Map<String, Game> gameMap = new HashMap<String, Game>();
	public List<Project> gameList = new ArrayList<Project>();
	//区服
	public Map<String, GameArea> areaMap = new HashMap<String, GameArea>();
	public List<Project> areaList = new ArrayList<Project>();
	
	
}
