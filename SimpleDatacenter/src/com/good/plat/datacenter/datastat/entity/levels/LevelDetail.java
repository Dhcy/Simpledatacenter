package com.good.plat.datacenter.datastat.entity.levels;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * @ClassName: LevelDetail
 * @Description: 等级详解
 * @author dmw
 * @date 2016年3月14日 下午4:41:16
 */
public class LevelDetail extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	public LevelDetail() {
	}
	
	//升级时长
	private Long times;
	private String timesDesc;
	//等级停滞率，百分比
	//private Double rate;
	
	//付费次数
	private Long payCount;
	
	//付费金额
	private Double payMoney;
	//停滞人数
	private Integer stallcnt;
	//到达等级或大于等级的总停滞数
	private Integer gtMaxlvlCnt;
	//所在等级人数
	private Integer maxlvlcnt;

	public Long getTimes() {
		return times;
	}

	public void setTimes(Long times) {
		this.times = times;
	}

	public Long getPayCount() {
		return payCount;
	}

	public void setPayCount(Long payCount) {
		this.payCount = payCount;
	}

	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	public String getTimesDesc() {
		return timesDesc;
	}

	public void setTimesDesc(String timesDesc) {
		this.timesDesc = timesDesc;
	}

	public Integer getStallcnt() {
		return stallcnt;
	}

	public void setStallcnt(Integer stallcnt) {
		this.stallcnt = stallcnt;
	}

	public Integer getGtMaxlvlCnt() {
		return gtMaxlvlCnt;
	}

	public void setGtMaxlvlCnt(Integer gtMaxlvlCnt) {
		this.gtMaxlvlCnt = gtMaxlvlCnt;
	}

	public Integer getMaxlvlcnt() {
		return maxlvlcnt;
	}

	public void setMaxlvlcnt(Integer maxlvlcnt) {
		this.maxlvlcnt = maxlvlcnt;
	}
	
	
	
}
