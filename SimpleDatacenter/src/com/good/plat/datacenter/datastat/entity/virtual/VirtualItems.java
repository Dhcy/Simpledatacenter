package com.good.plat.datacenter.datastat.entity.virtual;

import java.io.Serializable;
import java.util.List;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * @ClassName: VirtualItems
 * @Description: 消费点
 * @author dmw
 * @date 2016年3月14日 下午9:48:37
 */
public class VirtualItems extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public VirtualItems() {
	}

	//购买数量
	private Long currencyPurchase;
	
	//虚拟币总值
	private Long currencySum;
	
	//消耗数量
	private Long numberOfExpense;
	
	//购买人数
	private Long spendingAccounts;
	
	private String itemid;
	private String itemName;
	private Integer actorLevel;
	private Double rate1;
	private Double rate2;
	private Double rate3;
	private Double rate4;
	
	public Long getCurrencyPurchase() {
		return currencyPurchase;
	}

	public void setCurrencyPurchase(Long currencyPurchase) {
		this.currencyPurchase = currencyPurchase;
	}

	public Long getCurrencySum() {
		return currencySum;
	}

	public void setCurrencySum(Long currencySum) {
		this.currencySum = currencySum;
	}

	public Long getNumberOfExpense() {
		return numberOfExpense;
	}

	public void setNumberOfExpense(Long numberOfExpense) {
		this.numberOfExpense = numberOfExpense;
	}

	public Long getSpendingAccounts() {
		return spendingAccounts;
	}

	public void setSpendingAccounts(Long spendingAccounts) {
		this.spendingAccounts = spendingAccounts;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getActorLevel() {
		return actorLevel;
	}

	public void setActorLevel(Integer actorLevel) {
		this.actorLevel = actorLevel;
	}

	public Double getRate1() {
		return rate1;
	}

	public void setRate1(Double rate1) {
		this.rate1 = rate1;
	}

	public Double getRate2() {
		return rate2;
	}

	public void setRate2(Double rate2) {
		this.rate2 = rate2;
	}

	public Double getRate3() {
		return rate3;
	}

	public void setRate3(Double rate3) {
		this.rate3 = rate3;
	}

	public Double getRate4() {
		return rate4;
	}

	public void setRate4(Double rate4) {
		this.rate4 = rate4;
	}
	
}
