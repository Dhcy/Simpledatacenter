package com.good.plat.datacenter.datastat.entity.virtual;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * @ClassName: VirtualCurrency
 * @Description: 虚拟币
 * @author dmw
 * @date 2016年3月14日 下午9:46:38
 */
public class VirtualCurrency extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public VirtualCurrency() {
	}
	
	//购入
	private Long purchase;
	
	//赠予
	private Long reward;
	
	//消费
	private Long expense;
	
	
	private Long purchaseSUM;
	
	private Long rewardSUM;
	
	private Long expenseSUM;
	
	//消耗次数
	private Integer spendingTimes;
	
	//消耗人数
	private Integer spendingAccounts;
	
	//累计留存
	private Long currencyBalance;

	//角色等级
	private Integer actorlevel;
	
	public Long getPurchase() {
		return purchase;
	}

	public void setPurchase(Long purchase) {
		this.purchase = purchase;
	}

	public Long getReward() {
		return reward;
	}

	public void setReward(Long reward) {
		this.reward = reward;
	}

	public Long getExpense() {
		return expense;
	}

	public void setExpense(Long expense) {
		this.expense = expense;
	}

	public Long getPurchaseSUM() {
		return purchaseSUM;
	}

	public void setPurchaseSUM(Long purchaseSUM) {
		this.purchaseSUM = purchaseSUM;
	}

	public Long getRewardSUM() {
		return rewardSUM;
	}

	public void setRewardSUM(Long rewardSUM) {
		this.rewardSUM = rewardSUM;
	}

	public Long getExpenseSUM() {
		return expenseSUM;
	}

	public void setExpenseSUM(Long expenseSUM) {
		this.expenseSUM = expenseSUM;
	}

	public Integer getSpendingTimes() {
		return spendingTimes;
	}

	public void setSpendingTimes(Integer spendingTimes) {
		this.spendingTimes = spendingTimes;
	}

	public Integer getSpendingAccounts() {
		return spendingAccounts;
	}

	public void setSpendingAccounts(Integer spendingAccounts) {
		this.spendingAccounts = spendingAccounts;
	}

	public Long getCurrencyBalance() {
		return currencyBalance;
	}

	public void setCurrencyBalance(Long currencyBalance) {
		this.currencyBalance = currencyBalance;
	}

	public Integer getActorlevel() {
		return actorlevel;
	}

	public void setActorlevel(Integer actorlevel) {
		this.actorlevel = actorlevel;
	}
	
	
}
