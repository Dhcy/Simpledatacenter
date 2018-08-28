package com.good.plat.datacenter.datastat.entity.players;

import java.io.Serializable;

public class ActiveByTime implements Serializable {
	private String hourdate;// 时间段
	private Integer newact;// 新玩家
	private Integer newlogintimes;// 新玩家登陆次数
	private Integer oldlogintimes;// 老玩家登陆次数

	public String getHourdate() {
		return hourdate;
	}

	public void setHourdate(String hourdate) {
		this.hourdate = hourdate;
	}

	public Integer getNewact() {
		return newact;
	}

	public void setNewact(Integer newact) {
		this.newact = newact;
	}

	public Integer getNewlogintimes() {
		return newlogintimes;
	}

	public void setNewlogintimes(Integer newlogintimes) {
		this.newlogintimes = newlogintimes;
	}

	public Integer getOldlogintimes() {
		return oldlogintimes;
	}

	public void setOldlogintimes(Integer oldlogintimes) {
		this.oldlogintimes = oldlogintimes;
	}



}
