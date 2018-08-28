package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 活跃统计
 * @ClassName: ActiveStat
 * @Description: TODO
 * @author hwj
 * @date 2017-9-21 上午09:46:07
 */
public class ActiveStat extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String country;//国家
	private Integer activeGrade;//活跃度
	private Integer activeCount;//活跃度人数
	private Double activeRate;//活跃度人数占比
	
	/**回购*/
	private String goodsName;//回购物品名称
	private Integer times;//回购次数
	private Integer costDiamond;//消耗钻石数
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getActiveGrade() {
		return activeGrade;
	}
	public void setActiveGrade(Integer activeGrade) {
		this.activeGrade = activeGrade;
	}
	public Integer getActiveCount() {
		return activeCount;
	}
	public void setActiveCount(Integer activeCount) {
		this.activeCount = activeCount;
	}
	public Double getActiveRate() {
		return activeRate;
	}
	public void setActiveRate(Double activeRate) {
		this.activeRate = activeRate;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public Integer getCostDiamond() {
		return costDiamond;
	}
	public void setCostDiamond(Integer costDiamond) {
		this.costDiamond = costDiamond;
	}
	
	
	
	
	

}
