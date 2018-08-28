package com.good.plat.datacenter.datastat.entity.whales;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * @ClassName: Whales
 * @Description: 鲸鱼用户
 * @author dmw
 * @date 2016年3月14日 下午10:07:56
 */
public class Whales extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public Whales() {
	}
	
	//排名
	private Integer ranking;
	
	//帐号
	private String account;
	
	//充值金额
	private Double sumOfPayment;
	
	//充入虚拟币
	private Long currencyPurchased;
	
	//消耗总额
	private Long sumOfExpense;
	
	//新增日期
	private String firstLogin;
	
	//当前等级
	private Integer currentLevel;
	
	//账户ID
	private String accountID;
	
	//账户名称
	private String accountName;
	
	//渠道来源
	private String channelName;
	
	//地区
	private String address;
	
	//设备
	private String device;
	
	//private String firstLogin;
	
	//最后登录
	private String latestLogin;
	
	//首次充值
	private String firstPay;
	
	//虚拟币总值
	private Long currencyBalance;
	
	//支付渠道id
	private Integer payChannel;
	
	//支付渠道
	private String payChannelName;
	
	//消费点ID
	private Integer itemID;
	
	//消费点
	private String itemName;
	
	//消费数量
	private Integer numberOfExpense;
	
	//消耗虚拟币
	private Integer currencyspent;
	//角色名
	private String actorName;
	//终端类型(1:ios;2:android)
	private Integer termType;
	//区服名称
	private String gameAreaName;
	//当前剩余氪金数
	private Integer currLeftCount;
	//首冲日期
	private String firstRechargeDate;
	//最后充值日期
	private String lastRechargeDate;
	//当前
	private String leftCount;
	//剩余绑定氪金字符串
	private String leftBindCountStr;
	//剩余绑定氪金数
	private Integer leftBindCount;
	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Double getSumOfPayment() {
		return sumOfPayment;
	}

	public void setSumOfPayment(Double sumOfPayment) {
		this.sumOfPayment = sumOfPayment;
	}

	public Long getCurrencyPurchased() {
		return currencyPurchased;
	}

	public void setCurrencyPurchased(Long currencyPurchased) {
		this.currencyPurchased = currencyPurchased;
	}

	public Long getSumOfExpense() {
		return sumOfExpense;
	}

	public void setSumOfExpense(Long sumOfExpense) {
		this.sumOfExpense = sumOfExpense;
	}

	public String getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(String firstLogin) {
		this.firstLogin = firstLogin;
	}

	public Integer getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(Integer currentLevel) {
		this.currentLevel = currentLevel;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getLatestLogin() {
		return latestLogin;
	}

	public void setLatestLogin(String latestLogin) {
		this.latestLogin = latestLogin;
	}

	public String getFirstPay() {
		return firstPay;
	}

	public void setFirstPay(String firstPay) {
		this.firstPay = firstPay;
	}

	public Long getCurrencyBalance() {
		return currencyBalance;
	}

	public void setCurrencyBalance(Long currencyBalance) {
		this.currencyBalance = currencyBalance;
	}

	public Integer getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(Integer payChannel) {
		this.payChannel = payChannel;
	}

	public String getPayChannelName() {
		return payChannelName;
	}

	public void setPayChannelName(String payChannelName) {
		this.payChannelName = payChannelName;
	}

	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getNumberOfExpense() {
		return numberOfExpense;
	}

	public void setNumberOfExpense(Integer numberOfExpense) {
		this.numberOfExpense = numberOfExpense;
	}

	public Integer getCurrencyspent() {
		return currencyspent;
	}

	public void setCurrencyspent(Integer currencyspent) {
		this.currencyspent = currencyspent;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public Integer getTermType() {
		return termType;
	}

	public void setTermType(Integer termType) {
		this.termType = termType;
	}

	public String getGameAreaName() {
		return gameAreaName;
	}

	public void setGameAreaName(String gameAreaName) {
		this.gameAreaName = gameAreaName;
	}


	public Integer getCurrLeftCount() {
		return currLeftCount;
	}

	public void setCurrLeftCount(Integer currLeftCount) {
		this.currLeftCount = currLeftCount;
	}

	public String getFirstRechargeDate() {
		return firstRechargeDate;
	}

	public void setFirstRechargeDate(String firstRechargeDate) {
		this.firstRechargeDate = firstRechargeDate;
	}

	public String getLastRechargeDate() {
		return lastRechargeDate;
	}

	public void setLastRechargeDate(String lastRechargeDate) {
		this.lastRechargeDate = lastRechargeDate;
	}

	public String getLeftCount() {
		return leftCount;
	}

	public void setLeftCount(String leftCount) {
		this.leftCount = leftCount;
	}


	public String getLeftBindCountStr() {
		return leftBindCountStr;
	}

	public void setLeftBindCountStr(String leftBindCountStr) {
		this.leftBindCountStr = leftBindCountStr;
	}

	public Integer getLeftBindCount() {
		return leftBindCount;
	}

	public void setLeftBindCount(Integer leftBindCount) {
		this.leftBindCount = leftBindCount;
	}

	

	

}
