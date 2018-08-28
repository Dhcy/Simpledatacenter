package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 魔物统计
 * @ClassName: MwSummary
 * @Description: TODO
 * @author hwj
 * @date 2017-3-6 下午04:25:49
 */
public class MwSummary extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//魔物名称
	private String mwName;
	//总玩家数
	private Integer totalActor;
	//激活玩家数
	private Integer userCnt;
	//解锁(未激活玩家数)
	private Integer unUserCnt;
	//未激活玩家比例饿
	private Double unActiveRate;
	public String getMwName() {
		return mwName;
	}
	public void setMwName(String mwName) {
		this.mwName = mwName;
	}
	public Integer getTotalActor() {
		return totalActor;
	}
	public void setTotalActor(Integer totalActor) {
		this.totalActor = totalActor;
	}
	public Integer getUserCnt() {
		return userCnt;
	}
	public void setUserCnt(Integer userCnt) {
		this.userCnt = userCnt;
	}
	public Integer getUnUserCnt() {
		return unUserCnt;
	}
	public void setUnUserCnt(Integer unUserCnt) {
		this.unUserCnt = unUserCnt;
	}
	public Double getUnActiveRate() {
		return unActiveRate;
	}
	public void setUnActiveRate(Double unActiveRate) {
		this.unActiveRate = unActiveRate;
	}
	
	
	
	

}
