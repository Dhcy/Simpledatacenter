package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 充值查询
 * @ClassName: RechargeQuery
 * @Description: TODO
 * @author hwj
 * @date 2017-3-8 下午06:28:19
 */
public class RechargeQuery extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer termType;
	//设备id
	private String deviceId;
	//充值金额
	private Double money;
	//充值砖石
	private Integer count;
	//充值等级
	private Integer actorLevel;
	//充值日期
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date rechargeDate;
	//订单id
	private Long orderId;
	//来源系统
	private String sourceSys;
	//角色id
	private Integer actorId;
	//角色名称
	private String actorName;
	public Integer getTermType() {
		return termType;
	}
	public void setTermType(Integer termType) {
		this.termType = termType;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getActorLevel() {
		return actorLevel;
	}
	public void setActorLevel(Integer actorLevel) {
		this.actorLevel = actorLevel;
	}
	
	public Date getRechargeDate() {
		return rechargeDate;
	}
	public void setRechargeDate(Date rechargeDate) {
		this.rechargeDate = rechargeDate;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getSourceSys() {
		return sourceSys;
	}
	public void setSourceSys(String sourceSys) {
		this.sourceSys = sourceSys;
	}
	public Integer getActorId() {
		return actorId;
	}
	public void setActorId(Integer actorId) {
		this.actorId = actorId;
	}
	public String getActorName() {
		return actorName;
	}
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
	
	
	

}
