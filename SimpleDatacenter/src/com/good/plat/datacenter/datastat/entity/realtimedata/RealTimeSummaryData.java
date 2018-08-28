package com.good.plat.datacenter.datastat.entity.realtimedata;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 实时——游戏概况
 * @ClassName: RealTimeSummaryData
 * @Description: TODO
 * @author hwj
 * @date 2017-12-11 下午03:10:59
 */
public class RealTimeSummaryData  extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//设备激活
	public Integer deviceActivation = 0;
	
	//活跃玩家
	private Integer actUser = 0;
	
	//付费玩家
	private Integer payUser = 0;
	
	//收入
	private Double income = 0d;

	public Integer getDeviceActivation() {
		return deviceActivation;
	}

	public void setDeviceActivation(Integer deviceActivation) {
		this.deviceActivation = deviceActivation;
	}


	public Integer getActUser() {
		return actUser;
	}

	public void setActUser(Integer actUser) {
		this.actUser = actUser;
	}

	public Integer getPayUser() {
		return payUser;
	}

	public void setPayUser(Integer payUser) {
		this.payUser = payUser;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}
	
	
}
