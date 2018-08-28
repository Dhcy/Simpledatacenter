package com.good.plat.datacenter.datastat.entity.balanceplat;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ChannelAccounting {
	private Integer id;

	private Integer gameid;

	private Integer channel;

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date statdate;

	private Integer currency;

	private BigDecimal money;

	private BigDecimal sharerate;

	private Integer confcurr;

	private BigDecimal exchrate;
	private Byte coopmodel;

	private BigDecimal payrate;

	private BigDecimal finalpayrate;

	private BigDecimal taxrate;

	private BigDecimal exchangerate;

	private BigDecimal sharingrate;
	
	private BigDecimal rmb;
	private BigDecimal foreignMoney;
	private BigDecimal settleMoney;
	private BigDecimal shareMoney;
	private Byte gradient;

	private Byte settlementtype;
	private String gradientName;
	private String coopModelName;
	private String settlementtypeName;
	private String isFinalPayRate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date contractstartdate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date constractenddate;
	private Integer ispayrate;

	private String gameName;
	private String channelName;
	private String channelSimName;//全称
	private BigDecimal rmbrate;//人民币比率
	private Integer channelId;//渠道id
	private String projectName;//项目名称
    private String signCompany; //签约公司
	
	
	public ChannelAccounting(){
	}
	public ChannelAccounting(String channelName,BigDecimal money){
		this.channelName=channelName;
		this.money=money;
	}
	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getChannelSimName() {
		return channelSimName;
	}

	public void setChannelSimName(String channelSimName) {
		this.channelSimName = channelSimName;
	}

	public BigDecimal getRmbrate() {
		return rmbrate;
	}

	public void setRmbrate(BigDecimal rmbrate) {
		this.rmbrate = rmbrate;
	}

	public String getSettlementtypeName() {
		return settlementtypeName;
	}

	public void setSettlementtypeName(String settlementtypeName) {
		this.settlementtypeName = settlementtypeName;
	}

	public String getGameName() {
		return gameName;
	}

	public Date getStatdate() {
		return statdate;
	}

	public void setStatdate(Date statdate) {
		this.statdate = statdate;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getSharerate() {
		return sharerate;
	}

	public void setSharerate(BigDecimal sharerate) {
		this.sharerate = sharerate;
	}

	public Integer getConfcurr() {
		return confcurr;
	}

	public void setConfcurr(Integer confcurr) {
		this.confcurr = confcurr;
	}

	public BigDecimal getExchrate() {
		return exchrate;
	}

	public void setExchrate(BigDecimal exchrate) {
		this.exchrate = exchrate;
	}

	public String getGradientName() {
		return gradientName;
	}

	public void setGradientName(String gradientName) {
		this.gradientName = gradientName;
	}

	public String getCoopModelName() {
		return coopModelName;
	}

	public void setCoopModelName(String coopModelName) {
		this.coopModelName = coopModelName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGameid() {
		return gameid;
	}

	public Integer getIspayrate() {
		return ispayrate;
	}

	public void setIspayrate(Integer ispayrate) {
		this.ispayrate = ispayrate;
	}

	public void setGameid(Integer gameid) {
		this.gameid = gameid;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	

	public BigDecimal getPayrate() {
		return payrate;
	}

	public void setPayrate(BigDecimal payrate) {
		this.payrate = payrate;
	}

	public BigDecimal getFinalpayrate() {
		return finalpayrate;
	}

	public void setFinalpayrate(BigDecimal finalpayrate) {
		this.finalpayrate = finalpayrate;
	}

	public BigDecimal getTaxrate() {
		return taxrate;
	}

	public void setTaxrate(BigDecimal taxrate) {
		this.taxrate = taxrate;
	}

	public BigDecimal getExchangerate() {
		return exchangerate;
	}

	public void setExchangerate(BigDecimal exchangerate) {
		this.exchangerate = exchangerate;
	}

	public BigDecimal getSharingrate() {
		return sharingrate;
	}

	public void setSharingrate(BigDecimal sharingrate) {
		this.sharingrate = sharingrate;
	}

	public Byte getGradient() {
		return gradient;
	}

	public void setGradient(Byte gradient) {
		this.gradient = gradient;
	}

	

	public Byte getCoopmodel() {
		return coopmodel;
	}

	public void setCoopmodel(Byte coopmodel) {
		this.coopmodel = coopmodel;
	}

	public Byte getSettlementtype() {
		return settlementtype;
	}

	public void setSettlementtype(Byte settlementtype) {
		this.settlementtype = settlementtype;
	}

	public Date getContractstartdate() {
		return contractstartdate;
	}

	public void setContractstartdate(Date contractstartdate) {
		this.contractstartdate = contractstartdate;
	}

	public Date getConstractenddate() {
		return constractenddate;
	}

	public void setConstractenddate(Date constractenddate) {
		this.constractenddate = constractenddate;
	}

	public BigDecimal getRmb() {
		return rmb;
	}

	public void setRmb(BigDecimal rmb) {
		this.rmb = rmb;
	}

	public BigDecimal getForeignMoney() {
		return foreignMoney;
	}

	public void setForeignMoney(BigDecimal foreignMoney) {
		this.foreignMoney = foreignMoney;
	}

	public BigDecimal getSettleMoney() {
		return settleMoney;
	}

	public void setSettleMoney(BigDecimal settleMoney) {
		this.settleMoney = settleMoney;
	}

	public BigDecimal getShareMoney() {
		return shareMoney;
	}

	public void setShareMoney(BigDecimal shareMoney) {
		this.shareMoney = shareMoney;
	}

	public String getIsFinalPayRate() {
		return isFinalPayRate;
	}

	public void setIsFinalPayRate(String isFinalPayRate) {
		this.isFinalPayRate = isFinalPayRate;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getSignCompany() {
		return signCompany;
	}
	public void setSignCompany(String signCompany) {
		this.signCompany = signCompany;
	}
	
	


}