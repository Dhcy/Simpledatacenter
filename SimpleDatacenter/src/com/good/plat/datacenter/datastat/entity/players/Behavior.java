package com.good.plat.datacenter.datastat.entity.players;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * @ClassName: Behavior
 * @Description: 游戏习惯
 * @author dmw
 * @date 2016年3月14日 下午1:53:08
 */
public class Behavior extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public Behavior() {
	}

	
	//游戏时长
	private Integer gameTimes;
	
	//游戏次数
	private Integer gameCounts;
	
	//平均次数
	private Double countAVG;
	
	//总时长
	private Integer timeSUM;
	
	//账号数
	private Integer accounts;
	
	//百分比
	private Double rate;
	
	//标题,查询类型
	private String statType;


	public Integer getGameTimes() {
		return gameTimes;
	}

	public void setGameTimes(Integer gameTimes) {
		this.gameTimes = gameTimes;
	}

	public Integer getGameCounts() {
		return gameCounts;
	}

	public void setGameCounts(Integer gameCounts) {
		this.gameCounts = gameCounts;
	}

	public Double getCountAVG() {
		return countAVG;
	}

	public void setCountAVG(Double countAVG) {
		this.countAVG = countAVG;
	}

	public Integer getTimeSUM() {
		return timeSUM;
	}

	public void setTimeSUM(Integer timeSUM) {
		this.timeSUM = timeSUM;
	}

	public Integer getAccounts() {
		return accounts;
	}

	public void setAccounts(Integer accounts) {
		this.accounts = accounts;
	}

	public String getStatType() {
		return statType;
	}

	public void setStatType(String statType) {
		this.statType = statType;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	
}
