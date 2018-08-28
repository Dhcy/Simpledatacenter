package com.good.plat.datacenter.independ.entity.fwindex;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

public class FWStageWeapon extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String wpName;
	private Integer stageId;
	private String stageName;
	private Integer amount;
	
	public String getWpName() {
		return wpName;
	}
	public void setWpName(String wpName) {
		this.wpName = wpName;
	}
	public Integer getStageId() {
		return stageId;
	}
	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}
	
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	

}
