package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 新手任务
 * @ClassName: Sj3NewActTaskStat
 * @Description: TODO
 * @author hwj
 * @date 2018-1-11 下午02:32:38
 */
public class Sj3NewActTaskStat extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String missionid;//任务ID
	private String mission;//任务名称
	private Integer usercnt;//完成人数
	private Double finish_rate;//完成率
	private Integer missioncount;//完成次数
	private Double avgMissionCount;//人均次数
	public String getMissionid() {
		return missionid;
	}
	public void setMissionid(String missionid) {
		this.missionid = missionid;
	}
	public String getMission() {
		return mission;
	}
	public void setMission(String mission) {
		this.mission = mission;
	}
	public Integer getUsercnt() {
		return usercnt;
	}
	public void setUsercnt(Integer usercnt) {
		this.usercnt = usercnt;
	}
	public Double getFinish_rate() {
		return finish_rate;
	}
	public void setFinish_rate(Double finishRate) {
		finish_rate = finishRate;
	}
	public Integer getMissioncount() {
		return missioncount;
	}
	public void setMissioncount(Integer missioncount) {
		this.missioncount = missioncount;
	}
	public Double getAvgMissionCount() {
		return avgMissionCount;
	}
	public void setAvgMissionCount(Double avgMissionCount) {
		this.avgMissionCount = avgMissionCount;
	}
	
	

}
