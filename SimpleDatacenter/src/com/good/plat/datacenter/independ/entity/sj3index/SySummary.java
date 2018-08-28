package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 圣域统计
 * @ClassName: SySummary
 * @Description: TODO
 * @author hwj
 * @date 2017-3-6 下午06:36:39
 */
public class SySummary extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 封魔团、爵位战*/
	//圣域名称
	private String syName;
	//开启次数
	private Integer startCnt;
	//通关总用时
	private Long totalTime;
	//通关战斗数/完成次数
	private Integer succtCnt;
	//玩家数
	private Integer userCnt;
	//通关玩家数
	private Integer usuccCnt;
	//失败玩家数
	private Integer ufailCnt;
	//中途退出玩家数
	private Integer uexitCnt;
	//人均开启战斗数
	private Double avgStartCnt;
	
	/**猎魔记事*/
	//中途退出次数
	private Integer exitCnt;
	//掠夺次数
	private Integer robCnt;
	//成功猎夺次数
	private Integer succrobCnt;
	//开启人数
	private Integer startUserCnt;
	//人均开启次数
	private Double avgStartTimes;
	
	/**魔神降临*/
	//付费复活总次数
	private Integer reliveCnt;
	//击杀玩家人次
	private Integer killCnt;
	//平均用时
	private Double avgTime;
	private Integer bossCnt;
	public String getSyName() {
		return syName;
	}
	public void setSyName(String syName) {
		this.syName = syName;
	}
	public Integer getStartCnt() {
		return startCnt;
	}
	public void setStartCnt(Integer startCnt) {
		this.startCnt = startCnt;
	}
	public Long getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}
	public Integer getSucctCnt() {
		return succtCnt;
	}
	public void setSucctCnt(Integer succtCnt) {
		this.succtCnt = succtCnt;
	}
	public Integer getUserCnt() {
		return userCnt;
	}
	public void setUserCnt(Integer userCnt) {
		this.userCnt = userCnt;
	}
	public Integer getUsuccCnt() {
		return usuccCnt;
	}
	public void setUsuccCnt(Integer usuccCnt) {
		this.usuccCnt = usuccCnt;
	}
	public Integer getUfailCnt() {
		return ufailCnt;
	}
	public void setUfailCnt(Integer ufailCnt) {
		this.ufailCnt = ufailCnt;
	}
	public Integer getUexitCnt() {
		return uexitCnt;
	}
	public void setUexitCnt(Integer uexitCnt) {
		this.uexitCnt = uexitCnt;
	}
	public Integer getExitCnt() {
		return exitCnt;
	}
	public void setExitCnt(Integer exitCnt) {
		this.exitCnt = exitCnt;
	}
	public Integer getRobCnt() {
		return robCnt;
	}
	public void setRobCnt(Integer robCnt) {
		this.robCnt = robCnt;
	}
	public Integer getSuccrobCnt() {
		return succrobCnt;
	}
	public void setSuccrobCnt(Integer succrobCnt) {
		this.succrobCnt = succrobCnt;
	}
	public Integer getReliveCnt() {
		return reliveCnt;
	}
	public void setReliveCnt(Integer reliveCnt) {
		this.reliveCnt = reliveCnt;
	}
	public Integer getKillCnt() {
		return killCnt;
	}
	public void setKillCnt(Integer killCnt) {
		this.killCnt = killCnt;
	}
	public Double getAvgTime() {
		return avgTime;
	}
	public void setAvgTime(Double avgTime) {
		this.avgTime = avgTime;
	}
	public Integer getBossCnt() {
		return bossCnt;
	}
	public void setBossCnt(Integer bossCnt) {
		this.bossCnt = bossCnt;
	}
	public Double getAvgStartCnt() {
		return avgStartCnt;
	}
	public void setAvgStartCnt(Double avgStartCnt) {
		this.avgStartCnt = avgStartCnt;
	}
	public Integer getStartUserCnt() {
		return startUserCnt;
	}
	public void setStartUserCnt(Integer startUserCnt) {
		this.startUserCnt = startUserCnt;
	}
	public Double getAvgStartTimes() {
		return avgStartTimes;
	}
	public void setAvgStartTimes(Double avgStartTimes) {
		this.avgStartTimes = avgStartTimes;
	}
	
	
	
	
	
	
	
	
}
