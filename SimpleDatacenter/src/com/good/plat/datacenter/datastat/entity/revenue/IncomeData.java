package com.good.plat.datacenter.datastat.entity.revenue;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * @ClassName: IncomeData
 * @Description: 收入数据
 * @author dmw
 * @date 2016年3月14日 下午5:30:13
 */
public class IncomeData extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public IncomeData() {
	}
	
	//收入金额
	private Double incomes;
	
	//充值次数
	private Integer payCounts;
	
	//充值人数
	private Integer payAccounts;
	
	//标题,查询类型
	//private String statType;
	
	//百分比
	//private Double rate;

	public Double getIncomes() {
		return incomes;
	}

	public void setIncomes(Double incomes) {
		this.incomes = incomes;
	}

	public Integer getPayCounts() {
		return payCounts;
	}

	public void setPayCounts(Integer payCounts) {
		this.payCounts = payCounts;
	}

	public Integer getPayAccounts() {
		return payAccounts;
	}

	public void setPayAccounts(Integer payAccounts) {
		this.payAccounts = payAccounts;
	}

}
