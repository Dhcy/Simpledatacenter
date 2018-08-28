package com.good.plat.datacenter.datastat.entity.index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 数据对比
 * @ClassName: DataCompare
 * @Description: TODO
 * @author hwj
 * @date 2017-2-16 上午09:55:02
 */
public class DataCompare extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	public DataCompare() {
	}
	//设备数
	private Integer devices;
	//新增用户
	private Integer newPlayers;
	//账户数
	private Integer account;
	//新增付费玩家
	private Integer newPayUser;
	//总付费玩家
	private Integer totalPayUser;
	//收入金额
	private Double incomes;
	//充值次数
	private Integer payCounts;
	//acu
	private Integer acu;
	//pcu
	private Integer pcu;
	
	private Double value;
	private Double new_value;
	private String date;
	private String new_date;
	// 日付费率
	private Double payRate;
	//次日留存率
	private Double day1Rate;
	private Double acuRate;
	

	public Integer getDevices() {
		return devices;
	}
	public void setDevices(Integer devices) {
		this.devices = devices;
	}
	public Integer getNewPlayers() {
		return newPlayers;
	}
	public void setNewPlayers(Integer newPlayers) {
		this.newPlayers = newPlayers;
	}
	 
	public Integer getAccount() {
		return account;
	}
	public void setAccount(Integer account) {
		this.account = account;
	}
	public Integer getNewPayUser() {
		return newPayUser;
	}
	public void setNewPayUser(Integer newPayUser) {
		this.newPayUser = newPayUser;
	}
	public Integer getTotalPayUser() {
		return totalPayUser;
	}
	public void setTotalPayUser(Integer totalPayUser) {
		this.totalPayUser = totalPayUser;
	}
	public Double getIncomes() {
		return incomes;
	}
	public void setIncomes(Double incomes) {
		this.incomes = incomes;
	}
	public Integer getPayCounts() {
		return payCounts;
	}
	public void setPayCounts(Integer payCounts) {
		this.payCounts = payCounts;
	}
	public Integer getAcu() {
		return acu;
	}
	public void setAcu(Integer acu) {
		this.acu = acu;
	}
	public Integer getPcu() {
		return pcu;
	}
	public void setPcu(Integer pcu) {
		this.pcu = pcu;
	}
	public Double getNew_value() {
		return new_value;
	}
	public void setNew_value(Double newValue) {
		new_value = newValue;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNew_date() {
		return new_date;
	}
	public void setNew_date(String newDate) {
		new_date = newDate;
	}
	public Double getPayRate() {
		return payRate;
	}
	public void setPayRate(Double payRate) {
		this.payRate = payRate;
	}
	public Double getDay1Rate() {
		return day1Rate;
	}
	public void setDay1Rate(Double day1Rate) {
		this.day1Rate = day1Rate;
	}
	public Double getAcuRate() {
		return acuRate;
	}
	public void setAcuRate(Double acuRate) {
		this.acuRate = acuRate;
	}
	
	
	
	
	
	
	
	
	
	
	

}
