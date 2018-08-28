package com.good.plat.datacenter.datastat.entity.index;

import java.io.Serializable;
import java.util.List;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * @ClassName: SummaryData
 * @Description: 游戏概况
 * @author dmw
 * @date 2016年3月14日 上午11:49:14
 */
public class SummaryData extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	public SummaryData() {
	}
	
	//设备激活
	public Integer deviceActivation = 0;
	
	//新增玩家
	private Integer newUser = 0;
	
	//付费玩家
	private Integer payUser = 0;
	
	//收入
	private Double income = 0d;
	
	//SUM设备
	private Integer deviceSUM = 0;
	
	//SUM帐号
	private Integer accountSUM = 0;
	
	//AVG设备
	private Integer devicAVG = 0;
	
	//AVG帐号
	private Integer accountAVG = 0;
	
	/**
	 * 新增激活和账户
	 */
	//激活台数
	private Integer deviceCount = 0;
	
	//新增玩家
	private Integer userCount = 0;
	
	/**
	 * 活跃玩家
	 */
	//新玩家
	private Integer newActiveUser = 0;
	
	//老玩家
	private Integer oldActiveUser = 0;
	
	//总计
	private Integer activeUserSUM = 0;
	
	//AVG
	private Integer activeUserAVG = 0;
	
	
	/**
	 * 付费玩家
	 */
	//新增付费玩家
	private Integer newPayUser = 0;
	
	//老付费玩家
	private Integer oldPayUser = 0;
	
	//总计
	private Integer payUserSUM = 0;
		
	//AVG
	private Integer payUserAVG = 0;
	
	
	/**
	 * 收入
	 */
	//private Double income = 0;
	
	//SUM
	private Double incomeSUM = 0d;
	
	//AVG
	private Double incomeAVG = 0d;
	
	/**
	 * 日付费率
	 */
	private Double payRate = 0d;
	
	//AVG
	private Double payAVG = 0d;
	
	
	/**
	 * 日ARPU
	 */
	private Double dayARPU = 0d;
	
	/**
	 * 日ARPPU
	 */
	private Double dayARPPU = 0d;
	
	/**
	 * 新增账户留存
	 */
	//次日留存率
	private Double userDay2 = 0d;
	
	//7日留存率
	private Double userDay7 = 0d;
	
	//30日留存率
	private Double userDay30 = 0d;
	
	private Double day2AVG = 0d;
	
	private Double day7AVG = 0d;
	
	private Double day30AVG = 0d;
	
	/**
	 * 激活设备留存
	 */
	//次日留存率
	private Double deviceDay2 = 0d;
	
	//7日留存率
	private Double deviceDay7 = 0d;
	
	//30日留存率
	private Double deviceDay30 = 0d;
	
	
	/**
	 * 平均游戏时长与次数
	 */
	//平均游戏次数
	private Double gameCounts = 0d;
	
	//平均游戏时长
	private Double gameTimes = 0d;
	
	private Double timesAVG = 0d;
	
	private List<SummaryData> dataList;

	public Integer getDeviceActivation() {
		return deviceActivation;
	}

	public void setDeviceActivation(Integer deviceActivation) {
		this.deviceActivation = deviceActivation;
	}

	public Integer getNewUser() {
		return newUser;
	}

	public void setNewUser(Integer newUser) {
		this.newUser = newUser;
	}

	public Integer getPayUser() {
		return payUser;
	}

	public void setPayUser(Integer payUser) {
		this.payUser = payUser;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Integer getDeviceSUM() {
		return deviceSUM;
	}

	public void setDeviceSUM(Integer deviceSUM) {
		this.deviceSUM = deviceSUM;
	}

	public Integer getAccountSUM() {
		return accountSUM;
	}

	public void setAccountSUM(Integer accountSUM) {
		this.accountSUM = accountSUM;
	}

	public Integer getDevicAVG() {
		return devicAVG;
	}

	public void setDevicAVG(Integer devicAVG) {
		this.devicAVG = devicAVG;
	}

	public Integer getAccountAVG() {
		return accountAVG;
	}

	public void setAccountAVG(Integer accountAVG) {
		this.accountAVG = accountAVG;
	}

	public Integer getDeviceCount() {
		return deviceCount;
	}

	public void setDeviceCount(Integer deviceCount) {
		this.deviceCount = deviceCount;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public Integer getNewActiveUser() {
		return newActiveUser;
	}

	public void setNewActiveUser(Integer newActiveUser) {
		this.newActiveUser = newActiveUser;
	}

	public Integer getOldActiveUser() {
		return oldActiveUser;
	}

	public void setOldActiveUser(Integer oldActiveUser) {
		this.oldActiveUser = oldActiveUser;
	}

	public Integer getActiveUserSUM() {
		return activeUserSUM;
	}

	public void setActiveUserSUM(Integer activeUserSUM) {
		this.activeUserSUM = activeUserSUM;
	}

	public Integer getActiveUserAVG() {
		return activeUserAVG;
	}

	public void setActiveUserAVG(Integer activeUserAVG) {
		this.activeUserAVG = activeUserAVG;
	}

	public Integer getNewPayUser() {
		return newPayUser;
	}

	public void setNewPayUser(Integer newPayUser) {
		this.newPayUser = newPayUser;
	}

	public Integer getOldPayUser() {
		return oldPayUser;
	}

	public void setOldPayUser(Integer oldPayUser) {
		this.oldPayUser = oldPayUser;
	}

	public Integer getPayUserSUM() {
		return payUserSUM;
	}

	public void setPayUserSUM(Integer payUserSUM) {
		this.payUserSUM = payUserSUM;
	}

	public Integer getPayUserAVG() {
		return payUserAVG;
	}

	public void setPayUserAVG(Integer payUserAVG) {
		this.payUserAVG = payUserAVG;
	}

	public Double getIncomeSUM() {
		return incomeSUM;
	}

	public void setIncomeSUM(Double incomeSUM) {
		this.incomeSUM = incomeSUM;
	}

	public Double getIncomeAVG() {
		return incomeAVG;
	}

	public void setIncomeAVG(Double incomeAVG) {
		this.incomeAVG = incomeAVG;
	}

	public Double getPayRate() {
		return payRate;
	}

	public void setPayRate(Double payRate) {
		this.payRate = payRate;
	}

	public Double getPayAVG() {
		return payAVG;
	}

	public void setPayAVG(Double payAVG) {
		this.payAVG = payAVG;
	}

	public Double getDayARPU() {
		return dayARPU;
	}

	public void setDayARPU(Double dayARPU) {
		this.dayARPU = dayARPU;
	}

	public Double getDayARPPU() {
		return dayARPPU;
	}

	public void setDayARPPU(Double dayARPPU) {
		this.dayARPPU = dayARPPU;
	}

	public Double getUserDay2() {
		return userDay2;
	}

	public void setUserDay2(Double userDay2) {
		this.userDay2 = userDay2;
	}

	public Double getUserDay7() {
		return userDay7;
	}

	public void setUserDay7(Double userDay7) {
		this.userDay7 = userDay7;
	}

	public Double getUserDay30() {
		return userDay30;
	}

	public void setUserDay30(Double userDay30) {
		this.userDay30 = userDay30;
	}

	public Double getDay2AVG() {
		return day2AVG;
	}

	public void setDay2AVG(Double day2avg) {
		day2AVG = day2avg;
	}

	public Double getDay7AVG() {
		return day7AVG;
	}

	public void setDay7AVG(Double day7avg) {
		day7AVG = day7avg;
	}

	public Double getDay30AVG() {
		return day30AVG;
	}

	public void setDay30AVG(Double day30avg) {
		day30AVG = day30avg;
	}

	public Double getDeviceDay2() {
		return deviceDay2;
	}

	public void setDeviceDay2(Double deviceDay2) {
		this.deviceDay2 = deviceDay2;
	}

	public Double getDeviceDay7() {
		return deviceDay7;
	}

	public void setDeviceDay7(Double deviceDay7) {
		this.deviceDay7 = deviceDay7;
	}

	public Double getDeviceDay30() {
		return deviceDay30;
	}

	public void setDeviceDay30(Double deviceDay30) {
		this.deviceDay30 = deviceDay30;
	}

	public Double getGameCounts() {
		return gameCounts;
	}

	public void setGameCounts(Double gameCounts) {
		this.gameCounts = gameCounts;
	}

	public Double getGameTimes() {
		return gameTimes;
	}

	public void setGameTimes(Double gameTimes) {
		this.gameTimes = gameTimes;
	}

	public Double getTimesAVG() {
		return timesAVG;
	}

	public void setTimesAVG(Double timesAVG) {
		this.timesAVG = timesAVG;
	}

	public List<SummaryData> getDataList() {
		return dataList;
	}

	public void setDataList(List<SummaryData> dataList) {
		this.dataList = dataList;
	}

}
