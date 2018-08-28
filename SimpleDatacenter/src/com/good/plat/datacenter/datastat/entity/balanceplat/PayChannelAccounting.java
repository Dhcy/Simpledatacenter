package com.good.plat.datacenter.datastat.entity.balanceplat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PayChannelAccounting {
	
	public PayChannelAccounting() {
		super();
		this.money = new BigDecimal(0);
		
	}
	private Integer gameid;
	
	private Integer channel;
	private String gameName;
	private String channelName;
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date statdate;

	private Integer currency;

	private BigDecimal money;
	private BigDecimal rmb;
	//项目ID
	private Integer projectid;
	private List<ChannelAccounting> payChannelIncomes=new ArrayList<ChannelAccounting>();
	private List<ChannelAccounting> videoChannelIncomes=new ArrayList<ChannelAccounting>();
	/**
	 * 项目ID不等于1,2 有的
	 * */
	
	private BigDecimal alipayMoney;//支付宝
	private BigDecimal cardPayMoney;//卡充值
	private BigDecimal BankPayMoney;//银行汇款
	private BigDecimal mo9PayMoney;//mo9支付
	private BigDecimal pay19Money;//19pay
	private BigDecimal unionPayMoney;//银联TCL
	private BigDecimal ppWalletPayMoney;//PP钱包
	private BigDecimal ppSdkPayMoney;//PP钱包SDK版
	private BigDecimal wechatPayMoney;//微信支付
	private BigDecimal microsoftPayMoney;//微软支付
	private BigDecimal steamPayMoney;//steam
	private BigDecimal windowsPayMoney;//windows
	/**
	 * 项目ID等于1,2 有的
	 * */
	private BigDecimal microPayMoney;//小额支付
	private BigDecimal otherCarPayMoney;//其他卡
	private BigDecimal MO9Money;//MO9
	private BigDecimal newAlipayMoney;//新支付宝充值
	
	//
	private BigDecimal googlePayMoney;//googlepay
	private BigDecimal appstorePayMoney;//appstore
	private BigDecimal facebookPayMoney;//Facebook
	
	//
	private String  statdate1;
	
	
	
	public static class ChannelIncome{
		private Integer channelid;
		private String channelName;
		private Double money;
		private Double rmb;
		public Integer getChannelid() {
			return channelid;
		}
		public void setChannelid(Integer channelid) {
			this.channelid = channelid;
		}
		public String getChannelName() {
			return channelName;
		}
		public void setChannelName(String channelName) {
			this.channelName = channelName;
		}
		public Double getMoney() {
			return money;
		}
		public void setMoney(Double money) {
			this.money = money;
		}
		public Double getRmb() {
			return rmb;
		}
		public void setRmb(Double rmb) {
			this.rmb = rmb;
		}
		public ChannelIncome() {
			this.money = 0.0;
		}
		
	}
	
	public BigDecimal getRmb() {
		return rmb;
	}
	public void setRmb(BigDecimal rmb) {
		this.rmb = rmb;
	}

	private List<ChannelIncome> channelIncomes = new LinkedList();
	
	public Integer getGameid() {
		return gameid;
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
	public Date getStatdate() {
		return statdate;
	}
	public void setStatdate(Date statdate) {
		this.statdate = statdate;
	}
	public Integer getCurrency() {
		return currency;
	}
	
	public String getGameName() {
		return gameName;
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
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public List<ChannelIncome> getChannelIncomes() {
		return channelIncomes;
	}
	public void setChannelIncomes(List<ChannelIncome> channelIncomes) {
		this.channelIncomes = channelIncomes;
	}
	public Integer getProjectid() {
		return projectid;
	}
	public void setProjectid(Integer projectid) {
		this.projectid = projectid;
	}
	public BigDecimal getAlipayMoney() {
		return alipayMoney;
	}
	public void setAlipayMoney(BigDecimal alipayMoney) {
		this.alipayMoney = alipayMoney;
	}
	public BigDecimal getCardPayMoney() {
		return cardPayMoney;
	}
	public void setCardPayMoney(BigDecimal cardPayMoney) {
		this.cardPayMoney = cardPayMoney;
	}
	public BigDecimal getBankPayMoney() {
		return BankPayMoney;
	}
	public void setBankPayMoney(BigDecimal bankPayMoney) {
		BankPayMoney = bankPayMoney;
	}
	public BigDecimal getMo9PayMoney() {
		return mo9PayMoney;
	}
	public void setMo9PayMoney(BigDecimal mo9PayMoney) {
		this.mo9PayMoney = mo9PayMoney;
	}
	public BigDecimal getPay19Money() {
		return pay19Money;
	}
	public void setPay19Money(BigDecimal pay19Money) {
		this.pay19Money = pay19Money;
	}
	public BigDecimal getUnionPayMoney() {
		return unionPayMoney;
	}
	public void setUnionPayMoney(BigDecimal unionPayMoney) {
		this.unionPayMoney = unionPayMoney;
	}
	public BigDecimal getPpWalletPayMoney() {
		return ppWalletPayMoney;
	}
	public void setPpWalletPayMoney(BigDecimal ppWalletPayMoney) {
		this.ppWalletPayMoney = ppWalletPayMoney;
	}
	public BigDecimal getPpSdkPayMoney() {
		return ppSdkPayMoney;
	}
	public void setPpSdkPayMoney(BigDecimal ppSdkPayMoney) {
		this.ppSdkPayMoney = ppSdkPayMoney;
	}
	public BigDecimal getWechatPayMoney() {
		return wechatPayMoney;
	}
	public void setWechatPayMoney(BigDecimal wechatPayMoney) {
		this.wechatPayMoney = wechatPayMoney;
	}
	public BigDecimal getMicrosoftPayMoney() {
		return microsoftPayMoney;
	}
	public void setMicrosoftPayMoney(BigDecimal microsoftPayMoney) {
		this.microsoftPayMoney = microsoftPayMoney;
	}
	public BigDecimal getMicroPayMoney() {
		return microPayMoney;
	}
	public void setMicroPayMoney(BigDecimal microPayMoney) {
		this.microPayMoney = microPayMoney;
	}
	public BigDecimal getOtherCarPayMoney() {
		return otherCarPayMoney;
	}
	public void setOtherCarPayMoney(BigDecimal otherCarPayMoney) {
		this.otherCarPayMoney = otherCarPayMoney;
	}
	public BigDecimal getMO9Money() {
		return MO9Money;
	}
	public void setMO9Money(BigDecimal mO9Money) {
		MO9Money = mO9Money;
	}
	public BigDecimal getNewAlipayMoney() {
		return newAlipayMoney;
	}
	public void setNewAlipayMoney(BigDecimal newAlipayMoney) {
		this.newAlipayMoney = newAlipayMoney;
	}
	public List<ChannelAccounting> getPayChannelIncomes() {
		return payChannelIncomes;
	}
	public void setPayChannelIncomes(List<ChannelAccounting> payChannelIncomes) {
		this.payChannelIncomes = payChannelIncomes;
	}
	public BigDecimal getGooglePayMoney() {
		return googlePayMoney;
	}
	public void setGooglePayMoney(BigDecimal googlePayMoney) {
		this.googlePayMoney = googlePayMoney;
	}
	public BigDecimal getAppstorePayMoney() {
		return appstorePayMoney;
	}
	public void setAppstorePayMoney(BigDecimal appstorePayMoney) {
		this.appstorePayMoney = appstorePayMoney;
	}
	public BigDecimal getFacebookPayMoney() {
		return facebookPayMoney;
	}
	public void setFacebookPayMoney(BigDecimal facebookPayMoney) {
		this.facebookPayMoney = facebookPayMoney;
	}
	public String getStatdate1() {
		return statdate1;
	}
	public void setStatdate1(String statdate1) {
		this.statdate1 = statdate1;
	}
	public List<ChannelAccounting> getVideoChannelIncomes() {
		return videoChannelIncomes;
	}
	public void setVideoChannelIncomes(List<ChannelAccounting> videoChannelIncomes) {
		this.videoChannelIncomes = videoChannelIncomes;
	}
	
	public BigDecimal getSteamPayMoney() {
		return steamPayMoney;
	}
	public void setSteamPayMoney(BigDecimal steamPayMoney) {
		this.steamPayMoney = steamPayMoney;
	}
	public BigDecimal getWindowsPayMoney() {
		return windowsPayMoney;
	}
	public void setWindowsPayMoney(BigDecimal windowsPayMoney) {
		this.windowsPayMoney = windowsPayMoney;
	}
	@Override
	public String toString() {
		return "PayChannelAccounting [BankPayMoney=" + BankPayMoney
				+ ", MO9Money=" + MO9Money + ", alipayMoney=" + alipayMoney
				+ ", appstorePayMoney=" + appstorePayMoney + ", cardPayMoney="
				+ cardPayMoney + ", channel=" + channel + ", channelIncomes="
				+ channelIncomes + ", channelName=" + channelName
				+ ", currency=" + currency + ", facebookPayMoney="
				+ facebookPayMoney + ", gameName=" + gameName + ", gameid="
				+ gameid + ", googlePayMoney=" + googlePayMoney
				+ ", microPayMoney=" + microPayMoney + ", microsoftPayMoney="
				+ microsoftPayMoney + ", mo9PayMoney=" + mo9PayMoney
				+ ", money=" + money + ", newAlipayMoney=" + newAlipayMoney
				+ ", otherCarPayMoney=" + otherCarPayMoney + ", pay19Money="
				+ pay19Money + ", payChannelIncomes=" + payChannelIncomes
				+ ", ppSdkPayMoney=" + ppSdkPayMoney + ", ppWalletPayMoney="
				+ ppWalletPayMoney + ", projectid=" + projectid + ", rmb="
				+ rmb + ", statdate=" + statdate + ", statdate1=" + statdate1
				+ ", steamPayMoney=" + steamPayMoney + ", unionPayMoney="
				+ unionPayMoney + ", videoChannelIncomes="
				+ videoChannelIncomes + ", wechatPayMoney=" + wechatPayMoney
				+ ", windowsPayMoney=" + windowsPayMoney + "]";
	}
	
	
	
	
	
	
	
	
}