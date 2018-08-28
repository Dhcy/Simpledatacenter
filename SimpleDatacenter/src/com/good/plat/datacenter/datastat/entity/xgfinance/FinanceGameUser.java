package com.good.plat.datacenter.datastat.entity.xgfinance;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

public class FinanceGameUser extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 109374802076032993L;
	//新增用户数
	private Integer newUser;
	public Integer getNewUser() {
		return newUser;
	}
	public void setNewUser(Integer newUser) {
		this.newUser = newUser;
	}

}
