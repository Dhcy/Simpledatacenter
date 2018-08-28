package com.good.plat.datacenter.datastat.entity.financedata;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 财务数据
 * @ClassName: FinanceGameUser
 * @Description: TODO
 * @author hwj
 * @date 2018-3-2 下午04:27:22
 */
public class FinanceData extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 109374802076032993L;
	//新增用户数
	private Integer newUser;
	//单价
	private Double price;
	//金额
	private Double money;
	public Integer getNewUser() {
		return newUser;
	}
	public void setNewUser(Integer newUser) {
		this.newUser = newUser;
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
	

}
