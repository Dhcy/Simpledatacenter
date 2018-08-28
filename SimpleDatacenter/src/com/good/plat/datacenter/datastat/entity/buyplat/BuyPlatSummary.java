package com.good.plat.datacenter.datastat.entity.buyplat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BuyPlatSummary extends GameCommonEntity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Integer acvcount;

    private Integer regcount;

    private Integer logcount;

    private Integer acticount;

    private Integer lvl3count;

    private Integer rechgcount;

    private BigDecimal rechgmoney;

    private Integer oldcount;

    private BigDecimal adcost;
    //
    private Integer day2count;

    private Integer day3count;

    private Integer day7count;

    private Integer day15count;

    private Integer day30count;

    //
   
    private Double registTransRate;
    private Double activeRate;
    private Double lvl3Rate;
    private Double payRate;
    private Double oldRate;
    private Double day2Rate;
    private Double day3Rate;
    private Double day7Rate;
    private Double day15Rate;
    private Double day30Rate;
    //

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
	public Integer getLogcount() {
		return logcount;
	}
	public void setLogcount(Integer logcount) {
		this.logcount = logcount;
	}
	public Integer getActicount() {
		return acticount;
	}
	public void setActicount(Integer acticount) {
		this.acticount = acticount;
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
	public BigDecimal getRechgmoney() {
		return rechgmoney;
	}
	public void setRechgmoney(BigDecimal rechgmoney) {
		this.rechgmoney = rechgmoney;
	}
	public Integer getOldcount() {
		return oldcount;
	}
	public void setOldcount(Integer oldcount) {
		this.oldcount = oldcount;
	}
	public BigDecimal getAdcost() {
		return adcost;
	}
	public void setAdcost(BigDecimal adcost) {
		this.adcost = adcost;
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

	public Double getRegistTransRate() {
		return registTransRate;
	}
	public void setRegistTransRate(Double registTransRate) {
		this.registTransRate = registTransRate;
	}
	public Double getActiveRate() {
		return activeRate;
	}
	public void setActiveRate(Double activeRate) {
		this.activeRate = activeRate;
	}
	public Double getLvl3Rate() {
		return lvl3Rate;
	}
	public void setLvl3Rate(Double lvl3Rate) {
		this.lvl3Rate = lvl3Rate;
	}
	public Double getPayRate() {
		return payRate;
	}
	public void setPayRate(Double payRate) {
		this.payRate = payRate;
	}
	public Double getOldRate() {
		return oldRate;
	}
	public void setOldRate(Double oldRate) {
		this.oldRate = oldRate;
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
    
}
