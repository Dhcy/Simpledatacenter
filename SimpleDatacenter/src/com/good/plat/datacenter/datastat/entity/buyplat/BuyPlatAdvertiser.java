package com.good.plat.datacenter.datastat.entity.buyplat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 广告主数据列表
 * @ClassName: BuyPlatAdvertiser
 * @Description: TODO
 * @author moxw
 * @date 2016年5月30日 下午6:28:58
 */

public class BuyPlatAdvertiser extends GameCommonEntity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;

    private Date statdate;

    private Integer gameid;

    private Integer termtype;

    private Integer adchannel;

    //
	private Integer regcount;
	private Integer rechgcount;
	private Double rechgmoney;
	private Integer acticount;
	private String adspace;
	private Integer addisplaytime;
	private Integer adcost;
	private Integer clickcount;
	private Integer installcount;
	//
	private Integer day2retation;
	private Double day2retation_rate;
	private Integer day3retation;
	private Double day3retation_rate;
	private Integer day7retation;
	private Double day7retation_rate;
	private Integer day15retation;
	private Double day15retation_rate;
	private Integer day30retation;
	private Double day30retation_rate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getStatdate() {
		return statdate;
	}
	public void setStatdate(Date statdate) {
		this.statdate = statdate;
	}
	public Integer getGameid() {
		return gameid;
	}
	public void setGameid(Integer gameid) {
		this.gameid = gameid;
	}
	public Integer getTermtype() {
		return termtype;
	}
	public void setTermtype(Integer termtype) {
		this.termtype = termtype;
	}
	public Integer getAdchannel() {
		return adchannel;
	}
	public void setAdchannel(Integer adchannel) {
		this.adchannel = adchannel;
	}
	
	public Integer getRegcount() {
		return regcount;
	}
	public void setRegcount(Integer regcount) {
		this.regcount = regcount;
	}
	public Integer getRechgcount() {
		return rechgcount;
	}
	public void setRechgcount(Integer rechgcount) {
		this.rechgcount = rechgcount;
	}
	public Integer getDay2retation() {
		return day2retation;
	}
	public void setDay2retation(Integer day2retation) {
		this.day2retation = day2retation;
	}
	public Double getDay2retation_rate() {
		return day2retation_rate;
	}
	public void setDay2retation_rate(Double day2retation_rate) {
		this.day2retation_rate = day2retation_rate;
	}
	public Integer getDay3retation() {
		return day3retation;
	}
	public void setDay3retation(Integer day3retation) {
		this.day3retation = day3retation;
	}
	public Double getDay3retation_rate() {
		return day3retation_rate;
	}
	public void setDay3retation_rate(Double day3retation_rate) {
		this.day3retation_rate = day3retation_rate;
	}
	public Integer getDay7retation() {
		return day7retation;
	}
	public void setDay7retation(Integer day7retation) {
		this.day7retation = day7retation;
	}
	public Double getDay7retation_rate() {
		return day7retation_rate;
	}
	public void setDay7retation_rate(Double day7retation_rate) {
		this.day7retation_rate = day7retation_rate;
	}
	public Integer getDay15retation() {
		return day15retation;
	}
	public void setDay15retation(Integer day15retation) {
		this.day15retation = day15retation;
	}
	public Double getDay15retation_rate() {
		return day15retation_rate;
	}
	public void setDay15retation_rate(Double day15retation_rate) {
		this.day15retation_rate = day15retation_rate;
	}
	public Integer getDay30retation() {
		return day30retation;
	}
	public void setDay30retation(Integer day30retation) {
		this.day30retation = day30retation;
	}
	public Double getDay30retation_rate() {
		return day30retation_rate;
	}
	public void setDay30retation_rate(Double day30retation_rate) {
		this.day30retation_rate = day30retation_rate;
	}

	public String getAdspace() {
		return adspace;
	}
	public void setAdspace(String adspace) {
		this.adspace = adspace;
	}

	public Double getRechgmoney() {
		return rechgmoney;
	}
	public void setRechgmoney(Double rechgmoney) {
		this.rechgmoney = rechgmoney;
	}
	public Integer getActicount() {
		return acticount;
	}
	public void setActicount(Integer acticount) {
		this.acticount = acticount;
	}
	public Integer getAddisplaytime() {
		return addisplaytime;
	}
	public void setAddisplaytime(Integer addisplaytime) {
		this.addisplaytime = addisplaytime;
	}
	public Integer getAdcost() {
		return adcost;
	}
	public void setAdcost(Integer adcost) {
		this.adcost = adcost;
	}
	public Integer getClickcount() {
		return clickcount;
	}
	public void setClickcount(Integer clickcount) {
		this.clickcount = clickcount;
	}
	public Integer getInstallcount() {
		return installcount;
	}
	public void setInstallcount(Integer installcount) {
		this.installcount = installcount;
	}
}
