package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

public class Stage extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//关卡名称
	private String stageName;
	//进入关卡数
	private Integer entercnt;
	//通关玩家数
	private Integer succdcnt;
	//平均通关用时
	private Double avg_succ_time;
	//失败玩家
	private Integer failcnt;
	//关卡id
	private Integer stageId;
	private Integer stageType;//关卡类型(1:任务关卡,2：非任务关卡)
	private String stageTypeStr;
	//职业
	private String jobs;
	//接取主线账号数
	private Integer enter_uid_cnt;
	//主线完成账号数
	private Integer succ_uid_cnt;
	//主线未完成账号数
	private Integer fail_uid_cnt;
	/** 关卡统计*/
	//开启角色数
	private Integer opencnt;
	//中途退出角色数
	private Integer exitcnt;
	//通关总用时
	private Integer sumtime;
	//通关总次数
	private Integer succtimes;
	//开启关卡账号数
	private Integer openuidcnt;
	//进入关卡账号数
	private Integer enteruidcnt;
	//通关账号数
	private Integer succduidcnt;
	//未通关账号数
	private Integer failuidcnt;
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public Integer getEntercnt() {
		return entercnt;
	}
	public void setEntercnt(Integer entercnt) {
		this.entercnt = entercnt;
	}
	public Integer getSuccdcnt() {
		return succdcnt;
	}
	public void setSuccdcnt(Integer succdcnt) {
		this.succdcnt = succdcnt;
	}
	public Double getAvg_succ_time() {
		return avg_succ_time;
	}
	public void setAvg_succ_time(Double avgSuccTime) {
		avg_succ_time = avgSuccTime;
	}
	public Integer getFailcnt() {
		return failcnt;
	}
	public void setFailcnt(Integer failcnt) {
		this.failcnt = failcnt;
	}
	public Integer getStageId() {
		return stageId;
	}
	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}
	public Integer getStageType() {
		return stageType;
	}
	public void setStageType(Integer stageType) {
		this.stageType = stageType;
	}
	public String getStageTypeStr() {
		return stageTypeStr;
	}
	public void setStageTypeStr(String stageTypeStr) {
		this.stageTypeStr = stageTypeStr;
	}
	public String getJobs() {
		return jobs;
	}
	public void setJobs(String jobs) {
		this.jobs = jobs;
	}
	public Integer getEnter_uid_cnt() {
		return enter_uid_cnt;
	}
	public void setEnter_uid_cnt(Integer enterUidCnt) {
		enter_uid_cnt = enterUidCnt;
	}
	public Integer getSucc_uid_cnt() {
		return succ_uid_cnt;
	}
	public void setSucc_uid_cnt(Integer succUidCnt) {
		succ_uid_cnt = succUidCnt;
	}
	public Integer getFail_uid_cnt() {
		return fail_uid_cnt;
	}
	public void setFail_uid_cnt(Integer failUidCnt) {
		fail_uid_cnt = failUidCnt;
	}
	public Integer getOpencnt() {
		return opencnt;
	}
	public void setOpencnt(Integer opencnt) {
		this.opencnt = opencnt;
	}
	public Integer getExitcnt() {
		return exitcnt;
	}
	public void setExitcnt(Integer exitcnt) {
		this.exitcnt = exitcnt;
	}
	public Integer getSumtime() {
		return sumtime;
	}
	public void setSumtime(Integer sumtime) {
		this.sumtime = sumtime;
	}
	public Integer getSucctimes() {
		return succtimes;
	}
	public void setSucctimes(Integer succtimes) {
		this.succtimes = succtimes;
	}
	public Integer getOpenuidcnt() {
		return openuidcnt;
	}
	public void setOpenuidcnt(Integer openuidcnt) {
		this.openuidcnt = openuidcnt;
	}
	public Integer getEnteruidcnt() {
		return enteruidcnt;
	}
	public void setEnteruidcnt(Integer enteruidcnt) {
		this.enteruidcnt = enteruidcnt;
	}
	public Integer getSuccduidcnt() {
		return succduidcnt;
	}
	public void setSuccduidcnt(Integer succduidcnt) {
		this.succduidcnt = succduidcnt;
	}
	public Integer getFailuidcnt() {
		return failuidcnt;
	}
	public void setFailuidcnt(Integer failuidcnt) {
		this.failuidcnt = failuidcnt;
	}
	
	
	
	
	
	
	

}
