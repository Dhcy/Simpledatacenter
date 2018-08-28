package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 世界3王者争霸赛
 * @ClassName: Sj3Wzzb
 * @Description: TODO
 * @author hwj
 * @date 2017-10-12 下午03:17:42
 */
public class Sj3Wzzb extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer joinCnt;//参与人数
	private Integer trainCnt;//训练场使用人数
	private Integer jnCnt;//技能使用人数
	private Integer mwCnt;//魔物使用人数
	private Integer lhCnt;//灵魂使用人数
	/*观看战斗*/
	private Integer watchCnt;//观看人数
	private Integer watchTimes;//观看人次
	//
	private Integer type;//装备类型
	private String equipName;//装备名称
	private Integer equipCnt;//装备使用人数
	//押注
	private Integer betCnt;//押注参与人数
	private Integer betTimes;//押注总次数
	private Integer betCost;//押注消耗总数
	private Integer betGain;//押注获取总数
	//押注档次
	private Integer groupBetCnt;//小组赛押注参与人数
	private Integer groupBetTimes;//小组赛押注总次数
	private Integer finalsBetCnt;//总决赛押注参与人数
	private Integer finalsBetTimes;//总决赛押注总次数
	//外围赛
	private Integer actorid;//角色id
	private String actorname;//角色名
	private Integer joinTimes;//战斗场次
	private Integer winTimes;//胜利场次
	private Double winRate;//胜率
	private Integer lsTimes;//连胜次数
	private Integer lsCnt;//连胜人数
	private String raking;//排名
	private Integer level;//押注档次
	private String jobs;//职业
	public Integer getJoinCnt() {
		return joinCnt;
	}
	public void setJoinCnt(Integer joinCnt) {
		this.joinCnt = joinCnt;
	}
	public Integer getTrainCnt() {
		return trainCnt;
	}
	public void setTrainCnt(Integer trainCnt) {
		this.trainCnt = trainCnt;
	}
	public Integer getJnCnt() {
		return jnCnt;
	}
	public void setJnCnt(Integer jnCnt) {
		this.jnCnt = jnCnt;
	}
	public Integer getMwCnt() {
		return mwCnt;
	}
	public void setMwCnt(Integer mwCnt) {
		this.mwCnt = mwCnt;
	}
	public Integer getLhCnt() {
		return lhCnt;
	}
	public void setLhCnt(Integer lhCnt) {
		this.lhCnt = lhCnt;
	}
	public Integer getWatchCnt() {
		return watchCnt;
	}
	public void setWatchCnt(Integer watchCnt) {
		this.watchCnt = watchCnt;
	}
	public Integer getWatchTimes() {
		return watchTimes;
	}
	public void setWatchTimes(Integer watchTimes) {
		this.watchTimes = watchTimes;
	}
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	public Integer getEquipCnt() {
		return equipCnt;
	}
	public void setEquipCnt(Integer equipCnt) {
		this.equipCnt = equipCnt;
	}
	public Integer getBetCnt() {
		return betCnt;
	}
	public void setBetCnt(Integer betCnt) {
		this.betCnt = betCnt;
	}
	public Integer getBetTimes() {
		return betTimes;
	}
	public void setBetTimes(Integer betTimes) {
		this.betTimes = betTimes;
	}
	public Integer getBetCost() {
		return betCost;
	}
	public void setBetCost(Integer betCost) {
		this.betCost = betCost;
	}
	public Integer getBetGain() {
		return betGain;
	}
	public void setBetGain(Integer betGain) {
		this.betGain = betGain;
	}
	public Integer getGroupBetCnt() {
		return groupBetCnt;
	}
	public void setGroupBetCnt(Integer groupBetCnt) {
		this.groupBetCnt = groupBetCnt;
	}
	public Integer getGroupBetTimes() {
		return groupBetTimes;
	}
	public void setGroupBetTimes(Integer groupBetTimes) {
		this.groupBetTimes = groupBetTimes;
	}
	public Integer getFinalsBetCnt() {
		return finalsBetCnt;
	}
	public void setFinalsBetCnt(Integer finalsBetCnt) {
		this.finalsBetCnt = finalsBetCnt;
	}
	public Integer getFinalsBetTimes() {
		return finalsBetTimes;
	}
	public void setFinalsBetTimes(Integer finalsBetTimes) {
		this.finalsBetTimes = finalsBetTimes;
	}
	public Integer getActorid() {
		return actorid;
	}
	public void setActorid(Integer actorid) {
		this.actorid = actorid;
	}
	public String getActorname() {
		return actorname;
	}
	public void setActorname(String actorname) {
		this.actorname = actorname;
	}
	public Integer getJoinTimes() {
		return joinTimes;
	}
	public void setJoinTimes(Integer joinTimes) {
		this.joinTimes = joinTimes;
	}
	public Integer getWinTimes() {
		return winTimes;
	}
	public void setWinTimes(Integer winTimes) {
		this.winTimes = winTimes;
	}
	public Double getWinRate() {
		return winRate;
	}
	public void setWinRate(Double winRate) {
		this.winRate = winRate;
	}
	public Integer getLsTimes() {
		return lsTimes;
	}
	public void setLsTimes(Integer lsTimes) {
		this.lsTimes = lsTimes;
	}
	public Integer getLsCnt() {
		return lsCnt;
	}
	public void setLsCnt(Integer lsCnt) {
		this.lsCnt = lsCnt;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getRaking() {
		return raking;
	}
	public void setRaking(String raking) {
		this.raking = raking;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getJobs() {
		return jobs;
	}
	public void setJobs(String jobs) {
		this.jobs = jobs;
	}
	
	
	
	
	
	
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
