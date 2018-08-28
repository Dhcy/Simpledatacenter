package com.good.plat.datacenter.independ.entity.hie.index;

import java.io.Serializable;

public class HieOnLine implements Serializable{
	private String statdate;
	private Integer count;
	public String getStatdate() {
		return statdate;
	}
	public void setStatdate(String statdate) {
		this.statdate = statdate;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

}
