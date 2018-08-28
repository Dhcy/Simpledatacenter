package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 成就
 * @ClassName: Achieve
 * @Description: TODO
 * @author hwj
 * @date 2017-2-23 上午09:59:31
 */
public class Achieve extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 成就
	 */
	//成就id
	private Integer achieveId;
	//成就名称
	private String achieveName;
	//完成人数
	private Integer usercnt;
	//完成率
	private Double finish_rate;
	//成就总数
	private Integer total_cnt;
	//人均成就总数
	private Double avg_achieve_cnt;
	//职业
	private String jobs;
	
	public Integer getAchieveId() {
		return achieveId;
	}
	public void setAchieveId(Integer achieveId) {
		this.achieveId = achieveId;
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
	public Integer getTotal_cnt() {
		return total_cnt;
	}
	public void setTotal_cnt(Integer totalCnt) {
		total_cnt = totalCnt;
	}
	public Double getAvg_achieve_cnt() {
		return avg_achieve_cnt;
	}
	public void setAvg_achieve_cnt(Double avgAchieveCnt) {
		avg_achieve_cnt = avgAchieveCnt;
	}
	public String getAchieveName() {
		return achieveName;
	}
	public void setAchieveName(String achieveName) {
		this.achieveName = achieveName;
	}
	public String getJobs() {
		return jobs;
	}
	public void setJobs(String jobs) {
		this.jobs = jobs;
	}
	
	
}
