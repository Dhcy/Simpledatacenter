package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 祭坛
 * @ClassName: Altar
 * @Description: TODO
 * @author hwj
 * @date 2017-9-25 下午02:07:45
 */
public class Altar extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String country;//国家
	private Integer curtype;//货币类型
	private String curTypeName;//货币类型名称
	private Integer singleExtractTimes;//单抽次数
	private Integer tenExtractTimes;//十连抽次数
	private Integer singleExtractCost;//单抽物品数量
	private Integer tenExtractCost;//十连抽物品数量
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getSingleExtractTimes() {
		return singleExtractTimes;
	}
	public void setSingleExtractTimes(Integer singleExtractTimes) {
		this.singleExtractTimes = singleExtractTimes;
	}
	public Integer getTenExtractTimes() {
		return tenExtractTimes;
	}
	public void setTenExtractTimes(Integer tenExtractTimes) {
		this.tenExtractTimes = tenExtractTimes;
	}
	public Integer getSingleExtractCost() {
		return singleExtractCost;
	}
	public void setSingleExtractCost(Integer singleExtractCost) {
		this.singleExtractCost = singleExtractCost;
	}
	public Integer getTenExtractCost() {
		return tenExtractCost;
	}
	public void setTenExtractCost(Integer tenExtractCost) {
		this.tenExtractCost = tenExtractCost;
	}
	public Integer getCurtype() {
		return curtype;
	}
	public void setCurtype(Integer curtype) {
		this.curtype = curtype;
	}
	public String getCurTypeName() {
		return curTypeName;
	}
	public void setCurTypeName(String curTypeName) {
		this.curTypeName = curTypeName;
	}
	
	

}
