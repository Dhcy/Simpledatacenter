package com.good.plat.datacenter.datastat.entity.channels;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * @ClassName: ChannelData
 * @Description: 渠道数据
 * @author dmw
 * @date 2016年3月14日 下午4:07:57
 */
public class ChannelData extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public ChannelData() {
	}
	
	//跟踪方式
	private String trackMode;
	
	//设备激活
	private Integer devices;
	
	//新增玩家
	private Integer newPlayers;
	
	//注册转化率
	private Double regCon;
	
	//平均日活跃
	private Integer dailyActive;
	
	//一日玩家比例
	private Integer oneDayPlayer;
	
	//平均次日留存率
	private Double day1Retention;
	
	//首周付费比例
	private Double firstWeekCon;
	
	//收入
	private Double revenue;
	
	//付费账户
	private Integer paidAccounts;
	
	//日均付费率
	private Double dailyCon;
	
	//日均ARPU
	private Double dailyAvgARPU;
	
	//日均ARPPU
	private Double dailyAvgARPPU;
	
	//活跃玩家
	private Integer activeAccounts;
	
	private Double payRate;
	private Double retentionRate;
	
	private Map<String,List<Map<String,Object>>> trend = new HashMap<String,List<Map<String,Object>>>();

	public String getTrackMode() {
		return trackMode;
	}

	public void setTrackMode(String trackMode) {
		this.trackMode = trackMode;
	}

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

	public Double getRegCon() {
		return regCon;
	}

	public void setRegCon(Double regCon) {
		this.regCon = regCon;
	}

	public Integer getDailyActive() {
		return dailyActive;
	}

	public void setDailyActive(Integer dailyActive) {
		this.dailyActive = dailyActive;
	}

	public Integer getOneDayPlayer() {
		return oneDayPlayer;
	}

	public void setOneDayPlayer(Integer oneDayPlayer) {
		this.oneDayPlayer = oneDayPlayer;
	}

	public Double getDay1Retention() {
		return day1Retention;
	}

	public void setDay1Retention(Double day1Retention) {
		this.day1Retention = day1Retention;
	}

	public Double getFirstWeekCon() {
		return firstWeekCon;
	}

	public void setFirstWeekCon(Double firstWeekCon) {
		this.firstWeekCon = firstWeekCon;
	}

	public Double getRevenue() {
		return revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	public Integer getPaidAccounts() {
		return paidAccounts;
	}

	public void setPaidAccounts(Integer paidAccounts) {
		this.paidAccounts = paidAccounts;
	}

	public Double getDailyCon() {
		return dailyCon;
	}

	public void setDailyCon(Double dailyCon) {
		this.dailyCon = dailyCon;
	}

	public Double getDailyAvgARPU() {
		return dailyAvgARPU;
	}

	public void setDailyAvgARPU(Double dailyAvgARPU) {
		this.dailyAvgARPU = dailyAvgARPU;
	}

	public Double getDailyAvgARPPU() {
		return dailyAvgARPPU;
	}

	public void setDailyAvgARPPU(Double dailyAvgARPPU) {
		this.dailyAvgARPPU = dailyAvgARPPU;
	}

	public Integer getActiveAccounts() {
		return activeAccounts;
	}

	public void setActiveAccounts(Integer activeAccounts) {
		this.activeAccounts = activeAccounts;
	}

	public Map<String, List<Map<String, Object>>> getTrend() {
		return trend;
	}

	public void setTrend(Map<String, List<Map<String, Object>>> trend) {
		this.trend = trend;
	}

	public Double getPayRate() {
		return payRate;
	}

	public void setPayRate(Double payRate) {
		this.payRate = payRate;
	}

	public Double getRetentionRate() {
		return retentionRate;
	}

	public void setRetentionRate(Double retentionRate) {
		this.retentionRate = retentionRate;
	}
	

}
