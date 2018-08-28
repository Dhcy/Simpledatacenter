package com.good.plat.datacenter.independ.entity.fwindex;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 军团Boss统计
 * @ClassName: FWJTBoss
 * @Description: TODO
 * @author hwj
 * @date 2017-7-27 下午03:55:53
 */
public class FWJTBoss extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer jtId;//军团id
	private String jtName;//军团名称
	private Integer totalJoinNum;//总参加人数
	private String refreshTime;//刷新时间
	private String bossName;//boss名称
	private Integer bossLevel;//boss等级
	private Integer killTime;//击杀时间
	private String lsName;//最后击杀玩家名
	private Integer ranking;//伤害排名
	private String actorName;//玩家名
	private Integer hurt;//伤害点
	private String award;//奖励
	private Integer refreshNum;//刷新次数
	private Integer killNum;//击杀次数
	private Integer noKillNum;//未击杀次数
	private Integer fKillTime;//当天最快击杀时间（单位：秒）
	private Integer avgKillTime;//平均击杀时间（单位：秒）
	private String currentFKillTime;//当天最快击杀时间
	private String currentAvgKillTime;//当天平均击杀时间
	private String jtKillTime;//军团击杀时间
	public Integer getJtId() {
		return jtId;
	}
	public void setJtId(Integer jtId) {
		this.jtId = jtId;
	}
	public String getJtName() {
		return jtName;
	}
	public void setJtName(String jtName) {
		this.jtName = jtName;
	}
	public Integer getTotalJoinNum() {
		return totalJoinNum;
	}
	public void setTotalJoinNum(Integer totalJoinNum) {
		this.totalJoinNum = totalJoinNum;
	}
	public String getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(String refreshTime) {
		this.refreshTime = refreshTime;
	}
	public String getBossName() {
		return bossName;
	}
	public void setBossName(String bossName) {
		this.bossName = bossName;
	}
	public Integer getBossLevel() {
		return bossLevel;
	}
	public void setBossLevel(Integer bossLevel) {
		this.bossLevel = bossLevel;
	}
	public Integer getKillTime() {
		return killTime;
	}
	public void setKillTime(Integer killTime) {
		this.killTime = killTime;
	}
	public String getLsName() {
		return lsName;
	}
	public void setLsName(String lsName) {
		this.lsName = lsName;
	}
	public Integer getRanking() {
		return ranking;
	}
	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}
	public String getActorName() {
		return actorName;
	}
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
	public Integer getHurt() {
		return hurt;
	}
	public void setHurt(Integer hurt) {
		this.hurt = hurt;
	}
	public String getAward() {
		return award;
	}
	public void setAward(String award) {
		this.award = award;
	}
	public Integer getRefreshNum() {
		return refreshNum;
	}
	public void setRefreshNum(Integer refreshNum) {
		this.refreshNum = refreshNum;
	}
	public Integer getKillNum() {
		return killNum;
	}
	public void setKillNum(Integer killNum) {
		this.killNum = killNum;
	}
	public Integer getNoKillNum() {
		return noKillNum;
	}
	public void setNoKillNum(Integer noKillNum) {
		this.noKillNum = noKillNum;
	}
	public Integer getfKillTime() {
		return fKillTime;
	}
	public void setfKillTime(Integer fKillTime) {
		this.fKillTime = fKillTime;
	}
	public Integer getAvgKillTime() {
		return avgKillTime;
	}
	public void setAvgKillTime(Integer avgKillTime) {
		this.avgKillTime = avgKillTime;
	}
	public String getCurrentFKillTime() {
		return currentFKillTime;
	}
	public void setCurrentFKillTime(String currentFKillTime) {
		this.currentFKillTime = currentFKillTime;
	}
	public String getCurrentAvgKillTime() {
		return currentAvgKillTime;
	}
	public void setCurrentAvgKillTime(String currentAvgKillTime) {
		this.currentAvgKillTime = currentAvgKillTime;
	}
	public String getJtKillTime() {
		return jtKillTime;
	}
	public void setJtKillTime(String jtKillTime) {
		this.jtKillTime = jtKillTime;
	}
	
	
	
}
