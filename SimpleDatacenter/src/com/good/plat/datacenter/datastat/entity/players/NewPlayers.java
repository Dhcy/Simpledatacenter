package com.good.plat.datacenter.datastat.entity.players;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * @ClassName: NewPlayers
 * @Description: 新增玩家
 * @author dmw
 * @date 2016年3月14日 下午1:54:44
 */
public class NewPlayers extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	public NewPlayers() {
	}
	
	/**
	 * 新增玩家
	 */
	//激活台数
	private Integer devices;
	
	//新增玩家
	private Integer newPlayers;
	
	//SUM设备
	private Integer deviceSUM;
	
	//SUM账户
	private Integer accountSUM;
	
	//AVG设备
	private Integer deviceAVG;
	
	//AVG账户
	private Integer accountAVG;
	
	//玩家转化
	private Double playerConv;
	
	//private String statType;
	
	//新增设备
	private Integer newDevice;
	
	//百分比
	private Double rate;
	
	//首次创角数
	private Integer regCnt;
	
	//区域
	private String city;
	
	//新增账号
	private Integer newUser;
	//时间段
	private Integer hourdate;
	//时间段描述
	private String hourDateDesc;
	
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

	public Integer getDeviceAVG() {
		return deviceAVG;
	}

	public void setDeviceAVG(Integer deviceAVG) {
		this.deviceAVG = deviceAVG;
	}

	public Integer getAccountAVG() {
		return accountAVG;
	}

	public void setAccountAVG(Integer accountAVG) {
		this.accountAVG = accountAVG;
	}

	public Double getPlayerConv() {
		return playerConv;
	}

	public void setPlayerConv(Double playerConv) {
		this.playerConv = playerConv;
	}

	public Integer getNewDevice() {
		return newDevice;
	}

	public void setNewDevice(Integer newDevice) {
		this.newDevice = newDevice;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Integer getRegCnt() {
		return regCnt;
	}

	public void setRegCnt(Integer regCnt) {
		this.regCnt = regCnt;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getNewUser() {
		return newUser;
	}

	public void setNewUser(Integer newUser) {
		this.newUser = newUser;
	}

	public Integer getHourdate() {
		return hourdate;
	}

	public void setHourdate(Integer hourdate) {
		this.hourdate = hourdate;
	}

	public String getHourDateDesc() {
		return hourDateDesc;
	}

	public void setHourDateDesc(String hourDateDesc) {
		this.hourDateDesc = hourDateDesc;
	}
	
	
	
	
	
	
	
}
