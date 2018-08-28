package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 官职分布
 * @ClassName: Officer
 * @Description: TODO
 * @author hwj
 * @date 2017-2-28 下午05:05:52
 */
public class Officer extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	//官职名称
	private String officerName;
	//黎明圣火人数
	private Integer lmshCnt;
	//幽冥之翼人数
	private Integer ymzyCnt;
	//永夜明光人数
	private Integer yymgCnt;
	//总势力人数
	private Integer totalCnt;
	//黎明圣火占比
	private Double lmshRate;
	//幽冥之翼占比
	private Double ymzyRate;
	//永夜明光占比
	private Double yymgRate;
	//职业
	private String jobs;
	public String getOfficerName() {
		return officerName;
	}
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	public Integer getLmshCnt() {
		return lmshCnt;
	}
	public void setLmshCnt(Integer lmshCnt) {
		this.lmshCnt = lmshCnt;
	}
	public Integer getYmzyCnt() {
		return ymzyCnt;
	}
	public void setYmzyCnt(Integer ymzyCnt) {
		this.ymzyCnt = ymzyCnt;
	}
	public Integer getYymgCnt() {
		return yymgCnt;
	}
	public void setYymgCnt(Integer yymgCnt) {
		this.yymgCnt = yymgCnt;
	}
	public Integer getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(Integer totalCnt) {
		this.totalCnt = totalCnt;
	}
	public Double getLmshRate() {
		return lmshRate;
	}
	public void setLmshRate(Double lmshRate) {
		this.lmshRate = lmshRate;
	}
	public Double getYmzyRate() {
		return ymzyRate;
	}
	public void setYmzyRate(Double ymzyRate) {
		this.ymzyRate = ymzyRate;
	}
	public Double getYymgRate() {
		return yymgRate;
	}
	public void setYymgRate(Double yymgRate) {
		this.yymgRate = yymgRate;
	}
	public String getJobs() {
		return jobs;
	}
	public void setJobs(String jobs) {
		this.jobs = jobs;
	}
	
	
	
	
	

}
