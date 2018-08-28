package com.good.plat.datacenter.datastat.entity.players;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

public class GameFrequency  extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3220991356146578916L;
	
	private Integer frequency;//游戏频次
	private Integer accounts;//玩家数量
	private Integer timezone;//游戏实际(小时)
	
	public Integer getFrequency() {
		return frequency;
	}
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	public Integer getAccounts() {
		return accounts;
	}
	public void setAccounts(Integer accounts) {
		this.accounts = accounts;
	}
	public Integer getTimezone() {
		return timezone;
	}
	public void setTimezone(Integer timezone) {
		this.timezone = timezone;
	}
	
}
