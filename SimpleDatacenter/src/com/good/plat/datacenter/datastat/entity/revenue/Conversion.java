package com.good.plat.datacenter.datastat.entity.revenue;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * @ClassName: Conversion
 * @Description: 付费渗透
 * @author dmw
 * @date 2016年3月14日 下午5:29:18
 */
public class Conversion extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Conversion() {
	}
	
	//日期
	//private String statdate;
	
	//日付费率
	private Double dailyPayRate;
	
	//周付费率
	private Double weeklyPayRate;
	
	//月付费率
	private Double monthPayRate;
	//金额
	private Double money;
	//玩家等级
	private Integer actorLevel;
	//数量
	private Double quantity;
	//城市/地区
	private String city;
	
	private Double AVG;
	
	private Double dailyARPU;
	
	private Double monthARPU;
	
	private Double dailyARPPU;
	
	private Double monthARPPU;
	
	//流失率,百分比
	//private Double rate;
	
	//标题,查询类型
	//private String statType;

	public Double getDailyPayRate() {
		return dailyPayRate;
	}

	public void setDailyPayRate(Double dailyPayRate) {
		this.dailyPayRate = dailyPayRate;
	}

	public Double getWeeklyPayRate() {
		return weeklyPayRate;
	}

	public void setWeeklyPayRate(Double weeklyPayRate) {
		this.weeklyPayRate = weeklyPayRate;
	}

	public Double getMonthPayRate() {
		return monthPayRate;
	}

	public void setMonthPayRate(Double monthPayRate) {
		this.monthPayRate = monthPayRate;
	}

	public Double getAVG() {
		return AVG;
	}

	public void setAVG(Double aVG) {
		AVG = aVG;
	}

	public Double getDailyARPU() {
		return dailyARPU;
	}

	public void setDailyARPU(Double dailyARPU) {
		this.dailyARPU = dailyARPU;
	}

	public Double getMonthARPU() {
		return monthARPU;
	}

	public void setMonthARPU(Double monthARPU) {
		this.monthARPU = monthARPU;
	}

	public Double getDailyARPPU() {
		return dailyARPPU;
	}

	public void setDailyARPPU(Double dailyARPPU) {
		this.dailyARPPU = dailyARPPU;
	}

	public Double getMonthARPPU() {
		return monthARPPU;
	}

	public void setMonthARPPU(Double monthARPPU) {
		this.monthARPPU = monthARPPU;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getActorLevel() {
		return actorLevel;
	}

	public void setActorLevel(Integer actorLevel) {
		this.actorLevel = actorLevel;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
