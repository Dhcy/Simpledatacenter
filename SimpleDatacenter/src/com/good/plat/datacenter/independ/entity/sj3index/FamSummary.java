package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 秘境统计
 * @ClassName: FamSummary
 * @Description: TODO
 * @author hwj
 * @date 2017-3-3 下午04:59:45
 */
public class FamSummary extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//秘境id
	private Integer mjId;
	private String mjName;//秘境名称
	private Integer enterCnt;//进入秘境人数
	private Integer succdCnt;//通关数、
	private Integer totalTime;//总用时
	private Integer failCnt;//失败次数
	private Integer exitCnt;//中途退出次数
	private Double avgTime;//平均通关用时 
	private Integer enterTimes;//进入秘境次数
	private Double avgEnterTimes;//人均进入秘境次数
	public String getMjName() {
		return mjName;
	}
	public void setMjName(String mjName) {
		this.mjName = mjName;
	}
	public Integer getEnterCnt() {
		return enterCnt;
	}
	public void setEnterCnt(Integer enterCnt) {
		this.enterCnt = enterCnt;
	}
	public Integer getSuccdCnt() {
		return succdCnt;
	}
	public void setSuccdCnt(Integer succdCnt) {
		this.succdCnt = succdCnt;
	}
	public Integer getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}
	public Integer getFailCnt() {
		return failCnt;
	}
	public void setFailCnt(Integer failCnt) {
		this.failCnt = failCnt;
	}
	public Integer getExitCnt() {
		return exitCnt;
	}
	public void setExitCnt(Integer exitCnt) {
		this.exitCnt = exitCnt;
	}
	public Double getAvgTime() {
		return avgTime;
	}
	public void setAvgTime(Double avgTime) {
		this.avgTime = avgTime;
	}
	public Integer getMjId() {
		return mjId;
	}
	public void setMjId(Integer mjId) {
		this.mjId = mjId;
	}
	public Integer getEnterTimes() {
		return enterTimes;
	}
	public void setEnterTimes(Integer enterTimes) {
		this.enterTimes = enterTimes;
	}
	public Double getAvgEnterTimes() {
		return avgEnterTimes;
	}
	public void setAvgEnterTimes(Double avgEnterTimes) {
		this.avgEnterTimes = avgEnterTimes;
	}
	
	
	

}
