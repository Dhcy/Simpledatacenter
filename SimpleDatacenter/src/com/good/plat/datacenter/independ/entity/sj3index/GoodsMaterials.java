package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 物资
 * @ClassName: GoodsMaterials
 * @Description: TODO
 * @author hwj
 * @date 2017-9-25 下午03:50:01
 */
public class GoodsMaterials extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer limitTimeDiscountTotalNum;//限时折扣购买总人数
	private Integer dailySuppTotalNum;//每日物资购买总人数
	private Integer campSuppTotalNum;//阵营物资购买总人数
	private String shopName;//物品名称
	private Integer population;//购买人数
	private Integer purchases;//购买数量
	private Integer cost;//消耗钻石数
	public Integer getLimitTimeDiscountTotalNum() {
		return limitTimeDiscountTotalNum;
	}
	public void setLimitTimeDiscountTotalNum(Integer limitTimeDiscountTotalNum) {
		this.limitTimeDiscountTotalNum = limitTimeDiscountTotalNum;
	}
	public Integer getDailySuppTotalNum() {
		return dailySuppTotalNum;
	}
	public void setDailySuppTotalNum(Integer dailySuppTotalNum) {
		this.dailySuppTotalNum = dailySuppTotalNum;
	}
	public Integer getCampSuppTotalNum() {
		return campSuppTotalNum;
	}
	public void setCampSuppTotalNum(Integer campSuppTotalNum) {
		this.campSuppTotalNum = campSuppTotalNum;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getPopulation() {
		return population;
	}
	public void setPopulation(Integer population) {
		this.population = population;
	}
	public Integer getPurchases() {
		return purchases;
	}
	public void setPurchases(Integer purchases) {
		this.purchases = purchases;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	
	

}
