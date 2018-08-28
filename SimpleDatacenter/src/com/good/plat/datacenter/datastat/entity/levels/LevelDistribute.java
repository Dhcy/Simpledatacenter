package com.good.plat.datacenter.datastat.entity.levels;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * @ClassName: LevelDistribute
 * @Description: 等级分布
 * @author dmw
 * @date 2016年3月14日 下午4:40:27
 */
public class LevelDistribute extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public LevelDistribute() {
	}
	
	//活跃玩家
	private Integer activePlayers;
	
	//游戏次数
	private Integer gameCounts;
	
	//标题,查询类型
	private String statType;
	
	private Integer day7;
	
	private Integer day14;
	
	private Integer day30;
	//当前等级玩家数
	private Integer currLevPlayCnt;
	//时段等级玩家数
	private Integer durationLevPlayCnt;
	
	public Integer getActivePlayers() {
		return activePlayers;
	}

	public void setActivePlayers(Integer activePlayers) {
		this.activePlayers = activePlayers;
	}

	public Integer getGameCounts() {
		return gameCounts;
	}

	public void setGameCounts(Integer gameCounts) {
		this.gameCounts = gameCounts;
	}

	public String getStatType() {
		return statType;
	}

	public void setStatType(String statType) {
		this.statType = statType;
	}

	public Integer getDay7() {
		return day7;
	}

	public void setDay7(Integer day7) {
		this.day7 = day7;
	}

	public Integer getDay14() {
		return day14;
	}

	public void setDay14(Integer day14) {
		this.day14 = day14;
	}

	public Integer getDay30() {
		return day30;
	}

	public void setDay30(Integer day30) {
		this.day30 = day30;
	}

	public Integer getCurrLevPlayCnt() {
		return currLevPlayCnt;
	}

	public void setCurrLevPlayCnt(Integer currLevPlayCnt) {
		this.currLevPlayCnt = currLevPlayCnt;
	}

	public Integer getDurationLevPlayCnt() {
		return durationLevPlayCnt;
	}

	public void setDurationLevPlayCnt(Integer durationLevPlayCnt) {
		this.durationLevPlayCnt = durationLevPlayCnt;
	}
	

}
