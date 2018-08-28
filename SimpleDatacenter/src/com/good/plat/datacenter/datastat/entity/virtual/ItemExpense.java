package com.good.plat.datacenter.datastat.entity.virtual;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * @ClassName: ItemExpense
 * @Description: 消费喜好
 * @author dmw
 * @date 2016年3月14日 下午9:47:07
 */
public class ItemExpense extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public ItemExpense() {
	}
	
	//消费次数
	private Long numberOfSpending;
	
	//消费次数-百分比
	private Double numberOfSpendingPercentage;
	
	//消费额度
	private Long sumOfSpending;
	
	//消费额度-百分比
	private Double sumOfSpendingPercentage;

	public Long getNumberOfSpending() {
		return numberOfSpending;
	}

	public void setNumberOfSpending(Long numberOfSpending) {
		this.numberOfSpending = numberOfSpending;
	}

	public Double getNumberOfSpendingPercentage() {
		return numberOfSpendingPercentage;
	}

	public void setNumberOfSpendingPercentage(Double numberOfSpendingPercentage) {
		this.numberOfSpendingPercentage = numberOfSpendingPercentage;
	}

	public Long getSumOfSpending() {
		return sumOfSpending;
	}

	public void setSumOfSpending(Long sumOfSpending) {
		this.sumOfSpending = sumOfSpending;
	}

	public Double getSumOfSpendingPercentage() {
		return sumOfSpendingPercentage;
	}

	public void setSumOfSpendingPercentage(Double sumOfSpendingPercentage) {
		this.sumOfSpendingPercentage = sumOfSpendingPercentage;
	}
	

}
