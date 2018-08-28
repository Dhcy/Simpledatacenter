package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 圣域（地图）统计
 * @ClassName: SyMapSummary
 * @Description: TODO
 * @author hwj
 * @date 2017-9-21 下午02:12:42
 */
public class SyMapSummary extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer openCombatCnt;//开启战斗场次
	private Integer avgCombatTime;//平均战斗用时
	private String avgCombatTimeStr;//平均战斗时间
	//星级
	private Integer star;
	//星级
	private String starLevel;
	//参与玩家数	
	private Integer actjoincnt;
	//抢夺玩家数
	private Integer actlootcnt;
	//抢夺次数
	private Integer lootcnt;
	//离开玩家数
	private Integer actleavecnt;
	//失败玩家数
	private Integer actfailcnt;
	//中途退出玩家数
	private Integer actexitcnt;
	//已被占领海岛数量
	private Integer islandcnt;
	//职业
	private String jobs;
	public Integer getOpenCombatCnt() {
		return openCombatCnt;
	}
	public void setOpenCombatCnt(Integer openCombatCnt) {
		this.openCombatCnt = openCombatCnt;
	}
	public Integer getAvgCombatTime() {
		return avgCombatTime;
	}
	public void setAvgCombatTime(Integer avgCombatTime) {
		this.avgCombatTime = avgCombatTime;
	}
	public String getAvgCombatTimeStr() {
		return avgCombatTimeStr;
	}
	public void setAvgCombatTimeStr(String avgCombatTimeStr) {
		this.avgCombatTimeStr = avgCombatTimeStr;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public String getStarLevel() {
		return starLevel;
	}
	public void setStarLevel(String starLevel) {
		this.starLevel = starLevel;
	}
	public Integer getActjoincnt() {
		return actjoincnt;
	}
	public void setActjoincnt(Integer actjoincnt) {
		this.actjoincnt = actjoincnt;
	}
	public Integer getActlootcnt() {
		return actlootcnt;
	}
	public void setActlootcnt(Integer actlootcnt) {
		this.actlootcnt = actlootcnt;
	}
	public Integer getLootcnt() {
		return lootcnt;
	}
	public void setLootcnt(Integer lootcnt) {
		this.lootcnt = lootcnt;
	}
	public Integer getActleavecnt() {
		return actleavecnt;
	}
	public void setActleavecnt(Integer actleavecnt) {
		this.actleavecnt = actleavecnt;
	}
	public Integer getActfailcnt() {
		return actfailcnt;
	}
	public void setActfailcnt(Integer actfailcnt) {
		this.actfailcnt = actfailcnt;
	}
	public Integer getActexitcnt() {
		return actexitcnt;
	}
	public void setActexitcnt(Integer actexitcnt) {
		this.actexitcnt = actexitcnt;
	}
	public Integer getIslandcnt() {
		return islandcnt;
	}
	public void setIslandcnt(Integer islandcnt) {
		this.islandcnt = islandcnt;
	}
	public String getJobs() {
		return jobs;
	}
	public void setJobs(String jobs) {
		this.jobs = jobs;
	}
	
	
	
	
	
	
	

}
