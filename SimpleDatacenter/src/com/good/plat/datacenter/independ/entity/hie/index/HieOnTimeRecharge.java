package com.good.plat.datacenter.independ.entity.hie.index;

import java.io.Serializable;
import java.math.BigDecimal;

public class HieOnTimeRecharge implements Serializable {
	private String date;//时间段
	private Integer persons;//充值人数
	private Double personsaf;//人数占比
	private Double money;//充值金额
	private Double moneyaf;//充值占比

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getPersons() {
		return persons;
	}

	public void setPersons(Integer persons) {
		this.persons = persons;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getPersonsaf() {
		return personsaf;
	}

	public void setPersonsaf(Double personsaf) {
		this.personsaf = personsaf;
	}

	public Double getMoneyaf() {
		return moneyaf;
	}

	public void setMoneyaf(Double moneyaf) {
		this.moneyaf = moneyaf;
	}

}
