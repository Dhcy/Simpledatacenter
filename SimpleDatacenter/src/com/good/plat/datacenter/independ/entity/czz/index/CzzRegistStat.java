package com.good.plat.datacenter.independ.entity.czz.index;

import java.io.Serializable;

public class CzzRegistStat implements Serializable{
	private Integer gamestart;
	private Integer getaddress;
	private Integer updatecheck;
	private Integer regist;
	private Integer startgame;
	private Integer entergame;
	public Integer getGamestart() {
		return gamestart;
	}
	public void setGamestart(Integer gamestart) {
		this.gamestart = gamestart;
	}
	public Integer getGetaddress() {
		return getaddress;
	}
	public void setGetaddress(Integer getaddress) {
		this.getaddress = getaddress;
	}
	public Integer getUpdatecheck() {
		return updatecheck;
	}
	public void setUpdatecheck(Integer updatecheck) {
		this.updatecheck = updatecheck;
	}
	public Integer getRegist() {
		return regist;
	}
	public void setRegist(Integer regist) {
		this.regist = regist;
	}
	public Integer getStartgame() {
		return startgame;
	}
	public void setStartgame(Integer startgame) {
		this.startgame = startgame;
	}
	public Integer getEntergame() {
		return entergame;
	}
	public void setEntergame(Integer entergame) {
		this.entergame = entergame;
	}
	
}
