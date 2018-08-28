package com.good.plat.datacenter.datastat.entity.players;

import java.io.Serializable;

/**
 * @ClassName: ActiveByChnnel
 * @Description: TODO
 * @author moxw
 * @date 2018年7月27日 下午7:03:16
 */
public class ActiveByChnnel implements Serializable {
	private String chinnel;// 渠道
	private Integer newact;// 新玩家
	private Integer newlogintimes;// 新玩家登陆次数
	private Integer oldlogintimes;// 老玩家登陆次数

	public String getChinnel() {
		return chinnel;
	}

	public void setChinnel(String chinnel) {
		this.chinnel = chinnel;
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
