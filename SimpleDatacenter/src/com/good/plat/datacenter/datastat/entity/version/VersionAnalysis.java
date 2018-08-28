package com.good.plat.datacenter.datastat.entity.version;

import com.good.plat.datacenter.common.base.BaseEntity;

public class VersionAnalysis extends BaseEntity{

	public VersionAnalysis() {
	}
	
	//版本
	private String version;
	//新增玩家
	private Integer newUser;
	//活跃玩家
	private Integer activeUser;
	//登录玩家
	private Integer loginUser;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getNewUser() {
		return newUser;
	}

	public void setNewUser(Integer newUser) {
		this.newUser = newUser;
	}

	public Integer getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(Integer activeUser) {
		this.activeUser = activeUser;
	}

	public Integer getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(Integer loginUser) {
		this.loginUser = loginUser;
	}
	
}
