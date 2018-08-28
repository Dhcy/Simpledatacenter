package com.good.plat.datacenter.datastat.entity.revenue;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * @ClassName: Behavior
 * @Description: 付费习惯
 * @author dmw
 * @date 2016年3月14日 下午5:09:28
 */
public class Behavior extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public Behavior() {
	}
	
	//支付渠道id
	private Integer payChannel;
	
	//支付渠道
	private String payChannelName;
	
	//收入金额
	private Double incomes;
	
	//充值人次
	private Integer payUser;

	public Integer getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(Integer payChannel) {
		this.payChannel = payChannel;
	}

	public String getPayChannelName() {
		return payChannelName;
	}

	public void setPayChannelName(String payChannelName) {
		this.payChannelName = payChannelName;
	}

	public Double getIncomes() {
		return incomes;
	}

	public void setIncomes(Double incomes) {
		this.incomes = incomes;
	}

	public Integer getPayUser() {
		return payUser;
	}

	public void setPayUser(Integer payUser) {
		this.payUser = payUser;
	}
	
}
