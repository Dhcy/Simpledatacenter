package com.good.plat.datacenter.independ.entity.hie.index;

import java.io.Serializable;

public class HieSingleRecharge  implements Serializable{
	
	private Double money;//金额
	private Integer persons;//充值人数
	private Double personsaf;//人数占比
	private Integer rechargeCount;//充值次数
	private Double countaf;//次数占比
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getPersons() {
		return persons;
	}
	public void setPersons(Integer persons) {
		this.persons = persons;
	}
	public Double getPersonsaf() {
		return personsaf;
	}
	public void setPersonsaf(Double personsaf) {
		this.personsaf = personsaf;
	}
	public Integer getRechargeCount() {
		return rechargeCount;
	}
	public void setRechargeCount(Integer rechargeCount) {
		this.rechargeCount = rechargeCount;
	}
	public Double getCountaf() {
		return countaf;
	}
	public void setCountaf(Double countaf) {
		this.countaf = countaf;
	}

	

}
