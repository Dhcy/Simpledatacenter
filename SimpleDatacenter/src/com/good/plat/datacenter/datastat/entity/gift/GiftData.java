package com.good.plat.datacenter.datastat.entity.gift;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

public class GiftData extends BaseEntity implements Serializable{
	
	private String giftName;
	private Integer count;
	

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public GiftData() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
