package com.good.plat.datacenter.independ.entity.hie.index;

import java.io.Serializable;

public class HieMoneyStat implements Serializable {
	private String approach;// 类型
	private Integer moneynum;// 货币总数
	private Integer num;// 次数
	private Integer personnum;// 人数
	private double moneypre;// 货币占比
	private double personpre;// 人数占比

	public String getApproach() {
		return approach;
	}

	public void setApproach(String approach) {
		this.approach = approach;
	}

	public Integer getMoneynum() {
		return moneynum;
	}

	public void setMoneynum(Integer moneynum) {
		this.moneynum = moneynum;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getPersonnum() {
		return personnum;
	}

	public void setPersonnum(Integer personnum) {
		this.personnum = personnum;
	}

	public double getMoneypre() {
		return moneypre;
	}

	public void setMoneypre(double moneypre) {
		this.moneypre = moneypre;
	}

	public double getPersonpre() {
		return personpre;
	}

	public void setPersonpre(double personpre) {
		this.personpre = personpre;
	}

}
