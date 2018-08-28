package com.good.plat.datacenter.datastat.entity.xgfinance;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 香港财务费用
 * @ClassName: HongKongfinanceCost
 * @Description: TODO
 * @author hwj
 * @date 2017-8-12 上午10:40:26
 */
public class FinanceCost extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//用户数
	private Integer userNum;
	//顾客名称
	private String customerName;
	//单价
	private Double price;
	//金额
	private Double money;
	//汇兑率
	private Double rate;
	//折算
	private Double dollar;
	public Integer getUserNum() {
		return userNum;
	}
	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getDollar() {
		return dollar;
	}
	public void setDollar(Double dollar) {
		this.dollar = dollar;
	}
	

}
