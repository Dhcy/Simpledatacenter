package com.good.plat.datacenter.game.entity;

import java.io.Serializable;

/**
 * 充值获取警告
 * @ClassName: RechgGainWarn
 * @Description: TODO
 * @author hwj
 * @date 2017-8-15 上午11:19:57
 */
public class RechgGainWarn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3132591569481087056L;
	private Integer id;
	
	private Integer rechgTime;//充值次数
	
	private Integer rechgMoney;//充值金额
	
	private Integer gainTime;//获取次数
	
	private Integer gainCount;//获取总额
	
	private Integer warmThreShold;//告警阀值
	
	private String msgMail;//告警邮箱
	
	private Integer gameId;//游戏
	
	private Integer rechgRate;//比例
	
	private String gameName;//游戏名称
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRechgTime() {
		return rechgTime;
	}
	public void setRechgTime(Integer rechgTime) {
		this.rechgTime = rechgTime;
	}
	public Integer getRechgMoney() {
		return rechgMoney;
	}
	public void setRechgMoney(Integer rechgMoney) {
		this.rechgMoney = rechgMoney;
	}
	public Integer getGainTime() {
		return gainTime;
	}
	public void setGainTime(Integer gainTime) {
		this.gainTime = gainTime;
	}
	public Integer getGainCount() {
		return gainCount;
	}
	public void setGainCount(Integer gainCount) {
		this.gainCount = gainCount;
	}
	public Integer getWarmThreShold() {
		return warmThreShold;
	}
	public void setWarmThreShold(Integer warmThreShold) {
		this.warmThreShold = warmThreShold;
	}
	public String getMsgMail() {
		return msgMail;
	}
	public void setMsgMail(String msgMail) {
		this.msgMail = msgMail;
	}
	public Integer getGameId() {
		return gameId;
	}
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
	public Integer getRechgRate() {
		return rechgRate;
	}
	public void setRechgRate(Integer rechgRate) {
		this.rechgRate = rechgRate;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	

	

}
