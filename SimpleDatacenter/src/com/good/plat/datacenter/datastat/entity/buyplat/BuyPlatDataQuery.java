package com.good.plat.datacenter.datastat.entity.buyplat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BuyPlatDataQuery extends GameCommonEntity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer adchannel;
    private Integer acvcount;

    private Integer regcount;

    private Integer actregcount;

    private Integer acticount;

    private Integer regucount;

    private Integer logucount;

    private Integer lvl3count;

    private Integer rechgcount;
    private Integer rechgmoney;

    private Integer day2count;

    private Integer day3count;

    private Integer day7count;

    private Integer day15count;

    private Integer day30count;
    //
    private Double day2Rate;
    private Double day3Rate;
    private Double day7Rate;
    private Double day15Rate;
    private Double day30Rate;
    //
    private Double adcost;
    private Double costIncomeRate;
    //
    private Double payRate;						//新增付费率
    private Double arpu;						//新增付费arpu
    private Double activeRate;
    private Double registerTransRate;
    private Double lvl3Rate;
    private Integer oldcount;
    private Double oldRate;						//
    private Integer totalrechgcount;			//总充值用户数
    private Double totalrechgPayRate;			//总用户付费率
    private Double totalrechgARPU;				//总用户付费arpu
    private Double totalrechgmoney;			//总充值金额
    private Double actregcountRate;				//新增创角率
    
    
    
	public Double getTotalrechgPayRate() {
		return totalrechgPayRate;
	}

	public void setTotalrechgPayRate(Double totalrechgPayRate) {
		this.totalrechgPayRate = totalrechgPayRate;
	}

	public Double getTotalrechgARPU() {
		return totalrechgARPU;
	}

	public void setTotalrechgARPU(Double totalrechgARPU) {
		this.totalrechgARPU = totalrechgARPU;
	}

	public Double getActregcountRate() {
		return actregcountRate;
	}

	public void setActregcountRate(Double actregcountRate) {
		this.actregcountRate = actregcountRate;
	}

	public Double getOldRate() {
		return oldRate;
	}

	public void setOldRate(Double oldRate) {
		this.oldRate = oldRate;
	}

	public Integer getOldcount() {
		return oldcount;
	}

	public void setOldcount(Integer oldcount) {
		this.oldcount = oldcount;
	}

	public Integer getTotalrechgcount() {
		return totalrechgcount;
	}

	public void setTotalrechgcount(Integer totalrechgcount) {
		this.totalrechgcount = totalrechgcount;
	}

	public Double getTotalrechgmoney() {
		return totalrechgmoney;
	}

	public void setTotalrechgmoney(Double totalrechgmoney) {
		this.totalrechgmoney = totalrechgmoney;
	}

	public Double getAdcost() {
		return adcost;
	}

	public void setAdcost(Double adcost) {
		this.adcost = adcost;
	}

	public Double getCostIncomeRate() {
		return costIncomeRate;
	}

	public void setCostIncomeRate(Double costIncomeRate) {
		this.costIncomeRate = costIncomeRate;
	}

	public Integer getRechgmoney() {
		return rechgmoney;
	}

	public void setRechgmoney(Integer rechgmoney) {
		this.rechgmoney = rechgmoney;
	}

	public Integer getAcvcount() {
		return acvcount;
	}

	public void setAcvcount(Integer acvcount) {
		this.acvcount = acvcount;
	}

	public Integer getRegcount() {
		return regcount;
	}

	public void setRegcount(Integer regcount) {
		this.regcount = regcount;
	}

	public Integer getActregcount() {
		return actregcount;
	}

	public void setActregcount(Integer actregcount) {
		this.actregcount = actregcount;
	}

	public Integer getActicount() {
		return acticount;
	}

	public void setActicount(Integer acticount) {
		this.acticount = acticount;
	}

	public Integer getRegucount() {
		return regucount;
	}

	public void setRegucount(Integer regucount) {
		this.regucount = regucount;
	}

	public Integer getLogucount() {
		return logucount;
	}

	public void setLogucount(Integer logucount) {
		this.logucount = logucount;
	}

	public Integer getLvl3count() {
		return lvl3count;
	}

	public void setLvl3count(Integer lvl3count) {
		this.lvl3count = lvl3count;
	}

	public Integer getRechgcount() {
		return rechgcount;
	}

	public void setRechgcount(Integer rechgcount) {
		this.rechgcount = rechgcount;
	}

	public Integer getDay2count() {
		return day2count;
	}

	public void setDay2count(Integer day2count) {
		this.day2count = day2count;
	}

	public Integer getDay3count() {
		return day3count;
	}

	public void setDay3count(Integer day3count) {
		this.day3count = day3count;
	}

	public Integer getDay7count() {
		return day7count;
	}

	public void setDay7count(Integer day7count) {
		this.day7count = day7count;
	}

	public Integer getDay15count() {
		return day15count;
	}

	public void setDay15count(Integer day15count) {
		this.day15count = day15count;
	}

	public Integer getDay30count() {
		return day30count;
	}

	public void setDay30count(Integer day30count) {
		this.day30count = day30count;
	}



	public Double getPayRate() {
		return payRate;
	}

	public void setPayRate(Double payRate) {
		this.payRate = payRate;
	}

	

	public Double getArpu() {
		return arpu;
	}

	public void setArpu(Double arpu) {
		this.arpu = arpu;
	}

	public Double getActiveRate() {
		return activeRate;
	}

	public void setActiveRate(Double activeRate) {
		this.activeRate = activeRate;
	}

	public Double getRegisterTransRate() {
		return registerTransRate;
	}

	public void setRegisterTransRate(Double registerTransRate) {
		this.registerTransRate = registerTransRate;
	}

	public Double getLvl3Rate() {
		return lvl3Rate;
	}

	public void setLvl3Rate(Double lvl3Rate) {
		this.lvl3Rate = lvl3Rate;
	}


	public Double getDay2Rate() {
		return day2Rate;
	}

	public void setDay2Rate(Double day2Rate) {
		this.day2Rate = day2Rate;
	}

	public Double getDay3Rate() {
		return day3Rate;
	}

	public void setDay3Rate(Double day3Rate) {
		this.day3Rate = day3Rate;
	}

	public Double getDay7Rate() {
		return day7Rate;
	}

	public void setDay7Rate(Double day7Rate) {
		this.day7Rate = day7Rate;
	}

	public Double getDay15Rate() {
		return day15Rate;
	}

	public void setDay15Rate(Double day15Rate) {
		this.day15Rate = day15Rate;
	}

	public Double getDay30Rate() {
		return day30Rate;
	}

	public void setDay30Rate(Double day30Rate) {
		this.day30Rate = day30Rate;
	}

	public Integer getAdchannel() {
		return adchannel;
	}

	public void setAdchannel(Integer adchannel) {
		this.adchannel = adchannel;
	}
	

}
