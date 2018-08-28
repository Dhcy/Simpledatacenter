package com.good.plat.datacenter.datastat.entity.revenue;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

public class PayRetention extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	// 留存率
	private Double day1;
	// 次日留存率
	private Double day2;
	// 留存率
	private Double day3;
	// 留存率
	private Double day4;
	// 留存率
	private Double day5;
	// 留存率
	private Double day6;

	// 7日留存率
	private Double day7;
	// 留存率
	private Double day14;
	// 30日留存率
	private Double day30;

	// 留存账户数
	private Integer accounts;

	public Double getDay1() {
		return day1;
	}

	public void setDay1(Double day1) {
		this.day1 = day1;
	}

	public Double getDay2() {
		return day2;
	}

	public void setDay2(Double day2) {
		this.day2 = day2;
	}

	public Double getDay3() {
		return day3;
	}

	public void setDay3(Double day3) {
		this.day3 = day3;
	}

	public Double getDay4() {
		return day4;
	}

	public void setDay4(Double day4) {
		this.day4 = day4;
	}

	public Double getDay5() {
		return day5;
	}

	public void setDay5(Double day5) {
		this.day5 = day5;
	}

	public Double getDay6() {
		return day6;
	}

	public void setDay6(Double day6) {
		this.day6 = day6;
	}

	public Double getDay7() {
		return day7;
	}

	public void setDay7(Double day7) {
		this.day7 = day7;
	}

	public Double getDay14() {
		return day14;
	}

	public void setDay14(Double day14) {
		this.day14 = day14;
	}

	public Double getDay30() {
		return day30;
	}

	public void setDay30(Double day30) {
		this.day30 = day30;
	}

	public Integer getAccounts() {
		return accounts;
	}

	public void setAccounts(Integer accounts) {
		this.accounts = accounts;
	}
	
	

			
}
