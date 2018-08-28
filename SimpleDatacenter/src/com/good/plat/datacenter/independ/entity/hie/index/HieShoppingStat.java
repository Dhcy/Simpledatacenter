package com.good.plat.datacenter.independ.entity.hie.index;

import java.io.Serializable;

public class HieShoppingStat implements Serializable {
	private String name;// 商品名称
	private Integer totalsales;// 销售总额
	private Integer salesnum;// 销售数量
	private Integer personnum;// 购买人数
	private double salesaf;// 销售占比
	private double peraf;// 人数占比

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalsales() {
		return totalsales;
	}

	public void setTotalsales(Integer totalsales) {
		this.totalsales = totalsales;
	}

	public Integer getSalesnum() {
		return salesnum;
	}

	public void setSalesnum(Integer salesnum) {
		this.salesnum = salesnum;
	}

	public Integer getPersonnum() {
		return personnum;
	}

	public void setPersonnum(Integer personnum) {
		this.personnum = personnum;
	}

	public double getSalesaf() {
		return salesaf;
	}

	public void setSalesaf(double salesaf) {
		this.salesaf = salesaf;
	}

	public double getPeraf() {
		return peraf;
	}

	public void setPeraf(double peraf) {
		this.peraf = peraf;
	}

}
