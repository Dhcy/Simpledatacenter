package com.good.plat.datacenter.datastat.entity.players;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 滚服
 * @ClassName: RollServer
 * @Description: TODO
 * @author hwj
 * @date 2017-1-11 下午01:45:17
 */
public class RollServer implements Serializable {

	private static final long serialVersionUID = 1L;
	//日期
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date statDate;
	//总新增滚服数
	private Integer across_cnt;
	//总充值人数
	private Integer recharge_cnt;
	//总滚服充值人数
	private Integer acr_rcg_cnt;
	//总充值金额
	private Integer recharge_money;
	//总滚服充值金额
	private Integer across_recharge_money;
	//人数占比(滚服充值玩家在总充值玩家中的占比)
	private Double cntRate;
	//充值占比(滚服充值金额在总充值金额中的占比)
	private Double moneyRate;
	public Date getStatDate() {
		return statDate;
	}
	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}
	public Integer getAcross_cnt() {
		return across_cnt;
	}
	public void setAcross_cnt(Integer acrossCnt) {
		across_cnt = acrossCnt;
	}
	public Integer getRecharge_cnt() {
		return recharge_cnt;
	}
	public void setRecharge_cnt(Integer rechargeCnt) {
		recharge_cnt = rechargeCnt;
	}
	public Integer getAcr_rcg_cnt() {
		return acr_rcg_cnt;
	}
	public void setAcr_rcg_cnt(Integer acrRcgCnt) {
		acr_rcg_cnt = acrRcgCnt;
	}
	public Integer getRecharge_money() {
		return recharge_money;
	}
	public void setRecharge_money(Integer rechargeMoney) {
		recharge_money = rechargeMoney;
	}
	public Integer getAcross_recharge_money() {
		return across_recharge_money;
	}
	public void setAcross_recharge_money(Integer acrossRechargeMoney) {
		across_recharge_money = acrossRechargeMoney;
	}
	public Double getCntRate() {
		return cntRate;
	}
	public void setCntRate(Double cntRate) {
		this.cntRate = cntRate;
	}
	public Double getMoneyRate() {
		return moneyRate;
	}
	public void setMoneyRate(Double moneyRate) {
		this.moneyRate = moneyRate;
	}
	
	
	
	
	

}
