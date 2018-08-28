package com.good.plat.datacenter.independ.entity.hie.index;

import java.io.Serializable;

public class HieRegistStat implements Serializable {
	private String statdate;// 时间
	private Integer gamestart;// 启动游戏次数
	private Integer gameinit;// 游戏初始化次数
	private Integer gameunpack;// 解压次数
	private Integer updatecheck;// 检查更新次数
	private Integer gameupdate;// 更新次数
	private Integer regist;// 注册账号次数
	private Integer poitstart;// 点击开始游戏次数
	private Integer enterload;// 进入loading界面次数
	private Integer entergame;// 进入游戏界面次数

	public String getStatdate() {
		return statdate;
	}

	public void setStatdate(String statdate) {
		this.statdate = statdate;
	}

	public Integer getGamestart() {
		return gamestart;
	}

	public void setGamestart(Integer gamestart) {
		this.gamestart = gamestart;
	}

	public Integer getGameinit() {
		return gameinit;
	}

	public void setGameinit(Integer gameinit) {
		this.gameinit = gameinit;
	}

	public Integer getGameunpack() {
		return gameunpack;
	}

	public void setGameunpack(Integer gameunpack) {
		this.gameunpack = gameunpack;
	}

	public Integer getUpdatecheck() {
		return updatecheck;
	}

	public void setUpdatecheck(Integer updatecheck) {
		this.updatecheck = updatecheck;
	}

	public Integer getGameupdate() {
		return gameupdate;
	}

	public void setGameupdate(Integer gameupdate) {
		this.gameupdate = gameupdate;
	}

	public Integer getRegist() {
		return regist;
	}

	public void setRegist(Integer regist) {
		this.regist = regist;
	}

	public Integer getPoitstart() {
		return poitstart;
	}

	public void setPoitstart(Integer poitstart) {
		this.poitstart = poitstart;
	}

	public Integer getEnterload() {
		return enterload;
	}

	public void setEnterload(Integer enterload) {
		this.enterload = enterload;
	}

	public Integer getEntergame() {
		return entergame;
	}

	public void setEntergame(Integer entergame) {
		this.entergame = entergame;
	}

}
