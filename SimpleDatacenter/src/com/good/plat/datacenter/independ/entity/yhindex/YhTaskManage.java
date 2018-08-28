package com.good.plat.datacenter.independ.entity.yhindex;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 任务管理
 * @ClassName: YhCourseStat
 * @Description: TODO
 * @author hwj
 * @date 2017-12-27 下午03:37:45
 */
public class YhTaskManage extends BaseEntity implements Serializable{

	/**
	 * 课程
	 */
	private static final long serialVersionUID = 1L;
	//课程id
	private Integer courseid;
	//玩家数
	private Integer count;
	/**
	 * 系统参与度
	 */
	//系统名称
	private String sysname;
	//参与玩家
	private Integer joinUser;
	//参与玩家占比
	private Double joinUserRate;
	//平均参与人次
	private Double avgJoinTimes;
	//平均首次参与等级
	private Double avgFirstJoinLvl;
	public Integer getCourseid() {
		return courseid;
	}
	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getSysname() {
		return sysname;
	}
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	public Integer getJoinUser() {
		return joinUser;
	}
	public void setJoinUser(Integer joinUser) {
		this.joinUser = joinUser;
	}
	public Double getJoinUserRate() {
		return joinUserRate;
	}
	public void setJoinUserRate(Double joinUserRate) {
		this.joinUserRate = joinUserRate;
	}
	public Double getAvgJoinTimes() {
		return avgJoinTimes;
	}
	public void setAvgJoinTimes(Double avgJoinTimes) {
		this.avgJoinTimes = avgJoinTimes;
	}
	public Double getAvgFirstJoinLvl() {
		return avgFirstJoinLvl;
	}
	public void setAvgFirstJoinLvl(Double avgFirstJoinLvl) {
		this.avgFirstJoinLvl = avgFirstJoinLvl;
	}
	
	

}
